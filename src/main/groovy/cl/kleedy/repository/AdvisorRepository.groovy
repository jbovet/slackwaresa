package cl.kleedy.repository

import cl.kleedy.model.Advisor
import org.springframework.data.mongodb.repository.MongoRepository

/**
 * Created by josebovet on 9/15/15.
 */
interface AdvisorRepository extends MongoRepository<Advisor, String> {

    def Advisor findByPackageName(String packageName)

    def List<Advisor> findByYear(int year)

}