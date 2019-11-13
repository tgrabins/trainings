package tgrabins.performance.hash;

public class Point {

    private final int x;
    private final int y;

    public Point(int x, int y) {
        super();
        this.x = x;
        this.y = y;
    }

    @Override
    public int hashCode() {
        // int                   8421
        // x = 3     0000000000000011
        // y = 4     0000000000000100
        // x * y     0000000000001100
        //           0000001100000100
        int result = x << 16;
        result = result ^ y;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Point other = (Point) obj;
        if (x != other.x)
            return false;
        if (y != other.y)
            return false;
        return true;
    }



}

