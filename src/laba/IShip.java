package laba;

import java.awt.Color;
import java.awt.Graphics;

public interface IShip {

	// Установка позиции корабля
	void SetPosition(int x, int y, int width, int height);

	// Изменение направления пермещения
	void MoveTransport(Direction direction);

	// Отрисовка корабля
	void DrawShip(Graphics g);
	
	//Установление основного цвета
	void SetMainColor(Color color);

}
