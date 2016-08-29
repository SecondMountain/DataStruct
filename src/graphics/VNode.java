package graphics;

/**
 * Created by zyf on 2016/8/29.
 */
public class VNode {
    int element;
    ArcNode firstArc;

    public VNode(int element) {
        this(element,null);
//        this.element = element;
    }

    public VNode(int element, ArcNode firstArc) {
        this.element = element;
        this.firstArc = firstArc;
    }

    static class ArcNode{
        int adjVex;
        double wight;
        ArcNode nextArc;
        String info;

        public ArcNode(int adjVex,double wight) {
            this.adjVex = adjVex;
            this.wight = wight;
//            this.nextArc=null;
        }

        public ArcNode(int adjVex,double wight, ArcNode nextArc) {
            this.adjVex = adjVex;
            this.wight = wight;
            this.nextArc = nextArc;
        }

        public ArcNode(int adjVex,double wight, ArcNode nextArc, String info) {
            this.adjVex = adjVex;
            this.wight = wight;
            this.nextArc = nextArc;
            this.info = info;
        }
    }
}
