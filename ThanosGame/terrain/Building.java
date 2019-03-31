package ThanosGame.terrain;

import ThanosGame.Main;
import ThanosGame.Personnage;
import ThanosGame.World;
import ThanosGame.terrain.buildings.BuildingSaves;
import javafx.geometry.Point2D;

import java.util.LinkedList;
import java.util.Objects;

public class Building {
    BuildingModule myModules[];
    private Point2D myPosition;

    public Building(Point2D mPos, LinkedList<Personnage> enemyList, World myWorld, TerrainMap myTerrain) {
        myPosition = mPos;
        Point2D cPos = new Point2D(myPosition.getX(), Math.max(myPosition.getY() - 80, 1));


        int potentialBuildingHeight = (int) ((TerrainChunck.chunkParam.getY() * 4) / 80);
        int doorPlacement = (int) (cPos.getY() / 80);
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
                            if (Main.numberGenerator.nextInt(10) < 1)
                                enemyList.add(new Personnage(buildingPlan[x][y].myPosition.add(40, 40), myTerrain, myWorld));
                            buildingComplete = false;
                        }
                        if (buildingPlan[x + 1][y] != null && buildingPlan[x + 1][y].canLeft) {
                            buildingPlan[x][y] = getCanRight(buildingPlan[x + 1][y].myPosition.add(-80, 0));
                            plannedBuilding.add(buildingPlan[x][y]);
                            if (Main.numberGenerator.nextInt(10) < 1)
                                enemyList.add(new Personnage(buildingPlan[x][y].myPosition.add(40, 40), myTerrain, myWorld));
                            buildingComplete = false;
                        }
                        if (buildingPlan[x][y + 1] != null && buildingPlan[x][y + 1].canUp) {
                            buildingPlan[x][y] = getCanDown(buildingPlan[x][y + 1].myPosition.add(0, -80));
                            plannedBuilding.add(buildingPlan[x][y]);
                            if (Main.numberGenerator.nextInt(10) < 1)
                                enemyList.add(new Personnage(buildingPlan[x][y].myPosition.add(40, 40), myTerrain, myWorld));
                            buildingComplete = false;
                        }
                        if (buildingPlan[x][y - 1] != null && buildingPlan[x][y - 1].canDown) {
                            buildingPlan[x][y] = getCanUp(buildingPlan[x][y - 1].myPosition.add(0, 80));
                            plannedBuilding.add(buildingPlan[x][y]);
                            if (Main.numberGenerator.nextInt(10) < 1)
                                enemyList.add(new Personnage(buildingPlan[x][y].myPosition.add(40, 40), myTerrain, myWorld));
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

        myModules = plannedBuilding.toArray(new BuildingModule[0]);
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
        } while (!Objects.requireNonNull(potBuilding).canLeft);
        return potBuilding;
    }


    private BuildingModule getCanRight(Point2D mP) {
        BuildingModule potBuilding;
        do {
            potBuilding = getBuildingModule(mP);
        } while (!Objects.requireNonNull(potBuilding).canRight);
        return potBuilding;
    }


    private BuildingModule getCanUp(Point2D mP) {
        BuildingModule potBuilding;
        do {
            potBuilding = getBuildingModule(mP);
        } while (!Objects.requireNonNull(potBuilding).canUp);
        return potBuilding;
    }


    private BuildingModule getCanDown(Point2D mP) {
        BuildingModule potBuilding;
        do {
            potBuilding = getBuildingModule(mP);
        } while (!Objects.requireNonNull(potBuilding).canDown);
        return potBuilding;
    }


    public void changeTerrain(TerrainMap theTerrain) {
        LinkedList<Point2D> PToChange = new LinkedList<>();
        LinkedList<Byte> fVals = new LinkedList<>();

        for (BuildingModule myModule : myModules) {
            for (int x = 0; x < myModule.blocks.length; x++) {
                for (int y = 0; y < myModule.blocks[x].length; y++) {
                    if (myModule.blocks[x][y] != -100) {
                        PToChange.add(new Point2D(x * 4, y * 4).add(myModule.myPosition));
                        fVals.add(myModule.blocks[x][y]);
                    }
                }
            }
        }
        byte fValsFinal[] = new byte[fVals.size()];
        int i = 0;
        for (byte v : fVals) {
            fValsFinal[i] = v;
            i++;
        }
        theTerrain.changeTerrain(PToChange.toArray(new Point2D[0]), fValsFinal);
    }
}


class BuildingModule {
    public byte blocks[][];
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
            System.arraycopy(BuildingSaves.moduleTemplates[i][x], 0, res[x], 0, res.length);
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




