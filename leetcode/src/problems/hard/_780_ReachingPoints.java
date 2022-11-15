package problems.hard;

public class _780_ReachingPoints {
    public boolean reachingPoints(int sx, int sy, int tx, int ty) {
        while (tx >= sx && ty >= sy) {
            if (sx == tx && sy == ty) return true;
            if (sx == tx) return ty%sx == sy%sx;
            if (sy == ty) return tx%sy == sx%sy;

            if (tx > ty) {
                //if (tx%ty == 0) return ty == sy && sx%ty == 0;
                tx %= ty;
            } else {
                //if (ty%tx == 0) return tx == sx && sy%tx == 0;
                ty %= tx;
            }
        }

        return false;
    }
}
