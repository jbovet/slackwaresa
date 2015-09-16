package cl.kleedy.config

import cl.kleedy.repository.AdvisorRepository
import com.mongodb.Mongo
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.config.AbstractMongoConfiguration
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories

/**
 * Created by josebovet on 9/15/15.
 */
@Configuration
@EnableMongoRepositories(basePackageClasses = AdvisorRepository.class)
class MongoConfig extends AbstractMongoConfiguration {


    @Override
    protected String getDatabaseName() {
        return "advisors"
    }

    @Override
    Mongo mongo() throws Exception {
        return new Mongo()
    }
}
