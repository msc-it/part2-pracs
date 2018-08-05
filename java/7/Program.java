import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;

public class Program extends JFrame
        implements ActionListener, MouseMotionListener, MouseListener, CaretListener {

    Container cont;

    String completeFilePath = "";
    JLayeredPane glayer;
    JMenuBar mnuBar;
    JMenuItem mnuFile;

    JButton btLine, btRect, btEllipse;
    JToggleButton tbBold, tbItalic, tbUnderline;
    int countFrames = 0;

    JMenuItem miNew, miSave, miOpen, miExit;
    JDesktopPane desk;

    boolean line, lineSelected, rect, rectSelected, ellipse, ellipseSelected;
    DrawingPanel gp;

    int x, y, w, h, startX, startY, drawX, endX, endY;
    ArrayList<String> lines, rects, elips;

    ArrayList<JInternalFrame> allFrames = new ArrayList<JInternalFrame>();
    ArrayList allPanels = new ArrayList();

    ArrayList<JTextPane> allTextPanes = new ArrayList<JTextPane>();
    ArrayList allObjects = new ArrayList();

    JToolBar tools;
    JPanel top;

    Toolkit t = Toolkit.getDefaultToolkit();
    Dimension d = t.getScreenSize();

    Program() {
    }

    Program(String title) {

        setTitle(title);

        cont = getContentPane();
        cont.setLayout(new BorderLayout());

        top = new JPanel();
        top.setLayout(new BorderLayout());

        mnuBar = new JMenuBar();

        mnuFile = new JMenu("File");

        miNew = new JMenuItem("New");
        miNew.addActionListener(this);

        miSave = new JMenuItem("Save");
        miSave.addActionListener(this);

        miOpen = new JMenuItem("Open");
        miOpen.addActionListener(this);

        miExit = new JMenuItem("Exit");
        miExit.addActionListener(this);

        mnuFile.add(miNew);
        mnuFile.add(miOpen);

        mnuFile.add(miSave);
        mnuFile.add(miExit);

        mnuBar.add(mnuFile);

        btLine = new JButton("Line");
        btLine.addMouseListener(this);

        btRect = new JButton("Rectangle");
        btRect.addMouseListener(this);

        btEllipse = new JButton("Ellipse");
        btEllipse.addMouseListener(this);
        tbBold = new JToggleButton(new StyledEditorKit.BoldAction());
        tbItalic = new JToggleButton(new StyledEditorKit.ItalicAction());
        tbUnderline = new JToggleButton(new StyledEditorKit.UnderlineAction());

        tbBold.setFont(new Font("Arial", Font.BOLD, 14));
        tbItalic.setFont(new Font("Arial", Font.ITALIC, 14));
        tbUnderline.setFont(new Font("Arial", Font.BOLD, 14));

        tbBold.setText("B");
        tbItalic.setText("I");

        tbUnderline.setText("U");

        tools = new JToolBar();
        tools.add(btLine);
        tools.add(btRect);
        tools.add(btEllipse);
        tools.add(tbBold);
        tools.add(tbItalic);
        tools.add(tbUnderline);

        top.add(mnuBar, BorderLayout.WEST);
        top.add(tools, BorderLayout.CENTER);
        cont.add(top, BorderLayout.NORTH);

        desk = new JDesktopPane();
        desk.setBackground(Color.black);
        cont.add(desk, BorderLayout.CENTER);

        setSize(d.width, d.height - 50);
        setVisible(true);
    }

    public void saveFile(int currentFrameIndex, String fileName) {

        JInternalFrame currentFrame5 = allFrames.get(currentFrameIndex);
        ArrayList<String> shapesToWrite = new ArrayList<String>();
        System.out.println("File name is : " + fileName);

        JFileChooser chooser1 = new JFileChooser();
        int result1 = chooser1.showSaveDialog(this);

        if (result1 == JFileChooser.CANCEL_OPTION) {
            System.out.println("Pressed Cancel : " + result1);
            return;
        } else {
            System.out.println("Pressed Save: " + result1 + "\nContents are : \n"
                    + (allTextPanes.get(currentFrameIndex)).getText());

            if (currentFrame5 != null) {
                if (allObjects.size() > 1) {
                    shapesToWrite.add("Y[ProgramShapesExist]");
                    for (int i = 0; i < allObjects.size(); i++) {
                        if ((allObjects.get(i) instanceof JInternalFrame)
                                && (((JInternalFrame) (allObjects.get(i))) == currentFrame5)) {

                            for (int j = i + 1; j < allObjects.size(); j++) {
                                if ((allObjects.get(j) instanceof Line2D) || (allObjects.get(j) instanceof Rectangle2D)
                                        || (allObjects.get(j) instanceof Ellipse2D)) {
                                    if ((allObjects.get(j) instanceof Line2D)) {
                                        Line2D l = ((Line2D) (allObjects.get(j)));
                                        String strLine = "Line[Parameters]" + l.getX1() + "," + l.getY1() + ","
                                                + l.getX2() + "," + l.getY2() + "";
                                        shapesToWrite.add(strLine);
                                        shapesToWrite.add("[ProgramParseShapes]");
                                    }
                                    if ((allObjects.get(j) instanceof Rectangle2D)) {
                                        Rectangle2D r = ((Rectangle2D) (allObjects.get(j)));
                                        String strRect = "Rect[Parameters]" + r.getX() + "," + r.getY() + ","
                                                + r.getWidth() + "," + r.getHeight() + "";
                                        shapesToWrite.add(strRect);
                                        shapesToWrite.add("[ProgramParseShapes]");
                                    }
                                    if ((allObjects.get(j) instanceof Ellipse2D)) {
                                        Ellipse2D el = ((Ellipse2D) (allObjects.get(j)));
                                        String strEllipse = "Ellipse[Parameters]" + el.getX() + "," + el.getY() + ","
                                                + el.getWidth() + "," + el.getHeight() + "";
                                        shapesToWrite.add(strEllipse);
                                        shapesToWrite.add("[ProgramParseShapes]");
                                    }
                                } else {
                                    break;
                                }
                            } //for
                        } //if
                    } //for

                    shapesToWrite.add("[ProgramTextData]");
                    if ((allTextPanes.get(currentFrameIndex)).getText() != null)
                        shapesToWrite.add((allTextPanes.get(currentFrameIndex)).getText());
                    else
                        shapesToWrite.add("");
                } else {
                    System.out.println("In saving File - allObjects size is less than 1.");
                    shapesToWrite.add("N[ProgramShapesExist]");
                    if (allObjects.size() != 0) {
                        shapesToWrite.add("[ProgramTextData]");
                        if ((allTextPanes.get(currentFrameIndex)).getText() != null)
                            shapesToWrite.add((allTextPanes.get(currentFrameIndex)).getText());
                        else
                            shapesToWrite.add("");
                    }
                }
            } else {
                /*Write in file as no drawing objects*/
                System.out.println("In saving file - currentFrame is null");
            }
            String strAll = "";
            for (String str1 : shapesToWrite) {
                strAll = strAll + str1;
            }
            System.out.println("\n\nContents to write : " + strAll);
            try {
                FileOutputStream fout = new FileOutputStream(chooser1.getSelectedFile().getPath());
                byte b[] = new byte[500];
                b = strAll.getBytes();
                fout.write(b);
                fout.close();

                FileOutputStream fout1 = new FileOutputStream(chooser1.getSelectedFile().getPath() + ".ser");
                ObjectOutputStream os = new ObjectOutputStream(fout1);
                os.writeObject((allTextPanes.get(currentFrameIndex)).getStyledDocument());
                os.close();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error on Saving File.\nException : " + ex.getMessage());
            }
        }
    }

    public void openFile() {
        lines = new ArrayList<String>();
        rects = new ArrayList<String>();
        elips = new ArrayList<String>();

        JFileChooser chooser1 = new JFileChooser();
        int result1 = chooser1.showOpenDialog(this);
        if (result1 == JFileChooser.CANCEL_OPTION) {
            System.out.println("Pressed Cancel : " + result1);
            return;
        } else {
            String strGotAll = "";
            String[] ss1 = {};
            String filename = "";
            try {
                FileInputStream fin = new FileInputStream(chooser1.getSelectedFile().getPath());
                completeFilePath = chooser1.getSelectedFile().getPath();
                filename = chooser1.getSelectedFile().getName();
                System.out.println("Pressed Open : " + result1 + "\nContents are : ");

                int i = 0;
                do {
                    i = fin.read();
                    if (i != -1) {
                        System.out.print((char) i);
                        strGotAll = strGotAll + "" + ((char) i);
                    }
                } while (i != -1);
                fin.close();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error on Opening File.\nException : " + ex.getMessage());
            }

            String[] ss = strGotAll.split("\\[ProgramShapesExist\\]");

            if (ss.length != 0) {
                if (ss[0].equals("Y")) {
                    String strWithoutShapeExist = strGotAll.substring(28, strGotAll.length());
                    System.out.println("Without ShapesExist : \n" + strWithoutShapeExist + "\n\n");
                    ss1 = strWithoutShapeExist.split("\\[ProgramTextData\\]");
                    System.out.println(
                            "Without TextData :" + ss1[0] + "\n\nWithout ProgramParseShapes : All Shapes : \n");
                    String[] ss2 = ss1[0].split("\\[ProgramParseShapes\\]");
                    if (ss2.length != 0) {
                        for (String s : ss2) {
                            System.out.println(s);

                            String[] ss3 = s.split("\\[Parameters\\]");
                            if (ss3[0].equals("Line")) {
                                System.out.println("Line Parameters are : \n");
                                String[] ss4 = ss3[1].split(",");
                                for (String sf : ss4) {
                                    System.out.println(sf);
                                    lines.add(sf);
                                }
                            } //if
                            if (ss3[0].equals("Rect")) {
                                System.out.println("Rect Parameters are : \n");
                                String[] ss4 = ss3[1].split(",");
                                for (String sf : ss4) {
                                    System.out.println(sf);
                                    rects.add(sf);
                                }
                            }

                            if (ss3[0].equals("Ellipse")) {
                                System.out.println("Ellipse Parameters are : \n");
                                String[] ss4 = ss3[1].split(",");
                                for (String sf : ss4) {
                                    System.out.println(sf);
                                    elips.add(sf);
                                }
                            }

                        } //for

                    } //if there are no [ProgramParseShapes] 
                } //if

            } //if shapes are there 
            if (ss[0].equals("Y")) {
                displayFile(filename, ss1[1].trim());
            } else {
                String[] sh = ss[1].split("\\[ProgramTextData\\]");
                System.out.println("Contents of ss[1] : " + ss[1] + "\nsize of sh : " + sh.length);
                displayFile(filename, sh[1].trim());
            }
        } //else
    }

    public void displayFile(String filename, String strTextData) {
        createNewFrame(filename, strTextData);
    }

    public void caretUpdate(CaretEvent e) {
        JInternalFrame currentFrame6 = null;
        for (JInternalFrame f : allFrames) {
            try {
                if (!f.isIcon()) {
                    currentFrame6 = f;
                    break;
                }
            } catch (Exception e1) {
                System.out.println(
                        "Exception while checking in caret update which JInternalFrame is currently maximized : "
                                + e1.getMessage());
            }
        }

        StyledDocument doc = allTextPanes.get(allFrames.indexOf(currentFrame6)).getStyledDocument();
        AttributeSet a = doc.getCharacterElement(e.getDot() - 1).getAttributes();

        boolean bold = StyleConstants.isBold(a);
        if (bold != tbBold.isSelected())
            tbBold.setSelected(bold);

        boolean italic = StyleConstants.isItalic(a);
        if (italic != tbItalic.isSelected())
            tbItalic.setSelected(italic);

        boolean underline = StyleConstants.isUnderline(a);
        if (underline != tbUnderline.isSelected())
            tbUnderline.setSelected(underline);

    }

    public void changeCursor(int type) {
        Cursor c = new Cursor(type);
        JInternalFrame currentFrame2 = null;
        for (JInternalFrame f : allFrames) {
            try {
                if (!f.isIcon()) {
                    currentFrame2 = f;
                    if (type == 1) {
                        ((DrawingPanel) allPanels.get(allFrames.indexOf(currentFrame2))).grabFocus();
                    } else {
                        (allTextPanes.get(allFrames.indexOf(currentFrame2))).grabFocus();
                    }
                    ((DrawingPanel) allPanels.get(allFrames.indexOf(currentFrame2))).setCursor(c);
                    break;
                }
            } catch (Exception e1) {
                System.out.println("Exception while changing cursor type : " + e1.getMessage());
            }
        }
    }

    public void mouseReleased(MouseEvent me) {
        JInternalFrame currentFrame1 = null;
        if (line == true || rect == true || ellipse == true) {
            for (JInternalFrame f : allFrames) {
                try {
                    if (!f.isIcon()) {
                        currentFrame1 = f;
                        break;
                    }
                } catch (Exception e1) {
                    System.out.println(
                            "Exception while checking in mouse released which JInternalFrame is currently maximized : "
                                    + e1.getMessage());
                }
            }
        }
        if (line) {
            Line2D l = new Line2D.Double(drawX, startY, endX, endY);
            allObjects.add((allObjects.indexOf(currentFrame1) + 1), l);
            line = false;
            lineSelected = false;
            changeCursor(2);
        }

        if (rect) {
            Rectangle2D l = new Rectangle2D.Double(drawX, startY, w, h);
            allObjects.add((allObjects.indexOf(currentFrame1) + 1), l);
            rect = false;
            rectSelected = false;
            changeCursor(2);
        }

        if (ellipse) {
            Ellipse2D l = new Ellipse2D.Double(drawX, startY, w, h);
            allObjects.add((allObjects.indexOf(currentFrame1) + 1), l);
            ellipse = false;
            ellipseSelected = false;
            changeCursor(2);
        }
    }

    public void mouseDragged(MouseEvent me) {
        if (lineSelected == true || rectSelected == true || ellipseSelected == true) {
            if ((me.getSource()) instanceof DrawingPanel) {
                gp = (DrawingPanel) (me.getSource());
                endX = me.getX();
                endY = me.getY();
                if (startX >= endX) {
                    w = startX - endX;
                    drawX--;
                } else {
                    w = endX - startX;
                }
                if (startY >= endY) {
                    h = startY - endY;
                } else {
                    h = endY - startY;
                }

                if (lineSelected) {
                    line = true;
                    rect = false;
                    ellipse = false;
                }
                if (rectSelected) {
                    rect = true;
                    line = false;
                    ellipse = false;
                }
                if (ellipseSelected) {
                    ellipse = true;
                    rect = false;
                    line = false;
                }
                repaint();
            }
        }
    }

    public void mouseEntered(MouseEvent me) {
    }

    public void mouseExited(MouseEvent me) {
    }

    public void mouseClicked(MouseEvent me) {
    }

    public void mousePressed(MouseEvent me) {

        System.out.println("Inside mousePressed");
        if (me.getSource() == btLine) {
            changeCursor(1);
            System.out.println("\nInside mouse event handler for btLine.");
            lineSelected = true;
        }

        if (me.getSource() == btRect) {
            changeCursor(1);
            System.out.println("\nInside mouse event handler for btRect.");
            rectSelected = true;
        }

        if (me.getSource() == btEllipse) {
            changeCursor(1);
            System.out.println("\nInside mouse event handler for btEllipse.");
            ellipseSelected = true;
        }

        if (lineSelected == true || rectSelected == true || ellipseSelected == true) {
            System.out.println("Inside lineSelected,rectSelected,ellipseSelected of mouse event handler");
            if ((me.getSource()) instanceof DrawingPanel) {
                System.out.println("Its an instance of DrawingPanel.");
                startX = me.getX();
                drawX = startX;
                startY = me.getY();
            }
        }
    }

    public void mouseMoved(MouseEvent me) {
    }

    public void actionPerformed(ActionEvent ae) {

        System.out.println("\nInside actionPerformed");

        JInternalFrame currentFrame7 = null;
        for (JInternalFrame f : allFrames) {
            try {
                if (!f.isIcon()) {
                    currentFrame7 = f;
                    break;
                }

            } catch (Exception e1) {
                System.out.println(
                        "Exception while checking in actionPerformed which JInternalFrame is currently maximized : "
                                + e1.getMessage());
            }
        }

        if (ae.getSource() == tbBold) {
            allTextPanes.get(allFrames.indexOf(currentFrame7)).requestFocus();
        }
        if (ae.getSource() == miExit) {
            dispose();
        }
        if (ae.getSource() == miOpen) {
            openFile();
        }

        if (ae.getSource() == miSave) {
            JInternalFrame currentFrame4 = null;
            for (JInternalFrame f : allFrames) {
                try {
                    if (!f.isIcon()) {
                        currentFrame4 = f;
                        break;
                    }
                } catch (Exception e1) {
                    System.out.println(
                            "Exception while checking in actionPerformed of Save Button which JInternalFrame is currently maximized : "
                                    + e1.getMessage());
                }
            }
            if (currentFrame4 != null)
                saveFile(allFrames.indexOf(currentFrame4), currentFrame4.getTitle());
        }
        if (ae.getSource() == miNew)

        {
            /*
            
            If there are more than one JInternalFrame, then minimize old ones and maximize new one.*/
            createNewFrame(null, null);
        }

    }

    public void createNewFrame(String tit, String content) {

        for (JInternalFrame f : allFrames) {

            try {
                f.setIcon(true);
            } catch (Exception e)

            {
                System.out.println(
                        "Exception on minimizing all old JInternalFrames occured when a new JInternalFrame is created on pressing JMenuItem 'New'"
                                + e.getMessage());
            }
        }

        /* Make new JInternalFrame */ String strTitle = "";

        if (tit == null)

            strTitle = "Document " + (++countFrames);
        else
            strTitle = tit;

        JInternalFrame f = new JInternalFrame(strTitle, false, true, false, false);
        f.setSize(d.width - 5, d.height - 145);
        f.setVisible(true);

        f.addInternalFrameListener(new InternalListener(this));
        desk.add(f);

        /*Get the LayeredPane object to add 1st Panel of JTextPane.*/

        JLayeredPane layer = f.getLayeredPane();

        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BorderLayout());
        textPanel.setOpaque(true);

        textPanel.setBounds(0, 0, f.getWidth(), f.getHeight());

        JTextPane t = new JTextPane();
        t.setOpaque(true);

        if (content != null) {

            t.setText(content);
            if (content.length() != 0)

            {
                try

                {

                    FileInputStream fin1 = new FileInputStream(completeFilePath + ".ser");
                    ObjectInputStream ois = new ObjectInputStream(fin1);
                    StyledDocument sd1 = (StyledDocument) ois.readObject();

                    ois.close();

                    t.setStyledDocument(sd1);
                } catch (Exception e) {
                }

            }
        }

        t.addCaretListener(this);

        textPanel.add(t, BorderLayout.CENTER);
        DrawingPanel drawPanel = new DrawingPanel(this);
        drawPanel.setLayout(new BorderLayout());
        drawPanel.setOpaque(false);
        drawPanel.addMouseListener(this);
        drawPanel.addMouseMotionListener(this);
        drawPanel.setBounds(0, 0, f.getWidth(), f.getHeight());

        layer.add(textPanel, new Integer(1));
        layer.add(drawPanel, new Integer(2));
        glayer = layer;

        /*Adding new JInternalFrame object in ArrayList allFrames and new DrawingPanel in allPanels ArrayList.*/
        allFrames.add(f);
        allPanels.add(drawPanel);

        allObjects.add(f);
        allTextPanes.add(t);

        System.out.println("New JInternalFrame and DrawingPanel is Added.\nNow : \nSize of allFrames ArrayList : "
                + allFrames.size() + "\nSize of allPanels ArrayList : " + allPanels.size());

        System.out.println(
                "DrawingPanel is added in allObjects ArrayList.\nSize of allObjects : " + allObjects.size() + "\n\n");

        t.grabFocus();
        changeCursor(2);

    }

    public static void main(String[] args) {

        Program s = new Program("Special Editor");
        s.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
}

