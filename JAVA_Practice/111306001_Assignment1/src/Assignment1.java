import java.util.Scanner;

public class Assignment1 {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		double height1 = sc.nextDouble();
		double weight1 = sc.nextDouble();
		double height2 = sc.nextDouble();
		double weight2 = sc.nextDouble();
		double height3 = sc.nextDouble();
		double weight3 = sc.nextDouble();
		
		System.out.printf("%.2f", weight1/(height1 * height1/10000));
		System.out.print(" ");
		System.out.printf("%.2f", weight2/(height2 * height2/10000));
		System.out.print(" ");
		System.out.printf("%.2f", weight3/(height3 * height3/10000));
		System.out.print("\n");
		
		if(weight1/(height1 * height1/10000) < 18.5) {
			System.out.print("Underweight ");
		}
		else if(weight1/(height1 * height1/10000) >= 24) {
			System.out.print("Overweight ");
		}
		else {
			System.out.print("Normal ");
		}	
		if(weight2/(height2 * height2/10000) < 18.5) {
			System.out.print("Underweight ");
		}
		else if(weight2/(height2 * height2/10000) >= 24) {
			System.out.print("Overweight ");
		}
		else {
			System.out.print("Normal ");
		}	
		if(weight3/(height3 * height3/10000) < 18.5) {
			System.out.print("Underweight " + "\n");
		}
		else if(weight3/(height3 * height3/10000) >= 24) {
			System.out.print("Overweight " + "\n");
		}
		else {
			System.out.print("Normal" + "\n");
		}	

		String moneyType1 = sc.next();
		double dollar1 = sc.nextDouble();
		String moneyType2 = sc.next();
		double dollar2 = sc.nextDouble();
		String moneyType3 = sc.next();
		double dollar3 = sc.nextDouble();
		
		if(moneyType1.equals("TWD")) {
			
			System.out.printf("%.2f",dollar1);
			System.out.print(" " + moneyType1 + " = ");
			System.out.printf("%.2f",dollar1 * 0.032);
			System.out.print(" " + "EUR" + " = ");
			System.out.printf("%.2f",dollar1 * 44.36);
			System.out.print(" " + "KRW" + "\n");
		}
		else if(moneyType1.equals("EUR")) {
			
			System.out.printf("%.2f",dollar1);
			System.out.print(" " + moneyType1 + " = ");
			System.out.printf("%.2f",dollar1 * 1 / 0.032);
			System.out.print(" " + "TWD" + " = ");
			System.out.printf("%.2f",dollar1 * 44.36 / 0.032);
			System.out.print(" " + "KRW" + "\n");
		}
		else {

			System.out.printf("%.2f",dollar1);
			System.out.print(" " + moneyType1 + " = ");
			System.out.printf("%.2f",dollar1 * 1 / 44.36);
			System.out.print(" " + "TWD" + " = ");
			System.out.printf("%.2f",dollar1 * 0.032 / 44.36);
			System.out.print(" " + "EUR" + "\n");
		}
		if(moneyType2.equals("TWD")) {

			System.out.printf("%.2f",dollar2);
			System.out.print(" " + moneyType2 + " = ");
			System.out.printf("%.2f",dollar2 * 0.032);
			System.out.print(" " + "EUR" + " = ");
			System.out.printf("%.2f",dollar2 * 44.36);
			System.out.print(" " + "KRW" + "\n");
		}
		else if(moneyType2.equals("EUR")) {
			
			System.out.printf("%.2f",dollar2);
			System.out.print(" " + moneyType2 + " = ");
			System.out.printf("%.2f",dollar2 * 1 / 0.032);
			System.out.print(" " + "TWD" + " = ");
			System.out.printf("%.2f",dollar2 * 44.36 / 0.032);
			System.out.print(" " + "KRW" + "\n");
		}
		else {

			System.out.printf("%.2f",dollar2);
			System.out.print(" " + moneyType2 + " = ");
			System.out.printf("%.2f",dollar2 * 1 / 44.36);
			System.out.print(" " + "TWD" + " = ");
			System.out.printf("%.2f",dollar2 * 0.032 / 44.36);
			System.out.print(" " + "EUR" + "\n");
		}
		if(moneyType3.equals("TWD")) {
			
			System.out.printf("%.2f",dollar3);
			System.out.print(" " + moneyType3 + " = ");
			System.out.printf("%.2f",dollar3 * 0.032);
			System.out.print(" " + "EUR" + " = ");
			System.out.printf("%.2f",dollar3 * 44.36);
			System.out.print(" " + "KRW" + "\n");
		}
		else if(moneyType3.equals("EUR")) {

			System.out.printf("%.2f",dollar3);
			System.out.print(" " + moneyType3 + " = ");
			System.out.printf("%.2f",dollar3 * 1 / 0.032);
			System.out.print(" " + "TWD" + " = ");
			System.out.printf("%.2f",dollar3 * 44.36 / 0.032);
			System.out.print(" " + "KRW" + "\n");
		}
		else {
	
			System.out.printf("%.2f",dollar3);
			System.out.print(" " + moneyType3 + " = ");
			System.out.printf("%.2f",dollar3 * 1 / 44.36);
			System.out.print(" " + "TWD" + " = ");
			System.out.printf("%.2f",dollar3 * 0.032 / 44.36);
			System.out.print(" " + "EUR" + "\n");
		}

	}
}
