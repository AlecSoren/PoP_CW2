import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

public class Q3Test extends TestCase {

    public void testSetValuesBasic() {
        CustomSort mySort = new CustomSort();
        ArrayList<Double> values = new ArrayList<>(List.of(3.2, 1.5, 0.0, 2.7, 1.0, -52.5, -12.3, -0.0, -1.0));
        ArrayList<Double> expectedValues = new ArrayList<>(List.of(-52.5, -12.3, -1.0, -0.0, 0.0, 1.0, 1.5, 2.7, 3.2));
        mySort.setValues(values);
        assertEquals(expectedValues, values);
    }

    public void testSetValuesTwice() {
        CustomSort mySort = new CustomSort();
        ArrayList<Double> values = new ArrayList<>(List.of(101.0, 102.0, 99.0));
        ArrayList<Double> newValues = new ArrayList<>(List.of(3.2, 1.5, 0.0, 2.7, 1.0, -52.5, -12.3, -0.0, -1.0));
        ArrayList<Double> expectedValues = new ArrayList<>(List.of(-52.5, -12.3, -1.0, -0.0, 0.0, 1.0, 1.5, 2.7, 3.2));
        mySort.setValues(values);
        mySort.setValues(newValues);
        assertEquals(expectedValues, newValues);
    }

    public void testSetOneElement() {
        CustomSort mySort = new CustomSort();
        ArrayList<Double> values = new ArrayList<>(List.of(-1.0));
        ArrayList<Double> expectedValues = new ArrayList<>(List.of(-1.0));
        mySort.setValues(values);
        assertEquals(expectedValues, values);
    }

    public void testSetValuesEmpty() {
        CustomSort mySort = new CustomSort();
        ArrayList<Double> values = new ArrayList<>();
        ArrayList<Double> expectedValues = new ArrayList<>();
        mySort.setValues(values);
        assertEquals(expectedValues, values);
    }

    public void testSetValuesNull() {
        CustomSort mySort = new CustomSort();
        mySort.setValues(null);
    }

    public void testSetValuesNullElement() {
        CustomSort mySort = new CustomSort();
        ArrayList<Double> values = new ArrayList<>();
        values.add(null);
        values.add(0.1);
        values.add(-0.54);
        values.add(null);
        values.add(117.65);
        values.add(54.3);
        values.add(null);
        ArrayList<Double> expectedValues = new ArrayList<>(List.of(-0.54, 0.1, 54.3, 117.65));
        mySort.setValues(values);
        assertEquals(expectedValues, values);
    }

    public void testSetValuesDuplicate() {
        CustomSort mySort = new CustomSort();
        ArrayList<Double> values = new ArrayList<>(List.of(-2.0, 1.5, -2.0, 1.5, -2.0));
        ArrayList<Double> expectedValues = new ArrayList<>(List.of(-2.0, -2.0, -2.0, 1.5, 1.5));
        mySort.setValues(values);
        assertEquals(expectedValues, values);
    }

    public void testSetValuesLarge() {
        CustomSort mySort = new CustomSort();
        ArrayList<Double> values = new ArrayList<>();
        for (int i = 1; i <= 20000; i++) {
            values.add(1000000000.0 + i);
            values.add(1000000000.0 / i);
            values.add(2000000000.5);
        }
        ArrayList<Double> expectedValues = new ArrayList<>();
        for (int i = 20000; i >= 1; i--) {
            expectedValues.add(1000000000.0 / i);
        }
        for (int i = 1; i <= 20000; i++) {
            expectedValues.add(1000000000.0 + i);
        }
        for (int i = 1; i <= 20000; i++) {
            expectedValues.add(2000000000.5);
        }
        mySort.setValues(values);
        assertEquals(expectedValues, values);
    }

    public void testSetValuesLargeDoubles() {
        CustomSort mySort = new CustomSort();
        ArrayList<Double> values = new ArrayList<>(List.of(1.0e300+2, 1.0e300+1, 0.0, 1.0, -7.5e300, -7.5e300-2));
        ArrayList<Double> expectedValues = new ArrayList<>(List.of(-7.5e300-2, -7.5e300, 0.0, 1.0, 1.0e300+1,1.0e300+2));
        mySort.setValues(values);
        assertEquals(expectedValues, values);
    }

    public void testGetGapsBasic() {
        CustomSort mySort = new CustomSort();
        ArrayList<Double> values = new ArrayList<>(List.of(6.7, 56.2, 2.0, 1.0, 0.0, -0.0, 500.0, -1976.0));
        mySort.setValues(values);
        ArrayList<Integer> expectedGaps = new ArrayList<>(List.of(7, 3, 1));
        assertEquals(expectedGaps, mySort.getGaps());
    }

    //Tests that getGaps behaves correctly when the length of values is equal to a power of 2 minus 1
    public void testGetGapsLengthPower2() {
        CustomSort mySort = new CustomSort();
        ArrayList<Double> values = new ArrayList<>(List.of(6.7, 56.2, 2.0, 1.0, -0.0, 500.0, -1976.0));
        mySort.setValues(values);
        ArrayList<Integer> expectedGaps = new ArrayList<>(List.of(3, 1));
        assertEquals(expectedGaps, mySort.getGaps());
    }

