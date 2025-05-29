import java.util.Scanner;

public class calculadora {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        math_calc calc = new math_calc();

        System.out.println("=== Calculadora de Derivadas e Integrais ===");
        System.out.println("Exemplos: x^2, sin(x), cos(x), exp(x)");

        while (true) {
            System.out.print("\nDigite uma função (ou 'sair'): ");
            String function = scanner.nextLine();

            if (function.equalsIgnoreCase("sair")) break;

            String derivative = calc.derivate(function);
            String secondDerivative = calc.secondDerivate(function);
            String integral = calc.integrate(function);

            System.out.println("\nResultados:");
            System.out.println("f(x) = " + function);
            System.out.println("f'(x) = " + derivative);
            System.out.println("f''(x) = " + secondDerivative);
            System.out.println("∫f(x)dx = " + integral + " + C");
        }

        scanner.close();
    }
}
