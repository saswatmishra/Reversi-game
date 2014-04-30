package reversi;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;


public class GameBoard {
	public	int bDim; //dimension of game board, 8x8, 4x4 etc
	private Tile gameBoard [][]; /*The Reversi board matrix, 
	Its basically an array of Tiles*/
	private List<Integer> xcordValidMove = new ArrayList<Integer>();//x cordinate of valid move
	private List<Integer> ycordValidMove = new ArrayList<Integer>();//y coordinate of valid move
	private int xIndex;
	private int yIndex;
	
	/*Constructor to initialize the board with value zero (no pegs) */
	GameBoard()
	{
		bDim = 8;
	}
	public void GameBoardinitialize(int bDim)
	{
		gameBoard = new Tile[bDim][bDim];
		for (int row = 0; row < bDim; row++){
			for(int col = 0; col < bDim; col++){
				Tile addTile = new Tile();
				addTile.setColIndex(col);
				addTile.setRowIndex(row);
				addTile.setPegValue(0);
				//System.out.println(row + " " + col);
				gameBoard[row][col] = addTile; //this is a board with tiles
				addTile = null; //removing the object
			}
		}	
		gameBoard[3][3].setColIndex(3);
		gameBoard[3][3].setRowIndex(3);
		gameBoard[3][3].setPegValue(1);
		
		gameBoard[4][4].setColIndex(4);
		gameBoard[4][4].setRowIndex(4);
		gameBoard[4][4].setPegValue(1);
		
		gameBoard[3][4].setColIndex(4);
		gameBoard[3][4].setRowIndex(3);
		gameBoard[3][4].setPegValue(2);
		
		gameBoard[4][3].setColIndex(3);
		gameBoard[4][3].setRowIndex(4);
		gameBoard[4][3].setPegValue(2);
		
	}
	/* Method: boardUpdate
	 * to update a tile in the board for conditions:
	 * if player places a peg
	 * or a peg value changes, flips */
	
	public void boardUpdate(int row, int col, int value)
	{
		Tile changeTile;
		changeTile = gameBoard[row][col];
		changeTile.setPegValue(value);
		gameBoard[row][col] = changeTile;
	}
	
	/*Method:vallidTilesList DUMMY
	 * will set the list of valid tiles objects */
	
	
	/* Get and Set methods*/
	public int getbDim() {
		return bDim;
	}

	
	public void setbDim(int bDim) {
		this.bDim = bDim;
	}

			
	public Tile[][] getGameBoard() {
		return gameBoard;
	}

