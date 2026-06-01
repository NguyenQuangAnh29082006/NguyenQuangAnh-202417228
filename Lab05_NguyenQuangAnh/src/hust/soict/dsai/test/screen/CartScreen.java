package hust.soict.dsai.test.screen;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.media.Media;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import hust.soict.dsai.aims.media.Playable;
import hust.soict.dsai.aims.media.PlayerException;

public class CartScreen extends JFrame {
    private Cart cart;
    private JTable tblMedia;
    private DefaultTableModel tableModel;
    private JLabel lblTotalCost;
    private JButton btnPlay;
    private JButton btnRemove;

    public CartScreen(Cart cart) {
        this.cart = cart;

        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());

        cp.add(createNorth(), BorderLayout.NORTH);
        cp.add(createCenter(), BorderLayout.CENTER);
        cp.add(createRight(), BorderLayout.EAST);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        setTitle("AIMS Shopping Cart Dashboard");
        setSize(1024, 768);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JPanel createNorth() {
        JPanel north = new JPanel();
        north.setLayout(new BoxLayout(north, BoxLayout.Y_AXIS));

        JMenuBar menuBar = new JMenuBar();
        JMenu menuOptions = new JMenu("Options");
        
        //Create the item explicitly as a variable
        JMenuItem mnuViewStore = new JMenuItem("View Store");
        
        //Attach an action listener to close this window when clicked
        mnuViewStore.addActionListener(e -> {
            this.dispose(); 
        });
        
     
        menuOptions.add(mnuViewStore);
        menuBar.add(menuOptions);
        
        
        north.add(menuBar);

        JPanel header = new JPanel(new FlowLayout(FlowLayout.LEFT));
        header.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        JLabel title = new JLabel("CART");
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setForeground(new Color(0, 128, 128)); // Dark Cyan Theme
        header.add(title);
        north.add(header);

        return north;
    }

    private JPanel createCenter() {
        JPanel center = new JPanel(new BorderLayout());
        center.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        String[] columns = {"Title", "Category", "Cost"};
        tableModel = new DefaultTableModel(columns, 0);
        tblMedia = new JTable(tableModel);
        tblMedia.setFont(new Font("Arial", Font.PLAIN, 14));
        tblMedia.setRowHeight(24);

       
        updateTableData();

        JScrollPane scrollPane = new JScrollPane(tblMedia);
        center.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonBar = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnPlay = new JButton("Play");
        btnRemove = new JButton("Remove");

        btnPlay.setVisible(false);
        btnRemove.setVisible(false);

        buttonBar.add(btnPlay);
        buttonBar.add(btnRemove);
        center.add(buttonBar, BorderLayout.SOUTH);

        
        tblMedia.getSelectionModel().addListSelectionListener(e -> {
            int selectedRow = tblMedia.getSelectedRow();
            if (selectedRow >= 0) {
                btnRemove.setVisible(true);
                String title = (String) tblMedia.getValueAt(selectedRow, 0);
                
               
                if (title.equals("The Matrix") || title.equals("Abbey Road")) { 
                    btnPlay.setVisible(true);
                } else {
                    btnPlay.setVisible(false);
                }
                revalidate();
                repaint();
            }
        });

        btnPlay.addActionListener(e -> {
            int selectedRow = tblMedia.getSelectedRow();
            if (selectedRow >= 0) {
                
                String title = (String) tblMedia.getValueAt(selectedRow, 0);
                
               
                Media media = cart.getItemsOrdered().get(selectedRow);
                
                if (media instanceof Playable) {
                    try {
                      
                        ((Playable) media).play();
                        
                        
                        JOptionPane.showMessageDialog(this, "Playing audio/video track media stream: " + title, 
                                                      "AIMS Media Player", JOptionPane.INFORMATION_MESSAGE);
                    } catch (PlayerException ex) {
                        
                        JOptionPane.showMessageDialog(this, ex.getMessage(), 
                                                      "Media Playback Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                 
                    JOptionPane.showMessageDialog(this, "This media item is not playable.", 
                                                  "Playback Warning", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        btnRemove.addActionListener(e -> {
            int selectedRow = tblMedia.getSelectedRow();
            String title = (String) tblMedia.getValueAt(selectedRow, 0);
            
          
            tableModel.removeRow(selectedRow);
            JOptionPane.showMessageDialog(this, title + " removed from catalog selection list.", "Cart Update", JOptionPane.INFORMATION_MESSAGE);
            btnPlay.setVisible(false);
            btnRemove.setVisible(false);
            updateTotalCostMock();
        });

        return center;
    }

    private JPanel createRight() {
        JPanel right = new JPanel();
        right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));
        right.setBorder(BorderFactory.createEmptyBorder(50, 20, 20, 40));

        JPanel costPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel lblTotalText = new JLabel("Total: ");
        lblTotalText.setFont(new Font("Arial", Font.PLAIN, 24));
        
 
        lblTotalCost = new JLabel(String.format("%.2f $", cart.totalCost()));
        lblTotalCost.setFont(new Font("Arial", Font.BOLD, 24));
        lblTotalCost.setForeground(new Color(0, 128, 128));

        costPanel.add(lblTotalText);
        costPanel.add(lblTotalCost);
        right.add(costPanel);

        right.add(Box.createRigidArea(new Dimension(0, 20)));

        JButton btnOrder = new JButton("Place Order");
        btnOrder.setFont(new Font("Arial", Font.BOLD, 20));
        btnOrder.setBackground(new Color(0, 128, 128));
        btnOrder.setForeground(Color.WHITE);
        btnOrder.setOpaque(true);
        btnOrder.setBorderPainted(false);
        btnOrder.setPreferredSize(new Dimension(180, 50));
        btnOrder.setMaximumSize(new Dimension(180, 50));
        btnOrder.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        btnOrder.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Order processed successfully! Thank you.", "Checkout Notification", JOptionPane.INFORMATION_MESSAGE);
            tableModel.setRowCount(0);
            lblTotalCost.setText("0.00 $");
        });
        
        right.add(btnOrder);

        return right;
    }

  
    private void updateTableData() {
        tableModel.setRowCount(0);
        if (cart != null && cart.getItemsOrdered() != null) {
            for (Media media : cart.getItemsOrdered()) {
                tableModel.addRow(new Object[]{
                    media.getTitle(), 
                    media.getCategory(), 
                    media.getCost()
                });
            }
        }
    }

    
    private void updateTotalCostMock() {
        if (cart != null) {
            lblTotalCost.setText(String.format("%.2f $", cart.totalCost()));
        }
    }
}