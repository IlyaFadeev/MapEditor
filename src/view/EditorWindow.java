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

/**
 * Created by Ilya on 07.04.2015.
 */
public class EditorWindow extends JFrame {

    JSplitPane splitPane;
    JPanel texturePanel;
    TextureLoader loader = new TextureLoader();
    JLabel label;
    JPanel mapPanel;

    public EditorWindow()
    {
        super("Map Editor!");
        int posy = 0;
        final model.Map maper = new Map();
        mapPanel = new JPanel();
        mapPanel.setOpaque(true);
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

        maper.setHeight(25);
        maper.setWidth(25);
        maper.loadEmptyMap(mapPanel);
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setRightComponent(texturePanel);
        splitPane.setLeftComponent(mapPanel);
        getContentPane().add(splitPane);
        texturePanel.setOpaque(true);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



    }


}
