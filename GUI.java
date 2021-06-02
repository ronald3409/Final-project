import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.ActionListener
  
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GUI implements ActionListener {
  
 private int count =0;
 private JLabel label;
 private JFrame frame;
 private JPanel panel;
  
  public GUI()  {
    
     frame = new JFrame();
   
     button = JButton("Click me")
    button.addActionListener(this);
    
     label = new JLabel("Number of Clicks: 0");  
    
     panel = new JPanel();    
   panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
   panel.setLayout(new GridLayout(0,1));
 
   frame.add(panel, BorderLayout.CENTER);
   frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   frame.setTitle("Our GUI");
   frame.pack();
   frame.setVisible(true);
   
 }
  
  Public static void main(String[] args)  {
      new GUI();
  }
  
  @Override
  public void actionPerformed(ActionEvent e) {
   count++;
   label.setText("Number of clicks: " + count);
   
    
    
  } 
  
}  