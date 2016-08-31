package sort;

import static Print.Print.print;

/**
 * Created by zyf on 2016/8/31.
 */
public class ShellSort {
    /**
     * 使用希尔排序进行排序
     * 据测试增量需要比希尔排序需要多一个增量,即假如希尔排序期望是三个增量，那么本算法需要多加一个增量
     * @param unsort    乱序的序列
     * @param dk    增量
     * @return  排序之后的序列
     */
    public int[] sort(int[] unsort,int[] dk){
        int length = unsort.length;
        int dklength = dk.length;
        for (int i = 0;i<dklength;i++)
            for (int j= 0;j<length;j++){
                int dks = dk[i];
                if ((j+dks)<length && unsort[j] > unsort[j+dks])
                {
                    int temp = unsort[j];
                    unsort[j] = unsort[j+dks];
                    unsort[j+dks] = temp;
                }
            }
        int[] sort = unsort;
        return sort;
    }
    public static void main(String[] args){
        int[] unsort = {49,38,65,97,76,13,27,49,55,4};
        int[] dk={8,5,3,1};
        int[] sort = new ShellSort().sort(unsort,dk);
        for (int i = 0; i< sort.length;i++)
            print(sort[i]+" ");
    }
}