	public Tile[] ValidMove(Tile[][] GameBoard2,int PegVal)
	{
		
		GameBoard gBoard = new GameBoard();
		Tile [][] GameBoard = new Tile[8][8];
		gBoard.GameBoardinitialize(8);
		GameBoard = gBoard.getGameBoard();
		
		for (int w = 0; w< 8; w++) {
			for (int q = 0; q < 8; q++) {
				GameBoard[w][q].setPegValue(GameBoard2[w][q].getPegValue());
			}
		}
		
		int x,y;
		xcordValidMove.clear();
		ycordValidMove.clear();
		Boolean Status = false;
		Tile[] ValiMoveList;
		int OppPegVal;
		int k=0;
		if(PegVal == 1)
		{
			OppPegVal = 2;
		}
		else
		{
			OppPegVal = 1;
		}
		for(int i=0;i<8;i++)
		{
			for(int j=0;j<8;j++)
			{
				if(GameBoard[i][j].getPegValue() == OppPegVal)
				{
					if(i-1>-1 && i-1<8 && j-1>-1 && j-1<8 && GameBoard[i-1][j-1].getPegValue()==0)
					{
						x = i-1;
						y = j-1;
						
						Status = DownRight(x,y,PegVal,GameBoard);
						if(Status == true)
						{
							xcordValidMove.add(x);
							ycordValidMove.add(y);
						}
						Status = false;
					}
					if(i-1>-1 && i-1<8 && j>-1 && j<8 && GameBoard[i-1][j].getPegValue()==0)
					{
						x = i-1;
						y = j;
						
						Status = Down(x,y,PegVal,GameBoard);
						if(Status == true)
						{
							xcordValidMove.add(x);
							ycordValidMove.add(y);
						}
						Status = false;
					}
					if(i-1>-1 && i-1<8 && j+1>-1 && j+1<8 && GameBoard[i-1][j+1].getPegValue()==0)
					{
						x = i-1;
						y = j+1;
						
						Status = DownLeft(x,y,PegVal,GameBoard);
						if(Status == true)
						{
							xcordValidMove.add(x);
							ycordValidMove.add(y);
						}
						Status = false;
					}
					if(i>-1 && i<8 && j-1>-1 && j-1<8 && GameBoard[i][j-1].getPegValue()==0)
					{
						x = i;
						y = j-1;
						
						Status = Right(x,y,PegVal,GameBoard);
						if(Status == true)
						{
							xcordValidMove.add(x);
							ycordValidMove.add(y);
						}
						Status = false;
					}
					if(i>-1 && i<8 && j+1>-1 && j+1<8 && GameBoard[i][j+1].getPegValue()==0)
					{
						x =i;
						y = j+1;
						Status = Left(x,y,PegVal,GameBoard);
						if(Status == true)
						{
							xcordValidMove.add(x);
							ycordValidMove.add(y);
						}
						Status = false;
					}
					if(i+1>-1 && i+1<8 && j-1>-1 && j-1<8 && GameBoard[i+1][j-1].getPegValue()==0)
					{
						x =i+1;
						y = j-1;
						Status = UpRight(x,y,PegVal,GameBoard);
						if(Status == true)
						{
							xcordValidMove.add(x);
							ycordValidMove.add(y);
						}
						Status = false;
					}
					if(i+1>-1 && i+1<8 && j>-1 && j<8 && GameBoard[i+1][j].getPegValue()==0)
					{
						x =i+1;
						y = j;
						Status = Up(x,y,PegVal,GameBoard);
						if(Status == true)
						{
							xcordValidMove.add(x);
							ycordValidMove.add(y);
						}
						Status = false;
					}
					if(i+1>-1 && i+1<8 && j+1>-1 && j+1<8 && GameBoard[i+1][j+1].getPegValue()==0)
					{
						x =i+1;
						y = j+1;
						Status = UpLeft(x,y,PegVal,GameBoard);
						if(Status == true)
						{
							xcordValidMove.add(x);
							ycordValidMove.add(y);
						}
						Status = false;
					}
				}
			}
		}
		ValiMoveList = new Tile[xcordValidMove.size()];
		
		Iterator<Integer> iter1 = xcordValidMove.iterator();
		Iterator<Integer> iter2 = ycordValidMove.iterator();
		if(!iter1.hasNext()&&!iter2.hasNext())
		{
			return null;
		}
		while(iter1.hasNext() && iter2.hasNext())
		{
			ValiMoveList[k] = new Tile();
			ValiMoveList[k].setRowIndex(iter1.next());
			ValiMoveList[k].setColIndex(iter2.next());
			ValiMoveList[k].setPegValue(PegVal);
			k++;
		}
		//System.out.println("validmove computed");
		return ValiMoveList;
	}
	public Boolean DownRight(int x,int y,int pegValue,Tile[][] GameBoard)
	{
		Boolean status = false;
		int l,m;
		l = x+1;
		m = y+1;
		while(l>-1 && l<8 && m>-1 && m<8)
		{
			if(GameBoard[l][m].getPegValue()==pegValue)
			{
				status = true;
				xIndex = l;
				yIndex = m;
				break;
			}
			l = l+1;
			m = m+1;
		}
		return status;
	}
	public Boolean Down(int x,int y,int pegValue,Tile[][] GameBoard)
	{
		Boolean status = false;
		int l,m;
		l = x+1;
		m = y;
		while(l>-1 && l<8 && m>-1 && m<8)
		{
			if(GameBoard[l][m].getPegValue()==pegValue)
			{
				xIndex = l;
				yIndex = m;
				status = true;
				break;
			}
			l = l+1;
			
		}
		return status;
	}
	public Boolean DownLeft(int x,int y,int pegValue,Tile[][] GameBoard)
	{
		Boolean status = false;
		int l,m;
		l = x+1;
		m = y-1;
		while(l>-1 && l<8 && m>-1 && m<8)
		{
			if(GameBoard[l][m].getPegValue()==pegValue)
			{
				xIndex = l;
				yIndex = m;
				status = true;
				break;
			}
			l = l+1;
			m= m-1;
			
		}
		return status;
	}
	
