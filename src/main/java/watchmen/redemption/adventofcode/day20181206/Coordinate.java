package watchmen.redemption.adventofcode.day20181206;

public class Coordinate {

    private int x;
    private int y;
    private boolean isInfinite;

    public static Coordinate create(String specialFormatStr){
        if (null == specialFormatStr){
            return null;
        }
        String[] strs = specialFormatStr.split(",");
        if(strs.length != 2){
            return null;
        }

        int x = Integer.valueOf(strs[0].trim());
        int y = Integer.valueOf(strs[1].trim());
        return new Coordinate(x, y);
    }


    public Coordinate(int x,int y){
        this.x = x;
        this.y = y;
        isInfinite = false;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isInfinite() {
        return isInfinite;
    }

    public void setInfinite(boolean infinite) {
        isInfinite = infinite;
    }
}
