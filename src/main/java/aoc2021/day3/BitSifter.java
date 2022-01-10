package aoc2021.day3;

// TODO: come back and see if there's a completely bitwise approach to this

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public class BitSifter {
    private int[] unset = null;
    private int[] set = null;
    private char[] common = null; // probably don't need both of these
    private char[] uncommon = null;
    private final Set<String> vals = new HashSet<>();
    public int gamma;
    public int epsilon;

    public BitSifter(Stream<String> inputs) {
        // System.out.println("Another BS");
        inputs.forEach(this::add);
        calculate();
    }

    void add(String bits) {
        // System.out.println("Add " + bits);
        int size = bits.length();
        if (unset == null) {
            unset = new int[size];
            set = new int[size];
        }

        vals.add(bits);
        for (int i = 0; i < size; i++) {
            char c = bits.charAt(i);
            if      (c == '0') { unset[i]++; }
            else if (c == '1') {   set[i]++; }
        }
    }

    // ASSUMPTION: there is always a most common / least common value (no ties)?
    void calculate() {
        int size = unset.length;
        common = new char[size];
        uncommon = new char[size];
        for (int i = 0; i < size; i++) {
            if (unset[i] > set[i]) {
                  common[i] = '0';
                uncommon[i] = '1';
            } else {
                common[i] = '1';
                uncommon[i] = '0';
            }
        }
        gamma = Integer.parseInt(String.valueOf(common), 2);
        epsilon = Integer.parseInt(String.valueOf(uncommon), 2);
    }

    public int sift(boolean wantCommon) {
        return sift(0, wantCommon);
    }

    public int sift(int pos, boolean wantCommon) {
        if (pos >= set.length) {
            throw new IllegalStateException("Ran out of bits to shift");
        }

        var check = wantCommon ? common : uncommon;
        List<String> sifted = vals.stream().filter(v -> check[pos] == v.charAt(pos)).toList();

        if (sifted.size() == 1) {
            return Integer.parseInt(String.valueOf(sifted.get(0)), 2);
        }

        return new BitSifter(sifted.stream()).sift(pos + 1, wantCommon);
    }
}
