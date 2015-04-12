package model;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ilya on 12.04.2015.
 */
public class MapLoader implements Serializable {
    ArrayList<Block> blocks = new ArrayList<Block>();

    public List<Block> loadBlocks()
    {
        try
        {
            FileInputStream fileInputStream = new FileInputStream("Map.map");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            blocks = (java.util.ArrayList<Block>)objectInputStream.readObject();
            fileInputStream.close();
            objectInputStream.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }

        return blocks;
    }

    public void loadMap(JPanel mapPanel)
    {
        List<Block> blocks = loadBlocks();

        for (int i = 0; i < blocks.size(); i++) {
            JLabel lab = (JLabel) mapPanel.getComponent(i);
            lab.setIcon(blocks.get(i).getTexture());
        }
        mapPanel.repaint();
    }
}
