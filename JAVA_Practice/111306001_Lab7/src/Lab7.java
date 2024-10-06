import java.util.Scanner;
import java.util.ArrayList;

public class Lab7 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);
		ArrayList<String> nameList = new ArrayList<String>();
		for (int i = 0; i < 5; i++) {
			nameList.add(sc.next());	
		}
		
		String existence = sc.next();
		String findIndex = sc.next();
		int findName = sc.nextInt();
		
		// 移除第一筆資料
		nameList.remove(0);
		
		// 輸入名字是否在名單裡
		if (nameList.contains(existence)) {
			System.out.println("The list contains " + existence);
		}
		else {
			System.out.println("This ArrayList does not contain " + existence);
		}
		// 輸入名字在名單的序號，如不在則輸出不再
		if (nameList.contains(findIndex)) {
			System.out.println("The index of " + findIndex + " is " + nameList.indexOf(findIndex));
		}
		else {
			System.out.println("This ArrayList does not contain " + findIndex);
		}
		// 輸入數字找名字，若數字超過名單大小，則顯示出過範圍
		if(findName >= nameList.size()) {
			System.out.println("Index out of bound");
		}
		else {
			System.out.println("Index " + findName + " is " + nameList.get(findName));
		}
		if(nameList != null) {
			System.out.println("The list is not empty");
		}
		else {
			System.out.println("The list is empty");
		}
		System.out.println(nameList.size());
		sc.close();
	}
}
