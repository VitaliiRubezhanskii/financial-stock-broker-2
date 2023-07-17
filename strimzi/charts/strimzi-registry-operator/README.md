# strimzi-registry-operator

![Version: 2.1.0](https://img.shields.io/badge/Version-2.1.0-informational?style=flat-square) ![Type: application](https://img.shields.io/badge/Type-application-informational?style=flat-square) ![AppVersion: 0.6.0](https://img.shields.io/badge/AppVersion-0.6.0-informational?style=flat-square)

Operator to create and manage a Confluent Schema Registry in a Strimzi-managed Kafka cluster.

**Homepage:** <https://github.com/lsst-sqre/strimzi-registry-operator>

## Maintainers

| Name | Email | Url |
| ---- | ------ | --- |
| swnelson |  |  |
| jonathansick |  |  |

## Values

| Key | Type | Default | Description |
|-----|------|---------|-------------|
| clusterName | string | `""` | Name of the Strimzi Kafka cluster |
| clusterNamespace | string | `"default"` | Namespace where the Strimzi Kafka cluster is deployed |
| image.repository | string | `"ghcr.io/lsst-sqre/strimzi-registry-operator"` | The repository for the container with the operator application |
| image.tag | string | The appVersion of the chart | Tag of the image |
| operatorNamespace | string | `"strimzi-registry-operator"` | Namespace where the strimzi-registry-operator is deployed |

----------------------------------------------
Autogenerated from chart metadata using [helm-docs v1.11.0](https://github.com/norwoodj/helm-docs/releases/v1.11.0)