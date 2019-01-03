package laba;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

public class Ship_Liner extends Ship implements Serializable {
	// Ширина отрисовки коробля
	private int shipWidth = 100;
	// Ширина отрисовки коробля
	private int shipHeight = 60;

	// Дополнительный цвет
	public Color DopColor;

	// Признак наличия трубы
	public boolean Pipe;

	// Признак наличия дыма из трубы
	public boolean SmokeFromPipe;

	// Признак наличия окон
	public boolean Window;

	// Конструктор
	public Ship_Liner(int maxSpeed, float weight, Color mainColor, Color dopColor, boolean pipe, boolean smokeFromPipe,
			boolean window) {
		super(maxSpeed, weight, mainColor);
		DopColor = dopColor;
		Pipe = pipe;
		SmokeFromPipe = smokeFromPipe;
		Window = window;
	}

	// Перегрузка конструктора с предоставлением информации
	public Ship_Liner(String info) {
		super(info);
		String[] strs = info.split(";", 0);
		if (strs.length == 7) {
			MaxSpeed = Integer.parseInt(strs[0]);
			Weight = Integer.parseInt(strs[1]);
			MainColor = getColorByName(strs[2]);
			DopColor = getColorByName(strs[3]);
			Pipe = strs[4].equals("true");
			SmokeFromPipe = strs[5].equals("true");
			Window = strs[6].equals("true");
		}
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
		g.setColor(MainColor);
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

	public void SetDopColor(Color color) {
		DopColor = color;
	}

	// преобразование информации в строку
	public String toString() {
		return super.toString() + ";" + getColorName(DopColor) + ";" + Pipe + ";" + SmokeFromPipe + ";" + Window;
	}

	public int compareTo(Ship_Liner another) {
		if (another == null)
			return 1;
		if (MaxSpeed != another.MaxSpeed)
			return Integer.compare(MaxSpeed, another.MaxSpeed);
		if (Weight != another.Weight)
			return Float.compare(Weight, another.Weight);
		if (MainColor != another.MainColor)
			return Integer.compare(MainColor.getRGB(), another.MainColor.getRGB());
		if (DopColor != another.DopColor)
			return Integer.compare(DopColor.getRGB(), another.DopColor.getRGB());
		if (Pipe != another.Pipe)
			return Boolean.compare(Pipe, another.Pipe);
		if (SmokeFromPipe != another.SmokeFromPipe)
			return Boolean.compare(SmokeFromPipe, another.SmokeFromPipe);
		if (Window != another.Window)
			return Boolean.compare(Window, another.Window);
		return 0;
	}

	@Override
	public boolean equals(Object another) {
		if (another == null) {
			return false;
		}
		if (!(another instanceof Ship_Liner)) {
			return false;
		}
		Ship_Liner ship_LinerObf = (Ship_Liner) another;
		return equals(ship_LinerObf);
	}

	public boolean equals(Ship_Liner another) {
		if (another == null)
			return false;
		if (!(another instanceof Ship_Liner)) {
			return false;
		}
		if (MaxSpeed != another.MaxSpeed)
			return false;
		if (Weight != another.Weight)
			return false;
		if (MainColor != another.MainColor)
			return false;
		if (DopColor != another.DopColor)
			return false;
		if (Pipe != another.Pipe)
			return false;
		if (SmokeFromPipe != another.SmokeFromPipe)
			return false;
		if (Window != another.Window)
			return false;
		return true;
	}
}
