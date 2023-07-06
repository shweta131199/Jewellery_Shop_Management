import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;


class cust extends JFrame implements ActionListener,ItemListener
{
  
  JButton insrt,dlt,udt,srch,bk,ext,clr;
  
  JLabel lid,lname,ladd,lph,lsrch,ldt,ltitle,limg;
  JTextField tid,tname,tph,tsrch, tadd;
  DateButton tdt;
  ImageIcon i;
  JComboBox cb_srch;
  List l;
  Font f;
  
    Connection cn = null;
    PreparedStatement prstm;
     ResultSet rs;
    Statement stm;
    String sql;
    int cno;
  

cust()
       {
            super("Customer Information");
            setSize(800,400);
            setLocation(150,100);
            setLayout(null);
  
 i=new ImageIcon("");
 limg=new JLabel(i);
 
  lid=new JLabel("Customer ID");
  lname=new JLabel("Customer Name");
  ladd=new JLabel("Customer Address");
  lph=new JLabel("Phone No.");
  lsrch=new JLabel("Search By:");
  ldt=new JLabel("Date");
  ltitle=new JLabel("CUSTOMER DETAILS");
  f=new Font("Arial",Font.BOLD,25);
  ltitle.setFont(f);

  l=new List();

  tid=new JTextField();
  tname=new JTextField();
  tadd=new JTextField();
  tph=new JTextField();
  tsrch=new JTextField();
  tdt=new DateButton();
  cb_srch=new JComboBox();
  insrt=new JButton("Insert");
  dlt=new JButton("Delete");
  udt=new JButton("Update");
  srch=new JButton("Search");
  bk=new JButton("Back");
  ext=new JButton("Exit");
  clr=new JButton("Clear");
  
  
 
   add(lid);     add(lname);  add(ladd);  add(lph);  add(tid);  add(tname);  add(tadd);   add(tph);
   add(insrt);   add(dlt);   add(udt);  add(srch);   add(bk);     add(ext);
      add(ldt);  add(tdt);    add(clr);
   add(cb_srch); add(tsrch); add(lsrch);  add(ltitle); 
   cb_srch.addItem("select");
   cb_srch.addItem("By ID");
   cb_srch.addItem("By Name");
   add(limg);   //add(limg1);

   add(l);
   l.setBounds(510,120,250,200); 
 // limg.setBounds(480,120,300,300);   
  ltitle.setBounds(200,10,300,30);
  lid.setBounds(30,80,100,30);
  tid.setBounds(140,80,50,30);

  lsrch.setBounds(530,50,90,20);
  cb_srch.setBounds(630,50,150,20);
  

  lname.setBounds(210,80,130,30);
  tname.setBounds(350,80,150,30);
 
  tsrch.setBounds(630,80,150,30);

 
  lph.setBounds(30,120,100,30);
  tph.setBounds(140,120,150,30);

   ldt.setBounds(300,120,80,30);
  tdt.setBounds(350,120,150,30);


   ladd.setBounds(30,160,150,30);
  tadd.setBounds(190,160,280,50);


 
  insrt.setBounds(30,230,100,30);
  srch.setBounds(140,230,100,30);
  dlt.setBounds(250,230,100,30);
  udt.setBounds(30,270,100,30);
  
  bk.setBounds(140,270,100,30);
  clr.setBounds(250,270,100,30);
  ext.setBounds(360,230,100,70);
 
   insrt.setMnemonic('I');
   dlt.setMnemonic('D');
   udt.setMnemonic('U');
   srch.setMnemonic('S');
   bk.setMnemonic('B');
   ext.setMnemonic('E');
   clr.setMnemonic('C');

   tid.setEditable(false);

   //insrt.setEnabled(false);

   insrt.addActionListener(this);
   dlt.addActionListener(this);
   udt.addActionListener(this);
   srch.addActionListener(this);
   bk.addActionListener(this);
   ext.addActionListener(this);
   clr.addActionListener(this);
   l.addItemListener(this);
   text_validator(tph);
   text_val(tname);
   try
    {
     cn = DriverManager.getConnection("jdbc:mysql://localhost:3307/addon2","root","");
     stm=cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
     rs=stm.executeQuery("select * from customer order by cid");
     rs.first();     
     
    }
   catch(Exception e1)
   {
    e1.printStackTrace();
   }

tsrch.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent e)
      {
         if(e.getKeyChar()==KeyEvent.VK_ENTER)  //for enter key pressed
         {
           try
            {
              
     rs=stm.executeQuery("select * from customer where cname like '%"+ tsrch.getText() + "%'");
     while(rs.next())
     l.addItem(rs.getString(2));
            
          }
          catch(SQLException ee) { ee.printStackTrace();}
        }
      }
    });

   


   
   setVisible(true);
   setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   getMax();
  }
