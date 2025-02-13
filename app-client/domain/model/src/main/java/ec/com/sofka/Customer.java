package ec.com.sofka;

public class Customer extends Person {

    private Integer id;
    private Integer customerId;
    private String password;
    private Boolean status;

    public Customer(Integer id, String name, String gender, Integer age, String identification, String address, String phone,
                    Integer customerId,String password, Boolean status) {
        super(id, name, gender, age, identification, address, phone);
        this.password = password;
        this.status = status;
    }

    public Customer(Integer id1, Integer customerId, String password, Boolean status) {
        super(null, null, null, null, null, null, null);
        this.id = id1;
        this.customerId = customerId;
        this.password = password;
        this.status = status;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
