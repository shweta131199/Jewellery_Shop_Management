
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

class change extends JFrame implements ActionListener
  {
	
     JButton b1,b2,b3;
     JTextField tname;
     JPasswordField top,tcp,tnwp;
     JLabel lname,lop,lcp,lnwp,ltitle;//limg
    // ImageIcon i;
     Font f;
     //int cnt=0;
     Statement stm;
     Connection cn=null;
     PreparedStatement prstm;
     String sql;
     ResultSet rs;

   change()
       {
            super("change password");
            setSize(500,400);
            setLocation(250,260);
            setLayout(null);
           
            ltitle=new JLabel("CHANGE PASSWORD");
            f=new Font("Arial",Font.BOLD,25);
            ltitle.setFont(f);
  
            //i=new ImageIcon("");
           // limg=new JLabel(i);
	    b1=new JButton("Change");
	    b2=new JButton("Back");
            b3=new JButton("Exit");
	    lname=new JLabel("Enter User name");
            lop=new JLabel("Enter Old Password");
	    lnwp=new JLabel("Enter New Password");
             lcp=new JLabel("Confirm Password");
	    
	    tname=new JTextField();
             tnwp=new JPasswordField();
	    top=new JPasswordField();
	    tcp=new JPasswordField();
	     
	
				add(b1);
				add(b2);
				add(b3);
				add(lname); add(lnwp);
				add(lop);   add(lcp);   //add(limg);
				add(ltitle);
				add(tname);  add(tnwp);
				add(top);   add(tcp); 
				

                        ltitle.setBounds(120,10,300,30);
		     	//limg.setBounds(0,20,500,300);
			lname.setBounds(80,80,150,30);
                        tname.setBounds(240,80,150,30);
			
                        lop.setBounds(80,120,150,30);
			top.setBounds(240,120,150,30);
			
                       lnwp.setBounds(80,160,150,30);
			tnwp.setBounds(240,160,150,30);
			
                        lcp.setBounds(80,200,150,30);
			tcp.setBounds(240,200,150,30);
			
			b1.setBounds(80,250,100,30);
			b2.setBounds(190,250,100,30);
			b3.setBounds(300,250,100,30);
             
                        b1.setMnemonic('C');
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
               setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	     }

       public void actionPerformed(ActionEvent e)
       {
        
          if(e.getSource() == b1)
            {
            if(top.getText().equals(""))
            {
                JOptionPane.showMessageDialog(null,"Enter the User Name !!!","Incomplete Information !!!",JOptionPane.WARNING_MESSAGE);
                top.requestFocus();
            }
            else if(tnwp.getText().equals(""))
            {
                JOptionPane.showMessageDialog(null,"Enter the chng !!!","Incomplete Information !!!",JOptionPane.WARNING_MESSAGE);
                tnwp.requestFocus();
            }
            else if(tcp.getText().equals(""))
            {
                JOptionPane.showMessageDialog(null,"Enter the chng !!!","Incomplete Information !!!",JOptionPane.WARNING_MESSAGE);
                tcp.requestFocus();
            }
                        else
            {
                try
                   {
             String skname;
            skname=tname.getText();
         sql = "update shop_keeper set sk_pass='" +tnwp.getText()+ "' where sk_name='"+skname+"'";
                    prstm = cn.prepareStatement(sql);
                    prstm.execute() ;
                    prstm.close();
                    JOptionPane.showMessageDialog(null, "*** Password Successfully Changed ***");

                          Login ff = new Login();
                          ff.setVisible(true);
                          setVisible(false);
                       
                   }
                catch (Exception e1)   { e1.printStackTrace(); }
            }
        }

        

  if(e.getSource()==b2)
  {
    login2 m1 = new login2();
            m1.setVisible(true);
            setVisible(false);
  } 

  if(e.getSource()==b3)
  {
  System.exit(0);
  } 

}
public static void main(String args[])
  {
   new change();
  }

}

