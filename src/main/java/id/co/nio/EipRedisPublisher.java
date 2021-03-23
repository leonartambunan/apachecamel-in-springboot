package id.co.nio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

@Service
public class EipRedisPublisher implements MessagePublisher {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public EipRedisPublisher() {
    }

    public EipRedisPublisher(final RedisTemplate<String, Object> redisTemplate, final ChannelTopic topic) {
        this.redisTemplate = redisTemplate;
    }

    public void publish(final String topic, final String message) {
        redisTemplate.convertAndSend(topic, message);
    }
}
