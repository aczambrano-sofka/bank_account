package ec.com.sofka;

import ec.com.sofka.customer.GetCustomerByIdUseCase;
import ec.com.sofka.gateway.IBusMessageListener;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class BusListener implements IBusMessageListener {
    private final RabbitProperties envProperties;
    private final GetCustomerByIdUseCase getCustomerByIdUseCase;

    public BusListener(RabbitProperties envProperties, GetCustomerByIdUseCase getCustomerByIdUseCase) {
        this.envProperties = envProperties;
        this.getCustomerByIdUseCase = getCustomerByIdUseCase;
    }

    @Override
    @RabbitListener(queues = "#{rabbitProperties.getCustomerQueue()}")
    public Object receiveMessage(Integer customerId) {

        return getCustomerByIdUseCase.execute(customerId)
                .map(Customer::getCustomerId)
                .orElse(null);
    }

}