class InternalListener implements InternalFrameListener {

    Program se;

    InternalListener(Program se1) {
        se = se1;

    }

    public void internalFrameActivated(InternalFrameEvent e) {
    }

    public void internalFrameDeactivated(InternalFrameEvent e) {
    }

    public void internalFrameIconified(InternalFrameEvent e) {
    }

    public void internalFrameDeiconified(InternalFrameEvent e) {
        System.out.println("Deiconified Event occurred on " + ((JInternalFrame) e.getSource()).getTitle());
        System.out.println(
                "So now minimizing all other Documents except " + ((JInternalFrame) e.getSource()).getTitle() + "\n\n");

        /*Minimize all JInternalFrames except one on which this deiconified event has occured.*/

        for (JInternalFrame f : se.allFrames) {

            if (f != (JInternalFrame) e.getSource()) {

                try {
                    f.setIcon(true);
                } catch (Exception e1)

                {
                    System.out.println(
                            "Exception on minimizing all JInternalFrames except one on which this deiconified event has occured."
                                    + e1.getMessage());
                }
            } //if

        } //for //((JInternalFrame)e.getSource()).reshape(0,0,se.getWidth()-10,se.getHeight()-10//0);
    }

    public void internalFrameClosed(InternalFrameEvent e) {
    }

