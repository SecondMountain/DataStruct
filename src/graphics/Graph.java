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
     * 思路：将联通分量的点加入一个集合，未加入联通分量的放在一个集合，只要每次未加入联通分量的集合不为空那么久继续循环寻找点
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
        while (!V.isEmpty()) {      //该集合不为空，说明所有点未被加入联通分量中
            double min=1000;
            int vsize = V.size();
            int usize = U.size();
            //从联通分量中到未联通量找到最小的权值，记录该点
            for (int i = 0; i < usize; i++)
                for (int j = 0; j < vsize; j++)
                    if (graph.point[U.get(i)-1][V.get(j)-1] <= min && graph.point[U.get(i)-1][V.get(j)-1]!=0) {
                        min = graph.point[U.get(i)-1][V.get(j)-1];
                        vex = j;
                    }
            //将该点加入到联通分量，并从未联通分量总删除该点
            println("选取节点："+V.get(vex));
            U.add(V.get(vex));
            V.remove(vex);
        }
        //已经加入所有的联通分量
        println("---------------------最小生成树为------------------------");
        println(U);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void KruskalMinTree(Edgeset[] edgesets){
        Set<Integer> U=new HashSet<>();
        List<Integer> all = new ArrayList<>();
        int min=-2;
        while ((min = findMin(edgesets))!=-1){ //当返回值不为-1的时候结束
            int length=all.size();
            int begin = edgesets[min].begin;
            int end = edgesets[min].end;
            boolean notin=false;
            for (int i = 0;i<length;i++)    //该弧的头结点是否在该联通分量，不在则加入，否则不加
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
        println(all);   //输出该联通分量
    }
    //从边的集合中找到权值最小的，然后返回，若没有则返回-1
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
    //生成边的集合
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
        boolean visit[] = new boolean[length];  //记录是否遍历过
        for (int i = 0;i<length;i++)
            visit[i] = false;   //初始化遍历，均设置为未遍历
        List<Integer> list =new ArrayList<>();
        double sum=0;   //设置路径长度为0
        for (int j= 0;j<length;j++)     //去掉这个for循环就是对单一一个点到其他各点的距离
            if (!visit[j]){     //如果该点未遍历则进行
                boolean[] ano =visit.clone();   //该遍历记录的一个克隆，若不进行克隆那么就是每个点只进行一次遍历了
                alllengthDFS(graph,j,ano,list,sum);     //开始遍历方法
                list.remove(list.size()-1); //加入之后的再移除
            }
    }
    public void alllengthDFS(Graph graph,int vex,boolean[] visit,List<Integer> list,double sum){
        visit[vex] = true;
        list.add(vex+1);
        println(list+"      长度："+sum);  //输出到达该点，以及到该点的路径距离
        int length = graph.point.length;
        for (int i = 0;i< length;i++)
            if (graph.arcCell[vex][i].adj!=0 && !visit[i]){     //若该点未遍历
                boolean[] ano =visit.clone();
                sum += graph.arcCell[vex][i].adj; //计算到该点的距离
                alllengthDFS(graph,i,ano,list,sum);
                list.remove(list.size()-1); //从集合中移除加入过的在上边加入的该点
                sum -= graph.arcCell[vex][i].adj;   //减去上边加上的值，即记性还原sum值，进行下一次的运算
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
        List<List<Integer>> lists =new ArrayList<>();   //记录最短路径的list的list
        double sum=0;
        double[] road = new double[length];
        for (int j= 0;j<length;j++)     //去掉这个for循环就是对单一一个点到其他各点的距离
            if (!visit[j]){
                for (int i =0;i<length;i++)
                    road[i]=9999;   //初始化路径长度
                for (int i =0;i<length;i++)
                    lists.add(new ArrayList<>());   //初始化路径
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
            if (graph.arcCell[vex][i].adj!=0 && !visit[i]){ //若该点的距离不是无限远且没有被遍历则进行
                boolean[] ano =visit.clone();
                sum += graph.arcCell[vex][i].adj;   //计算路径长度

//                new ArrayList<Integer>(list)
                minlengthDFS(graph,i,ano,list,sum,road,lists);
                if (sum<road[i]) {  //若到达该点的路径长度小于已经记录的最小长度，那么更新该值，并更新路径
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
