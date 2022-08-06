package demo.es.hl.client;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;

import java.io.IOException;

/**
 * ES 索引操作 Demo
 */
public class IndexOperatorDemo {

    private static RestHighLevelClient createClient() {
        String hostname = "192.168.137.132";
        int port = 9200;
        RestClientBuilder restClientBuilder = RestClient.builder(new HttpHost(hostname, port));

        // 创建并建立 ES 客户端连接
        return new RestHighLevelClient(restClientBuilder);
    }

    private static void closeClient(RestHighLevelClient client) throws IOException {
        client.close();
    }

    public static void main(String[] args) throws IOException {
        RestHighLevelClient client = createClient();

        // 创建索引
        CreateIndexRequest request = new CreateIndexRequest("user");
        CreateIndexResponse createIndexResponse = client.indices().create(request, RequestOptions.DEFAULT);

        System.out.println("索引创建：" + (createIndexResponse.isAcknowledged() ? "成功" : "失败"));

        closeClient(client);
    }

}
