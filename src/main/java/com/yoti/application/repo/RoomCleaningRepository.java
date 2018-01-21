package com.yoti.application.repo;

import com.yoti.application.entity.RoomCleaning;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;

import static com.yoti.application.config.PersistenceConfig.PERSISTENCE_PROFILE;

@Profile(PERSISTENCE_PROFILE)
public interface RoomCleaningRepository extends MongoRepository<RoomCleaning, String> {
}
