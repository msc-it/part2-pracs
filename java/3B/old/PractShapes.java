import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import javax.swing.*;

import javax.swing.border.*;
import javax.swing.event.*;

/*
<applet code="PractShapes" width=1015 height=660> </applet>
*/

public class PractShapes extends JApplet implements ActionListener, ChangeListener {

    Container cont;
    float v = 1;

    JButton btCircle, btSquare, btRect, btElip, btPoly;
    JPanel p, ptop, sliderPanel;

    String shapeType = "";

    int radius, x, y, width, height, num;
    int[] xPoints, yPoints;

    JSlider slider;
    JCheckBox chkGrad;

    JRadioButton rbtDraw, rbtFill;
    ButtonGroup bg;

    JComboBox cboX1, cboY1, cboX2, cboY2, cboCol1, cboCol2;
    Color[] colors = { Color.black, Color.blue, Color.cyan, Color.darkGray, Color.lightGray, Color.gray, Color.green,
            Color.magenta, Color.orange, Color.pink, Color.red,

            Color.yellow };

    String[] strColors = { "Black", "Blue", "Cyan", "Dark Gray", "Light Gray", "Gray", "Green", "Magenta", "Orange",
            "Pink", "Red", "Yellow" };

    public void init() {

        cont = getContentPane();

        ptop = new JPanel();
        ptop.setLayout(new FlowLayout());

        chkGrad = new JCheckBox("Gradient");
        chkGrad.addActionListener(this);
        ptop.add(chkGrad);

        cboX1 = new JComboBox();
        cboY1 = new JComboBox();

        cboX2 = new JComboBox();
        cboY2 = new JComboBox();

        cboX1.setMaximumRowCount(30);
        cboY1.setMaximumRowCount(30);

        cboX2.setMaximumRowCount(30);
        cboY2.setMaximumRowCount(30);

        cboX1.addActionListener(this);
        cboY1.addActionListener(this);

        cboX2.addActionListener(this);
        cboY2.addActionListener(this);

        for (int i = 0; i < 300; i++) {

            cboX1.addItem("" + i);
            cboY1.addItem("" + i);

            cboX2.addItem("" + i);
            cboY2.addItem("" + i);

        }

        cboCol1 = new JComboBox();
        cboCol2 = new JComboBox();

        cboCol1.setMaximumRowCount(strColors.length);
        cboCol2.setMaximumRowCount(strColors.length);

        cboCol1.addActionListener(this);
        cboCol2.addActionListener(this);

        for (String s : strColors)
            cboCol1.addItem(s);

        for (String s : strColors)
            cboCol2.addItem(s);

        ptop.add(new JLabel("x1 : "));
        ptop.add(cboX1);
        ptop.add(new JLabel("y1 : "));
        ptop.add(cboY1);
        ptop.add(new JLabel("x2 : "));
        ptop.add(cboX2);
        ptop.add(new JLabel("y1 : "));
        ptop.add(cboY2);

        ptop.add(new JLabel("Color 1"));
        ptop.add(cboCol1);
        ptop.add(new JLabel("Color 2"));
        ptop.add(cboCol2);

        cont.add(ptop, BorderLayout.NORTH);

        slider = new JSlider(0, 100);

        slider.setMajorTickSpacing(5);
        slider.setMinorTickSpacing(1);

        slider.setPaintTicks(true);
        slider.setPaintLabels(true);

        slider.setValue(100);
        slider.setOrientation(1);

        slider.addChangeListener(this);

        btCircle = new JButton("Circle");
        btSquare = new JButton("Square");
        btRect = new JButton("Rectangle");
        btElip = new JButton("Ellipse");
        btPoly = new JButton("Polygon");

        btCircle.addActionListener(this);
        btSquare.addActionListener(this);

        btRect.addActionListener(this);
        btElip.addActionListener(this);

        btPoly.addActionListener(this);

        p = new JPanel();
        p.setLayout(new FlowLayout());

        rbtDraw = new JRadioButton("Draw");
        rbtDraw.setSelected(true);

        rbtFill = new JRadioButton("Fill");

        rbtDraw.addActionListener(this);

        rbtFill.addActionListener(this);

        rbtDraw.setOpaque(true);
        bg = new ButtonGroup();
        bg.add(rbtDraw);
        bg.add(rbtFill);

        p.add(rbtDraw);
        p.add(rbtFill);

        p.add(btCircle);
        p.add(btSquare);

        p.add(btRect);
        p.add(btElip);

        p.add(btPoly);
        cont.add(slider, BorderLayout.EAST);

        cont.add(p, BorderLayout.SOUTH);
        comboState(false);

        slider.setPreferredSize(new Dimension(100, 400));
        slider.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.black, 1), "Transperancy",
                TitledBorder.CENTER, TitledBorder.BELOW_TOP,

