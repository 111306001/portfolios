import java.io.IOException;
import java.util.ArrayList;

public class WebTree
{
	public WebNode root;

	public WebTree(WebPage rootPage)
	{
		this.root = new WebNode(rootPage);
	}

	public void setPostOrderScore(ArrayList<Keyword> keywords) throws IOException
	{
		setPostOrderScore(root, keywords);
	}

	private void setPostOrderScore(WebNode startNode, ArrayList<Keyword> keywords) throws IOException
	{
		// YOUR TURN webNode可以算了 從root算
		// 3. compute the score of children nodes via post-order, then setNodeScore for
		startNode.setNodeScore(keywords);
	}

	public void eularPrintTree()
	{
		eularPrintTree(root);
	}

	private void eularPrintTree(WebNode startNode)
	{
		int nodeDepth = startNode.getDepth();

		if (nodeDepth > 1)
			System.out.print("\n" + repeat("\t", nodeDepth - 1));

		System.out.print("(");
		System.out.print(startNode.webPage.name + "," + startNode.nodeScore);
		
		// YOUR TURN
		// 4. print child via pre-order
		
		for(int i = 0; i < startNode.children.size(); i++) {
			if (startNode.children.get(i).isTheLastChild()) {
				nodeDepth = startNode.children.get(i).getDepth();
				System.out.print("\n" + repeat("\t", nodeDepth - 1) + "(" + startNode.children.get(i).webPage.name + "," + startNode.children.get(i).nodeScore + ")");
				System.out.println();
			}
			else {
				eularPrintTree(startNode.children.get(i));
				
			}
		}
		System.out.print(repeat("\t", nodeDepth - 2));
		System.out.print(")");
		
		
	}

	private String repeat(String str, int repeat)
	{
		String retVal = "";
		for (int i = 0; i < repeat; i++)
		{
			retVal += str;
		}
		return retVal;
	}
}