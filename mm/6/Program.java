import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.speech.synthesis.*;
import javax.speech.*;

class Cal extends JFrame implements ActionListener
{
    Container cont;
    JPanel numPad;
    JTextField txtAns;
    JButton btAdd, btSub, btMult, btDiv, btEqual, btClear;
    boolean operatorSelected = false, calculated = false;
    String strFirst, strSecond, strOperatorType;
    Voice v;
    SynthesizerModeDesc smd;
    Synthesizer syn;

    public Cal(Voice v1, SynthesizerModeDesc smd1, Synthesizer syn1)
    {
        v = v1;
        smd = smd1;
        syn = syn1;
        cont = getContentPane();
        txtAns = new JTextField();
        numPad = new JPanel();
        numPad.setLayout(new GridLayout(4, 4));
        for (int i = 0; i < 10; i++)
        {
            JButton b = new JButton("" + i);
            numPad.add(b);
            b.addActionListener(this);
        }

        btAdd = new JButton("+");
        btSub = new JButton("-");
        btMult = new JButton("*");
        btDiv = new JButton("/");
        btEqual = new JButton("=");
        btClear = new JButton("Clear");

        numPad.add(btAdd);
        numPad.add(btSub);
        numPad.add(btMult);
        numPad.add(btDiv);
        numPad.add(btEqual);
        numPad.add(btClear);
        btAdd.addActionListener(this);
        btSub.addActionListener(this);
        btMult.addActionListener(this);
        btDiv.addActionListener(this);
        btEqual.addActionListener(this);
        btClear.addActionListener(this);
        cont.add(txtAns, BorderLayout.NORTH);
        cont.add(numPad, BorderLayout.CENTER);
        setBounds(300, 200, 300, 300);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae)
    {
        if (ae.getSource() != btAdd && ae.getSource() != btSub && ae.getSource() != btMult && ae.getSource() != btDiv
                && ae.getSource() != btEqual && ae.getSource() != btClear)
        {
            if (operatorSelected)
            {
                txtAns.setText("");
                operatorSelected = false;
            }
            if (calculated)
            {
                txtAns.setText("");
                calculated = false;
            }
            String strOld = txtAns.getText();
            String strNew = strOld + ((JButton) (ae.getSource())).getText();
            txtAns.setText(strNew);
        }

        if (ae.getSource() == btAdd)
        {
            strOperatorType = "PLUS";
            operatorSelected = true;
            strFirst = txtAns.getText();
        }

        if (ae.getSource() == btSub)
        {
            strOperatorType = "MINUS";
            operatorSelected = true;
            strFirst = txtAns.getText();
        }

        if (ae.getSource() == btMult)
        {
            strOperatorType = "MULTIPLIEDBY";
            operatorSelected = true;
            strFirst = txtAns.getText();
        }

        if (ae.getSource() == btDiv)
        {
            strOperatorType = "DIVIDEDBY";
            operatorSelected = true;
            strFirst = txtAns.getText();
        }

        if (ae.getSource() == btEqual)
        {
            if (strOperatorType.equals("PLUS"))
            {
                strSecond = txtAns.getText();
                System.out.println("First : " + strFirst + "\nSecond : " + strSecond);
                int ans = Integer.parseInt(strFirst) + Integer.parseInt(strSecond);
                txtAns.setText("" + ans);
                calculated = true;
                String finalText = strFirst + " " + strOperatorType + " " + strSecond + "=" + ans;
                System.out.println(finalText);
                speakOutput(finalText);
            }

            if (strOperatorType.equals("MINUS"))
            {
                strSecond = txtAns.getText();
                System.out.println("First : " + strFirst + "\nSecond : " + strSecond);
                int ans = Integer.parseInt(strFirst) - Integer.parseInt(strSecond);
                txtAns.setText("" + ans);
                calculated = true;
                String finalText = strFirst + " " + strOperatorType + " " + strSecond + "=" + ans;
                System.out.println(finalText);
                speakOutput(finalText);
            }

            if (strOperatorType.equals("MULTIPLIEDBY"))
            {
                strSecond = txtAns.getText();
                System.out.println("First : " + strFirst + "\nSecond : " + strSecond);
                int ans = Integer.parseInt(strFirst) * Integer.parseInt(strSecond);
                txtAns.setText("" + ans);
                calculated = true;
                String finalText = strFirst + " " + strOperatorType + " " + strSecond + "=" + ans;
                System.out.println(finalText);
                speakOutput(finalText);
            }

            if (strOperatorType.equals("DIVIDEDBY"))
            {
                strSecond = txtAns.getText();
                System.out.println("First : " + strFirst + "\nSecond : " + strSecond);
                int ans = Integer.parseInt(strFirst) / Integer.parseInt(strSecond);
                txtAns.setText("" + ans);
                calculated = true;
                String finalText = strFirst + " " + strOperatorType + " " + strSecond + "=" + ans;
                System.out.println(finalText);
                speakOutput(finalText);
            }
        }
        if (ae.getSource() == btClear) {
            txtAns.setText("");
        }
    }

    public synchronized void speakOutput(String text)
    {
        try {
            syn.allocate();
            syn.resume();
            syn.getSynthesizerProperties().setVoice(v);
            syn.speakPlainText(text, null);
            syn.waitEngineState(Synthesizer.QUEUE_EMPTY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
        Voice voice = null;
        try {
            String voiceName = "kevin16";
            SynthesizerModeDesc desc = new SynthesizerModeDesc(null, "general", Locale.US, null, null);
            Synthesizer synthesizer = Central.createSynthesizer(desc);
            if (synthesizer == null) {
                System.out.println(
                        "Error : NO Synthesizer created.\nMissing Speech.properties file or set proper classpath.");
                System.exit(1);
            }
            desc = (SynthesizerModeDesc) synthesizer.getEngineModeDesc();
            Voice[] voices = desc.getVoices();
            for (int i = 0; i < voices.length; i++) {
                if (voices[i].getName().equals(voiceName)) {
                    voice = voices[i];
                    break;
                }
            }
            if (voice == null)
            {
                System.err.println("Synthesizer does not have a voice named " + voiceName + ".");
                System.exit(0);
            }
            Cal c = new Cal(voice, desc, synthesizer);
            c.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}