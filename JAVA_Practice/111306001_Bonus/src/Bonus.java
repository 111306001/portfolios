import java.util.ArrayList;
import java.util.Scanner;

public class Bonus {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		ArrayList <String> suit = new ArrayList <String>();
		ArrayList <Integer> ranks = new ArrayList <Integer>();
		ArrayList <Card> cards = new ArrayList <Card>();
		
		while(sc.hasNext()) {
			for(int i = 0; i <= 9; i++) {
				if(i < 5) {
					suit.add(sc.next());
					String[] space = sc.nextLine().split(",");
				}
				else {
					
					String word = sc.next();
					int cardNum = 0;
					String[] space = sc.nextLine().split(",");
					
					if(word.equals("A")) {
						cardNum = 1;
						ranks.add(cardNum);
					}
					else if(word.equals("K")){
						cardNum = 13;
						ranks.add(cardNum);
					}
					else if (word.equals("Q")) {
						cardNum = 12;
						ranks.add(cardNum);
					}
					else if (word.equals("J")) {
						cardNum = 11;
						ranks.add(cardNum);
					}
					else {
						cardNum = Integer.parseInt(word);
						ranks.add(cardNum);
					}
				}
			}
		}
		
	}

}
