package laba;

import java.awt.Color;
import java.awt.Graphics;

public abstract class Boat implements IShip {
	/// Левая координата отрисовки корабля
	protected float _startPosX;
	/// Правая кооридната отрисовки корабля
	protected float _startPosY;
	/// Ширина окна отрисовки
	protected int _pictureWidth;
	// Высота окна отрисовки
	protected int _pictureHeight;
	/// Максимальная скорость
	public int MaxSpeed;
	/// Вес корабля
	public float Weight;
	/// Основной цвет
	public Color MainColor;

	public void SetPosition(int x, int y, int width, int height) {
		_startPosX = x;
		_startPosY = y;
		_pictureWidth = width;
		_pictureHeight = height;
	}

	public abstract void DrawShip(Graphics g);

	public abstract void MoveTransport(Direction direction);
}
