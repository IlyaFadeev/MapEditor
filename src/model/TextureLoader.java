package model;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ilya on 07.04.2015.
 */
public class TextureLoader {
    private List<Image> textures = new ArrayList<>();

    public List<Image> loadTextures(String path)
    {
        File directory = new File(path);
        File[] files = directory.listFiles();
        Image currImage;
        for (File file : files) {
            textures.add(Toolkit.getDefaultToolkit().getImage(file.getPath()));
        }

        return textures;
    }

}
