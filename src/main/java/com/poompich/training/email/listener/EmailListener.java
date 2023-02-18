package com.poompich.training.email.listener;

import com.poompich.training.common.EmailRequest;
import com.poompich.training.email.service.EmailService;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class EmailListener {

    private final EmailService emailService;

    public EmailListener(EmailService emailService) {
        this.emailService = emailService;
    }

    @KafkaListener(topics = "activation-email")
    public void listenForActivationEmail(EmailRequest request) {
        log.info("Kafka received: " + request.getTo());
        log.info(request.getContent());
        emailService.send(request.getTo(), request.getSubject(), request.getContent());
    }

}
