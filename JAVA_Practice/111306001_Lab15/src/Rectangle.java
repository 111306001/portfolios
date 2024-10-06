
public class Rectangle implements Shape {
	
	private double length;
	private double width;
	private double area;
	
	public Rectangle(double length, double width) {
		this.length = length;
		this.width = width;
	}
	
	@Override
	public void calcArea() {
		// TODO Auto-generated method stub
		area = length * width;
	}

	@Override
	public double getArea() {
		// TODO Auto-generated method stub
		calcArea();
		return area;
	}

}
