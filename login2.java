//javac -cp ".;c:\*" login2.java


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

class login2 extends JFrame implements ActionListener
{
  JButton bk;
  JMenuBar mbr;
  JMenu adm,det,stk,bl,rprt;
  JMenuItem nu,cng,ci,pi,sb,cb,ski,si,cr,sr,br,oi,exit,skr,dd;
  ImageIcon i;
  JLabel l1,l2,l3,l4,l5,l6,lwel;
  Font f;
  login2()
       {
            super("Home Page");
            setSize(1200,750);
            setLocation(20,20);
            setLayout(null);
            //setResizable

			
		//	getContentPane().setBackground(Color.pink);
			
   i=new ImageIcon(getClass().getResource("77.jpg"));
   l1=new JLabel(i);
  
  i=new ImageIcon(getClass().getResource("aa.jpg"));
   l2=new JLabel(i);
 

  i=new ImageIcon(getClass().getResource("kk.jpg"));
   l3=new JLabel(i);
 

  i=new ImageIcon(getClass().getResource("dd1.jpg"));
   l4=new JLabel(i);
 
  i=new ImageIcon(getClass().getResource("pp1.jpg"));
   l5=new JLabel(i);
  
  
  i=new ImageIcon(getClass().getResource("kk1.jpg"));
   l6=new JLabel(i);
  
   
   lwel=new JLabel("*****Welcome to Jewellary shop*******");
   
   f=new Font("ALGERIAN",Font.BOLD,25);
   lwel.setFont(f);

   bk=new JButton("Back");
   mbr=new JMenuBar();
   adm=new JMenu("Administrator"); det=new JMenu("Detail"); stk=new JMenu("Supplier");
   bl=new JMenu("Bill");        rprt=new JMenu("Report");
  nu=new JMenuItem("New User");  cng=new JMenuItem("Change Password");
   ci=new JMenuItem("Customer Info");
   pi=new JMenuItem("Product Info");
   cb=new JMenuItem("Customer Bill");
   dd=new JMenuItem("Dealer Info");
  
   
   sb=new JMenuItem("Suplier Transaction");
   ski=new JMenuItem("Supplier Detail");
   si=new JMenuItem("Supplier Detail");
   cr=new JMenuItem("Customer Report");
   sr=new JMenuItem("Supplier Report");
   oi=new JMenuItem("Order Product");
   br=new JMenuItem("Bill Report");
   skr = new JMenuItem("Stock Report");
   exit=new JMenuItem("Log Out");
   
	add(bk);     add(mbr);    mbr.add(adm);  mbr.add(det);  
       mbr.add(stk);  mbr.add(bl); mbr.add(rprt);
         adm.add(exit);  det.add(ci);      det.add(pi);    det.add(dd); 	 add(l1);
		 adm.add(nu);
		add(l2);
		add(l3);
		add(l4);
		add(l5);
		add(l6);
	
		
		
		
        bl.add(cb);   stk.add(ski); /* bl.add(sb);   stk.add(si);   stk.add(oi);*/
        rprt.add(cr); /*rprt.add(br); */  add(lwel);
        lwel.setForeground(Color.RED);
        l1.setBounds(50,100,300,300);
        l2.setBounds(360,100,300,300);
        l3.setBounds(50,400,300,300);
        l4.setBounds(360,400,300,300);
        l5.setBounds(670,100,300,300);
        l6.setBounds(670,400,300,300);
		
		
        lwel.setBounds(200,40,600,80);
        bk.setBounds(570,0,100,20);
        mbr.setBounds(10,0,550,20);
	//	i.setBounds(100,300,100,100);

       bk.setMnemonic('B');

       bk.addActionListener(this);
       nu.addActionListener(this);
       cng.addActionListener(this);
       ci.addActionListener(this);
	    dd.addActionListener(this);
       cb.addActionListener(this);
       pi.addActionListener(this);
       sb.addActionListener(this);
       ski.addActionListener(this);
       si.addActionListener(this);
       cr.addActionListener(this);
       sr.addActionListener(this);
       oi.addActionListener(this);
      br.addActionListener(this);
      skr.addActionListener(this);
      exit.addActionListener(this);
          
   setVisible(true);
   setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
public void actionPerformed(ActionEvent e)
  {
   if(e.getSource()==bk)
   {
			new Login();
       
            setVisible(false);
  
        }
         if(e.getSource()==nu)
		  {
		    new  Nuser();
			dispose();
                    
		  }
        if(e.getSource()==cng)
		  {
		    new  change();
			dispose();
                    
		  }
        
          if(e.getSource()==ci)
		  {
		    new  cust_details();
                   dispose();
		  }
		  
		  
		  
		  
		   if(e.getSource()==dd)
		  {
		    new  dealer_details();
                   dispose();
		  }
		  
		  
		  
		  
		  
		  
		  
        if(e.getSource()==cb)
		  {
		    new  Bill_report();
                    dispose();
		  }
       if(e.getSource()==pi)
		  {
		    new  product();
                  dispose();       
		  }

     if(e.getSource()==exit)
		  {
                    int ans=JOptionPane.showConfirmDialog(null,"Are You Sure want to Exit?");
                    if(ans==0)
                    {
                      System.exit(0);
                    } 
                   else if(ans==1)
                   {
                                         
                   }     
		  }

      if(e.getSource()==sb)
		  {
		    new  supplierTrans();
                    dispose();
		  }
      
     if(e.getSource()==ski)
		  {
		    new  supplier();
                    dispose();
		  }
    if(e.getSource()==si)
		  {
		    new  supplier();
                    dispose();
		  }
     if(e.getSource()==cr)
		  {
		    new  report();
                    dispose();
		  }
    if(e.getSource()==sr)
		  {
		    new  suplrep();
                    dispose();
		  }
    if(e.getSource()==oi)
		  {
		    new  orderInfo();
                    dispose();
		  }
   if(e.getSource()==br)
		  {
		    new  stockrep();
                    dispose();
		  }
    if(e.getSource()==skr)
		  {
		    new  report();
                    dispose();
		  }
      

}
public static void main(String args[])
  {
   new login2();
  }
}

