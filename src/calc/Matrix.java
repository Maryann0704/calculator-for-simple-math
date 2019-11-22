package calc;

import java.util.concurrent.atomic.AtomicReference;

public class Matrix extends Var {

    private static final String SIZE = ConsoleRunner.manager.getString(Msg.SIZE);

    private double[][] value;

    private Matrix(double[][] value) {
        double[][] value2 = new double[value.length][value[0].length];
        for (int i = 0; i < value2.length; i++)
            System.arraycopy(value[i], 0, value2[i], 0, value2[0].length);
        this.value = value2;
    }

    Matrix(String strMatrix) {
        StringBuilder sb = new StringBuilder(strMatrix);
        for (int i = 0; i < 2; i++) {
            sb.deleteCharAt(0).deleteCharAt(sb.length() - 1);
        }
        String str = sb.toString();
        String[] rowsM = str.split("(},\\s?\\{)");
        String[] colsInRow = rowsM[0].split("[, ]+");

        double[][] resMatrix = new double[rowsM.length][colsInRow.length];
        for (int i = 0; i < rowsM.length; i++) {
            String[] colsM = rowsM[i].split("[, ]+");
            for (int j = 0; j < colsM.length; j++) {
                resMatrix[i][j] = Double.parseDouble(colsM[j]);
            }
        }
        this.value = resMatrix;
    }


    @Override
    public Var add(Var other) throws CalcException {
        Matrix result1 = new Matrix(value);
        if (other.toString().matches(Patterns.SCALAR)) {
            for (int i = 0; i < result1.value.length; i++)
                for (int j = 0; j < result1.value[0].length; j++)
                    result1.value[i][j] = result1.value[i][j] + ((Scalar) other).getValue();
            return result1;
        }
        Matrix result2 = new Matrix(value);
        if (other.toString().matches(Patterns.MATRIX)) {
            for (int i = 0; i < result2.value.length; i++)
                for (int j = 0; j < result2.value[0].length; j++) {
                    if (result2.value[i].length != ((Matrix) other).value[j].length) {
                        throw new CalcException(SIZE);
                    }
                    result2.value[i][j] = result2.value[i][j] + ((Matrix) other).value[i][j];
                }
            return result2;
        }
        return super.add(other);
    }

    @Override
    public Var sub(Var other) throws CalcException {
        Matrix result3 = new Matrix(value);
        if (other.toString().matches(Patterns.SCALAR)) {
            for (int i = 0; i < result3.value.length; i++)
                for (int j = 0; j < result3.value[0].length; j++)
                    result3.value[i][j] = result3.value[i][j] - ((Scalar) other).getValue();
            return result3;
        }

        Matrix result4 = new Matrix(value);
        if (other.toString().matches(Patterns.MATRIX)) {
            for (int i = 0; i < result4.value.length; i++)
                for (int j = 0; j < result4.value[0].length; j++) {
                    if (result4.value[i].length != ((Matrix) other).value[j].length) {
                        throw new CalcException(SIZE);
                    }
                    result4.value[i][j] = result4.value[i][j] - ((Matrix) other).value[i][j];
                }
            return result4;
        }

        return super.sub(other);
    }

    @Override
    public Var mul(Var other) throws CalcException {
        Matrix result5 = new Matrix(value);
        if (other.toString().matches(Patterns.SCALAR)) {
            for (int i = 0; i < result5.value.length; i++)
                for (int j = 0; j < result5.value[0].length; j++)
                    result5.value[i][j] = result5.value[i][j] * ((Scalar) other).getValue();
            return result5;
        }

        Matrix result6 = new Matrix(value);
        if (other.toString().matches(Patterns.VECTOR)) {
            double[] resultVector = new double[value.length];
            for (int i = 0; i < result6.value.length; i++)
                for (int j = 0; j < resultVector.length; j++) {
                    if (result6.value[i].length != ((Vector) other).getValue().length) {
                        throw new CalcException(SIZE);
                    }
                    resultVector[i] += result6.value[i][j] * ((Vector) other).getValue()[j];
                }
            return new Vector(resultVector);
        }

        Matrix result7 = new Matrix(value);
        if (other.toString().matches(Patterns.MATRIX)) {
            double[][] result0 = new double[result7.value.length][((Matrix) other).value[0].length];
            if (result7.value.length != ((Matrix) other).value[0].length) {
                throw new CalcException(SIZE);
            }
            for (int i = 0; i < result7.value.length; i++)
                for (int j = 0; j < ((Matrix) other).value[0].length; j++)
                    for (int k = 0; k < ((Matrix) other).value.length; k++)
                        result0[i][j] += result7.value[i][k] * ((Matrix) other).value[k][j];
            return new Matrix(result0);
        }
        return super.mul(other);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{{");
        AtomicReference<String> delimiter = new AtomicReference<>("");
        for (int i = 0; i < value.length; i++) {
            delimiter.set("");
            for (int j = 0; j < value[0].length; j++) {
                sb.append(delimiter.get()).append(value[i][j]);
                delimiter.set(", ");
            }
            if (i != value.length - 1) sb.append("}, {");
            else sb.append("}}");
        }
        return sb.toString();
    }
}
