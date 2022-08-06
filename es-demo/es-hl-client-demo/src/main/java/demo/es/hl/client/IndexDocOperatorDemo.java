package demo.es.hl.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHost;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;

/**
 * 索引文档操作 Demo
 */
public class IndexDocOperatorDemo {

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

        // 创建（索引）文档
        // createDoc(client);

        // 更新文档
        // updateDoc(client);

        // 查找文档
        // getDoc(client);

        // 删除文档
        // deleteDec(client);

        closeClient(client);
    }

    public static void createDoc(RestHighLevelClient client) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        User user = new User("张三", 20, "广东广州");
        String userJson = mapper.writeValueAsString(user);

        IndexRequest request = new IndexRequest();
        request.index("user").id("1001");
        request.source(userJson, XContentType.JSON);

        IndexResponse response = client.index(request, RequestOptions.DEFAULT);

        System.out.println(response.getResult());
    }

    public static void updateDoc(RestHighLevelClient client) throws IOException {

        UpdateRequest request = new UpdateRequest();
        request.index("user").id("1001");
        request.doc(XContentType.JSON, "addr", "广东汕头");

        UpdateResponse response = client.update(request, RequestOptions.DEFAULT);

        System.out.println(response.getResult());
    }

    public static void getDoc(RestHighLevelClient client) throws IOException {

        GetRequest request = new GetRequest();
        request.index("user").id("1001");

        GetResponse response = client.get(request, RequestOptions.DEFAULT);
        System.out.println(response.getSourceAsString());
    }

    public static void deleteDec(RestHighLevelClient client) throws IOException {

        DeleteRequest request = new DeleteRequest();
        request.index("user").id("1001");

        DeleteResponse response = client.delete(request, RequestOptions.DEFAULT);

        System.out.println(response.getResult());
    }
}
