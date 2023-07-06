
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

class Fpass extends JFrame implements ActionListener
  {
	
     JButton b1,b2,b3;
     JTextField tu,tno,ta;
     JComboBox cb;
     JLabel lu,lno,lq,la,limg,ltitle;
      ImageIcon i;
      Font f;
     int cnt=0;
     Statement stm;
     Connection cn=null;
     PreparedStatement prstm;
     String sql;
     ResultSet rs;

   Fpass()
       {
            super("password recover");
            setSize(500,350);
            setLocation(250,260);
            setLayout(null);
 
           f=new Font("Arial",Font.BOLD,25);
              ltitle=new JLabel("RECOVER PASSWORD");
             ltitle.setFont(f);            

            b1=new JButton("Recover");
	    b2=new JButton("Back");
            b3=new JButton("Exit");
	    lu=new JLabel("Enter User name");
	    lno=new JLabel("Enter Contact No.");
            lq =new JLabel("Enter Question.");
            la =new JLabel("Enter Answer.");
           //i=new ImageIcon("logo.png");
            //limg=new JLabel(i);
            cb=new JComboBox();	
            ta=new JTextField();    
	    tu=new JTextField();
	    tno=new JTextField();
	    
	     
	
				add(b1);
				add(b2);
				add(b3);
				add(lu);
				add(lno);    //add(limg);
				add(lq);
                                add(la);
                                add(ta);
                                add(cb);
                                cb.addItem("Select Que");
                                cb.addItem("Mom-Dad Anniversary");
                                cb.addItem("Favourit Game");
                                cb.addItem("Favourite Product");
				add(tu);
				add(tno);
				add(ltitle); 
		     	//limg.setBounds(0,0,450,250);

                         ltitle.setBounds(120,20,400,30);
			lu.setBounds(50,80,150,30);
			lno.setBounds(50,120,150,30);
			lq.setBounds(50,160,150,30);
                        la.setBounds(50,200,150,30);

       			tu.setBounds(210,80,160,30);
			tno.setBounds(210,120,160,30);
                        cb.setBounds(210,160,160,30);
                        ta.setBounds(210,200,160,30);
			
			b1.setBounds(50,260,100,30);
			b2.setBounds(160,260,100,30);
			b3.setBounds(270,260,100,30);
             
                        b1.setMnemonic('R');
                        b2.setMnemonic('B');
                        b3.setMnemonic('E');

                        //Add Listener
                         b1.addActionListener(this);
                         b2.addActionListener(this);
                         b3.addActionListener(this);

                          try
                {
                cn = DriverManager.getConnection("jdbc:mysql://localhost:3307/komal","root","");
		        stm=cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			rs=stm.executeQuery("select * from shop_keeper");
		
                }
                catch (Exception e)
                {
                e.printStackTrace();
                }

               setVisible(true);
               setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	     }

       public void actionPerformed(ActionEvent e)
       {
       if(e.getSource()==b1)
        {
         if(tu.getText().equals("") ||  tno.getText().equals("") || cb.getSelectedItem().toString().equals("Select Que")) 
         {
        JOptionPane.showMessageDialog(null,"***All Fields Are Neccessary***");
          } 
          else
	    {
               
                  String sql;
                    sql = "select * from shop_keeper where sk_name='"+tu.getText()+"' and sk_phone="+tno.getText()+" and que='"+cb.getSelectedItem()+"' and ans='"+ta.getText()+"'";
                        try
                        {
                            rs = stm.executeQuery(sql);

                            rs.next();
                            JOptionPane.showMessageDialog(null, "Password Successfully Recoverd !!! \n Your Password : " + rs.getString(2) );
                            tu.setText("");
                           
                            tno.setText("");
                            cb.setSelectedItem("Select Que");
                            ta.setText("");
                            tu.requestFocus();
                        }
                        catch (Exception e1)
                        {
                         JOptionPane.showMessageDialog(null,"This is NOT a valid Information !!! \n Try Again !!!","Invalid Information !!!",JOptionPane.ERROR_MESSAGE);
                         tu.setText("");
                         cb.setSelectedItem("Select Que");
                         ta.setText("");
                         tno.setText("");
                         tu.requestFocus();
                        }
                }
        }

  if(e.getSource()==b2)
  {
    login m = new login();
            m.setVisible(true);
            setVisible(false);
  } 

  if(e.getSource()==b3)
  {
  System.exit(0);
  } 

}
public static void main(String args[])
  {
   new Fpass();
  }

}

