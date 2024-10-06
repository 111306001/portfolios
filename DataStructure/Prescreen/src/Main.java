public class Main
{
	public static void main(String[] args)
	{
		testExecute(5);

		// You can use this to check whether printStack can execute properly or not. 
		//testPrintStack(4); 

	}

	public static void testPrintStack(int disk)
	{
		MyStack<Integer> rod = new MyStack<Integer>("test");
		for (int i = disk; i >= 1; i--)
		{
			rod.push(i);
		}
		rod.printStack();
	}

	public static void testExecute(int disk)
	{
		HanoiTower t = new HanoiTower(disk);
		t.start();
		System.out.println("Total time: " + t.getTimes()); // total times by formula
	}
}