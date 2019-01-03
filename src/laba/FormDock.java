package laba;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import java.awt.SystemColor;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTree;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JScrollPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileFilter;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class FormDock {

	private JFrame frame;
	private final DrawingHelper panel_Dock = new DrawingHelper();
	private JTextField textField_1;
	private JTextField textField_2;
	// объект от класса многоуровнего дока
	public MultiLevelDock dock;
	// объект от интерфейса
	public IShip ship;
	// количество уровней
	private final int countLevel = 5;
	private FormShipConfig select;
	private static JList list;
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem menuItem_Save;
	private JMenuItem menuItem_Load;
	private static Logger logger = Logger.getLogger(FormDock.class.getName());
	private static int place;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormDock window = new FormDock();
					window.frame.setVisible(true);
					FileHandler fh;
					fh = new FileHandler("D://loggerJava.txt");
					logger.addHandler(fh);
					SimpleFormatter formatter = new SimpleFormatter();
					fh.setFormatter(formatter);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FormDock() {
		initialize();
	}

	public void getShip() throws DockOverFlowException, DockAlreadyHaveException {
		select = new FormShipConfig(frame);
		if (select.res()) {
			IShip ship = select.ship;
			int temp = list.getSelectedIndex();
			if (temp == -1) {
				temp = 0;
			}
			place = dock.getAt(temp).Add(ship);
			logger.info("Добавлен корабль " + ship.toString() + " на место " + place);
		}
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 995, 649);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		panel_Dock.setBackground(Color.WHITE);
		panel_Dock.setBounds(0, 0, 802, 599);
		frame.getContentPane().add(panel_Dock);
		dock = new MultiLevelDock(countLevel, panel_Dock.getWidth(), panel_Dock.getHeight());
		panel_Dock.Docker = dock.getAt(0);

		list = new JList();
		list.setBounds(834, 11, 135, 93);
		frame.getContentPane().add(list);
		DefaultListModel listModel = new DefaultListModel();
		list.setModel(listModel);
		for (int i = 0; i < countLevel; i++) {
			listModel.addElement("\u0423\u0440\u043E\u0432\u0435\u043D\u044C " + Integer.toString(i + 1));
		}
		ListSelectionListener listSelectionListener = new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				panel_Dock.Docker = dock.getAt(list.getSelectedIndex());
				panel_Dock.repaint();
			}
		};
		list.addListSelectionListener(listSelectionListener);

		JButton button_order = new JButton(
				"\u0417\u0430\u043A\u0430\u0437\u0430\u0442\u044C \u043A\u043E\u0440\u0430\u0431\u043B\u044C");
		button_order.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					getShip();
					panel_Dock.repaint();
				} catch (DockOverFlowException e) {
					JOptionPane.showMessageDialog(null, "Переполнение", "Результат", JOptionPane.INFORMATION_MESSAGE);
				} catch (DockAlreadyHaveException e) {
					JOptionPane.showMessageDialog(null, "Такой корабль уже есть в доке", "Результат", JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Неизвестная ошибка", "Результат",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		button_order.setBounds(834, 126, 135, 37);
		frame.getContentPane().add(button_order);

		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setBackground(SystemColor.menu);
		textField_1.setText("\u0417\u0430\u0431\u0440\u0430\u0442\u044C \u043A\u043E\u0440\u0430\u0431\u043B\u044C");
		textField_1.setBounds(865, 230, 104, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);

		textField_2 = new JTextField();
		textField_2.setBackground(SystemColor.menu);
		textField_2.setText("\u041C\u0435\u0441\u0442\u043E");
		textField_2.setBounds(851, 261, 51, 20);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);

		JTextArea textIndex = new JTextArea();
		textIndex.setBounds(912, 261, 57, 20);
		frame.getContentPane().add(textIndex);

		JPanel panel_takeShip = new DrawingTake();
		panel_takeShip.setBackground(Color.WHITE);
		panel_takeShip.setBounds(844, 363, 135, 110);
		frame.getContentPane().add(panel_takeShip);

		JButton button_Take = new JButton("\u0417\u0430\u0431\u0440\u0430\u0442\u044C");
		button_Take.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textIndex.getText() != "") {
					try {
						IShip ship = null;
						ship = dock.getAt(list.getSelectedIndex()).Del(Integer.parseInt(textIndex.getText()));
						ship.SetPosition(panel_takeShip.getWidth() - 110, panel_takeShip.getHeight() - 50,
								panel_takeShip.getWidth(), panel_takeShip.getHeight());
						((DrawingTake) panel_takeShip).addShip(ship);
						logger.info("Изъят корабль " + ship.toString() + " с места "
								+ Integer.parseInt(textIndex.getText()));
						panel_Dock.repaint();
						panel_takeShip.repaint();
					} catch (DockNotFoundException ex) {
						JOptionPane.showMessageDialog(null, "Не найдено", "Результат", JOptionPane.INFORMATION_MESSAGE);
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "Неизвестная ошибка", "Результат",
								JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});
		button_Take.setBounds(865, 311, 89, 23);
		frame.getContentPane().add(button_Take);

		JButton buttonSort = new JButton("\u0421\u043E\u0440\u0442\u0438\u0440\u043E\u0432\u0430\u0442\u044C");
		buttonSort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dock.Sort();
				panel_Dock.repaint();
				panel_Dock.Docker = dock.getAt(0);
				logger.info("Сортировка уровней");
			}
		});
		buttonSort.setBounds(834, 174, 135, 37);
		frame.getContentPane().add(buttonSort);

		menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		menu = new JMenu("\u0424\u0430\u0439\u043B");
		menuBar.add(menu);

		menuItem_Save = new JMenuItem("\u0421\u043E\u0445\u0440\u0430\u043D\u0438\u0442\u044C");
		menuItem_Save.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				JFileChooser fc = new JFileChooser();
				fc.setFileFilter(new MyFilter());
				int returnVal = fc.showOpenDialog(frame); // Where frame is the parent component

				File file = null;
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					file = fc.getSelectedFile();
					try {
						dock.SaveData(file.getPath());
						JOptionPane.showMessageDialog(null, "Сохранение прошло успешно", "Результат",
								JOptionPane.INFORMATION_MESSAGE);
						logger.info("Сохранено в файл " + file.getName());
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, "Неизвестная ошибка при сохранении", "Результат",
								JOptionPane.INFORMATION_MESSAGE);
					}

				}
			}
		});
		menu.add(menuItem_Save);

		menuItem_Load = new JMenuItem("\u0417\u0430\u0433\u0440\u0443\u0437\u0438\u0442\u044C");
		menuItem_Load.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				JFileChooser fc = new JFileChooser();
				fc.setFileFilter(new MyFilter());
				int returnVal = fc.showOpenDialog(frame); // Where frame is the parent component

				File file = null;
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					file = fc.getSelectedFile();
					try {
						dock.LoadData(file.getPath());
						JOptionPane.showMessageDialog(null, "Загрузили", "Результат", JOptionPane.INFORMATION_MESSAGE);
						logger.info("Загружено из файла " + file.getName());
					} catch (DockOccupiedPlaceException ex) {
						JOptionPane.showMessageDialog(null, "Занятое место", "Результат",
								JOptionPane.INFORMATION_MESSAGE);
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "Не загрузили", "Результат",
								JOptionPane.INFORMATION_MESSAGE);
					}
				}
				panel_Dock.repaint();
			}
		});
		menu.add(menuItem_Load);

	}
}

class MyFilter extends javax.swing.filechooser.FileFilter {

	@Override
	public boolean accept(File arg0) {
		if (arg0.getName().lastIndexOf('.') == -1)
			return true;
		return arg0.getName().substring(arg0.getName().lastIndexOf('.')).equals(".txt");
	}

	@Override
	public String getDescription() {
		return null;
	}

}
