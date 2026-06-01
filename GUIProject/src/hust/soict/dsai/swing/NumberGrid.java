package hust.soict.dsai.swing;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class NumberGrid extends JFrame {
    private JButton[] btnNumbers = new JButton[10];
    private JButton btnDelete, btnReset;
    private JTextField tfDisplay;

    public NumberGrid() {
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());

       
     
        tfDisplay = new JTextField(2); 
        tfDisplay.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        tfDisplay.setFont(new Font("Arial", Font.BOLD, 22)); 
        tfDisplay.setPreferredSize(new Dimension(250, 40));  
        cp.add(tfDisplay, BorderLayout.NORTH);

        // Keypad grid sub-panel (4 rows x 3 columns)
        JPanel panelButtons = new JPanel(new GridLayout(4, 3, 5, 5));

        
        ButtonListener btnListener = new ButtonListener();

        // Create and add buttons 1 to 9
        for (int i = 1; i <= 9; i++) {
            btnNumbers[i] = new JButton("" + i);
            panelButtons.add(btnNumbers[i]);
            btnNumbers[i].addActionListener(btnListener);
        }

        // Bottom row: DEL, 0, C
        btnDelete = new JButton("DEL");
        panelButtons.add(btnDelete);
        btnDelete.addActionListener(btnListener);

        btnNumbers[0] = new JButton("0");
        panelButtons.add(btnNumbers[0]);
        btnNumbers[0].addActionListener(btnListener);

        btnReset = new JButton("C");
        panelButtons.add(btnReset);
        btnReset.addActionListener(btnListener);

        cp.add(panelButtons, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Number Grid");
        setSize(250, 300);
        setVisible(true);
    }

    public static void main(String[] args) {
        new NumberGrid();
    }

    
    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String buttonLabel = e.getActionCommand();
            
            if (buttonLabel.equals("C")) {
           
                tfDisplay.setText("");
            } 
            else if (buttonLabel.equals("DEL")) {
               
                String currentText = tfDisplay.getText();
                if (currentText.length() > 0) {
                    tfDisplay.setText(currentText.substring(0, currentText.length() - 1));
                }
            } 
            else {
              
                tfDisplay.setText(tfDisplay.getText() + buttonLabel);
            }
        }
    }
}