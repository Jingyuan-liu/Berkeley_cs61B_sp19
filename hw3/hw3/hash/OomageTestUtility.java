package hw3.hash;

import java.util.List;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        /*
         * Write a utility function that returns true if the given oomages
         * have hashCodes that would distribute them fairly evenly across
         * M buckets. To do this, convert each oomage's hashcode in the
         * same way as in the visualizer, i.e. (& 0x7FFFFFFF) % M.
         * and ensure that no bucket has fewer than N / 50
         * Oomages and no bucket has more than N / 2.5 Oomages.
         */
        int[] buskets = new int[M];
        int N = oomages.size();
        for (Oomage ooA : oomages) {
            int bucketNum = (ooA.hashCode() & 0x7FFFFFFF) % M;
            buskets[bucketNum] += 1;
        }

        for (int i = 0; i < buskets.length; i++) {
            if (buskets[i] > ((double) N) / 2.5 || buskets[i] < N / 50) {
                return false;
            }
        }

        return true;
    }
    public static void main(String[] asl) { }
}