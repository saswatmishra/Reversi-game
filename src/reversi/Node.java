package reversi;

public class Node {

	
	private int level; //at what level the node is, this will help in applying utility function
	private Node children[]; // list of all children
	private Node parent; //single parent
	private int weight = 0; //initially weight of each node
	private Tile tile_coord; //node coordinates on board
	private Tile [][] BoardState;
	

	public Tile[][] getBoardState() {
		return BoardState;
	}
	public void setBoardState(Tile[][] boardState) {
		BoardState = boardState;
	}
	
	public Tile getTile_coord() {
		return tile_coord;
	}
	public void setTile_coord(Tile tile_coord) {
		this.tile_coord = tile_coord;
	}
	
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public Node[] getChildren() {
		return children;
	}
	public void setChildren(Node[] children) {
		this.children = children;
	}
	public Node getParent() {
		return parent;
	}
	public void setParent(Node parent) {
		this.parent = parent;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
}
