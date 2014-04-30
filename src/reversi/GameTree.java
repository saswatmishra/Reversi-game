package reversi;

public class GameTree {

	// for root node initially tile_coord is zero.

	Node root = new Node();

	// root node is the final move so,
	/*
	 * GameBoard will give me the list of objects of vallid Tiles "moves" in
	 * form of an array so I will iterate for every value in array to add it to
	 * my root, but before that I will call the function to calculate the vallid
	 * moves for given game state and player
	 */

	private void genTree(int treeLevel, Node parent, Tile gameBoard2[][],int peg) {
		
		GameBoard gboard = new GameBoard();
		gboard.GameBoardinitialize(8);
		Tile[][]gameBoard = new Tile[8][8];
		gameBoard = gboard.getGameBoard();
		for (int w = 0; w < 8; w++) {
			for (int q = 0; q < 8; q++) {
				gameBoard[w][q].setPegValue(gameBoard2[w][q]
						.getPegValue());
			}
		}
		
		int numChildren;
		GameBoard gameMoves = new GameBoard();
		Tile[][] NodeBoard;
		Tile [] validmoves ;
		
		validmoves = gameMoves.ValidMove(gameBoard, peg); // compute
																	// vallid
																	// moves for
																	// current
																	// state of
			//System.out.println("length is " +validmoves.length );														// gameBoard
		if(!(validmoves == null)){
		int numMoves = validmoves.length;
		
		
		Node childList[] = new Node[numMoves];
		for (numChildren = 0; numChildren < numMoves; numChildren++) {
			Node child = new Node();
			child.setChildren(null);
			child.setLevel(treeLevel++);
			if(!(parent == null)){
			child.setParent(parent);
			}
			else
				System.out.println("parent is null");
			
			child.setWeight(0); // recheck
			child.setTile_coord(validmoves[numChildren]);
			childList[numChildren] = child;
			NodeBoard = gameMoves.UpdateBoard(
					validmoves[numChildren].getRowIndex(),
					validmoves[numChildren].getColIndex(), gameBoard, peg);
			if(!(NodeBoard == null)){
			child.setBoardState(NodeBoard);
				}
			else
				System.out.println("board is null");
			
			child = null;
		}
		parent.setChildren(childList);
		//System.out.println("board is not null" + parent.getChildren()[0].getBoardState()[4][5].getColIndex());
		
		}
		else{
			parent.setChildren(null);
			System.out.println("no vallid moves, so no children");
		}
		
	}

	public Node genGameTree(Tile gameBoard[][], int gameLevel) {

		int treeLevel = 0; // print and check the tree level
		int gLevel = 0; // easy,average,hard
		int numchild = 0;
		int everychild;
		root.setParent(null);
		root.setLevel(0);
		root.setWeight(0);
		root.setTile_coord(null);
		
		//System.out.println(root.getChildren()[0].getParent().getTile_coord().getColIndex() + " root is" + root.getTile_coord().getColIndex());
		
		GameBoard updateboard = new GameBoard();
		Tile updatedGameboard[][] = new Tile[8][8];
		Tile currentGameBoard1[][] = new Tile[8][8];

		GameBoard currgameb = new GameBoard();
		currgameb.GameBoardinitialize(8);
		currentGameBoard1 = currgameb.getGameBoard();
		for (int w = 0; w < gameBoard.length; w++) {
			for (int q = 0; q < gameBoard.length; q++) {
				currentGameBoard1[w][q].setPegValue(gameBoard[w][q]
						.getPegValue());
			}
		}
				
		
		System.out.println("for root@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		genTree(treeLevel, root, currentGameBoard1,2);
		for(int z= 0; z<root.getChildren().length; z++){
		System.out.println("the child coordinates are" + root.getChildren()[z].getTile_coord().getRowIndex() +"  **** "	+ root.getChildren()[z].getTile_coord().getColIndex());
		}
		
		for (gLevel = 2; gLevel <= gameLevel; gLevel++) {
			if(!(root.getChildren() == null)){
			numchild = root.getChildren().length;
			for (everychild = 0; everychild < numchild; everychild++) {
				
				
				int peg = 2;
				for (int w = 0; w < gameBoard.length; w++) {
					for (int q = 0; q < gameBoard.length; q++) {
						updatedGameboard[w][q] = updateboard.UpdateBoard(root
								.getChildren()[everychild].getTile_coord()
								.getRowIndex(), root.getChildren()[everychild]
								.getTile_coord().getColIndex(),
								currentGameBoard1, peg )[w][q];
					}
				}
				
				
				genTree(treeLevel, root.getChildren()[everychild],
						updatedGameboard,1);
			}
		}
		}
		return root;

	}

	/**
	 * Utility(): Is used to calculate utility values of min max nodes
	 * 
	 * @param gameBoard
	 *            : State of min/max node
	 * @return Utility value of node
	 */
	public int Utility(Tile[][] gameBoard) {
		int Utility = 0;
		int i;
		int j;
		if (gameBoard[0][0].getPegValue() == 2)// If (0,0) corner has black
		{
			Utility += 10;
		}
		if (gameBoard[0][7].getPegValue() == 2)// If (0,8) corner has black
		{
			Utility += 10;
		}

		// Check if it is next to corner (0,0)
		if (gameBoard[0][1].getPegValue() == 2)// If (0,0) corner has black
		{
			Utility -= 8;
		}
		if (gameBoard[1][1].getPegValue() == 2)// If (0,0) corner has black
		{
			Utility -= 8;
		}
		if (gameBoard[1][0].getPegValue() == 2)// If (0,0) corner has black
		{
			Utility -= 8;
		}
		// Check if it is next to corner (8,8)
		for (i = 6; i <= 7; i++) {
			for (j = 6; j <= 7; j++) {
				if (i == 7 && j == 7) {
					if (gameBoard[1][0].getPegValue() == 2)// If (0,0) corner
															// has black
					{
						Utility += 10;
					}
				}

				else {
					if (gameBoard[1][0].getPegValue() == 2)// If (0,0) corner
															// has black
					{
						Utility -= 8;
					}
				}

			}
		}
		// Check if it is next to corner (7,0)
		for (i = 6; i <= 7; i++) {
			for (j = 0; j <= 1; j++) {
				if (i == 7 && j == 0) {
					if (gameBoard[1][0].getPegValue() == 2)// If (0,0) corner
															// has black
					{
						Utility += 10;
					}
				}

				else {
					if (gameBoard[1][0].getPegValue() == 2)// If (0,0) corner
															// has black
					{
						Utility -= 8;
					}
				}

			}
		}

		for (i = 1; i < 7; i++)// Check in first row
		{
			if (gameBoard[0][i].getPegValue() == 2) {
				Utility += 8;
			}
		}
		for (i = 1; i < 7; i++)// check in last row
		{
			if (gameBoard[7][i].getPegValue() == 2) {
				Utility += 8;
			}
		}
		for (i = 1; i < 7; i++)// check in first col
		{
			if (gameBoard[i][0].getPegValue() == 2) {
				Utility += 8;
			}
		}
		for (i = 1; i < 7; i++)// check in last col
		{
			if (gameBoard[i][7].getPegValue() == 2) {
				Utility += 8;
			}
		}
		for (i = 0; i < 8; i++) {
			for (j = 0; j < 8; j++) {
				if (gameBoard[i][j].getPegValue() == 2) {
					Utility += 1;
				}
			}
		}
		// System.out.println("In Utility function"+Utility);
		return Utility;
	}

}
