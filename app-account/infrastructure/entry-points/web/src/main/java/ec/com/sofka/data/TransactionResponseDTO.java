package ec.com.sofka.data;

import java.math.BigDecimal;

public class TransactionResponseDTO {

    private Integer transactionId;
    private Integer accountId;
    private String transactionType;
    private BigDecimal value;
    private BigDecimal balance;

    public TransactionResponseDTO(Integer transactionId, Integer accountId, String transactionType, BigDecimal value, BigDecimal balance) {
        this.transactionId = transactionId;
        this.accountId = accountId;
        this.transactionType = transactionType;
        this.value = value;
        this.balance = balance;
    }

    public TransactionResponseDTO() {}

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

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
}
