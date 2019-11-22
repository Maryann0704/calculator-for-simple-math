package calc;

abstract class Var {

    public Var add(Var other) throws CalcException {
        throw new CalcException();
    }

    public Var sub(Var other) throws CalcException {
        throw new CalcException();
    }

    public Var mul(Var other) throws CalcException {
        throw new CalcException();
    }

    public Var div(Var other) throws CalcException {
        throw new CalcException();
    }

    public String toString() {
        return ConsoleRunner.manager.getString(Msg.MESSAGE);
    }

}
