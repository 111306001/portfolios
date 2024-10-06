import java.util.ArrayList;

public class Company {
	
	private String name;
	private int year = 1;
	private double bookValue;
	private double depreciation;
	private ArrayList <FixedAsset> assets = new ArrayList <FixedAsset> ();
	private ArrayList <Double> AccumulatesDepre = new ArrayList <Double> ();
	private double accumulate;
	
	public Company(String name) {
		this.name = name;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public void addFixedAsset(FixedAsset asset) {
		assets.add(asset);
	}
	public String getName(){
		return name;
	}
	public int getYear() {
		return year;
	}
	public void setBookValue() {
		bookValue = 0;
		for(FixedAsset asset : assets) {
			bookValue += asset.getValue();
		}
	}
	public double getBookValue() {
		setBookValue();
		bookValue -= AccumulatesDepre.get(year - 1);
		return bookValue;
	}
	public double getDepreciation() {
		depreciation = 0;
		for(FixedAsset asset : assets) {
			asset.calcDepreciation(year);
			depreciation += asset.getDepreciation();
			accumulate += asset.getDepreciation();
		}
		if(year == 1) {
			AccumulatesDepre.add(depreciation);
		}
		else if(year > 1) {
			AccumulatesDepre.add(accumulate);
		}
		return depreciation;
	}
	
}
