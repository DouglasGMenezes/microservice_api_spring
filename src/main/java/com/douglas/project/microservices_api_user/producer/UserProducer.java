package com.douglas.project.microservices_api_user.producer;


import com.douglas.project.microservices_api_user.dto.EmailDTO;
import com.douglas.project.microservices_api_user.models.User;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UserProducer {

    final RabbitTemplate rabbitTemplate;

    public UserProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Value(value = "${broker.queue.email.name}")
    private String routingKey;

    public void publishMessageEmail(User user) {
        var emailDTO = new EmailDTO();
        emailDTO.setUserId(user.getUserId());
        emailDTO.setEmailTo(user.getEmail());
        emailDTO.setSubject("Cadastro realizado com sucesso!");
        emailDTO.setText(user.getName() + ", seja bem-vindo! \nAgradecemos o seu cadastro, aproveite agora nossas ofertas imperdiveis.");

        rabbitTemplate.convertAndSend("",routingKey, emailDTO);
    }

}
