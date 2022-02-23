import cl.tuxy.util.SlackHtmlParser
import org.junit.Test
import spock.lang.Specification

class SlackHtmlParserTest extends Specification {



    @Test
    void "parse by year"() {
        when:
        def list = SlackHtmlParser.parse(2022)

        then:
        !list.isEmpty()
    }
}
