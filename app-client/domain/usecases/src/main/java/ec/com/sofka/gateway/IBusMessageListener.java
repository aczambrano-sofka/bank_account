package ec.com.sofka.gateway;

import ec.com.sofka.data.CustomerInfoRequestRecord;

public interface IBusMessageListener {
    Object receiveMessage(CustomerInfoRequestRecord request);
}
