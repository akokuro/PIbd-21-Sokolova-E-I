package laba;

public class DockNotFoundException extends Exception {
	public DockNotFoundException(int i){
		super("�� ������ ������� �� ����� " + i);
	}
}
