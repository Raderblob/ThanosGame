package ThanosGame.terrain;

import ThanosGame.Main;
import ThanosGame.terrain.buildings.BuildingSaves;
import javafx.geometry.Point2D;

import java.util.LinkedList;

public class Building {
    BuildingModule myModules[];
    private Point2D myPosition;

    public Building(Point2D mPos) {
        myPosition = mPos;
        Point2D cPos = new Point2D(myPosition.getX(), Math.max(myPosition.getY() - 80,1));

      /*  for (int i = 0; i < myModules.length; i++) {
            myModules[i] = new BuildingModule(cPos);
            cPos = cPos.add(80, 0);
        }*/
        int potentialBuildingHeight = (int) ((TerrainChunck.chunkParam.getY() * 4) / 80) ;
        int doorPlacement = (int) (cPos.getY() / 80);
        System.out.println(cPos.getY() + " " + doorPlacement);
        BuildingModule[][] buildingPlan = new BuildingModule[10][potentialBuildingHeight];
        LinkedList<BuildingModule> plannedBuilding = new LinkedList<>();
        boolean buildingComplete;
        buildingPlan[1][doorPlacement] = new EntranceModule(new Point2D(cPos.getX(), cPos.getY()));
        plannedBuilding.add(buildingPlan[1][doorPlacement]);
        do {
            buildingComplete = true;
            for (int x = 1; x < buildingPlan.length - 1; x++) {
                for (int y = 1; y < buildingPlan[x].length - 1; y++) {
                    if (buildingPlan[x][y] == null) {
                        if (buildingPlan[x - 1][y] != null && buildingPlan[x - 1][y].canRight) {
                            buildingPlan[x][y] = getCanLeft(buildingPlan[x - 1][y].myPosition.add(80, 0));
                            plannedBuilding.add(buildingPlan[x][y]);
                            buildingComplete = false;
                        }
                        if (buildingPlan[x + 1][y] != null && buildingPlan[x + 1][y].canLeft) {
                            buildingPlan[x][y] = getCanRight(buildingPlan[x + 1][y].myPosition.add(-80, 0));
                            plannedBuilding.add(buildingPlan[x][y]);
                            buildingComplete = false;
                        }
                        if (buildingPlan[x][y + 1] != null && buildingPlan[x][y + 1].canUp) {
                            buildingPlan[x][y] = getCanDown(buildingPlan[x][y + 1].myPosition.add(0, -80));
                            plannedBuilding.add(buildingPlan[x][y]);
                            buildingComplete = false;
                        }
                        if (buildingPlan[x][y - 1] != null && buildingPlan[x][y - 1].canDown) {
                            buildingPlan[x][y] = getCanUp(buildingPlan[x][y - 1].myPosition.add(0, 80));
                            plannedBuilding.add(buildingPlan[x][y]);
                            buildingComplete = false;
                        }
                    }
                }
            }
        } while (!buildingComplete);
        for (int x = 0; x < buildingPlan.length; x++) {
            for (int y = 0; y < buildingPlan[x].length; y++) {
                if (buildingPlan[x][y] == null) {
                    if (x > 0) {
                        if (buildingPlan[x - 1][y] != null && buildingPlan[x - 1][y].shouldCover) {
                            buildingPlan[x][y] = new WallLeftModule(buildingPlan[x - 1][y].myPosition.add(80, 0));
                            plannedBuilding.add(buildingPlan[x][y]);
                        }
                    }
                    if (x < buildingPlan.length - 1) {
                        if (buildingPlan[x + 1][y] != null && buildingPlan[x + 1][y].shouldCover) {
                            buildingPlan[x][y] = new WallRightModule(buildingPlan[x + 1][y].myPosition.add(-80, 0));
                            plannedBuilding.add(buildingPlan[x][y]);
                        }
                    }
                    if (y < buildingPlan[x].length - 1) {
                        if (buildingPlan[x][y + 1] != null && buildingPlan[x][y + 1].shouldCover) {
                            buildingPlan[x][y] = new CeilingModule(buildingPlan[x][y + 1].myPosition.add(0, -80));
                            plannedBuilding.add(buildingPlan[x][y]);
                        }
                    }
                    if (y > 0) {
                        if (buildingPlan[x][y - 1] != null && buildingPlan[x][y - 1].shouldCover) {
                            buildingPlan[x][y] = new FloorModule(buildingPlan[x][y - 1].myPosition.add(0, 80));
                            plannedBuilding.add(buildingPlan[x][y]);
                        }
                    }
                }
            }
        }

        myModules = plannedBuilding.toArray(new BuildingModule[plannedBuilding.size()]);
    }

    private BuildingModule getBuildingModule(Point2D mP) {
        switch (Main.numberGenerator.nextInt(6)) {
            case 0:
                return new CorridorModule(mP);
            case 1:
                return new TrapDoorModule(mP);
            case 2:
                return new CorridorModule(mP);
            case 3:
                return new OpenModule(mP);
            case 4:
                return new CeilingModule(mP);
            case 5:
                return new LadderModule(mP);
            default:
                System.out.println("AllowError");
                return null;
        }
    }

