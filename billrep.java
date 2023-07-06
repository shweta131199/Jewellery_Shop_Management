import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
class billrep extends JFrame implements ActionListener
{
   JLabel l_fdt,l_tdt,l_title;
   DateButton fdt,tdt;
   JButton gnrt,cancel;
   Font f;
   billrep()
    {
       super("Bill Report");
       setSize(600,300);
       setLocation(250,250);
       setLayout(null);

     f = new Font("Arial",Font.BOLD,25);
     l_title = new JLabel("Bill Report");
     l_title.setFont(f);

     l_fdt = new JLabel("From Date");
     l_tdt = new JLabel("To Date");
     fdt = new DateButton();
     tdt = new DateButton();
     gnrt = new JButton("Generate Report");
     cancel = new JButton("Cancel");

     add(l_fdt);  add(l_tdt);     add(fdt);    add(tdt);
     add(gnrt);   add(l_title);   add(cancel);

     l_title.setBounds(240,20,260,30);
     l_fdt.setBounds(40,80,150,30);
     fdt.setBounds(130,80,150,30);

     l_tdt.setBounds(320,80,150,30);
     tdt.setBounds(390,80,150,30);

    gnrt.setBounds(110,140,170,30);
    cancel.setBounds(390,140,100,30);

     gnrt.setMnemonic('G');           
     gnrt.addActionListener(this);     cancel.addActionListener(this);
   
     setVisible(true);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   
    }
  public void actionPerformed(ActionEvent e)
   {
    try
    {
     if(e.getSource()==gnrt)
      {
          new billreport(fdt.getText(),tdt.getText());  

   }
    if(e.getSource()==cancel)
      {
        new login2();
        dispose();   
      }
 }
  
  catch(Exception ee)
  {
  ee.printStackTrace();
  }       
  
  
   

         
      
   }
 public static void main(String args[])
  {
     new billrep();
  }
}
