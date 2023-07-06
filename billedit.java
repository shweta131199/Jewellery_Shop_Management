import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
//import java.text.*;
import java.sql.*;

class billedit extends JFrame implements ActionListener,ItemListener
{
  JLabel l_bno,l_bdt,l_cid,l_cname,l_pid,l_pname,l_price,l_qnt,l_tot,ltitle,l_btm,l_id,l_type,l_head,l_gtot;
  JTextField t_bno,t_cid,t_cname,t_pid,t_pname,t_price,t_qnt,t_tot,t_btm,t_id,t_type,t_gtot;
  DateButton bdt;
  JComboBox cb_ptype;
  List lst;
   JLabel l2;
  
   JButton mdf,bk,prnt;

    PreparedStatement prstm;
     ResultSet rs;
    Statement stm;
    Connection cn = null;
    String sql;
    int bno,tid;
    billedit()
   {
    super("Customer Bill");
    setSize(900,600);
    setLocation(250,250);
    setLayout(null);
  //mem alloc




            Font f1=new Font("Arial",Font.BOLD,20);
            l2 = new JLabel("Bill Modify");
     			l2.setFont(f1);
			//l2.setFont(f1);
			//l2.setForeground(Color.blue);
			
                  l_bno = new JLabel("Bill No.");
         l_bdt = new JLabel("Bill Date:");
         l_btm = new JLabel("Bill Time:");
         l_cid = new JLabel("Customer ID:");
         l_cname = new JLabel("Customer Name:");
         l_pid = new JLabel("Product ID:");
         l_pname = new JLabel("Product Name:");
       
         l_price = new JLabel("Price:");
         l_qnt = new JLabel("Quantity:");
         l_tot = new JLabel("Total:");
      
         l_id = new JLabel("Trans ID");
         l_type=new JLabel("Product Type");
         l_gtot=new JLabel("Total Bill");
         t_gtot=new JTextField();         

         t_type=new JTextField();
         
         t_id = new JTextField();
         t_bno = new JTextField();
          
         l_head = new JLabel("Trans ID's");
         lst = new List();

         bdt = new DateButton();
         cb_ptype = new JComboBox();
         
         t_btm = new JTextField();
         t_cid = new JTextField();
         t_cname = new JTextField();
         t_pid = new JTextField();
         t_pname = new JTextField();
         t_price = new JTextField();
         t_qnt = new JTextField();
         t_tot = new JTextField();
         
          mdf = new  JButton("Modify");
          bk = new  JButton("Back");
          prnt=new JButton("Print");
        add(l_bno);     add(l_bdt);    add(l_cid);   add(l_cname);    add(l_pid);  add(l_btm);  
      add(l_pname);     add(l_price); add(l_qnt);     add(l_tot);   add(l_type);
      add(t_bno);     add(bdt);      add(t_cid);   add(t_cname);    add(t_pid);   
      add(l_id);      add(t_id);     add(l_head);   add(lst);       add(bk);add(prnt);
      add(t_pname);   add(t_price);  add(t_qnt);   add(t_tot);      add(t_btm);  
      add(l_gtot);    add(t_gtot);   add(l2);
     add(mdf);       add(cb_ptype);
        cb_ptype.addItem("Select");
      cb_ptype.addItem("Hair Product");
   cb_ptype.addItem("Skin Product");   cb_ptype.addItem("Body Product");
   cb_ptype.addItem("Eye Product");    cb_ptype.addItem("Nail Product");
   cb_ptype.addItem("Perfume Product");  cb_ptype.addItem("Lip Product");


     
          
    // l_img.setBounds(100,280,600,300);
      // limg1.setBounds(560,210,430,250);
        
         l2.setBounds(350,20,300,30);
       l_id.setBounds(30,70,80,30);
       t_id.setBounds(110,70,50,30);

       l_bno.setBounds(180,70,80,30);
       t_bno.setBounds(270,70,50,30);

      l_bdt.setBounds(340,70,80,30);
      bdt.setBounds(430,70,150,30);
   
      l_btm.setBounds(600,70,80,30);
      t_btm.setBounds(690,70,100,30);
     
      l_cid.setBounds(80,120,100,30);
      t_cid.setBounds(190,120,50,30);

      l_cname.setBounds(320,120,130,30);
      t_cname.setBounds(460,120,150,30);

      l_pid.setBounds(80,170,100,30);
      t_pid.setBounds(190,170,60,30);
 
      l_pname.setBounds(320,170,130,30);
      t_pname.setBounds(460,170,150,30);
 
      l_head.setBounds(630,210,130,30);
      lst.setBounds(630,250,250,200);
      
      l_type.setBounds(180,220,110,30);
     cb_ptype.setBounds(300,220,150,30);

     l_price.setBounds(80,280,60,30);
     t_price.setBounds(150,280,50,30);

     l_qnt.setBounds(230,280,80,30);
     t_qnt.setBounds(320,280,50,30);

     l_tot.setBounds(400,280,60,30);    l_gtot.setBounds(360,340,100,30);
     t_tot.setBounds(470,280,80,30);    t_gtot.setBounds(470,340,100,30);

     mdf.setBounds(180,440,100,30);     bk.setBounds(290,440,100,30);
     prnt.setBounds(400,440,100,30);    
         //add
     
      mdf.addActionListener(this); 
      lst.addItemListener(this);
      bk.addActionListener(this);
      prnt.addActionListener(this);

     try
    {
     cn = DriverManager.getConnection("jdbc:mysql://localhost:3307/addon2","root","");
     stm=cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
     rs=stm.executeQuery("select * from prod_bill order by b_no");
     rs.first();     
     
    }
   catch(Exception e1)
   {
    e1.printStackTrace();
   }
   t_bno.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent e)
      {
         if(e.getKeyChar()==KeyEvent.VK_ENTER)  //for enter key pressed
         {
           try
            {
              lst.clear();
     rs=stm.executeQuery("select t_id from prod_bill where b_no="+t_bno.getText());
    // rs=stm.executeQuery("select * from product where pid="+ t.getText());
        while(rs.next())
       {
         
     lst.addItem(rs.getString(1));
       }     
          }
          catch(SQLException ee) { ee.printStackTrace();}
        try
          {
              rs=stm.executeQuery("select * from bill where b_no="+t_bno.getText());
  
        while(rs.next())
               t_gtot.setText(rs.getString(3));
 
           }
   catch(Exception ee) { ee.printStackTrace();}
    

        }
        
      }
    });

  /*t_id.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent e)
      {
         if(e.getKeyChar()==KeyEvent.VK_ENTER)  //for enter key pressed
         {
           try
            {
              
     rs=stm.executeQuery("select * from prod_bill where t_id="+t_id.getText());
    // rs=stm.executeQuery("select * from product where pid="+ t.getText());
        while(rs.next())
       {
           bdt.setText(rs.getString(3));
          
           t_btm.setText(rs.getString(4));
           t_cname.setText(rs.getString(5));
           t_pname.setText(rs.getString(6));
           cb_ptype.setSelectedItem(rs.getString(7));
           t_cid.setText(rs.getString(8));
           t_pid.setText(rs.getString(9));
           t_price.setText(rs.getString(10));
           t_qnt.setText(rs.getString(11));
           t_tot.setText(rs.getString(12));
           
       }     
          }
          catch(SQLException ee) { ee.printStackTrace();}
        }
      }
    });*/

 t_pid.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent e)
      {
         if(e.getKeyChar()==KeyEvent.VK_ENTER)  //for enter key pressed
         {
           try
            {
              
     rs=stm.executeQuery("select * from product where pid="+t_pid.getText());
    // rs=stm.executeQuery("select * from product where pid="+ t.getText());
        while(rs.next())
      {
    t_pname.setText(rs.getString(3));
             cb_ptype.setSelectedItem(rs.getString(2));
             t_price.setText(rs.getString(6));
            } 
            
          }
          catch(SQLException ee) { ee.printStackTrace();}
        }
      }
    });

   t_qnt.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent e)
      {
         if(e.getKeyChar()==KeyEvent.VK_ENTER)  //for enter key pressed
         {
           try
            {
               rs=stm.executeQuery("select * from product where pid="+t_pid.getText());
    // rs=stm.executeQuery("select * from product where pid="+ t.getText());
        while(rs.next())
      {
         /* int tqnt=Integer.parseInt(rs.getString(4));
          int cqnt=Integer.parseInt(t_qnt.getText());
          if(cqnt<=tqnt)
           {
                int qnt=tqnt-cqnt;
              sql="update product set qnt="+qnt+" where pid="+t_pid.getText();
              prstm = cn.prepareStatement(sql);
               prstm.execute();
               prstm.close();
            JOptionPane.showMessageDialog(null,"Remaining Stock is:"+qnt);*/
              int tot,qnt1,pri;
              qnt1=Integer.parseInt(t_qnt.getText());
              pri=Integer.parseInt(t_price.getText());
              tot=qnt1*pri;
              t_tot.setText(""+tot);
             //}
          /*else
           {
           JOptionPane.showMessageDialog(null,"Stock is not available","avaibility",JOptionPane.WARNING_MESSAGE);
           t_qnt.setText("");
          }*/
       } 
   
                       }
          catch(Exception ee) { ee.printStackTrace();}
         
         try
           {
              sql="update prod_bill set qnt="+t_qnt.getText()+",pid="+t_pid.getText()+",pname='"+t_pname.getText()+"',ptype='"+cb_ptype.getSelectedItem()+"',price="+t_price.getText()+",tot="+t_tot.getText()+" where t_id="+t_id.getText();
    
              prstm = cn.prepareStatement(sql);
               prstm.execute();
              prstm.close();

    JOptionPane.showMessageDialog(null,"Record successfully modyfied...");
              
 
          // t_gtot.setText(rs.getString(3));
         
           }
     catch(Exception ee) { ee.printStackTrace();}

      try
           {
              rs=stm.executeQuery("select sum(tot) from prod_bill where b_no="+t_bno.getText());
             while(rs.next())
              
           t_gtot.setText(rs.getString(1));
         
           }
     catch(Exception ee) { ee.printStackTrace();}
               

                }
      }
    });




 setVisible(true);
 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

