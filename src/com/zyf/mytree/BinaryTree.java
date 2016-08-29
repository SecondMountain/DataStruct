package com.zyf.mytree;

/**
 * Created by zyf on 2016/3/14.
 */
public class BinaryTree {
    public BinaryTree(int element) {
        this(element,null,null);
    }

    public BinaryTree(int element, BinaryTree left, BinaryTree right) {
        this.element = element;
        this.left = left;
        this.right = right;
    }
    public BinaryTree makeNewNode(){
        return new BinaryTree(0);
    }
    int element;
    BinaryTree left;
    BinaryTree right;

}
