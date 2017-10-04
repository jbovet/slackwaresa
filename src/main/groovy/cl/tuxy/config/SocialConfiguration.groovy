package cl.tuxy.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.social.twitter.api.impl.TwitterTemplate

/**
 * Created by josebovet on 9/15/15.
 */
@Configuration
class SocialConfiguration {


    @Value("#{systemEnvironment['TWITTER_CONSUMERKEY']}")
    String twitterConsumerKey = ""

    @Value("#{systemEnvironment['TWITTER_CONSUMER_SECRET']}")
    String twitterConsumerSecret = ""

    @Value("#{systemEnvironment['TWITTER_ACCESSTOKEN']}")
    String twitterAccessToken = ""

    @Value("#{systemEnvironment['TWITTER_ACCESSTOKEN_SECRET']}")
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