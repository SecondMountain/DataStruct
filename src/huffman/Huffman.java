package huffman;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static Print.Print.println;

/**
 * Created by zyf on 2016/8/28.
 */
public class Huffman {
    /**
     * make a huffman tree by a list
     * @param pool  this is a list
     * @return
     */
    public static DataNode makeHuffMan(List<DataNode> pool){
        int size = pool.size()-1;
        DataNode dataNode;
        DataNode dataNode1;
        DataNode dataNode2;
        for (int i = 0;i<size;i++){
            int min=findMin(pool);
            dataNode1 = pool.get(min);
            pool.remove(min);
            min=findMin(pool);
            dataNode2 = pool.get(min);
            pool.remove(min);
            dataNode = new DataNode(dataNode1.wight+dataNode2.wight,dataNode2,dataNode1);
            pool.add(dataNode);
        }
        dataNode = pool.get(0);
        return dataNode;
    }

    /**
     * find a min wight in list
     * @param pool
     * @return
     */
    public static  int findMin(List<DataNode> pool){
        int size = pool.size();
        int min = 0;
        DataNode dataNode1;
        DataNode dataNode2;
        for (int i = 0;i<size;i++){
            dataNode1=pool.get(i);
            dataNode2=pool.get(min);
            if (dataNode1.wight<dataNode2.wight)
                min=i;
        }
        return min;
    }

    /**
     * print huffman tree that you just made
     * @param dataNode huffman tree you make
     * @param code just  code
     */
    public static void printHuffMan(DataNode dataNode,String code){
        //find node util he doesn't have a child,in this time,this node is we want print
        if (dataNode.left != null) {
            printHuffMan(dataNode.left, code + "0");
            printHuffMan(dataNode.right,code + "1");
        }
        else
            println(code+" : "+dataNode.element);
    }
    public static void main(String[] args){
        Random random = new Random(47);
        List<DataNode> list=new ArrayList<DataNode>();
        //random make ten node and add to a list
        for (int i = 0;i<10;i++){
            DataNode dataNode = new DataNode(String.valueOf(i),random.nextInt(),null,null);
            list.add(dataNode);
        }
        for (int i = 0;i<10;i++){
            DataNode dataNode = list.get(i);
            println(dataNode.element+" : "+dataNode.wight);
        }
        printHuffMan(makeHuffMan(list),"");
    }
}
