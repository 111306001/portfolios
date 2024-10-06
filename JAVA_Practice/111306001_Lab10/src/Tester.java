import java.util.Scanner;

public class Tester{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SavingsAccount accountA = new SavingsAccount (8000, 306049001, 1);
		SavingsAccount accountB = new SavingsAccount (1000, 306049011, 2);
		
		CheckingAccount accountC = new CheckingAccount (9000, 306016033);
		CheckingAccount accountD = new CheckingAccount (3000, 306016041);
		
		Scanner sc = new Scanner(System.in);
		
		BankAccount[] accounts = {accountA, accountB, accountC, accountD};
		
		System.out.print("D)Deposit W)Withdraw M)Month end Q)Quit:");
		String word = sc.next();
		
		while(word.equals("Q") == false){
			 if(word.equals("D")) {
				for(BankAccount bankAccount : accounts) {
					System.out.print("Enter your account number:");
					int accountNum = sc.nextInt();
					if(accountA.getID() != accountNum && accountB.getID() != accountNum && accountC.getID() != accountNum && accountD.getID() != accountNum) {
						System.out.println("Corresponding account cannot be found.");
						System.out.print("D)Deposit W)Withdraw M)Month end Q)Quit:");
						word = sc.next();
						break;
					}
					else {
						System.out.print("Enter transaction amount:");
					}
					double transaction = sc.nextDouble();
					bankAccount.deposit(transaction);
					System.out.println("Balance: " + bankAccount.getBalance());
					System.out.print("D)Deposit W)Withdraw M)Month end Q)Quit:");
					word = sc.next();
					break;
				}
			 }
			else if(word.equals("W")) {
				for(BankAccount bankAccount : accounts) {
					System.out.print("Enter your account number:");
					int accountNum = sc.nextInt();
					
					if(accountA.getID() != accountNum && accountB.getID() != accountNum && accountC.getID() != accountNum && accountD.getID() != accountNum) {
						System.out.println("Corresponding account cannot be found.");
						System.out.print("D)Deposit W)Withdraw M)Month end Q)Quit:");
						word = sc.next();
						break;
					}
					else {
						System.out.print("Enter transaction amount:");
					}
					double transaction = sc.nextDouble();
					bankAccount.withdraw(transaction);
					System.out.println("Balance: " + bankAccount.getBalance());
					System.out.print("D)Deposit W)Withdraw M)Month end Q)Quit:");
					word = sc.next();
					break;
				}
			}
			 
			else if(word.equals("M")) {
				for(int i = 0; i < accounts.length; i++) {
					accounts[i].monthEnd();
					System.out.println(i + " " + accounts[i].getBalance());
				}
				System.out.print("D)Deposit W)Withdraw M)Month end Q)Quit:");
				word = sc.next();	
			}
			else if(word.equals("Q")) {
				break;
			}
			else {
				System.out.print("D)Deposit W)Withdraw M)Month end Q)Quit:");
				word = sc.next();
			}
			
		}
	}
}
