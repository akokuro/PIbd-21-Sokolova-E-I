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

	// Конструктор дока.
	public Dock(int size, int pictureWidth, int pictureHeight) {
		_maxCount = size;
		_places = new HashMap<Integer, T>(size);
		PictureWidth = pictureWidth;
		PictureHeight = pictureHeight;
	}

	// Оператор "+": поставить корабль
	public int Add(T ship) throws DockOverFlowException {
		if (_places.size() == _maxCount) {
			throw new DockOverFlowException();
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
	public T Del(int index) throws DockNotFoundException {
		if (!CheckFreePlace(index)) {
			T ship = _places.get(index);
			_places.remove(index);
			return ship;
		}
		throw new DockNotFoundException(index);
	}

	// Проверить по номеру места, свободно ли оно
	private boolean CheckFreePlace(int index) {
		return !_places.containsKey(index);
	}

	// Отрисовка дока
	public void Draw(Graphics g) {
		DrawMarking(g);
		for (T ship : _places.values()) {
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

	public T getAt(int index) throws DockNotFoundException {

		if (_places.containsKey(index)) {
			return _places.get(index);
		}
		throw new DockNotFoundException(index);
	}

	public void setAt(int index, T ship) throws DockOccupiedPlaceException {
		if (CheckFreePlace(index)) {
			ship.SetPosition(5 + index / 5 * _placeSizeWidth + 5, index % 5 * _placeSizeHeight + 80,
					PictureWidth, PictureHeight);
			_places.put(index, ship);
		}
		else {
			throw new DockOccupiedPlaceException(index);
		}
	}
}
