package data_structures;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.function.BinaryOperator;


class SegmentTree<T> {
    private final int size;
    private final T[] array;
    public final int length;
    public final T neutralElement;
    public final BinaryOperator<T> operator;

    public SegmentTree(int length, T neutralElement, BinaryOperator<T> operator) {
        this.neutralElement = neutralElement;
        this.operator = operator;
        this.length = length;

        int innerSize = 1;
        while (innerSize < length) {
            innerSize *= 2;
        }

        this.size = innerSize;
        this.array = (T[])Array.newInstance(neutralElement.getClass(), size * 2 - 1);
        Arrays.fill(this.array, neutralElement);
    }

    public SegmentTree(T[] inputArray, T neutralElement, BinaryOperator<T> operator) {
        this(inputArray.length, neutralElement, operator);
        build(inputArray, 0,0, size);
    }

    private void build(T[] inputArray, int stIndex, int leftIndex, int rightIndex) {
        if (rightIndex - leftIndex == 1) {
            if (leftIndex < inputArray.length) {
                array[stIndex] = inputArray[leftIndex];
            }
            return;
        }
        int middleIndex = (leftIndex + rightIndex) / 2;
        build(inputArray, 2 * stIndex + 1, leftIndex, middleIndex);
        build(inputArray, 2 * stIndex + 2, middleIndex, rightIndex);
        array[stIndex] = this.operator.apply(array[2 * stIndex + 1], array[2 * stIndex + 2]);
    }

    private void set(int index, T value, int stIndex, int leftIndex, int rightIndex) {
        if (rightIndex - leftIndex == 1) {
            array[stIndex] = value;
            return;
        }
        int mid = (leftIndex + rightIndex) / 2;
        if (index < mid) {
            set(index, value, 2 * stIndex + 1, leftIndex, mid);
        } else {
            set(index, value, 2 * stIndex + 2, mid, rightIndex);
        }
        array[stIndex] = this.operator.apply(array[2 * stIndex + 1], array[2 * stIndex + 2]);
    }

    public void set(int index, T value) {
        set(index, value, 0, 0, size);
    }

    // Returns element in range [leftIndex, rightindex]
    public T get(int leftIndex, int rightIndex) {
        return get(leftIndex, rightIndex + 1, 0, 0, size);
    }

    private T get(int leftIndex, int rightIndex, int stIndex, int stLeftIndex, int stRightIndex) {
        if (stLeftIndex >= rightIndex || leftIndex >= stRightIndex) {
            return neutralElement;
        }
        if (stLeftIndex >= leftIndex && stRightIndex <= rightIndex) {
            return array[stIndex];
        }
        int middleIndex = (stLeftIndex + stRightIndex) / 2;
        T leftValue = get(leftIndex, rightIndex, 2 * stIndex + 1, stLeftIndex, middleIndex);
        T rightValue = get(leftIndex, rightIndex, 2 * stIndex + 2, middleIndex, stRightIndex);
        return this.operator.apply(leftValue, rightValue);
    }
}