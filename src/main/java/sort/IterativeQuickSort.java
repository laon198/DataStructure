package sort;

import list.ArrayList;
import list.List;
import stack.ArrayStack;
import stack.Stack;

public class IterativeQuickSort {
    public static void sort(List<Integer> list) {
        Stack<Integer> stack = new ArrayStack<>();

        stack.push(0);
        stack.push(list.size()-1);

        while(!stack.isEmpty()){
            int high = stack.pop();
            int low = stack.pop();
            // median of three 방식, 기존 코드 재활용하기 위해 간단하게 교체
            swap(list, low, getMedianOf(list, low, high));
            int pivotIdx = partition(list, low, low, high);

            //재귀에서 조건과 동일
            if(pivotIdx-1>low){
                stack.push(low);
                stack.push(pivotIdx-1);
            }

            if(high>pivotIdx+1){
                stack.push(pivotIdx+1);
                stack.push(high);
            }
        }
    }

    public static int getMedianOf(List<Integer> list, int low, int high){
        int lowVal = list.get(low);
        int midVal = list.get((low+high)/2);
        int highVal = list.get(high);

        if(lowVal>midVal){
            if(midVal>highVal){
                return (low+high)/2;
            }else{
                if(lowVal>highVal){
                    return low;
                }else{
                    return high;
                }
            }
        }else{
            if(highVal>midVal){
                return (low+high)/2;
            }else{
                if(lowVal>highVal){
                    return low;
                }else{
                    return high;
                }
            }
        }
    }

    // divide 역할
//    public static void sort(List<Integer> list) {
//        sort(list, 0, list.size() - 1);
//    }
//
//    private static void sort(List<Integer> list, int low, int high) {
//        if (low >= high) {
//            return;
//        }
//
//        int pivotIdx = partition(list, low, low, high);
//
//        sort(list, low, pivotIdx - 1);
//        sort(list, pivotIdx + 1, high);
//    }

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
