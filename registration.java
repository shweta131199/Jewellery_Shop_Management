//javac -cp ".;c:\*" registration.java

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

class registration extends JFrame implements ActionListener
{
	JLabel l1,l2,l3,l4,l5,l6,l7,l8,l9;
	JTextField t1,t2,t3,t4,t5,t6,t7,t8;
	JButton b1,b2;
	
	registration()
	{
		super("Registration Form");
		setSize(800,500);
		setLayout(null);
		setLocation(300,200);
		
		l1 = new JLabel("Registration Form");			
		l2 = new JLabel("Name");
	l3 = new JLabel("Surname");	
	l4 = new JLabel("User Name");
	l5 = new JLabel("Password");	
	l6 = new JLabel("Re-Password");
	l7 = new JLabel("E-mail");	
	l8 = new JLabel("Billing Address");
	l9 = new JLabel("Telephone");	
		
		t1 = new JTextField();	
		t2 = new JTextField();	
		t3 = new JTextField();	
		t4 = new JTextField();	
		t5 = new JTextField();	
		t6 = new JTextField();	
		t7 = new JTextField();	
		t8 = new JTextField();	

		
		b1 = new JButton("Submit");                      b2 = new JButton("Reset Form");
		
		add(l1);
		add(l2);		add(l3);
		add(l4);		add(l5);		add(l6);
		add(l7);
		add(l8);
		add(l9);
		
		add(t1);	
		add(t2);
		add(t3);
		add(t4);
		add(t5);
		add(t6);
		add(t7);
		add(t8);
		add(b1);		add(b2);
		
		l1.setBounds(170,50,150,20);
		
		
		t1.setBounds(210,80,200,20);
		b1.setBounds(150,350,100,20);	b2.setBounds(270,350,100,20);
		l2.setBounds(50,80,150,20);		//	t1.setBounds(210,80,200,20);
		l3.setBounds(50,110,150,20);		t2.setBounds(210,110,200,20);
		l4.setBounds(50,140,150,20);		t3.setBounds(210,140,200,20);
		l5.setBounds(50,170,150,20);		t4.setBounds(210,170,200,20);
		l6.setBounds(50,200,150,20);		t5.setBounds(210,200,200,20);
		l7.setBounds(50,230,150,20);	l8.setBounds(50,270,150,20);	
		l9.setBounds(50,300,150,20);	
		t6.setBounds(210,230,200,20);
t7.setBounds(210,270,200,20);
t8.setBounds(210,300,200,20);

		
	//	t6.setBounds(300,200,110,20);		b1.setBounds(150,230,200,20);
		
		
		
		b1.addActionListener(this);
		b2.addActionListener(this);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
	
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==b2)
		{
			t1.setText("");
			t2.setText("");
			t3.setText("");
			t4.setText("");
			t5.setText("");
			t6.setText("");
			t7.setText("");
			t8.setText("");
		}
	}
	public static void main(String args[])
	{
		new registration();
	}
}