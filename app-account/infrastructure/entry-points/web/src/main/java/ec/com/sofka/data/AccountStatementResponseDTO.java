package ec.com.sofka.data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class AccountStatementResponseDTO {
    private final String accountNumber;
    private final BigDecimal accountInitialBalance;
    private final BigDecimal accountAvailableBalance;
    private final String accountType;
    private final String customerName;
    private final Boolean status;
    private final BigDecimal movementAmount;
    private final LocalDateTime date;

    public AccountStatementResponseDTO(String accountNumber, BigDecimal accountInitialBalance, BigDecimal accountAvailableBalance,
                                       String accountType, String customerName, Boolean status, BigDecimal movementAmount, LocalDateTime date) {
        this.accountNumber = accountNumber;
        this.accountInitialBalance = accountInitialBalance;
        this.accountAvailableBalance = accountAvailableBalance;
        this.accountType = accountType;
        this.customerName = customerName;
        this.status = status;
        this.movementAmount = movementAmount;
        this.date = date;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public BigDecimal getAccountInitialBalance() {
        return accountInitialBalance;
    }

    public BigDecimal getAccountAvailableBalance() {
        return accountAvailableBalance;
    }

    public String getAccountType() {
        return accountType;
    }

    public String getCustomerName() {
        return customerName;
    }

    public Boolean getStatus() {
        return status;
    }

    public BigDecimal getMovementAmount() {
        return movementAmount;
    }

    public LocalDateTime getDate() {
        return date;
    }
}
