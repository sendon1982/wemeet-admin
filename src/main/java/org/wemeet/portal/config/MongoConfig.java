package org.wemeet.portal.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.connection.ConnectionPoolSettings;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import tech.jhipster.domain.util.JSR310DateConverters;

// TODO: need to use this make it work
@Configuration
public class MongoConfig {

    @Value("${wemeetadmin.mongodb.name}")
    private String mongodbName;

    @Value("${spring.data.mongodb.uri}")
    private String connectionString;

    @Bean
    public MongoCustomConversions customConversions() {
        List<Converter<?, ?>> converters = new ArrayList<>();
        converters.add(JSR310DateConverters.DateToZonedDateTimeConverter.INSTANCE);
        converters.add(JSR310DateConverters.ZonedDateTimeToDateConverter.INSTANCE);
        return new MongoCustomConversions(converters);
    }

    @Bean
    public MongoTemplate mongoTemplate(MongoClient mongoClient) {
        return new MongoTemplate(mongoClient, mongodbName);
    }

    @Bean
    public MongoClient mongoClient(MongoClientSettings mongoClientSettings) {
        return MongoClients.create(mongoClientSettings);
    }

    @Bean
    public MongoClientSettings mongoClientSettings() {
        return MongoClientSettings.builder()
            .applyConnectionString(new ConnectionString(connectionString))
            .retryReads(true)
            .retryWrites(true)
            .applyToConnectionPoolSettings((ConnectionPoolSettings.Builder builder) -> {
                builder
                    .maxSize(16)
                    .minSize(8)
                    .maxConnectionLifeTime(120, TimeUnit.SECONDS)
                    .maxConnectionIdleTime(120, TimeUnit.SECONDS)
                    .maxWaitTime(120, TimeUnit.SECONDS);
            })
            .applyToServerSettings(builder -> {
                builder.heartbeatFrequency(60, TimeUnit.SECONDS);
            })
            .applyToSocketSettings(builder -> {
                builder.connectTimeout(120, TimeUnit.SECONDS).readTimeout(120, TimeUnit.SECONDS);
            })
            .build();
    }
}
