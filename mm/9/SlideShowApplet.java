import java.awt.*;
import javax.swing.*;
import java.applet.*;
import java.awt.event.*;

/*
   <applet code="SlideShowApplet" width=800 height=640>
   </applet>
*/

public class SlideShowApplet extends JApplet implements ActionListener {
  SlideShowThread slideShow;
  MediaTracker tracker;
  Image[] img = new Image[9];
  int current_img = 0;
  boolean shouldPlay = true;

  JPanel panel = new JPanel();
  JButton btNext= new JButton("Next"),  
          btPrev = new JButton("Previous"),
          btStart = new JButton("Start"),
          btStop = new JButton("Stop");

  public void init() {
    Container cont = getContentPane();
    cont.setLayout(new BorderLayout());

    btNext.addActionListener(this);
    btPrev.addActionListener(this);
    btStart.addActionListener(this);
    btStop.addActionListener(this);

    panel.add(btNext);
    panel.add(btPrev);
    panel.add(btStart);
    panel.add(btStop);

    cont.add(panel, BorderLayout.NORTH);

    //Add the images to the media tracker
    tracker = new MediaTracker(this);
    for (int i = 0; i < img.length; i++) {
      img[i] = getImage(getCodeBase(), i + ".jpg");
      tracker.addImage(img[i], i);
    }
    try {
    // Wait for the images to get loaded
      tracker.waitForAll();
    } catch(InterruptedException e) { e.printStackTrace();}

    // Initialize the thread
    slideShow = new SlideShowThread(this);
    // and start the slideshow
    startSlideShow();
  }
  
  public void paint(Graphics g) {
    Graphics ge = panel.getGraphics();
    panel.paint(ge);

    g.drawImage(img[current_img], 0, 40, null);
    showStatus("Slide: " + current_img);
  }

  public void startSlideShow() {
    shouldPlay = true;
    slideShow.start();
  }

  public void stopSlideShow() {
    shouldPlay = false;
  }

  public void nextSlide() {
    current_img = (current_img + 1) % img.length;
  }

  public void previousSlide() {
    current_img = (current_img == 0) ? (img.length - 1) : (current_img - 1);
  }

  public void actionPerformed(ActionEvent ae) {
    if (ae.getSource() == btStop) {
      stopSlideShow();
    }

    if (ae.getSource() == btStart) {
      startSlideShow();
    }

    if (ae.getSource() == btNext) {
      nextSlide();
      repaint();
    }

    if (ae.getSource() == btPrev) {
      previousSlide();
      repaint();
    }
  }
}

class SlideShowThread implements Runnable {
  SlideShowApplet applet;
  Thread thread;

  SlideShowThread(SlideShowApplet applet) {
    this.applet = applet;
  }

  public void start() {
    if(thread == null) {
      thread = new Thread(this);
      thread.start();
    }
  }

  public void run() {
    while (applet.shouldPlay) {

      applet.nextSlide();
      applet.repaint();

      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) { }
    }
    thread = null;
  }

}
