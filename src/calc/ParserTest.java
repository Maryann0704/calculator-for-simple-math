package calc;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ParserTest {

    private static Parser parser;

    @BeforeClass
    public static void createParser(){
        parser = new Parser();
    }

    @Test (timeout = 30)
    public void additionScalarCalc() throws CalcException {
        Var var = parser.calc("5+2");
        double expected = 7.0;
        double actual = Double.parseDouble(var.toString());
        assertEquals(expected, actual, 1e-6);
        System.out.println("additionScalarCalc() "+ConsoleRunner.manager.getString(Msg.TEST));
    }

    @Test (timeout = 100)
    public void subtractionScalarCalc() throws CalcException {
        Var var = parser.calc("6-1");
        double expected = 5.0;
        double actual = Double.parseDouble(var.toString());
        assertEquals(expected, actual, 1e-6);
        System.out.println("subtractionScalarCalc() "+ConsoleRunner.manager.getString(Msg.TEST));
    }

    @Test (timeout = 100)
    public void multiplicationScalarCalc() throws CalcException {
        Var var = parser.calc("2*8");
        double expected = 16.0;
        double actual = Double.parseDouble(var.toString());
        assertEquals(expected, actual, 1e-6);
        System.out.println("multiplicationScalarCalc() "+ConsoleRunner.manager.getString(Msg.TEST));
    }

    @Test (timeout = 30)
    public void divisionScalarCalc() throws CalcException {
        Var var = parser.calc("2/4");
        double expected = 0.5;
        double actual = Double.parseDouble(var.toString());
        assertEquals(expected, actual, 1e-6);
        System.out.println("divisionScalarCalc() "+ConsoleRunner.manager.getString(Msg.TEST));
    }

    @Test (timeout = 100)
    public void additionVectorScalarCalc() throws CalcException {
        Var var = parser.calc("{2,5}+2");
        String expected = "{4.0, 7.0}";
        String actual = var.toString();
        assertEquals(expected, actual);
        System.out.println("additionVectorScalarCalc() "+ConsoleRunner.manager.getString(Msg.TEST));
    }

    @Test (timeout = 100)
    public void additionVectorCalc() throws CalcException {
        Var var = parser.calc("{2,5}+{2,6}");
        String expected = "{4.0, 11.0}";
        String actual = var.toString();
        assertEquals(expected, actual);
        System.out.println("additionVectorCalc() "+ConsoleRunner.manager.getString(Msg.TEST));
    }

    @Test (timeout = 100)
    public void subtractionVectorScalarCalc() throws CalcException {
        Var var = parser.calc("{6,1}-2");
        String expected = "{4.0, 1.0}";
        String actual = var.toString();
        assertEquals(expected, actual);
        System.out.println("subtractionVectorScalarCalc() "+ConsoleRunner.manager.getString(Msg.TEST));
    }

    @Test (timeout = 100)
    public void subtractionVectorCalc() throws CalcException {
        Var var = parser.calc("{6,1}-{2,1}");
        String expected = "{4.0, 0.0}";
        String actual = var.toString();
        assertEquals(expected, actual);
        System.out.println("subtractionVectorCalc() "+ConsoleRunner.manager.getString(Msg.TEST));
    }

    @Test (timeout = 100)
    public void multiplicationVectorScalarCalc() throws CalcException {
        Var var = parser.calc("{2,8}*4");
        String expected = "{8.0, 32.0}";
        String actual = var.toString();
        assertEquals(expected, actual);
        System.out.println("multiplicationVectorScalarCalc() "+ConsoleRunner.manager.getString(Msg.TEST));
    }
    @Test (timeout = 100)
    public void multiplicationVectorCalc() throws CalcException {
        Var var = parser.calc("{2,8}*{4,3}");
        double expected = 32.0;
        double actual = Double.parseDouble(var.toString());
        assertEquals(expected, actual, 1e-6);
        System.out.println("multiplicationVectorCalc() "+ConsoleRunner.manager.getString(Msg.TEST));
    }

    @Test (timeout = 50)
    public void divisionVectorScalarCalc() throws CalcException {
        Var var = parser.calc("{2,4}/2");
        String expected = "{1.0, 2.0}";
        String actual = var.toString();
        assertEquals(expected, actual);
        System.out.println("divisionVectorScalarCalc() "+ConsoleRunner.manager.getString(Msg.TEST));
    }

    @Test (timeout = 100)
    public void scalarExpressionCalc() throws CalcException {
        Var var = parser.calc("2+2*2/4-1");
        double expected = 2.0;
        double actual = Double.parseDouble(var.toString());
        assertEquals(expected, actual, 1e-6);
        System.out.println("scalarExpressionCalc() "+ConsoleRunner.manager.getString(Msg.TEST));
    }

    @Test (timeout = 100)
    public void additionMatrixScalarCalc() throws CalcException {
        Var var = parser.calc("{{2,3},{4,5}}+4");
        String expected = "{{6.0, 7.0}, {8.0, 9.0}}";
        String actual = var.toString();
        assertEquals(expected, actual);
        System.out.println("additionMatrixScalarCalc() "+ConsoleRunner.manager.getString(Msg.TEST));
    }

    @Test (timeout = 100)
    public void additionMatrixCalc() throws CalcException {
        Var var = parser.calc("{{2,3},{4,5}}+{{3,4},{5,6}}");
        String expected = "{{5.0, 7.0}, {9.0, 11.0}}";
        String actual = var.toString();
        assertEquals(expected, actual);
        System.out.println("additionMatrixCalc() "+ConsoleRunner.manager.getString(Msg.TEST));
    }

    @Test (timeout = 100)
    public void subtractionMatrixScalarCalc() throws CalcException {
        Var var = parser.calc("{{2,3},{4,5}}-4");
        String expected = "{{-2.0, -1.0}, {0.0, 1.0}}";
        String actual = var.toString();
        assertEquals(expected, actual);
        System.out.println("subtractionMatrixScalarCalc() "+ConsoleRunner.manager.getString(Msg.TEST));
    }

    @Test (timeout = 100)
    public void subtractionMatrixCalc() throws CalcException {
        Var var = parser.calc("{{2,3},{4,5}}-{{3,4},{5,6}}");
        String expected = "{{-1.0, -1.0}, {-1.0, -1.0}}";
        String actual = var.toString();
        assertEquals(expected, actual);
        System.out.println("subtractionMatrixCalc() " +ConsoleRunner.manager.getString(Msg.TEST));
    }

    @Test (timeout = 100)
    public void multiplicationMatrixScalarCalc() throws CalcException {
        Var var = parser.calc("{{2,3},{4,5}}*3");
        String expected = "{{6.0, 9.0}, {12.0, 15.0}}";
        String actual = var.toString();
        assertEquals(expected, actual);
        System.out.println("multiplicationMatrixScalarCalc() "+ConsoleRunner.manager.getString(Msg.TEST));
    }

    @Test (timeout = 100)
    public void multiplicationMatrixVectorCalc() throws CalcException {
        Var var = parser.calc("{{6,9},{12,15}}*{2,8}");
        String expected = "{84.0, 144.0}";
        String actual = var.toString();
        assertEquals(expected, actual);
        System.out.println("multiplicationMatrixVectorCalc() "+ConsoleRunner.manager.getString(Msg.TEST));
    }
    @Test (timeout = 100)
    public void multiplicationMatrixCalc() throws CalcException {
        Var var = parser.calc("{{2,3},{4,5}}*{{3,4},{5,6}}");
        String expected = "{{21.0, 26.0}, {37.0, 46.0}}";
        String actual = var.toString();
        assertEquals(expected, actual);
        System.out.println("multiplicationMatrixCalc() "+ConsoleRunner.manager.getString(Msg.TEST));
    }

    @Test (timeout = 100)
    public void vectorExpressionCalc() throws CalcException {
        Var var = parser.calc("{2,3}/(20/2)");
        String expected = "{0.2, 0.3}";
        String actual = var.toString();
        assertEquals(expected, actual);
        System.out.println("vectorExpressionCalc() "+ConsoleRunner.manager.getString(Msg.TEST));
    }

    @Test (timeout = 100)
    public void createVarCalc() throws CalcException {
        parser.calc("A=3+5*2");
        Var var=FactoryVar.createVar("A");
        double expected = 13.0;
        assert var != null;
        double actual = Double.parseDouble(var.toString());
        assertEquals(expected, actual, 1e-6);
        System.out.println("createVarCalc() " +ConsoleRunner.manager.getString(Msg.TEST));
    }

    @Test (timeout = 30)
    public void createAndPrintScalar() throws CalcException {
        Var var=FactoryVar.createVar("-25");
        double expected = -25.0;
        assert var != null;
        double actual = Double.parseDouble(var.toString());
        assertEquals(expected, actual, 1e-6);
        System.out.println("createAndPrintScalar() " +ConsoleRunner.manager.getString(Msg.TEST));
    }

    @Test (timeout = 30)
    public void createAndPrintVector() throws CalcException {
        Var var=FactoryVar.createVar("{2,5,2.5}");
        String expected = "{2.0, 5.0, 2.5}";
        assert var != null;
        String actual = var.toString();
        assertEquals(expected, actual);
        System.out.println("createAndPrintVector() " +ConsoleRunner.manager.getString(Msg.TEST));
    }

    @Test (timeout = 30)
    public void createAndPrintMatrix() throws CalcException {
        Var var=FactoryVar.createVar("{{2,5.2,2.5},{7.1,8,9}}");
        String expected = "{{2.0, 5.2, 2.5}, {7.1, 8.0, 9.0}}";
        assert var != null;
        String actual = var.toString();
        assertEquals(expected, actual);
        System.out.println("createAndPrintMatrix() " +ConsoleRunner.manager.getString(Msg.TEST));
    }
}