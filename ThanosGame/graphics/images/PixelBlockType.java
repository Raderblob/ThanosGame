package ThanosGame.graphics.images;

public enum PixelBlockType {
    NOTHING((byte)0),DIRT((byte)1),GRASS((byte)2),BEDROCK((byte)3),STONE((byte)4),BRICK((byte)10),BRICK2((byte)11);
    private byte myVal;
    PixelBlockType(byte i) {
        myVal = i;
    }



    public byte getMyVal() {
        return myVal;
    }
}
