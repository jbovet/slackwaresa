package cl.tuxy.model

import org.springframework.data.annotation.Id

/**
 * Created by josebovet on 9/15/15.
 */
class Advisor {
    @Id
    String id
    Date date
    String packageName
    String url
    int year
}
