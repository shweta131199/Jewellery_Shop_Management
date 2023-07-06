import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

class prod_details extends JFrame implements ActionListener,ItemListener
{
	JLabel lid,lbrd,lmod,limei,lprice,lfrom,ldisp,lstge,lram,ldisk,lbkup,lcam,lver,l,ldid,lh;
	JTextField tid,tbrd,tmod,timei,tprice,tdisp,tstge,tram,tdisk,tbkup,tcam,tver,t,tdid;
	JComboBox cbfrom;
	JButton bclr,badd,bupdt,bdel,bext,bsrch,bbck,bshow;
	JRadioButton rbmod;
	ButtonGroup grp;
	JPanel pnl;
	List li;
	
	String colHead[] = {"P-ID","Brand","Price","Model_No"};	
	String data[][];
	int r_cnt=0,i,id,id1,flg=0;
	
	Connection cn;
	Statement stm;
	ResultSet rs,rs1;
	PreparedStatement prstm;
	String sql;	
	
	
	
	prod_details()
	{
		super("Product Details");
		setSize(1900,1500);
		setLayout(null);
		//setLayout(new FlowLayout());
		lid = new JLabel("Product ID");
		lbrd = new JLabel("Brand");
		lmod = new JLabel("Model No");
		limei = new JLabel("IMEI");
		lprice = new JLabel("Purchase Price");
		lfrom = new JLabel("Purchase From");
		ldisp = new JLabel("Display");
		lstge = new JLabel("Storage");
		lram = new JLabel("RAM");
		ldisk = new JLabel("Hard Disk");
		lbkup = new JLabel("Battery Backup");
		lcam = new JLabel("Camera");
		lver = new JLabel("Andriod Version");
		ldid = new JLabel("Delear ID");
		lh = new JLabel("Computer Details");
		
		tid = new JTextField();
		tbrd = new JTextField();
		tmod = new JTextField();
		timei = new JTextField();
		tprice = new JTextField();
		tdisp = new JTextField();
		tstge = new JTextField();
		tram = new JTextField();
		tdisk = new JTextField();
		tbkup = new JTextField();
		tcam = new JTextField();
		tver = new JTextField();
		tdid = new JTextField();
		cbfrom = new JComboBox();
		
		bclr = new JButton("CLEAR");
		badd = new JButton("SAVE");	
		bupdt = new JButton("UPDATE");
		bdel = new JButton("DELETE");
		bsrch = new JButton("SEARCH");
		bbck = new JButton("BACK");
		bext = new JButton("EXIT");
		bshow = new JButton("SHOW");
		
		rbmod = new JRadioButton("Search by Model No.",true);
		grp = new ButtonGroup();
		
		pnl = new JPanel();
		l = new JLabel("Enter Data");
		t = new JTextField();
		li = new List();
		
		lid.setFont(new Font("Arial",Font.PLAIN,20));
		lbrd.setFont(new Font("Arial",Font.PLAIN,20));	
		lmod.setFont(new Font("Arial",Font.PLAIN,20));
		limei.setFont(new Font("Arial",Font.PLAIN,20));
		lprice.setFont(new Font("Arial",Font.PLAIN,20));
		lfrom.setFont(new Font("Arial",Font.PLAIN,20));
		ldisp.setFont(new Font("Arial",Font.PLAIN,20));
		lstge.setFont(new Font("Arial",Font.PLAIN,20));
		lram.setFont(new Font("Arial",Font.PLAIN,20));
		ldisk.setFont(new Font("Arial",Font.PLAIN,20));
		lbkup.setFont(new Font("Arial",Font.PLAIN,20));
		lcam.setFont(new Font("Arial",Font.PLAIN,20));
		lver.setFont(new Font("Arial",Font.PLAIN,20));
		ldid.setFont(new Font("Arial",Font.PLAIN,20));
		l.setFont(new Font("Arial",Font.PLAIN,20));
		lh.setFont(new Font("Arial",Font.PLAIN,30));
		
		tid.setFont(new Font("Arial",Font.PLAIN,20));
		tbrd.setFont(new Font("Arial",Font.PLAIN,20));	
		tmod.setFont(new Font("Arial",Font.PLAIN,20));
		timei.setFont(new Font("Arial",Font.PLAIN,20));
		tprice.setFont(new Font("Arial",Font.PLAIN,20));
		tdisp.setFont(new Font("Arial",Font.PLAIN,20));
		tstge.setFont(new Font("Arial",Font.PLAIN,20));
		tram.setFont(new Font("Arial",Font.PLAIN,20));
		tdisk.setFont(new Font("Arial",Font.PLAIN,20));
		tbkup.setFont(new Font("Arial",Font.PLAIN,20));
		tcam.setFont(new Font("Arial",Font.PLAIN,20));
		tver.setFont(new Font("Arial",Font.PLAIN,20));
		cbfrom.setFont(new Font("Arial",Font.PLAIN,20));
		tdid.setFont(new Font("Arial",Font.PLAIN,20));
		t.setFont(new Font("Arial",Font.PLAIN,20));
		
		rbmod.setFont(new Font("Arial",Font.PLAIN,20));
			
		bclr.setFont(new Font("Arial",Font.PLAIN,20));
		badd.setFont(new Font("Arial",Font.PLAIN,20));	
		bdel.setFont(new Font("Arial",Font.PLAIN,20));
		bupdt.setFont(new Font("Arial",Font.PLAIN,20));	
		bsrch.setFont(new Font("Arial",Font.PLAIN,20));
		bshow.setFont(new Font("Arial",Font.PLAIN,20));
		bbck.setFont(new Font("Arial",Font.PLAIN,20));
		bext.setFont(new Font("Arial",Font.PLAIN,20));
		
		li.setFont(new Font("Arial",Font.PLAIN,20));
	
		add(lid); add(lbrd); add(lmod);	add(limei);	add(lprice); add(lfrom); add(ldisp);
		add(lstge);	add(lram); add(ldisk); add(lbkup); add(lcam); add(lver); add(ldid);
		add(lh);
		add(tid); add(tbrd); add(tmod); add(timei);	add(tprice); add(tdisp); add(tstge);
		add(tram); add(tdisk); add(tbkup); add(tcam); add(tver); add(tdid);	add(cbfrom);
		add(bclr); add(badd); add(bupdt); add(bdel); add(bsrch); add(bbck); add(bext); add(bshow);
				
		add(rbmod);		grp.add(rbmod);
		cbfrom.addItem("select customer name");
		pnl.setLayout(null);
		pnl.setVisible(false);
		pnl.setBackground(Color.PINK);
		pnl.add(rbmod);		pnl.add(l);			pnl.add(t);	
		pnl.add(li);		add(pnl);
		
		lh.setBounds(350,20,250,100);
		
		lid.setBounds(50,130,120,30);    tid.setBounds(210,130,200,30);
		
		lbrd.setBounds(50,170,120,30);  tbrd.setBounds(210,170,200,30);
		
		lmod.setBounds(50,210,120,30);  tmod.setBounds(210,210,200,30);
		limei.setBounds(50,250,120,30); timei.setBounds(210,250,200,30);
		
		
		lprice.setBounds(50,290,140,30);  tprice.setBounds(210,290,200,30);
		
		lfrom.setBounds(50,330,140,30);   cbfrom.setBounds(210,330,200,30);
		
		ldisp.setBounds(50,370,120,30);    tdisp.setBounds(210,370,200,30);
		
		lstge.setBounds(470,130,120,30);    tstge.setBounds(620,130,200,30);
		
		
		lram.setBounds(470,170,120,30);     tram.setBounds(620,170,200,30);
		
		ldisk.setBounds(470,210,120,30);    tdisk.setBounds(620,210,200,30);
		
		lbkup.setBounds(470,250,150,30);     tbkup.setBounds(620,250,200,30);
		
		lcam.setBounds(470,290,120,30);      tcam.setBounds(620,290,200,30);
		lver.setBounds(470,330,150,30);       tver.setBounds(620,330,200,30);
		ldid.setBounds(470,370,120,30);       tdid.setBounds(620,370,200,30);
		
		bclr.setBounds(50,450,120,30);
		badd.setBounds(230,450,120,30);
		bupdt.setBounds(390,450,120,30);
		bdel.setBounds(50,500,120,30);
		bsrch.setBounds(230,500,120,30);
		bshow.setBounds(390,500,120,30);
		bbck.setBounds(50,550,120,30);
		bext.setBounds(230,550,120,30);
		
		t.setBounds(180,70,250,30);
		l.setBounds(20,70,150,30);
		rbmod.setBounds(10,10,280,30);
		
		pnl.setBounds(850,130,450,890);
		
		li.setBounds(20,150,410,710);
		bclr.setMnemonic('C');
		badd.setMnemonic('S');
		bupdt.setMnemonic('U');
		bdel.setMnemonic('D');
		bsrch.setMnemonic('E');
		bbck.setMnemonic('K');
		bext.setMnemonic('X');
		bshow.setMnemonic('H');
		
		bclr.setToolTipText("Click here to Clear all Fields");
		badd.setToolTipText("It is add new record");
		bupdt.setToolTipText("Click here to Update the Record");
		bdel.setToolTipText("Click here to Delete the Record");
		bsrch.setToolTipText("Click here to Search the Record");
		bbck.setToolTipText("Click here to go Back through this Page");
		bext.setToolTipText("Click here to Exit");
		bshow.setToolTipText("Click here to Show all Records");
						
		try
		{
			cn = DriverManager.getConnection("jdbc:mysql://localhost:3307/komal","root","");
			stm =  cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			
			rs = stm.executeQuery("select * from deal_info order by d_id");
			while(rs.next())
				cbfrom.addItem(rs.getString(2));
			
			rs1 = stm.executeQuery("select * from deal_bill");
			while(rs1.next())
			{
				tbrd.setText(rs1.getString(3));
				int x = Integer.parseInt(rs1.getString(6));
				x = x+1000;
				tprice.setText(""+x);
			}
		}

		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null,"Invalid Operation","Database Connection Error",JOptionPane.ERROR_MESSAGE);
		}
		
		t.addKeyListener(new KeyAdapter()
		{
			public void keyTyped(KeyEvent e)
			{
				if(e.getKeyChar()==KeyEvent.VK_ENTER)
				{
					try
					{
						li.clear();
						if(rbmod.isSelected())
							rs = stm.executeQuery("select * from prod_info where p_modelno like '%"+t.getText()+"%'");
						
						while(rs.next())
							li.addItem(rs.getString(3)+" - "+rs.getString(4));
					}

					catch(Exception ex)
					{
						JOptionPane.showMessageDialog(null,"Invalid Operation","ComboBox Error",JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		
		text_validator(timei);
		text_validator(tprice);
		
		bclr.addActionListener(this);
		badd.addActionListener(this);
		bupdt.addActionListener(this);
		bdel.addActionListener(this);
		bsrch.addActionListener(this);
		bbck.addActionListener(this);
		bshow.addActionListener(this);
		bext.addActionListener(this);
		li.addItemListener(this);
		cbfrom.addItemListener(this);
				
		getMax();
		
		tid.setEditable(false);
		tdid.setEditable(false);
		//tbrd.setEditable(false);
		//tprice.setEditable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	void text_validator(final JTextField tt)
	{
		tt.addKeyListener(new KeyAdapter()
		{
			public void keyTyped(KeyEvent e)
			{
				if(tt.getText().length() < 14 && e.getKeyChar() >= '0' && e.getKeyChar() <= '9')
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
	
	public void refresh()
	{
		tid.setText("");
		tbrd.setText("");
		tmod.setText("");
		tprice.setText("");
		cbfrom.setSelectedIndex(0);
		tdisp.setText("");
		tstge.setText("");
		tram.setText("");
		tdisk.setText("");
		tbkup.setText("");
		tcam.setText("");
		tver.setText("");
		timei.setText("");
		tdid.setText("");
		pnl.setVisible(false);
		badd.setEnabled(true);
	}
	
	void getMax()
	{
		try
		{
			sql = "select max(p_id) from prod_info";
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
	
	public void dispTab()
	{
		try
		{
			rs = stm.executeQuery("select count(*) from prod_info");
			rs.next();
						
			r_cnt = rs.getInt(1);
			data = new String[r_cnt][4];
						
			rs = stm.executeQuery("select * from prod_info order by p_id");
			i=0;
			while(rs.next())
			{
				data[i][0] = rs.getString(1);
				data[i][1] = rs.getString(2);
				data[i][2] = rs.getString(5);
				data[i][3] = rs.getString(3);
				i++;
			}
						
			JTable table = new JTable(data,colHead);
			add(table);
			table.setEnabled(false);
					
			int v = ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
			int h = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;				
						
			JScrollPane jsp = new JScrollPane(table,v,h);
			add(jsp);
			jsp.setBounds(1100,50,500,890);
		}

		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null,"Invalid Operation","Table Error",JOptionPane.ERROR_MESSAGE);
		}
	}
		
	public void actionPerformed(ActionEvent e)
	{
		try
		{
			if(e.getSource()==bclr)
			{
				refresh();
				getMax();
				
			}
			
			if(e.getSource()==badd)
			{
				if(tid.getText().length()==0 || tbrd.getText().length()==0 || tmod.getText().length()==0 || tprice.getText().length()==0 || tdisp.getText().length()==0 || tstge.getText().length()==0 || tram.getText().length()==0 || tdisk.getText().length()==0 || tbkup.getText().length()==0 || tcam.getText().length()==0 || tver.getText().length()==0 || timei.getText().length()==0 || cbfrom.getSelectedIndex()==0)
							
					JOptionPane.showMessageDialog(null,"All fields are required !!!","Insert Error",JOptionPane.WARNING_MESSAGE);     

				else if(tid.getText().length()<5 && tbrd.getText().length()<10 && tmod.getText().length()<5 && tprice.getText().length()<7 && tdisp.getText().length()<5 && tstge.getText().length()<5 && tram.getText().length()<5 && tdisk.getText().length()<5 && tbkup.getText().length()==2 && tcam.getText().length()==2 && tver.getText().length()<3 && timei.getText().length()==13 && cbfrom.getSelectedIndex()>0)
					
				{
					sql = "insert into prod_info values("+tid.getText()+",'"+tbrd.getText()+"','"+tmod.getText()+"','"+timei.getText()+"',"+tprice.getText()+","+tdisp.getText()+","+tstge.getText()+","+tram.getText()+","+tdisk.getText()+","+tbkup.getText()+","+tcam.getText()+","+tver.getText()+","+tdid.getText()+")";
												
					prstm = cn.prepareStatement(sql);
					prstm.execute();
					prstm.close();
												
					JOptionPane.showMessageDialog(null,"*** Record Successfully Saved ***");
					dispTab();
					refresh();
					getMax();
					
				}
				else
				{
					JOptionPane.showMessageDialog(null,"*** Please Check IMI NO ***");
	
				}
			}
		
			if(e.getSource()==bupdt)
			{
				if(flg==0)
					JOptionPane.showMessageDialog(null,"Search the Record Before Update !!!","Update Error",JOptionPane.WARNING_MESSAGE);
				
				else
				{
					if(tid.getText().length()==0 || tbrd.getText().length()==0 || tmod.getText().length()==0 || tprice.getText().length()==0 || tdisp.getText().length()==0 || tstge.getText().length()==0 || tram.getText().length()==0 || tdisk.getText().length()==0 || tbkup.getText().length()==0 || tcam.getText().length()==0 || tver.getText().length()==0 || timei.getText().length()==0)
						
						JOptionPane.showMessageDialog(null,"All fields are required !!!","Update Error",JOptionPane.WARNING_MESSAGE);
					
					else
					{
						sql = "update prod_info set p_brand='"+tbrd.getText()+"',p_modelno='"+tmod.getText()+"',p_imei='"+timei.getText()+"',p_price="+tprice.getText()+",p_display="+tdisp.getText()+",p_storage="+tstge.getText()+",p_ram="+tram.getText()+",p_disk="+tdisk.getText()+",p_battbkup="+tbkup.getText()+",p_camera="+tcam.getText()+",p_verison="+tver.getText()+",d_id="+tdid.getText()+" where p_id="+tid.getText();	
						
						prstm = cn.prepareStatement(sql);
						prstm.execute();
						prstm.close();						
						JOptionPane.showMessageDialog(null,"*** Record Successfully Updated ***");
						dispTab();
						refresh();
						getMax();
						//getMax1();
						flg=0;
					}
				}
			}
		
			if(e.getSource()==bdel)
			{
				try
				{
					if(flg==0)
						JOptionPane.showMessageDialog(null,"Search the Record Before Delete !!!","Delete Error",JOptionPane.WARNING_MESSAGE);
					
					else
					{
						if(tid.getText().length()==0 || tbrd.getText().length()==0 || tmod.getText().length()==0 || tprice.getText().length()==0 || tdisp.getText().length()==0 || tstge.getText().length()==0 || tram.getText().length()==0 || tdisk.getText().length()==0 || tbkup.getText().length()==0 || tcam.getText().length()==0 || tver.getText().length()==0 || timei.getText().length()==0)
							
							JOptionPane.showMessageDialog(null,"All fields are required !!!","Delete Error",JOptionPane.WARNING_MESSAGE);
						
						else
						{
							sql = "delete from prod_info where p_id= "+tid.getText();						
							prstm = cn.prepareStatement(sql);
							prstm.execute();
							prstm.close();						
							JOptionPane.showMessageDialog(null,"*** Record Successfully Deleted ***");
							dispTab();
							refresh();
							getMax();
							//getMax1();
							flg=0;
						}
					}
				}

				catch(Exception ex)
				{
					System.out.println(ex);
					//JOptionPane.showMessageDialog(null,"Can't Delete Product","Delete Error",JOptionPane.ERROR_MESSAGE);
				}
			}
		
			if(e.getSource()==bsrch)
			{
				badd.setEnabled(false);
				pnl.setVisible(true);
				t.requestFocus();
			}
		
			if(e.getSource()==bshow)
			{
				dispTab();
			}
			
			if(e.getSource()==bbck)
			{
				//new choicefrm();
				dispose();
			}
		
			if(e.getSource()==bext)
			{
				System.exit(0);
			}
		}

		catch(Exception ex)
		{
			System.out.println(ex);
			//JOptionPane.showMessageDialog(null,"Invalid Operation","Operation Error",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void itemStateChanged(ItemEvent e)
	{
		try
		{
			if(e.getSource()==cbfrom)
			{
				sql = "select * from deal_info where d_name='" +cbfrom.getSelectedItem()+ "'";
				rs=stm.executeQuery(sql);
                rs.next();
				tdid.setText(rs.getString(1));
                int z = Integer.parseInt(tdid.getText());
				
				sql = "select * from prod_info where d_id="+z;
                rs=stm.executeQuery(sql);
			}
			
			if(e.getSource()==li)
			{
				String arr[] = li.getSelectedItem().split("\\-");
				
				if(rbmod.isSelected())
					sql = "select * from prod_info,deal_info where p_modelno='"+arr[0]+"' AND prod_info.d_id=deal_info.d_id";
				
				rs = stm.executeQuery(sql);
				rs.next();
				tid.setText(rs.getString(1));
				tbrd.setText(rs.getString(2));
				tmod.setText(rs.getString(3));
				timei.setText(rs.getString(4));
				tprice.setText(rs.getString(5));
				tdisp.setText(rs.getString(6));
				tstge.setText(rs.getString(7));
				tram.setText(rs.getString(8));
				tdisk.setText(rs.getString(9));
				tbkup.setText(rs.getString(10));
				tcam.setText(rs.getString(11));
				tver.setText(rs.getString(12));
				tdid.setText(rs.getString(14));
				cbfrom.removeItemListener(this);
				cbfrom.setSelectedItem(rs.getString(15));
		
				cbfrom.addItemListener(this);
				flg=1;
				t.setText("");
				li.clear();
			}
		}

		catch(Exception ex)
		{
			System.out.println(ex);
		//	JOptionPane.showMessageDialog(null,"Invalid Operation","Operation Error",JOptionPane.ERROR_MESSAGE);
		}
	}
public static void main(String args[])
{
       new prod_details();
}
}
