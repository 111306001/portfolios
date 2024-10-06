import java.util.*;

public class KeywordList
{
	private LinkedList<Keyword> lst;

	public KeywordList()
	{
		this.lst = new LinkedList<Keyword>();
	}

	public void add(Keyword keyword)
	{
		// YOUR TURN
		// 1.add keyword to proper index base on its count.
		// ASCENDING SORT BY COUNT AND WEIGHT
		// You can use printKeywordList(lst) to check if elements are sorted
		for (int i = 0; i < lst.size(); i++)
		{
			Keyword k = lst.get(i);
			if (keyword.count <= k.count)
			{
				if(keyword.count < k.count) {
					lst.set(i, keyword);
					keyword = k;
				}
				else if(keyword.count == k.count) { 
					if(keyword.weight < k.weight) {	
						lst.set(i, keyword);
						keyword = k;
					}
				}
				// If the count is smaller than the original, place it in the front.
				// If their count are same, smaller weight placed in the front
			}
		}
		lst.add(keyword);
	}

	public void outputIndex(int i)
	{
		if (i >= lst.size())
		{
			System.out.println("InvalidOperation");
			return;
		}
		Keyword k = lst.get(i);
		System.out.println(k);
	}

	public void outputCount(int c)
	{
		LinkedList<Keyword> results = new LinkedList<>();
		for (int i = 0; i < lst.size(); i++)
		{
			Keyword k = lst.get(i);
			if (k.count == c)
			{
				results.add(k);
			}
		}
		if (results.isEmpty())
		{
			System.out.println("NotFound");
		}
		else
		{
			printKeywordList(results);
		}
	}

	public void outputHas(String pattern)
	{
		LinkedList<Keyword> results = new LinkedList<>();
		for (int i = 0; i < lst.size(); i++)
		{
			Keyword k = lst.get(i);
			if (k.name.contains(pattern))
			{
				results.add(k);
			}
		}
		if (results.isEmpty())
		{
			System.out.println("NotFound");
		}
		else
		{
			printKeywordList(results);
		}
	}

	public void outputName(String pattern)
	{
		LinkedList<Keyword> results = new LinkedList<>();
		for (int i = 0; i < lst.size(); i++)
		{
			Keyword k = lst.get(i);
			if (k.name.equals(pattern))
			{
				results.add(k);
			}
		}
		if (results.isEmpty())
		{
			System.out.println("NotFound");
		}
		else
		{
			printKeywordList(results);
		}
	}

	public void outputFirstN(int n)
	{
		if (n > lst.size())
		{
			System.out.println("InvalidOperation");
			return;
		}
		LinkedList<Keyword> found = new LinkedList<>();

		for (int i = 0; i < n; i++)
		{
			Keyword k = lst.get(i);
			found.add(k);
		}
		printKeywordList(found);
	}

	public void outputScore()
	{
		float results = 0;
		// YOUR TURN
		// 2.To calculate all keyword's count*weight
		for(int i = 0; i < lst.size(); i++) {
			results += (lst.get(i).count * lst.get(i).weight);
		}
		System.out.println(results);
	}

	public void deleteIndex(int i)
	{
		if (i >= lst.size())
		{
			return;
		}
		lst.remove(i);
	}

	public void deleteCount(int c)
	{
		// In the following methods, you can use the way similar to that in the
		// previous output method and integrate the provided code, or use your own way.

		LinkedList<Keyword> found = new LinkedList<>();
		for(int i = 0; i < lst.size(); i++) {
			if(lst.get(i).count == c) {
				found.add(lst.get(i));
			}
		}
		if (!found.isEmpty())
		{
			lst.removeAll(found);
		}

		// YOUR TURN
		// 3. remove nodes that the count is equal to c
	}

	public void deleteHas(String pattern)
	{
		// YOUR TURN
		// 4. remove nodes that the name contains input name
		LinkedList<Keyword> found = new LinkedList<>();
		for(int i = 0; i < lst.size(); i++) {
			if(lst.get(i).name.contains(pattern)) {
				found.add(lst.get(i));
			}
		}
		if (!found.isEmpty())
		{
			lst.removeAll(found);
		}
	}

	public void deleteName(String name)
	{
		// YOUR TURN
		// 5. remove nodes that the name is equal to input name
		LinkedList<Keyword> found = new LinkedList<>();
		for(int i = 0; i < lst.size(); i++) {
			if(lst.get(i).name.equals(name)) {
				found.add(lst.get(i));
			}
		}
		if (!found.isEmpty())
		{
			lst.removeAll(found);
		}
	}

	public void deleteFirstN(int n)
	{
		// YOUR TURN
		// 6. remove first n nodes
		for(int i = 0; i < n; i++) {
			lst.removeFirst();
		}
	}

	public void deleteAll()
	{
		lst = new LinkedList<Keyword>();
	}

	private void printKeywordList(LinkedList<Keyword> kLst)
	{
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < kLst.size(); i++)
		{
			Keyword k = kLst.get(i);
			if (i > 0)
				sb.append(" ");
			sb.append(k.toString());
		}
		System.out.println(sb.toString());
	}
}