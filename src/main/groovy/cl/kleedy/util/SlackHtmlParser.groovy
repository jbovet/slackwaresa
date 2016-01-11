package cl.kleedy.util

import cl.kleedy.model.Advisor
import org.cyberneko.html.parsers.SAXParser

import java.text.SimpleDateFormat

/**
 * Created by josebovet on 9/15/15.
 */
class SlackHtmlParser {

    static final BASEURL = 'http://www.slackware.com/security/'
    static final qry = 'list.php?l=slackware-security&y='

    def static List<Advisor> parse(int year) {
        def advisors = []
        def x = get(year)
        def parser = new SAXParser()
        def list = new XmlSlurper(parser).parseText(x)
        def lines = list.text().split('\\n')
        if (lines.first().trim().length() == 0) {
            return advisors
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd")
        lines.each { line ->
            def ad = line.split(' - ')
            def url = BASEURL + ad[1]
            def pkg = ad[2]
            def d = sdf.parse(ad[0])
            def advisor = new Advisor(url: url, packageName: pkg, date: d, year: year)
            advisors.add(advisor)
        }
        return advisors
    }

    def static get(int year) {
        def html = new URL(BASEURL + qry + year).getText()
        return clean(html)

    }

    def static clean(String html) {
        def f = html.substring(html.indexOf('slackware-security from') + 28, html.indexOf('Slackware&reg'))
        def c = f.replace('<A HREF="viewer.php?', 'viewer.php?')
        return c.replace('">[slackware-security]', ' -')
    }
}
