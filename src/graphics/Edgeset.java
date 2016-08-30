package graphics;

/**
 * Created by zyf on 2016/8/30.
 */

/**
 * 图的边
 */
public class Edgeset {
    public Edgeset(int begin, int end, double wight) {
        this.begin = begin;
        this.end = end;
        this.wight = wight;
    }

    int begin;  //弧的起点
    int end;    //弧的终点
    double wight;   //弧的权值
}
