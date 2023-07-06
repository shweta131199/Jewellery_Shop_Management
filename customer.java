import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.sql.*;

class customer extends JFrame implements ActionListener,ItemListener
{
	Font f,f1;
	JLabel lbl_cno,lbl_date,lbl_cname,lbl_cadd,lbl_cphone,lbl_obal,lbl_time,lbl_title,lbl_ser;
	JTextField txt_cno,txt_ser,txt_date,txt_cname,txt_cadd,txt_cphone,txt_obal,txt_time;
	JButton btn_add,btn_back,btn_delete,btn_reset,btn_update;
        JPanel p1;
        List l1;
        
	
	Connection cn;
	Statement stm;
	PreparedStatement prstm;
	String sql;
        ResultSet rs,rs1,rs2;

	
	customer()
	{
	super("Customer Frame");
	setSize(750,500);
	setLocation(250,150);
	setResizable(false);
	setLayout(null);    //Imp
	getContentPane().setBackground(Color.pink);
		
	//2)Memory allocation
	lbl_cno=new JLabel("C No");
	lbl_date=new JLabel("Date.");
	lbl_cname=new JLabel("Customer Name ");
	lbl_cadd=new JLabel("Customer Add  ");
	lbl_cphone=new JLabel("Customer Phone ");
	lbl_obal=new JLabel("Opening Balance  ");
	lbl_time=new JLabel("Time : "); 
	lbl_title=new JLabel("****CUSTOMER RECORDS****");  
	lbl_title.setFont(new Font("Serif",Font.BOLD,25));
	lbl_ser=new JLabel("Search Customer By Name");
	lbl_ser.setFont(new Font("Serif",Font.BOLD,15));
	
	
	txt_ser=new JTextField();
	txt_time=new JTextField();
	txt_cno=new JTextField();
	txt_date=new JTextField();
	txt_cname=new JTextField();
	txt_cadd=new JTextField();
	txt_cphone=new JTextField();
	txt_obal=new JTextField("0");
	
	txt_date.setBackground(Color.pink);
	txt_time.setBackground(Color.pink);

	
	txt_date.setBorder(new LineBorder(Color.pink));		
	txt_time.setBorder(new LineBorder(Color.pink));		

		
	l1=new List();
	p1=new JPanel();

	
	btn_add=new JButton("ADD");
	btn_back=new JButton("Back");
	btn_delete=new JButton("DELETE");
	btn_update=new JButton("UPDATE");
	btn_reset=new JButton("RESET");
	
	btn_back.setBackground(Color.pink);	
	btn_back.setBorder(new LineBorder(Color.pink));			
	txt_date.setEditable(false);
	txt_time.setEditable(false);
	
	
	
	add(lbl_cno);add(lbl_date);add(lbl_cname);add(lbl_cadd);add(lbl_cphone);add(lbl_ser);
	add(txt_cno);add(txt_date);add(txt_cname);add(txt_cadd);add(txt_cphone);	
	add(btn_add);add(btn_back);add(btn_reset);
	add(btn_update);add(btn_delete);
	
	add(txt_time);
	add(p1);
	add(lbl_title);
	add(lbl_time);
	p1.add(txt_ser);
	p1.add(l1);
	
	p1.setLayout(null);
	
	btn_back.setBounds(0,0,100,20);
	lbl_title.setBounds(150,60,500,20);
	lbl_ser.setBounds(420,120,500,20);
	lbl_time.setBounds(100,0,50,20);	txt_time.setBounds(160,0,80,20);
	lbl_cno.setBounds(135,150,50,20);	txt_cno.setBounds(180,150,200,20);
	lbl_date.setBounds(250,0,50,20);	txt_date.setBounds(300,0,100,20);
	lbl_cname.setBounds(55,190,130,20);	txt_cname.setBounds(180,190,200,20);
	lbl_cadd.setBounds(70,230,130,20);	txt_cadd.setBounds(180,230,200,20);
	lbl_cphone.setBounds(50,270,130,20);	txt_cphone.setBounds(180,270,200,20);
	lbl_obal.setBounds(50,310,130,20);	txt_obal.setBounds(180,310,200,20);
	btn_add.setBounds(50,350,155,30);	btn_reset.setBounds(220,350,155,30);
	btn_update.setBounds(50,390,155,30);	btn_delete.setBounds(220,390,155,30);
	p1.setBounds(400,150,300,270);
	l1.setBounds(10,40,280,220);
	txt_ser.setBounds(10,10,280,20);

	txt_cno.setEditable(false);
	
	p1.setBackground(Color.gray);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		btn_add.addActionListener(this);
		btn_back.addActionListener(this);
		btn_delete.addActionListener(this);
		btn_update.addActionListener(this);
		btn_reset.addActionListener(this);	
		l1.addItemListener(this);  
		  
		try
	        {
		        cn=DriverManager.getConnection("jdbc:mysql://localhost:3307/addon2","root","");	
		        stm=cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
		        		
		    	rs1=stm.executeQuery("select MAX(c_no) from customer1");
		        rs1.next();
		        int a=Integer.parseInt(rs1.getString(1));
		        int b=a+1;
       		        txt_cno.setText(""+b);
		        
		        rs=stm.executeQuery("select CURDATE()");
                                        rs.next();
                        txt_date.setText(rs.getString(1));   
                         
                         
                        rs1=stm.executeQuery("select CURTIME()");
		        rs1.next();
		        txt_time.setText(rs1.getString(1));     
	        }
	        catch (Exception e)
	        {			
	                System.out.println(e);
	        }
	        
	        
	        txt_ser.addKeyListener(new KeyAdapter()
	        {
			public void keyPressed(KeyEvent e2)
			{
				if(e2.getKeyChar()==KeyEvent.VK_ENTER)
				{
					try
					{
						l1.clear();
						rs=stm.executeQuery("select *from customer1 where c_name like '%"+txt_ser.getText()+"%'");
						while(rs.next())
						l1.addItem(rs.getString(2));
					}
					catch(Exception e)
					{
						System.out.println(e);
					}
				}	
			}
	        });
	        
	        txt_cphone.addKeyListener(new KeyAdapter()
		{
			public void keyTyped(KeyEvent e)
			{
		
					char c=e.getKeyChar();
					if(Character.isDigit(c) && txt_cphone.getText().length()<10)
					super.keyTyped(e);   //optional
					else
					{
						e.consume();          //Discard the event
						Toolkit tk=Toolkit.getDefaultToolkit();
						tk.beep();           //Raise the sound		  
					}
				}
		});
	
	        
	
	 p1.setVisible(true);       
	 setVisible(true);
	}

