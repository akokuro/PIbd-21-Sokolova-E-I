package laba_1;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Ship {
	// ����� ���������� ��������� �������
	private float _startPosX;
	// ������ ���������� ��������� �������
	private float _startPosY;
	// ������ ���� ���������
	private int _pictureWidth;
	// ������ ���� ���������
	private int _pictureHeight;
	// ������ ��������� �������
	private int shipWidth = 100;
	// ������ ��������� �������
	private int shipHeight = 60;
	// ������������ ��������
	public int MaxSpeed;
	// ��� �������
	public float Weight;
	// �������� ����
	public Color MainColor;
	// �������������� ����
	public Color DopColor;
	// ������� ������� �����
	public boolean Pipe;
	// ������� ������� ������� ���������
	public boolean SmokeFromPipe;
	// ������� ������� ����
	public boolean Window;

	// �����������
	public Ship(int maxSpeed, float weight, Color mainColor, Color dopColor, boolean pipe, boolean smokeFromPipe,
			boolean window) {
		MaxSpeed = maxSpeed;
		Weight = weight;
		MainColor = mainColor;
		DopColor = dopColor;
		Pipe = pipe;
		SmokeFromPipe = smokeFromPipe;
		Window = window;
	}

	// ��������� ������� �������
	public void SetPosition(int x, int y, int width, int height) {
		_startPosX = x;
		_startPosY = y;
		_pictureWidth = width;
		_pictureHeight = height;
	}

	// ��������� ����������� ����������
	public void MoveTransport(Direction direction) {
		float step = MaxSpeed / Weight;
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
	public void DrawShip(Graphics g) {
		// �������� ������ �����
		if (Pipe) {
			g.setColor(Color.BLUE);
			g.fillRect((int) _startPosX + 60, (int) _startPosY - 50, 10, 15);
		}
		// � ���
		if (SmokeFromPipe) {
			g.setColor(DopColor);
			g.fillOval((int) _startPosX + 57, (int) _startPosY - 47, 2, 2);
			g.fillOval((int) _startPosX + 52, (int) _startPosY - 44, 3, 3);
			g.fillOval((int) _startPosX + 43, (int) _startPosY - 42, 5, 5);
		}
		// ������ �������� �������� ����� �������
		g.setColor(Color.BLACK);
		g.drawLine((int) _startPosX + 10, (int) _startPosY, (int) _startPosX + 80, (int) _startPosY);
		g.drawLine((int) _startPosX + 10, (int) _startPosY, (int) _startPosX, (int) _startPosY - 25);
		g.drawLine((int) _startPosX + 80, (int) _startPosY, (int) _startPosX + 90, (int) _startPosY - 25);
		g.drawLine((int) _startPosX, (int) _startPosY - 25, (int) _startPosX + 90, (int) _startPosY - 25);
		g.setColor(Color.RED);
		g.drawLine((int) _startPosX + 5, (int) _startPosY - 13, (int) _startPosX + 85, (int) _startPosY - 13);
		g.setColor(Color.BLACK);
		g.drawRect((int) _startPosX + 10, (int) _startPosY - 35, 70, 10);

		// ������ ������ ������� ����������
		if (Window) {
			g.setColor(Color.BLUE);
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