

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Account  {
	
	private Integer accountNo;

	private String name;
	
	private LocalDate dob;
	private String openingDate = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) ;
	
	
	public void setOpeningDate(String openingDate) {
		this.openingDate = openingDate;
	}
	private Map<AccountType,Double> accountTypeWithBalance;
	
	private List<BankServices> availedServices;
	
	public List<BankServices> getAvailedServices() {
		return availedServices;
	}

	public void setAvailedServices(List<BankServices> availedServices) {
		this.availedServices = availedServices;
	}

	public String getOpeningDate() {
		return openingDate;
	}
	private String gender;
	
	private List<AccountType> accountType = new ArrayList<>(3);


	public Account() {
		
	}
	
	public Account(Integer accountNo,String name, LocalDate dob, String gender, List<AccountType> accountType2,Map<AccountType,Double> accountTypeWithBalance,
			List<BankServices> availedServices) {
		this.accountNo=accountNo;
		this.name = name;
		this.setDob(dob);
		this.gender = gender;
		this.accountType = accountType2;
		this.accountTypeWithBalance=accountTypeWithBalance;
		this.availedServices=availedServices;
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

	public List<AccountType> getAccountType() {
		return accountType;
	}

	public void setAccountType(List<AccountType> accountType) {
		this.accountType = accountType;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}
	public Integer getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(Integer accountNo) {
		this.accountNo = accountNo;
	}
	@Override
	public String toString() {
		return "\nAccountNo=" + accountNo+ "\nName="+ name
				+ " \nDob=" + dob + " \nOpeningDate=" + openingDate + " \nGender=" + gender
				+ " \nAccountType=" + accountType + " \nAccountTypeWithBalance=" + accountTypeWithBalance + " \nAvailedServices=" + availedServices;
	}

	public Map<AccountType,Double> getAccountTypeWithBalance() {
		return accountTypeWithBalance;
	}

	public void setAccountTypeWithBalance(Map<AccountType,Double> accountTypeWithBalance) {
		this.accountTypeWithBalance = accountTypeWithBalance;
	}

	

}
