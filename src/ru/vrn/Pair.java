package ru.vrn;

/**
 * Represents pair of integers (like apache commons Pair).
 *
 * Created by Roman Velichkin <vrnroman@gmail.com>.
 */
public class Pair {

    private int left;

    private int right;

    public Pair(int left, int right) {
        this.left = left;
        this.right = right;
    }

    public int getLeft() {
        return left;
    }

    public void setLeft(int left) {
        this.left = left;
    }

    public int getRight() {
        return right;
    }

    public void setRight(int right) {
        this.right = right;
    }
}