public void itemStateChanged(ItemEvent e)
 {
    try
     {
        if(e.getSource()==lst)
         {
             sql="select * from prod_bill where t_id="+lst.getSelectedItem();
               rs=stm.executeQuery(sql);
              rs.next();

             bdt.setText(rs.getString(3));
          t_id.setText(rs.getString(1));
           t_btm.setText(rs.getString(4));
           t_cname.setText(rs.getString(5));
           t_pname.setText(rs.getString(6));
           cb_ptype.setSelectedItem(rs.getString(7));
           t_cid.setText(rs.getString(8));
           t_pid.setText(rs.getString(9));
           t_price.setText(rs.getString(10));
           t_qnt.setText(rs.getString(11));
           t_tot.setText(rs.getString(12));
           
         }
     }
    catch(Exception ee)
    {
       ee.printStackTrace();
    }
 }

public void actionPerformed(ActionEvent e)
 {
    try
   {
     if(e.getSource()==bk)
     {
       new custbill1();
        dispose();
     }
    if(e.getSource()==mdf)
     {
        /* if(bdt.getText().length()==0 || t_btm.getText().length()==0 || t_cid.getText().length()==0 || t_pid.getText().length()==0 || t_qnt.getText().length()==0)*/
     if(t_gtot.getText().length()==0)
     JOptionPane.showMessageDialog(null,"All fields are neccessary");
   else
    {
       
  int ans=JOptionPane.showConfirmDialog(null,"Do you modify record?");
     if(ans==0)
     {
      
     sql="update bill set tot="+t_gtot.getText()+" where b_no="+t_bno.getText();
              
    prstm = cn.prepareStatement(sql);
     prstm.execute();
     prstm.close();
JOptionPane.showMessageDialog(null,"Record successfully modyfied...");

          
          }
    else if(ans==1)
     {
      JOptionPane.showMessageDialog(null,"Your record is not modify");
      }
     }
   }
   if(e.getSource()==prnt)
     {
      try
      {
      new customerbill(Integer.parseInt(t_bno.getText()));
        sql="select * from prod_bill where b_no="+t_bno.getText();
              prstm = cn.prepareStatement(sql);
               prstm.execute();
               prstm.close();
         t_id.setText("");
     t_bno.setText("");
     bdt.setText("");
      t_btm.setText("");
      t_cid.setText("");
      t_cname.setText("");
      t_pid.setText("");
      t_pname.setText("");
      cb_ptype.setSelectedIndex(0);
      t_price.setText("");
      t_qnt.setText("");
      t_tot.setText("");
      lst.clear();
    

     }
     
    catch(Exception ax)
     {
      JOptionPane.showMessageDialog(null,"NO Bill Selected");
     }
   }
  } 
   catch(Exception ex)
    {
       ex.printStackTrace();
    }
 }
 public static void main(String args[])
  {
     new billedit();
  }
}
