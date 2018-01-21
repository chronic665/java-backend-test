package com.yoti.application.config;

import com.mongodb.MongoClient;
import com.yoti.application.repo.RoomCleaningRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

import static com.yoti.application.config.PersistenceConfig.PERSISTENCE_PROFILE;

@Configuration
@EnableReactiveMongoRepositories(basePackageClasses = RoomCleaningRepository.class)
@Profile(PERSISTENCE_PROFILE)
public class PersistenceConfig extends AbstractMongoConfiguration {

    public static final String PERSISTENCE_PROFILE = "mongodb";
    private final Environment environment;

    public PersistenceConfig(Environment environment) {
        this.environment = environment;
    }

    @Override
    public MongoClient mongoClient() {
        int port = environment.getProperty("local.mongo.port", Integer.class);
        return new MongoClient("127.0.0.1", port);
    }

    @Override
    protected String getDatabaseName() {
        return "hoover";
    }
}