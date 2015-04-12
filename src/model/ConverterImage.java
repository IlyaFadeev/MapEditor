package model;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.Serializable;

/**
 * Created by Ilya on 12.04.2015.
 */
public class ConverterImage implements Serializable{
    private BufferedImage tex;
    public BufferedImage IconToBufferedImage(Icon label)
    {
        tex = new BufferedImage(label.getIconWidth(), label.getIconHeight(), BufferedImage.TYPE_INT_RGB);
        label.paintIcon(null, tex.getGraphics(), 0, 0);
        return tex;
    }
}
