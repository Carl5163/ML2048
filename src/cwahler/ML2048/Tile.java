package cwahler.ML2048;

public class Tile {
    int number;
    boolean merged;

    public Tile(int num) {
        number = num;
        merged = false;
    }
    @Override
    public String toString() {
        return Integer.toString(number);
    }

    @Override
    public boolean equals(Object obj) {
        boolean ret = false;
        if(obj instanceof Tile) {
            if(((Tile)obj).number == number) {
                ret = true;
            }
        }
        return ret;
    }

    public boolean equals(int num) {
        return num == number;
    }
}
