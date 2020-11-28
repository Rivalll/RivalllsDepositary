package deque;

import java.util.LinkedList;

/**
 *  实现算法逻辑：
 *      给定一个数组，[2,6,1,5,8,6]
 *      有一个滑动窗口长度为3，在数组内从左到右滑动
 *      求得窗口每次滑动，窗口内的最大值
 *  提示：双端队列，LinkedList双端队列
 * */
public class Code01_deque {

    public static void main(String[] args){
        int[] var = {2,6,1,5,8,6,10};
        int slider = 3;
        deque(var ,slider);
    }
    //暴力求解法
    private static void violence(int[] var1 , int slide){
        int[] out = new int[var1.length - slide + 1];
        int left  = 0;
        int right = left+slide-1;
        for(int i = left ; i < var1.length - slide + 1 ; i++){
            int max = 0;
            for(int q = i ; q <= right ; q++ ){
                int tmp = var1[q];
                if(tmp > max){
                    max = tmp;
                }
            }
            right++;
            out[i] = max;
        }
        print(out);
    }

    //双端队列
    public static void deque(int[] var1 , int slide){
        LinkedList<Integer> deque = new LinkedList<>();
        int[] result = new int[var1.length - slide + 1];
        int index = 0;
        for(int i = 0; i< var1.length ; i++){
            while (!deque.isEmpty() && var1[deque.peekLast()] <= var1[i]){
                deque.pollLast();
            }
            deque.addLast(i);
            if(deque.peekFirst() == i - slide){
                deque.peekFirst();
            }
            if(i >= slide - 1){
                result[index++] = var1[deque.peekFirst()];
            }
        }
        print(result);
    }



    private static void print(int[] out){
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0 ; i< out.length ; i++){
            stringBuilder.append(out[i]+",");
        }
        String value = stringBuilder.substring(0  , stringBuilder.length()-1);
        System.out.println(value);
    }

}
