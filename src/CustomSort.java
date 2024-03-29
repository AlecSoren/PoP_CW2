import java.util.ArrayList;

public class CustomSort implements SortingInterface {

    private ArrayList<Double> values;

    //Pseudocode implementation
    private ArrayList<Integer> calculateGaps() {
        ArrayList<Integer> temp = new ArrayList<>();
        ArrayList<Integer> gaps = new ArrayList<>();
        int n = values.size();
        int gap = 1;
        int i = 2;
        while (gap < n) {
            temp.add(gap);
            gap = power2(i) - 1;
            i++;
        }
        for (i = temp.size() - 1; i >= 0; i--) {
            gaps.add(temp.get(i));
        }
        return gaps;
    }

    //Pseudocode implementation
    private void shellSort() {
        int n = values.size();
        ArrayList<Integer> gaps = calculateGaps();
        for (int gap:
                gaps) {
            for (int i = gap; i < n; i++) {
                Double temp = values.get(i);
                int j = 0;
                for (j = i; j >= gap; j -= gap) {
                    if (values.get(j - gap) <= temp) {
                        break;
                    }
                    values.set(j, values.get(j - gap));
                }
                values.set(j, temp);
            }
        }
    }

    @Override
    public void setValues(ArrayList<Double> values) {
        this.values = values;
        sort();
    }

    @Override
    public ArrayList<Integer> getGaps() {
        return calculateGapsSafe();
    }

    @Override
    public void add(Double value) {
        if (values != null) {
            values.add(value);
            sort();
        }
    }

    @Override
    public void remove(int index) {
        if (values == null) { return; }
        sort();
        try {
            values.remove(index);
        } catch (IndexOutOfBoundsException ignored) {}
    }

    @Override
    public void sort() {
        if (values != null) {
            removeNull();
            shellSort();
        }
    }

    private ArrayList<Integer> calculateGapsSafe() {
        if (values == null) {
            return new ArrayList<>();
        }
        removeNull();
        return calculateGaps();
    }

    /**
     * Custom function for calculating powers of 2 since Math.pow is not allowed.
     * Returns 2 to the power of the exponent.
     */
    private int power2(int exponent) {
        int result = 1;
        for (int i = 0; i < exponent; i++) {
            result *= 2;
        }
        return result;
    }

    /**
     * Removes null doubles from values, as the sorting algorithm cannot deal with them.
     */
    private void removeNull() {
        if (values == null) { return; }
        int index = 0;
        while (index < values.size()) {
            if (values.get(index) == null) {
                values.remove(index);
            }
            index++;
        }
    }
}