package laba;

public class DockOccupiedPlaceException extends Exception{
	public DockOccupiedPlaceException(int i) {
		super("�� ����� " + i + " ��� �����	�������");
	}
}
