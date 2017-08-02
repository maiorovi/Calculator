package app.core.evaluation;

public class Division implements Expr {
    private Expr top;
    private Expr down;

    public Division(Expr top, Expr down) {
        this.top = top;
        this.down = down;
    }

    public Expr getTop() {
        return top;
    }

    public Expr getBottom() {
        return down;
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
        return false;
    }

    @Override
    public boolean isDivision() {
        return true;
    }

    @Override
    public boolean isNegation() {
        return false;
    }

}
