package uz.zohidjon.ecommerce.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaPaymentTopicConfig {

    @Bean
    public NewTopic paymentTopic() {
        return TopicBuilder
                .name("payment_topic")
                .build();
    }
}
