package graphics;

import static Print.Print.println;

/**
 * Created by zyf on 2016/8/29.
 * 邻接表存储方法在边比较稀疏的情况下使用
 * 若无向图中有n个顶点，e条边，则它的邻接表需要n个头结点和2e个表节点。在2e<n(n-1)的情况下，即边比较少的情况下，边的信息比较多的情况下
 * 用邻接表比较节省空间
 */
public class Graph_Adjacency_List {
    public static final int MAX_VERTEX_NUM = 20;

    /**
     * 利用一个二维数组初始化链表
     * @param ponit
     * @param arcnum
     */
    public Graph_Adjacency_List(double[][] ponit, int arcnum) {
        this.point = ponit;
        this.arcnum = arcnum;
        this.vexnum = point.length;
        vertices = new VNode[MAX_VERTEX_NUM];
    }
    double[][] point;
    VNode[] vertices;
    int arcnum,vexnum;
    int kind;

    public Graph_Adjacency_List makeGraph_Adjacency_List(Graph_Adjacency_List graph_adjacency_list){
        int length = graph_adjacency_list.point.length;
        for (int i = 0;i<length;i++)
            vertices[i] = new VNode(i+1);
        for (int i = 0;i<length;i++)
            for (int j = 0; j<length;j++){
                if (point[i][j] !=0) { //如果不是0，那么就是两个节点之间有边连接，否则没有边连接
                    VNode.ArcNode arcNode = vertices[i].firstArc; //添加第一条弧
                    if (arcNode != null) //若已经有不少于一条弧了，那么执行下面语句，继续添加一条弧，用if语句也可以，因为只执行一次
                        arcNode = arcNode.nextArc;
                    arcNode = new VNode.ArcNode(j,point[i][j]); //对添加的弧进行初始化，加入顶点和权值
                }
            }
        return graph_adjacency_list;
    }

    /**
     * 深度遍历
     * @param graph_adjacency_list
     */
    public void DFSTraverse(Graph_Adjacency_List graph_adjacency_list){
        int length = graph_adjacency_list.point.length;
        boolean visit[] = new boolean[length];
        for (int i = 0;i<length;i++)
            visit[i] = false;
        for (int j= 0;j<length;j++)
            if (!visit[j])
                visit = DFS(graph_adjacency_list.vertices[j],j,visit); //若没有遍历过则进行遍历

    }

    /**
     * 深度遍历
     * @param vNode
     * @param vex
     * @param visit
     * @return
     */
    public boolean[] DFS(VNode vNode,int vex,boolean[] visit){
        visit[vex] = true;
        println("顶点："+vNode.element);
        /*
        int length = visit.length;
        for (int i = 0;i< length;i++)
            if (!visit[i])
                visit=DFS(vNode,i,visit);
                */
        int n = -1;
        if (vNode.firstArc!=null)
            n=vNode.firstArc.adjVex;    //找到下一个弧所指向的顶点，进行遍历
        if (n!=-1)
            visit = DFS(vNode,n,visit);
        return visit;
    }

    /**
     * 广度遍历
     * @param graph_adjacency_list
     */
    public void WFSTraverse(Graph_Adjacency_List graph_adjacency_list){
        int length = graph_adjacency_list.point.length;
        boolean visit[] = new boolean[length];
        for (int i = 0;i<length;i++)
            visit[i] = false;
        for (int j= 0;j<length;j++)
            if (!visit[j])
                visit = vist(graph_adjacency_list,j,visit); //开始遍历
    }
    public boolean[] vist(Graph_Adjacency_List graph_adjacency_list,int vex,boolean[] visit){
        visit[vex] = true;
        VNode vNode = graph_adjacency_list.vertices[vex];
        println("顶点："+vNode.element);
        VNode.ArcNode arcNode = vNode.firstArc;
        //若该顶点有指向下一个顶点的弧，开始进行遍历
        while (arcNode!=null){
            visit[arcNode.adjVex] = true;
            vNode = graph_adjacency_list.vertices[vex];
            println("顶点："+vNode.element);
            arcNode = vNode.firstArc;   //指向改点的下一条弧
        }
        //该点所有弧广度搜索完毕
        return visit;
    }
    public static void main(String[] args){
        double[][] point={
                {0,5,0,7,0,0},
                {0,0,4,0,0,0},
                {8,0,0,0,0,9},
                {0,0,5,0,0,6},
                {0,0,0,5,0,0},
                {3,0,0,0,1,0}
        };
        double[][] pointG1={
            {0,1,1,0},
            {0,0,0,0},
            {0,0,0,1},
            {1,0,0,0}
        };
        Graph_Adjacency_List graph_adjacency_list =new Graph_Adjacency_List(point,6);
        println("----------deep traverse-------------------------");
        graph_adjacency_list.DFSTraverse(graph_adjacency_list.makeGraph_Adjacency_List(graph_adjacency_list));


        println("----------deep traverse-------------------------");
        Graph_Adjacency_List graph_adjacency_list1 = new Graph_Adjacency_List(pointG1,4);
        graph_adjacency_list1.DFSTraverse(graph_adjacency_list1.makeGraph_Adjacency_List(graph_adjacency_list1));

        println("----------width traverse-------------------------");
        graph_adjacency_list1.WFSTraverse(graph_adjacency_list1.makeGraph_Adjacency_List(graph_adjacency_list1));




    }
}
