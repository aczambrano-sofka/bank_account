package ec.com.sofka;

import com.fasterxml.jackson.databind.ObjectMapper;
import ec.com.sofka.data.CustomerInfoRecord;
import ec.com.sofka.data.CustomerInfoRequestRecord;
import ec.com.sofka.gateway.IBusMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

@Service
public class BusAdapter implements IBusMessage {

    private  final RabbitTemplate rabbitTemplate;
    private  final RabbitProperties rabbitEnvProperties;
    private final ObjectMapper objectMapper;
    private final Map<Class<?>, Function<Object, Object>> responseHandlers;

    public BusAdapter(RabbitTemplate rabbitTemplate, RabbitProperties rabbitEnvProperties) {
        this.rabbitTemplate = rabbitTemplate;
        this.rabbitEnvProperties = rabbitEnvProperties;
        this.objectMapper = new ObjectMapper();
        this.responseHandlers = new HashMap<>();
        responseHandlers.put(Integer.class, response -> response);
        responseHandlers.put(LinkedHashMap.class, response -> objectMapper.convertValue(response, CustomerInfoRecord.class));
        responseHandlers.put(CustomerInfoRecord.class, response -> response);

    }

    @Override
    public Object sendMessage(CustomerInfoRequestRecord request) {

        return Optional.ofNullable(
                        rabbitTemplate.convertSendAndReceive(
                                rabbitEnvProperties.getCustomerExchange(),
                                rabbitEnvProperties.getCustomerRoutingKey(),
                                request
                        )
                )
                .map(this::processResponse)
                .orElse(null);
    }

    private Object processResponse(Object response) {
        return Optional.ofNullable(responseHandlers.get(response.getClass()))
                .map(handler -> handler.apply(response))
                .orElse(null);
    }

}
