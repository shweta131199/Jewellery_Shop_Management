
/*

create table bill
(
b_no integer primary key,  
 c_name varchar(30),
 p_name varchar(30),
 price integer,
 gst   float,
t_amt  integer

);


create table bill1
(
  c_id integer primary key,
c_name varchar(30),
c_add varchar(30),
c_phone varchar(10),
c_mail varchar(50)

);
insert into cust_info values(1,"swapnil","pune",9172117833,"swapnil@.com");
insert into bill values(1,'komal','ring',5000,500,5500,10,3);

*/
/*
import javax.swing.table.*;
import java.text.*;
import java.sql.*;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.awt.Desktop;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.applet.*;

*/
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import java.util.Date;
import java.text.SimpleDateFormat;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import javax.swing.table.*;
import java.awt.Font.*;


class Bill_report extends JFrame  implements ActionListener
{
	//Font f;
	JLabel l1,l2,l3,l4,l5,title,lbno;
	JTextField t1,t2,t3,t4,t5,tbno,date;
	JTable tab;
	JButton b1,b2,show,TOTAL,clear,srch,back;
	DefaultTableModel md1;
	JPanel p0,p1;  
	DateButton calb1;
		ButtonGroup grp;
	OutputStream file;
	Document document; 
	Paragraph p;
	PdfPTable table;
	PdfPCell c1,c2;

// Font f;
	
	
//JTable table;
	
	String colHead[] = {"B-NO","C-Name","P_Name","Price","GST","T_amt"};	
	Object data[][] = {};
	int r_cnt=0,i,id,flg=0;
		
	int id1,tamt,price,no1,no3;
	
	
	//	Font f;

