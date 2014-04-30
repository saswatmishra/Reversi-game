package reversi;

import java.awt.event.*;
//import java.awt.*;
import javax.swing.*;
import java.awt.Graphics;
//import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

//import javax.swing.JComponent;
import javax.swing.JFrame;

public class Grids extends JPanel implements MouseListener {

	private static final long serialVersionUID = -3146737332328071955L;
	static GameBoard Reversi = new GameBoard();
	Image image1;
	Image image2;
	Image image3;
	static int clickedX, clickedY, a = 0;
	static boolean userTurn = false;
	// static boolean userTurn2 = false;
	static Tile currentBoard[][] = new Tile[8][8];
	static Tile[] vMoveArray;
	static int count1=1;
	static int count2=1;
	static int count3=1;
	static String s;

	// static Tile [] array = new Tile[16];

	// Create a constructor method
	public Grids() {
		super();
		addMouseListener(this);
		image1 = Toolkit.getDefaultToolkit().getImage("Black40.png");
		image2 = Toolkit.getDefaultToolkit().getImage("Red40.png");
		image3 = Toolkit.getDefaultToolkit().getImage("White40.png");
	}

	/*
	 * public static void initVMove() {
	 * 
	 * 
	 * int moves = array.length; for(int i =0; i<moves; i++){ vMoveArray[i] =
	 * array[i]; System.out.println(vMoveArray[i]); } for(int j=moves; j<16;
	 * j++){ vMoveArray[j] = null; System.out.println(vMoveArray[j]); }
	 * 
	 * 
	 * }
	 */

	public void mouseClicked(MouseEvent mouse) {

		// Get the location of the current mouse click.
		int cx, cy;
		boolean moveStatus = false;

		if (userTurn) {

			System.out.println("Players Turn");
			cx = tileCoord(mouse.getX(), mouse.getY())[0];
			cy = tileCoord(mouse.getX(), mouse.getY())[1];
			// System.out.println(cx + "  cx" + cy);
			if(!(vMoveArray == null)){
			for(int m=0; m<vMoveArray.length; m++)
				System.out.println("its is updated*************" + vMoveArray[m].getRowIndex() + " " + vMoveArray[m].getColIndex());
			}
			for (int i = 0; i < vMoveArray.length; i++) {
				if (cx == vMoveArray[i].getColIndex()
						&& cy == vMoveArray[i].getRowIndex()) {
					clickedX = cx;
					clickedY = cy;
					moveStatus = true;

					player(clickedY, clickedX, 1);

					break; // should this break be here ??
				}

			}
			if (!moveStatus) {
				System.out.println("Invallid Move");
			}
			if (moveStatus) {
				repaint();
				userTurn = false;
							
				aiTurn();
				s=calculateScore();
			}
		}
	}
	
	
	/*
	 * else{ userTurn2 = false; System.out.println("Computer's Turn");
	 * MinMaxAlgo minmaxAlgo = new MinMaxAlgo(); tileAI =
	 * minmaxAlgo.algoMinMax(currentBoard);
	 * 
	 * int tempx = tileAI.getRowIndex(); int tempy = tileAI.getColIndex();
	 * System.out.println("row is "+tempx+" col is"+tempy);
	 * player(tempx,tempy,2); //Reversi.boardUpdate(tileAI.getRowIndex(),
	 * tileAI.getColIndex(), 2);
	 * 
	 * //computer ai functions repaint(); userTurn = true; userTurn2 = true; }
	 */

	public static void aiTurn() {

		Tile tileAI = new Tile();
		// userTurn2 = false;
		System.out.println("Computer's Turn");

		/*MinMaxAlgo minmaxAlgo = new MinMaxAlgo();

		tileAI = minmaxAlgo.algoMinMax(currentBoard);*/
		
		AlphaBetaSearch absearch = new AlphaBetaSearch();
		tileAI = absearch.algoAlphaBeta(currentBoard);
		
	if (!(tileAI == null)){

		int tempx = tileAI.getRowIndex();
		int tempy = tileAI.getColIndex();
		System.out.println("row is " + tempx + " col is" + tempy);

		player(tempx, tempy, 2);
		}
		else {
			System.out.println("*************no valid moves , players turn*****************");
		}
		userTurn = true;

		// userTurn2 = true;
	}

	public static void player(int x, int y, int playerPeg) {
		// System.out.println("i am called");
		GameBoard UpdateBoard = new GameBoard();
		Tile CurrentState2[][] = new Tile[8][8];
		// System.out.println(clickedX + "  <-col  ->row" + clickedY);

		// CurrentState2 = UpdateBoard.UpdateBoard(clickedY, clickedX,
		// currentBoard, 1);
		CurrentState2 = UpdateBoard.UpdateBoard(x, y, currentBoard, playerPeg);
		for (int i = 0; i < currentBoard.length; i++) {
			for (int j = 0; j < currentBoard.length; j++) {
				System.out.print(" " + currentBoard[i][j].getPegValue());

			}
			System.out.println();
		}
		currentBoard = CurrentState2;

		/*
		 * GameTree utility = new GameTree(); int utilityValue =
		 * utility.Utility(CurrentState2); System.out.println(utilityValue +
		 * "*************");
		 */

	}

