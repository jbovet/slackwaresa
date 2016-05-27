import cl.kleedy.SlackwareSecurityAdvisorApplication
import cl.kleedy.model.Advisor
import cl.kleedy.repository.AdvisorRepository
import junit.framework.TestCase
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.support.AnnotationConfigContextLoader

/**
 * Created by josebovet on 9/15/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SlackwareSecurityAdvisorApplication.class,
        loader = AnnotationConfigContextLoader.class)
class AdvisorTest extends TestCase {

    @Autowired
    AdvisorRepository advisorRepository


    @Ignore
    public void insertAdvisor() {
        given:
        def advisor = new Advisor(
                packageName: 'mozilla-thunderbird (SSA:2015-137-01)',
                date: new Date(),
                url: 'http://www.slackware.com/security/viewer.php?l=slackware-security&y=2015&m=slackware-security.360362')
        when:
        def a = advisorRepository.save(advisor)

        then:
        a != null

    }


    @Ignore
    public void findAllTest() {
        when:
        def advisor = advisorRepository.findByPackageName('mozilla-thunderbird (SSA:2015-137-01)s')

        then:
        advisor.packageName == 'mozilla-thunderbird (SSA:2015-137-01)'

    }

    @Ignore
    public void findAll() {
        when:
        def list = advisorRepository.findAll()

        then:
        list.size() >= 0

    }

    @Test
    public void findByYear() {
        when:
        def list = advisorRepository.findByYear(2016)

        then:
        list.size() >= 0

    }


    @Ignore
    public void removeByYear() {
        when:
        def result = advisorRepository.deleteAdvisorByYear(2015)

        then:
        result >= 0
    }
}
