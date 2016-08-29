package graphics;

import java.util.ArrayList;
import java.util.List;

import static Print.Print.println;

/**
 * Created by zyf on 2016/8/29.
 */

/**
 * 矩阵存储法，存储图
 */
public class Graph {
    public static int MAX_VERTEX_NUM=20;

    public Graph(double[][] ponit,int vexnum, int arcnum, int kind) {
        if (kind<1||kind>40)
            return;
        this.point = ponit;
        this.vexnum = vexnum;
        this.arcnum = arcnum;
        this.kind = kind;
        vexs = new int[MAX_VERTEX_NUM];
        arcCell = new ArcCell[MAX_VERTEX_NUM][MAX_VERTEX_NUM];
    }

    int[] vexs;
    ArcCell[][] arcCell;
    double[][] point;
    int vexnum,arcnum;
    int kind;
    public Graph makeGraph(Graph graph){
        int length = graph.point.length;
        for (int i = 0; i< length ;i++)
            graph.vexs[i] = i+1;
//        Random random =new  Random();
//        Random random1 = new Random(47);
        if (graph.point.length<1||graph.point.length>20) {
            println("point is null! error");
            return null;
        }
        for (int i=0;i<length;i++)
            for (int j=0;j<length;j++)
                graph.arcCell[i][j]=new ArcCell(point[i][j]);
        return graph;
    }
    public void DFSTraverse(Graph graph){
        int length = graph.point.length;
        boolean visit[] = new boolean[length];
        for (int i = 0;i<length;i++)
            visit[i] = false;
        for (int j= 0;j<length;j++)
            if (!visit[j])
                visit = DFS(graph,j,visit);

    }
    public boolean[] DFS(Graph graph,int vex,boolean[] visit){
        visit[vex] = true;
        println("顶点："+graph.vexs[vex]);
        int length = graph.point.length;
        for (int i = 0;i< length;i++)
            if (graph.arcCell[vex][i].adj!=0 && !visit[i])
                visit=DFS(graph,i,visit);
        return visit;
    }
    public void WFSTraverse(Graph graph){
        int length = graph.point.length;
        boolean visit[] = new boolean[length];
        for (int i = 0;i<length;i++)
            visit[i] = false;
        List<Integer> vexs = new ArrayList<Integer>();
        for (int j= 0;j<length;j++) {
            if (!visit[j]) {
                vexs.add(j);
                for (int i = 0;i< length;i++)
                    if (graph.arcCell[j][i].adj!=0 && !visit[i])
                        vexs.add(i);
                while (!vexs.isEmpty()){
                    int v = vexs.get(0);
                    vexs.remove(0);
                    visit = visit(graph,v,visit);
                }
            }
        }
    }
    public boolean[] visit(Graph graph,int vex,boolean[] visit){
        visit[vex] = true;
        println("顶点："+graph.vexs[vex]);
        return visit;
    }

    public static void main(String[] args){
        double[][] point={
                {0,5,0,7,0,0},
                {0,0,4,0,0,0},
                {8,0,0,0,0,9,},
                {0,0,5,0,0,6},
                {0,0,0,5,0,0},
                {3,0,0,0,1,0}
        };
        Graph graph =new Graph(point,6,10,1);
        println("----------deep traverse-------------------------");
        graph.DFSTraverse(graph.makeGraph(graph));
        println("----------wide traverse-------------------------");
        graph.WFSTraverse(graph.makeGraph(graph));
    }
}
