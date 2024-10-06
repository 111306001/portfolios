import java.util.Scanner;

public class Assignment6 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("Input company employees information");
		Scanner sc = new Scanner(System.in);
		
		String companyName = sc.next();
		Company company = new Company(companyName);
		
		int workerNum = sc.nextInt();
		
		for(int i  = 0; i < workerNum ; i++) {
			String title = sc.next();
			if(title.equals("staff")) {
				Employee employee = new Employee(title, sc.next(), sc.nextInt());
				company.addE(employee);
			}
			else if(title.equals("manager")) {
				Manager manager = new Manager(title, sc.next(), sc.nextInt(), sc.nextDouble());
				company.addE(manager);
			}
		}
		
		System.out.println("Input employees working data");
		
		int dataNum = sc.nextInt();
		for(int i  = 0; i < dataNum; i++) {
			company.addWork(sc.next(), sc.nextInt());
		}
		
		company.getInfo();
		company.callA();
		
		sc.close();
	}

}
