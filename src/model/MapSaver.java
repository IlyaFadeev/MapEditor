package model;

import javax.swing.*;
import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

/**
 * Created by Ilya on 12.04.2015.
 */
public class MapSaver implements Serializable {

    public void saveMap(JPanel map)
    {
        Component[] labels = map.getComponents();
        java.util.ArrayList<Block> blocks = new ArrayList<Block>();

        for (int i = 0; i < labels.length; i++) {
            blocks.add(new Block((JLabel)labels[i]));
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("Map.map");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(blocks);
            fileOutputStream.close();
            objectOutputStream.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }


    }
}
