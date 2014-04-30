package reversi;

public class AlphaBetaSearch {

	private Tile finalMove;

	public Tile algoAlphaBeta(Tile[][] currentGameBoard2) {
		
		GameBoard gboard = new GameBoard();
		gboard.GameBoardinitialize(8);
		Tile [][] currentGameBoard = new Tile[8][8];
		
		for(int k=0;k<8;k++){
			for(int w=0; w<8; w++){
				currentGameBoard[k][w] = gboard.getGameBoard()[k][w];
			}
		}
		
		for(int k=0;k<8;k++){
			for(int w=0; w<8; w++){
				currentGameBoard[k][w] = currentGameBoard2[k][w];
			}
		}
		
		
		Node root = new Node();
		GameTree gameTree = new GameTree();
		root = gameTree.genGameTree(currentGameBoard, 3);
		int a = 9999;
		int b = -9999;
		maxSearch(root, a, b);
		return finalMove;
	}

	private int maxSearch(Node state, int a, int b) {
		int v = -9999;
		Node node;
		
		if(!(state.getChildren() == null)){
		for (int i = 0; i < state.getChildren().length; i++) {
			node = state.getChildren()[i];
			int x = node.getTile_coord().getRowIndex();
			int y = node.getTile_coord().getColIndex();
						
			GameBoard gboard = new GameBoard();
			gboard.GameBoardinitialize(8);
			Tile [][] gameBoard = new Tile[8][8];
			
			for(int k=0;k<8;k++){
				for(int w=0; w<8; w++){
					gameBoard[k][w] = gboard.getGameBoard()[k][w];
				}
			}
			
			for(int k=0;k<8;k++){
				for(int w=0; w<8; w++){
					
					gameBoard[k][w] = state.getBoardState()[k][w];
					
				}
			}
			
			gameBoard[x][y].setPegValue(2);
			int temp;
			temp = minSearch(node, a, b, gameBoard);
			if (v < temp) {
				v = temp;
				finalMove = node.getTile_coord();
			}
			/*
			 * if(v>=b) break;
			 */
			if (v > a)
				a = v;
		}
		return v;
		}
		else
			return 0;
		
	}

	private int minSearch(Node state, int a, int b, Tile[][] gameBoard2) {
		int v = 9999;
		Node node;
		
		GameBoard gboard = new GameBoard();
		gboard.GameBoardinitialize(8);
		Tile [][] gameBoard = new Tile[8][8];
		
		for(int k=0;k<8;k++){
			for(int w=0; w<8; w++){
				gameBoard[k][w] = gboard.getGameBoard()[k][w];
			}
		}
		
		for(int k=0;k<8;k++){
			for(int w=0; w<8; w++){
				gameBoard[k][w] = gameBoard2[k][w];
			}
		}

		for (int j = 0; j < state.getChildren().length; j++) {

			int randomNum2 = 5 + (int) (Math.random() * 50);
			node = state.getChildren()[j];
			int x = node.getTile_coord().getRowIndex();
			int y = node.getTile_coord().getColIndex();
			int temp;
			gameBoard[x][y].setPegValue(1);
			GameTree gameTree = new GameTree();
			// temp=gameTree.Utility(gameBoard);
			temp = randomNum2;
			if (v < temp)
				v = temp;
			if (v <= a)
				return v;
			if (v > b)
				b = v;
		}
		return v;
	}
}
