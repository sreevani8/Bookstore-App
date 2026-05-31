package com.mulit.bookstore.webapp.clients;

import com.mulit.bookstore.webapp.ApplicationProperties;
import com.mulit.bookstore.webapp.clients.catalog.CatalogServiceClient;
import com.mulit.bookstore.webapp.clients.orders.OrderServiceClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
class ClientsConfig {
    private final ApplicationProperties properties;

    ClientsConfig(ApplicationProperties properties) {
        this.properties = properties;
    }

    /*@Bean
    RestClientCustomizer restClientCustomizer() {
        var requestFactory = ClientHttpRequestFactoryBuilder.simple()
                .withCustomizer(c -> {
                    c.setConnectTimeout(Duration.ofSeconds(5));
                    c.setReadTimeout(Duration.ofSeconds(5));
                })
                .build();
        return restClientBuilder ->
                restClientBuilder.baseUrl(properties.apiGatewayUrl()).requestFactory(requestFactory);
    }*/

    @Bean
    CatalogServiceClient catalogServiceClient() {
        RestClient restClient = RestClient.create(properties.apiGatewayUrl());
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(RestClientAdapter.create(restClient))
                .build();
        return factory.createClient(CatalogServiceClient.class);
    }

    @Bean
    OrderServiceClient orderServiceClient() {
        RestClient restClient = RestClient.create(properties.apiGatewayUrl());
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(RestClientAdapter.create(restClient))
                .build();
        return factory.createClient(OrderServiceClient.class);
    }
}