    public void testGetGapsOneValue() {
        CustomSort mySort = new CustomSort();
        ArrayList<Double> values = new ArrayList<>(List.of(82.0));
        mySort.setValues(values);
        ArrayList<Integer> expectedGaps = new ArrayList<>();
        assertEquals(expectedGaps, mySort.getGaps());
    }

    public void testGetGapsEmpty() {
        CustomSort mySort = new CustomSort();
        ArrayList<Double> values = new ArrayList<>();
        mySort.setValues(values);
        ArrayList<Integer> expectedGaps = new ArrayList<>();
        assertEquals(expectedGaps, mySort.getGaps());
    }

    public void testGetGapsNull() {
        CustomSort mySort = new CustomSort();
        mySort.setValues(null);
        ArrayList<Integer> expectedGaps = new ArrayList<>();
        assertEquals(expectedGaps, mySort.getGaps());
    }

    public void testGetGapsLarge() {
        CustomSort mySort = new CustomSort();
        ArrayList<Double> values = new ArrayList<>();
        for (int i = 0; i < 1000000; i++) {
            values.add(0.0 + i);
        }
        mySort.setValues(values);
        ArrayList<Integer> expectedGaps = new ArrayList<>();
        for (int i = 19; i > 0; i--) {
            expectedGaps.add((int) Math.pow(2, i) - 1);
        }
        assertEquals(expectedGaps, mySort.getGaps());
    }

    public void testAddBasic() {
        CustomSort mySort = new CustomSort();
        ArrayList<Double> values = new ArrayList<>();
        mySort.setValues(values);
        mySort.add(5.4);
        mySort.add(-0.9);
        mySort.add(0.0);
        mySort.add(1.0);
        ArrayList<Double> expectedValues = new ArrayList<>(List.of(-0.9, 0.0, 1.0, 5.4));
        assertEquals(expectedValues, values);
    }

    public void testAddGetGaps() {
        CustomSort mySort = new CustomSort();
        ArrayList<Double> values = new ArrayList<>();
        mySort.setValues(values);
        mySort.add(11.0);
        mySort.add(7.9);
        mySort.add(115.0);
        mySort.add(-0.2);
        mySort.add(-0.17);
        ArrayList<Integer> expectedGaps = new ArrayList<>(List.of(3, 1));
        assertEquals(expectedGaps, mySort.getGaps());
    }

    public void testSetAndAdd() {
        CustomSort mySort = new CustomSort();
        ArrayList<Double> values = new ArrayList<>(List.of(0.7, 11.15, 19.5));
        mySort.setValues(values);
        mySort.add(5.4);
        mySort.add(-0.9);
        mySort.add(0.0);
        mySort.add(1.0);
        ArrayList<Double> expectedValues = new ArrayList<>(List.of(-0.9, 0.0, 0.7, 1.0, 5.4, 11.15, 19.5));
        assertEquals(expectedValues, values);
    }

    public void testAddToNull() {
        CustomSort mySort = new CustomSort();
        mySort.setValues(null);
        mySort.add(5.4);
    }

    public void testAddNull() {
        CustomSort mySort = new CustomSort();
        ArrayList<Double> values = new ArrayList<>(List.of(0.7, 11.15, 19.5));
        mySort.setValues(values);
        mySort.add(null);
        ArrayList<Double> expectedValues = new ArrayList<>(List.of(0.7, 11.15, 19.5));
        assertEquals(expectedValues, values);
    }

    public void testAddMany() {
        CustomSort mySort = new CustomSort();
        ArrayList<Double> values = new ArrayList<>();
        mySort.setValues(values);
        ArrayList<Double> expectedValues = new ArrayList<>();
        for (int i = 0; i < 10001; i++) {
            mySort.add(90001.0 - i);
            expectedValues.add(80001.0 + i);
        }
        assertEquals(expectedValues, values);
    }

    public void testRemoveBasic() {
        CustomSort mySort = new CustomSort();
        ArrayList<Double> values = new ArrayList<>(List.of(-0.9, 0.0, 1.0, 5.4));
        mySort.setValues(values);
        mySort.remove(1);
        mySort.remove(2);
        ArrayList<Double> expectedValues = new ArrayList<>(List.of(-0.9, 1.0));
        assertEquals(expectedValues, values);
    }

    public void testRemoveBadIndex() {
        CustomSort mySort = new CustomSort();
        ArrayList<Double> values = new ArrayList<>(List.of(-0.9, 0.0, 1.0, 5.4));
        mySort.setValues(values);
        mySort.remove(4);
        mySort.remove(5);
        ArrayList<Double> expectedValues = new ArrayList<>(List.of(-0.9, 0.0, 1.0, 5.4));
        assertEquals(expectedValues, values);
    }

