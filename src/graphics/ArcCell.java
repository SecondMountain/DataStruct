package graphics;

/**
 * Created by zyf on 2016/8/29.
 */
public class ArcCell {
    public ArcCell(String info, double adj) {
        this.info = info;
        this.adj = adj;
    }

    public ArcCell(double adj) {
        this.adj = adj;
    }

    double adj;
    String info;
}
