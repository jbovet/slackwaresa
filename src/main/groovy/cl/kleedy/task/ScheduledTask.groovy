package cl.kleedy.task

import cl.kleedy.service.SlackService
import org.apache.log4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

/**
 * Created by josebovet on 9/16/15.
 */
@Component
class ScheduledTask {

    static Logger LOG = Logger.getLogger(ScheduledTask.class);

    @Autowired
    SlackService slackService

    @Scheduled(initialDelayString = "5000", fixedRateString = "60000")
    void checkSecurityAdvisories() {
        LOG.info('run checkSecurityAdvisories task ...searching new updates')
        slackService.checkUpdates()
    }
}
