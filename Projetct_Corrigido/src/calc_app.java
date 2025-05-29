import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class calc_app extends Application {

    private final math_calc calculator = new math_calc();

    @Override
    public void start(Stage primaryStage) {
        Label title = new Label("Calculadora de Derivadas e Integrais");
        title.setFont(new Font("Arial", 24));
        title.setTextFill(Color.web("#FFFFFF"));

        TextField inputField = new TextField();
        inputField.setPromptText("Digite uma função, ex: x^2 ou sin(x)");
        inputField.setPrefHeight(40);
        inputField.setStyle("-fx-background-color: #2a2a2a; -fx-text-fill: white; -fx-prompt-text-fill: #777; -fx-border-color: #0; -fx-border-radius: 8; -fx-background-radius: 8;");

        ComboBox<String> operationBox = new ComboBox<>();
        operationBox.getItems().addAll("Derivada", "Derivada Segunda Ordem", "Integral Indefinida", "Integral Definida");
        operationBox.setPromptText("Escolha a operação");
        operationBox.setPrefHeight(40);
        operationBox.setStyle("-fx-background-color: #2a2a2a; -fx-text-fill: white; -fx-prompt-text-fill: #777; -fx-border-color: #0; -fx-border-radius: 8; -fx-background-radius: 8;");

        TextField lowerLimitField = new TextField();
        lowerLimitField.setPromptText("Limite Inferior (a)");
        lowerLimitField.setPrefHeight(30);
        lowerLimitField.setVisible(false);
        lowerLimitField.setStyle("-fx-background-color: #2a2a2a; -fx-text-fill: white; -fx-prompt-text-fill: #777; -fx-border-color: #555; -fx-border-radius: 8; -fx-background-radius: 8;");

        TextField upperLimitField = new TextField();
        upperLimitField.setPromptText("Limite Superior (b)");
        upperLimitField.setPrefHeight(30);
        upperLimitField.setVisible(false);
        upperLimitField.setStyle("-fx-background-color: #2a2a2a; -fx-text-fill: white; -fx-prompt-text-fill: #777; -fx-border-color: #555; -fx-border-radius: 8; -fx-background-radius: 8;");

        Button calculateButton = new Button("Calcular");
        calculateButton.setPrefHeight(50);
        calculateButton.setDefaultButton(true);
        calculateButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-background-radius: 8; -fx-cursor: hand;");

        Button clearButton = new Button("Limpar");
        clearButton.setPrefHeight(50);
        clearButton.setStyle("-fx-background-color: #F44336; -fx-text-fill: white; -fx-background-radius: 8; -fx-cursor: hand;");

        TextArea resultArea = new TextArea();
        resultArea.setEditable(false);
        resultArea.setWrapText(true);
        resultArea.setPrefHeight(200);
        resultArea.setStyle("-fx-control-inner-background: #2a2a2a; -fx-text-fill: white; -fx-border-color: #0; -fx-border-radius: 8; -fx-background-radius: 8;");

        operationBox.setOnAction(e -> {
            String operation = operationBox.getValue();
            if ("Integral Definida".equals(operation)) {
                lowerLimitField.setVisible(true);
                upperLimitField.setVisible(true);
            } else {
                lowerLimitField.setVisible(false);
                upperLimitField.setVisible(false);
            }
        });

        calculateButton.setOnAction(e -> {
            String input = inputField.getText();
            String operation = operationBox.getValue();
            if (input == null || input.isEmpty() || operation == null) {
                resultArea.setText("Por favor, insira a função e selecione a operação.");
                return;
            }

            switch (operation) {
                case "Derivada":
                    resultArea.setText("Derivada: " + calculator.derivate(input));
                    break;
                case "Derivada Segunda Ordem":
                    resultArea.setText("Derivada de Segunda Ordem: " + calculator.secondDerivate(input));
                    break;
                case "Integral Indefinida":
                    resultArea.setText("Integral Indefinida: " + calculator.integrate(input));
                    break;
                case "Integral Definida":
                    String lowerStr = lowerLimitField.getText();
                    String upperStr = upperLimitField.getText();
                    if (lowerStr.isEmpty() || upperStr.isEmpty()) {
                        resultArea.setText("Por favor, insira os limites inferior e superior.");
                        return;
                    }
                    try {
                        double lower = Double.parseDouble(lowerStr);
                        double upper = Double.parseDouble(upperStr);
                        double result = calculator.integrateDefinite(input, lower, upper);
                        resultArea.setText("Integral Definida de " + input + " de " + lower + " até " + upper + " = " + result);
                    } catch (NumberFormatException ex) {
                        resultArea.setText("Os limites devem ser números válidos.");
                    }
                    break;
                default:
                    resultArea.setText("Operação inválida.");
            }
        });

        clearButton.setOnAction(e -> {
            inputField.clear();
            operationBox.getSelectionModel().clearSelection();
            resultArea.clear();
            lowerLimitField.clear();
            upperLimitField.clear();
            lowerLimitField.setVisible(false);
            upperLimitField.setVisible(false);
        });

        VBox layout = new VBox(10, title, resultArea, inputField, operationBox, lowerLimitField, upperLimitField, calculateButton, clearButton);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(8));
        layout.setStyle("-fx-background-color: #1e1e1e;");

        Scene scene = new Scene(layout, 520, 520);
        primaryStage.setTitle("Calculadora Simbólica - Dark Mode");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