    public void internalFrameOpened(InternalFrameEvent e) {
    }

    public void internalFrameClosing(InternalFrameEvent e) {

        System.out.println("Closing Event occured on " + ((JInternalFrame) e.getSource()).getTitle());

        System.out.println("Size of allFrames ArrayList before removing closed frame object : " + se.allFrames.size()
                + "\nSize of allPanels : " + se.allPanels.size());

        /*Remove the JInternalFrame Object from ArrayList 'allFrames', DrawingPanel from allPanels and allObjects becoz this JInternalFrame is closed.*/

        int index = se.allFrames.indexOf((JInternalFrame) e.getSource());

        se.allFrames.remove(index);
        se.allTextPanes.remove(index);

        se.allPanels.remove(index);

        /*Also remove objects from allObjects*/ if (se.allObjects.size() > 0) {

            int frameClosedIndex;
            if (se.allObjects.contains(e.getSource()))

            {
                frameClosedIndex = se.allObjects.indexOf(e.getSource());
                if (frameClosedIndex == (se.allObjects.size() - 1)) {

                    se.allObjects.remove(frameClosedIndex);
                }

                else {

                    for (int i = (frameClosedIndex + 1); i < se.allObjects.size(); i++) {

                        if (!(se.allObjects.get(i) instanceof JInternalFrame)) {

                            se.allObjects.remove(i);
                        } else {

                            break;
                        }

                    } //for
                } //else

            } //if

        } //if else
        {

            System.out.println(
                    "Size of allObjects when checked after Closing a particular JInternalFrame, it was is less than 0.");
        }

        System.out.println("After removing allFrames size is : " + se.allFrames.size() + "\nallPanels size : "
                + se.allPanels.size() + "\nse.allObjects size : " + se.allObjects.size());

        /*Now Maximize last object from the ArrayList allFrames.*/ try {

            if (se.allFrames.size() > 0) {
                (se.allFrames.get(se.allFrames.size() - 1)).setMaximum(true);

                (se.allFrames.get(se.allFrames.size() - 1)).reshape(0, 0, se.getWidth() - 5, se.getHeight() - 95);

            } else {

                System.out.println(
                        "There are no more objects in allFrames ArrayList to maximize after closing previous JInternalFrame.");
            }
        } catch (Exception e1) {

            System.out.println("Exception on maximizing last JInternalFrame after closing one of the JInternalFrame."
                    + e1.getMessage());
        }
    }
}

