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
        if(System.getenv('OPENSHIFT_MONGODB_DB_HOST')!= null){
            String openshiftMongoDbHost = System.getenv("OPENSHIFT_MONGODB_DB_HOST");
            int openshiftMongoDbPort = Integer.parseInt(System.getenv("OPENSHIFT_MONGODB_DB_PORT"));
            return new Mongo(openshiftMongoDbHost, openshiftMongoDbPort);
        }else{
            return new Mongo()
        }
    }
}
