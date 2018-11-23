package laba;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Ship extends Boat {
	// ������ ��������� �������
	private int shipWidth = 100;
	// ������ ��������� �������
	private int shipHeight = 60;

	// �����������
	public Ship(int maxSpeed, float weight, Color mainColor) {
		MaxSpeed = maxSpeed;
		Weight = weight;
		MainColor = mainColor;
	}

	// ��������� ����������� ����������
	@Override
	public void MoveTransport(Direction direction) {
		float step = MaxSpeed * 2 / Weight;
		switch (direction) {
		// ������
		case Right:
			if (_startPosX + step < _pictureWidth - shipWidth) {
				_startPosX += step;
			}
			break;
		// �����
		case Left:
			if (_startPosX - step > 0) {
				_startPosX -= step;
			}
			break;
		// �����
		case Up:
			if (_startPosY - step > shipHeight) {
				_startPosY -= step;
			}
			break;
		// ����
		case Down:
			if (_startPosY + step < _pictureHeight) {
				_startPosY += step;
			}
			break;
		}
	}

	// ��������� �������
	@Override
	public void DrawShip(Graphics g) {

		// �������� �������
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
}