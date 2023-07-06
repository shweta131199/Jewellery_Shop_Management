import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.Color.*;

class orderInfo extends JFrame implements ActionListener,ItemListener
  {

	
         JTextField t_id,t_nm,t1,t_add,t_qty,t_cmp;
          DateButton t_odate;
	 JLabel o_id,nm,qty,cmp,o_date,l2;
	 JButton b_add,b_del,b_mod,btn_clr,btn_ext,btn_search,btn_bck;
	JPanel p;
 	List l1;
 	JRadioButton rb1,rb2;
 	ButtonGroup b1;
        int id;
	Connection cn; Statement stm; ResultSet rs; 
 	String sql;
 	PreparedStatement prstm; 
	orderInfo()
       {
            super("Order Information");
            setSize(900,450);
            setLocation(100,100);
            setLayout(null);

			
			o_id=new JLabel("Order ID");
			nm=new JLabel("Order Name");
			
			qty=new JLabel("Quantity");
			cmp=new JLabel("Company");
			o_date=new JLabel("Order Date");

			l2=new JLabel("Order Information");

			
			btn_search=new JButton("Search");
			b_add=new JButton("Add");
			b_mod=new JButton("Modify");
			b_del=new JButton("Delete");
			btn_clr=new JButton("Clear");
      			btn_ext=new JButton("Exit");
      			btn_bck=new JButton("Back");
			
			t_id   =new JTextField("");
			t_nm   =new JTextField("");
			
			t_qty  =new JTextField("");
			t_cmp   =new JTextField("");
			t_odate   =new DateButton();


			p=new JPanel();
      			l1=new List();
      			t1=new JTextField();

      			rb1=new JRadioButton("by o_id",true);
      			rb2=new JRadioButton("by o_name");
      			b1=new ButtonGroup();
      			p.setLayout(null);

				add(t_id);						
				add(t_odate);
				
				add(t_qty);
				add(t_cmp);
				add(t_nm);
					
				add(b_add);
				add(b_del);
				add(b_mod);
				
				add(nm);
				add(qty);
				
				add(o_date);
				add(o_id);
				add(cmp);

				
				
				add(btn_clr);
				add(btn_ext);
				add(btn_search);
	                        add(btn_bck);
				add(l2);
				add(p);p.add(t1);p.add(l1); 
				p.add(rb1);p.add(rb2); b1.add(rb1);b1.add(rb2);
			
				Font f1=new Font("",Font.BOLD,20);
     				l2.setFont(f1);
				l2.setFont(f1);
				l2.setForeground(Color.red);
				//4)setBounds to each component
			l2.setBounds(270,20,600,30);			
			o_id.setBounds(60,100,80,30);
			t_id.setBounds(170,100,50,30);
			
					
			nm.setBounds(60,150,100,30);
			t_nm.setBounds(170,150,150,30);

                        qty.setBounds(340,150,80,30);
			t_qty.setBounds(440,150,60,30);

                        cmp.setBounds(60,200,100,30);
			t_cmp.setBounds(170,200,150,30);
			
			o_date.setBounds(340,200,110,30);
			t_odate.setBounds(440,200,120,30);
			
			b_add.setBounds(60,250,110,30);
			b_mod.setBounds(190,250,110,30);
			b_del.setBounds(320,250,110,30);

                        btn_bck.setBounds(190,290,110,30);
			btn_ext.setBounds(320,290,110,30);
			btn_search.setBounds(60,290,110,30);
                        btn_clr.setBounds(450,250,110,60);
			
									
			p.setBounds(580,80,300,250);
      			p.setBackground(Color.PINK);
     			rb1.setBounds(20,20,120,30);
     			rb2.setBounds(160,20,120,30);
     			t1.setBounds(20,60,260,30);
     			l1.setBounds(20,100,260,120);
                        t_id.setEditable(false);

			try
     {
     cn=DriverManager.getConnection("jdbc:mysql://localhost:3307/komal","root","");
     stm=cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
     getMax();
     rs=stm.executeQuery("select * from orders order by o_id");
     rs.first();
     
     }
   catch(Exception e)
    {
    e.printStackTrace();
    }

     

     //5.add Listener      
    btn_clr.addActionListener(this);
    b_add.addActionListener(this);
    b_del.addActionListener(this);
    b_mod.addActionListener(this);
    btn_ext.addActionListener(this);
    btn_search.addActionListener(this);
    btn_bck.addActionListener(this);

    rb1.addActionListener(this);
    rb2.addActionListener(this);
    l1.addItemListener(this);
    t1.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent e)
       {
          if(e.getKeyChar()== KeyEvent.VK_ENTER)
            {
              try
                {
                 l1.clear();
                rs=stm.executeQuery("select * from orders where o_name like '%"+t1.getText()+"%'");
                while(rs.next())
                  l1.addItem(rs.getString(2));
                 }
                catch(Exception e1)
                   {e1.printStackTrace();}
               }
           }
       });

			setVisible(true);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        getMax();
			text_validator(t_qty);
			text_val(t_nm);
		}



     void getMax()
	{
		try
		{
                sql = "select max(o_id) from orders";
                rs = stm.executeQuery(sql);
                rs.next();
                id=rs.getInt(1);
				id++;
				t_id.setText(""+id);
		}
		catch(Exception e)
		{
				id=1;
				t_id.setText(""+id);			
		}
	}

