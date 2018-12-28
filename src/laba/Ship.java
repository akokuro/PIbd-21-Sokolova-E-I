package laba;

import java.awt.Color;
import java.awt.Graphics;
import java.lang.reflect.Field;

import javax.swing.JPanel;

public class Ship extends Boat {
	// Ширина отрисовки коробля
	private int shipWidth = 100;
	// Высота отрисовки коробля
	private int shipHeight = 60;

	// Конструктор
	public Ship(int maxSpeed, float weight, Color mainColor) {
		MaxSpeed = maxSpeed;
		Weight = weight;
		MainColor = mainColor;
	}

	// Перегрузка конструктора с предоставлением информации
	public Ship(String info) {
		String[] strs = info.split(";", 0);
		if (strs.length == 3) {
			MaxSpeed = Integer.parseInt(strs[0]);
			Weight = Integer.parseInt(strs[1]);
			MainColor = getColorByName(strs[2]);
		}
	}

	// Изменение направления пермещения
	@Override
	public void MoveTransport(Direction direction) {
		float step = MaxSpeed * 2 / Weight;
		switch (direction) {
		// вправо
		case Right:
			if (_startPosX + step < _pictureWidth - shipWidth) {
				_startPosX += step;
			}
			break;
		// влево
		case Left:
			if (_startPosX - step > 0) {
				_startPosX -= step;
			}
			break;
		// вверх
		case Up:
			if (_startPosY - step > shipHeight) {
				_startPosY -= step;
			}
			break;
		// вниз
		case Down:
			if (_startPosY + step < _pictureHeight) {
				_startPosY += step;
			}
			break;
		}
	}

	// Отрисовка коробля
	@Override
	public void DrawShip(Graphics g) {

		// отрисуем корабль
		g.setColor(MainColor);
		g.drawLine((int) _startPosX + 10, (int) _startPosY, (int) _startPosX + 80, (int) _startPosY);
		g.drawLine((int) _startPosX + 10, (int) _startPosY, (int) _startPosX, (int) _startPosY - 25);
		g.drawLine((int) _startPosX + 80, (int) _startPosY, (int) _startPosX + 90, (int) _startPosY - 25);
		g.drawLine((int) _startPosX, (int) _startPosY - 25, (int) _startPosX + 90, (int) _startPosY - 25);
		g.setColor(Color.RED);
		g.drawLine((int) _startPosX + 5, (int) _startPosY - 13, (int) _startPosX + 85, (int) _startPosY - 13);
		g.setColor(MainColor);
		g.drawRect((int) _startPosX + 10, (int) _startPosY - 35, 70, 10);
	}
	
	//преобразование информации в строку
	public String toString() {
		return MaxSpeed + ";" + (int)Weight + ";" + getColorName(MainColor);
	}
	
	//получение имени цвета
	protected String getColorName(Color c) {
		Field[] fields = Color.class.getFields();
		for (Field f : fields ) {
			try {
				if (((Color)f.get(null)).equals(c)) {
					return f.getName();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "";
	}
	
	//получение цвета по имени
	protected Color getColorByName(String name) {
	    try {
	        return (Color)Color.class.getField(name.toUpperCase()).get(null);
	    } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
	        e.printStackTrace();
	        return null;
	    }
	}
}