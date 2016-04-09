package algorithms;

import java.util.ArrayList;

/**
 * Created by krystian on 8/03/2016.
 */
class Permutations {
    private ArrayList<ArrayList<Integer>> permutations;
    private static long elapsedTime;
    private static boolean addedFirstPermutation = false;

    Permutations(final int n) {
        permuteFromZeroToN(n);
    }

    private void permuteFromZeroToN(final int n) {
        permutations = new ArrayList<>();
        long startTime = System.currentTimeMillis();
        permuteFromZeroToN(n, createIntegersFromOneToN(n));

        elapsedTime = System.currentTimeMillis() - startTime;
    }

    private ArrayList<Integer> createIntegersFromOneToN(final int lastValue) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < lastValue; ++i) {
            list.add(i);
        }
        return list;
    }

    private void permuteFromZeroToN(final int n, ArrayList<Integer> permutable) {
        if (!addedFirstPermutation) {
            permutations.add(getDeepCopy(permutable));
            addedFirstPermutation = true;
        }
        if (n != 1) {
            for (int i = 0; i < n - 1; ++i) {
                permuteFromZeroToN(n - 1, permutable);

                int temp;
                if (n % 2 == 0) {
                    temp = permutable.get(i);
                    permutable.set(i, permutable.get(n - 1));
                } else {
                    temp = permutable.get(0);
                    permutable.set(0, permutable.get(n - 1));
                }
                permutable.set(n - 1, temp);
                permutations.add(getDeepCopy(permutable));
            }
            permuteFromZeroToN(n - 1, permutable);
        }
    }

    private ArrayList<Integer> getDeepCopy(ArrayList<Integer> copying) {
        ArrayList<Integer> deepCopied = new ArrayList<>(copying.size());
        for(Integer element: copying) {
            deepCopied.add(element);
        }
        return deepCopied;
     }

    public int size() {
        return permutations.size();
    }

    public ArrayList<ArrayList<Integer>> getPermutations() {
        return permutations;
    }

    public static long getElapsedTime() {
        return elapsedTime;
    }
}
