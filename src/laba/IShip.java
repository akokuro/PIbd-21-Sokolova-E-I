package laba;

import java.awt.Color;
import java.awt.Graphics;

public interface IShip {

	// ��������� ������� �������
	void SetPosition(int x, int y, int width, int height);

	// ��������� ����������� ����������
	void MoveTransport(Direction direction);

	// ��������� �������
	void DrawShip(Graphics g);
	
	//������������ ��������� �����
	void SetMainColor(Color color);
}
