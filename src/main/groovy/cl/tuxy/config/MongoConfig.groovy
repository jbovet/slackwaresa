package cl.tuxy.config

import cl.tuxy.repository.AdvisorRepository
import com.mongodb.Mongo
import com.mongodb.MongoClient
import com.mongodb.MongoCredential
import com.mongodb.ServerAddress
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.config.AbstractMongoConfiguration
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper
import org.springframework.data.mongodb.core.convert.MappingMongoConverter
import org.springframework.data.mongodb.core.mapping.MongoMappingContext
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories

/**
 * Created by josebovet on 9/15/15.
 */
@Configuration
@EnableMongoRepositories(basePackageClasses = AdvisorRepository.class)
class MongoConfig extends AbstractMongoConfiguration {


    @Value("#{systemEnvironment['MONGODB_DB_HOST']}")
    String mongoHost = "localhost"

    @Value("#{systemEnvironment['MONGODB_DB_PORT']}")
    String mongoPort = "27017"

    @Value("#{systemEnvironment['APP_NAME']}")
    String mongoDbName = "advisors"

    @Value("#{systemEnvironment['MONGODB_DB_USERNAME']}")
    String mongoUsername = ''

    @Value("#{systemEnvironment['MONGODB_DB_PASSWORD']}")
    String mongoPassword = ''

    @Override
    protected String getDatabaseName() {
        mongoDbName
    }

    @Override
    Mongo mongo() throws Exception {
        def serverAddress = new ServerAddress(mongoHost, mongoPort.toInteger())
        if (mongoUsername && mongoPassword) {
            def credential = MongoCredential.createScramSha1Credential(mongoUsername, mongoDbName, mongoPassword.toCharArray())
            new MongoClient(serverAddress, [credential])
        } else {
            new MongoClient(serverAddress)
        }
    }

    @Bean
    MongoTemplate mongoTemplate() throws Exception {
        def converter = new MappingMongoConverter(mongoDbFactory(), new MongoMappingContext())
        converter.setTypeMapper(new DefaultMongoTypeMapper(null))
        new MongoTemplate(mongoDbFactory(), converter)
    }
}
