package ThanosGame.terrain.buildings;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class BuildingSaves {
    private static final int MODULEDIMENSION = 20;
    public static byte[][][] moduleTemplates = new byte[10][MODULEDIMENSION][MODULEDIMENSION];

    public static void loadBuildings(){
        for(int i =0;i<moduleTemplates.length;i++){
            moduleTemplates[i]=readModule("module" + i + ".png");
        }
        readModule("module9.png");
    }

    private static byte[][] readModule(String str) {
        byte res[][] = new byte[MODULEDIMENSION][MODULEDIMENSION];
        BufferedImage img;
        try {
            img = ImageIO.read(BuildingSaves.class.getResource(str));
        } catch (IOException e) {
            e.printStackTrace();
            img = new BufferedImage(1, 1, BufferedImage.TYPE_4BYTE_ABGR);
        }
        for (int x = 0; x < MODULEDIMENSION; x++) {
            for (int y = 0; y < MODULEDIMENSION; y++) {
                int myRGB = img.getRGB(x, y);
                System.out.println(myRGB + " " + new Color(myRGB).toString());
                switch (myRGB) {
                    case -16777216:
                        res[x][y] = 10;
                        break;
                    case -6737152:
                        res[x][y] = -1;
                        break;
                    default:
                        res[x][y] = 0;
                        break;
                }
            }
        }

        return res;
    }

}



