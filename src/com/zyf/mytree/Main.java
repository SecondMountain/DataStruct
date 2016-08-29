package com.zyf.mytree;
import javax.xml.soap.Node;

import static com.zyf.mytree.SearchBinaryTree.*;
/**
 * Created by zyf on 2016/3/1.
 */
public class Main {
    public static void main(String[] args) {
        //TODO write your code here
        BinaryTree root = new BinaryTree(0);
//        System.out.print(root.element);
        root.left = new BinaryTree(1);
        root.right = new BinaryTree(2);
        firstSearch(root);
        midSearch(root);
        lastSearch(root);

    }
}
