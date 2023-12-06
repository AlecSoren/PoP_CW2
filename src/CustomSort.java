import java.util.ArrayList;

public class CustomSort implements SortingInterface {
    private ArrayList<Double> values;
    private ArrayList<Integer> gaps;

    @Override
    public void setValues(ArrayList<Double> values) {
        this.values = values;
        sort();
    }

    @Override
    public ArrayList<Integer> getGaps() {
        return gaps;
    }

    @Override
    public void add(Double value) {
        values.add(value);
        sort();
    }

    @Override
    public void remove(int index) {
        values.remove(index);
        calculateGaps();
    }

    @Override
    public void sort() {
        int n = values.size();
        calculateGaps();
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

    private void calculateGaps() {
        ArrayList<Integer> temp = new ArrayList<Integer>();
        ArrayList<Integer> gaps = new ArrayList<Integer>();
        int n = values.size();
        int gap = 1;
        int i = 2;
        while (gap < n) {
            temp.add(gap);
            gap = (int) (Math.pow(2, i) - 1);
            i++;
        }
        for (i = temp.size() - 1; i >= 0; i--) {
            gaps.add(temp.get(i));
        }
        this.gaps = gaps;
    }
}