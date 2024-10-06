
public class Vehicle implements FixedAsset {
	
	private int durableYear;
	private double residualValue;
	private double value;
	private double depreciation;
	private double deRate;
	
	public Vehicle(int durableYear, double residualValue, double value) {
		// TODO Auto-generated constructor stub
		this.durableYear = durableYear;
		this.residualValue = residualValue;
		this.value = value;
	}
	public void calcDepreciationRate() {
		// TODO Auto-generated method stub
		deRate = 1 / (double)durableYear;
		deRate *= 2;
	}
	public void calcDepreciation(int year) {
		calcDepreciationRate();
		if(year == durableYear) {
			depreciation = (value - residualValue) / durableYear;
		}
		else if(year == 1) {
			depreciation = 0;
		}
		else if(year > durableYear) {
			depreciation = 0;
		}
		else {
			calcDepreciationRate();
			depreciation = residualValue * deRate;
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
