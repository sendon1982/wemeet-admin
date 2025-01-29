package org.wemeet.portal.config;

import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.impl.conn.SystemDefaultRoutePlanner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    // Configuration parameters
    private static final int MAX_TOTAL_CONNECTIONS = 512;
    private static final int MAX_CONNECTIONS_PER_ROUTE = 64;
    private static final int TIMEOUT = 6000 * 100; // 600 seconds

    @Bean
    public RestTemplate restTemplate() {
        // Setup connection manager
        PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager();

        // Configure the max connections per route
        poolingHttpClientConnectionManager.setDefaultMaxPerRoute(MAX_CONNECTIONS_PER_ROUTE);
        // Set the max total connections
        poolingHttpClientConnectionManager.setMaxTotal(MAX_TOTAL_CONNECTIONS);

        // Configure timeouts
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(TIMEOUT).setSocketTimeout(TIMEOUT).build();

        // Create the HttpClient with the connection manager and timeouts
        CloseableHttpClient httpClient = HttpClients.custom()
            .setDefaultRequestConfig(requestConfig)
            .setConnectionManager(poolingHttpClientConnectionManager)
            .build();

        // Return the RestTemplate
        return new RestTemplate(new HttpComponentsClientHttpRequestFactory(httpClient));
    }
}
