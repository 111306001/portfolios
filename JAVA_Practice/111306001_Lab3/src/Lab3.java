import java.util.Scanner;

public class Lab3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);
		int ticket1 = sc.nextInt();
		int price1 = sc.nextInt();
		int ticket2 = sc.nextInt();
		int price2 = sc.nextInt();
		int budget = sc.nextInt();
		
		String result;
		result = ((ticket1 * price1) + (ticket2 * price2) > budget)? 
				"-1" :"$" + (budget - (ticket1 * price1) - (ticket2 * price2));
		System.out.print(result + "\n");
		
		Scanner sc1 = new Scanner(System.in);
		int ticket = sc1.nextInt();
		int price = sc1.nextInt();
		int studentTicket = sc1.nextInt();
		int studentPrice = sc1.nextInt();
		int budget1 = sc1.nextInt();
		int L = sc1.nextInt();
		
		if(ticket + studentTicket > L) {
			if((ticket * price) + (studentTicket * studentPrice) > budget1) {
				System.out.print("-1,-2");
			}
			else {
				System.out.print("-1,$" + (budget1 - (ticket * price) - (studentTicket * studentPrice)));
			}
		}
		else {
			if((ticket * price) + (studentTicket * studentPrice) > budget1) {
				System.out.print((L - ticket - studentTicket) + ",-2");
			}
			else {
				System.out.print((L - ticket - studentTicket) + ",$" + (budget1 - (ticket * price) - (studentTicket * studentPrice)));
			}
		}
	}
}
