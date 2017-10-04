package cl.tuxy.repository

import cl.tuxy.model.Advisor
import org.springframework.data.mongodb.repository.MongoRepository

/**
 * Created by josebovet on 9/15/15.
 */
interface AdvisorRepository extends MongoRepository<Advisor, String> {

    Advisor findByPackageName(String packageName)

    List<Advisor> findByYear(int year)

    Long deleteAdvisorByYear(int year)

}