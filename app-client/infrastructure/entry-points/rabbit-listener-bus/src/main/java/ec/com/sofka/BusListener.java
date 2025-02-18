package ec.com.sofka;

import ec.com.sofka.customer.GetCustomerByIdUseCase;
import ec.com.sofka.data.CustomerInfoRecord;
import ec.com.sofka.data.CustomerInfoRequestRecord;
import ec.com.sofka.gateway.IBusMessageListener;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
    public Object receiveMessage(CustomerInfoRequestRecord request) {
        System.out.println(request);
        try{
            return !request.isInfo() ? getCustomerByIdUseCase.execute(request.identification())
                    .map(Customer::getCustomerId)
                    : getCustomerByIdUseCase.execute(request.identification())
                    .map(customer -> new CustomerInfoRecord(customer.getCustomerId(), customer.getName()));
        }
        catch (Exception e) {
            System.out.println("Error" + e);
            return "";
        }

    }

}
