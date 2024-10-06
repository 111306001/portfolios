import java.util.Scanner;

public class Lab5 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);
		
		double sideA = sc.nextDouble();
		double sideB = sc.nextDouble();
		double angleC = sc.nextDouble();
		
		Triangle triangle = new Triangle();
		double sideC = triangle.side(sideA, sideB, angleC);
		double perimeter = triangle.perimeter(sideA, sideB, sideC);
		double area = triangle.area(sideA, sideB, sideC, perimeter);
		
		System.out.printf("%.1f",sideC);
		System.out.print(" ");
		System.out.printf("%.1f",perimeter);
		System.out.print(" ");
		System.out.printf("%.1f",area);
		
		// 關閉input
		sc.close();
	}

}

class Triangle {
	
	public double side(double sideA, double sideB, double angleC) {
		angleC = Math.cos(angleC / 180 * Math.PI);
		double sideC = Math.sqrt(sideA * sideA + sideB * sideB - 2 * sideA * sideB * angleC); 
		return sideC;
	}
	public double perimeter (double sideA, double sideB, double sideC) {
		
		double perimeter = sideA + sideB + sideC;
		return perimeter;
		
	}
	public double area(double sideA, double sideB, double sideC, double perimeter) {
		
		double sides = perimeter / 2;
		double area = Math.sqrt(sides * (sides - sideA) * (sides - sideB) * (sides - sideC));
		return area;
		
	}
	
}



