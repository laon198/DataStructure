package sort;

import list.ArrayList;
import list.List;

public class IterativeMergeSort {
    // divide 역할
    public static void sort(List<Integer> list) {
        ArrayList<Integer> res = new ArrayList<>(list.size());
        for (int i = 0; i < list.size(); i++) {
            res.add(0);
        }

        for (int gap = 2; gap < list.size(); gap *= 2) {
            for (int low = 0, high = gap - 1;
                 low < list.size() - 1-gap; low += gap, high += gap) {
                merge(list, res, low, high);
            }
            merge(list, res, list.size()-1-gap, list.size()-1);
        }
    }

    // conquer(정렬)의 역할
    private static void merge(List<Integer> list, List<Integer> res, int low, int high) {
        int mid = (low + high) / 2;
        int i = low;
        int j = mid + 1;

        for (int k = low; k <= high; k++) {
            if (j > high || (i < mid + 1 && list.get(i) < list.get(j))) {
                res.set(list.get(i++), k);
            } else {
                res.set(list.get(j++), k);
            }
        }

        for (int k = low; k <= high; k++) {
            list.set(res.get(k), k);
        }
    }

    private static void fillList(List<Integer> origin, List<Integer> target,
                                 int begin, int end) {
        for (int i = begin; i < end; i++) {
            target.add(origin.get(i));
        }
    }


    public static void print(List<Integer> list) {
        for (int i : list) {
            System.out.print(i + " ");
        }
    }

    public static void main(String args[]) {
        List<Integer> list = new ArrayList<>();
        list.add(5);
        list.add(3);
        list.add(1);
        list.add(4);
        list.add(2);
        list.add(7);
        list.add(9);
        list.add(8);
        list.add(10);
        list.add(6);

        System.out.println("정렬 전");
        print(list);
        System.out.println();

        sort(list);

        System.out.println();
        System.out.println("정렬 후");
        print(list);
    }
}
