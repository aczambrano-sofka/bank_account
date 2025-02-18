package ec.com.sofka;

import com.fasterxml.jackson.databind.ObjectMapper;
import ec.com.sofka.data.CustomerInfoRecord;
import ec.com.sofka.data.CustomerInfoRequestRecord;
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
    public Object sendMessage(CustomerInfoRequestRecord request) {

        Object response = rabbitTemplate.convertSendAndReceive(
                rabbitEnvProperties.getCustomerExchange(),
                rabbitEnvProperties.getCustomerRoutingKey(),
                request
        );

        if (response != null) {

            if (response instanceof Integer) {
                return (Integer) response;
            } if (response instanceof LinkedHashMap) {
                ObjectMapper objectMapper = new ObjectMapper();
                return objectMapper.convertValue(response, CustomerInfoRecord.class);
            }

            if (response instanceof CustomerInfoRecord) {
                return response;
            }
        }
        return null;
    }

}
