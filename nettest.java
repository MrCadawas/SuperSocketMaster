import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.net.*;

public class nettest implements ActionListener{
  JFrame theFrame;
  JPanel thePanel;
  SuperSocketMaster ssm;
  JButton serverBut;
  JButton clientBut;
  JTextField ipaddress;
  JTextField port;
  JLabel ipaddresslabel;
  JLabel portlabel;
  JTextField texttosend;
  JLabel texttosendlabel;
  JTextArea textrecieved;
  JScrollPane theScroll;
  JLabel textrecievedlabel;
  JButton discBut;
  
  
  
  public void actionPerformed(ActionEvent evt){
    if(evt.getSource() == clientBut){
      serverBut.setEnabled(false); 
      clientBut.setEnabled(false); 
      ipaddress.setEnabled(false);
      port.setEnabled(false);
      ssm = new SuperSocketMaster(ipaddress.getText(), Integer.parseInt(port.getText()), this);
      boolean gotConnect = ssm.connect();
      if(gotConnect){
        discBut.setEnabled(true);
        textrecieved.append("My Address: "+ssm.getMyAddress()+"\n");
        textrecieved.append("My Hostname: "+ssm.getMyHostname()+"\n");
      }else{
        serverBut.setEnabled(true); 
        clientBut.setEnabled(true); 
        ipaddress.setEnabled(true);
        port.setEnabled(true);
      }
      
    }else if(evt.getSource() == serverBut){
      serverBut.setEnabled(false); 
      clientBut.setEnabled(false); 
      ipaddress.setEnabled(false);
      port.setEnabled(false);     
      ssm = new SuperSocketMaster(Integer.parseInt(port.getText()), this);
      boolean gotConnect = ssm.connect();
      if(gotConnect){
        discBut.setEnabled(true);
        textrecieved.append("My Address: "+ssm.getMyAddress()+"\n");
        textrecieved.append("My Hostname: "+ssm.getMyHostname()+"\n");
      }else{
        serverBut.setEnabled(true); 
        clientBut.setEnabled(true); 
        ipaddress.setEnabled(true);
        port.setEnabled(true);
      }
      
    }else if(evt.getSource() == texttosend){
      if(ssm != null){
        ssm.sendText(texttosend.getText());
      }
    }else if(evt.getSource() == ssm){
      textrecieved.append(ssm.readText() + "\n");
      textrecieved.setCaretPosition(textrecieved.getDocument().getLength());
      
      
    }else if(evt.getSource() == discBut){
      serverBut.setEnabled(true); 
      clientBut.setEnabled(true); 
      ipaddress.setEnabled(true);
      port.setEnabled(true); 
      ssm.disconnect();
      discBut.setEnabled(false);
    }
    
  }
  
  public nettest(){
    theFrame = new JFrame("Testing");
    theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    thePanel = new JPanel();
    thePanel.setLayout(null);
    thePanel.setPreferredSize(new Dimension(300, 600));
    theFrame.setContentPane(thePanel);
    theFrame.pack();
    serverBut = new JButton("Server");
    serverBut.setSize(300, 25);
    serverBut.setLocation(0,0);
    serverBut.addActionListener(this);
    thePanel.add(serverBut);
    clientBut = new JButton("Client");
    clientBut.setSize(300, 25);
    clientBut.setLocation(0, 25);
    clientBut.addActionListener(this);
    thePanel.add(clientBut);
    ipaddresslabel = new JLabel("ip");
    ipaddresslabel.setHorizontalAlignment(SwingConstants.CENTER);
    ipaddresslabel.setSize(300, 25);
    ipaddresslabel.setLocation(0, 50);
    thePanel.add(ipaddresslabel);
    ipaddress = new JTextField("localhost");
    ipaddress.setSize(300,25);
    ipaddress.setLocation(0, 75);
    thePanel.add(ipaddress);
    portlabel = new JLabel("port");
    portlabel.setHorizontalAlignment(SwingConstants.CENTER);
    portlabel.setSize(300, 25);
    portlabel.setLocation(0, 100);
    thePanel.add(portlabel);
    port = new JTextField("6112");
    port.setSize(300,25);
    port.setLocation(0, 125);
    thePanel.add(port);
    texttosendlabel = new JLabel("Text to Send Over Network");
    texttosendlabel.setHorizontalAlignment(SwingConstants.CENTER);
    texttosendlabel.setSize(300, 25);
    texttosendlabel.setLocation(0, 150);
    thePanel.add(texttosendlabel);
    texttosend = new JTextField();
    texttosend.setSize(300,25);
    texttosend.setLocation(0, 175);
    texttosend.addActionListener(this);
    thePanel.add(texttosend);
    textrecievedlabel = new JLabel("Text Recieved From Network");
    textrecievedlabel.setHorizontalAlignment(SwingConstants.CENTER);
    textrecievedlabel.setSize(300, 25);
    textrecievedlabel.setLocation(0, 200);
    thePanel.add(textrecievedlabel);
    textrecieved = new JTextArea();
    theScroll = new JScrollPane(textrecieved);
    theScroll.setSize(300,350);
    theScroll.setLocation(0, 225);
    thePanel.add(theScroll);  
    discBut = new JButton("Disconnect");
    discBut.setSize(300, 25);
    discBut.setLocation(0,575);
    discBut.addActionListener(this);
    discBut.setEnabled(false);
    thePanel.add(discBut);
    
    // Needed for Windows. But not in macOS
    // When one closes the window, disconnect any open sockets
    // Seems to do it automatically in macOS but not in windows
    theFrame.addWindowListener(new java.awt.event.WindowAdapter() {
      @Override
      public void windowClosing(java.awt.event.WindowEvent windowEvent) {
        if(ssm != null){
          ssm.disconnect();
        }
      }
    });
    
    theFrame.setVisible(true);
    
  }
  
  public static void main(String[] args){
    nettest nt = new nettest(); 
    
    
  }
  
  
  
  
  
  
  
}