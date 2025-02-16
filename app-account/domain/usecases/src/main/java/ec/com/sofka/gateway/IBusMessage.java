package ec.com.sofka.gateway;

import ec.com.sofka.data.CustomerInfoRequestRecord;

public interface IBusMessage {
    Object sendMessage(Integer request);

}
