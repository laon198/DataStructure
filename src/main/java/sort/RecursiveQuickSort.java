package sort;

import list.ArrayList;
import list.List;

public class RecursiveQuickSort {
    // divide 역할
    public static void sort(List<Integer> list) {
        sort(list, 0, list.size() - 1);
    }

    private static void sort(List<Integer> list, int low, int high) {
        if (low >= high) {
            return;
        }

        int pivotIdx = partition(list, low, low, high);

        sort(list, low, pivotIdx - 1);
        sort(list, pivotIdx + 1, high);
    }

    // conquer(정렬)의 역할
    private static int partition(List<Integer> list, int pivotIdx, int low, int high) {
        int pivot = list.get(pivotIdx);
        int left = low;
        int right = high;

        while (left < right) {
            while (left < right && list.get(right) > pivot) {
                right--;
            }

            while (left < right && list.get(left) <= pivot) {
                left++;
            }

            swap(list, left, right);
        }


        swap(list, pivotIdx, left);
        return left;
    }

    private static void swap(List<Integer> list, int i, int j) {
        int tmp = list.get(i);
        list.set(list.get(j), i);
        list.set(tmp, j);
    }

    public static void print(List<Integer> list) {
        for (int i : list) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public static void main(String args[]) {
        List<Integer> list = new ArrayList<>();
        list.add(5);
        list.add(3);
        list.add(1);
        list.add(10);
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
