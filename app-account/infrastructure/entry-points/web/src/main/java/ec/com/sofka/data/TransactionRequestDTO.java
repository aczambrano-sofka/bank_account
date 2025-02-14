package ec.com.sofka.data;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionRequestDTO {

    @NotNull(message = "Account ID cannot be null")
    private Integer accountId;

    @NotNull(message = "Transaction type cannot be null")
    private String transactionType;

    @NotNull(message = "Value cannot be null")
    @DecimalMin(value = "0.0", inclusive = true, message = "Value must be positive")
    private BigDecimal value;

    private BigDecimal balance;

    private LocalDateTime date;

    public TransactionRequestDTO(Integer accountId, String transactionType, BigDecimal value, BigDecimal balance, LocalDateTime date) {
        this.accountId = accountId;
        this.transactionType = transactionType;
        this.value = value;
        this.balance = balance;
        this.date = date;
    }

    public TransactionRequestDTO() {}

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
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

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

}
