package data_structures;

public class SubsetBitMask {

    private final int setSize;
    private final long max;
    private long current;

    public SubsetBitMask(int setSize) {
        this(setSize, 0);
    }

    public SubsetBitMask(int setSize, long start) {
        this.setSize = setSize;
        this.current = start;
        this.max = setSize > 0 ? (long)Math.pow(2, setSize) : 0;
    }

    public boolean[] next() {
        if (!hasNext()) {
            throw new IndexOutOfBoundsException();
        }

        long num = current++;
        boolean[] arr = new boolean[setSize];
        for (int i = setSize-1; i >= 0 && num > 0; i--) {
            arr[i] = (num & 1) != 0;
            num = num >> 1;
        }

        return arr;
    }

    public boolean hasNext() {
        return current < max;
    }
}
