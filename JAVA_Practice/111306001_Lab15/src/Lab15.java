import java.util.Scanner;

public class Lab15 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("Input land's name and unit price:");
		Scanner sc = new Scanner(System.in);
		Land land = new Land(sc.next(), sc.nextDouble());
		
		System.out.println("Input number of land's area:");
		int areaNum = sc.nextInt();
		
		for(int i = 0; i < areaNum; i++) {
			System.out.println("Input area:");
			String shape = sc.next();
			if(shape.equals("rectangle")) {
				Shape rectangle = new Rectangle(sc.nextDouble(), sc.nextDouble());
				land.addArea(rectangle);
				
			}
			else if(shape.equals("circle")) {
				Shape circle = new Circle(sc.nextDouble());
				land.addArea(circle);
			}
		}
		land.getInfo();
		sc.close();
	}

}
