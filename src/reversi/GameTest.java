package reversi;

public class GameTest {
	
	public static void main(String[] args) {
		
		GameBoard Reversi = new GameBoard();
		Reversi.setbDim(8);
		int gridSize = Reversi.getbDim();
		//System.out.println(gridSize);
		Reversi.GameBoardinitialize(8);
		//System.out.println("ruunning");
		Reversi.boardUpdate(1, 1, 1);
		Reversi.boardUpdate(2, 2, 1);
		Tile CurrentState[][] = new Tile[gridSize][gridSize];
		CurrentState = Reversi.getGameBoard();
		int length = CurrentState.length;
		System.out.println(length);
		int count = 1;
		for (int i=0;i< length; i++)
		{
			for (int j = 0 ;j< length;j++)
			{
				System.out.println( i+" "+j+" "+ CurrentState[i][j].getPegValue()+ "   " +count);
				count++;
			}
		}
		
		
		int l2 = Reversi.ValidMove(CurrentState,1).length;
		for (int i = 0; i < l2; i++) {
			System.out.println(Reversi.ValidMove(CurrentState,1)[i]
					.getRowIndex()
					+ "   "
					+ Reversi.ValidMove(CurrentState,1)[i]
							.getPegValue());
		}
	
		GameTree reversiTree = new GameTree();
		reversiTree.genGameTree(CurrentState, 1);
	}
	
}

	
	
	
	


