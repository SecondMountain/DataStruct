package graphics;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    /**
     * 使用普利姆算法，算出最小生成树
     */
    public void minTree(Graph graph,int point){
        List<Integer> V = new ArrayList<>();    //原图中所有的点
        List<Integer> U = new ArrayList<>();//构成的联通分量
        int length = graph.point.length;
        for (int i = 0;i< length;i++)
            V.add(i+1);
        U.add(V.get(point-1));
        V.remove(point-1);
        println("初始选取节点为："+U.get(0));
        int vex=-1;
        while (!V.isEmpty()) {
            double min=1000;
            int vsize = V.size();
            int usize = U.size();
            for (int i = 0; i < usize; i++)
                for (int j = 0; j < vsize; j++)
                    if (graph.point[U.get(i)-1][V.get(j)-1] <= min && graph.point[U.get(i)-1][V.get(j)-1]!=0) {
                        min = graph.point[U.get(i)-1][V.get(j)-1];
                        vex = j;
                    }
            println("选取节点："+V.get(vex));
            U.add(V.get(vex));
            V.remove(vex);
        }
        println("---------------------最小生成树为------------------------");
        println(U);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void KruskalMinTree(Edgeset[] edgesets){
        Set<Integer> U=new HashSet<>();
        List<Integer> all = new ArrayList<>();
        int min=-2;
        while ((min = findMin(edgesets))!=-1){
            int length=all.size();
            int begin = edgesets[min].begin;
            int end = edgesets[min].end;
            boolean notin=false;
            for (int i = 0;i<length;i++)
                if (begin==all.get(i))
                    notin=true;
            if (!notin)
                all.add(begin);
            for (int i = 0;i<length;i++)
                if (end==all.get(i))
                    notin=true;
            if (!notin)
                all.add(end);
//            U.add(edgesets[min].begin);
//            U.add(edgesets[min].end);
            edgesets[findMin(edgesets)]=null;
        }
        println(all);
    }
    public int findMin(Edgeset[] edgesets){
        int length = edgesets.length;
        if (length<=0)
            return -1;
        double min=1000;
        int which=-1;
        for (int i = 0;i<length;i++)
            if (edgesets[i]!=null && min>edgesets[i].wight)
            {   min=edgesets[i].wight;
                which = i;}
        return which;
    }
    public Edgeset[] edgesets(Graph graph){
        int edge = graph.arcnum;
        Edgeset[] edgesets = new Edgeset[edge];
        int length = graph.point.length;
        int k=-1;
        for (int i =0;i<length;i++)
            for (int j = 0;j<length;j++)
                if (j<i&&graph.point[i][j]!=0)
                    edgesets[++k]=new Edgeset(i,j,graph.point[i][j]);
        return edgesets;
    }

    /**
     * 所有路径的查找已经路径长度
     */
    public void alllength(Graph graph,int vex){
        int length = graph.point.length;
        boolean visit[] = new boolean[length];
        for (int i = 0;i<length;i++)
            visit[i] = false;
        List<Integer> list =new ArrayList<>();
        double sum=0;
        for (int j= 0;j<length;j++)     //去掉这个for循环就是对单一一个点到其他各点的距离
            if (!visit[j]){
                boolean[] ano =visit.clone();
                alllengthDFS(graph,j,ano,list,sum);
                list.remove(list.size()-1);
            }
    }
    public void alllengthDFS(Graph graph,int vex,boolean[] visit,List<Integer> list,double sum){
        visit[vex] = true;
        list.add(vex+1);
        println(list+"      长度："+sum);
        int length = graph.point.length;
        for (int i = 0;i< length;i++)
            if (graph.arcCell[vex][i].adj!=0 && !visit[i]){
                boolean[] ano =visit.clone();
                sum += graph.arcCell[vex][i].adj;
                alllengthDFS(graph,i,ano,list,sum);
                list.remove(list.size()-1);
                sum -= graph.arcCell[vex][i].adj;
            }
    }
    /*
    end/////////////////////////////////////////////
     */

    /**
     *一个点到其他点的最短路径，均为按权值来搜索。若权值均为一呢？？？
     * @param graph         图
     * @param vex       初始顶点
     */
    public void minlength(Graph graph,int vex){
        int length = graph.point.length;
        boolean visit[] = new boolean[length];
        for (int i = 0;i<length;i++)
            visit[i] = false;
        List<Integer> list =new ArrayList<>();
        List<List<Integer>> lists =new ArrayList<>();
        double sum=0;
        double[] road = new double[length];
        for (int j= 0;j<length;j++)     //去掉这个for循环就是对单一一个点到其他各点的距离
            if (!visit[j]){
                for (int i =0;i<length;i++)
                    road[i]=9999;

                for (int i =0;i<length;i++)
                    lists.add(new ArrayList<>());
                boolean[] ano =visit.clone();
                road[j]=0;      //修改j
                minlengthDFS(graph,j,ano,list,sum,road,lists); //修改j
                list.remove(list.size()-1);
                for (int i =0;i<length;i++)
                    println("顶点"+(j+1)+"到顶点 "+(i+1)+" 的最短路径为："+road[i]+"---路径为："+lists.get(i));  //修改j
                println();

            }
    }
    public void minlengthDFS(Graph graph,int vex,boolean[] visit,List<Integer> list,double sum,double[] road,List<List<Integer>> lists){
        visit[vex] = true;
        list.add(vex+1);
        println(list+"      长度："+sum);
        int length = graph.point.length;
        for (int i = 0;i< length;i++)
            if (graph.arcCell[vex][i].adj!=0 && !visit[i]){
                boolean[] ano =visit.clone();
                sum += graph.arcCell[vex][i].adj;

//                new ArrayList<Integer>(list)
                minlengthDFS(graph,i,ano,list,sum,road,lists);
                if (sum<road[i]) {
                    road[i] = sum;
                    lists.set(i,new ArrayList<>(list));
//                    println(lists.get(i));
                }
                list.remove(list.size()-1);
                sum -= graph.arcCell[vex][i].adj;
            }

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
        println("----------min Tree-----------------------------");
        double[][] points={
                {0,6,1,5,0,0},
                {6,0,5,0,3,0},
                {1,5,0,5,6,4},
                {5,0,5,0,0,2},
                {0,3,6,0,0,6},
                {0,0,4,2,6,0}
        };
        Graph graph1 = new Graph(points,6,10,1);
        graph1.minTree(graph1.makeGraph(graph1),1);

        println("--------------Kruskal minTree-------------------------");
        graph1.KruskalMinTree(graph1.edgesets(graph1));

        println("------------------------所有路径--------------------------------");
//        graph.alllength(graph.makeGraph(graph),0);

        println("------------------------路径--------------------------------");
        graph.minlength(graph.makeGraph(graph),0);

    }
}
