

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface AccountService {
	
	Account createAccount(Integer accountNo,String name, LocalDate dob, String gender, List<AccountType> accountType,Map<AccountType,Double> accountTypeWithBalance,List<BankServices> availedServices);

	void viewAccount() throws IOException, ClassNotFoundException;
	
	void updateAccount(String action, double amount, AccountType accountType,Integer accountNo ) throws IOException, ClassNotFoundException, ParseException;
	
	void createAccountImpl(String transactionType,Account account);
	
	void calculateFutureFixedDeposit(Double amount, int years);
	
	void calculateFutureSavingDeposit(Double amount, int years);
	
	void calculateFutureCurrentDeposit(Double amount, int years);
}
