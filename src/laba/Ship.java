package laba;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;
import java.lang.reflect.Field;

import javax.swing.JPanel;

public class Ship extends Boat implements Serializable, Comparable<Ship> {
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

	// ���������� ������������ � ��������������� ����������
	public Ship(String info) {
		String[] strs = info.split(";", 0);
		if (strs.length == 3) {
			MaxSpeed = Integer.parseInt(strs[0]);
			Weight = Integer.parseInt(strs[1]);
			MainColor = getColorByName(strs[2]);
		}
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
	
	//�������������� ���������� � ������
	public String toString() {
		return MaxSpeed + ";" + (int)Weight + ";" + getColorName(MainColor);
	}
	
	//��������� ����� �����
	protected String getColorName(Color c) {
		Field[] fields = Color.class.getFields();
		for (Field f : fields ) {
			try {
				if (((Color)f.get(null)).equals(c)) {
					return f.getName();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "";
	}
	
	//��������� ����� �� �����
	protected Color getColorByName(String name) {
	    try {
	        return (Color)Color.class.getField(name.toUpperCase()).get(null);
	    } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
	        e.printStackTrace();
	        return null;
	    }
	}
	
	 @Override
	    public int compareTo(Ship another) {
	        if (another == null) {
	            return 1;
	        }
	        
	        if (MaxSpeed != another.MaxSpeed) {
	            return Integer.compare(MaxSpeed,another.MaxSpeed);
	        }
	        if (Weight != another.Weight) {
	            return Float.compare(Weight,another.Weight);
	        }
	        if (MainColor != another.MainColor) {
	            return Integer.compare(MainColor.getRGB(),another.MainColor.getRGB());
	        }
	        return 0;
	    }

	    @Override
	    public boolean equals(Object another) {
	        if (another == null) return false;
	        if (!(another instanceof Ship)) return false;
	       Ship airCraftObj = (Ship) another;
	        return equals(airCraftObj);
	    }

	    public boolean equals(Ship another) {
	        if (another == null) return false;
	        if (!(another instanceof Ship)) return false;
	        if (MaxSpeed != another.MaxSpeed) return false;
	        if (Weight != another.Weight) return false;
	        if (MainColor != another.MainColor) return false;
	        return true;
	    }
}