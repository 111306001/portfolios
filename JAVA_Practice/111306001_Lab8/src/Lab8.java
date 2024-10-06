import java.util.Scanner;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Lab8 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Fruit apple = new Fruit("apple", 10, 15);
		Fruit banana = new Fruit("banana", 5, 10);
		Fruit orange = new Fruit("orange", 20, 35);
		
		Shop shop = new Shop();

		try {
			File data = new File("salesData.txt");
			Scanner reader = new Scanner(data);
			
			String[] name = reader.nextLine().split(" ");
			
			while(reader.hasNextLine()) {
				String[] num = reader.nextLine().split(" ");
				for(int i = 0; i < 3; i++) {
					
					int number = Integer.parseInt(num[i]);
					if(i % 3 == 0) {
						shop.addOrder(apple, number);
					}
					else if (i % 3 == 1) {
						shop.addOrder(banana, number);
					}
					else {
						shop.addOrder(orange, number);
					}
				}
			}
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(shop.getReport());
		
		FileWriter report;
		try {
			report = new FileWriter("shop_report.txt");
			report.write(shop.getReport());
			report.close();
			System.out.println("Report has been written to shop_report.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
