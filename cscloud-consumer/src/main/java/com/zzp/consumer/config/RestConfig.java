package com.zzp.consumer.config;

import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.apache.http.impl.nio.reactor.IOReactorConfig;
import org.apache.http.message.BasicHeader;
import org.elasticsearch.client.Node;
import org.elasticsearch.client.NodeSelector;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;

/**
 * @ClassName RestConfig
 * @Description
 * @Date
 * @Author Administrator
 **/
@Configuration
public class RestConfig {
    @Bean
    public RestClient getClient() throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
        // 如果有多个从节点可以持续在内部new多个HttpHost，参数1是ip,参数2是HTTP端口，参数3是通信协议
        RestClientBuilder clientBuilder = RestClient.builder(new HttpHost("192.168.145.12", 9200, "http"));

        // 添加其他配置，返回来的还是RestClientBuilder对象，这些配置都是可选的
        // clientBuilder.setXX()...
        // 设置请求头，每个请求都会带上这个请求头
        Header[] defaultHeaders = {new BasicHeader("header", "value")};
        clientBuilder.setDefaultHeaders(defaultHeaders);
        // 设置超时时间，多次尝试同一请求时应该遵守的超时。默认值为30秒，与默认套接字超时相同。若自定义套接字超时，则应相应地调整最大重试超时
        clientBuilder.setMaxRetryTimeoutMillis(60000);

        // 设置监听器，每次节点失败都可以监听到，可以作额外处理
        clientBuilder.setFailureListener(new RestClient.FailureListener() {
            @Override
            public void onFailure(Node node) {
                super.onFailure(node);
                System.out.println(node.getName() + "==节点失败了");
            }
        });
        clientBuilder.setNodeSelector(NodeSelector.SKIP_DEDICATED_MASTERS);

        // 进行详细的配置
        clientBuilder.setNodeSelector(new NodeSelector() {
            // 设置分配感知节点选择器，允许选择本地机架中的节点（如果有），否则转到任何机架中的任何其他节点。
            @Override
            public void select(Iterable<Node> nodes) {
                boolean foundOne = false;
                for (Node node: nodes) {
                    String rackId = node.getAttributes().get("rack_id").get(0);
                    if ("rack_one".equals(rackId)) {
                        foundOne = true;
                        break;
                    }
                }
                if (foundOne) {
                    Iterator<Node> nodesIt = nodes.iterator();
                    while (nodesIt.hasNext()) {
                        Node node = nodesIt.next();
                        String rackId = node.getAttributes().get("rack_id").get(0);
                        if ("rack_one".equals(rackId) == false) {
                            nodesIt.remove();
                        }
                    }
                }
            }
        });

        clientBuilder.setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
            @Override
            public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpAsyncClientBuilder) {
                return httpAsyncClientBuilder.setDefaultIOReactorConfig(
                        IOReactorConfig.custom().setIoThreadCount(1).build()
                );
            }
        });

        /*
    配置请求超时，将连接超时（默认为1秒）和套接字超时（默认为30秒）增加，
    这里配置完应该相应地调整最大重试超时（默认为30秒），即上面的setMaxRetryTimeoutMillis，一般于最大的那个值一致即60000
    */
        clientBuilder.setRequestConfigCallback(new RestClientBuilder.RequestConfigCallback() {
            @Override
            public RequestConfig.Builder customizeRequestConfig(RequestConfig.Builder requestConfigBuilder) {
                // 连接5秒超时，套接字连接60s超时
                return requestConfigBuilder.setConnectTimeout(5000).setSocketTimeout(60000);
            }
        });


        // 最后配置好的clientBuilder再build一下即可得到真正的Client
        return clientBuilder.build();
    }
}
