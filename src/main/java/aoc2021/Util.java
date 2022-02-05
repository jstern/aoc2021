package aoc2021;

import java.util.List;

public class Util {
    public static int wrappedIndex(int size, int idx) {
        var i = idx % size;
        if (i < 0) {
            return size + i;
        }
        return i;
    }

    /**
     * Get the element at idx mod the size of the array.
     */
    public static <T> T wrappedGet(T[] arr, int idx) {
        return arr[wrappedIndex(arr.length, idx)];
    }

    /**
     * Get the element at idx mod the size of the list.
     */
    public static <T> T wrappedGet(List<T> lst, int idx) {
        return lst.get(wrappedIndex(lst.size(), idx));
    }
}
