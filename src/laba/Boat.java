package laba;

import java.awt.Color;
import java.awt.Graphics;

public abstract class Boat implements IShip {
	/// ����� ���������� ��������� �������
	protected float _startPosX;
	/// ������ ���������� ��������� �������
	protected float _startPosY;
	/// ������ ���� ���������
	protected int _pictureWidth;
	// ������ ���� ���������
	protected int _pictureHeight;
	/// ������������ ��������
	public int MaxSpeed;
	/// ��� �������
	public float Weight;
	/// �������� ����
	public Color MainColor;

	public void SetPosition(int x, int y, int width, int height) {
		_startPosX = x;
		_startPosY = y;
		_pictureWidth = width;
		_pictureHeight = height;
	}

	public abstract void DrawShip(Graphics g);

	public abstract void MoveTransport(Direction direction);
	
	public void SetMainColor(Color color)
	 {
	 MainColor = color;
	 }

}
