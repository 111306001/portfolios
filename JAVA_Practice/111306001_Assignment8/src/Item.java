public class Item {
	private int ID;
	private String name;
	private int price;

	public Item(int ID, String name, int price) {
		this.ID = ID;
		this.name = name;
		this.price = price;
	}
	public String getName(){
		return this.name;
	}
	public int getPrice() {
		return this.price;
	}
}