package laba;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JColorChooser;
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

public class FormDock {

	private JFrame frame;
	private final DrawingHelper panel_Dock = new DrawingHelper();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	// ������ �� ������ ������������� ����
	public MultiLevelDock dock;
	// ������ �� ����������
	public IShip ship;
	// ���������� �������
	private final int countLevel = 5;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormDock window = new FormDock();
					window.frame.setVisible(true);
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

	/**
	 * Initialize the contents of the frame.
	 */
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

		JList list = new JList();
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
        
		JButton button_set_Ship = new JButton("\u041A\u043E\u0440\u0430\u0431\u043B\u044C");
		button_set_Ship.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Color MainColor = JColorChooser.showDialog(null, "�������� ����", Color.WHITE);
				ship = new Ship(50 + (int) (Math.random() * 300), 1000 + (int) (Math.random() * 2000), MainColor);
				 int place = dock.getAt(list.getSelectedIndex()).Add(ship);
	                if (place == -1) {
	                    JOptionPane.showMessageDialog(null, "��� ��������� ����");
	                }
				panel_Dock.repaint();
			}
		});
		button_set_Ship.setBounds(834, 134, 135, 37);
		frame.getContentPane().add(button_set_Ship);

		textField = new JTextField();
		textField.setEditable(false);
		textField.setBackground(SystemColor.menu);
		textField.setText("\u041F\u0440\u0438\u0448\u0432\u0430\u0440\u0442\u043E\u0432\u0430\u0442\u044C:");
		textField.setBounds(850, 115, 104, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		JButton button_set_Liner = new JButton("\u041B\u0430\u0439\u043D\u0435\u0440");
		button_set_Liner.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Color MainColor = JColorChooser.showDialog(null, "�������� �������� ����", Color.WHITE);
				Color DopColor = JColorChooser.showDialog(null, "�������� �������������� ����", Color.WHITE);
				ship = new Ship_Liner(50 + (int) (Math.random() * 300), 1000 + (int) (Math.random() * 2000), MainColor,
						DopColor, true, true, true);
				int place = dock.getAt(list.getSelectedIndex()).Add(ship);
                if (place == -1) {
                    JOptionPane.showMessageDialog(null, "��� ��������� ����");
                }
				panel_Dock.repaint();
			}
		});
		button_set_Liner.setBounds(834, 182, 135, 37);
		frame.getContentPane().add(button_set_Liner);

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
					IShip ship =  dock.getAt(list.getSelectedIndex()).Del(Integer.parseInt(textIndex.getText()));
					if (ship != null) {
						ship.SetPosition(panel_takeShip.getWidth() - 110, panel_takeShip.getHeight() - 50,
								panel_takeShip.getWidth(), panel_takeShip.getHeight());
						((DrawingTake) panel_takeShip).addShip(ship);
					} else {
						((DrawingTake) panel_takeShip).addShip(null);
					}
				}
				panel_Dock.repaint();
				panel_takeShip.repaint();
			}
		});
		button_Take.setBounds(865, 311, 89, 23);
		frame.getContentPane().add(button_Take);

	}
}
