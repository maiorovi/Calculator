package app.core.evaluation;

public class Subtract implements Expr {
    private Expr left;
    private Expr right;

    public Subtract(Expr left, Expr right) {
        this.left = left;
        this.right = right;
    }

    public Expr getLeft() {
        return left;
    }

    public Expr getRight() {
        return right;
    }

    @Override
    public boolean isNumb() {
        return false;
    }

    @Override
    public boolean isSum() {
        return false;
    }

    @Override
    public boolean isMult() {
        return false;
    }

    @Override
    public boolean isSubtraction() {
        return true;
    }
}
