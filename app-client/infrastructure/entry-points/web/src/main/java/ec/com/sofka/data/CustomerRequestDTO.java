package ec.com.sofka.data;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class CustomerRequestDTO {


    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotBlank(message = "Gender cannot be blank")
    private String gender;

    @Min(value = 0, message = "Age must be a positive number")
    private Integer age;

    @NotBlank(message = "Identification cannot be blank")
    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "Identification must be alphanumeric")
    private String identification;

    private String address;

    @NotBlank(message = "Phone cannot be blank")
    @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Phone must be a valid phone number")
    private String phone;

    @NotNull(message = "Customer ID cannot be null")
    private Integer customerId;

    @NotBlank(message = "Password cannot be blank")
    private String password;

    @NotNull(message = "Status cannot be null")
    private Boolean status;

    public CustomerRequestDTO(String name, String gender, Integer age, String identification, String address, String phone, Integer customerId, String password, Boolean status) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.identification = identification;
        this.address = address;
        this.phone = phone;
        this.customerId = customerId;
        this.password = password;
        this.status = status;
    }

    public CustomerRequestDTO(String identification) {
        this.identification = identification;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
