import java.util.Scanner;

public class Lab4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);
		
		// 第一題
		int num = sc.nextInt();
				
		// 上半三角形
		for(int i = 1; i <= num; i++){
			// 空白
			for(int j = num; j > i; j--) {
				System.out.print(" ");
			}
					// *
			for(int j = 1; j <= i * 2 - 1; j++) {
				System.out.print("*");
			}
			
			System.out.println();
			
		}
		// 下半三角形
		for(int i = 1; i < num; i++){
			// 空白
			for(int j = 0; j < i; j ++) {
				System.out.print(" ");
			}
			// *
			for(int j = num * 2 - 3; j >= i * 2 - 1; j--) {
				System.out.print("*");
			}
			System.out.println();
		}
		
		// 第二題
		double odd = 0;
		double even = 0;
		int oddSum = 0;
		double oddAverage = 0;
		int evenSum = 0;
		double evenAverage = 0;
		
		while(sc.hasNextInt()) {
			
			int num1 = sc.nextInt();
			// 奇數
			if(num1 % 2 != 0) {
				odd++;
				oddSum += num1;
				oddAverage = oddSum / odd;
				}
			// 偶數
			else if(num1 % 2 == 0) {
				even++;
				evenSum += num1;
				evenAverage = evenSum / even;
			}
			else {
				break;
			}
		}
		System.out.print(oddSum);
		System.out.print(" ");
		System.out.printf("%.2f",oddAverage);
		System.out.println();
		System.out.print(evenSum);
		System.out.print(" ");
		System.out.printf("%.2f",evenAverage);
		
		// 關閉input
		sc.close();
	}
}
