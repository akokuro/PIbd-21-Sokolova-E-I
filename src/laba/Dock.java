package laba;

import java.awt.Color;
import java.awt.Graphics;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Dock<T extends IShip> {
	private T[] _places;

	// Размер одного места парковки по ширине.
	private int _placeSizeWidth = 200;

	// Размер одного места парковки по высоте.
	private int _placeSizeHeight = 120;

	// Размер парковки по ширине.
	private int PictureWidth;

	// Размер парковки по высоте.
	private int PictureHeight;

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
	public Dock(T[] places, int pictureWidth, int pictureHeight) {
		_places = places;
		PictureWidth = pictureWidth;
		PictureHeight = pictureHeight;
		for (int i = 0; i < _places.length; i++) {
			_places[i] = null;
		}
	}

	// Оператор "+": поставить корабль
	public int Add(T ship) {
		for (int i = 0; i < _places.length; i++) {
			if (CheckFreePlace(i)) {
				_places[i] = ship;
				_places[i].SetPosition(5 + i / 5 * _placeSizeWidth + 5, i % 5 * _placeSizeHeight + _placeSizeHeight - 4, PictureWidth,
						PictureHeight);

				return i;
			}
		}

		return -1;
	}

	// Оператор "-": забрать корабль
	public T Del(int index) {
		if (index < 0 || index >= _places.length) {
			return null;
		}

		if (!CheckFreePlace(index)) {
			T ship = _places[index];
			_places[index] = null;
			return ship;
		}

		return null;
	}

	// Проверить по номеру места, свободно ли оно
	private boolean CheckFreePlace(int index) {
		return _places[index] == null;
	}

	// Отрисовка дока
	public void Draw(Graphics g) {
		DrawMarking(g);
		for (int i = 0; i < _places.length; i++) {
			if (!CheckFreePlace(i)) {
				_places[i].DrawShip(g);
			}
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
