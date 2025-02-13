package ec.com.sofka.data;

import jakarta.persistence.*;

@Entity
@Table(name = "customer", uniqueConstraints = @UniqueConstraint(columnNames = "customer_id"))
public class CustomerEntity extends PersonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Integer customerId;

    @Column(name = "password")
    private String password;

    @Column(name = "status")
    private Boolean status;

    public CustomerEntity(String name, String gender, Integer age, String identification, String address, String phone,
                          Integer customerId, String password, Boolean status) {
        super(name, gender, age, identification, address, phone);
        this.customerId = customerId;
        this.password = password;
        this.status = status;
    }

    public CustomerEntity() {}

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
