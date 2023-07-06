import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.text.*;
import java.sql.*;

class cBill extends JFrame implements ActionListener
{
  JLabel l_bno,l_bdt,l_cid,l_cname,l_pid,l_pname,l_price,l_ptype,l_qnt,l_tot,ltitle,l_btm,l_srch,l_id,l_desc,l_note,l_head,l_img,l_dt1,l_tm1,l_tot1;
  JTextField t_bno,t_cid,t_cname,t_pid,t_pname,t_price,t_qnt,t_tot,t_btm,t_srch,t_id,t_tm1,t_tot1;
  DateButton bdt,bdt1;
  JComboBox cb_ptype,cb_srch;
  JButton sve,srch,clr,ext,bill,prnt;
  Font f,f1;
  ImageIcon i;
  
   JTable tab;
   DefaultTableModel mdl;

   Object colHead[]={"Product Name","Quntity","Price"};
   Object data[][]={{"","Total Bill=",new Integer(0)}};
   JScrollPane jsp;
    //int i=0;

    Connection cn = null;
    PreparedStatement prstm;
     ResultSet rs;
    Statement stm;
    String sql;
    int bno,tid,tot1=0;
    
  cBill()
  {
    super("Customer Bill");
    setSize(900,950);
    setLocation(100,50);
    setLayout(null);
  //mem alloc
         i=new ImageIcon("");
         l_img=new JLabel(i);
         ltitle=new JLabel("CUSTOMER BILL");
         l_head = new JLabel("JWELLARY");
         f1 = new Font("Arial",Font.BOLD,25);
         f=new Font("Arial",Font.ITALIC,25);
         ltitle.setFont(f);
         l_head.setFont(f1);
         l_head.setForeground(Color.RED);
         l_desc = new JLabel("Address:A/P-Baramati,SWAPNKOM Complex(1st floor) Shop No:3,Phone:0211225242,9921913913"); 
         l_note=new JLabel("Note:Once the Product is sales then dont get return them");
         l_bno = new JLabel("Bill No.");
         l_bdt = new JLabel("Bill Date:");
         l_btm = new JLabel("Bill Time:");
         l_cid = new JLabel("Customer ID:");
         l_cname = new JLabel("Customer Name:");
         l_pid = new JLabel("Product ID:");
         l_pname = new JLabel("Product Name:");
         l_ptype = new JLabel("Product Type:");
         l_price = new JLabel("Price:");
         l_qnt = new JLabel("Quantity:");
         l_tot = new JLabel("Total:");
         l_srch = new JLabel("Search By:");
         l_id = new JLabel("Trans ID");
         l_dt1=new JLabel("Bill date:");
         l_tm1=new JLabel("Bill Time:");
         l_tot1=new JLabel("Total:");
         t_tot1=new JTextField();
         t_id = new JTextField();
         t_bno = new JTextField();
          
         bdt = new DateButton();
         bdt1=new DateButton();
         t_btm = new JTextField();
         t_cid = new JTextField();
         t_cname = new JTextField();
         t_pid = new JTextField();
         t_pname = new JTextField();
         cb_ptype = new JComboBox();
         cb_srch = new JComboBox();
         t_price = new JTextField();
         t_qnt = new JTextField();
         t_tot = new JTextField();
         t_srch = new JTextField();
         t_tm1=new JTextField();

         sve = new JButton("Save");
         srch = new JButton("Search");
        // mdf = new JButton("Modify");
         clr = new JButton("Clear");
         ext = new JButton("Exit");
         bill = new JButton("Add to Bill");
         prnt = new JButton("Print");

   //add
      add(l_bno);     add(l_bdt);    add(l_cid);   add(l_cname);    add(l_pid);  add(l_btm);   add(l_srch);
      add(l_pname);   add(l_ptype);  add(l_price); add(l_qnt);     add(l_tot);   add(ltitle);
      add(l_desc);    add(l_head);   add(l_img);   add(l_note);    add(l_dt1);   add(l_tm1);   add(l_tot1);
      add(bdt1);      add(t_tm1); 
      add(t_bno);     add(bdt);      add(t_cid);   add(t_cname);    add(t_pid);   add(t_tot1);
      add(l_id);      add(t_id);
      add(t_pname);   add(t_price);  add(t_qnt);   add(t_tot);      add(t_btm);  add(t_srch);
      add(cb_ptype);  add(cb_srch);  cb_srch.addItem("<select>");   cb_srch.addItem("By Bill No.");
      cb_ptype.addItem("Select");
     cb_ptype.addItem("Select");
      cb_ptype.addItem("Earring");
   cb_ptype.addItem("Necklace");   cb_ptype.addItem("Chain");
   cb_ptype.addItem("Bracelet");    cb_ptype.addItem("Ring");
   cb_ptype.addItem("Anklet");  
     add(sve);    add(srch);   add(clr);    add(ext);     add(bill);  
    add(prnt);
 //setBounds

       ltitle.setBounds(320,20,250,30);
   
       l_id.setBounds(30,70,80,30);
       t_id.setBounds(110,70,50,30);

       l_bno.setBounds(180,70,80,30);
       t_bno.setBounds(270,70,50,30);

      l_bdt.setBounds(340,70,80,30);
      bdt.setBounds(430,70,150,30);
   
      l_btm.setBounds(600,70,80,30);
      t_btm.setBounds(690,70,100,30);

      l_srch.setBounds(640,110,100,30);
      cb_srch.setBounds(750,110,100,30);
      t_srch.setBounds(750,150,100,30);

      l_cid.setBounds(80,120,100,30);
      t_cid.setBounds(190,120,50,30);

      l_cname.setBounds(320,120,130,30);
      t_cname.setBounds(460,120,150,30);

      l_pid.setBounds(80,170,100,30);
      t_pid.setBounds(190,170,60,30);
 
      l_pname.setBounds(320,170,130,30);
      t_pname.setBounds(460,170,150,30);

      l_ptype.setBounds(180,220,110,30);
      cb_ptype.setBounds(300,220,150,30);

     l_price.setBounds(80,280,60,30);
     t_price.setBounds(150,280,50,30);

     l_qnt.setBounds(230,280,80,30);
     t_qnt.setBounds(320,280,50,30);

     l_tot.setBounds(400,280,60,30);
     t_tot.setBounds(470,280,80,30);

    sve.setBounds(210,330,100,30);
    srch.setBounds(320,330,100,30);
   // mdf.setBounds(270,330,100,30);
    clr.setBounds(430,330,100,30);
    ext.setBounds(540,330,100,30);
    bill.setBounds(280,370,150,30);
    prnt.setBounds(440,370,100,30);

    sve.setMnemonic('S');
    srch.setMnemonic('r');
    //mdf.setMnemonic('M');
    clr.setMnemonic('C');
    ext.setMnemonic('E');
    bill.setMnemonic('A');
    prnt.setMnemonic('P');

   sve.addActionListener(this);
   srch.addActionListener(this);
  // mdf.addActionListener(this);
   clr.addActionListener(this);
   ext.addActionListener(this);
   bill.addActionListener(this);
   prnt.addActionListener(this);

   t_id.setEditable(false);
   t_bno.setEditable(false);
   t_cname.setEditable(false);
   t_pname.setEditable(false);
   t_price.setEditable(false);
   t_tot.setEditable(false);
   text_validator(t_cid);
   text_validator(t_pid);
   text_validator(t_qnt);
   
   mdl = new DefaultTableModel(data,colHead);
   
   tab = new JTable(mdl)
    {
     public Class getColumnClass(int column)
	{
	
      switch(column)
       {
         //case 0: return Integer.class;
         case 0: return String.class;
         case 1: return Integer.class;
        default: return Integer.class;
       }
    }
   };

   //Resize the table columns
      DefaultTableColumnModel colModel =(DefaultTableColumnModel)tab.getColumnModel();
      TableColumn col=colModel.getColumn(1);
      col=colModel.getColumn(0);        col.setPreferredWidth(200);
      col=colModel.getColumn(1);        col.setPreferredWidth(50);
      col=colModel.getColumn(2);        col.setPreferredWidth(50);
 

    // add(tab);
    // tab.setBounds(50,900,600,400);
     jsp = new JScrollPane(tab);
     add(jsp);  
     l_img.setBounds(50,480,800,150);
     l_head.setBounds(320,340,500,200);
     l_dt1.setBounds(440,470,100,20);    bdt1.setBounds(510,470,150,20);
     l_tm1.setBounds(670,470,100,20);    t_tm1.setBounds(740,470,100,20);
     jsp.setBounds(50,500,800,100);
     l_tot1.setBounds(700,610,100,30);    t_tot1.setBounds(750,610,100,30);
     l_desc.setBounds(100,610,700,100);
     l_note.setBounds(100,630,750,100);
     
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
   
   t_cid.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent e)
      {
         if(e.getKeyChar()==KeyEvent.VK_ENTER)  //for enter key pressed
         {
           try
            {
              
     rs=stm.executeQuery("select * from customer where cid="+t_cid.getText());
    // rs=stm.executeQuery("select * from product where pid="+ t.getText());
        while(rs.next())
     t_cname.setText(rs.getString(2));
            
          }
          catch(SQLException ee) { ee.printStackTrace();}
        }
      }
    });

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
          int tqnt=Integer.parseInt(rs.getString(4));
          int cqnt=Integer.parseInt(t_qnt.getText());
          if(cqnt<=tqnt)
           {
                int qnt=tqnt-cqnt;
              sql="update product set qnt="+qnt+" where pid="+t_pid.getText();
              prstm = cn.prepareStatement(sql);
               prstm.execute();
               prstm.close();
            JOptionPane.showMessageDialog(null,"Remaining Stock is:"+qnt);

            }
          else
           JOptionPane.showMessageDialog(null,"Stock is not available","avaibility",JOptionPane.WARNING_MESSAGE);
           t_qnt.setText("");
       } 
    
              int tot,qnt,pri;
              qnt=Integer.parseInt(t_qnt.getText());
              pri=Integer.parseInt(t_price.getText());
              tot=qnt*pri;
              t_tot.setText(""+tot);
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

   void getMax()
  {
    try
     {
       sql="select max(b_no) from prod_bill";
       rs=stm.executeQuery(sql);
       rs.next();
       bno=rs.getInt(1);
         bno++;
         t_bno.setText(""+bno);
     }
    catch(Exception e2)
    {
      bno=1;
      t_bno.setText(""+bno);
    }
  }

 void getMax1()
  {
    try
     {
       sql="select max(t_id) from prod_bill";
       rs=stm.executeQuery(sql);
       rs.next();
       tid=rs.getInt(1);
         tid++;
         t_id.setText(""+tid);
     }
    catch(Exception e2)
    {
      tid=1;
      t_id.setText(""+tid);
    }
  }


 public void actionPerformed(ActionEvent e)
  {  
     try
      {
     if(e.getSource()==sve)
       {
          srch.setEnabled(false);
        
        //mdf.setEnabled(false);
        //bill.setEnabled(false);
        //prnt.setEnabled(false);
        ext.setEnabled(false);
        
      if(bdt.getText().length()==0 || t_btm.getText().length()==0 || t_cid.getText().length()==0 || t_pid.getText().length()==0 || t_qnt.getText().length()==0)
    {
     JOptionPane.showMessageDialog(null,"All fields are neccessary");
        getMax();
        getMax1();
    }
   else
    {
       
       sql="select max(t_id) from prod_bill where b_no="+t_bno.getText();
         prstm = cn.prepareStatement(sql);
         prstm.execute();
         prstm.close();
     
  int ans=JOptionPane.showConfirmDialog(null,"Do you want to save?");
     if(ans==0)
     {
      
      String sql;
     // int pri,pid;
      
    sql = "insert into prod_bill values("+t_id.getText()+","+t_bno.getText()+ ",'" +bdt.getText()+ "','" +t_btm.getText()+ "','"+t_cname.getText()+"','"+t_pname.getText()+"','"+cb_ptype.getSelectedItem()+"',"+t_cid.getText()+"," +t_pid.getText()+",'"+t_price.getText()+"',"+t_qnt.getText()+",'"+t_tot.getText()+"')";
     prstm = cn.prepareStatement(sql);
     prstm.execute();
     prstm.close();
     JOptionPane.showMessageDialog(null,"Record successfully added...");
      getMax1();
      
      /*t_bno.setText("");
      bdt.setText("");
      t_btm.setText("");
      cb_ptype.setSelectedIndex(0);
      t_cid.setText("");
      t_cname.setText("");
      t_pid.setText("");
      t_pname.setText("");
      t_price.setText("");
      t_qnt.setText("");
      t_tot.setText("");
      //tid.requestFocus();*/
    }
    else if(ans==1)
     {
      JOptionPane.showMessageDialog(null,"Your record is not saved");
      t_btm.setText("");
      bdt.setText("");
      t_cid.setText("");
      t_cname.setText("");
      t_pid.setText("");
      t_pname.setText("");
      cb_ptype.setSelectedIndex(0);
      t_price.setText("");
      t_qnt.setText("");
      t_tot.setText("");
      
     }
    }
        
       }
     if(e.getSource()==srch)
      {
         try
      {
      sve.setEnabled(false);
       // srch.setEnabled(false);
        //mdf.setEnabled(false);
        bill.setEnabled(false);
        prnt.setEnabled(false);
        ext.setEnabled(false);
        
       
        
      if(cb_srch.getSelectedIndex()==0 || t_srch.getText().length()==0)
       {
         JOptionPane.showMessageDialog(null,"select ID and enter corresponding input");
         t_srch.requestFocus();
       }
      else if(cb_srch.getSelectedIndex()==1)
         {
           
           String sql;
           sql="select * from prod_bill where b_no="+t_srch.getText();
           rs=stm.executeQuery(sql);
           rs.next();
           
           t_id.setText(rs.getString(1));
           t_bno.setText(rs.getString(2));
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
   catch(Exception ex2)
    {
      JOptionPane.showMessageDialog(null,"Record Does Not Exists");
    } 
      }
    if(e.getSource()==clr)
     {
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
      sve.setEnabled(true);
      srch.setEnabled(true);
      ext.setEnabled(true);
      bill.setEnabled(true);
      prnt.setEnabled(true);
      getMax();  
     }
    if(e.getSource()==ext)
      {
        System.exit(0);
      }
    if(e.getSource()==bill)
     {
        if(bdt.getText().length()==0 || t_btm.getText().length()==0 || t_cid.getText().length()==0 || t_pid.getText().length()==0 || t_qnt.getText().length()==0)
    {
     JOptionPane.showMessageDialog(null,"All fields are neccessary");
     }
    else
       {
           //to get the total value
           int rno = mdl.getRowCount();
           int tot_val=Integer.parseInt((mdl.getValueAt(rno-1,2)).toString());

         //set/over write new values to last row/total cost row
         //  mdl.setValueAt(t_pid.getText(),rno-1,0);
           mdl.setValueAt(t_pname.getText(),rno-1,0);
           mdl.setValueAt(t_qnt.getText(),rno-1,1);
           mdl.setValueAt(t_price.getText(),rno-1,2);

        //calculate new total
          int tot=Integer.parseInt(t_price.getText())*Integer.parseInt(t_qnt.getText());
          tot_val=tot_val+tot;
       //clear
         
        // t_btm.setText("");
        // bdt.setText("");
        // t_cid.setText("");
        // t_cname.setText("");
         t_pid.setText("");
         t_pname.setText("");
        cb_ptype.setSelectedIndex(0);
         t_price.setText("");
        t_qnt.setText("");
        t_tot.setText("");
        t_btm.requestFocus();
  
     //add new to for total cost
       mdl.addRow(colHead);
       rno=mdl.getRowCount();
        mdl.setValueAt("",rno-1,0);
       //mdl.setValueAt("",rno-1,1);
       mdl.setValueAt("Total Bill",rno-1,1);
       mdl.setValueAt(tot_val,rno-1,2);
         t_tot1.setText(""+tot_val);
        }
     } if(e.getSource()==prnt)
     {tot1=0;
        new customerbill(Integer.parseInt(t_bno.getText()));
       /* sql="select * from prod_bill where b_no="+t_bno.getText();
              prstm = cn.prepareStatement(sql);
               prstm.execute();
               prstm.close();*/

      }
      }
     catch(Exception ex)
      {
        ex.printStackTrace();
      }
  }
 public static void main(String args[])
  {
     new custbill1();
  }
}

