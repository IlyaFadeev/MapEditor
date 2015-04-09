package controller;

import view.*;

import java.awt.*;

/**
 * Created by Ilya on 07.04.2015.
 */
public class Main {
    public static void main(String[] args) {
        EditorWindow frame = new EditorWindow();
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setSize(new Dimension(270, 245));
    }
}
