package cl.kleedy.service

import cl.kleedy.repository.AdvisorRepository
import cl.kleedy.util.SlackHtmlParser
import org.apache.log4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * Created by josebovet on 9/15/15.
 */
@Service
class SlackService {

    static Logger LOG = Logger.getLogger(SlackService.class);

    @Autowired
    TwitterService twitterService


    @Autowired
    AdvisorRepository advisorRepository

    def update(list) {
        LOG.info('searching updates...')
        list.each { a ->
            if (!existAdvisor(a.packageName)) {
                advisorRepository.save(a)
                def statusMessage = new StringBuffer()
                statusMessage.append(" #slackware security")
                statusMessage.append(a.packageName)
                statusMessage.append(' - ')
                statusMessage.append(a.url)
                twitterService.update(statusMessage.toString())
            }
        }
    }

    def boolean checkUpdates() {
        LOG.info('checking updates...')
        int year = Calendar.getInstance().get(Calendar.YEAR);
        def currents = current(year)
        def latest = getLatest(year)
        if (latest.size() > currents.size()) {
            LOG.info('we found updates...')
            update(latest.reverse())
            return true
        }
        LOG.info('no updates found...')
        return false
    }

    def current(int year) {
        return advisorRepository.findByYear(year)
    }

    private getLatest(int year) {
        return SlackHtmlParser.parse(year)
    }

    private boolean existAdvisor(String packageName) {
        return (advisorRepository.findByPackageName(packageName) != null)
    }
}
