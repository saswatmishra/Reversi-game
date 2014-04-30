package reversi;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class ReversiGUI extends JFrame implements MouseListener {
	public Tile [][] currentBoard = null;
    public JPanel board = new JPanel();
	public JLabel[][] grid; 
	
	
	
	
	
	
	public void invalidMessage()
	{
		JOptionPane.showMessageDialog(this, "invalid move!! try again");
		
	}
	
	public void buildGui(int x, int y)
	{ 
		//panelContent.setLayout(new GridLayout(x,y));
		grid = new JLabel[x][y];
		for(int j=0; j<x; j++){
            for(int i=0; i<y; i++){
                    grid[i][j]=new JLabel(); //creates new button    
          //          panelContent.add(grid[i][j]); //adds button to grid
		
            }			
		}
		
		//panelContent.setVisible(true);
		//panelContent.pack();
		//panelContent.setTitle("game");
		
	}
	
	
	
	
	public static void main(String args[])
	{
		ReversiGUI gui = new ReversiGUI();
		gui.buildGui(8, 8);
	}
	
	
	
	

	private Color Color(int i, int j, int k) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}