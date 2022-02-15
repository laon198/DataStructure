package sort;

import list.ArrayList;
import list.List;

public class LSDRadixSort {
    public static void sort(List<Integer> list, int maxDigit) {
        for (int curExp = 0; (int) Math.pow(10, curExp) <= maxDigit; curExp++) {
            countingSortByLSD(list, curExp);
        }
    }

    private static void countingSortByLSD(List<Integer> list, int digitExp) {
        final int DECIMAL = 10;
        int[] counter = new int[DECIMAL];
        // 개수 세기
        for (int i : list) {
            counter[getCounterIndex(i, digitExp)]++;
        }

        //인덱스 계산
        for (int i = 1; i < counter.length; i++) {
            counter[i] += counter[i - 1];
        }

        //정렬
        int[] tmp = new int[list.size()];
        for(int i=list.size()-1; i>=0; i--){
            int index = --counter[getCounterIndex(list.get(i), digitExp)];
            tmp[index] = list.get(i);
        }

        //복사
        for (int i = 0; i < list.size(); i++) {
            list.set(tmp[i], i);
        }
    }

    private static int getCounterIndex(int value, int digit) {
        return ((value / (int) Math.pow(10, digit)) % 10);
    }

    public static void print(List<Integer> list) {
        for (int i : list) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public static void main(String args[]) {
        List<Integer> list = new ArrayList<>();
        final int range = 20;

        for (int i = 0; i < 20; i++) {
            list.add((int) ((Math.random() * 900) + 100));
        }

        System.out.println("정렬 전");
        print(list);
        System.out.println();

        sort(list, 100);

        System.out.println();
        System.out.println("정렬 후");
        print(list);
    }
}
