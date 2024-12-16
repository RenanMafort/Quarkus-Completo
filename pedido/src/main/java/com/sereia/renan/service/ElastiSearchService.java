package com.sereia.renan.service;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.xcontent.XContentType;

@ApplicationScoped
public class ElastiSearchService {

//    private RestHighLevelClient client;
//
//    void startup(@Observes StartupEvent startupEvent) {
//        client = new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", 9200, "http")));
//    }
//
//    void shutdown(@Observes ShutdownEvent shutdownEvent) {
//        try {
//            if (client != null) {
//                client.close();
//            }
//        }catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void index(String index,String json){
//        IndexRequest indexRequest = new IndexRequest(index).source(json, XContentType.JSON);
//        indexRequest.index();
//    }

}


