import java.util.ArrayList;

public class Order {
	
	int totalAmount;
	ArrayList<Item> items;
	ArrayList<Integer> quantities;
	
	public Order() {
		items = new ArrayList<Item>();
		quantities = new ArrayList<Integer>();
	}
	public void addOrder(Item item, int quantity) {
		quantities.add(quantity);
		items.add(item);
	}
	public int calTotalAmount() {
		totalAmount = 0;
		for(int i = 0; i < items.size(); i++) {
			totalAmount += items.get(i).getPrice() * quantities.get(i);
		}
		return totalAmount;
	}
	public String orderList() {
		String orderList = "";
		for(int i = 0; i < items.size(); i++) {
			orderList += String.format("%10s",items.get(i).getName()) + String.format("%10s",items.get(i).getPrice()) + String.format("%10s",quantities.get(i)) + "\n";
		}
		return orderList;
	}
}
