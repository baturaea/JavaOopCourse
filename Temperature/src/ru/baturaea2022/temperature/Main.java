package ru.baturaea2022.temperature;

import javax.swing.*;
import java.awt.*;

public class Main {
    private static double temperatureConverter(int inputSystem, int outputSystem, double inputTemperature) {
        if (inputSystem == outputSystem) {
            return inputTemperature;
        }

        String selectedSystems = "" + inputSystem + outputSystem;

        return switch (selectedSystems) {
            case "01" -> inputTemperature + 273.15;
            case "02" -> inputTemperature * 1.8 + 32;
            case "10" -> inputTemperature - 273.15;
            case "12" -> inputTemperature * 1.8 - 459.67;
            case "20" -> (inputTemperature + 459.67) / 1.8;
            case "21" -> (inputTemperature - 32) / 1.8;
            default -> 0;
        };
    }

    private static void addItem(JPanel panel, JComponent component, int x, int y) {
        GridBagConstraints gridBag = new GridBagConstraints();
        gridBag.gridx = x;
        gridBag.gridy = y;
        gridBag.insets = new Insets(5, 5, 5, 5);
        gridBag.fill = GridBagConstraints.NONE;
        panel.add(component, gridBag);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {
        }

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Конвертер температур");
            frame.setSize(450, 200);
            frame.setLocationRelativeTo(null);
            frame.setResizable(false);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JPanel panel = new JPanel(new GridBagLayout());
            JButton convertButton = new JButton("Перевод");
            JLabel textField = new JLabel("Результат:");
            JLabel outputField = new JLabel("0");
            JTextField inputField = new JTextField("0", 10);

            String[] str = {"Градус Цельсия", "Кельвин", "Градус Фарингейта"};
            JComboBox<String> inputSystemComboBox = new JComboBox<>(str);
            JComboBox<String> outputSystemComboBox = new JComboBox<>(str);

            addItem(panel, inputSystemComboBox, 0, 0);
            addItem(panel, outputSystemComboBox, 2, 0);
            addItem(panel, inputField, 0, 1);
            addItem(panel, textField, 1, 1);
            addItem(panel, outputField, 2, 1);
            addItem(panel, convertButton, 2, 2);

            frame.add(panel);
            frame.setVisible(true);

            convertButton.addActionListener(e -> {
                String text = inputField.getText();
                try {
                    double inputNumber = Double.parseDouble(text);
                    double result = temperatureConverter(inputSystemComboBox.getSelectedIndex(),
                            outputSystemComboBox.getSelectedIndex(), inputNumber);
                    outputField.setText(String.format("%.2f", result));
                } catch (NumberFormatException exception) {
                    JOptionPane.showMessageDialog(null, "Введено некорретное число.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            });
        });
    }
}