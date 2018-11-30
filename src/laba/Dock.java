package laba;

import java.awt.Color;
import java.awt.Graphics;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;

public class Dock<T extends IShip> {
	private HashMap<Integer, T> _places;

	// Размер одного места парковки по ширине.
	private int _placeSizeWidth = 200;

	// Размер одного места парковки по высоте.
	private int _placeSizeHeight = 120;

	// Размер парковки по ширине.
	private int PictureWidth;

	// Размер парковки по высоте.
	private int PictureHeight;
	// Максимальное количество мест
	private int _maxCount;

	// Геттеры и сеттеры для PictureWudth и PictureHeight.
	public int getPictureWidth() {
		return PictureWidth;
	}

	public void setPictureWidth(int pictureWidth) {
		PictureWidth = pictureWidth;
	}

	public int getPictureHeight() {
		return PictureHeight;
	}

	public void setPictureHeight(int pictureHeight) {
		PictureHeight = pictureHeight;
	}

	// Конструктор дока.
	public Dock(int size, int pictureWidth, int pictureHeight) {
		_maxCount = size;
		_places = new HashMap<Integer, T>(size);
		PictureWidth = pictureWidth;
		PictureHeight = pictureHeight;
	}

	// Оператор "+": поставить корабль
	public int Add(T ship) {
		if (_places.size() == _maxCount) {
			return -1;
		}

		for (int i = 0; i < _maxCount; i++) {
			if (CheckFreePlace(i)) {
				_places.put(i, ship);
				_places.get(i).SetPosition(5 + i / 5 * _placeSizeWidth + 5,
						i % 5 * _placeSizeHeight + _placeSizeHeight - 4, PictureWidth, PictureHeight);

				return i;
			}
		}
		return -1;
	}

	// Оператор "-": забрать корабль
	public T Del(int index) {
		if (!CheckFreePlace(index))
        {
            T ship = _places.get(index);
            _places.remove(index);
            return ship;
        }
        return null;	}

	// Проверить по номеру места, свободно ли оно
	private boolean CheckFreePlace(int index) {
		 return !_places.containsKey(index);
	}

	// Отрисовка дока
	public void Draw(Graphics g) {
		DrawMarking(g);
		 for (T ship: _places.values()) {
	            ship.DrawShip(g);
	        }
	}

	// Отрисовка линий
	private void DrawMarking(Graphics g) {
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, _placeSizeWidth * 4, _placeSizeHeight * 5);
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 4; j++) {
				g.drawLine(j * _placeSizeWidth, i * _placeSizeHeight, j * _placeSizeWidth + _placeSizeWidth / 4 * 3,
						i * _placeSizeHeight);

				g.drawLine(j * _placeSizeWidth, i * _placeSizeHeight, j * _placeSizeWidth,
						i * _placeSizeHeight + _placeSizeHeight);
			}
		}
	}
}
