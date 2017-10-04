package cl.tuxy.controller

import cl.tuxy.exception.AdvisorException
import cl.tuxy.model.Advisor
import cl.tuxy.repository.AdvisorRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody

import static org.springframework.data.domain.Sort.Direction.DESC
import static org.springframework.http.HttpStatus.NOT_FOUND
import static org.springframework.http.HttpStatus.OK
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import static org.springframework.web.bind.annotation.RequestMethod.GET

/**
 * Created by josebovet on 9/19/15.
 */
@Controller
class AdvisoryController {

    @Autowired
    AdvisorRepository advisorRepository

    @RequestMapping(value = "/list", produces = [APPLICATION_JSON_VALUE], method = GET)
    @ResponseBody
    ResponseEntity<Advisor> index(@RequestParam(value = "limit", defaultValue = "10") Integer limit) {
        def pageRequest = new PageRequest(0, limit.intValue(), DESC, "date")
        def page = advisorRepository.findAll(pageRequest)
        def list = page.content.collect { it }
        new ResponseEntity<List<Advisor>>(list, OK)
    }

    @ExceptionHandler(AdvisorException)
    @ResponseBody
    ResponseEntity handle(AdvisorException ae) {
        new ResponseEntity(ae.message, NOT_FOUND)
    }
}
