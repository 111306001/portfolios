
public class Order {
	private  int number;
	private  Fruit fruit;
	
	public Order(Fruit fruit, int number) {
		this.fruit = fruit;
		this.number = number;
	}
	public int getOrderCost() {
		int orderCost = fruit.getCost() * number;
		return orderCost;
	}
	public int getOrderRevenue() {
		int orderRevenue = fruit.getPrice() * number;
		return orderRevenue;
	}

}
