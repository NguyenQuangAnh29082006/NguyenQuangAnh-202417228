package hust.soict.dsai.swing;

import java.awt.*;
import java.awt.event.*;

public class AWTAccumulator extends Frame {
    private TextField tfInput;
    private TextField tfOutput;
    private int sum = 0; 
    
    public AWTAccumulator() {
        
        setLayout(new GridLayout(2, 2));

        add(new Label("Enter an Integer: "));
        tfInput = new TextField(10);
        add(tfInput);

       
        tfInput.addActionListener(new TFInputListener());

        add(new Label("The Accumulated Sum: "));
        tfOutput = new TextField(10);
        tfOutput.setEditable(false); 
        add(tfOutput);

        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        setTitle("AWT Accumulator");
        setSize(350, 120);
        setVisible(true);
    }

    public static void main(String[] args) {
        new AWTAccumulator();
    }

   
    private class TFInputListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent evt) {
            
            int numberIn = Integer.parseInt(tfInput.getText());
            sum += numberIn;
            tfInput.setText(""); 
            tfOutput.setText(sum + ""); 
        }
    }
}