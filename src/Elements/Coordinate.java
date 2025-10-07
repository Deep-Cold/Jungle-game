package Elements;

public class Coordinate {
    private int x;
    private int y;

    public Coordinate(int _x, int _y) {
        x = _x;
        y = _y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void changeX(int delta) {
        x += delta;
    }

    public void changeY(int delta) {
        y += delta;
    }

    public boolean checkBound(int boundX, int boundY) {
        return x > 0 && x <= boundX && y > 0 && y <= boundY;
    }

    public boolean equals(Coordinate target) {
        return x == target.getX() && y == target.getY();
    }
}
