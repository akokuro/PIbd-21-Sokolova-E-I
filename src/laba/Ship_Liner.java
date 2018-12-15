package laba;

import java.awt.Color;
import java.awt.Graphics;

public class Ship_Liner extends Ship {
	// Ширина отрисовки коробля
	private int shipWidth = 100;
	// Ширина отрисовки коробля
	private int shipHeight = 60;

	// Дополнительный цвет
	public Color DopColor;

	public Color getDopColor() {
		return DopColor;
	}

	public void setDopColor(Color value) {
		DopColor = value;
	}

	// Признак наличия трубы
	public boolean Pipe;

	public boolean getPipe() {
		return Pipe;
	}

	public void setPipe(boolean value) {
		Pipe = value;
	}

	// Признак наличия дыма из трубы
	public boolean SmokeFromPipe;

	public boolean getSmokeFromPipe() {
		return SmokeFromPipe;
	}

	public void setSmokeFromPipe(boolean value) {
		SmokeFromPipe = value;
	}

	// Признак наличия окон
	public boolean Window;

	public boolean getWindow() {
		return Window;
	}

	public void setWindow(boolean value) {
		Window = value;
	}

	// Конструктор
	public Ship_Liner(int maxSpeed, float weight, Color mainColor, Color dopColor, boolean pipe, boolean smokeFromPipe,
			boolean window) {
		super(maxSpeed, weight, mainColor);
		DopColor = dopColor;
		Pipe = pipe;
		SmokeFromPipe = smokeFromPipe;
		Window = window;
	}

	// Отрисовка коробля
	@Override
	public void DrawShip(Graphics g) {
		// отрисуем сперва трубу
		if (Pipe) {
			g.setColor(MainColor);
			g.fillRect((int) _startPosX + 60, (int) _startPosY - 50, 10, 15);
		}
		// и дым
		if (SmokeFromPipe) {
			g.setColor(Color.GRAY);
			g.fillOval((int) _startPosX + 57, (int) _startPosY - 47, 2, 2);
			g.fillOval((int) _startPosX + 52, (int) _startPosY - 44, 3, 3);
			g.fillOval((int) _startPosX + 43, (int) _startPosY - 42, 5, 5);
		}
		// теперь отрисуем основную часть корабля
		g.setColor(MainColor);
		g.drawLine((int) _startPosX + 10, (int) _startPosY, (int) _startPosX + 80, (int) _startPosY);
		g.drawLine((int) _startPosX + 10, (int) _startPosY, (int) _startPosX, (int) _startPosY - 25);
		g.drawLine((int) _startPosX + 80, (int) _startPosY, (int) _startPosX + 90, (int) _startPosY - 25);
		g.drawLine((int) _startPosX, (int) _startPosY - 25, (int) _startPosX + 90, (int) _startPosY - 25);
		g.setColor(Color.RED);
		g.drawLine((int) _startPosX + 5, (int) _startPosY - 13, (int) _startPosX + 85, (int) _startPosY - 13);
		g.setColor(Color.BLACK);
		g.drawRect((int) _startPosX + 10, (int) _startPosY - 35, 70, 10);

		// рисуем окна
		if (Window) {
			g.setColor(DopColor);
			g.fillOval((int) _startPosX + 45, (int) _startPosY - 23, 7, 7);
			g.fillOval((int) _startPosX + 35, (int) _startPosY - 23, 7, 7);
			g.fillOval((int) _startPosX + 55, (int) _startPosY - 23, 7, 7);
			g.fillOval((int) _startPosX + 65, (int) _startPosY - 23, 7, 7);
			g.fillOval((int) _startPosX + 25, (int) _startPosY - 23, 7, 7);
			g.fillOval((int) _startPosX + 15, (int) _startPosY - 23, 7, 7);
			g.fillOval((int) _startPosX + 75, (int) _startPosY - 23, 7, 7);
			g.fillOval((int) _startPosX + 5, (int) _startPosY - 23, 7, 7);
		}
	}
}