void text_validator(final JTextField tt)
	 {
	 tt.addKeyListener(new KeyAdapter()
	  {
	    public void keyTyped(KeyEvent e)
		{
		  if(tt.getText().length()<5 && e.getKeyChar()>='0'&& e.getKeyChar()<='9')
		  super.keyTyped(e); //optional
		  else
		   {
		      e.consume();   //discard the event
			  Toolkit tk = Toolkit.getDefaultToolkit();
			  tk.beep();
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



		public void actionPerformed(ActionEvent e)
				{
				try
   				{
     
   if(e.getSource()==btn_clr)
    {				


      t_id.setText("");
      t_nm.setText("");
      
      t_qty.setText("");
      t_cmp.setText("");
      t_odate.setText("");
      t1.setText("");
      getMax();
      l1.clear();


         b_mod.setEnabled(true);  
         b_del.setEnabled(true);  
         btn_search.setEnabled(true);  
         b_add.setEnabled(true);
            
    }
    if(e.getSource()==b_add)
    {
	try
	{
         b_mod.setEnabled(false);  
         b_del.setEnabled(false);  
         btn_search.setEnabled(false);  

       if(t_id.getText().length()==0 || t_nm.getText().length()==0 || t_qty.getText().length()==0 || t_cmp.getText().length()==0 ||t_odate.getText().length()==0)
     {
       JOptionPane.showMessageDialog(null,"all fields are necssary ");
	t_id.requestFocus();  
         
	}
    else
      {
       int qty,id;

       String sql,name,odate,cmp;
       
                                                                                                                          
        id=Integer.parseInt(t_id.getText());
	name=t_nm.getText();
	
	qty=Integer.parseInt(t_qty.getText());	
        cmp=t_cmp.getText();
	odate=t_odate.getText();
         
        
         
         
       
          sql="insert into orders values("+id+",'"+name+"',"+qty+",'"+cmp+"','"+odate+"')";
          prstm=cn.prepareStatement(sql);
          prstm.execute();
          prstm.close();
          JOptionPane.showMessageDialog(null,"Record Successfully Inserted********* ");

         t_id.setText("");
      t_nm.setText("");
      
      t_qty.setText("");
      t_cmp.setText("");
      t_odate.setText("");
      t1.setText("");
      getMax();
     // l1.setText("");
            
          
        }
         }
	catch(Exception ex)
	{
	JOptionPane.showMessageDialog(null,"Order already Exist ");
         }
	}
        if(e.getSource()==b_del)
        {
         b_mod.setEnabled(false);  
         
         btn_search.setEnabled(false);  
         b_add.setEnabled(false);

        
	if(t_id.getText().length()==0 || t_nm.getText().length()==0 || t_qty.getText().length()==0 ||t_cmp.getText().length()==0 || t_odate.getText().length()==0)
     {
       JOptionPane.showMessageDialog(null,"all fields are necssary ");
	t_id.requestFocus();
     }
    else
      {
          int id;
          String sql,name,pdate,add;
         id=Integer.parseInt(t_id.getText());
         
          sql="delete from orders where o_id="+id;
          prstm=cn.prepareStatement(sql);
          prstm.execute();
          prstm.close();
          JOptionPane.showMessageDialog(null,"record successfully deleted ");
          
         }
	}
	if(e.getSource()==btn_search)
    {
    
         b_mod.setEnabled(false);  
         b_del.setEnabled(false);  
         b_add.setEnabled(false);

   
    if(rb1.isSelected())
   {
     if(t1.getText().length()==0)
	{
       JOptionPane.showMessageDialog(null,"all fields are necssary ");
     }
    else
      {

    String sql;
    sql="select * from orders where o_id="+t1.getText();
    rs=stm.executeQuery(sql);
    rs.next();
     t_id.setText(rs.getString(1));
    t_nm.setText(rs.getString(2));
   
    t_qty.setText(rs.getString(3));
    t_cmp.setText(rs.getString(4));
    t_odate.setText(rs.getString(5));
    
   }
  }
}
        if(e.getSource()==b_mod)
         {
          try
           {
          b_del.setEnabled(false);  
          btn_search.setEnabled(false);  
          b_add.setEnabled(false);

           if(t_id.getText().length()==0 || t_nm.getText().length()==0 || t_qty.getText().length()==0 || t_cmp.getText().length()==0 || t_odate.getText().length()==0)
     {
       JOptionPane.showMessageDialog(null,"all fields are necssary ");
     }
    else
      {
          int qty,id,size;
         String nm,sql;
         id=Integer.parseInt(t_id.getText());
         qty=Integer.parseInt(t_qty.getText());
       
         nm=t_nm.getText();
        
         sql="update orders set o_name='"+nm+"',o_qnt="+qty+",o_comp="+t_cmp+" where o_id="+id;
         prstm=cn.prepareStatement(sql);
         prstm.execute();
         prstm.close();
         JOptionPane.showMessageDialog(null,"record successfully updated ");
         
         }
         }
       catch(Exception ab)
        { ab.printStackTrace(); }
         }


       if(e.getSource() ==btn_bck)
        {

	login2 ff1=new login2();
            
            ff1.setVisible(true);
            setVisible(false);
        }


        
    
  if(e.getSource()==btn_ext)
    {
     System.exit(0);
    }
    				
	
}

catch(Exception e1)
    {
     e1.printStackTrace();
    } 
}



				

		public void itemStateChanged(ItemEvent ie)
				{
				try
  {
 if(ie.getSource()==l1)
  {
     if(rb2.isSelected())
   {
     
    String sql;
    sql="select * from orders where o_name='"+l1.getSelectedItem() +"'";
    rs=stm.executeQuery(sql);
    rs.next();
    t_id.setText(rs.getString(1));
    t_nm.setText(rs.getString(2));
    
    t_qty.setText(rs.getString(3));
    t_cmp.setText(rs.getString(4));
    t_odate.setText(rs.getString(5));
    

   }
 
   }
  }
  catch(Exception e1)
  {
   e1.printStackTrace();
  }


}

		public  static void  main(String args[])
        {
		new orderInfo();
		}
	}
