package com.globant.shoppingcart.messaging;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("dev")
@Service
@Slf4j
public class Listener {

    @RabbitListener(queues = "${listen.queue.name}")
    public void listen(String in) {
        log.info("Message read from testQueue: {} ", in);
    }
}
