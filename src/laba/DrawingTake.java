package laba;

import java.awt.Graphics;

import javax.swing.JPanel;

public class DrawingTake extends JPanel{
	private IShip ship;
	public void addShip(IShip ship) {
		this.ship = ship;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		if (ship != null)
			ship.DrawShip(g);
	}
	
}
