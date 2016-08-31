package sort;


import static Print.Print.print;

/**
 * Created by zyf on 2016/8/31.
 */
public class QuickSort {
    public int partion(int[] unsort,int low,int height){
        int privoty = low;      //设置关键字均为数组第一个
        while (low<height){     //当最低指针小于最高指针时执行
            while (low<height && unsort[height]>= unsort[privoty])  //直到直到一个比关键字小的一个，然后交换
                --height;
            int temp = unsort[height];
            unsort[height] = unsort[privoty];
            unsort[privoty] = temp;
            privoty = height;   //记录关键字的位置
            while (low<height && unsort[low]<=unsort[privoty])  //直到找到一个比关键字大的一个，然后交换
                ++low;
            temp = unsort[low];
            unsort[low] = unsort[privoty];
            unsort[privoty] = temp;
            privoty =low;   //关键字的位置
        }
        return privoty; //返回关键字的位置
    }
    public int[] Qsort(int[] sort,int low,int height){
        if (low<height) {
            int partion = partion(sort, low, height);
            Qsort(sort, low, partion - 1);
            Qsort(sort, partion + 1, height);
        }
        return sort;
    }
    public static void main(String[] args){
        int[] unsort={49,38,65,97,76,13,27,49};
        int[] sort=new QuickSort().Qsort(unsort,0,unsort.length-1);
        for (int i:sort){
            print(i+" ");
        }
    }
}
