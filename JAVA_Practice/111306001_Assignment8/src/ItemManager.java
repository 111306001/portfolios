import java.util.ArrayList;

public class ItemManager {
	private ArrayList <Item> items;
	
	public ItemManager() {
		items = new ArrayList <Item>();
	}
	public ArrayList<Item> getItems() {
		return this.items;
	}
	public void addItem() {
		items.add(new Item(1, "Shirt", 499));
		items.add(new Item(2, "Coat", 1320));
		items.add(new Item(3, "Pants", 799));
		items.add(new Item(4, "Shoes", 1280));
	}
}