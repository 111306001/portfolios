
public class Circle implements Shape{
	
	private double pi = 3.14;
	private double radius;
	private double area;
	
	public Circle (double radius) {
		this.radius = radius;
	}
	
	@Override
	public void calcArea() {
		// TODO Auto-generated method stub
		area = radius * radius * pi;
	}

	@Override
	public double getArea() {
		// TODO Auto-generated method stub
		calcArea();
		return area;
	}

}
