
public class Machine implements FixedAsset {
	
	private int durableYear;
	private double residualValue;
	private double value;
	private double depreciation;
	
	public Machine (int durableYear, double residualValue, double value) {
		// TODO Auto-generated constructor stub
		this.durableYear = durableYear;
		this.residualValue = residualValue;
		this.value = value;
	}
	public void calcDepreciation(int year) {
		// TODO Auto-generated method stub
		if(year == 1 || year > durableYear) {
			depreciation = 0;
		}
		else {
			depreciation = (value - residualValue) / durableYear;
		}
	}
	
	@Override
	public double getDepreciation() {
		// TODO Auto-generated method stub
		return depreciation;
	}
	
	@Override
	public double getValue() {
		// TODO Auto-generated method stub
		return value;
	}
}
