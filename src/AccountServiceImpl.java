

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccountServiceImpl implements AccountService {


	@Override
	public Account createAccount(Integer accountNo,String name, LocalDate dob, String gender, List<AccountType> accountType,Map<AccountType,Double> accountTypeWithBalance, List<BankServices> availedServices) {
		
		Account account= new Account(accountNo,name,dob,gender,accountType,accountTypeWithBalance,availedServices);
		createAccountImpl("createAccount",account);
		
		return account;
	}
	
	public void viewAccount() throws IOException, ClassNotFoundException {
		
		String file="C:\\Users\\Nidhi\\OneDrive\\Desktop\\MyBankDetails.txt";
		BufferedReader reader = new BufferedReader(
			    new InputStreamReader(
			        new FileInputStream(file),
			        Charset.forName("UTF-8")));
		String line= reader.readLine();
		while(line!=null) {
			System.out.println(line);
			line=reader.readLine();
		}
		
		reader.close();
 		
	}
/*	
	public void updateAccount(String action, double amount, AccountType accountType,Account account ) throws IOException, ClassNotFoundException, ParseException{
		FileInputStream fi = new FileInputStream("C:\\Users\\Praveen\\OneDrive\\Desktop\\MyFile.txt");
 		ObjectInputStream oi = new ObjectInputStream(fi);
 		Account account1= (Account) oi.readObject();
 		account=account1;
 		if(AccountType.FixedDeposit.equals(accountType)) {
 			LocalDateTime date= LocalDateTime.now();
 			SimpleDateFormat formatter1=new SimpleDateFormat("dd/MM/yyyy"); 
			
			Date date1=formatter1.parse(account.getOpeningDate()); 
 			int diff=date.getYear()-date1.getYear();
 		     if(diff<1)
 			    throw new IOException("Fixed Deposit");
 			
 		}
 		

 		// Read objects
 		Account pr = (Account)oi.readObject();
 		
 		if(action.equalsIgnoreCase("Deposit")) {
 			pr.getAccountTypeWithBalance().put(accountType,
 			pr.getAccountTypeWithBalance().get(accountType)+amount);
 		}
 		else {
 			pr.getAccountTypeWithBalance().put(accountType,
 		 			pr.getAccountTypeWithBalance().get(accountType)-amount);
 		}
 		FileOutputStream fileOut = new FileOutputStream("C:\\Users\\Praveen\\OneDrive\\Desktop\\MyFile.txt");
 		ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
 		
 		objectOut.writeObject(pr);
        objectOut.close();
        System.out.println("The Object  was succesfully updated to a file");
 		
	}
	*/
	
	public void updateAccount(String action, double amount, AccountType accountType,Integer accountNo ) throws IOException, ClassNotFoundException, ParseException{
	
		Account acc= findAccountByAccountNo(accountNo);
		
		if(AccountType.FixedDeposit.equals(accountType)) {
 			    throw new IOException("Fixed Deposit cannot be transact before 1 year");
 			}
		
 		
 		if(action.equalsIgnoreCase("Deposit")) {
 			acc.getAccountTypeWithBalance().put(accountType,
 			acc.getAccountTypeWithBalance().get(accountType)+amount);
 		}
 		else {
 			acc.getAccountTypeWithBalance().put(accountType,
 		 			acc.getAccountTypeWithBalance().get(accountType)-amount);
 		}

 		createAccountImpl("createAccount", acc);
 		
        System.out.println("The Object  was succesfully updated to a file");
 		
	}
		

	
	
	
	
	// create account
	@Override
	public void createAccountImpl(String transactionType,Account account) {
	     if (transactionType.equalsIgnoreCase("createAccount")) {
	    try{
	    	
	    String filepath="C:\\Users\\Nidhi\\OneDrive\\Desktop\\MyBankDetails.txt";
	     
	    FileWriter fileOut = new FileWriter(filepath,true);
	    Writer writer= new BufferedWriter(fileOut);
	    writer.write("\n"+ account.toString());
	    
	    writer.close();
	    fileOut.close();
	    

        System.out.println("You Account has been Successfully Created");

	             }catch (IOException e) {
	                 System.err.println("Caught IOException: " + e.getMessage());
	             }
	     }
	 }
	 
	 public void calculateFutureFixedDeposit(Double amount, int years) {
		 InterestRate rate= new InterestRate(AccountType.FixedDeposit);

			
			double temp= 1+rate.getRateOfInterest();
			double result=temp;
			
			for(int i=1;i<years;i++)
			{
				result=result*temp;
			}
			System.out.println(amount*result);
		
	 }
	 
	 public void calculateFutureSavingDeposit(Double amount, int years) {
		 InterestRate rate= new InterestRate(AccountType.Saving);

			
			double temp= rate.getRateOfInterest();
			
			System.out.println(amount*temp*years+amount);
		
	 }
	 
	 public void calculateFutureCurrentDeposit(Double amount, int years) {
		 InterestRate rate= new InterestRate(AccountType.Current);

			
		 double temp= rate.getRateOfInterest();
			
		 System.out.println(amount*temp*years+amount);
		
	 }
	 
	 public Account findAccountByAccountNo(int number) throws IOException, ParseException {
		 Account account = new Account();
		 String acc= "AccountNo="+number;
		 
			String file="C:\\Users\\Nidhi\\OneDrive\\Desktop\\MyBankDetails.txt";
			String file1="C:\\Users\\Nidhi\\OneDrive\\Desktop\\MyBankDetails.txt";
			 FileWriter fileOut = new FileWriter(file1,true);
			 Writer writer= new BufferedWriter(fileOut);
			BufferedReader reader;
			try {
				reader = new BufferedReader(
					    new InputStreamReader(
					        new FileInputStream(file),
					        Charset.forName("UTF-8")));
				String line= reader.readLine();
				while(line!=null) {
					if(line.contains(acc)) {
						
						String [] arr=line.split("=");
						account.setAccountNo(Integer.parseInt(arr[1])); //accno
						
						line=reader.readLine();
						arr=line.split("=");
						account.setName(arr[1]); //name
						
						line=reader.readLine();
						arr=line.split("=");
						
						
						account.setDob(LocalDate.parse(arr[1].trim())); //dob
						
						line=reader.readLine();
						arr=line.split("=");
						account.setOpeningDate(arr[1]); //opening date
						
						line=reader.readLine();
						arr=line.split("=");
						account.setGender(arr[1]);//gender
						
						line=reader.readLine();
						arr=line.split("=");
						StringBuilder sb= new StringBuilder();
						for(int i=1;i<arr[1].length();i++) {
							if(arr[1].charAt(i)==']')
								break;
							sb.append(arr[1].charAt(i));
						}
							String resultant= new String(sb);
						String[] array=resultant.split(",");
						List<AccountType> accType= new ArrayList<>();
						
						for(String s : array) {
							accType.add(AccountType.valueOf(s.trim()));
						}
						account.setAccountType(accType);//accountType
						
						
						line=reader.readLine();
						line= line.substring(24, line.length()-2);
						
						String[] array1=line.split(",");
						Map<AccountType,Double> accountTypeWithBalance= new HashMap<>();
						
						for(String s: array1) {
							String[] temp= s.split("=");
							accountTypeWithBalance.put(AccountType.valueOf(temp[0].trim()), Double.parseDouble(temp[1]));
						}
						
						line=reader.readLine();
						arr=line.split("=");
						StringBuilder sb1= new StringBuilder();
						for(int i=1;i<arr[1].length();i++) {
							if(arr[1].charAt(i)==']')
								break;
							sb1.append(arr[1].charAt(i));
						}
							String resultant1= new String(sb1);
						String[] array2=resultant1.split(",");
						List<BankServices> bankServices= new ArrayList<>();
						
						for(String s : array2) {
							bankServices.add(BankServices.valueOf(s.trim()));
						}
						account.setAvailedServices(bankServices); // BankServices availed
						
						
						
						account.setAccountTypeWithBalance(accountTypeWithBalance);//aaccountTypeWithBalance
						
						
					}
					
					else
						writer.write(line+"\n");
					line=reader.readLine();
				}
				reader.close();	
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			writer.close();
			fileOut.close();
			
			rewriteToFile(file, file1);
			return account;
			
	 
	   
}

public void rewriteToFile(String file, String file1) throws IOException {
	 FileWriter fileOut=null;
	 Writer writer = null;
	 Path path= Paths.get(file);
	 Files.newBufferedWriter(path , StandardOpenOption.TRUNCATE_EXISTING);
	try {
     fileOut = new FileWriter(file,true);
	 writer= new BufferedWriter(fileOut);
	 BufferedReader reader;
		reader = new BufferedReader(
			    new InputStreamReader(
			        new FileInputStream(file1),
			        Charset.forName("UTF-8")));
		
		String line= reader.readLine();
		while(line!=null) {
			writer.write(line+"\n");
			line=reader.readLine();
		}

		reader.close();
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
	writer.close();
	fileOut.close();
}
}



