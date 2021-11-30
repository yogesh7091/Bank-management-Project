package com.braindata.bankmanagement.serviceImp;
import com.braindata.bankmanagement.service.Rbi;
import com.braindata.bankmanagement.model.Account;

import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Sbi implements Rbi {
	Scanner sc = new Scanner(System.in);
	Account acc = new Account();
	//create instance variable for pin because we use in many method without creating object
	int pin;
	
	//Implementation Create Account
	@Override
	public void createAccount() 
	{
		//exception handling for if user provide wrong input
		System.out.println("Enter Account No");
		acc.setAccNo(sc.nextInt());
		System.out.println("Enter Account Holder Name");
		//getting valid account holder name using regular expression(regx)
		//use while loop for it break when user provide correct input
		while(true)
		{
			String name = sc.next();
			boolean nameValid = Pattern.matches("[A-Z]{1}[a-z]{1,9}", name);
			if(nameValid)
			{
				acc.setName(name);
				break;
			}
			else
			{
				System.out.println("Enter Valid Name");
			}
			
		}
		System.out.println("Enter Mobile No");
		//getting valid account holder mobile no using regular expression(regx)
		//use while loop for it break when user provide correct input
		while(true)
		{
			String mobNo = sc.next();
			boolean mobNovalid = Pattern.matches("[7-9]{1}[0-9]{9}", mobNo);
			if(mobNovalid)
			{
				acc.setMobNo(mobNo);
				break;
			}
			else
			{
				System.out.println("Enter valid Mobile No");
			}
		}
		System.out.println("Enter Adhar No");
		//getting valid account holder mobile no using regular expression(regx)
		//use while loop for it break when user provide correct input
		while(true)
		{
			String addharNo = sc.next();
			boolean addharNoValid = Pattern.matches("[0-9]{12}", addharNo);
			if(addharNoValid)
			{
				acc.setAdharNo(addharNo);
				break;
			}
			else
			{
				System.out.println("Enter Valid Adhar No");
			}
		}
		System.err.println("Enter Gender");
		//getting valid account holder Gender using regular expression(regx)
		//use while loop for it break when user provide correct input
		while(true)
		{
		String gender = sc.next();
		boolean genderValid = Pattern.matches("[M,F][e]{0,1}[m]{0,1}[a]{1}[l]{1}[e]{1}", gender);
		
			if(genderValid)
			{
				acc.setGender(gender);
				break;
			}
			else
			{
				System.out.println("Enter valid Gender");
			}
		}
		System.out.println("Enter Age");
		acc.setAge(sc.nextInt());
		setBal();
		setAtmPin();

		
		
		
	}
	//Set ATM Pin randomly using Math class Random method in the 4 digit
	public  void setAtmPin() 
	{
		double rand = 1000+Math.random()*8999;
		this.pin = (int)rand;
		System.out.println("Your Account PIN is "+ pin);
	}
	//method for checking Account pin correct or not
	public void checkPin()
	{
		//for loop for giving 3 chance to user
		for(int i =3; i>=1; i--)
		{
			System.out.println("Enter Your Account Pin For Verification");
			int enterPin = sc.nextInt();
			if(pin == enterPin)
			{
				break;
			}
			else
			{
				System.out.println("You Enter Incorrect Pin You have Remaning "+ (i-1) + " Chances");
				//if loops for after try 3 chances account is blocked
				if(i==1)
				{
					System.out.println("You Reached Your Enter Incorrect Pin Limit. Sorry We Block Your Account");
					System.exit(0);
				}
			}
			
		}
	}
	//deposit amount equal or more than 2000 method implementation
	public void setBal() 
	{
		System.out.println("Enter Balance");
		double balance =sc.nextDouble();
		// if else condition for deposit amount more than 5000 at time of create account
		if(balance>=2000)
		{
		  acc.setBalance(balance);
		  System.out.println("Account is succesfully Created");
		}
		else
		{
			System.out.println(" Please Deposite Minimum Amount 2000 For Create Account");
			setBal();
		}
		
	}
	//Implementation Account Details
	@Override
	public void displayAllDetails() 
	{
		//if else condition for create account first
		if(acc.getBalance()>0)
		{
			//if else condition for get details using account no
			int accNo = acc.getAccNo();
			System.out.println("Enter Account No for Verificateion");
			int eAccNo = sc.nextInt();
			if(eAccNo==accNo)
			{
				checkPin();
				System.out.println("Account No: "+ acc.getAccNo());
				System.out.println("Account Holder Name: "+ acc.getName());
				System.out.println("Mobile No: "+ acc.getMobNo());
				System.out.println("Adhar No: "+ acc.getAdharNo());
				System.out.println("Gender: "+ acc.getGender());
				System.out.println("Age: "+acc.getAge());
				System.out.println("Account Balance: "+ acc.getBalance());
			}
			else
			{
				System.out.println("Your Account Is unavialable please enter correct Account No");
				displayAllDetails();
			}
		}
		else
		{
			System.out.println("Your account is not avilable Please create  Account first");
			createAccount();
		}
		
	}
	
	//Implementation Deposit Money
	@Override
	public void depositeMoney() 
	{
		//if else condition for create account first
		if(acc.getBalance()>0)
		{
			checkPin();
			double currentBalance = acc.getBalance();
			System.out.println("Enter Deposite Amount");
			double depositeMoney = sc.nextDouble();
			//if else for minimum amount deposit
			if(depositeMoney>=1000)
			{
				double netBalance = currentBalance+depositeMoney;
				acc.setBalance(netBalance);
				System.out.println("Your Money Is Succesfully Deposite And Your Account Balance Is: "+ acc.getBalance());
			}
			else
			{
				System.out.println("Please Deposit more than 1000");
				depositeMoney();
			}
		}
		else
		{
			System.out.println("Your account is not avilable Please create  Account first");
			createAccount();
		}
		
	}
	
	//method for fast withdrawal
	@Override
	public void withdrawal()
	{
		//if else condition for create account first
		if(acc.getBalance()>0)
		{
		checkPin();
		//giving option to customer for withdraw money
		System.out.println("For withdraw 500 Enter 1");
		System.out.println("For withdraw 1000 Enter 2");
		System.out.println("For withdraw 2000 Enter 3");
		System.out.println("For withdraw other amount Enter 4");
		//Switch case for fast Withdrawal
		int caseNo = sc.nextInt();
		switch(caseNo)
		{
			case 1: withOption(500);
			break;
			
			case 2: withOption(1000);
			break;
			
			case 3: withOption(2000);
			break;
			
			case 4: System.out.println("Enter Withdrawal Amount");
					double withdrawalMoney = sc.nextDouble();
					withOption(withdrawalMoney);
			break;
					
			default:System.out.println("You Select Wrong Option Please Try agian");
					System.exit(0);
		}
		}
		else
		{
			System.out.println("Your account is not avilable Please create  Account first");
			createAccount();
		}
	}
	
	
	public void withOption(double wAmount)
	{
		
		double currentBalance = acc.getBalance();
		double withdrawalMoney = wAmount;
		//if else for condition withdraw amount is 500x
		if(withdrawalMoney%100 ==0)
		{
		//if else condition for withdraw minimum amount
		 if(withdrawalMoney>=500)
		 {
			double netBalance = currentBalance-withdrawalMoney;
		    //if else condition to maintain minimum balance
			if(netBalance>=2000)
			{
				
			 acc.setBalance(netBalance);
			 System.out.println("Your Money Is Succesfully Withdrawal And Your Account Balance Is: "+ acc.getBalance());
			}	
			else
			{
				System.out.println("You have insufficient Balance in your account");
				withdrawal();
			}
		}
		else
		{
			System.out.println("Minimum amount for withdrawal is 500");
			withdrawal();
		}
	   }
	  else
	  {
		System.out.println("You can withdraw amount in 100x");
		withdrawal();
	  }
				
	}
	
	//Implementation Balance check
	@Override
	public void balanceCheck() 
	{
		//if else condition for create account first
		if(acc.getBalance()>0)
		{
			checkPin();
			System.out.println("Your Account Balance Is: "+acc.getBalance());
		}
		else
		{
			System.out.println("Your account is not avilable Please create  Account first");
			createAccount();
		}
	}
	

}
