import java.util.ArrayList;

public class OrderList {
	
	ArrayList<Order> orderList =  new ArrayList<Order>(); 
	
	public void addOrder(Order order) {
		orderList.add(order);
	}
	public int getTotalCost() {
		int sumCost = 0;
		for(Order order : orderList) {
			sumCost += order.getOrderCost();
		}
		return sumCost;
	}
	public int getTotalRevenue() {
		int sum = 0;
		for(Order order : orderList) {
			sum += order.getOrderRevenue();
		}
		return sum;
	}
}