    private BuildingModule getCanLeft(Point2D mP) {
        BuildingModule potBuilding;
        do {
            potBuilding = getBuildingModule(mP);
        } while (!potBuilding.canLeft);
        return potBuilding;
    }


    private BuildingModule getCanRight(Point2D mP) {
        BuildingModule potBuilding;
        do {
            potBuilding = getBuildingModule(mP);
        } while (!potBuilding.canRight);
        return potBuilding;
    }


    private BuildingModule getCanUp(Point2D mP) {
        BuildingModule potBuilding;
        do {
            potBuilding = getBuildingModule(mP);
        } while (!potBuilding.canUp);
        return potBuilding;
    }


    private BuildingModule getCanDown(Point2D mP) {
        BuildingModule potBuilding;
        do {
            potBuilding = getBuildingModule(mP);
        } while (!potBuilding.canDown);
        return potBuilding;
    }


    public void changeTerrain(TerrainMap theTerrain) {
        LinkedList<Point2D> PToChange = new LinkedList<>();
        LinkedList<Byte> fVals = new LinkedList<Byte>();

        for (int i = 0; i < myModules.length; i++) {
            for (int x = 0; x < myModules[i].blocks.length; x++) {
                for (int y = 0; y < myModules[i].blocks[x].length; y++) {
                    if(myModules[i].blocks[x][y] != -1) {
                        PToChange.add(new Point2D(x * 4, y * 4).add(myModules[i].myPosition));
                        fVals.add(myModules[i].blocks[x][y]);
                    }
                }
            }
        }
        byte fValsFinal[] = new byte[fVals.size()];
        for (int i = 0; i < fValsFinal.length; i++) {
            fValsFinal[i] = fVals.get(i);
        }
        theTerrain.changeTerrain(PToChange.toArray(new Point2D[PToChange.size()]), fValsFinal, false);
    }
}


class BuildingModule {
    public boolean isVoid = false;
    public byte blocks[][];
    public boolean allowRight, allowLeft, allowBottom, allowTop;
    public boolean needRight, needLeft, needBottom, needTop;
    public boolean canRight, canLeft, canUp, canDown, shouldCover;
    public Point2D myPosition;
    private int myModel;

    public BuildingModule(Point2D mPos) {
        myPosition = mPos;
        blocks = getModule();

    }

    public BuildingModule(Point2D mPos, boolean cR, boolean cL, boolean cD, boolean cU, boolean sC) {
        myPosition = mPos;
        canRight = cR;
        canLeft = cL;
        canUp = cU;
        canDown = cD;
        shouldCover = sC;
    }


    private byte[][] getModule() {
        byte res[][];

        switch (Main.numberGenerator.nextInt(2)) {
            case 0:
                myModel = 0;
                break;
            case 1:
                myModel = 1;
                break;
            default:
                System.out.println("Error module not known");
        }


        res = readModule(myModel);
        return res;
    }

    private byte[][] readModule(int i) {
        byte res[][] = new byte[BuildingSaves.moduleTemplates[0].length][BuildingSaves.moduleTemplates[0][0].length];
        for (int x = 0; x < res.length; x++) {
            for (int y = 0; y < res.length; y++) {
                res[x][y] = BuildingSaves.moduleTemplates[i][x][y];
            }
        }

        return res;
    }

    protected void setModule(int[] indexes) {
        myModel = indexes[Main.numberGenerator.nextInt(indexes.length)];
        blocks = readModule(myModel);
    }
}

class EntranceModule extends BuildingModule {
    private final int[] indexes = {1};

    public EntranceModule(Point2D mPos) {
        super(mPos, true, false, false, false, false);
        setModule(indexes);
    }

}

class CorridorModule extends BuildingModule {
    private final int[] indexes = {0};

    public CorridorModule(Point2D mPos) {
        super(mPos, true, true, false, false, false);
        setModule(indexes);
    }
}

class TrapDoorModule extends BuildingModule {
    private final int[] indexes = {4};

    public TrapDoorModule(Point2D mPos) {
        super(mPos, true, true, true, false, false);
        setModule(indexes);
    }
}

class LadderModule extends BuildingModule {
    private final int[] indexes = {2};

    public LadderModule(Point2D mPos) {
        super(mPos, true, true, false, true, false);
        setModule(indexes);
    }
}

class OpenModule extends BuildingModule {
    private final int[] indexes = {5};

    public OpenModule(Point2D mPos) {
        super(mPos, true, true, true, true, true);
        setModule(indexes);
    }
}

class CeilingModule extends BuildingModule {
    private final int[] indexes = {6};

    public CeilingModule(Point2D mPos) {
        super(mPos, false, false, false, false, false);
        setModule(indexes);
    }
}

class WallLeftModule extends BuildingModule {
    private final int[] indexes = {7};

    public WallLeftModule(Point2D mPos) {
        super(mPos, false, false, false, false, false);
        setModule(indexes);
    }
}

class WallRightModule extends BuildingModule {
    private final int[] indexes = {8};

    public WallRightModule(Point2D mPos) {
        super(mPos, false, false, false, false, false);
        setModule(indexes);
    }
}

class FloorModule extends BuildingModule {
    private final int[] indexes = {9};

    public FloorModule(Point2D mPos) {
        super(mPos, false, false, false, false, false);
        setModule(indexes);
    }
}




