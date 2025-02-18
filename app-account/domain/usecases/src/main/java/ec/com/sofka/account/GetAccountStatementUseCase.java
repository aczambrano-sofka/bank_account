package ec.com.sofka.account;

import ec.com.sofka.Account;
import ec.com.sofka.data.AccountStatementInfo;
import ec.com.sofka.data.CustomerInfoRecord;
import ec.com.sofka.data.CustomerInfoRequestRecord;
import ec.com.sofka.gateway.IAccountRepository;
import ec.com.sofka.gateway.IBusMessage;
import ec.com.sofka.gateway.ITransactionrepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

public class GetAccountStatementUseCase {

    private final IAccountRepository accountRepository;
    private final ITransactionrepository transactionrepository;
    private final IBusMessage busMessage;

    public GetAccountStatementUseCase(IAccountRepository accountRepository, ITransactionrepository transactionrepository, IBusMessage busMessage) {
        this.accountRepository = accountRepository;
        this.transactionrepository = transactionrepository;
        this.busMessage = busMessage;
    }

    public List<AccountStatementInfo> execute(String dateRange, Integer identification){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String[] dates= dateRange.split("-");
        if(dates.length != 2){
            throw new RuntimeException("Invalid date range format. Use dd/MM/yyyy-dd/MM/yyyy");
        }
        LocalDate startDate = LocalDate.parse(dates[0], formatter);
        LocalDate endDate = LocalDate.parse(dates[1],formatter);
        LocalDateTime startDateTime = startDate.atStartOfDay();
        LocalDateTime endDatetime = endDate.atTime(23,59,59);
        Object response = busMessage.sendMessage(new CustomerInfoRequestRecord(identification, true));
        CustomerInfoRecord info;
        if (response == "") {
            throw new RuntimeException("Error creating account statement: Customer info not Found");
        } else {
            info = (CustomerInfoRecord) response;
        }
        Optional<Account> accountOptional = accountRepository.findByCustomerId(info.customerId());

        Account account = accountOptional.get();

        return transactionrepository.findTransactionsByDateRangeAndCustomer(startDateTime,endDatetime,account.getAccountId())
                .stream()
                .map(transaction -> new AccountStatementInfo(
                        account.getAccountNumber(),
                        transaction.getBalance().subtract(transaction.getValue()),
                        transaction.getBalance(),
                        account.getAccountType(),
                        info.customerName(),
                        account.getStatus(),
                        transaction.getValue(),
                        transaction.getDate()
                ))
                .toList();

    }

}
