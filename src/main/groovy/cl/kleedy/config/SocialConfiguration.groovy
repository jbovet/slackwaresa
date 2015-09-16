package cl.kleedy.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.social.twitter.api.impl.TwitterTemplate

/**
 * Created by josebovet on 9/15/15.
 */
@Configuration
class SocialConfiguration {


    String twitterConsumerKey = ""


    String twitterConsumerSecret = ""


    String twitterAccessToken = "-"


    String twitterAccessTokenSecret = ""

    @Bean
    TwitterTemplate twitterTemplate() {
        new TwitterTemplate(
                twitterConsumerKey,
                twitterConsumerSecret,
                twitterAccessToken,
                twitterAccessTokenSecret
        )
    }
}