	public Boolean Right(int x, int y,int pegValue,Tile[][] GameBoard)
	{
		Boolean status = false;
		int l,m;
		l = x;
		m = y+1;
		while(l>-1 && l<8 && m>-1 && m<8)
		{
			if(GameBoard[l][m].getPegValue()==pegValue)
			{
				xIndex = l;
				yIndex = m;
				status = true;
				break;
			}
			
			m= m+1;
			
		}
		return status;
	}
	
	public Boolean Left(int x, int y,int pegValue,Tile[][] GameBoard)
	{
		Boolean status = false;
		int l,m;
		l = x;
		m = y-1;
		while(l>-1 && l<8 && m>-1 && m<8)
		{
			if(GameBoard[l][m].getPegValue()==pegValue)
			{
				xIndex = l;
				yIndex = m;
				status = true;
				break;
			}
			
			m= m-1;
			
		}
		return status;
	}
	
	public Boolean UpRight(int x,int y,int pegValue,Tile[][] GameBoard)
	{
		Boolean status = false;
		int l,m;
		l = x-1;
		m = y+1;
		while(l>-1 && l<8 && m>-1 && m<8)
		{
			if(GameBoard[l][m].getPegValue()==pegValue)
			{
				xIndex = l;
				yIndex = m;
				status = true;
				break;
			}
			l = l-1;
			m = m+1;
			
		}
		return status;
	}
	
	public Boolean Up(int x,int y,int pegValue,Tile[][] GameBoard)
	{
		Boolean status = false;
		int l,m;
		l = x-1;
		m = y;
		while(l>-1 && l<8 && m>-1 && m<8)
		{
			if(GameBoard[l][m].getPegValue()==pegValue)
			{
				xIndex = l;
				yIndex = m;
				status = true;
				break;
			}
			l = l-1;
			
			
		}
		
		return status;
	}
	
