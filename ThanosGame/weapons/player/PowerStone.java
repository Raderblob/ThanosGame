package ThanosGame.weapons.player;

import ThanosGame.Thanos;

public class PowerStone extends Stone {
    public PowerStone(Thanos owner) {
        super(owner);
        stoneType = 3;
        stoneName="Power Stone";
        myPower=1000;
        coolDown = 300;
    }

}