void text_validator(final JTextField tt)
{
   tt.addKeyListener(new KeyAdapter()
   {
    public void keyTyped(KeyEvent e)
	 {
	  if(tt.getText().length()<10 && e.getKeyChar()>='0' && e.getKeyChar()<='9')
	       super.keyTyped(e); // Optional
	   else
	     {
		   e.consume();  //erase the event
		 
		 }
	 }
	  
   });
  }
 void text_val(final JTextField tt)
{
   tt.addKeyListener(new KeyAdapter()
   {
    public void keyTyped(KeyEvent e)
	 {
	  if(e.getKeyChar()>='a' && e.getKeyChar()<='z' || e.getKeyChar()>='A' && e.getKeyChar()<='Z' || e.getKeyChar()<=' ')
	       super.keyTyped(e); // Optional
	   else
	     {
		   e.consume();  //erase the event
		 
		 }
	 }
	  
   });
  }

  void getMax()
  {
    try
     {
       sql="select max(cid) from customer";
       rs=stm.executeQuery(sql);
       rs.next();
       cno=rs.getInt(1);
         cno++;
         tid.setText(""+cno);
     }
    catch(Exception e2)
    {
      cno=1;
      tid.setText(""+cno);
    }
  }


public void itemStateChanged(ItemEvent e2)
 {
   if(e2.getSource()==l)
   {
    try
     {
     if(cb_srch.getSelectedIndex()==2)
         {
           
            String sql;
           sql="select * from customer where cname='"+l.getSelectedItem()+"'";
           rs=stm.executeQuery(sql);
           rs.next();
           tid.setText(rs.getString(1));
           tname.setText(rs.getString(2));
           tadd.setText(rs.getString(3));
           tph.setText(rs.getString(4));
           tdt.setText(rs.getString(5));
         }
    }
   catch(Exception ex2)
    {
      JOptionPane.showMessageDialog(null,"Record Does Not Exists");}
    } 
   }


