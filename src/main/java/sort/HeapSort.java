package sort;

import common.Entry;
import common.SimpleEntry;
import list.ArrayList;
import list.List;
import priorityQueue.BinaryHeap;

public class HeapSort {
    public static List<Entry<Integer,Integer>> sort(List<Entry<Integer, Integer>> list){
        List<Entry<Integer,Integer>> res = new ArrayList<>();
        BinaryHeap<Integer, Integer> heap = new BinaryHeap<>(list);
//        BinaryHeap<Integer, Integer> heap = new BinaryHeap<>();
//        for(int i=0; i<list.size(); i++){
//            heap.add(list.get(i).getKey(), list.get(i).getValue());
//        }

        while(!heap.isEmpty()){
            System.out.println("key = " + heap.getMin().getKey());
            res.add(heap.removeMin());
        }

        return res;
    }

    public static void print(List<Entry<Integer,Integer>> list){
        for(Entry<Integer,Integer> e: list){
            System.out.print(e.getKey()+" ");
        }
    }

    public static void main(String args[]){
        List<Entry<Integer, Integer>> list = new ArrayList<>();
        list.add(new SimpleEntry<>(5, 5));
        list.add(new SimpleEntry<>(3, 3));
        list.add(new SimpleEntry<>(1, 1));
        list.add(new SimpleEntry<>(4, 4));
        list.add(new SimpleEntry<>(2, 2));
        list.add(new SimpleEntry<>(7, 7));
        list.add(new SimpleEntry<>(9, 9));
        list.add(new SimpleEntry<>(6, 6));
        list.add(new SimpleEntry<>(8, 8));
        list.add(new SimpleEntry<>(10, 10));

        System.out.println("정렬 전");
        print(list);
        System.out.println();

        List<Entry<Integer,Integer>> res = sort(list);

        System.out.println();
        System.out.println("정렬 후");
        print(res);
    }
}
