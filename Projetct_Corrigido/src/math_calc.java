public class math_calc {

    public String derivate(String function) {
        function = function.toLowerCase().trim();

        switch (function) {
            case "x^2": return "2*x";
            case "x^3": return "3*x^2";
            case "x": return "1";
            case "sin(x)": return "cos(x)";
            case "cos(x)": return "-sin(x)";
            case "exp(x)": return "exp(x)";
            case "1/x": return "-1/x^2";
            case "ln(x)": return "1/x";
            default: return "Função não reconhecida";
        }
    }

    public String secondDerivate(String function) {
        String first = derivate(function);
        return derivate(first);
    }

    public String integrate(String function) {
        function = function.toLowerCase().trim();

        switch (function) {
            case "x^2": return "x^3/3";
            case "x^3": return "x^4/4";
            case "x": return "x^2/2";
            case "1": return "x";
            case "sin(x)": return "-cos(x)";
            case "cos(x)": return "sin(x)";
            case "exp(x)": return "exp(x)";
            case "1/x": return "ln(|x|)";
            default: return "Função não reconhecida";
        }
    }

    public double integrateDefinite(String function, double a, double b) {
        String integral = integrate(function);
        if (integral.equals("Função não reconhecida")) {
            System.out.println("Função não reconhecida para integração.");
            return Double.NaN;
        }

        double upper = evaluate(integral, b);
        double lower = evaluate(integral, a);

        return upper - lower;
    }

    public double evaluate(String expression, double x) {
        expression = expression.replace(" ", "").toLowerCase();

        try {
            switch (expression) {
                case "x": return x;
                case "x^2": return Math.pow(x, 2);
                case "x^3": return Math.pow(x, 3);
                case "x^3/3": return Math.pow(x, 3) / 3;
                case "x^4/4": return Math.pow(x, 4) / 4;
                case "x^2/2": return Math.pow(x, 2) / 2;
                case "-cos(x)": return -Math.cos(x);
                case "cos(x)": return Math.cos(x);
                case "sin(x)": return Math.sin(x);
                case "exp(x)": return Math.exp(x);
                case "ln(|x|)": return Math.log(Math.abs(x));
                default:
                    System.out.println("Expressão não reconhecida: " + expression);
                    return Double.NaN;
            }
        } catch (Exception e) {
            System.out.println("Erro ao avaliar a expressão: " + expression);
            return Double.NaN;
        }
    }
}

