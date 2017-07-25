package app.core.evaluation;

public class ExpressionProcessor {

	public double eval(Expr expr) {
		if (expr instanceof Numb) {
			Numb numb = (Numb) expr;
			return numb.getValue();
		} else if (expr instanceof Sum) {
			return eval(((Sum) expr).getEx1()) + eval(((Sum) expr).getEx2());
		} else if (expr instanceof Mult) {
			return eval( ((Mult) expr).getEx1()) * eval(((Mult) expr).getEx2());
		}

		return 0;
	}
}
