import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
class custrep extends JFrame implements ActionListener
{
   JLabel l_fdt,l_tdt,l_title;
   DateButton fdt,tdt;
   JButton gnrt, b_bk;
   Font f;
   custrep()
    {
       super("Customer Report");
       setSize(600,300);
       setLocation(250,250);
       setLayout(null);

     f = new Font("Arial",Font.BOLD,25);
     l_title = new JLabel("Customer Report");
     l_title.setFont(f);

     l_fdt = new JLabel("From Date");
     l_tdt = new JLabel("To Date");
     fdt = new DateButton();
     tdt = new DateButton();
     gnrt = new JButton("Generate Report");
      b_bk=new JButton("Cancle");


     add(l_fdt);  add(l_tdt);     add(fdt);    add(tdt);
     add(gnrt);   add(l_title);    add(b_bk);

     l_title.setBounds(180,20,260,30);
     l_fdt.setBounds(40,80,150,30);
     fdt.setBounds(130,80,150,30);

     l_tdt.setBounds(320,80,150,30);
     tdt.setBounds(390,80,150,30);

    gnrt.setBounds(130,130,170,30);
    b_bk.setBounds(390,130,170,30);

     gnrt.setMnemonic('G');
     gnrt.addActionListener(this);
        b_bk.addActionListener(this);
     setVisible(true);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
   
    }
  public void actionPerformed(ActionEvent e)
   {
    try
    {
     if(e.getSource()==gnrt)
      {
          new custreport1(fdt.getText(),tdt.getText()); 
            //dispose();
   }
 }
  
 
  catch(Exception ee)
  {
  ee.printStackTrace();
  }    

if(e.getSource()==b_bk)
     {
          login2 m1 = new login2();
            m1.setVisible(true);
            setVisible(false);
  
        }

   }
 public static void main(String args[])
  {
     new custrep();
  }
}
