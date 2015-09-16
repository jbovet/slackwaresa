package cl.kleedy.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.social.twitter.api.Tweet
import org.springframework.social.twitter.api.impl.TwitterTemplate
import org.springframework.stereotype.Service

import static groovyx.gpars.GParsPool.withPool

/**
 * Created by josebovet on 9/15/15.
 */
@Service
class TwitterService {

    @Autowired
    TwitterTemplate twitter

    void update(String status) {
        tweetAsync(status)
    }

    List<Tweet> getTweets() {
        return twitter.timelineOperations().getUserTimeline(100)
    }

    void removeStatus(Long id) {
        removeAsync(id)
    }

    def remove = { id -> twitter.timelineOperations().deleteStatus(id) }

    def removeAsync = { id -> withPool() { remove.async()(id) } }


    def tweet = { status -> twitter.timelineOperations().updateStatus(status) }

    def tweetAsync = { status -> withPool() { tweet.async()(status) } }
}