    public void testRemoveNegativeIndex() {
        CustomSort mySort = new CustomSort();
        ArrayList<Double> values = new ArrayList<>(List.of(-0.9, 0.0, 52.6, 1.0, 5.4));
        mySort.setValues(values);
        mySort.remove(-1);
        mySort.remove(-4);
//        This is only if we allow negative indices, which we probably shouldn't
//        ArrayList<Double> expectedValues = new ArrayList<>(List.of(0.0, 1.0, 5.4));
        ArrayList<Double> expectedValues = new ArrayList<>(List.of(-0.9, 0.0, 1.0, 5.4, 52.6));
        assertEquals(expectedValues, values);
    }

    public void testRemoveMany() {
        CustomSort mySort = new CustomSort();
        ArrayList<Double> values = new ArrayList<>();
        ArrayList<Double> expectedValues = new ArrayList<>();
        for (int i = 0; i < 20000; i += 2) {
            values.add(0.0 + i);
            values.add(1.0 + i);
            expectedValues.add(1.0 + i);
        }
        mySort.setValues(values);
        for (int i = 0; i < 10000; i++) {
            mySort.remove(i);
        }
        assertEquals(expectedValues, values);
    }

    public void testSortBasic() {
        CustomSort mySort = new CustomSort();
        ArrayList<Double> values = new ArrayList<>(List.of(3.2, 1.5, 0.0, 2.7, 1.0, -52.5, -12.3, -0.0, -1.0));
        ArrayList<Double> expectedValues = new ArrayList<>(List.of(-52.5, -12.3, -1.0, -0.0, 0.0, 1.0, 1.5, 2.7, 3.2));
        mySort.setValues(values);
        mySort.sort();
        assertEquals(expectedValues, values);
    }

    public void testSortEmpty() {
        CustomSort mySort = new CustomSort();
        ArrayList<Double> values = new ArrayList<>();
        ArrayList<Double> expectedValues = new ArrayList<>();
        mySort.setValues(values);
        mySort.sort();
        assertEquals(expectedValues, values);
    }

    public void testSortNull() {
        CustomSort mySort = new CustomSort();
        mySort.setValues(null);
        mySort.sort();
    }

    public void testOriginalValuesAdd() {
        CustomSort mySort = new CustomSort();
        ArrayList<Double> values = new ArrayList<>(List.of(2.7, 1.0, -52.5, -12.3, -0.0, -1.0));
        mySort.setValues(values);

        values.add(-6.0);
        values.add(null);
        ArrayList<Double> expectedValues = new ArrayList<>(List.of(-52.5, -12.3, -1.0, -0.0, 1.0, 2.7, -6.0));
        expectedValues.add(null);
        assertEquals(expectedValues, values);
        mySort.sort();
        expectedValues = new ArrayList<>(List.of(-52.5, -12.3, -6.0, -1.0, -0.0, 1.0, 2.7));
        assertEquals(expectedValues, values);
        values.add(null);
        ArrayList<Integer> expectedGaps = new ArrayList<>(List.of(3, 1));
        assertEquals(expectedGaps, mySort.getGaps());
        values.add(2.9);
        expectedGaps = new ArrayList<>(List.of(7, 3, 1));
        assertEquals(expectedGaps, mySort.getGaps());

        values.add(1.11);
        values.add(null);
        mySort.add(-2.11);
        expectedValues = new ArrayList<>(List.of(-52.5, -12.3, -6.0, -2.11, -1.0, -0.0, 1.0, 1.11, 2.7, 2.9));
        assertEquals(expectedValues, values);

        values.add(2.3);
        values.add(null);
        mySort.remove(10);
        expectedValues = new ArrayList<>(List.of(-52.5, -12.3, -6.0, -2.11, -1.0, -0.0, 1.0, 1.11, 2.3, 2.7));
        assertEquals(expectedValues, values);
    }

    public void testOriginalValuesRemove() {
        CustomSort mySort = new CustomSort();
        ArrayList<Double> values = new ArrayList<>(List.of(2.7, 1.0, -52.5, -12.3, -0.0, -1.0));
        values.remove(3);
        mySort.setValues(values);

        values.remove(1);
        ArrayList<Double> expectedValues = new ArrayList<>(List.of(-52.5, -0.0, 1.0, 2.7));
        assertEquals(expectedValues, values);

        ArrayList<Integer> expectedGaps = new ArrayList<>(List.of(3, 1));
        assertEquals(expectedGaps, mySort.getGaps());
        values.remove(0);
        expectedGaps = new ArrayList<>(List.of(1));
        assertEquals(expectedGaps, mySort.getGaps());

        values.remove(1);
        mySort.add(-2.11);
        expectedValues = new ArrayList<>(List.of(-2.11, -0.0, 2.7));
        assertEquals(expectedValues, values);

        values.remove(0);
        mySort.remove(1);
        expectedValues = new ArrayList<>(List.of(-0.0));
        assertEquals(expectedValues, values);
    }
}