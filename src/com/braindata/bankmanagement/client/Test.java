package com.braindata.bankmanagement.client;
import java.util.Scanner;
import com.braindata.bankmanagement.serviceImp.Sbi;
import com.braindata.bankmanagement.service.*;
public class Test 
{
	
	public static void main(String[] args) 
	{
		System.out.println("Enter 1 For Account Create");
		System.out.println("Enter 2 For Account Details");
		System.out.println("Enter 3 For Deposite Money");
		System.out.println("Enter 4 For Withdraw Money");
		System.out.println("Enter 5 For Balance Check");
		
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		Rbi a = new Sbi();
		//use while loop for continuous Operations
		while(true) 
		{
			int caseNo = sc.nextInt();
			//Use Switch case
				switch(caseNo) 
				{
					case 1: a.createAccount();
					break;
				
					case 2: a.displayAllDetails();
					break;
		
					case 3: a.depositeMoney();
					break;
				
					case 4: a.withdrawal();
					break;
				
					case 5: a.balanceCheck();
					break;
				
					default:System.out.println("You Select Invalid Option Please Select Valid Option");
					System.exit(0);
		
				}
		}
	}
	
}
