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
        myModules = new BuildingModule[Main.numberGenerator.nextInt(3) + 1];
        Point2D cPos = new Point2D(myPosition.getX(), myPosition.getY());

      /*  for (int i = 0; i < myModules.length; i++) {
            myModules[i] = new BuildingModule(cPos);
            cPos = cPos.add(80, 0);
        }*/
        int b = (int) ((TerrainChunck.chunkParam.getY() * 4 - 1) / 80)-1;
        BuildingModule[][] buildingPlan = new BuildingModule[6][b];
        LinkedList<BuildingModule> plannedBuilding = new LinkedList<>();
        boolean buildingComplete;
        buildingPlan[1][1] = new EntranceModule(new Point2D(cPos.getX(), 80));
        plannedBuilding.add(buildingPlan[1][1]);
        do {
            buildingComplete = true;
            System.out.println("start loop");
            for (int x = 0; x < buildingPlan.length; x++) {
                for (int y = 0; y < buildingPlan[x].length; y++) {
                    if (buildingPlan[x][y] != null) {
                        System.out.println(x + " " + y);

                        if (x < buildingPlan.length - 1 && buildingPlan[x + 1][y] == null) {
                            if (buildingPlan[x][y].needRight) {
                                if (x + 1 < buildingPlan.length - 1) {
                                    buildingPlan[x + 1][y] = getAllowLeft(buildingPlan[x][y].myPosition.add(80, 0));
                                } else {
                                    buildingPlan[x + 1][y] = getAllowLeftEnd(buildingPlan[x][y].myPosition.add(80, 0));
                                }
                                plannedBuilding.add(buildingPlan[x + 1][y]);
                                buildingComplete = false;
                            }
                            if (buildingPlan[x][y].allowRight) {
                                if (Main.numberGenerator.nextInt(2) == 0) {
                                    if (x + 1 < buildingPlan.length - 1) {
                                        buildingPlan[x + 1][y] = getAllowLeft(buildingPlan[x][y].myPosition.add(80, 0));
                                    } else {
                                        buildingPlan[x + 1][y] = getAllowLeftEnd(buildingPlan[x][y].myPosition.add(80, 0));
                                    }
                                    plannedBuilding.add(buildingPlan[x + 1][y]);
                                    buildingComplete = false;
                                } else {
                                    buildingPlan[x + 1][y] = new EndModule();
                                }
                            }
                        }

                        if (x > 0 && buildingPlan[x - 1][y] == null) {
                            if (buildingPlan[x][y].needLeft) {
                                if (x - 1 > 0) {
                                    buildingPlan[x - 1][y] = getAllowRight(buildingPlan[x][y].myPosition.add(-80, 0));
                                } else {
                                    buildingPlan[x - 1][y] = getAllowRightEnd(buildingPlan[x][y].myPosition.add(-80, 0));
                                }
                                plannedBuilding.add(buildingPlan[x - 1][y]);
                                buildingComplete = false;
                            }
                            if (buildingPlan[x][y].allowLeft) {
                                if (Main.numberGenerator.nextInt(2) == 0) {
                                    if (x - 1 > 0) {
                                        buildingPlan[x - 1][y] = getAllowRight(buildingPlan[x][y].myPosition.add(-80, 0));
                                    } else {
                                        buildingPlan[x - 1][y] = getAllowRightEnd(buildingPlan[x][y].myPosition.add(-80, 0));
                                    }
                                    plannedBuilding.add(buildingPlan[x - 1][y]);
                                    buildingComplete = false;
                                } else {
                                    buildingPlan[x - 1][y] = new EndModule();
                                }
                            }
                        }


                        if (y > 0 && buildingPlan[x][y - 1] == null) {
                            if (buildingPlan[x][y].needTop) {
                                if(y-1>0) {
                                    buildingPlan[x][y - 1] = getAllowBottom(buildingPlan[x][y].myPosition.add(0, -80));
                                }else{
                                    buildingPlan[x][y - 1] = getAllowBottomEnd(buildingPlan[x][y].myPosition.add(0, -80));
                                }
                                plannedBuilding.add(buildingPlan[x][y - 1]);
                                buildingComplete = false;
                            }
                            if (buildingPlan[x][y].allowTop){
                                if (Main.numberGenerator.nextInt(2) == 0) {
                                    if(y-1>0) {
                                        buildingPlan[x][y - 1] = getAllowBottom(buildingPlan[x][y].myPosition.add(0, -80));
                                    }else{
                                        buildingPlan[x][y - 1] = getAllowBottomEnd(buildingPlan[x][y].myPosition.add(0, -80));
                                    }
                                    plannedBuilding.add(buildingPlan[x][y - 1]);
                                    buildingComplete = false;
                                } else {
                                    buildingPlan[x ][y-1] = new EndModule();
                                }
                            }
                        }


                        if (y < buildingPlan[x].length - 1 && buildingPlan[x][y + 1] == null) {
                            if (buildingPlan[x][y].needBottom) {
                                if(y+1<buildingPlan[x].length-1) {
                                    buildingPlan[x][y + 1] = getAllowTop(buildingPlan[x][y].myPosition.add(0, +80));
                                }else{
                                    buildingPlan[x][y + 1] = getAllowTopEnd(buildingPlan[x][y].myPosition.add(0, +80));
                                }
                                plannedBuilding.add(buildingPlan[x][y + 1]);
                                buildingComplete = false;
                            }
                            if (buildingPlan[x][y].allowBottom) {
                                if (Main.numberGenerator.nextInt(2) == 0) {
                                    if(y+1<buildingPlan[x].length-1) {
                                        buildingPlan[x][y + 1] = getAllowTop(buildingPlan[x][y].myPosition.add(0, +80));
                                    }else{
                                        buildingPlan[x][y + 1] = getAllowTopEnd(buildingPlan[x][y].myPosition.add(0, +80));
                                    }
                                    plannedBuilding.add(buildingPlan[x][y + 1]);
                                    buildingComplete = false;
                                } else {
                                    buildingPlan[x][y+1] = new EndModule();
                                }
                            }
                        }
                    } else {
                        System.out.println(x + " " + y + " null");
                    }
                }
            }
        } while (!buildingComplete);
        myModules = plannedBuilding.toArray(new BuildingModule[plannedBuilding.size()]);
    }

    private BuildingModule getBuildingModule(Point2D mP) {
        switch (Main.numberGenerator.nextInt(6)) {
            case 0:
                return new CorridorModule(mP);
            case 1:
                return new TrapDoorModule(mP);
            case 2:
                return new EntranceModule(mP);
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

    private BuildingModule getAllowLeft(Point2D mP) {
        BuildingModule potBuilding;
        do {
            potBuilding = getBuildingModule(mP);
        } while (!potBuilding.allowLeft);
        return potBuilding;
    }

    private BuildingModule getAllowLeftEnd(Point2D mP) {
        BuildingModule potBuilding;
        do {
            potBuilding = getAllowLeft(mP);
        } while (potBuilding.needRight);
        return potBuilding;
    }

    private BuildingModule getAllowRight(Point2D mP) {
        BuildingModule potBuilding;
        do {
            potBuilding = getBuildingModule(mP);
        } while (!potBuilding.allowRight);
        return potBuilding;
    }

    private BuildingModule getAllowRightEnd(Point2D mP) {
        BuildingModule potBuilding;
        do {
            potBuilding = getAllowRight(mP);
        } while (potBuilding.needLeft);
        return potBuilding;
    }

    private BuildingModule getAllowTop(Point2D mP) {
        BuildingModule potBuilding;
        do {
            potBuilding = getBuildingModule(mP);
        } while (!potBuilding.allowTop);
        return potBuilding;
    }

    private BuildingModule getAllowTopEnd(Point2D mP) {
        BuildingModule potBuilding;
        do {
            potBuilding = getAllowTop(mP);
        } while (potBuilding.needBottom);
        return potBuilding;
    }

    private BuildingModule getAllowBottom(Point2D mP) {
        BuildingModule potBuilding;
        do {
            potBuilding = getBuildingModule(mP);
        } while (!potBuilding.allowBottom);
        return potBuilding;
    }

    private BuildingModule getAllowBottomEnd(Point2D mP) {
        BuildingModule potBuilding;
        do {
            potBuilding = getAllowBottom(mP);
        } while (potBuilding.needTop);
        return potBuilding;
    }


    public void changeTerrain(TerrainMap theTerrain) {
        LinkedList<Point2D> PToChange = new LinkedList<>();
        LinkedList<Byte> fVals = new LinkedList<Byte>();

        for (int i = 0; i < myModules.length; i++) {
            for (int x = 0; x < myModules[i].blocks.length; x++) {
                for (int y = 0; y < myModules[i].blocks[x].length; y++) {
                    PToChange.add(new Point2D(x * 4, y * 4).add(myModules[i].myPosition));
                    fVals.add(myModules[i].blocks[x][y]);
                }
            }
        }
        byte fValsFinal[] = new byte[fVals.size()];
        for (int i = 0; i < fValsFinal.length; i++) {
            fValsFinal[i] = fVals.get(i).byteValue();
        }
        theTerrain.changeTerrain(PToChange.toArray(new Point2D[PToChange.size()]), fValsFinal, false);
    }
}


class BuildingModule {
    public boolean isVoid = false;
    public byte blocks[][];
    public boolean allowRight, allowLeft, allowBottom, allowTop;
    public boolean needRight, needLeft, needBottom, needTop;
    public Point2D myPosition;
    private int myModel;

    public BuildingModule(Point2D mPos) {
        myPosition = mPos;
        blocks = getModule();

    }

    public BuildingModule(Point2D mPos, boolean aR, boolean aL, boolean aB, boolean aT, boolean nR, boolean nL, boolean nB, boolean nT) {
        myPosition = mPos;
        allowLeft = aL;
        allowRight = aR;
        allowBottom = aB;
        allowTop = aT;
        needBottom = nB;
        needLeft = nL;
        needRight = nR;
        needTop = nT;
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
        super(mPos, true, false, true, true, true, false, false, false);
        setModule(indexes);
    }
}

class CorridorModule extends BuildingModule {
    private final int[] indexes = {0};

    public CorridorModule(Point2D mPos) {
        super(mPos, true, true, true, true, true, true, false, false);
        setModule(indexes);
    }
}

class TrapDoorModule extends BuildingModule {
    private final int[] indexes = {4};

    public TrapDoorModule(Point2D mPos) {
        super(mPos, true, true, true, true, false, false, true, false);
        setModule(indexes);
    }
}

class LadderModule extends BuildingModule {
    private final int[] indexes = {2};

    public LadderModule(Point2D mPos) {
        super(mPos, true, true, true, true, false, false, false, false);
        setModule(indexes);
    }
}

class OpenModule extends BuildingModule {
    private final int[] indexes = {1};

    public OpenModule(Point2D mPos) {
        super(mPos, true, true, true, true, true, true, true, true);
        setModule(indexes);
    }
}

class CeilingModule extends BuildingModule {
    private final int[] indexes = {6};

    public CeilingModule(Point2D mPos) {
        super(mPos, true, true, true, false, false, false, true, false);
        setModule(indexes);
    }
}


class EndModule extends BuildingModule {
    public EndModule() {
        super(null, false, false, false, false, false, false, false, false);
        isVoid = true;
    }
}

