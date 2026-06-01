package hust.soict.dsai.swing;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class SwingPainter extends JFrame {
    
    // Track the active drawing state (Pen vs Eraser)
    private JRadioButton radPen;
    private JRadioButton radEraser;
    private CanvasPanel canvas;

    // Internal data structure to save the drawn point coordinates and colors
    private class DrawPoint {
        int x, y, size;
        Color color;
        
        DrawPoint(int x, int y, int size, Color color) {
            this.x = x;
            this.y = y;
            this.size = size;
            this.color = color;
        }
    }

    public SwingPainter() {
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());

        // 1. Create Left Sidebar Layout Panel
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        // 2. Create Tools Radio Box Selection Option
        JPanel panelTools = new JPanel();
        panelTools.setLayout(new BoxLayout(panelTools, BoxLayout.Y_AXIS));
        panelTools.setBorder(BorderFactory.createTitledBorder("Tools"));

        radPen = new JRadioButton("Pen", true);
        radEraser = new JRadioButton("Eraser", false);

        // Group them together so only one tool can be active at a time
        ButtonGroup toolGroup = new ButtonGroup();
        toolGroup.add(radPen);
        toolGroup.add(radEraser);

        panelTools.add(radPen);
        panelTools.add(radEraser);
        sidebar.add(panelTools);
        sidebar.add(Box.createRigidArea(new Dimension(0, 10))); // Spacing

        // 3. Create Clear Button
        JButton btnClear = new JButton("Clear");
        btnClear.setMaximumSize(new Dimension(Short.MAX_VALUE, 30));
        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                canvas.clearCanvas();
            }
        });
        sidebar.add(btnClear);

        cp.add(sidebar, BorderLayout.WEST);

        // 4. Create Center Drawing Canvas Surface
        canvas = new CanvasPanel();
        cp.add(canvas, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Painter (Swing Migration Version)");
        setSize(640, 480);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Inner custom panel component tracking continuous drag paths
    private class CanvasPanel extends JPanel {
        private ArrayList<DrawPoint> points = new ArrayList<>();

        public CanvasPanel() {
            setBackground(Color.WHITE);

            // Add mouse drag adapter motion behavior
            addMouseMotionListener(new MouseMotionAdapter() {
                @Override
                public void mouseDragged(MouseEvent e) {
                    Color currentColor = Color.BLACK;
                    int size = 8;

                    // If eraser tool is checked active, swap paint to match canvas background
                    if (radEraser.isSelected()) {
                        currentColor = Color.WHITE;
                        size = 32; // Larger size brush for erasing efficiently
                    }

                    // Log point position data
                    points.add(new DrawPoint(e.getX(), e.getY(), size, currentColor));
                    repaint(); // Request window redrawing sequence update
                }
            });
        }

        public void clearCanvas() {
            points.clear();
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Redraw every coordinate saved in the sequence trace array
            for (DrawPoint p : points) {
                g2d.setColor(p.color);
                g2d.fillOval(p.x - p.size / 2, p.y - p.size / 2, p.size, p.size);
            }
        }
    }

    public static void main(String[] args) {
        new SwingPainter();
    }
}