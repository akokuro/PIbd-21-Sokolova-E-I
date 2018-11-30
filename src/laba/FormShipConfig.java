package laba;

import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.datatransfer.*;
import java.io.IOException;

public class FormShipConfig extends JDialog {

	private JPanel contentPane;
	public boolean r;
	public IShip ship;

	public boolean res() {
        setVisible(true);
        return r;
    }

    public Color selectColor(String s) {
        switch (s) {
            case "lavender":
                return new Color(153,51,153);
            case "black":
                return Color.BLACK;
            case "cyan":
                return Color.CYAN;
            case "green":
                return Color.GREEN;
            case "orange":
                return Color.ORANGE;
            case "red":
                return Color.RED;
            case "blue":
                return Color.BLUE;
            case "yellow":
                return Color.YELLOW;
        }

        return null;
    }

    public void draw(JPanel panel, IShip ship) {
        if (ship != null) {
            Graphics gr = panel.getGraphics();
            gr.clearRect(0, 0, panel.getWidth(), panel.getHeight());
            ship.SetPosition(30, 80, panel.getWidth(), panel.getHeight());
            ship.DrawShip(gr);
        }
    }

    public FormShipConfig(JFrame parent) {
        super(parent, true);
        
        this.getContentPane().setBackground(SystemColor.controlHighlight);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        setBounds(100, 100, 433, 292);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JButton buttonAdd = new JButton("\u0414\u043E\u0431\u0430\u0432\u0438\u0442\u044C");
        buttonAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                r = true;
                dispose();
            }
        });
        buttonAdd.setBounds(10, 145, 122, 49);
        contentPane.add(buttonAdd);

        JButton buttonCancel = new JButton("\u041E\u0442\u043C\u0435\u043D\u0430");
        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                r = false;
                dispose();
            }
        });
        buttonCancel.setBounds(10, 199, 122, 49);
        contentPane.add(buttonCancel);

        Border solidBorder = BorderFactory.createLineBorder(Color.BLACK, 1);

        JLabel AddShip = new JLabel("Ship");
        AddShip.setHorizontalAlignment(SwingConstants.CENTER);
        AddShip.setBorder(solidBorder);
        AddShip.setBounds(10, 11, 122, 56);

        contentPane.add(AddShip);

        JLabel AddLiner = new JLabel("Ship_Liner");
        AddLiner.setHorizontalAlignment(SwingConstants.CENTER);
        AddLiner.setBorder(solidBorder);
        AddLiner.setBounds(10, 78, 122, 56);
        contentPane.add(AddLiner);

        JPanel panelBorder = new JPanel();
        panelBorder.setBounds(144, 11, 157, 123);
        panelBorder.setBorder(solidBorder);
        panelBorder.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(1, 1, 155, 121);

        FlowLayout flowLayout = (FlowLayout) panel.getLayout();
        panel.setDropTarget(new DropTarget() {
            public void drop(DropTargetDropEvent e) {
                try {
                    for (DataFlavor df : e.getTransferable().getTransferDataFlavors()) {
                        String str = (String)e.getTransferable().getTransferData(df);
                        if (e.getTransferable().getTransferData(df) == "Ship") {
                            ship = new Ship(100, 100, Color.BLACK);
                        } else if (e.getTransferable().getTransferData(df) == "Ship_Liner") {
                            ship = new Ship_Liner(100, 100, Color.BLACK, Color.BLACK,
                                    true, true, true);
                        }
                        draw(panel, ship);
                    }
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }

            public void dragEnter(DropTargetDragEvent e) {
                for (DataFlavor df : e.getTransferable().getTransferDataFlavors()) {
                    try {
                        if (e.getTransferable().getTransferData(df) instanceof String)
                            e.acceptDrag(DnDConstants.ACTION_COPY);
                        else
                            e.acceptDrag(DnDConstants.ACTION_NONE);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
        panelBorder.add(panel);
        contentPane.add(panelBorder);

        JLabel SetMainColor = new JLabel("\u041E\u0441\u043D\u043E\u0432\u043D\u043E\u0439 \u0446\u0432\u0435\u0442");
        SetMainColor.setHorizontalAlignment(SwingConstants.CENTER);
        SetMainColor.setBorder(solidBorder);
        SetMainColor.setBounds(142, 145, 159, 49);
        SetMainColor.setDropTarget(new DropTarget() {
            public void drop(DropTargetDropEvent e) {
                if (ship != null) {
                    try {
                        for (DataFlavor df : e.getTransferable().getTransferDataFlavors()) {
                            ship.SetMainColor((selectColor(e.getTransferable().getTransferData(df).toString())));
                            draw(panel, ship);
                        }
                    } catch (Exception ex) {
                        System.out.println(ex + "FF");
                    }
                }
            }

            public void dragEnter(DropTargetDragEvent e) {
                for (DataFlavor df : e.getTransferable().getTransferDataFlavors()) {
                    try {
                        if (e.getTransferable().getTransferData(df) instanceof String)
                            e.acceptDrag(DnDConstants.ACTION_COPY);
                        else
                            e.acceptDrag(DnDConstants.ACTION_NONE);
                    } catch (UnsupportedFlavorException | IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
        contentPane.add(SetMainColor);

        JLabel SetDopColor = new JLabel("\u0414\u043E\u043F\u043E\u043B\u043D\u0438\u0442\u0435\u043B\u044C\u043D\u044B\u0439 \u0446\u0432\u0435\u0442");
        SetDopColor.setHorizontalAlignment(SwingConstants.CENTER);
        SetDopColor.setBorder(solidBorder);
        SetDopColor.setBounds(142, 199, 159, 49);
        SetDopColor.setDropTarget(new DropTarget() {
            public void drop(DropTargetDropEvent e) {
                if (ship != null) {
                    try {
                        for (DataFlavor df : e.getTransferable().getTransferDataFlavors()) {
                            ((Ship_Liner) ship).SetDopColor((selectColor(e.getTransferable().getTransferData(df).toString())));
                            draw(panel, ship);
                        }
                    } catch (Exception ex) {
                        System.out.println(ex);
                    }
                }
            }

            public void dragEnter(DropTargetDragEvent e) {
                for (DataFlavor df : e.getTransferable().getTransferDataFlavors()) {
                    try {
                        if (e.getTransferable().getTransferData(df) instanceof String)
                            e.acceptDrag(DnDConstants.ACTION_COPY);
                        else
                            e.acceptDrag(DnDConstants.ACTION_NONE);
                    } catch (UnsupportedFlavorException | IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
        contentPane.add(SetDopColor);

        MouseListener ml = new MouseListener() {

            @Override
            public void mousePressed(MouseEvent e) {
                JComponent jc = (JComponent) e.getSource();
                TransferHandler th = jc.getTransferHandler();
                th.exportAsDrag(jc, e, TransferHandler.COPY);
            }

            @Override
            public void mouseClicked(MouseEvent arg0) {
            }

            @Override
            public void mouseEntered(MouseEvent arg0) {
            }

            @Override
            public void mouseExited(MouseEvent arg0) {
            }

            @Override
            public void mouseReleased(MouseEvent arg0) {
            }
        };

        AddLiner.addMouseListener(ml);
        AddShip.addMouseListener(ml);
        AddLiner.setTransferHandler(new TransferHandler("text"));
        AddShip.setTransferHandler(new TransferHandler("text"));

        JPanel panelLavender = new JPanel();
        panelLavender.setBackground(new Color(153, 51, 153));
        panelLavender.setBounds(311, 11, 40, 40);
        panelLavender.setName("lavender");
        contentPane.add(panelLavender);

        JPanel panelBlack = new JPanel();
        panelBlack.setBackground(Color.BLACK);
        panelBlack.setBounds(361, 11, 40, 40);
        panelBlack.setName("black");
        contentPane.add(panelBlack);

        JPanel panelCyan = new JPanel();
        panelCyan.setBackground(Color.CYAN);
        panelCyan.setBounds(311, 78, 40, 40);
        panelCyan.setName("cyan");
        contentPane.add(panelCyan);

        JPanel panelOrange = new JPanel();
        panelOrange.setBackground(Color.ORANGE);
        panelOrange.setBounds(361, 78, 40, 40);
        panelOrange.setName("orange");
        contentPane.add(panelOrange);

        JPanel panelPink = new JPanel();
        panelPink.setBackground(Color.RED);
        panelPink.setBounds(311, 145, 40, 40);
        panelPink.setName("red");
        contentPane.add(panelPink);

        JPanel panelBlue = new JPanel();
        panelBlue.setBackground(Color.BLUE);
        panelBlue.setBounds(361, 145, 40, 40);
        panelBlue.setName("blue");
        contentPane.add(panelBlue);

        JPanel panelYellow = new JPanel();
        panelYellow.setBackground(Color.YELLOW);
        panelYellow.setBounds(311, 208, 40, 40);
        panelYellow.setName("yellow");
        contentPane.add(panelYellow);

        JPanel panelGreen = new JPanel();
        panelGreen.setBackground(Color.GREEN);
        panelGreen.setBounds(361, 208, 40, 40);
        panelGreen.setName("green");
        contentPane.add(panelGreen);

        panelLavender.addMouseListener(ml);
        panelLavender.setTransferHandler(new TransferHandler("name"));

        panelBlack.addMouseListener(ml);
        panelBlack.setTransferHandler(new TransferHandler("name"));

        panelCyan.addMouseListener(ml);
        panelCyan.setTransferHandler(new TransferHandler("name"));

        panelOrange.addMouseListener(ml);
        panelOrange.setTransferHandler(new TransferHandler("name"));

        panelPink.addMouseListener(ml);
        panelPink.setTransferHandler(new TransferHandler("name"));

        panelBlue.addMouseListener(ml);
        panelBlue.setTransferHandler(new TransferHandler("name"));

        panelYellow.addMouseListener(ml);
        panelYellow.setTransferHandler(new TransferHandler("name"));

        panelGreen.addMouseListener(ml);
        panelGreen.setTransferHandler(new TransferHandler("name"));

    }

}