	public void itemStateChanged(ItemEvent e1)
	{
		if(e1.getSource()==l1)
		{
			try
			{
				sql="select * from customer1 where c_name='"+l1.getSelectedItem()+"'";
				rs1=stm.executeQuery(sql);
				rs1.next();
				txt_cno.setText(rs1.getString(1));
				txt_cname.setText(rs1.getString(2));
				txt_cadd.setText(rs1.getString(3));
				txt_cphone.setText(rs1.getString(4));
				txt_obal.setText(rs1.getString(5));
			}
			catch(Exception w)
			{}
		}
	}	
	
	 public void actionPerformed(ActionEvent e)
	{
	
		if(e.getSource()==btn_update)
		{

			if(txt_cname.getText().length()==0||txt_cadd.getText().length()==0||txt_cphone.getText().length()==0)
			{
				JOptionPane.showMessageDialog(null,"All Fields Are Necessary");
			}
			else
			{	
		        try
		        {       

               sql="update customer1 set c_name='"+txt_cname.getText()+"',c_address='"+txt_cadd.getText()+"',c_phone="+txt_cphone.getText()+" where c_no="+txt_cno.getText();
		                        
		                        
		                        
		                        prstm=cn.prepareStatement(sql);
		                        prstm.execute();
		                        prstm.close();
		                        JOptionPane.showMessageDialog(null,"Record Updated Successfully");  
		                        
		                        txt_cno.setText("");		         
		                        txt_cname.setText("");
		                        txt_cadd.setText("");
		                        txt_cphone.setText("");
		                        txt_obal.setText("");
		          }
		          catch(Exception e1)
		          {
		                System.out.println(e1);
		         
		         } }
		}
		
			
		
		
		if(e.getSource()==btn_add)
		{
			if(txt_cname.getText().length()==0||txt_cadd.getText().length()==0||txt_obal.getText().length()==0)
			{
				JOptionPane.showMessageDialog(null,"All Fields Are Necessary");
			}
				
			/*if((txt_cphone.getText().isInt())==true)
			{
				JOptionPane.showMessageDialog(null,"Mobile Number Must in Digit Not Character's");
			}*/
			if((txt_cphone.getText().length())!=10)
			{
				txt_cphone.requestFocus();
				JOptionPane.showMessageDialog(null,"Mobile Number Must Be 10 Digit's");
				txt_cphone.setText("");
			}
			else
			{
			        
                                try
                                {
                                        sql="insert into customer1 values("+txt_cno.getText()+",'"+txt_cname.getText()+"','"+txt_cadd.getText()+"',"+txt_cphone.getText()+")";
                                        
		                        prstm=cn.prepareStatement(sql);
		                        prstm.execute();
		                        prstm.close();
		                        JOptionPane.showMessageDialog(null,"Record insert Successfully");  
		                        
		                        rs1=stm.executeQuery("select MAX(c_no) from customer");
		                        rs1.next();
		                        int a=Integer.parseInt(rs1.getString(1));
		                        int b=a+1;
		                        txt_cno.setText(""+b);
		         
		                        txt_cname.setText("");
		                        txt_cadd.setText("");
		                        txt_cphone.setText("");
		                        txt_obal.setText("");
                                }
                                catch(Exception r)
                                {
                                        // JOptionPane.showMessageDialog(null,"Invalid Record Inserted");
                                 }
                        }
		}
		
		if(e.getSource()==btn_back)
		{
		        new login2();
		        dispose();
		}
		if(e.getSource()==btn_reset)
		{
		        txt_cname.setText("");
		        txt_cadd.setText("");
		        txt_cphone.setText("");	
          		txt_obal.setText("");
          	}
		
		
		
		if(e.getSource()==btn_delete)
		{
			if(txt_cname.getText().length()==0||txt_cadd.getText().length()==0||txt_cphone.getText().length()==0||txt_obal.getText().length()==0)
			{
				JOptionPane.showMessageDialog(null,"Please Select The Customer Whose Record Are to be deleted");
			}
			else
			{
		        try
		        {
		        sql="delete from customer1 where c_no="+txt_cno.getText();
		                        prstm=cn.prepareStatement(sql);
		                        prstm.execute();
		                        prstm.close();
		                        JOptionPane.showMessageDialog(null,"Record Deleted Successfully");  
		                        txt_cno.setText("");		         
		                        txt_cname.setText("");
		                        txt_cadd.setText("");
		                        txt_cphone.setText("");
		                        txt_obal.setText("");

		          }
		          catch(Exception e1)
		          {
		                System.out.println(e1);
		          }
		}
		}
	}
	public static void main(String args[])
	{
		new customer();
	}
}
