terraform {
  required_providers {
    kind = {
      source = "tehcyx/kind"
      version = "0.0.12"
    }
    kubectl = {
      source  = "gavinbunney/kubectl"
      version = ">= 1.7.0"
    }
  }
}

provider "kind" {}

resource "kind_cluster" "default" {
  name = "cluster-1"
  wait_for_ready = true
  kind_config {
    kind = "Cluster"
    api_version = "kind.x-k8s.io/v1alpha4"

    node {
      role = "control-plane"
    }

    node {
      role = "worker"
      image = "kindest/node:v1.23.4"
    }

    node {
      role = "worker"
      image = "kindest/node:v1.23.4"
    }

    node {
      role = "worker"
      image = "kindest/node:v1.23.4"
    }
  }
}

provider "kubernetes" {
  host = "${kind_cluster.default.endpoint}"
  cluster_ca_certificate = "${kind_cluster.default.cluster_ca_certificate}"
  client_certificate = "${kind_cluster.default.client_certificate}"
  client_key = "${kind_cluster.default.client_key}"
}

provider "kubectl" {
  host = "${kind_cluster.default.endpoint}"
  cluster_ca_certificate = "${kind_cluster.default.cluster_ca_certificate}"
  client_certificate = "${kind_cluster.default.client_certificate}"
  client_key = "${kind_cluster.default.client_key}"
}

provider "helm" {
  kubernetes {
    host = "${kind_cluster.default.endpoint}"
    cluster_ca_certificate = "${kind_cluster.default.cluster_ca_certificate}"
    client_certificate = "${kind_cluster.default.client_certificate}"
    client_key = "${kind_cluster.default.client_key}"
  }
}

data "kubectl_file_documents" "crds" {
  content = file("olm/crds.yaml")
}


resource "kubectl_manifest" "crds_apply" {
  for_each  = data.kubectl_file_documents.crds.manifests
  yaml_body = each.value
  wait = true
  server_side_apply = true
}

data "kubectl_file_documents" "olm" {
  content = file("olm/olm.yaml")
}

resource "kubectl_manifest" "olm_apply" {
  depends_on = [data.kubectl_file_documents.crds]
  for_each  = data.kubectl_file_documents.olm.manifests
  yaml_body = each.value

}

resource "kubernetes_namespace" "kafka" {
  metadata {
    name = "kafka"
  }
}

resource "kubernetes_secret" "realm-secret" {

  metadata {
    name      = "realm-secret"
    namespace = kubernetes_namespace.kafka.metadata[0].name
  }
  type = "Opaque"
  data = {
    "realm.json" = file("${path.cwd}/keycloak/realm.json")
  }

  depends_on = [kubernetes_namespace.kafka]
}

resource "kubernetes_cluster_role_v1" "cluster-role" {
  metadata {
    name = "namespace-reader"
  }
  rule {
    verbs = ["get", "list", "watch"]
    resources = ["configmaps", "pods", "services", "endpoints", "secrets"]
    api_groups = ["", "extensions", "apps"]
  }
}

resource "kubernetes_cluster_role_binding_v1" "cluster-role-binding" {
  metadata {
    name = "namespace-reader-binding"
  }
  subject {
    kind = "ServiceAccount"
    name = "default"
    api_group = ""
    namespace = "kafka"
  }

  role_ref {
    kind      = "ClusterRole"
    name      = kubernetes_cluster_role_v1.cluster-role.metadata[0].name
    api_group = "rbac.authorization.k8s.io"
  }
  depends_on = [kubernetes_cluster_role_v1.cluster-role]
}

resource "helm_release" "argocd" {
  name  = "argocd"

  repository       = "https://argoproj.github.io/argo-helm"
  chart            = "argo-cd"
  namespace        = "argocd"
  version          = "4.9.7"
  create_namespace = true

  values = [
    file("argocd/application.yaml")
  ]
}

resource "helm_release" "rbac" {
  chart = "cluster-rbac"
  repository  = "https://vitaliirubezhanskii.github.io/financial-stock-broker-2-helm-charts"
  name = "cluster-rbac"
  namespace = kubernetes_namespace.kafka.metadata[0].name
  version = "1.0.0"
  create_namespace = true
  verify = false
}

resource "helm_release" "postgres" {
    chart = "postgresql"
    name = "postgres"
    version = "11.6.19"
    timeout = 600
    repository = "https://charts.bitnami.com/bitnami"
    namespace = kubernetes_namespace.kafka.metadata[0].name

    values = [
      file("${path.cwd}/postgres/values.yml")
    ]
  depends_on = [kubernetes_namespace.kafka]
}

resource "helm_release" "keycloak" {
  chart      = "keycloak"
  repository = "https://vitaliirubezhanskii.github.io/financial-stock-broker-2-helm-charts"
  name       = "keycloak"
  namespace = kubernetes_namespace.kafka.metadata[0].name
  version    = "18.4.4"
  create_namespace = true
  verify = false
  depends_on = [kubernetes_secret.realm-secret, helm_release.postgres, helm_release.rbac]
}

resource "helm_release" "account" {
  chart = "account"
  repository = "https://vitaliirubezhanskii.github.io/financial-stock-broker-2-helm-charts"
  name  = "account-service"
  namespace = kubernetes_namespace.kafka.metadata[0].name
  version = "0.4.1"
  create_namespace = true
  verify = false
  depends_on = [helm_release.postgres, helm_release.rbac, helm_release.keycloak, kubernetes_cluster_role_binding_v1.cluster-role-binding]
}

resource "helm_release" "order" {
  chart = "order"
  repository = "https://vitaliirubezhanskii.github.io/financial-stock-broker-2-helm-charts"
  name  = "order-service"
  namespace = kubernetes_namespace.kafka.metadata[0].name
  version = "0.4.1"
  create_namespace = true
  verify = false
  depends_on = [helm_release.postgres, helm_release.rbac, helm_release.keycloak, kubernetes_cluster_role_binding_v1.cluster-role-binding]
}

resource "helm_release" "gateway" {
  chart = "gateway"
  repository = "https://vitaliirubezhanskii.github.io/financial-stock-broker-2-helm-charts"
  name  = "gateway"
  namespace = kubernetes_namespace.kafka.metadata[0].name
  version = "0.4.1"
  create_namespace = true
  verify = false
  depends_on = [helm_release.postgres, helm_release.rbac, helm_release.keycloak, kubernetes_cluster_role_binding_v1.cluster-role-binding]
}

resource "helm_release" "quotes-provider" {
  chart = "quotes-provider"
  repository = "https://vitaliirubezhanskii.github.io/financial-stock-broker-2-helm-charts"
  name  = "quotes-provider"
  namespace = kubernetes_namespace.kafka.metadata[0].name
  version = "0.4.1"
  create_namespace = true
  verify = false
  depends_on = [helm_release.postgres, helm_release.rbac, helm_release.keycloak, kubernetes_cluster_role_binding_v1.cluster-role-binding]
}

resource "helm_release" "analytics" {
  chart = "analytics"
  repository = "https://vitaliirubezhanskii.github.io/financial-stock-broker-2-helm-charts"
  name  = "analytics"
  namespace = kubernetes_namespace.kafka.metadata[0].name
  version = "0.4.1"
  create_namespace = true
  verify = false
  depends_on = [helm_release.postgres, helm_release.rbac, helm_release.keycloak, kubernetes_cluster_role_binding_v1.cluster-role-binding]
}