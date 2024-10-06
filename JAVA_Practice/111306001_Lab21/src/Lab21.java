import java.util.Scanner;

public class Lab21 {
	public static void main(String[] args) {
	// TODO Auto-generated method stub
		Solution s = new Solution();
		Scanner scanner = new Scanner(System.in);
		// Q1
		String ans1 = s.recapitalized(scanner.nextLine());
		System.out.println(ans1);
		// Q2
		int ans2 = s.counting(scanner.next(), scanner.nextInt());
		System.out.println(ans2);
	}
}
