package com.zyf.mytree;

/**
 * Created by zyf on 2016/3/15.
 */
public class SearchBinaryTree {
    public static void firstSearch(BinaryTree binaryTree){
        if (binaryTree.left != null) {
            firstSearch(binaryTree.left);
        }
        System.out.print(binaryTree.element + " ");
        if (binaryTree.left != null) {
            firstSearch(binaryTree.right);
        }
    }
    public static void midSearch(BinaryTree binaryTree){
        System.out.print(binaryTree.element + " ");
        if (binaryTree.left != null) {
            midSearch(binaryTree.left);
        }
        if (binaryTree.right != null) {
            midSearch(binaryTree.right);
        }
    }
    public static void lastSearch(BinaryTree binaryTree){
        if (binaryTree.left != null) {
            lastSearch(binaryTree.left);
        }
        if (binaryTree.right != null) {
            lastSearch(binaryTree.right);
        }
        System.out.print(binaryTree.element + " ");
    }

}
