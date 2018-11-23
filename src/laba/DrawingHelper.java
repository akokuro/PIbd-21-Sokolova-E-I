package laba;

import java.awt.Graphics;

import javax.swing.JPanel;

public class DrawingHelper extends JPanel {

	public Dock<IShip> Docker;
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Docker.Draw(g);
	}
}
