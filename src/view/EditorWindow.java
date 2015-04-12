package view;

import javafx.scene.canvas.*;
import javafx.scene.canvas.Canvas;
import model.*;
import model.Map;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;

/**
 * Created by Ilya on 07.04.2015.
 */
public class EditorWindow extends JFrame {

    JSplitPane splitPane;
    public static JPanel texturePanel;
    TextureLoader loader = new TextureLoader();
    JLabel label;
    JPanel mapPanel;

    public EditorWindow()
    {
        super("Map Editor!");
        int posy = 21;
        final model.Map maper = new Map();
        mapPanel = new JPanel();
        mapPanel.setOpaque(false);
        Image x = new BufferedImage(500, 500, BufferedImage.TYPE_INT_RGB);
        java.util.List<Image> images = new ArrayList<Image>();
        images.addAll(loader.loadTextures("C:\\Users\\Ilya\\Desktop\\testDir"));
        texturePanel = new JPanel();
        texturePanel.setLayout(null);
        mapPanel.setLayout(null);
        for (int i = 0; i < images.size(); i++) {
            label = new JLabel(new ImageIcon(images.get(i)));
            label.setBounds(5, posy, 32, 32);
            label.setTransferHandler(new TransferHandler("icon"));
            final JPopupMenu popupMenu = new JPopupMenu();
            popupMenu.setName(Integer.toString(i));
            JMenuItem item = new JMenuItem("Fill");
            popupMenu.add(item);
            label.add(popupMenu);
            label.setInheritsPopupMenu(true);
            label.setComponentPopupMenu(popupMenu);
             final JLabel lab = (JLabel)popupMenu.getParent();

            item.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    maper.fillMap(mapPanel,lab);
                }
            });



            texturePanel.add(label);


            label.addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent evt) {
                    if (evt.getButton() == MouseEvent.BUTTON1)
                    {
                    JComponent comp = (JComponent) evt.getSource();
                    TransferHandler th = comp.getTransferHandler();

                    th.exportAsDrag(comp, evt, TransferHandler.COPY);
                    }
                }
            });

            posy += 35;
        }

        final MapSaver saver = new MapSaver();
        BufferedImage imageB = new BufferedImage(label.getIcon().getIconWidth(),label.getIcon().getIconHeight(),BufferedImage.TYPE_INT_RGB);
        label.getIcon().paintIcon(mapPanel, imageB.getGraphics(), 0, 0);
        texturePanel.setOpaque(false);
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Settings");
        JMenuItem save = new JMenuItem("Save");
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saver.saveMap(mapPanel);
            }
        });
        menu.add(save);
        final MapLoader loader = new MapLoader();
        JMenuItem open = new JMenuItem("Open");
        open.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loader.loadMap(mapPanel);

            }
        });
        menu.add(open);
        menuBar.add(menu);
        texturePanel.add(menuBar);
        menuBar.setBounds(0,0,1000,20);
        maper.setHeight(80);
        maper.setWidth(80);
        maper.loadEmptyMap(mapPanel);
        texturePanel.setAutoscrolls(true);
        mapPanel.setAutoscrolls(true);
        texturePanel.scrollRectToVisible(new Rectangle());
        mapPanel.scrollRectToVisible(new Rectangle());
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setRightComponent(texturePanel);
        splitPane.setLeftComponent(mapPanel);
        getContentPane().add(splitPane);
        texturePanel.setOpaque(true);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



    }


}
