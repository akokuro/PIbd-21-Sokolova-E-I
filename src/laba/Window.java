package laba;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.FlowLayout;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class Window {

	private JFrame frame;
	public IShip ship;
	public DrawingHelper picturePanelShip;
	/**
	 * Create the application.
	 * 
	 * @throws IOException
	 */
	public Window() throws IOException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @throws IOException
	 */
	private void initialize() throws IOException {
		frame = new JFrame();
		frame.setBounds(100, 100, 486, 311);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		picturePanelShip = new DrawingHelper();
		picturePanelShip.setBackground(Color.WHITE);
		picturePanelShip.setBounds(0, 0, 333, 261);
		frame.getContentPane().add(picturePanelShip);
		JButton buttonCreateShip = new JButton(
				"\u0421\u043E\u0437\u0434\u0430\u0442\u044C \u043A\u043E\u0440\u0430\u0431\u043B\u044C");
		buttonCreateShip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Random rnd = new Random();
				ship = new Ship(rnd.nextInt(2) * 200 + 100, rnd.nextInt(2) * 90 + 10, Color.BLUE);
				ship.SetPosition(rnd.nextInt(2) * 10 + 10, rnd.nextInt(2) * 30 + 70, picturePanelShip.getWidth(),
						picturePanelShip.getHeight());
				picturePanelShip.addShip(ship);
				frame.repaint();
			}
		});
		buttonCreateShip.setFont(new Font("Verdana", Font.PLAIN, 9));
		buttonCreateShip.setBounds(333, 0, 137, 30);
		frame.getContentPane().add(buttonCreateShip);

		BufferedImage buttonIconUp = ImageIO
				.read(new File("D:\\All Kurai\\Универ\\2 курс\\Технологии программирования\\стрелка вверх.jpg"));
		JButton buttonUp = new JButton(new ImageIcon(buttonIconUp));
		buttonUp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				ship.MoveTransport(Direction.Up);
				frame.repaint();
			}
		});
		buttonUp.setBounds(388, 157, 30, 30);
		frame.getContentPane().add(buttonUp);

		BufferedImage buttonIconLeft = ImageIO
				.read(new File("D:\\All Kurai\\Универ\\2 курс\\Технологии программирования\\стрелка влево.jpg"));
		JButton buttonLeft = new JButton(new ImageIcon(buttonIconLeft));
		buttonLeft.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ship.MoveTransport(Direction.Left);
				frame.repaint();
			}
		});
		buttonLeft.setBounds(359, 186, 30, 30);
		frame.getContentPane().add(buttonLeft);

		BufferedImage buttonIconRight = ImageIO
				.read(new File("D:\\All Kurai\\Универ\\2 курс\\Технологии программирования\\стрелка вправо.jpg"));
		JButton buttonRight = new JButton(new ImageIcon(buttonIconRight));
		buttonRight.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ship.MoveTransport(Direction.Right);
				frame.repaint();
			}
		});
		buttonRight.setBounds(418, 186, 30, 30);
		frame.getContentPane().add(buttonRight);

		BufferedImage buttonIconDown = ImageIO
				.read(new File("D:\\All Kurai\\Универ\\2 курс\\Технологии программирования\\стрелка вниз.jpg"));
		JButton buttonDown = new JButton(new ImageIcon(buttonIconDown));
		buttonDown.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ship.MoveTransport(Direction.Down);
				frame.repaint();
			}
		});
		buttonDown.setBounds(388, 215, 30, 30);
		frame.getContentPane().add(buttonDown);
		
		JButton buttonCreateLiner = new JButton("\u0421\u043E\u0437\u0434\u0430\u0442\u044C \u043B\u0430\u0439\u043D\u0435\u0440");
		buttonCreateLiner.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Random rnd = new Random();
				ship = new Ship_Liner(rnd.nextInt(2) * 200 + 100, rnd.nextInt(2) * 90 + 10, Color.BLUE, Color.GRAY, true, true, true);
				ship.SetPosition(rnd.nextInt(2) * 10 + 10, rnd.nextInt(2) * 30 + 70, picturePanelShip.getWidth(),
						picturePanelShip.getHeight());
				picturePanelShip.addShip(ship);
				frame.repaint();
			}
		});
		buttonCreateLiner.setFont(new Font("Verdana", Font.PLAIN, 9));
		buttonCreateLiner.setBounds(333, 30, 137, 30);
		frame.getContentPane().add(buttonCreateLiner);

	}
}
