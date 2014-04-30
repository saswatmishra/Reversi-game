package reversi;

public class Tile {
	
	/*Every Tile will have three fields
	 * it rowindex, column index and the peg value as 1,2,0*/
	private int rowIndex;
	private int colIndex;
	private int pegValue; // 0,1,2 for no tile,blacktile,whitetile
	
	
	public int getRowIndex() {
		return rowIndex;
	}
	
	public void setRowIndex(int rowIndex) {
		this.rowIndex = rowIndex;
	}
	
	public int getColIndex() {
		return colIndex;
	}
	
	public void setColIndex(int colIndex) {
		this.colIndex = colIndex;
	}
	
	public int getPegValue() {
		return pegValue;
	}
	
	public void setPegValue(int pegValue) {
		this.pegValue = pegValue;
	}
}
