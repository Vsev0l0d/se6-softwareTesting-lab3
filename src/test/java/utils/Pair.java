package utils;

public class Pair<T, D> {
    private final T left;
    private final D right;

    public Pair(T left, D right) {
        this.left = left;
        this.right = right;
    }

    public T getLeft() {
        return left;
    }

    public D getRight() {
        return right;
    }

    @Override
    public String toString() {
        return "Pair{" +
                "left=" + left +
                ", right=" + right +
                '}';
    }
}
