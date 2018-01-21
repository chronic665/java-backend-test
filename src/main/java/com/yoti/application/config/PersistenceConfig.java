package com.yoti.application.config;

import com.yoti.application.repo.RoomCleaningRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import static com.yoti.application.config.PersistenceConfig.PERSISTENCE_PROFILE;

@Configuration
@EnableMongoRepositories(basePackageClasses = RoomCleaningRepository.class)
@Profile(PERSISTENCE_PROFILE)
public class PersistenceConfig {

    public static final String PERSISTENCE_PROFILE = "mongodb";
}