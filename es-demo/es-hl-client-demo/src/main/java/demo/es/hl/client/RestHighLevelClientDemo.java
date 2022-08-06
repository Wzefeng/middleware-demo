package demo.es.hl.client;


import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;

/**
 * ES {@link  RestHighLevelClient} Demo
 */
public class RestHighLevelClientDemo {

    public static void main(String[] args) throws IOException {
        String hostname = "192.168.137.132";
        int port = 9200;
        RestClientBuilder restClientBuilder = RestClient.builder(new HttpHost(hostname, port));

        // 创建并建立 ES 客户端连接
        RestHighLevelClient esClient = new RestHighLevelClient(restClientBuilder);

        // 关闭 ES 客户端连接
        esClient.close();
    }
}