class DrawingPanel extends JPanel {

    Program sd;

    DrawingPanel(Program sw) {

        sd = sw;
    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        JInternalFrame currentFrame = null;

        for (JInternalFrame f : sd.allFrames) {
            try {
                if (!f.isIcon()) {
                    currentFrame = f;

                    break;
                }

            } catch (Exception e1) {

                System.out.println(
                        "Exception while checking in paintcomponent which JInternalFrame is currently maximized : "
                                + e1.getMessage());
            }
        }

        if (sd.lines != null) {

            for (int i = 0; i < sd.lines.size(); i = i + 4) {

                Line2D k = new Line2D.Double(Double.parseDouble(sd.lines.get(i)),
                        Double.parseDouble(sd.lines.get(i + 1)), Double.parseDouble(sd.lines.get(i + 2)),
                        Double.parseDouble(sd.lines.get(i + 3)));
                g2d.draw(k);

                sd.allObjects.add((sd.allObjects.indexOf(currentFrame) + 1), k);
            }

            sd.lines.clear();
        }

        if (sd.rects != null) {
            for (int i = 0; i < sd.rects.size(); i = i + 4) {

                Rectangle2D k = new Rectangle2D.Double(Double.parseDouble(sd.rects.get(i)),
                        Double.parseDouble(sd.rects.get(i + 1)), Double.parseDouble(sd.rects.get(i + 2)),
                        Double.parseDouble(sd.rects.get(i + 3)));

                g2d.draw(k);
                sd.allObjects.add((sd.allObjects.indexOf(currentFrame) + 1), k);

            }
            sd.rects.clear();
        }

        if (sd.elips != null) {

            for (int i = 0; i < sd.elips.size(); i = i + 4) {

                Ellipse2D k = new Ellipse2D.Double(Double.parseDouble(sd.elips.get(i)),
                        Double.parseDouble(sd.elips.get(i + 1)), Double.parseDouble(sd.elips.get(i + 2)),
                        Double.parseDouble(sd.elips.get(i + 3)));
                g2d.draw(k);

                sd.allObjects.add((sd.allObjects.indexOf(currentFrame) + 1), k);
            }

            sd.elips.clear();
        }

        if (currentFrame != null) {
            if (sd.allObjects.size() > 1)

            {
                System.out.println("In paintComponent current frame is : " + currentFrame.getTitle());

                wh: for (int i = 0; i < sd.allObjects.size(); i++) {

                    if ((sd.allObjects.get(i) instanceof JInternalFrame)
                            && (((JInternalFrame) (sd.allObjects.get(i))) == currentFrame))

                    {
                        for (int j = (i + 1); j < sd.allObjects.size(); j++)

                        {

                            if ((sd.allObjects.get(j) instanceof Line2D)
                                    || (sd.allObjects.get(j) instanceof Rectangle2D)
                                    || (sd.allObjects.get(j) instanceof Ellipse2D))

                            {
                                if ((sd.allObjects.get(j) instanceof Line2D))
                                    g2d.draw((Line2D) (sd.allObjects.get(j)));

                                if ((sd.allObjects.get(j) instanceof Rectangle2D))
                                    g2d.draw((Rectangle2D) (sd.allObjects.get(j)));

                                if ((sd.allObjects.get(j) instanceof Ellipse2D))
                                    g2d.draw((Ellipse2D) (sd.allObjects.get(j)));
                            } else {
                                break;

                            }
                        } //for

                    } //if
                } //for

            } else {
                System.out.println("In paintComponent - allObjects size is less than 1.");
            }

        } else {
            System.out.println("In paintComponent - currentFrame is null");
        }

        if (sd.line) {
            System.out.println("Repainting Line...");

            Line2D l = new Line2D.Double(sd.drawX, sd.startY, sd.endX, sd.endY);
            g2d.draw(l);
        }

        if (sd.rect) {
            System.out.println("Repainting Rectangle...");

            Rectangle2D r = new Rectangle2D.Double(sd.drawX, sd.startY, sd.w, sd.h);
            g2d.draw(r);
        }
        if (sd.ellipse) {

            System.out.println("Repainting Ellipse...");

            Ellipse2D el = new Ellipse2D.Double(sd.drawX, sd.startY, sd.w, sd.h);
            g2d.draw(el);
        }

    }
}