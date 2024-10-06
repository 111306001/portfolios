
public class Shop {
	
	private OrderList orderList = new OrderList();
	
	public void addOrder(Fruit fruit, int number) {
		
		Order order = new Order(fruit, number);
		orderList.addOrder(order);
		
	}
	public String getReport() {
		
		int profit = orderList.getTotalRevenue() - orderList.getTotalCost();
		String shopReport = "Total Revenue: " + orderList.getTotalRevenue() + "\n" + "Total Cost: " + orderList.getTotalCost() + "\n" + "Total Profit: " + profit;
		
		return shopReport;
	}
	
}
