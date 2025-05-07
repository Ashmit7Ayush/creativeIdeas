package com.creativeIdeas.indexing.config;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ElasticSearchClientConfig {
    @Value("${elasticsearch.host}")
    private String host;

    @Bean
    public RestClient restClient(){
        return RestClient.builder(new HttpHost(host, 9200, "http")).build();
    }

    @Bean
    public ElasticsearchClient elasticsearchClient(RestClient restClient){
        ElasticsearchTransport transport = new RestClientTransport(
                restClient, new JacksonJsonpMapper()
        );
        return new ElasticsearchClient(transport);
    }

}
