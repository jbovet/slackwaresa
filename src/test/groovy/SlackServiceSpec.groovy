import cl.kleedy.SlackwareSecurityAdvisorApplication
import cl.kleedy.service.SlackService
import cl.kleedy.service.TwitterService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.support.AnnotationConfigContextLoader
import spock.lang.Ignore
import spock.lang.Specification

/**
 * Created by josebovet on 9/15/15.
 */
@ContextConfiguration(classes = SlackwareSecurityAdvisorApplication.class,
        loader = AnnotationConfigContextLoader.class)
class SlackServiceSpec extends Specification {

    @Autowired
    SlackService slackService

    @Autowired
    TwitterService twitterService

    void "get security advisors"() {
        when:
        boolean updates = slackService.checkUpdates()
        then:
        updates != null
    }

    @Ignore
    void "get tweets"() {
        when:
        def list = twitterService.getTweets();

        then:
        list.size() >= 0
    }


    @Ignore
    void "remove status from time line"() {
        when:
        def list = twitterService.getTweets();
        list.each { t ->
            twitterService.removeStatus(t.id)
        }

        then:
        1 == 1

    }
}
