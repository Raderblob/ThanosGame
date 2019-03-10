package ThanosGame.terrain;

import ThanosGame.Main;
import ThanosGame.terrain.buildings.BuildingSaves;
import javafx.geometry.Point2D;

import java.util.LinkedList;

public class Building {
    BuildingModule myModules[];
    private int baseModel;
    private Point2D myPosition;

    public Building(Point2D mPos) {
        myPosition = mPos;
        baseModel = 0;
        myModules = new BuildingModule[1];
        myModules[0] = new BuildingModule(baseModel,mPos);

    }

    public void changeTerrain(TerrainMap theTerrain){
        LinkedList<Point2D> PToChange = new LinkedList<>();
        LinkedList<Byte> fVals = new LinkedList<Byte>();

        for(int i = 0;i<myModules.length;i++){
            for(int x = 0;x<myModules[i].blocks.length;x++){
                for(int y =0;y<myModules[i].blocks[x].length;y++){
                    PToChange.add(new Point2D(x*4,y*4).add(myModules[i].myPosition));
                    fVals.add(myModules[i].blocks[x][y]);
                }
            }
        }
        byte fValsFinal[] = new byte[fVals.size()];
        for(int i = 0;i<fValsFinal.length;i++){
            fValsFinal[i] = fVals.get(i).byteValue();
        }
        theTerrain.changeTerrain(PToChange.toArray(new Point2D[PToChange.size()]),fValsFinal,false);
    }
}


class BuildingModule {
    public byte blocks[][];
    private int moduleModel;
    public Point2D myPosition;
    public BuildingModule(int bModel,Point2D mPos) {
        myPosition = mPos;
        moduleModel = 0;
        blocks = getModule(bModel);

    }




    private byte[][] getModule(int bModel) {
        byte res[][];
        switch (bModel) {
            case 0:
                switch (Main.numberGenerator.nextInt(1)) {
                    case 0:
                        moduleModel = 0;
                        break;
                    default:
                        System.out.println("Error module not known");
                }
                break;
            default:
                System.out.println("Error building not known");
        }
        res = readModule( moduleModel );
        return res;
    }

    private byte[][] readModule(int i) {
        byte res[][] = new byte[BuildingSaves.moduleTemplates[0].length][BuildingSaves.moduleTemplates[0][0].length];
        for(int x =0;x<res.length;x++){
            for(int y = 0;y<res.length;y++){
                res[x][y]=BuildingSaves.moduleTemplates[i][x][y];
            }
        }

        return res;
    }
}

