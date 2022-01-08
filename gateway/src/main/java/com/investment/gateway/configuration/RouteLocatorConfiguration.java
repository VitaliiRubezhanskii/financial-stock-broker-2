package com.investment.gateway.configuration;


import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.gateway.filter.factory.TokenRelayGatewayFilterFactory;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class RouteLocatorConfiguration {

    private final TokenRelayGatewayFilterFactory filterFactory;

    @Bean
    @LoadBalanced
    public RestTemplate loadBalancedRestTemplate() {
        return new RestTemplate();
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes().route(route -> route.path("/feign/**")
                        .filters(f-> f.stripPrefix(1).filters(filterFactory.apply()).removeRequestHeader("Cookie"))
                        .uri("http://localhost:8091")).build();
    }

}
