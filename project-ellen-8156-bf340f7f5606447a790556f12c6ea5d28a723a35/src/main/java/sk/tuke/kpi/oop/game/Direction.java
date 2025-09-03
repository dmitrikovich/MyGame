package sk.tuke.kpi.oop.game;

public enum Direction {
    NORTH(0, 1),
    EAST(1,0),
    SOUTH(0, -1),
    WEST(-1, 0),
    NORTHEAST(1,1),
    NORTHWEST(-1,1),
    SOUTHEAST(1, -1),
    SOUTHWEST(-1,-1),
    NONE(0,0);

    private final int dx;
    private final int dy;

     Direction(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public float getAngle(){
        if(this == NORTH){
            return 0;
        } else if(this == SOUTH){
            return 180;
        } else if(this == EAST){
            return 270;
        } else if(this == WEST){
            return 90;
        } else if(this == NORTHEAST) {
            return 315;
        } else if(this == NORTHWEST) {
            return 45;
        } else if(this == SOUTHEAST) {
            return 225;
        }  else if(this == SOUTHWEST) {
            return 135;
        }
        return 0;
    }

    private Direction getDirection(int x, int y) {
        if(x == 0 && y == 1) return NORTH;
        else if(x == 0 && y == -1) return SOUTH;
        else if(x == 1 && y == 0) return EAST;
        else if(x == -1 && y == 0) return WEST;
        else if(x == 1 && y == 1) return NORTHEAST;
        else if(x == 1 && y == -1) return SOUTHEAST;
        else if(x == -1 && y == 1) return NORTHWEST;
        else if(x == -1 && y == -1) return SOUTHWEST;

        return NONE;
    }

    public Direction combine(Direction other) {
        int x = this.getDx() + other.getDx();
        int y = this.getDy() + other.getDy();

        return getDirection(x,y);
    }

    public Direction split(Direction other) {
        int x = this.getDx() - other.getDx();
        int y = this.getDy() - other.getDy();

        return getDirection(x,y);
    }

    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }
}
