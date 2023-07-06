import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.text.*;
import java.sql.*;
import com.itextpdf.text.*;
import com.itextpdf.text.List.*;
import com.itextpdf.text.pdf.*;
import java.awt.Desktop;
import java.io.*;
import java.util.*;

class Billdb extends JFrame implements ActionListener,ItemListener
{
	JComboBox cb,tcname;
	JTextField tbno,tdate,trate,tqty,tcid,tpid,litotal,tgstamt,tgstper,tamt;
	JLabel lbno,ldate,lcname,lpname,lrate,lqty,lcid,lpid,lgstamt,lgstper,total;
	//Font big,small;
	JButton clr,print,remove,back,exit,ok,save;
	DateButton calb1;
	java.awt.List li;
	//String data[];
	
	int id,no1,no2,no3,amt,gstamt,famt,amt1=0,cnt=0;
	
	OutputStream file;
	Document document; 
	Paragraph p;
	PdfPTable table;
	PdfPCell c1;
	
	String sql,sql1;
	Connection cn=null;
	Statement stm;
	PreparedStatement prstm;
	ResultSet rs,rs1,rs2,rs3;
	
	Billdb()
	{
		super("Customer Bill");
		setSize(900,500);
		setLocation(150,150);

		setLayout(null);
		
		tbno=new JTextField("");
		tdate=new JTextField("");
		trate=new JTextField("");
		tqty=new JTextField("1");
		tgstper=new JTextField("");
		tgstamt=new JTextField("");
		/*tdiscount=new JTextField("");
		tamt=new JTextField("");
		tfamt=new JTextField("");
		tpay=new JTextField("");*/
		tcid=new JTextField("");
		tpid=new JTextField("");
		litotal=new JTextField("");
		
		li=new java.awt.List();
		calb1 = new DateButton();
		
		total=new JLabel("Total ");
		lbno=new JLabel("Bill No : ");
		ldate=new JLabel("Date : ");
		lcname=new JLabel("Customer Name : ");
		lpname=new JLabel("Item Name ");
		lrate=new JLabel("Rate ");
		lqty=new JLabel("Quantity  ");
		lgstper=new JLabel("GST % ");
		lgstamt=new JLabel("Final ");
		/*ldiscount=new JLabel("Discount ");
		lamt=new JLabel("Amount ");
		lfamt=new JLabel("Final Amt ");
		lpay=new JLabel("Pay By ");*/
		lcid=new JLabel("Cust_ID:");
		lpid=new JLabel("Prod_ID:");
		
		clr=new JButton("CLEAR");
		print=new JButton("PRINT");
		remove=new JButton("REMOVE");
		back=new JButton("BACK");
		exit=new JButton("EXIT");
		ok=new JButton("OK");
		save=new JButton("SAVE");
		
		//data = new String[3];
		
		cb=new JComboBox();
		tcname=new JComboBox();
		
		add(tbno);	add(tdate);	add(tcname);	add(trate);	add(tqty);	add(tcid);
		add(tpid);	add(calb1);	add(tgstper);
		add(tgstamt);	//add(tdiscount);	//add(tamt);	add(tfamt);	add(tpay);
		add(cb);	add(lcid);	add(lpid);
		add(lbno);	add(ldate);	add(lcname);	add(lpname);	add(lrate);	add(lqty);
		add(lgstper);	add(lgstamt);	//add(ldiscount);	//add(lamt);	add(lfamt);	add(lpay);
		add(clr);	add(print);	add(remove);
		add(back);	add(exit);	add(ok);	add(li);	add(save);
		add(litotal);	add(total);	
		
		tcname.addItem("Select Customer Name");
		cb.addItem("Select Item Name");
		
		lbno.setBounds(50,50,80,30);
		tbno.setBounds(140,50,100,30);
		
		lcid.setBounds(450,50,60,30);
		tcid.setBounds(520,50,60,30);
		ldate.setBounds(270,50,60,30);
		calb1.setBounds(340,50,100,20);
		lcname.setBounds(50,100,140,30);
		tcname.setBounds(200,100,200,30);
		lpid.setBounds(450,100,60,30);
		tpid.setBounds(520,100,60,30);
		lpname.setBounds(50,140,140,30);
		cb.setBounds(50,170,140,30);
		lrate.setBounds(200,140,80,30);
		trate.setBounds(200,170,80,30);
		lqty.setBounds(290,140,80,30);
		tqty.setBounds(290,170,80,30);
		lgstper.setBounds(380,140,100,30);
		tgstper.setBounds(380,170,100,30);
		
		lgstamt.setBounds(280,210,100,30);
		tgstamt.setBounds(380,210,100,30);
		/*lamt.setBounds(280,250,100,30);
		tamt.setBounds(380,250,100,30);
		ldiscount.setBounds(280,290,100,30);
		tdiscount.setBounds(380,290,100,30);
		lfamt.setBounds(280,330,100,30);
		tfamt.setBounds(380,330,100,30);*/
		
		li.setBounds(600,60,150,200);
		total.setBounds(600,280,80,20);
		litotal.setBounds(700,280,80,20);
		ok.setBounds(650,320,100,30);
		
		save.setBounds(50,270,120,30);
		print.setBounds(190,270,120,30);
		clr.setBounds(330,270,120,30);
		back.setBounds(50,320,120,30);
		//remove.setBounds(190,320,120,30);
		exit.setBounds(330,320,120,30);
		
		clr.setMnemonic('C');
		print.setMnemonic('P');
		//remove.setMnemonic('R');
		back.setMnemonic('B');
		exit.setMnemonic('X');
		ok.setMnemonic('O');
		
		clr.setToolTipText("Click here to Clear All Fields");
		print.setToolTipText("Click here to Print Bill");
		remove.setToolTipText("Click here to Remove Entry");
		back.setToolTipText("Click here to Back");
		exit.setToolTipText("Click here to Exit");
		ok.setToolTipText("Click here to Calulate Bill");
		
		//big=new Font(Font.FontFamily.TIMES_ROMAN,18,Font.BOLD);
		//small=new Font(Font.FontFamily.TIMES_ROMAN,12,Font.BOLD);
	
		
		try
		{
			
			cn = DriverManager.getConnection("jdbc:mysql://localhost:3307/addon2","root","");
			stm = cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			
			rs1 = stm.executeQuery("select * from cust_info order by c_name");
			
			while(rs1.next())
				tcname.addItem(rs1.getString(2));
				
			rs2 = stm.executeQuery("select * from product order by pname");
			
			while(rs2.next())
				cb.addItem(rs2.getString(2));
				
			rs = stm.executeQuery("select * from cust_bill order by b_no");
			
			//while(rs.next())
			//tbno.addItem(rs.getString(1));
				
		}
		
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
		cb.addItemListener(this);
		tcname.addItemListener(this);
		
		clr.addActionListener(this);
		print.addActionListener(this);
		remove.addActionListener(this);
		back.addActionListener(this);
		exit.addActionListener(this);
		ok.addActionListener(this);
		save.addActionListener(this);
		
		getMax();
		trate.setEditable(false);
		tbno.setEditable(false);
		tcid.setEditable(false);
		tpid.setEditable(false);
		litotal.setEditable(false);
		tqty.setEditable(false);
		//tgstper.setEditable(false);
		tgstamt.setEditable(false);
		
		setVisible(true);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
	void getMax()
	{
		try
		{
			sql = "select max(b_no) from cust_bill";
			rs = stm.executeQuery(sql);
			rs.next();
			id = rs.getInt(1);
			id++;
			tbno.setText(""+id);
		}
		catch(Exception ex)
		{
			id=1;
			tbno.setText(""+id);
		}
	}

	public void refresh()
	{
			trate.setText("");
			cb.setSelectedIndex(0);
			tcname.setSelectedIndex(0);
			li.clear();
			tgstper.setText("");
			tgstamt.setText("");
			litotal.setText("");
			tcid.setText("");
			tpid.setText("");
			
	}
	
	public void actionPerformed(ActionEvent e)
	{
		try
		{
		if(e.getSource()==print)
		{
			if(trate.getText().length()==0)
			{
				JOptionPane.showMessageDialog(null,"All fields are required !!!","INSERT Error",JOptionPane.WARNING_MESSAGE);
			}
			else
			{
			
			String path="Project.pdf";
			file=new FileOutputStream(new File(path));
			document=new Document();
			PdfWriter.getInstance(document,file);
			document.open();
			
			//PDF File Properties
			document.addTitle("Customer Bill");
			document.addSubject("I am in Subject");
			document.addKeywords("I am in Keywords");
			document.addAuthor("I am Author");
			document.addCreator("I am Creator");
			
			//Write in a PDF
			p=new Paragraph("\t *** JEWELLERY SHOP ***");
			p.setAlignment(Element.ALIGN_CENTER);
			document.add(p);
			document.add(new Paragraph(" "));
			
			
			p=new Paragraph("CELL : 9158147054 / 7350028839 / 7040010400");
			p.setAlignment(Element.ALIGN_CENTER);
			document.add(p);
			document.add(new Paragraph(" "));
			
			p=new Paragraph("Date : "+new java.util.Date());
			p.setAlignment(Element.ALIGN_RIGHT);
			document.add(p);
			document.add(new Paragraph(" "));

			
			p=new Paragraph("Bill No : "+tbno.getText());
			p.setAlignment(Element.ALIGN_LEFT);
			document.add(p);
			document.add(new Paragraph(" "));
			
			p=new Paragraph("Customer Name : "+tcname.getSelectedItem());
			p.setAlignment(Element.ALIGN_LEFT);
			document.add(p);
			document.add(new Paragraph(" "));
			
			PdfPTable table=new PdfPTable(1);
			PdfPCell c1;
			
			//table.addCell(new Phrase("Amount"));
			
		/*	c1=new PdfPCell(new Phrase("Prize"));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			
			table.addCell(c1);
			
			c1=new PdfPCell(new Phrase("AMOUNT"));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(c1);
			
			table.setHeaderRows(1);
			
			 table.addCell(""+cb.getSelectedItem());
			 table.addCell(trate.getText()); 
			 table.addCell(tamt.getText());

			
			*/
			/*List li1=new List(true);
			li1.add(li);
			//list.add(new ListItem("Second Point"));
			//list.add(new ListItem("Third Point"));
			document.add(li1);
			
			document.add(new Paragraph(" "));
			document.add(new Paragraph(" "));*/
			
			
			//cnt=Integer.parseInt(li.getItems());
			
			String[] items=li.getItems();
			
			for(int i=0;i<cnt;i++)
			{
				p=new Paragraph(items[i]);
				p.setAlignment(Element.ALIGN_LEFT);
				document.add(p);
				//document.add(new Paragraph(" "));
			
			}
			document.add(new Paragraph(" "));
			c1=new PdfPCell(new Paragraph("TOTAL AMOUNT = "+litotal.getText()));
			c1.setColspan(3);
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(c1);
			
			document.add(table);
			
			document.add(new Paragraph(" "));
			
			
			
			p=new Paragraph("Signature ");
			p.setAlignment(Element.ALIGN_RIGHT);
			document.add(p);
			document.add(new Paragraph(" "));
			
			document.close();
			file.close();
			
			Desktop desktop=Desktop.getDesktop();
			desktop.open(new java.io.File(path));
			getMax();
			}
		}
		if(e.getSource()==remove)
		{
			getMax();
		}
		
		if(e.getSource()==ok)
		
		{
		
		if(trate.getText().length()==0 || tgstper.getText().length()==0 || tcid.getText().length()==0 || tpid.getText().length()==0)
			{
				JOptionPane.showMessageDialog(null,"All fields are required !!!","INSERT Error",JOptionPane.WARNING_MESSAGE);
			}
			else
			{
			li.add(cb.getSelectedItem()+" - "+trate.getText());
			
			int c = Integer.parseInt(trate.getText());
			amt1 = amt1 + c;
			litotal.setText(Integer.toString(amt1));
			
			int g = Integer.parseInt(tgstper.getText());
			int l = Integer.parseInt(litotal.getText());
				
			int amt2 = l+(l*g)/100;
			tgstamt.setText(Integer.toString(amt2));
			
			cnt=li.getItemCount();
			//tgstamt.setText(""+cnt);
			}
			
		}
		
		if(e.getSource()==save)
		{
			if(trate.getText().length()==0 || tgstamt.getText().length()==0)
			{
				JOptionPane.showMessageDialog(null,"All fields are required !!!","INSERT Error",JOptionPane.WARNING_MESSAGE);
			}
			else
			{
			
			sql = "insert into cust_bill values("+tbno.getText()+",'"+calb1.getText()+"',"+tcid.getText()+","+tpid.getText()+","+litotal.getText()+","+tgstper.getText()+","+tgstamt.getText()+")";
												
					prstm = cn.prepareStatement(sql);
					prstm.execute();
					prstm.close();
					getMax();							
					JOptionPane.showMessageDialog(null,"*** Record Successfully Saved ***");
					
				int g = Integer.parseInt(tgstper.getText());
				int l = Integer.parseInt(litotal.getText());
				
			int amt2 = l+(l*g)/100;
			tgstamt.setText(Integer.toString(amt2));
				
		//original price+(original price*10)/100=final Amount
					
	
			}
		}
		
		if(e.getSource()==clr)
		{		
			refresh();
			
			getMax();
		}
		if(e.getSource()==back)
		{
			new login2();
			dispose();
		}
		
		if(e.getSource()==exit)
		{
			System.exit(0);
		}
		}
		catch(Exception ex)
		{
		
		}
	}
	
	public void itemStateChanged(ItemEvent e)
	{
		try
		{
			if(e.getSource()==tcname)
			{
				sql = "select * from cust_info where c_name='" +tcname.getSelectedItem()+ "'";
				rs1=stm.executeQuery(sql);
                		rs1.next();
				tcid.setText(rs1.getString(1));
                		int z = Integer.parseInt(tcid.getText());
				
				sql = "select * from cust_info where c_name="+z;
                		rs1=stm.executeQuery(sql);
			
			}
			if(e.getSource()==cb)
			{
				sql = "select * from product where ptype='" +cb.getSelectedItem()+ "'";
				rs2=stm.executeQuery(sql);
                		rs2.next();
				tpid.setText(rs2.getString(1));
                		int x = Integer.parseInt(tpid.getText());
				
				sql = "select * from cust_bill where c_id="+x;
                		rs2=stm.executeQuery(sql);

				sql="select * from product where ptype='" +cb.getSelectedItem()+ "'";
				rs3=stm.executeQuery(sql);
				rs3.next();
				trate.setText(rs3.getString(1));
				int y=Integer.parseInt(trate.getText());
				int ad=y+200;
				trate.setText(Integer.toString(ad));
							
			}
			
			}
			
			catch(Exception eee)
			{
			
			}
	}
	
	public static void main(String args[])
	{
		new Billdb();
	}
}