	public Boolean UpLeft(int x,int y,int pegValue,Tile[][] GameBoard)
	{
		Boolean status = false;
		int l,m;
		l = x-1;
		m = y-1;
		while(l>-1 && l<8 && m>-1 && m<8)
		{
			if(GameBoard[l][m].getPegValue()==pegValue)
			{
				xIndex = l;
				yIndex = m;
				status = true;
				break;
			}
			l = l-1;
			m = m-1;
					
		}
		return status;
		
	}

public Tile[][] UpdateBoard(int row,int col, Tile GameBoard2[][],int pValue)
{
//System.out.println("row"+ row + " col" + col + "peg" + pValue);
//System.out.println(GameBoard[row][col].getPegValue()+ " peg value initial");
	
	GameBoard gBoard = new GameBoard();
	Tile [][] GameBoard = new Tile[8][8];
	gBoard.GameBoardinitialize(8);
	GameBoard = gBoard.getGameBoard();
	
	for (int w = 0; w< 8; w++) {
		for (int q = 0; q < 8; q++) {
			GameBoard[w][q].setPegValue(GameBoard2[w][q].getPegValue());
		}
	}
	
	Boolean Flag = false;
	GameBoard[row][col].setPegValue(pValue);
	/* int length = GameBoard.length;
	for (int i = 0; i < length; i++) {
	for (int j = 0; j < length; j++) {
		System.out.print(" "
				+ GameBoard[i][j].getPegValue() );
		
	}
	System.out.println();
}*/
	GameBoard[row][col].setPegValue(pValue);
	if(GameBoard[row][col].getPegValue()==pValue)
	{
		Flag = UpLeft(row, col, pValue,GameBoard);
		if(Flag==true)
		{
			GameBoard = UpdateUpLeft(row,col,GameBoard,pValue);
			Flag = false;
		}
		Flag = Up(row,col,pValue,GameBoard);
		if(Flag==true)
		{
			GameBoard = UpdateUp(row,col,GameBoard,pValue);
			Flag = false;
		}
		Flag = UpRight(row,col,pValue,GameBoard);
		if(Flag==true)
		{
			GameBoard = UpdateUpRight(row,col,GameBoard,pValue);
			Flag = false;
		}
		Flag = Left(row,col,pValue,GameBoard);
		if(Flag==true)
		{
			GameBoard = UpdateLeft(row,col,GameBoard,pValue);
			Flag = false;
		}
		Flag = Right(row,col,pValue,GameBoard);
		if(Flag==true)
		{
			GameBoard = UpdateRight(row,col,GameBoard,pValue);
			Flag = false;
		}
		Flag = DownLeft(row,col,pValue,GameBoard);
		if(Flag==true)
		{
			GameBoard = UpdateDownLeft(row,col,GameBoard,pValue);
			Flag = false;
		}
		Flag = Down(row,col,pValue,GameBoard);
		if(Flag==true)
		{
			GameBoard = UpdateDown(row,col,GameBoard,pValue);
			Flag = false;
		}
		Flag = DownRight(row,col,pValue,GameBoard);
		if(Flag==true)
		{
			GameBoard = UpdateDownRight(row,col,GameBoard,pValue);
			Flag = false;
		}
		
	}
	//GameBoard[4][5].setPegValue(1);
	//System.out.println(GameBoard[row][col].getPegValue()+ " peg value final");
	
/*
	for (int i = 0; i < length; i++) {
	for (int j = 0; j < length; j++) {
		System.out.print(" "
				+ GameBoard[i][j].getPegValue() );
		
	}
	System.out.println();
}*/
	return GameBoard;
}

public Tile[][] UpdateUpLeft(int row,int col,Tile GameBoard[][],int pValue)
{
	int l,m;
	l = row-1;
	m = col-1;
	while(l>=xIndex && m>=yIndex)
	{
		GameBoard[l][m].setPegValue(pValue);
		l = l-1;
		m = m-1;
				
	}
	return GameBoard;
}

public Tile[][] UpdateUp(int row,int col,Tile GameBoard[][],int pValue)
{
	int l,m;
	l = row-1;
	m = col;
	//System.out.println("X-Index:"+xIndex);
	
	while(l>=xIndex)
	{
		GameBoard[l][m].setPegValue(pValue);
		l = l-1;
						
	}
	return GameBoard;
}
public Tile[][] UpdateUpRight(int row,int col,Tile GameBoard[][],int pValue)
{
	int l,m;
	l = row-1;
	m = col+1;
	while(l>=xIndex && m<=yIndex)
	{
		GameBoard[l][m].setPegValue(pValue);
		l = l-1;
		m=m+1;
						
	}
	return GameBoard;
}
public Tile[][] UpdateLeft(int row,int col,Tile GameBoard[][],int pValue)
{
	int l,m;
	l = row;
	m = col-1;
	while(m>=yIndex)
	{
		GameBoard[l][m].setPegValue(pValue);
		m=m-1;
						
	}
	return GameBoard;
}
public Tile[][] UpdateRight(int row,int col,Tile GameBoard[][],int pValue)
{
	int l,m;
	l = row;
	m = col+1;
	while(m<=yIndex)
	{
		GameBoard[l][m].setPegValue(pValue);
		m=m+1;
						
	}
	return GameBoard;
}
public Tile[][] UpdateDownLeft(int row,int col,Tile GameBoard[][],int pValue)
{
	int l,m;
	l = row+1;
	m = col-1;
	while(m>=yIndex && l<=xIndex)
	{
		GameBoard[l][m].setPegValue(pValue);
		l=l+1;
		m=m-1;
						
	}
	return GameBoard;
}
public Tile[][] UpdateDown(int row,int col,Tile GameBoard[][],int pValue)
{
	int l,m;
	l = row+1;
	m= col;
	
	while(l<=xIndex)
	{
		GameBoard[l][m].setPegValue(pValue);
		l=l+1;
		
						
	}
	return GameBoard;
}

public Tile[][] UpdateDownRight(int row,int col,Tile GameBoard[][],int pValue)
{
	int l,m;
	l = row+1;
	m= col + 1;
	
	while(l<=xIndex && m<=yIndex)
	{
		GameBoard[l][m].setPegValue(pValue);
		l=l+1;
		m= m+1;
							
	}
	return GameBoard;
}
}
	
	
	
	
	
	
