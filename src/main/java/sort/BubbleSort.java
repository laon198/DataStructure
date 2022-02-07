package sort;

import list.ArrayList;
import list.List;

public class BubbleSort {
    public static void sort(List<Integer> list){
        for(int i=0; i<list.size(); i++){
            for(int j=0; j<list.size()-i-1; j++){
                int left = list.get(j);
                int right = list.get(j+1);

                if(left>right){
                    list.set(left, j+1);
                    list.set(right, j);
                }
            }
        }
    }

    public static void enhancedSort(List<Integer> list){
        boolean isSorted = false;

        for(int i=0; i<list.size(); i++){
            for(int j=0; j<list.size()-i-1; j++){
                isSorted = true;
                int left = list.get(j);
                int right = list.get(j+1);

                if(left>right){
                    isSorted = false;
                    list.set(left, j+1);
                    list.set(right, j);
                }
            }

            if(isSorted){
                break;
            }
        }
    }

    public static void main(String args[]){
        List<Integer> list = new ArrayList<>();
        list.add(5);
        list.add(3);
        list.add(1);
        list.add(4);
        list.add(2);
        list.add(0);

        System.out.println("정렬 전");
        for(int i: list){
            System.out.print(i+" ");
        }

        enhancedSort(list);

        System.out.println();
        System.out.println("정렬 후");
        for(int i: list){
            System.out.print(i+" ");
        }

    }
}