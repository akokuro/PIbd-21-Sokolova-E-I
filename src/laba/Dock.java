package laba;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Iterator;

public class Dock<T extends IShip> implements Serializable, Comparable<Dock<T>>, Iterable<T>, Iterator<T> {
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
	private int currentIndex;

	// Конструктор дока.
	public Dock(int size, int pictureWidth, int pictureHeight) {
		_maxCount = size;
		_places = new HashMap<Integer, T>(size);
		PictureWidth = pictureWidth;
		PictureHeight = pictureHeight;
	}

	// Оператор "+": поставить корабль
	public int Add(T ship) throws DockOverFlowException, DockAlreadyHaveException {
		if (_places.size() == _maxCount) {
			throw new DockOverFlowException();
		}
		int index = _places.size();
		for (int i = 0; i < _places.size(); i++) {
			if (CheckFreePlace(i))
				index = i;
			if (_places.containsValue(ship))
				throw new DockAlreadyHaveException();
		}
		_places.put(index, ship);
		_places.get(index).SetPosition(5 + index / 5 * _placeSizeWidth + 5,
				index % 5 * _placeSizeHeight + _placeSizeHeight - 4, PictureWidth, PictureHeight);
		return index;

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
			ship.SetPosition(5 + index / 5 * _placeSizeWidth + 5, index % 5 * _placeSizeHeight + 80, PictureWidth,
					PictureHeight);
			_places.put(index, ship);
		} else {
			throw new DockOccupiedPlaceException(index);
		}
	}

	@Override
	public boolean hasNext() {
		if (currentIndex + 1 >= _places.size()) {
			currentIndex = -1;
			return false;
		}
		currentIndex++;
		return true;
	}

	@Override
	public T next() {
		return (T) _places.get(currentIndex);
	}

	@Override
	public Iterator<T> iterator() {
		 return this;
	}

	@Override
	public int compareTo(Dock<T> other) {
		 if (this._places.size() > other._places.size()) {
	            return -1;
	        } else if (this._places.size() < other._places.size()) {
	            return 1;
	        } else {
	            Integer[] thisKeys = this._places.keySet().toArray(new Integer[this._places.size()]);
	            Integer[] otherKeys = other._places.keySet().toArray(new Integer[other._places.size()]);
	            for (int i = 0; i < this._places.size(); i++) {
	                if (this._places.get(thisKeys[i]).getClass().equals( Ship.class)
	                        && other._places.get(otherKeys[i]).getClass().equals( Ship_Liner.class)) {
	                    return 1;
	                }
	                if (this._places.get(thisKeys[i]).getClass().equals( Ship_Liner.class)
	                        && other._places.get(otherKeys[i]).getClass().equals( Ship.class)) {
	                    return -1;
	                }
	                if (this._places.get(thisKeys[i]).getClass().equals( Ship.class)
	                        && other._places.get(otherKeys[i]).getClass().equals( Ship.class)) {
	                    return (( Ship) this._places.get(thisKeys[i])).compareTo(( Ship) other._places.get(otherKeys[i]));
	                }
	                if (this._places.get(thisKeys[i]).getClass().equals( Ship_Liner.class)
	                        && other._places.get(otherKeys[i]).getClass().equals( Ship_Liner.class)) {
	                    return (( Ship_Liner) this._places.get(thisKeys[i]))
							.compareTo((Ship_Liner) other._places.get(otherKeys[i]));
	                }
	            }
	        }
	        return 0;
	}

	private void reset() {
		currentIndex = -1;
	}
}
