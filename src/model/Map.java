package model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;

/**
 * Created by Ilya on 08.04.2015.
 */
public class Map {
    private JLabel[][] map;
    private int height;
    private int width;
    JProgressBar progressBar;

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void setHeight(int height) {

        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    private JLabel[][] createEmptyMap()
    {
        map = new JLabel[this.height][this.width];
        TextureLoader loader = new TextureLoader();
        java.util.List<Image> images = new ArrayList<Image>();
        images.addAll(loader.loadTextures("C:\\Users\\Ilya\\Desktop\\testDir"));
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                JLabel label = new JLabel(new ImageIcon(images.get(7)));
                label.setMinimumSize(new Dimension(32, 32));
                label.setOpaque(true);
                label.setForeground(Color.black);
                map[i][j] = new JLabel();
            }
        }

        return map;
    }




    public void loadEmptyMap(final JPanel mapPanel)
    {
        JLabel[][] map = createEmptyMap();
        int lx = 0;

        int ly = 0;
        for (int i = 0; i < getHeight(); i++) {
            for (int j = 0; j < getWidth(); j++) {
                map[i][j].setBounds(lx, ly, 32, 32);
                map[i][j].setTransferHandler(new TransferHandler("icon"));
                map[i][j].addMouseListener(new MouseAdapter() {
                    public void mousePressed(MouseEvent evt) {
                        if (evt.getButton() == MouseEvent.BUTTON1) {
                            JComponent comp = (JComponent) evt.getSource();
                            TransferHandler th = comp.getTransferHandler();

                            th.exportAsDrag(comp, evt, TransferHandler.COPY);
                        }
                    }
                });

                JPopupMenu popupMenu = new JPopupMenu();
                JMenuItem item = new JMenuItem("Remove");

                map[i][j].add(popupMenu);
                map[i][j].setComponentPopupMenu(popupMenu);
                final JLabel label = (JLabel) popupMenu.getParent();
                item.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        romoveBlock(mapPanel, label);
                        System.out.println("Ji");
                    }
                });
                popupMenu.add(item);
                mapPanel.add(map[i][j]);
                lx += 32;
            }
            lx = 0;
            ly += 32;
        }
    }

    public void fillMap(JPanel panel, JLabel label)
    {
        JLabel labels;
        for (int i = 0; i < panel.getComponentCount(); i++) {
            labels = (JLabel)panel.getComponent(i);
            labels.setIcon(label.getIcon());
            panel.repaint();
        }
    }

    public void romoveBlock(JPanel panel, JLabel label)
    {
        TextureLoader loader = new TextureLoader();
        Point p = label.getLocation();
        JLabel label1;
        JLabel q = new JLabel(new ImageIcon(loader.loadTextures("C:\\Users\\Ilya\\Desktop\\testDir").get(0)));
        for (int i = 0; i < panel.getComponentCount(); i++) {
            label1 = (JLabel) panel.getComponent(i);
            if (label.getX() == label1.getX() && label.getY() == label1.getY())
            {
                label1.setIcon(q.getIcon());
                break;
            }
        }

        panel.repaint();
    }

}
