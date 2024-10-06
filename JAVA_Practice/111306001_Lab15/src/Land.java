
public class Land {
	
	private String name;
	private double unitPrice;
	private double totalArea;
	private double totalPrice;
	
	public Land(String name, double unitPrice)  {
		// TODO Auto-generated method stub
		this.name = name;
		this.unitPrice = unitPrice;
	}
	public void addArea(Shape shape) {
		totalArea += shape.getArea();
		totalPrice = unitPrice * totalArea;
	}
	public void getInfo() {
		System.out.println("Land : " + name);
		System.out.println("Total area = " +  String.format("%.2f", totalArea));
		System.out.println("Total price = " +  String.format("%.2f", totalPrice));
	}
	
}