	public void paintComponent(Graphics g) {
		// g.drawLine(10,10,150,150); // Draw a line from (10,10) to (150,150)
		// g.drawLine(0,100,800,100);
		int y1array[] = new int[8];
		int y2array[] = new int[8];
		boolean ZeroStatus = false;
		int x1 = 0;
		int x2 = 400;
		for (int i = 1; i <= 8; i++) {
			y1array[i - 1] = i * 50;
			y2array[i - 1] = i * 50;
		}
		for (int j = 0; j < 8; j++) {
			g.drawLine(x1, y1array[j], x2, y2array[j]);

		}

		int x1array[] = new int[8];
		int x2array[] = new int[8];
		int y1v = 0;
		int y2v = 400;
		for (int i = 1; i <= 8; i++) {
			x1array[i - 1] = i * 50;
			x2array[i - 1] = i * 50;
		}
		for (int j = 0; j < 8; j++) {
			g.drawLine(x1array[j], y1v, x2array[j], y2v);

		}

		int x, y;

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (currentBoard[i][j].getPegValue() == 1) {
					// System.out.println( i+" "+j+" "+
					// currentBoard[i][j].getPegValue());
					y = currentBoard[i][j].getRowIndex() * 50;
					x = currentBoard[i][j].getColIndex() * 50;
					g.drawImage(image1, x + 4, y + 6, 40, 40, this);

				} else if (currentBoard[i][j].getPegValue() == 2) {
					y = currentBoard[i][j].getRowIndex() * 50;
					x = currentBoard[i][j].getColIndex() * 50;
					g.drawImage(image2, x + 4, y + 6, 40, 40, this); // here
					// replace
					// the
					// image
					// with
					// opponent
					// color
				} 
				else if (userTurn) {
					// System.out.println("userTurn2 is true");
					for (int k = 0; k < vMoveArray.length; k++) {
						if (currentBoard[i][j].getRowIndex() == vMoveArray[k]
								.getRowIndex()
								&& currentBoard[i][j].getColIndex() == vMoveArray[k]
										.getColIndex()) {
							y = currentBoard[i][j].getRowIndex() * 50;
							x = currentBoard[i][j].getColIndex() * 50;
							g.drawImage(image3, x + 4, y + 6, 40, 40, this);
							ZeroStatus = true;
						}
						// draw valid moves
					}
					if (ZeroStatus == false) {
						y = currentBoard[i][j].getRowIndex() * 50;
						x = currentBoard[i][j].getColIndex() * 50;
						g.clearRect(x + 4, y + 6, 40, 40);

					}
					ZeroStatus = false;
				} 

			}
		}
		// g.drawImage(image,54,56,40,40,this);

	}

	/*
	 * The following methods have to be here to comply with the MouseListener
	 * interface, but we don't use them, so their code blocks are empty.
	 */
	public void mouseEntered(MouseEvent mouse) {
	}

	public void mouseExited(MouseEvent mouse) {

	}

	public void mousePressed(MouseEvent mouse) {
	}

	public void mouseReleased(MouseEvent mouse) {

	}

	public int[] tileCoord(int x, int y) {
		int xy[] = new int[2]; // xy[0] is x and xy[1]is y value.
		// clickedX,clickedY
		xy[0] = x / 50;
		xy[1] = y / 50;
		return xy;
	}
	
	public static String calculateScore(){
		count1=0;
		count2=0;
		count3=0;
		for(int i=0;i<8;i++)
		{
			for(int j=0;j<8;j++)
			{
				int pegValue=currentBoard[i][j].getPegValue();
				if(pegValue==1)
					count1=count1+1;
				if(pegValue==2)
					count2=count2+1;
				if(pegValue==0)
					count3=count3+1;
			}
		}
		String s="   Score-   AI:  "+count2+"   You:  "+count1;
		return s;
	}

	public static void main(String arg[]) {

		Reversi.setbDim(8);
		Reversi.GameBoardinitialize(8);
		Tile CurrentState[][] = new Tile[8][8];
		CurrentState = Reversi.getGameBoard();
		currentBoard = CurrentState;
		userTurn = true;
		boolean gameloop = true;

		JFrame frame = new JFrame("BasicPanel");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(410, 440);
		Grids panel = new Grids();
		frame.setContentPane(panel);
		frame.setVisible(true);
		//frame.setResizable(false);
		// Tile [] array;
		int updatevarray = 0;
		while (gameloop && count1!=0 && count2!=0 && count3!=0) {

			// userTurn2 = false;
			updatevarray = updatevarray+1;
			
			if (userTurn) {
				
				vMoveArray = Reversi.ValidMove(currentBoard, 1);
				
				// userTurn2 = true;
				// initVMove();
			}
			
			s=calculateScore();

			// System.out.println("userTurn2" + userTurn2 );
			// for(int m=0; m<vMoveArray.length; m++){
			// System.out.println("vallid moves" + vMoveArray[m].getRowIndex()
			// );
			// }

			frame.setTitle("REVERSI" + s);

			try {
				// thread to sleep for the specified number of milliseconds
				Thread.sleep(1000);
			} catch (java.lang.InterruptedException ie) {
				System.out.println(ie);
			}

		}
		// }
	}
}
