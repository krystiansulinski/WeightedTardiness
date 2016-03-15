import java.util.ArrayList;

/**
 * Created by krystian on 8/03/2016.
 */
public class Permutations {
    private ArrayList<ArrayList<Integer>> permutations;
    private boolean addedFirstPermutation = false;

    public Permutations() {
        permutations = new ArrayList<>();
    }

    public void permute(int n) {
        ArrayList<Integer> list = new ArrayList<>(n);
        for (int i = 0; i < n; ++i) {
            list.add(i+1);
        }

        permute(n, list);
        for (int i = 0; i < permutations.size(); ++i) {
            for (int j = 0; j < permutations.get(i).size(); ++j) {
                permutations.get(i).set(j, permutations.get(i).get(j) - 1);
            }
        }
    }
    private void permute(int n, ArrayList<Integer> list) {
        if (!addedFirstPermutation) {
            addPermutation(list);
            addedFirstPermutation = true;
        }
        if (n != 1) {
            for (int i = 0; i < n - 1; ++i) {
                permute(n - 1, list);
                int temp;
                if (n % 2 == 0) {
                    temp = list.get(i);
                    list.set(i, list.get(n-1));
                } else {
                    temp = list.get(0);
                    list.set(0, list.get(n-1));
                }
                list.set(n-1, temp);
                addPermutation(list);
            }
            permute(n - 1, list);
        }
    }

    private void addPermutation(ArrayList<Integer> permutation) {
        ArrayList<Integer> array = new ArrayList<>();
        for (Integer p : permutation) {
            array.add(p);
        }
        permutations.add(array);
    }

    public int size() {
        return permutations.size()*permutations.get(0).size();
    }

    public ArrayList<Integer> getPermutation(int index) {
        return permutations.get(index);
    }

    public ArrayList<ArrayList<Integer>> getPermutations() {
        return permutations;
    }
}
