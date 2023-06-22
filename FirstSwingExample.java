// calculator, programed by Jovit bro...

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import javax.script.*;

public class FirstSwingExample {
    public static void main(String[] args) {  
        int num1,result;
        String operator;
    
        JFrame frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        frame.setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout());
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(new EmptyBorder(10, 0, 0, 0));
        buttonPanel.setLayout(new GridLayout(5, 4, 5, 5));
        
        JTextField displayField = new JTextField();
        displayField.setEditable(false); 
        displayField.setPreferredSize(new Dimension(250, 30));
        contentPane.add(displayField, BorderLayout.NORTH);

        String[] buttonLabels = {
                    "7", "8", "9", "/",
                    "4", "5", "6", "*",
                    "1", "2", "3", "-",
                    "0", ".", "%", "+",
                    "C","AC","^","="
        };
        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String buttonText = button.getText();
                    if (!("AC".equals(buttonText) || "C".equals(buttonText) || "=".equals(buttonText))) {
                        displayField.setText(displayField.getText() + buttonText);
                    }else if ("AC".equals(buttonText)) {
                        displayField.setText("");
                    }else if ("C".equals(buttonText)) {
                        String currentText = displayField.getText();
                        if (currentText.length() > 0) {
                            displayField.setText(currentText.substring(0, currentText.length() - 1));
                        }
                    }else if ("=".equals(buttonText)) {
                        String expression = displayField.getText();
                        try {
                            ScriptEngineManager manager = new ScriptEngineManager();
                            ScriptEngine engine = manager.getEngineByName("js");
                            Object result = engine.eval(expression);
                            displayField.setText(result.toString());
                       }catch (ScriptException ex) {
                            displayField.setText("Error");
                       }
                   }else {
                   displayField.setText(displayField.getText() + buttonText);
                   }
                }
            });
            buttonPanel.add(button);
        }
        contentPane.add(buttonPanel, BorderLayout.CENTER);

        frame.pack();
        frame.setSize(300,400);
        frame.setVisible(true);
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int centerX = (screenSize.width - frame.getWidth()) / 2;
        int centerY = (screenSize.height - frame.getHeight()) / 2;
        frame.setLocation(centerX, centerY);
        
        frame.setResizable(false);
    }  
}

