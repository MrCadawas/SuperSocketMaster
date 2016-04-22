import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

// Simple test of the network to demonstrate the importance
// of formatting network data traffic
// Students will need to determine the appropriate message format
// to send to the server to make any of the four balls move

public class ballcontrol implements ActionListener{
  JFrame theframe;
  JGraphics thepanel;
  Timer thetimer;
  String strNetText;
  
  // Super Socket Master
  SuperSocketMaster ssm;
  
  public void actionPerformed(ActionEvent evt){
    if(evt.getSource() == thetimer){
     thepanel.repaint(); 
      
    }else if(evt.getSource() == ssm){
      strNetText = ssm.readText();
      if(strNetText.equals("bbu")){
        thepanel.intbby -=3;
      }else if(strNetText.equals("bbd")){
        thepanel.intbby +=3;        
      }else if(strNetText.equals("bbl")){
        thepanel.intbbx -=3;        
      }else if(strNetText.equals("bbr")){
        thepanel.intbbx +=3;        
      }else if(strNetText.equals("rbu")){
        thepanel.intrby -=3;        
      }else if(strNetText.equals("rbd")){
        thepanel.intrby +=3;        
      }else if(strNetText.equals("rbl")){
        thepanel.intrbx -=3;        
      }else if(strNetText.equals("rbr")){
        thepanel.intrbx +=3;        
      }else if(strNetText.equals("gbu")){
        thepanel.intgby -=3;        
      }else if(strNetText.equals("gbd")){
        thepanel.intgby +=3;        
      }else if(strNetText.equals("gbl")){
        thepanel.intgbx -=3;        
      }else if(strNetText.equals("gbr")){
        thepanel.intgbx +=3;        
      }else if(strNetText.equals("cbu")){
        thepanel.intcby -=3;        
      }else if(strNetText.equals("cbd")){
        thepanel.intcby +=3;        
      }else if(strNetText.equals("cbl")){
        thepanel.intcbx -=3;        
      }else if(strNetText.equals("cbr")){
        thepanel.intcbx +=3;        
      }
      
    }
    
  }
  
  public ballcontrol(){
    theframe = new JFrame();
    thepanel = new JGraphics();
    
    thepanel.setLayout(null);
    thepanel.setPreferredSize(new Dimension(600, 600));
    
    theframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    theframe.setContentPane(thepanel);
    theframe.pack();
    theframe.setVisible(true);
    
    thetimer = new Timer(1000/60, this);
    thetimer.start();
    
    // SuperSocketMaster server mode
    ssm = new SuperSocketMaster(6112, this);
    ssm.connect();
  }
  
  // main method
  public static void main(String[] args){
    ballcontrol bc = new ballcontrol();
  }
  
  // Should have created this in a separate file... oh well
  private class JGraphics extends JPanel{
    // Ball variables
    int intbbx = 50;
    int intbby = 50;
    int intrbx = 50;
    int intrby = 500;
    int intgbx = 500;
    int intgby = 50;
    int intcbx = 500;
    int intcby = 500;
    
    
    // Repainting screen based on network input
    // and how it changes the ball variables
    public void paintComponent(Graphics g){
      g.clearRect(0, 0, 600, 600);
      g.setColor(Color.BLUE);
      g.fillOval(intbbx, intbby, 50, 50);
      g.setColor(Color.RED);
      g.fillOval(intrbx, intrby, 50, 50);
      g.setColor(Color.GREEN);
      g.fillOval(intgbx, intgby, 50, 50);
      g.setColor(Color.CYAN);
      g.fillOval(intcbx, intcby, 50, 50);
      
      
      
    }
    
    
    public JGraphics(){
      super(); 
    }
    
  }
  
}
