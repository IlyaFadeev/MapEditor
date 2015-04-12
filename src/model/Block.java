package model;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.Serializable;

/**
 * Created by Ilya on 12.04.2015.
 */
public class Block implements Serializable {

    private Icon texture = null;
    private JLabel label;
    private int X;
    private int Y;
    public Block(JLabel label)
    {
        this.label = label;
        this.X = this.label.getX();
        this.Y = this.label.getY();
        texture = label.getIcon();
    }

    public JLabel getLabel() {
        return label;
    }

    public int getY() {
        return Y;
    }

    public Icon getTexture() {
        return texture;
    }

    public int getX() {
        return X;
    }
}
