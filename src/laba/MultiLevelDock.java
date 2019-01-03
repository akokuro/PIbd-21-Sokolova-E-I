package laba;

import java.beans.Encoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.swing.Spring;

import org.omg.CORBA.Environment;

public class MultiLevelDock {
	ArrayList<Dock<IShip>> dockStages;

	private final int countPlaces = 20;
	private int pictureWidth;
	private int pictureHeight;

	public MultiLevelDock(int countStages, int pictureWidth, int pictureHeight) {
		dockStages = new ArrayList<Dock<IShip>>();
		this.pictureWidth = pictureWidth;
		this.pictureHeight = pictureHeight;
		for (int i = 0; i < countStages; ++i) {
			dockStages.add(new Dock<IShip>(countPlaces, pictureWidth, pictureHeight));
		}
	}

	public Dock<IShip> getAt(int index) {
		if (index > -1 && index < dockStages.size()) {
			return dockStages.get(index);
		}

		return null;
	}

	private void WriteToFile(String text, FileOutputStream stream) {
		try {
			byte[] info = text.getBytes();
			stream.write(info, 0, info.length);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void SaveData(String filename) throws DockNotFoundException, FileNotFoundException {
		File file = new File(filename);
		if (file.exists()) {
			file.delete();
		}
		FileOutputStream fs;
		fs = new FileOutputStream(filename);
		// «аписываем количество уровней
		WriteToFile("CountLeveles:" + dockStages.size() + "\r\n", fs);
		for (Dock<IShip> level : dockStages) {
			// Ќачинаем уровень
			WriteToFile("Level" + "\r\n", fs);
			for (IShip ship : level) {
				if (ship != null) {
					// если место не пустое
					// «аписываем тип корабл€
					if (ship instanceof Ship_Liner) {
						WriteToFile(":Ship_Liner:", fs);
					} else if (ship instanceof Ship) {
						WriteToFile(":Ship:", fs);
					}

					// «аписываемые параметры
					System.out.println(ship.toString() + "\r\n");
					WriteToFile(ship.toString() + "\r\n", fs);

				}
			}
		}
	}

	public void LoadData(String filename) throws Exception {
		File file = new File(filename);
		if (!file.exists()) {
			throw new FileNotFoundException();
		}
		String bufferTextFromFile = "";
		FileInputStream fn;

		fn = new FileInputStream(filename);

		int i = -1;
		int size = 0;
		while ((i = fn.read()) != -1) {
			size++;
		}
		char[] b = new char[size];
		fn = new FileInputStream(filename);
		int j = 0;
		while ((i = fn.read()) != -1) {
			b[j] = (char) i;
			j++;
		}

		bufferTextFromFile = new String(b);

		bufferTextFromFile = bufferTextFromFile.replace("\r", "");
		String[] strs = bufferTextFromFile.split("\n", 0);
		if (strs[0].contains("CountLeveles")) {
			// считываем количество уровней
			int count = Integer.parseInt(strs[0].split(":", 0)[1]);
			if (dockStages != null) {
				dockStages.clear();
			}
			dockStages = new ArrayList<Dock<IShip>>(count);
		} else {
			// если нет такой записи, то это не те данные
			throw new Exception("Ќеверный формат файла");
		}
		int counter = -1;
		IShip ship = null;
		for (int k = 1; k < strs.length; ++k) {
			// идем по считанным запис€м
			if (strs[k].equals("Level")) {
				// начинаем новый уровень
				counter++;
				dockStages.add(new Dock<IShip>(countPlaces, pictureWidth, pictureHeight));
				continue;
			}
			if (strs[k] == null || strs[k].isEmpty()) {
				continue;
			}
			if (strs[k].contains("Ship_Liner")) {
				ship = new Ship_Liner(strs[k].split(":", 0)[2]);
			} else if (strs[k].contains("Ship")) {
				ship = new Ship(strs[k].split(":", 0)[2]);
			}
			dockStages.get(counter).setAt(Integer.parseInt(strs[k].split(":", 0)[0]), ship);
		}
	}

	// —ортировка уровней
	public void Sort() {
		dockStages.sort(null);
	}
}
