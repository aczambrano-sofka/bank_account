package ec.com.sofka.gateway;

public interface IBusMessageListener {
    Object receiveMessage(Integer request);
}
