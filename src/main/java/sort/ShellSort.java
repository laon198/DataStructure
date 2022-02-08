package sort;

import list.ArrayList;
import list.List;

public class ShellSort {
    public static void sort(List<Integer> list){
        for(int i=list.size()/2; i>0; i/=2){
            sort(list, i);
        }
    }

    private static void sort(List<Integer> list, int gap){
        for(int i=gap; i<list.size(); i++) {
            for(int j=i; j>0; j-=gap){
                if(list.get(j-gap)>list.get(j)){
                    swap(list, j, j-gap);
                }else{
                    break;
                }
            }
        }
    }

    private static void swap(List<Integer> list, int i, int j){
        int tmp = list.get(i);
        list.set(list.get(j), i);
        list.set(tmp, j);
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
        list.add(6);
        list.add(8);
        list.add(10);

        System.out.println("정렬 전");
        print(list);

        sort(list);

        System.out.println();
        System.out.println("정렬 후");
        print(list);

    }
}