                new Font("Arial", Font.BOLD, 12), Color.red));

        ptop.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.black, 1), "Gradient Options",
                TitledBorder.CENTER, TitledBorder.BELOW_TOP,

                new Font("Arial", Font.BOLD, 12), Color.red));

        p.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.black, 1), "Drawing Options",
                TitledBorder.CENTER, TitledBorder.BELOW_TOP, new Font("Arial", Font.BOLD, 12), Color.red));

        JLabel lblBack = new JLabel("Drawing Area", JLabel.CENTER);
        lblBack.setFont(new Font("Arial", Font.BOLD, 80));
        cont.add(lblBack, BorderLayout.CENTER);

        lblBack.setBorder(new LineBorder(Color.green, 5));

        p.setBackground(Color.orange);
        ptop.setBackground(Color.orange);

        slider.setBackground(Color.orange);
        lblBack.setForeground(Color.orange);

    }

    public void stateChanged(ChangeEvent ae) {

        v = ((Float.parseFloat("" + slider.getValue()) / 100));
        repaint();

    }

    public void comboState(boolean state)

    {
        cboX1.setEnabled(state);

        cboY1.setEnabled(state);
        cboX2.setEnabled(state);

        cboY2.setEnabled(state);

        cboCol1.setEnabled(state);

        cboCol2.setEnabled(state);
    }

    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == cboX1) {
            repaint();
        }

        if (ae.getSource() == cboY1) {
            repaint();
        }

        if (ae.getSource() == cboX2) {
            repaint();
        }

        if (ae.getSource() == cboY2) {
            repaint();
        }

        if (ae.getSource() == cboCol1) {
            repaint();
        }

        if (ae.getSource() == cboCol2) {
            repaint();
        }

        if (ae.getSource() == chkGrad) {

            if (chkGrad.isSelected()) {

                comboState(true);
            }

            else {

                comboState(false);
            }

            repaint();
        }

        if (ae.getSource() == rbtDraw) {

            repaint();

        }

        if (ae.getSource() == rbtFill) {

            repaint();
        }

        if (ae.getSource() == btCircle) {

            int[] values = new int[1];
            values[0] = Integer.parseInt(JOptionPane.showInputDialog("Enter the radius :"));
            drawShapes("Circle", values);

        }

        if (ae.getSource() == btSquare) {
            ShapeDialog sd = new ShapeDialog(this, "Square");
        }

        if (ae.getSource() == btRect) {
            ShapeDialog sd = new ShapeDialog(this, "Rectangle");
        }

        if (ae.getSource() == btElip)

        {
            ShapeDialog sd = new ShapeDialog(this, "Ellipse");
        }

        if (ae.getSource() == btPoly) {

            //num=Integer.parseInt(JOptionPane.showInputDialog("Enter number of points to draw a polygon :"));

            ShapeDialog sd = new ShapeDialog(this, "Polygon");
            num = sd.getPolyNum();

        }
    }

    public void drawPolyShape(int[] xValues, int[] yValues) {

        shapeType = "Polygon";

        xPoints = xValues;

        yPoints = yValues;
        repaint();

    }

    public void drawShapes(String typeOfShape, int[] values) {

        shapeType = typeOfShape;

        if (typeOfShape.equals("Circle")) {

            radius = values[0];
            repaint();

        }

        if (typeOfShape.equals("Square"))

        {
            x = values[0];

            y = values[1];
            width = height = values[2];

            repaint();
        }

        if (typeOfShape.equals("Rectangle") || typeOfShape.equals("Ellipse")) {

            x = values[0];
            y = values[1];
            width = values[2];

            height = values[3];
            repaint();

        }
    }

    public void paint(Graphics g1) {

        Color c1, c2;
        c1 = colors[cboCol1.getSelectedIndex()];
        c2 = colors[cboCol2.getSelectedIndex()];

        int x1, y1, x2, y2;

        x1 = Integer.parseInt(cboX1.getSelectedItem().toString());
        y1 = Integer.parseInt(cboY1.getSelectedItem().toString());

        x2 = Integer.parseInt(cboX2.getSelectedItem().toString());
        y2 = Integer.parseInt(cboY2.getSelectedItem().toString());

        Graphics ge = cont.getGraphics();
        cont.paint(ge);
        
        Graphics2D g = (Graphics2D) g1;
        GradientPaint gradient = new GradientPaint(x1, y1, c1, x2, y2, c2, true);

        // g.setStroke(stroke);

        g.setColor(Color.black);
        g.setFont(new Font("Arial Narrow", Font.BOLD, 20));
        g.setFont(new Font("Arial", Font.BOLD, 14));

        if (shapeType.equals("Circle")) {
            Ellipse2D c2d = new Ellipse2D.Double((getWidth() / 2) - (radius / 2), (getHeight() / 2) - (radius / 2),
                    radius, radius);
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, v));
            if (rbtFill.isSelected())
            {
                if (chkGrad.isSelected())
                {
                    g.setPaint(gradient);
                } else {
                    g.setPaint(null);
                }
                g.fill(c2d);
            }
            else
            {
                g.draw(c2d);
            }
            g.setPaintMode();
        }

        if (shapeType.equals("Square")) {
            Rectangle2D s2d = new Rectangle2D.Double(x, y, width, height);
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, v));
            if (rbtFill.isSelected()) {
                if (chkGrad.isSelected())
                {
                    g.setPaint(gradient);
                } else {
                    g.setPaint(null);
                }
                g.fill(s2d);
            }
            else {
                g.draw(s2d);
            }
            g.setPaintMode();
        }

        if (shapeType.equals("Rectangle")) {
            Rectangle2D r2d = new Rectangle2D.Double(x, y, width, height);
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, v));
            if (rbtFill.isSelected()) {
                if (chkGrad.isSelected())
                {
                    g.setPaint(gradient);
                } else {
                    g.setPaint(null);
                }
                g.fill(r2d);
            }
            else {
                g.draw(r2d);
            }
            g.setPaintMode();
        }

        if (shapeType.equals("Ellipse")) {
            Ellipse2D e2d = new Ellipse2D.Double(x, y, width, height);
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, v));
            if (rbtFill.isSelected()) {
                if (chkGrad.isSelected())
                {
                    g.setPaint(gradient);
                } else {
                    g.setPaint(null);
                }
                g.fill(e2d);
            }
            else {
                g.draw(e2d);
            }
            g.setPaintMode();
        }

        if (shapeType.equals("Polygon")) {
            GeneralPath path = new GeneralPath();
            path.moveTo(xPoints[0], yPoints[0]);

            for (int j = 0; j < num; j++) {
                path.lineTo(xPoints[j], yPoints[j]);
            }

            path.closePath();
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, v));
            if (rbtFill.isSelected())
            {
                if (chkGrad.isSelected())
                {
                    g.setPaint(gradient);
                } else {
                    g.setPaint(null);
                }
                g.fill(path);
            } else {
                g.draw(path);
            }
            for (int i = 0; i < num; i++) {
                g.drawString("(" + xPoints[i] + ", " + yPoints[i] + ")", xPoints[i], yPoints[i]);
            }
            g.setPaintMode();
        }

    }
}
