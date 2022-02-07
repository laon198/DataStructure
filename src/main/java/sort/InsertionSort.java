package sort;

import list.ArrayList;
import list.List;

public class InsertionSort {
    //교환하면서 진행하는 방식
    public static void sort(List<Integer> list){
        for(int i=1; i<list.size(); i++) {
            for(int j=i; j>0; j--){
                if(list.get(j-1)>list.get(j)){
                    int tmp = list.get(j);
                    list.set(list.get(j-1), j);
                    list.set(tmp, j-1);
                }else{
                    break;
                }

            }
        }
    }

    //밀어내면서 진행하는 방식
    public static void sort2(List<Integer> list){

        for(int i=1; i<list.size(); i++) {
            int curVal = list.get(i);

            int j = i-1;
            for(; j>=0 && curVal<list.get(j); j--){
                list.set(list.get(j), j+1);
            }

            list.set(curVal, j+1);
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

        sort2(list);

        System.out.println();
        System.out.println("정렬 후");
        for(int i: list){
            System.out.print(i+" ");
        }

    }
}
