package com.yoti.application.repo;

import com.yoti.application.entity.RoomCleaning;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import static com.yoti.application.config.PersistenceConfig.PERSISTENCE_PROFILE;

@Profile(PERSISTENCE_PROFILE)
@RepositoryRestResource(collectionResourceRel = "history", path = "history")
public interface RoomCleaningRepository extends MongoRepository<RoomCleaning, String> {
}
