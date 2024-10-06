import java.util.Comparator;

public class KeywordComparator implements Comparator<Keyword>
{
	@Override
	public int compare(Keyword o1, Keyword o2)
	{
		if (o1 == null || o2 == null) {
			throw new NullPointerException();
		}
		else if (o1.getCount() < o2.getCount()) {
			return -1;
		}
		else if (o1.getCount() > o2.getCount()) {
			return 1;
		}
		else {
			return 0;
		}
		// YOUR TURN
		// 1. compare count
		// Hint: If o1 is less than o2 return negative integer, o1 greater than o2
		// return positive integer, equal return zero
	}
}
