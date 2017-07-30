package app.core.evaluation;

public interface Expr {
    boolean isNumb();
    boolean isSum();
    boolean isMult();
    boolean isSubtraction();
}
