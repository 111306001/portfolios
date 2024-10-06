import java.util.Scanner;

public class Lab2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.print("1 - A" + "\n");
		System.out.print("Please input two numbers(use space to split):");
		
		Scanner sc = new Scanner(System.in);
		
		int num1 = sc.nextInt();
		int num2 = sc.nextInt();
		double num3 = (num1 + num2)/2;
		
		System.out.print("Gap:" + Math.abs(num1 - num2) + "\n");
		System.out.print("Mul:" + num1 * num2 + "\n");
		System.out.print("Avg:" + num3 + "\n");
		System.out.print("Min:" + Math.min(num1, num2) + "\n");
		System.out.print("\n");
		
		int rand = (int) (Math.random()* (Math.abs(num1 - num2) - 1) + Math.min(num1, num2) + 1 );
		
		System.out.print("1 - B" + "\n");
		System.out.print("Random number between " + num1 + " and " + num2 + ": " + rand + "\n");
		System.out.print("\n");
		
		System.out.print("1 - C" + "\n");
		
		String fullName = "Joanna Chien";
		
		System.out.print( fullName + "\n");
		
		String firstName = fullName.substring(0,6);
		System.out.print("My first name: " + firstName +" - " + firstName.length() + "\n");
		String lastName = fullName.substring(7);
		System.out.print("My last name: " + lastName +" - " + lastName.length() + "\n");
		
		
	}

}
