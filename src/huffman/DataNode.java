package huffman;

/**
 * Created by zyf on 2016/8/28.
 */
public class DataNode {
    public DataNode(String element,double wight) {
        this(element,wight,null,null);
    }
    public DataNode(double wight,DataNode left, DataNode right) {

        this(null,wight,left,right);
    }
    public DataNode(String element,double wight, DataNode left, DataNode right) {
        this.element = element;
        this.wight = wight;
        this.left = left;
        this.right = right;
    }
    String element;
    double wight;
    DataNode left;
    DataNode right;
}
