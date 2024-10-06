import java.util.Scanner;

public class Lab6 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		// 輸入變數
		int radius = sc.nextInt();
		int x = sc.nextInt();
		int y = sc.nextInt();
		int circle_x = sc.nextInt();
		int circle_y = sc.nextInt();
		String rangePoint = null;
		
		// 呼叫circle class以及裡面的method
		Circle circle1 = new Circle();
		
		double area = circle1.area(radius);
		double circumference = circle1.circumference(radius);
		rangePoint = circle1.rangePoint(x, circle_x, y, circle_y, radius, rangePoint);
		
		// 列印
		System.out.printf("%.2f", area);
		System.out.print(" ");
		System.out.printf("%.2f", circumference);
		System.out.println();
		System.out.print(rangePoint);
		
		// 關閉input
		sc.close();
	}

}

class Circle{
	
	public double area(int radius) {
		// 面積公式
		double area = Math.PI * radius * radius;
		// 回傳
		return area;
	}
	
	public double circumference(int radius) {
		// 周長公式
		double circumference = Math.PI * 2 * radius;
		// 回傳
		return circumference;
	}
	
	public String rangePoint(int x, int circle_x,int y, int circle_y, int radius, String rangePoint) {
		// 點到圓心距離公式
		double d = Math.sqrt(Math.pow(x-circle_x, 2) + Math.pow(y-circle_y, 2));
		
		// 依據距離長短分析點與圓的關係
		if (d > radius) {
			rangePoint = "The point is outside the circle.";
		}
		else if (d == radius) {
			rangePoint = "The point is on the circle.";
		}
		else if (d < radius){
			rangePoint = "The point is inside the circle.";
		}
		// 回傳
		return rangePoint;
	}
	
}
