package laba;

public class DockOverFlowException extends Exception{
	public DockOverFlowException() {
		super("В доке нет свободных мест");
	}

}
