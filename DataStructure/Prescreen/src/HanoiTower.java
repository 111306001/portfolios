public class HanoiTower
{
	private MyStack<Integer> rodA;
	private MyStack<Integer> rodB;
	private MyStack<Integer> rodC;
	private int times;
	private int numOfDisks;

	/**
	 * @TODO Initialize hanoi tower with some disks (numOfDisks). A disk is
	 *       represent by an integer number. Bigger number stand for bigger disk.
	 *       Also, the smallest disk will be represented by 1, not 0.
	 * 
	 *       In this method, you will be given a parameter "numOfDisks" which
	 *       describes how many disks are there in this hanoi tower. Please put
	 *       those disks into rodA in order; bigger disks should be put first.
	 * 
	 * @HINT: You have to reassign appropriate value/instances to the following
	 *        fields:
	 * @this.numOfDisks
	 * @this.times
	 * @this.rodA
	 * @this.rodB
	 * @this.rodC
	 */
	public HanoiTower(int numOfDisks)
	{
		this.numOfDisks = numOfDisks;
		rodA = new MyStack <Integer> ("A");
		for(int i = numOfDisks; i > 0; i--) {
			rodA.push(i);
		}
		rodB = new MyStack <Integer> ("B");
		rodC = new MyStack <Integer> ("C");
		
	}

	public void start()
	{	
		execute(this.numOfDisks, rodA, rodC, rodB);
	}

	/**
	 * @TODO: This method should be called recursively. Move n disks from "src"
	 *        (source rod) to "dest" (destination rod), and "spare" (temporary rod)
	 *        will act as you temporary space to put disks. Also you should print
	 *        the "step: time from src to dest".
	 */
	private void execute(int n, MyStack<Integer> src, MyStack<Integer> dest, MyStack<Integer> spare)
	{
		
		if(n == 1) {
			times++;
			move(src, dest);
		}
		else {
			execute(n - 1, src, spare, dest);
			execute(1, src, dest, spare);
			execute(n - 1, spare, dest, src);
		}
	}

	/**
	 * @TODO: Move disk from "src" (source rod) to "dest" (destination rod). 
	 * 		  Notice thatYou cannot move disk when source rod is empty, 
	 *  	  or the disk in the destination rod is smaller than 
	 *  	  the disk in the source rod.
	 */
	public void move(MyStack<Integer> src, MyStack<Integer> dest)
	{	
		if(dest.size() == 0 || dest.top() >= src.top()) {
			dest.push(src.pop());
			System.out.println("step: " + times + " from " + src.getName() + " to " + dest.getName());
		}
		else {
			System.out.println("move unsuccessfully");
			System.exit(0);
		}
		printHanoiTower();
	}

	public int getTimes()
	{
		int totaltimes = (int) Math.pow(2, this.numOfDisks) - 1;
		return totaltimes;
	}

	/**
	 * @TODO: Print Current State and the elements of each rod in the step. 
	 * 		  Each step should be separated by --------------. 
	 * 		  Ex:Current state: 1 
	 * 		  A|-4 3 2 
	 * 		  B|-1 
	 *   	  C|- 
	 *   	  --------------
	 */
	public void printHanoiTower()
	{	
		System.out.println("Current states: " + times);
		System.out.print(rodA.getName());
		rodA.printStack();
		System.out.print(rodB.getName());
		rodB.printStack();
		System.out.print(rodC.getName());
		rodC.printStack();
		System.out.println("--------------");
	}

	// ----------------
	public MyStack<Integer> getRodA()
	{
		return rodA;
	}

	public void setRodA(MyStack<Integer> rodA)
	{
		this.rodA = rodA;
	}

	public MyStack<Integer> getRodB()
	{
		return rodB;
	}

	public void setRodB(MyStack<Integer> rodB)
	{
		this.rodB = rodB;
	}

	public MyStack<Integer> getRodC()
	{
		return rodC;
	}

	public void setRodC(MyStack<Integer> rodC)
	{
		this.rodC = rodC;
	}

	public void setTimes(int times)
	{
		this.times = times;
	}

	public int getNumOfDisks()
	{
		return numOfDisks;
	}

	public void setNumOfDisks(int numOfDisks)
	{
		this.numOfDisks = numOfDisks;
	}
}
