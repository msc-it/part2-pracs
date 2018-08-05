import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ShapeDialog extends JDialog implements ActionListener {

    Container cont;
    String shpType = "";

    JLabel lblX, lblY, lblWidth, lblHeight;
    JTextField txtX, txtY, txtWidth, txtHeight;
    JButton btOk;

    int[] values, valuesX, valuesY;
    int num;

    PractShapes ps;
    JPanel polyPanel;
    JScrollPane sp;

    JTextField[] txtPX, txtPY;

    public ShapeDialog(PractShapes mainClass, String typeOfShape) {

        shpType = typeOfShape;
        ps = mainClass;

        cont = getContentPane();
        cont.setLayout(null);

        if (typeOfShape.equals("Square")) {

            lblX = new JLabel("X Axis : ");
            lblY = new JLabel("Y Axis : ");

            lblWidth = new JLabel("Width=Hieght : ");

            txtX = new JTextField(20);
            txtY = new JTextField(20);
            txtWidth = new JTextField(20);

            lblX.setBounds(50, 50, 50, 25);
            lblY.setBounds(50, 100, 50, 25);

            lblWidth.setBounds(50, 150, 100, 25);

            txtX.setBounds(150, 50, 50, 25);
            txtY.setBounds(150, 100, 50, 25);

            txtWidth.setBounds(150, 150, 50, 25);

            cont.add(lblX);
            cont.add(txtX);

            cont.add(lblY);
            cont.add(txtY);

            cont.add(lblWidth);
            cont.add(txtWidth);

        }

        if (typeOfShape.equals("Rectangle") || typeOfShape.equals("Ellipse")) {

            lblX = new JLabel("X : ");
            lblY = new JLabel("Y : ");
            lblWidth = new JLabel("Width : ");

            lblHeight = new JLabel("Height : ");

            txtX = new JTextField(20);
            txtY = new JTextField(20);
            txtWidth = new JTextField(20);
            txtHeight = new JTextField(20);

            lblX.setBounds(50, 50, 50, 25);
            lblY.setBounds(50, 100, 50, 25);

            lblWidth.setBounds(50, 150, 100, 25);
            lblHeight.setBounds(50, 200, 100, 25);

            txtX.setBounds(150, 50, 50, 25);
            txtY.setBounds(150, 100, 50, 25);

            txtWidth.setBounds(150, 150, 50, 25);
            txtHeight.setBounds(150, 200, 50, 25);

            cont.add(lblX);
            cont.add(lblY);

            cont.add(lblWidth);
            cont.add(lblHeight);

            cont.add(txtX);
            cont.add(txtY);

            cont.add(txtWidth);
            cont.add(txtHeight);

        }

        if (typeOfShape.equals("Polygon")) {

            String strSides = JOptionPane.showInputDialog("Enter number of sides :");
            if (strSides != null) {

                cont.setLayout(new BorderLayout());
                num = Integer.parseInt(strSides);
                txtPX = new JTextField[num];
                txtPY = new JTextField[num];
                polyPanel = new JPanel();
                polyPanel.setLayout(new GridLayout(num, 4));

                for (int i = 0; i < num; i++) {

                    JLabel l1 = new JLabel("X" + (i + 1) + " : ");
                    txtPX[i] = new JTextField();

                    JLabel l2 = new JLabel("Y" + (i + 1) + " : ");
                    txtPY[i] = new JTextField();

                    polyPanel.add(l1);
                    polyPanel.add(l2);

                    polyPanel.add(txtPX[i]);

                    polyPanel.add(txtPY[i]);
                    sp = new JScrollPane(polyPanel);
                }
                cont.add(sp, BorderLayout.CENTER);

            }
        }

        btOk = new JButton("OK");
        btOk.addActionListener(this);
        if (!typeOfShape.equals("Polygon"))

        {
            btOk.setBounds(210, 220, 70, 30);

            cont.add(btOk);
        }

        else
            cont.add(btOk, BorderLayout.SOUTH);

        JLabel lblTitle = new JLabel("<html><u>Enter co-ordinates</u></html>", JLabel.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 16));

        if (!typeOfShape.equals("Polygon")) {

            lblTitle.setBounds(0, 0, getWidth(), 30);
            cont.add(lblTitle);

        }

        else
            cont.add(lblTitle, BorderLayout.NORTH);

        Toolkit t = Toolkit.getDefaultToolkit();
        Dimension d = t.getScreenSize();

        setSize(300, 300);
        setLocation((d.width / 2) - (getWidth() / 2), (d.height / 2) - (getHeight() / 2));

        setVisible(true);
    }

    public int getPolyNum() {

        return num;
    }

    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == btOk) {

            if (shpType.equals("Square")) {

                values = new int[3];
                values[0] = Integer.parseInt(txtX.getText());
                values[1] = Integer.parseInt(txtY.getText());
                values[2] = Integer.parseInt(txtWidth.getText());
                ps.drawShapes(shpType, values);

                dispose();
            }

            if (shpType.equals("Rectangle") || shpType.equals("Ellipse")) {

                values = new int[4];
                values[0] = Integer.parseInt(txtX.getText());
                values[1] = Integer.parseInt(txtY.getText());
                values[2] = Integer.parseInt(txtWidth.getText());
                values[3] = Integer.parseInt(txtHeight.getText());
                ps.drawShapes(shpType, values);

                dispose();
            }

            if (shpType.equals("Polygon")) {

                valuesX = new int[num];
                valuesY = new int[num];

                for (int i = 0; i < num; i++) {

                    valuesX[i] = Integer.parseInt(txtPX[i].getText());
                    valuesY[i] = Integer.parseInt(txtPY[i].getText());

                }

                ps.drawPolyShape(valuesX, valuesY);
                dispose();

            }

        }
    }

}
