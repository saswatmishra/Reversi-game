package reversi;

public class MinMaxAlgo {
	
	private Tile finalMove;

	public Tile algoMinMax(Tile[][] currentGameBoard2) {


		System.out.println("I am min max called");

		Node root = new Node();

		GameTree gameTree = new GameTree();
		GameBoard minmaxboard = new GameBoard();
		minmaxboard.GameBoardinitialize(8);
		Tile [][]currentGameBoard = minmaxboard.getGameBoard();
		System.out.println("min max****************************************");
		for (int w = 0; w< currentGameBoard2.length; w++) {
			for (int q = 0; q < currentGameBoard2.length; q++) {
				System.out.print(" "+ currentGameBoard2[w][q].getPegValue() );
			}
			System.out.println();
		}
	
		for (int w = 0; w< currentGameBoard2.length; w++) {
			for (int q = 0; q < currentGameBoard2.length; q++) {
				currentGameBoard[w][q].setPegValue(currentGameBoard2[w][q].getPegValue());
			}
		}
		
		root = gameTree.genGameTree(currentGameBoard, 3);
		
		
		int minWeight;
		int maxWeight;
		Node node, tempnode;
		
		if(!(root.getChildren() == null)){
		for (int i = 0; i < root.getChildren().length; i++) {
			node = root.getChildren()[i];
			int randomNum2 = 5 + (int)(Math.random()* 50);
			if(!(node.getChildren() == null)){
			for (int j = 0; j < node.getChildren().length; j++) {
				
				int randomNum = 5 + (int)(Math.random()* 50);
				//System.out.println(randomNum + "minmaxrandom");
				tempnode = node.getChildren()[j];
				
				tempnode.setWeight(randomNum);
				//System.out.println(tempnode.getWeight()+ "weight of each node");
				root.getChildren()[i].getChildren()[j].setWeight(randomNum);
				
				//tempnode.setWeight(gameTree.Utility(tempnode.getBoardState()));
			}
		}
			root.getChildren()[i].setWeight(randomNum2);
		}
		root.setWeight(8888);
		}
		// MIN TURN
		for (int i = 0; i < root.getChildren().length; i++) {
			minWeight = minWeight(root.getChildren()[i]);
			root.getChildren()[i].setWeight(minWeight);
		}

		// MAX TURN ROOT WEIGHT

		maxWeight = maxWeight(root);
		root.setWeight(maxWeight);
		System.out.println(finalMove.getRowIndex()+ "***********" + finalMove.getColIndex());
		return finalMove;
	}

	private int minWeight(Node node) {
		
		if(!(node.getChildren() == null)){
		int noChildren = node.getChildren().length;

		int[] min = new int[noChildren];

		for (int i = 0; i < noChildren; i++) {
			min[i] = node.getChildren()[i].getWeight();
		}

		int minWeight = min[0];

		for (int i = 0; i < noChildren; i++) {

			if (min[i] < minWeight) {
				minWeight = min[i];
			}
		}
		System.out.println("min weight is" + minWeight + "cord are rows" + 
		node.getTile_coord().getRowIndex() + "col are" + node.getTile_coord().getColIndex());
		return minWeight;
	}
		else
			return node.getWeight();
	}

	private int maxWeight(Node node) { 
		
		if(!(node.getChildren() == null)){
			
		int noChildren = node.getChildren().length;
		System.out.println("noChildren" + noChildren);
		int[] max = new int[noChildren];

		for (int i = 0; i < noChildren; i++) {
			max[i] = node.getChildren()[i].getWeight();
			System.out.println(max[i]+ " max-i");
		}

		int maxWeight = max[0];
		int maxnode = 0;
		for (int i = 0; i < noChildren; i++) {

			if (max[i] > maxWeight) {
				maxWeight = max[i];
				maxnode = i;
			}
		}
		finalMove = node.getChildren()[maxnode].getTile_coord();
		System.out.println("maxnode coordinates are%%%%%%%%%%%%" + node.getChildren()[maxnode].getTile_coord().getRowIndex() 
				+ " " + node.getChildren()[maxnode].getTile_coord().getColIndex());
		System.out.println("max weight is" + maxWeight);
		return maxWeight;
	}
		else{
			finalMove = null;
			return node.getWeight();
		}
	}
	
}
