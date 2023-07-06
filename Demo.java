//javac -cp ".;c:\*" login2.java

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

class Demo extends JFrame implements ActionListener
{
  
  JLabel l1,l2,l3,l4,l5,l6,l7;
  JTextField t1,t2,t3,t4,t5;
  JButton b1,b2,b3,b4,b5,b6,b7;
 // JComboBox cmb1;
  Demo()
       {
            super("Home Page");
            setSize(1100,650);
            setLocation(50,50);
            setLayout(null);
            //setResizable
  
  	l1 = new JLabel("CUSTOMER DETAIL");
  	l2 = new JLabel("Customer Id");
  	l3 = new JLabel("Phone No :");
	l4 = new JLabel("Customer Address");
	l5 = new JLabel("Customer name");
	l6 = new JLabel("Date");
	l7 = new JLabel("Search By");
	
	

	
t1  = new JTextField();
t2  = new JTextField();
t3 = new JTextField();
t4 = new JTextField();
t5 = new JTextField();



b1 = new JButton("Insert");
b2 = new JButton("Update");
b3 = new JButton("Search");
b4 = new JButton("Back");
b5 = new JButton("Delete");
b6 = new JButton("Clear");
b7 = new JButton("Exit");

//cmb1 = new JComboBox();


		
		add(l1);		add(l3);
		add(l2);        add(l4);
		add(t1);        add(t2);
		add(t3);        add(l5);
		add(t4);        add(l6);
		add(t5);        add(b1);
		add(b2);        add(b3);
		add(b4);        add(b5);
		add(b6);        add(b7);
		add(l7);
		//add(cmb1);
		
		l1.setBounds(400,50,150,20);
		l2.setBounds(200,70,150,20);        		t1.setBounds(350,70,150,20);
		l3.setBounds(200,100,150,20);            t2.setBounds(350,100,150,20);
		l3.setBounds(200,100,150,20);            t2.setBounds(350,100,150,20);
		l4.setBounds(200,150,150,20);            t3.setBounds(350,140,300,50);
		l5.setBounds(550,70,300,20);             	t4.setBounds(650,70,150,20);
		l6.setBounds(550,100,150,20);           	t5.setBounds(650,100,150,20); 
		b1.setBounds(200,250,100,20);           b2.setBounds(200,280,100,20);   
		b3.setBounds(350,250,100,20);           b4.setBounds(350,280,100,20);    
		b5.setBounds(500,250,100,20);          	b6.setBounds(500,280,100,20); 
		b7.setBounds(630,250,100,20);
		l7.setBounds(550,650,100,20);            

		//cmb1.setBounds(550,90,300,20);



		




		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		b5.addActionListener(this);
		b6.addActionListener(this);
		b7.addActionListener(this);
		//cmb1.addActionListener(this);



		

  
  
   setVisible(true);
   setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
public void actionPerformed(ActionEvent e)
  {

}
public static void main(String args[])
  {
   new Demo();
  }
}

