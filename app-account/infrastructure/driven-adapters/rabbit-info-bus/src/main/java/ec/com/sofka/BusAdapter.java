package ec.com.sofka;

import com.fasterxml.jackson.databind.ObjectMapper;
import ec.com.sofka.gateway.IBusMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;

@Service
public class BusAdapter implements IBusMessage {

    private  final RabbitTemplate rabbitTemplate;
    private  final RabbitProperties rabbitEnvProperties;

    public BusAdapter(RabbitTemplate rabbitTemplate, RabbitProperties rabbitEnvProperties) {
        this.rabbitTemplate = rabbitTemplate;
        this.rabbitEnvProperties = rabbitEnvProperties;
    }


    @Override
    public Object sendMessage(Integer  request) {

        Object response = rabbitTemplate.convertSendAndReceive(
                rabbitEnvProperties.getCustomerExchange(),
                rabbitEnvProperties.getCustomerRoutingKey(),
                request
        );

        if (response != null) {

            if (response instanceof Integer) {
                return (Integer) response;
            }else {
                throw new IllegalStateException("Unexpected response type from bus: " + response.getClass().getName());
            }
        }
        return null;
    }

}
