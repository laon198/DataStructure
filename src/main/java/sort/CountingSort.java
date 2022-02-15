package sort;

import list.ArrayList;
import list.List;
import stack.ArrayStack;
import stack.Stack;

public class CountingSort {
    public static void sort(List<Integer> list, int maxRange) {
        int[] counter = new int[maxRange+1];
        // 개수 세기
        for(int i : list){
            counter[i]++;
        }

        //인덱스 계산
        for(int i=1; i<counter.length; i++){
            counter[i] += counter[i-1];
        }

        //정렬
        int[] tmp = new int[list.size()];
        for(int i=list.size()-1; i>=0; i--){
            int index = --counter[list.get(i)];
            tmp[index] = list.get(i);
        }

        //복사
        for(int i=0; i<list.size(); i++){
            list.set(tmp[i], i);
        }
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

        for(int i=0; i<20; i++){
            list.add((int)(Math.random()*range));
        }

        System.out.println("정렬 전");
        print(list);
        System.out.println();

        sort(list, range);

        System.out.println();
        System.out.println("정렬 후");
        print(list);
    }
}