		Connection cn=null;
		PreparedStatement prstm;
		Statement stm;
		ResultSet rs;
		String sql;


	
	Bill_report()
	{
			super("Bill_report");
			setSize(800,800);
			setLocation(0,0);
			setLayout(null);
			l1 = new JLabel("Cust Name");
			l2 = new JLabel("Prod Name");
			l3 = new JLabel("Prod Price");
			l4 = new JLabel("GST");
			l5 = new JLabel("Total amt");
			show = new JButton("SHOW");
			clear = new JButton("CLEAR");
			back = new JButton("BACK");
		
	//	f=new Font("Arial",Font.BOLD,25);
		
			calb1 = new DateButton();
			lbno=new JLabel("Bill No : ");
			
			
			
			
			p0 = new JPanel();
		p0.setLayout(null);
	//	p0.setBackground(Color.pink);
		p0.setBounds(80,0,800,40);
	/*
		p1 = new JPanel();
		p1.setLayout(null);
		p1.setBackground(Color.GRAY);
		p1.setBounds(10,110,454,362);
	
	*/
	
/*	dt = new JLabel("Local Time:");
	dt.setFont(new Font("Serif",Font.BOLD,15));*/
		date = new JTextField();/*date.setBackground(Color.GRAY);date.setBorder(new LineBorder(Color.GRAY));	*/
		
		Date d=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("dd-MMM-yyyy");
		date.setText(sdf.format(d));

			
			
			title = new JLabel("Customer Bill");
			t1 = new JTextField();
			t2 = new JTextField();
			t3 = new JTextField();
			t4 = new JTextField("18");
			t5 = new JTextField();
			
			tbno=new JTextField("");
			
			b1 = new JButton("ADD Bill");
			b2 = new JButton("Print");
			TOTAL = new JButton("TOTAL");
			
			
			
			add(l1);			add(tbno);
			add(l2);			add(lbno);	
			add(l3);			add(l4);
			add(t1);
			add(t2);				add(l5);
			add(t3);
			add(b1);			add(TOTAL);
			add(b2);			add(clear);
		//		add(calb1);
			add(title);			add(back);
			add(t4);
			add(t5);
		//	add(show);
				add(p0);    //p0.add(dt); 
		p0.add(date);
	
	date.setBounds(500,0,100,20);	
	
			calb1.setBounds(340,50,100,20);
			l1.setBounds(40,130,120,20);
			l2.setBounds(40,190,120,20);
			l3.setBounds(40,230,120,20);
			l4.setBounds(310,130,120,20);
			l5.setBounds(310,190,120,20);
			show.setBounds(580,380,150,30);
			TOTAL.setBounds(280,300,120,30);
			clear.setBounds(410,300,120,30);
			back.setBounds(550,300,120,30);
			
		lbno.setBounds(50,70,80,30);
		tbno.setBounds(140,70,100,30);
		
		
		
			t1.setBounds(150,130,100,30);
			t2.setBounds(150,180,100,30);
			t3.setBounds(150,230,100,30);
			t4.setBounds(450,130,120,30);
			t5.setBounds(450,190,120,30);
			
			b1.setBounds(40,300,110,30);
			b2.setBounds(160,300,110,30);
			
			
			title.setBounds(300,10,1000,50);
			
			
					show.setMnemonic('h');

			
	//	title.setFont(new Font("Arial",Font.BOLD,40));
	//	title.setFont(new Font("ALGERIAN",Font.BOLD,30));
	/*	l1.setFont(new Font("Arial",Font.PLAIN,20));
	    l2.setFont(new Font("Arial",Font.PLAIN,20));	
		l3.setFont(new Font("Arial",Font.PLAIN,20));
		lbno.setFont(new Font("Arial",Font.PLAIN,20));
		l4.setFont(new Font("Arial",Font.PLAIN,20));
		l5.setFont(new Font("Arial",Font.PLAIN,20));
		
				show.setFont(new Font("Arial",Font.PLAIN,20));
		show.setToolTipText("Click here to Show Record");

		
		b1.setFont(new Font("Arial",Font.PLAIN,20));
		b2.setFont(new Font("Arial",Font.PLAIN,20));	
		TOTAL.setFont(new Font("Arial",Font.PLAIN,20));
		clear.setFont(new Font("Arial",Font.PLAIN,20));
		temail.setFont(new Font("Arial",Font.PLAIN,20));
		t.setFont(new Font("Arial",Font.PLAIN,20));
			
		*/	
		//getMax();
		tbno.setEditable(false);
		t5.setEditable(false);
		t4.setEditable(false);
		date.setEditable(false);
		
		
			b1.addActionListener(this);
			b2.addActionListener(this);
			show.addActionListener(this);
			TOTAL.addActionListener(this);
			clear.addActionListener(this);
			back.addActionListener(this);
			
			
		/*		md1 = new DefaultTableModel(data,colHead);
			tab = new JTable(md1);
			{
				public class getColumnClass(int column);
				{
					switch(column)
					{
						case 0:		return String.class;
						case 1:		return String.class;
						case 2:		return Integer.class;
					}
				}
			};
		*/
			
			
				try
		{
			cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jwellery","root","");
			stm =  cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
		}

		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null,"Invalid Operation","Database Connection Error",JOptionPane.ERROR_MESSAGE);
		}
		
	
			
			
			
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

	}
	
	
	/*
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
	
	*/
	
	
	
		public void dispTab()
	{
		try
		{
			rs = stm.executeQuery("select count(*) from bill");
			rs.next();
						
			r_cnt = rs.getInt(1);
			data = new String[r_cnt][6];
						
			rs = stm.executeQuery("select * from bill order by b_no");
			i=0;
			while(rs.next())
			{
				data[i][0] = rs.getString(1);
				data[i][1] = rs.getString(2);
				data[i][2] = rs.getString(3);
				data[i][3] = rs.getString(4);
				data[i][4] = rs.getString(5);
				data[i][5] = rs.getString(6);
				i++;
			}
						
			tab = new JTable(data,colHead);
			add(tab);
			tab.setEnabled(false);
					
			int v = ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
			int h = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;				
						
			JScrollPane jsp = new JScrollPane(tab,v,h);
			add(jsp);
			jsp.setBounds(50,400,500,200);
		}

		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null,"Invalid Operation","Table Error",JOptionPane.ERROR_MESSAGE);
		}
	}

	
	void getMax()
	{
		try
		{
			sql = "select max(b_no) from bill";
			rs = stm.executeQuery(sql);
			rs.next();
			id1 = rs.getInt(1);
			id1++;
			tbno.setText(""+id1);
		}

		catch(Exception ex)
		{
			id1=1;
			tbno.setText(""+id1);
		}
	}

	
	
	public void actionPerformed(ActionEvent e)
	{
		try
		{
		if(e.getSource()==b2)
		{
			if(tbno.getText().length()==0 || t1.getText().length()==0 || t2.getText().length()==0 || t3.getText().length()==0 || t4.getText().length()==0 || t5.getText().length()==0)
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
			p=new Paragraph("\t SWAPNIL \n *** JEWELLERY SHOP ***");
			p.setAlignment(Element.ALIGN_CENTER);
			document.add(p);
			document.add(new Paragraph(" "));
			
		/*	p=new Paragraph("DEALS IN ALL KINDS OF MOBILE SETS & ACCESSORIES");
			p.setAlignment(Element.ALIGN_CENTER);
			document.add(p);
			document.add(new Paragraph(" "));
		*/	
			p=new Paragraph("CELL : 9307262696 / 7350028839 / 7040010400");
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
			
			p=new Paragraph("Customer Name : "+t1.getText());
			p.setAlignment(Element.ALIGN_LEFT);
			document.add(p);
			document.add(new Paragraph(" "));
			
			PdfPTable table=new PdfPTable(4);
			PdfPCell c1;
			
			table.addCell(new Phrase("PRODUCT NAME"));
			
			c1=new PdfPCell(new Phrase("RATE"));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			
			table.addCell(c1);
			
			
			
			c1=new PdfPCell(new Phrase("GST %"));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(c1);
			
			
			c1=new PdfPCell(new Phrase("TOTAL AMOUNT"));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(c1);
			
			
			table.setHeaderRows(1);
			
		//	 table.addCell(""+cb.getSelectedItem());
		table.addCell(t2.getText());
		table.addCell(t3.getText());		
			 table.addCell(t4.getText()); 
			 table.addCell(t5.getText());
			// table.addCell(t2.getText());
			 

			//c1=new PdfPCell(new Paragraph("Discount= "));
			//c1.setColspan(8);
		//	c1.setHorizontalAlignment(Element.ALIGN_RIGHT);
			//table.addCell(c1);
		/*	table.addCell(tdiscount.getText());
			
			c1=new PdfPCell(new Paragraph("TOTAL AMOUNT = "+tfamt.getText()));
			c1.setColspan(3);
			c1.setHorizontalAlignment(Element.ALIGN_RIGHT);*/
			table.addCell(c1);
			
			document.add(table);
		
		
				//	table.addCell(c2);

			document.add(new Paragraph(" "));
	
	
			p=new Paragraph("ADDRESS : PUNE");
			p.setAlignment(Element.ALIGN_LEFT);
			document.add(p);
			document.add(new Paragraph(" "));
			
			p=new Paragraph("Signature :");
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
		
		if(e.getSource()==clear)
			{
				//tbno.setText("");
				t1.setText("");
				t2.setText("");
				t3.setText("");
				//t4.setText("");
				t5.setText("");
				getMax();
			}
			
			
			
			if(e.getSource()==back)
			{
				new login2();
				dispose();
			}
		
			
			
		
		if(e.getSource()==TOTAL)
		{
			if(t4.getText().length()==0 || t3.getText().length()==0)
			{
				JOptionPane.showMessageDialog(null,"All fields are required !!!","INSERT Error",JOptionPane.WARNING_MESSAGE);
			}
			else
			{
			no3=Integer.parseInt(t4.getText());
		
			no1=Integer.parseInt(t3.getText());
			price=no1+(no1*18)/100;
			t5.setText(""+price);
		/*	
			//no2=Integer.parseInt(tamt.getText());
			
		//	gstamt=no2-no1;
		//	t4.setText(""+gstamt);
			
		//	no3=Integer.parseInt(tdiscount.getText());
			
			//famt=amt-no3;
		//	famt=amt-amt/100*no3;
			
		//	tfamt.setText(""+famt);
			
		//	discount=
			
			//ori price+(ori price*10)/100=final Amount
			getMax();
			//refresh();
	//JTextField tbno,tdate,trate,tqty,tgstper,tgstamt,tdiscount,tamt,tfamt,tpay,tcid,tpid;
			
		/*	sql = "insert into cust_bill values("+tbno.getText()+","+tcid.getText()+","+tpid.getText()+","+trate.getText()+","+tamt.getText()+")";
												
					prstm = cn.prepareStatement(sql);
					prstm.execute();
					prstm.close();
												
					JOptionPane.showMessageDialog(null,"*** Record Successfully Saved ***");
						
					*/
					getMax();
			}
			
		}
		
		
			if(e.getSource()==b1)
			{

				
						try
						{						
						sql="insert into bill values("+tbno.getText()+",'"+t1.getText()+"','"+t2.getText()+"',"+t3.getText()+",'"+t4.getText()+"',"+t5.getText()+")";
					//	sql
		                        prstm=cn.prepareStatement(sql);
		                        prstm.execute();
		                        prstm.close();
		                        JOptionPane.showMessageDialog(null,"Record insert Successfully");
		                        }
		                        catch(Exception ee)
		                        {
						JOptionPane.showMessageDialog(null,"All Field Are necessary");
						getMax();
					//	dispTab();
		                        	//System.out.println(ee);
		                        }	
		}	
	
	
		if(e.getSource()==show)
			{
				dispTab();
			}
			
		}
		catch(Exception ex)
		{
		
		}
	}
			
	
	public static void main(String args[])
	{
		new Bill_report();
	}

	
}	
						
							
			
			
			
			
	


