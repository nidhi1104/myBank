

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class BankManagementApplication {

	public static void main(String[] args) throws ParseException, ClassNotFoundException, IOException {

		AccountServiceImpl acc = new AccountServiceImpl();

		String choice, ch, operation;
		Scanner sc = new Scanner(System.in);
		double amount;
		do {

			System.out.print("\n");
			System.out.print("\tQ:What do you want to do next?\n\n");
			System.out.print("\t\t1 : New account\n\n");
			System.out.print("\t\t2 : Transaction\n\n");
			System.out.print("\t\t3 : View Account Information\n\n");
			System.out.print("\t\t4 : Calculate FutureFixedDeposit\n\n");
			System.out.print("\t\t5 : Calculate FutureCurrentDeposit\n\n");
			System.out.print("\t\t6 : Calculate FutureSavingDeposit\n\n");
			System.out.print("\t\t7 : Delete Account\n\n");
			System.out.print("\t\tq : Exit\n\n");
			System.out.println("Your choice: ");
			choice = sc.next();
			switch (choice) {
			
			////
			////    creation of account
			////
			case "1":  
				System.out.println("Enter AccountNo :");
				
				Integer accountNo1 = sc.nextInt();
				System.out.println("Enter your name :");
				String name = sc.next();
				sc.nextLine();
				System.out.println("Enter dob in dd/mm/yyyy format :");
				String dob = sc.nextLine();
				System.out.println("Enteryour gender :");
				String gender= sc.nextLine();
				
				System.out.println("Enter the type of accounts you want to open with , seperated: FixedDeposit/Saving/Current");
				
				 String accType=sc.nextLine();
				 String[] accTypesArray=accType.split(",");
				 List<AccountType> accTypes= new ArrayList<>();
				 for(String s: accTypesArray) {
					 accTypes.add(AccountType.valueOf(s));
				 }
				 sc.nextLine();
				 
				 Map<AccountType,Double> accountTypeWithBalance= new HashMap<>();
				 
				 for(AccountType accc:accTypes ) {
					 System.out.println("Enter opening balance for :"+ accc + " account Type");
					 Double d= sc.nextDouble();
					 accountTypeWithBalance.put(accc,d);
				 }
				DateTimeFormatter formatter= DateTimeFormatter.ofPattern("dd/MM/yyyy");
				
			   LocalDate date=LocalDate.parse(dob,formatter); 
				sc.nextLine();
				System.out.println("Services you want to avail with , seperated : PRIORITY_QUEUING, PERSONAL_MANAGER, CASH_DELIVERED_AT_HOME");
				String services= sc.nextLine();
				String[] servicesArray=services.split(",");
				List<BankServices> availedServices= new ArrayList<>();
				 for(String s: servicesArray) {
					 availedServices.add(BankServices.valueOf(s));
				 }

				System.out.println();
				
				acc.createAccount(accountNo1,name,date,gender,accTypes,accountTypeWithBalance,availedServices);
				
				break;
				
				// transaction : deposit/withdraw
				
			case "2":
				System.out.print("\tQ:What do you want to do for Transaction?\n\n");
				System.out.print("\t\ta : Deposit\n\n");
				System.out.print("\t\tb : Withdraw\n\n");
				ch = sc.next();
				if (ch.equalsIgnoreCase("a"))
					operation = "Deposit";
				else if (ch.equalsIgnoreCase("b"))
					operation = "Withdraw";
				else {
					operation = "Invalid option";
				}
				
				System.out.print("\tQ:Enter the type of account : FixedDeposit/Saving/Current\n\n"); 
				sc.nextLine();
				String type=sc.nextLine();
				AccountType accountType= AccountType.valueOf(type);
				System.out.println("Account Number:");
				accountNo1 = sc.nextInt();
				System.out.println("Amount:");
				amount = sc.nextDouble();
				
				acc.updateAccount(operation, amount,accountType,accountNo1);
				break;
				
				// view accounts
			case "3":

				acc.viewAccount();
				break;
				
				
			case "4":

				System.out.println("Enter your fixed deposit amount:");
				double fdAmount= sc.nextDouble();
				
				System.out.println("Enter number of years:");
				
				int tenure= sc.nextInt();
				
				acc.calculateFutureFixedDeposit(fdAmount, tenure);
				break;
				
			case "5":

				System.out.println("Enter your current deposit amount:");
				double cdAmount= sc.nextDouble();
				
				System.out.println("Enter number of years:");
				
				int tenureCd= sc.nextInt();
				
				acc.calculateFutureCurrentDeposit(cdAmount, tenureCd);
				break;
				
			case "6":

				System.out.println("Enter your saving deposit amount:");
				double sdAmount= sc.nextDouble();
				
				System.out.println("Enter number of years:");
				
				int tenuresd= sc.nextInt();
				
				acc.calculateFutureSavingDeposit(sdAmount, tenuresd);
				break;
				
				//delete account
			
			case "7":
				System.out.println("Enter Account No which you want to delete:");
				int accountNo2=sc.nextInt();
				acc.findAccountByAccountNo(accountNo2);
				System.out.println("!!! Your account has been deleted !!!");
				break;
				
			case "q":
				System.out.println("Thank you!");
				return;
			}
		} while (choice != "q");
		sc.close();

	}

}
