import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.geom.*;
class welcome extends JFrame implements ActionListener 
 {

  JPanel p1;
  JLabel l1,l2,l3,image;
  JButton login;
  welcome()
    {
     
     super("Welcome Page");
     setSize(1400,800);

     p1=new JPanel();
     l1=new JLabel("WELCOME TO COSMETIC SHOP MANAGEMENT SYSTEM");     l2=new JLabel("TULJARAM CHATURCHAND COLLEGE");
     l3=new JLabel("BARAMATI");
     login=new JButton("Next");


        image=new JLabel(new ImageIcon("logo.jpg"));
	image.setBounds(10,10,1350,700);


     
     add(l1);
     add(l2);
	 add(l3);
	 add(login);
     login.addActionListener(this);
     
     p1.setBackground(Color.black);
     l1.setForeground(Color.red);
     l2.setForeground(Color.white);
     l3.setForeground(Color.green);
	 login.setForeground(Color.black);
	  
    
     Font f2=new Font("",Font.BOLD,40);
     l1.setFont(f2);
     
     Font f3=new Font("",Font.BOLD,37);
     l2.setFont(f3);

     Font f4=new Font("",Font.BOLD,36);
     l3.setFont(f4);
	      f3=new Font("",Font.BOLD,25);     login .setFont(f3);
  
     p1.setLayout(null);
     p1.add(login).setBounds(600,450,150,45);
     p1.add(l1).setBounds(70,100,1250,40);
     p1.add(l2).setBounds(310,170,800,50);
     p1.add(l3).setBounds(600,240,800,50);
     p1.add(image);

     add(p1);
     
     setVisible(true);
     setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
  }
  public void actionPerformed(ActionEvent ae)
   {
     if(ae.getSource()==login)
       {
	     new login2();
		 dispose();
       }
   }
 public static void main(String[] args)
     {
       new welcome();
         
    }
 }