public void actionPerformed(ActionEvent e)
  {
    try
    {
    if(e.getSource()==insrt)
    {
      try
      {
        insrt.setEnabled(true);
        srch.setEnabled(false);
        dlt.setEnabled(false);
        udt.setEnabled(false);
        bk.setEnabled(false);
        ext.setEnabled(false);
        
      if(tid.getText().length()==0 || tname.getText().length()==0 || tadd.getText().length()==0 || tph.getText().length()==0 || tdt.getText().length()==0)
   {
     JOptionPane.showMessageDialog(null,"All fields are neccessary");
      
    }
    else if(tph.getText().length()<10)
      {
        JOptionPane.showMessageDialog(null,"Phone Number must be 10 digits");
        tph.setText("");
      }

     else 
    {
       
    int ans=JOptionPane.showConfirmDialog(null,"Do you want to save?");
     if(ans==0)
     {
       
      String phn;
      int cid;
         
      cid=Integer.parseInt(tid.getText());
      
     
    sql = "insert into customer values("+cid+ ",'" +tname.getText()+ "','" +tadd.getText()+ "'," +tph.getText()+ ",'"+tdt.getText()+"')";
     prstm = cn.prepareStatement(sql);
     prstm.execute();
     prstm.close();
    JOptionPane.showMessageDialog(null,"Record successfully added...");
     tid.setText("");
         tname.setText("");
         tadd.setText("");
         tph.setText("");
         tdt.setText("");
         
    }
   
    else if(ans==1)
      JOptionPane.showMessageDialog(null,"Your record is not saved");
      tid.setText("");
         tname.setText("");
         tadd.setText("");
         tph.setText("");
          tdt.setText("");
    }
     }
   catch(Exception ex)
    {   ex.printStackTrace();
      //JOptionPane.showMessageDialog(null,"Record Already Exists");
      tid.setText("");
         tname.setText("");
         tadd.setText("");
         tph.setText("");
         cb_srch.setSelectedIndex(0);
         tsrch.setText("");
          tdt.setText("");

    }
    }
   
   if(e.getSource()==dlt)
    {
     try
     {
      insrt.setEnabled(false);
        srch.setEnabled(false);
      //  dlt.setEnabled(false);
        udt.setEnabled(false);
         bk.setEnabled(false);
        ext.setEnabled(false);
       
       
        
     if(tid.getText().length()==0 || tname.getText().length()==0 || tadd.getText().length()==0 || tph.getText().length()==0)
   {
     JOptionPane.showMessageDialog(null,"All fields are neccessary");
    }

     else
    {
    
 int ans=JOptionPane.showConfirmDialog(null,"Do you want to delete?");
     if(ans==0)
     {
      
      String phn,sql;
      int cid;
         
      cid=Integer.parseInt(tid.getText());
      
     
     sql = "delete from customer where cid="+cid; 
    prstm = cn.prepareStatement(sql);
     prstm.execute();
     prstm.close();
    JOptionPane.showMessageDialog(null,"Record successfully deleted...");
         tid.setText("");
         tname.setText("");
         tadd.setText("");
         tph.setText("");
         tdt.setText("");
        
    }
   
    else if(ans==1)
         JOptionPane.showMessageDialog(null,"Your record is not deleted");
         tid.setText("");
         tname.setText("");
         tadd.setText("");
         tph.setText("");
         tdt.setText("");
    } 
   }
  catch(Exception ex1)
    {
       JOptionPane.showMessageDialog(null,"Record Not Found");
    } 
    }
   if(e.getSource()==udt)
    {
     
       insrt.setEnabled(false);
        srch.setEnabled(false);
        dlt.setEnabled(false);
        //udt.setEnabled(false);
         bk.setEnabled(false);
        ext.setEnabled(false);
        tdt.setEnabled(false);
       
       
        
       if(tid.getText().length()==0 || tname.getText().length()==0 || tadd.getText().length()==0 || tph.getText().length()==0)
   {
     JOptionPane.showMessageDialog(null,"All fields are neccessary");
    }

     else
    {
    
 int ans=JOptionPane.showConfirmDialog(null,"Do you want to modify?");
     if(ans==0)
     {
      
      String phn,sql;
      int cid;
         
      cid=Integer.parseInt(tid.getText());
      
     
    sql = "update customer set cname='"+tname.getText()+"',addr='"+tadd.getText()+"',ph_no='"+tph.getText()+"' where cid="+cid;
      
    prstm = cn.prepareStatement(sql);
     prstm.execute();
     prstm.close();
    JOptionPane.showMessageDialog(null,"Record successfully modified...");
         tid.setText("");
         tname.setText("");
         tadd.setText("");
         tph.setText("");
         tdt.setText("");
    }
   
    else if(ans==1)
      JOptionPane.showMessageDialog(null,"Your record is not modified");
         tid.setText("");
         tname.setText("");
         tadd.setText("");
         tph.setText("");
         tdt.setText("");
    }  
    }
    
   if(e.getSource()==srch)
    {
     try
      {
      insrt.setEnabled(false);
       // srch.setEnabled(false);
        dlt.setEnabled(true);
        udt.setEnabled(true);
         bk.setEnabled(false);
        ext.setEnabled(false);
        if(cb_srch.getSelectedIndex()==0 || tsrch.getText().length()==0)
       {
         JOptionPane.showMessageDialog(null,"select ID or Name and enter corresponding input");
         tsrch.requestFocus();
       }
     else if(cb_srch.getSelectedIndex()==1)
         {
           
           String sql;
           sql="select * from customer where cid="+tsrch.getText();
           rs=stm.executeQuery(sql);
           rs.next();
           tid.setText(rs.getString(1));
           tname.setText(rs.getString(2));
          
           tadd.setText(rs.getString(3));
           tph.setText(rs.getString(4));
           tdt.setText(rs.getString(5));
           
         }

        }
      catch(Exception ab)
       { System.out.println(ab);}


     }
   
   if(e.getSource()==bk)
   {
     login2 m1 = new login2();
            m1.setVisible(true);
            setVisible(false);
  
        }
   if(e.getSource()==ext)
    {
       System.exit(0);
     }
   if(e.getSource()==clr)
   {
     tid.setText("");
      tname.setText("");
      tadd.setText("");
      tph.setText("");
      tdt.setText("");
       l.clear();
      cb_srch.setSelectedIndex(0);
      tsrch.setText("");
      insrt.setEnabled(true);
      udt.setEnabled(true);
      dlt.setEnabled(true);
      srch.setEnabled(true);
      bk.setEnabled(true);
      ext.setEnabled(true);
      getMax();
      tid.setEnabled(false);
   }
  
     }
    
   catch(Exception e1)
    {
       System.out.println(e1);
     }
   }
public static void main(String args[]) 
  {
   new cust();
  }
}

