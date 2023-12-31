import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.text.*;
import java.sql.*;

class dealer_details extends JFrame implements ActionListener,ItemListener 
{
	JLabel lid,lnm,laddr,lphnno,lemail,l,title;
	JButton save,updt,dlt,clr,bck,srch,ext,show;
	JTextField tid,tnm,taddr,tphnno,temail,t;
	JRadioButton srch_nm,srch_pno;
	ButtonGroup grp;
	JPanel pnl;
	List li;
	JTable table;
	
	String colHead[] = {"D-ID","Name","Address","Phone No.","Mail-ID"};	
	String data[][];
	int r_cnt=0,i,id,flg=0;
	
	Connection cn;
	Statement stm;
	ResultSet rs;
	PreparedStatement prstm;
	String sql;

	dealer_details()
	{
		super("Dealer Details");
		setSize(1300,550);
		setLocation(0,0);
		setLayout(null);
				
		li = new List();

		lid = new JLabel("Dealer ID");
		lnm = new JLabel("Name");
		laddr = new JLabel("Address");
		lphnno = new JLabel("Contact Number");
		lemail = new JLabel("Email ID");
		l = new JLabel("Enter Data");
		
		title = new JLabel("Dealer Details");
		
		tid = new JTextField();
		tnm = new JTextField();
		taddr = new JTextField();
		tphnno = new JTextField();
		temail = new JTextField();
		t = new JTextField();

		save = new JButton("SAVE");
		updt = new JButton("UPDATE");
		dlt = new JButton("DELETE");
		clr = new JButton("CLEAR");
		bck = new JButton("BACK");
		srch = new JButton("SEARCH");
		ext = new JButton("EXIT");
		show = new JButton("SHOW");
		
		save.setMnemonic('S');
		updt.setMnemonic('U');
		dlt.setMnemonic('D');
		clr.setMnemonic('C');
		bck.setMnemonic('B');
		srch.setMnemonic('E');
		ext.setMnemonic('X');
		show.setMnemonic('H');
		
		save.setToolTipText("Click here to Save the Record");
		updt.setToolTipText("Click here to Update the Record");
		dlt.setToolTipText("Click here to Delete the Record");
		bck.setToolTipText("Click here to go Back through this Page");
		clr.setToolTipText("Click here to Clear all Fields");
		srch.setToolTipText("Click here to Search the Record");
		ext.setToolTipText("Click here to Exit");
		show.setToolTipText("Click here to Show all Records");
		
		srch_nm = new JRadioButton("Search by Name",true);
		srch_pno = new JRadioButton("Search by Contact Number");
		
		grp = new ButtonGroup();
		pnl = new JPanel();
		
		add(srch_nm);		add(srch_pno);
		grp.add(srch_nm);
		grp.add(srch_pno);
		
		
		title.setFont(new Font("Arial",Font.BOLD,40));

		add(lid);	add(lnm);	add(laddr);		add(lphnno);	add(lemail);		
		add(tid);	add(tnm);	add(taddr);		add(tphnno);	add(temail);		
		add(save);	add(updt);	add(dlt);	add(clr);	add(bck);	add(srch);	add(ext);	add(show);	
		
		add(pnl);
		pnl.add(li);
		pnl.add(srch_nm);	pnl.add(srch_pno);
		pnl.add(l);		pnl.add(t);
		pnl.setLayout(null);
		pnl.setVisible(false);		add(title);
		pnl.setBackground(Color.PINK);

		lid.setBounds(50,90,120,30);
		lnm.setBounds(50,120,120,30);
		laddr.setBounds(50,160,120,30);
		lphnno.setBounds(50,200,120,30);
		lemail.setBounds(50,240,120,30);

		tid.setBounds(180,80,200,30);
		tnm.setBounds(180,120,200,30);
		taddr.setBounds(180,160,200,30);
		tphnno.setBounds(180,200,200,30);
		temail.setBounds(180,240,200,30);

		save.setBounds(50,300,100,30);
		updt.setBounds(160,300,100,30);
		dlt.setBounds(270,300,100,30);
		clr.setBounds(50,340,100,30);
		srch.setBounds(160,340,100,30);
		show.setBounds(270,340,100,30);
		bck.setBounds(50,380,155,30);
		ext.setBounds(210,380,155,30);
		
		pnl.setBounds(450,100,330,400);
		srch_nm.setBounds(10,10,120,30);
		srch_pno.setBounds(140,10,180,30);
		l.setBounds(10,50,80,30);
		t.setBounds(100,50,210,30);
		li.setBounds(10,90,310,300);
		
		
		title.setBounds(400,5,1000,50);
		
		try
		{
			cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jwellery","root","");
			stm =  cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
		t.addKeyListener(new KeyAdapter()
		{
			public void keyTyped(KeyEvent e)
			{
				if(srch_nm.isSelected())
				{
					char c = e.getKeyChar();
						
					if(Character.isLetter(c) || c == KeyEvent.VK_SPACE)
						super.keyTyped(e);
					
					else
					{
						e.consume();
						Toolkit tk = Toolkit.getDefaultToolkit();
						tk.beep();
					}
				}
					
				else if(srch_pno.isSelected())
				{
					char c = e.getKeyChar();
					
					if(Character.isDigit(c) && t.getText().length()<10)
						super.keyTyped(e);
					
					else
					{
						e.consume();
						Toolkit tk = Toolkit.getDefaultToolkit();
						tk.beep();
					}
				}
				
				if(e.getKeyChar()==KeyEvent.VK_ENTER)
				{
					try
					{
						li.clear();
						if(srch_nm.isSelected())
							rs = stm.executeQuery("select * from deal_info where d_name like '%"+t.getText()+"%'");
						else
							rs = stm.executeQuery("select * from deal_info where d_phone like '%"+t.getText()+"%'");
						
						while(rs.next())
							li.addItem(rs.getString(2)+" - "+rs.getString(4));
					}
					catch(Exception ex)
					{
						ex.printStackTrace();
					}
				}
			}
		});

		text_validator(tphnno);
		text_validator1(tnm);
		
		save.addActionListener(this);
		updt.addActionListener(this);
		dlt.addActionListener(this);
		clr.addActionListener(this);
		bck.addActionListener(this);
		srch.addActionListener(this);
		ext.addActionListener(this);
		show.addActionListener(this);
		li.addItemListener(this);

		getMax();
		tid.setEditable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
	}
	
	void text_validator(final JTextField tt)
	{
		tt.addKeyListener(new KeyAdapter()
		{
			public void keyTyped(KeyEvent e)
			{
				if(tt.getText().length() < 10 && e.getKeyChar() >= '0' && e.getKeyChar() <= '9')
					super.keyTyped(e);
					
				else
				{
					e.consume();						
					Toolkit tk = Toolkit.getDefaultToolkit();
					tk.beep();
				}
			}
		});
	}
	
	void text_validator1(final JTextField tt)
	{
		tt.addKeyListener(new KeyAdapter()
		{
			public void keyTyped(KeyEvent e)
			{
				if(e.getKeyChar() >= 'A' && e.getKeyChar() <= 'Z' || e.getKeyChar() >= 'a' && e.getKeyChar() <= 'z')
					super.keyTyped(e);
					
				else
				{
					e.consume();						
					Toolkit tk = Toolkit.getDefaultToolkit();
					tk.beep();
				}
			}
		});
	}
	
	public void dispTab()
	{
		try
		{
			rs = stm.executeQuery("select count(*) from deal_info");
			rs.next();
						
			r_cnt = rs.getInt(1);
			data = new String[r_cnt][5];
						
			rs = stm.executeQuery("select * from deal_info order by d_id");
			i=0;
			while(rs.next())
			{
				data[i][0] = rs.getString(1);
				data[i][1] = rs.getString(2);
				data[i][2] = rs.getString(3);
				data[i][3] = rs.getString(4);
				data[i][4] = rs.getString(5);
				i++;
			}
						
			table = new JTable(data,colHead);
			add(table);
			table.setEnabled(false);
					
			int v = ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
			int h = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;				
						
			JScrollPane jsp = new JScrollPane(table,v,h);
			add(jsp);
			jsp.setBounds(820,50,400,400);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	public void refresh()
	{
		tid.setText("");
		tnm.setText("");
		taddr.setText("");
		tphnno.setText("");
		temail.setText("");
		t.setText("");
		pnl.setVisible(false);
		save.setEnabled(true);
	}
	
	void getMax()
	{
		try
		{
			sql = "select max(d_id) from deal_info";
			rs = stm.executeQuery(sql);
			rs.next();
			id = rs.getInt(1);
			id++;
			tid.setText(""+id);
		}
		catch(Exception ex)
		{
			id=1;
			tid.setText(""+id);
		}
	}

	public void actionPerformed(ActionEvent e)
	{
		try
		{
			if(e.getSource()==clr)
			{
				refresh();
				getMax();
			}
			
			if(e.getSource()==save)
			{
				if(tid.getText().length()==0 || tnm.getText().length()==0 || taddr.getText().length()==0 || tphnno.getText().length()==0 || temail.getText().length()==0)
						
					JOptionPane.showMessageDialog(null,"All fields are required !!!","INSERT Error",JOptionPane.WARNING_MESSAGE);     

				else
				{
					sql = "insert into deal_info values("+tid.getText()+",'"+tnm.getText()+"','"+taddr.getText()+"',"+tphnno.getText()+",'"+temail.getText()+"')";
											
					prstm = cn.prepareStatement(sql);
					prstm.execute();
					prstm.close();
											
					JOptionPane.showMessageDialog(null,"*** Record Successfully Saved ***");
					dispTab();
					refresh();
					getMax();
				}
			}
			
		if(e.getSource()==dlt)
			{
				try
				{
					if(flg==0)
						JOptionPane.showMessageDialog(null,"Search the Record Before Delete !!!","Delete Error",JOptionPane.WARNING_MESSAGE);
					
					else
					{
						if(tid.getText().length()==0 || tnm.getText().length()==0 || taddr.getText().length()==0 || tphnno.getText().length()==0 || temail.getText().length()==0)
							
							JOptionPane.showMessageDialog(null,"All fields are required !!!","DELETE Error",JOptionPane.WARNING_MESSAGE);
						
						else
						{
							sql = "delete from deal_info where d_id= "+tid.getText();						
							prstm = cn.prepareStatement(sql);
							prstm.execute();
							prstm.close();						
							JOptionPane.showMessageDialog(null,"*** Record Successfully Deleted ***");
							refresh();
							getMax();
							flg=0;
							dispTab();
						}
					}
				}
				
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(null,"Can't Delete Dealer","Delete Error",JOptionPane.ERROR_MESSAGE);
				}
			}
				
			if(e.getSource()==updt)
			{
				if(flg==0)
					JOptionPane.showMessageDialog(null,"Search the Record Before Update !!!","UPDATE Error",JOptionPane.WARNING_MESSAGE);
				
				else
				{
					if(tid.getText().length()==0 || tnm.getText().length()==0 || taddr.getText().length()==0 || tphnno.getText().length()==0 || temail.getText().length()==0)
						
						JOptionPane.showMessageDialog(null,"All fields are required !!!","UPDATE Error",JOptionPane.WARNING_MESSAGE);
					
					else
					{
						sql = "update deal_info set d_name='"+tnm.getText()+"',d_add='"+taddr.getText()+"',d_phone="+tphnno.getText()+",d_mail='"+temail.getText()+"' where d_id="+tid.getText();						
						prstm = cn.prepareStatement(sql);
						prstm.execute();
						prstm.close();						
						JOptionPane.showMessageDialog(null,"*** Record Successfully Updated ***");
						dispTab();
						refresh();
						getMax();
						flg=0;
					}
				}
			}
			
			if(e.getSource()==show)
			{
				dispTab();
			}
			
			if(e.getSource()==bck)
			{
				new login2();
				dispose();
			}
			
			if(e.getSource()==srch)
			{
				save.setEnabled(false);
				pnl.setVisible(true);
				t.requestFocus();
			}
			
			if(e.getSource()==ext)
				System.exit(0);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	public void itemStateChanged(ItemEvent e)
	{
		try
		{
			if(e.getSource()==li)
			{
				String arr[] = li.getSelectedItem().split("\\-");
				
				if(srch_nm.isSelected())
					sql = "select * from deal_info where d_name = '"+arr[0]+"'";
				else
					sql = "select * from deal_info where d_phone = "+arr[1];
				
				rs = stm.executeQuery(sql);
				rs.next();
				tid.setText(rs.getString(1));
				tnm.setText(rs.getString(2));
				taddr.setText(rs.getString(3));
				tphnno.setText(rs.getString(4));
				temail.setText(rs.getString(5));
				flg=1;
				t.setText("");
				li.clear();
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	public static void main(String args[])
	{
		new dealer_details();
	}
}