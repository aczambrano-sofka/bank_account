package ec.com.sofka.data;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transaction")
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private Integer transactionId;

    private LocalDateTime date;

    @NotBlank
    @Column(name = "transaction_type", nullable = false)
    private String transactionType;

    @NotNull
    @Column(name = "value", nullable = false, precision = 15, scale = 2)
    private BigDecimal value;

    private BigDecimal balance;

    @NotNull
    @Column(name = "account_id", nullable = false)
    private Integer accountId;

    public TransactionEntity(Integer transactionId, LocalDateTime date, String transactionType, BigDecimal value, BigDecimal balance, Integer accountId) {
        this.transactionId = transactionId;
        this.date = date;
        this.transactionType = transactionType;
        this.value = value;
        this.balance = balance;
        this.accountId = accountId;
    }

    public TransactionEntity() {

    }


    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }
}
