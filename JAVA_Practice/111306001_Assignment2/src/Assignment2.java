import java.util.Scanner;

public class Assignment2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);
		
		// 第一題
		// 輸入未知數
		int input = sc.nextInt();
		int numMax = 0;
		int numMin = 0;
		
		if (input < 10) {
			input = input * 100;
		}
		else if (input < 100 && input >= 10) {
			input = input * 10;
		}
		
		while (input != 495) {
			// 個位
			int num1 = input % 10;
			// 十位
			int num2 = ((input - num1) % 100) / 10;
			// 百位
			int num3 = (input - num1 - num2) / 100;

			if(num1 >= num2 && num2 >= num3) {
				numMax = num1 * 100 + num2 * 10 + num3;
				numMin = num3 * 100 + num2 * 10 + num1;
			}
			else if (num1 >= num3 && num3 >= num2) {
				numMax = num1 * 100 + num3 * 10 + num2;
				numMin = num2 * 100 + num3 * 10 + num1;
			}
			else if (num2 >= num1 && num1 >= num3){
				numMax = num2 * 100 + num1 * 10 + num3;
				numMin = num3 * 100 + num1 * 10 + num2;
			}
			else if (num2 >= num3 && num3 >= num1) {
				numMax = num2 * 100 + num3 * 10 + num1;
				numMin = num1 * 100 + num3 * 10 + num2;
			}
			else if (num3 >= num2 && num2 >= num1) {
				numMax = num3 * 100 + num2 * 10 + num1;
				numMin = num1 * 100 + num2 * 10 + num3;
			}
			else {
				numMax = num3 * 100 + num1 * 10 + num2;
				numMin = num2 * 100 + num1 * 10 + num3;
			}
			input = numMax - numMin;
			System.out.print(input);
			
			if (input != 495) {
				System.out.print(",");
			}
		}
		System.out.println();
		
		// 第二題
		int interval = sc.nextInt();
		int demand = sc.nextInt();
		int totalPrice = 0;
		int weight = 0;
				
		for (int i = 1; i <= interval; i++) {
			int weight1 = sc.nextInt();
			int price =  sc.nextInt();
			
				if (demand > weight1 - weight) {
					totalPrice += (weight1 - weight) * price;
				}
				else {
					totalPrice += demand * price;	
				}
				// 將上一個重量存進weight使用，以便weight1存新數
				demand -= (weight1 - weight);
				weight = weight1;
		}
		
		System.out.print(totalPrice);
		
		// 關閉input
		sc.close();
	}
}
