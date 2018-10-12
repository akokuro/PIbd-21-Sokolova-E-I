package laba_1;

import java.awt.Graphics;

import javax.swing.JPanel;

public class DrawingHelper extends JPanel {
	
	private Ship ship;
	
	public void addShip(Ship ship) {
		this.ship = ship;
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		if(ship != null)
			ship.DrawShip(g);
	}
}
