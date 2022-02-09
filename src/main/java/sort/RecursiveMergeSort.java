package sort;

import list.ArrayList;
import list.List;

public class RecursiveMergeSort {
    // divid의 역할
    public static void sort(List<Integer> list){
        if(list.size()<2){
            return;
        }

        int mid = list.size()/2;
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        fillList(list, left, 0, mid);
        fillList(list, right, mid, list.size());

        sort(left);
        sort(right);

        merge(left, right, list);
    }

    // conquer(정렬)의 역할
    private static void merge(List<Integer> left, List<Integer> right, List<Integer> res){
        int i = 0, j = 0;

        while(i+j<res.size()){
            if(j>=right.size() || (i<left.size() && left.get(i)<right.get(j))){
                res.set(left.get(i), i+j);
                i++;
            }else{
                res.set(right.get(j), i+j);
                j++;
            }
        }
    }

    private static void fillList(List<Integer> origin, List<Integer> target,
                            int begin, int end){
        for(int i=begin; i<end; i++){
            target.add(origin.get(i));
        }
    }


    public static void print(List<Integer> list){
        for(int i: list){
            System.out.print(i+" ");
        }
    }

    public static void main(String args[]){
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
