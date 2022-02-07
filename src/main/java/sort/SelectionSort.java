package sort;

import list.ArrayList;
import list.List;

public class SelectionSort {
    public static void sort(List<Integer> list){
        for(int i=0; i<list.size(); i++){
            int minIdx = i;

            for(int j=i+1; j<list.size(); j++){
                if(list.get(j)<list.get(minIdx)){
                    minIdx = j;
                }
            }

            if(minIdx!=i){
                int tmp = list.get(i);
                list.set(list.get(minIdx), i);
                list.set(tmp, minIdx);
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

        sort(list);

        System.out.println();
        System.out.println("정렬 후");
        for(int i: list){
            System.out.print(i+" ");
        }

    }
}
