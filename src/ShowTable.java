import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ListSelectionModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTable;

import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

import javax.swing.JTextArea;
import javax.xml.transform.Result;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JList;
import javax.swing.JRadioButton;


public class ShowTable extends JFrame implements TableModelListener {

	private JPanel contentPane;
	private JPanel panel,panel_1;
	private Connection conn=null;
	private Statement stmt=null;
	private JTextField textField;
	private JTextField textField_1;
	private JTable table;
	static JFrame form=null;
	private JTextField textField_2;
	private JTextField textField_3;
	private static String sqlStatement;
	private JComboBox comboBox;
	ArrayList<String> rownumber=new ArrayList<String>();
	DbConnect dbsam;
	JLabel lblupdate;
	String[] checkboxname= new String[20];
	int num=0;
	private JTable table_1;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTable table_2;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					String title = null;
					String title2=null;
					
					ShowTable frame = new ShowTable(title,title2, form);
					frame.setVisible(true);
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param string 
	 * @param pas 
	 * @param frame 
	 */
	public ShowTable(String string, String pas, JFrame frame) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 801, 732);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmNew = new JMenuItem("New");
		mnFile.add(mntmNew);
		mntmNew.addActionListener(new menuaction());
		JMenuItem mntmExit = new JMenuItem("Exit");
		mnFile.add(mntmExit);
		mntmExit.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				System.exit(0);
				
			}
		});
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenuItem mntmHowToInsert = new JMenuItem("How To Insert");
		mntmHowToInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(mntmHowToInsert, "Goto Insert Tab and select table from combo box");
			}
		});
		mnHelp.add(mntmHowToInsert);
		
		JMenuItem mntmHowToDelete = new JMenuItem("How To Delete");
		mntmHowToDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(mntmHowToDelete, "In the Show Table Tab Select a row and press delete");
			}
		});
		mnHelp.add(mntmHowToDelete);
		
		JMenuItem mntmHowToUpdate = new JMenuItem("How to Update");
		mntmHowToUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(mntmHowToUpdate, "In the Show Table Tab double click the value you want to update from the table and change the value and press Enter");
			}
		});
		mnHelp.add(mntmHowToUpdate);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSuccess = new JLabel("Wrong  Credentials");
		lblSuccess.setBounds(26, 0, 738, 42);
		contentPane.add(lblSuccess);
		
		 comboBox = new JComboBox();
		
		comboBox.setBounds(46, 39, 126, 26);
		contentPane.add(comboBox);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(15, 74, 734, 555);
		contentPane.add(tabbedPane);
		
		 panel = new JPanel();
		tabbedPane.addTab("Show Table", null, panel, null);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(177, 48, 517, 338);
		panel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		panel_1 = new JPanel();
		tabbedPane.addTab("Insert", null, panel_1, null);
		panel_1.setLayout(null);
		
		JLabel insert = new JLabel("New label");
		insert.setBounds(520, 138, 69, 20);
		panel_1.add(insert);
		
		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_1.setBounds(45, 27, 631, 250);
		
		JTabbedPane tabbedPane_2 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_1.addTab("New tab", null, tabbedPane_2, null);
		
		JTabbedPane tabbedPane_3 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_1.addTab("New tab", null, tabbedPane_3, null);
		
			dbsam=new DbConnect(string,pas);
			 final DefaultListModel model = new DefaultListModel();
				
			form=frame;
			conn=dbsam.login();
			if(conn!=null){
					lblSuccess.setText("Successfully logged ");
				}
			try{
			ResultSet rs=dbsam.show_tablename();
		      while(rs.next()){
		          String first = rs.getString("Tables_in_"+string);
		          comboBox.addItem(first);
		        model.addElement(first);
		      }
			
			}catch(Exception e){}

		ResultSet rs1;
		try {
				rs1=dbsam.gettable(comboBox.getSelectedItem().toString());
			
			table.setModel(DbUtils.resultSetToTableModel(rs1));
		
			
			JButton btnNewButton = new JButton("Delete a Row");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String txt="DELETE FROM "+comboBox.getSelectedItem().toString()+" where ";
					int i=table.getSelectedRow();
					if(i>=0)
					
					{
						
					i++;
					Connection conn=dbsam.login();
					try {
						String primary=null;
						DatabaseMetaData dmd = conn.getMetaData();
						String tableName=comboBox.getSelectedItem().toString();
						ResultSet rser = dmd.getPrimaryKeys(null, null, tableName);
						int k=0;
						String sam=null;
						while(rser.next()){
						if(k==0){
							primary=rser.getString("COLUMN_NAME");
							
								txt=txt+rser.getString("COLUMN_NAME")+" =";
								
								ResultSet rs=dbsam.gettable(comboBox.getSelectedItem().toString());
								int j=1;
								
								while(rs.next()){
									if(j==i){
										sam=rs.getString(primary);
										txt=txt+" "+"'"+sam+"'";
									}
									j++;
								}
						
						k++;
						}
						
						else{
							primary=rser.getString("COLUMN_NAME");
							
							txt=txt+" AND "+rser.getString("COLUMN_NAME")+" =";
							
							ResultSet rs=dbsam.gettable(comboBox.getSelectedItem().toString());
							int j=1;
							
							while(rs.next()){
								if(j==i){
									 sam=rs.getString(primary);
									txt=txt+" "+"'"+sam+"'";
								}
								j++;
							}
						
						}
						
					}
						lblupdate.setText("");
						if(comboBox.getSelectedItem().toString().contains("Titles")){
						String query22="DELETE FROM AuthorISBN where ISBN = '"+sam+"'";
						
						String resul=dbsam.updaterow(query22);
						}
						String del=dbsam.updaterow(txt);
						String syntax="You have an error in your SQL syntax";
						if(del!=null){
							if(del.contains(syntax)){
						
								JOptionPane.showMessageDialog(panel_1, "cannot delete for this table");
								
							}
							
							if(del.contains("Cannot delete or update a parent row")){
								JOptionPane.showMessageDialog(panel_1, "This value is linked with other table");
								
							}
							else{
								JOptionPane.showMessageDialog(panel_1, del);
									
							}
								
						}
						ResultSet result=dbsam.gettable(comboBox.getSelectedItem().toString());
						
						table.setModel(DbUtils.resultSetToTableModel(result));
						
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
					if(i<0){
						lblupdate.setText("Please select a row");
					}
				
					table.getModel().addTableModelListener(new TableModelListener() {
						
						@Override
						public void tableChanged(TableModelEvent e) {
							// TODO Auto-generated method stub
							String txt="UPDATE "+comboBox.getSelectedItem().toString()+" SET ";
							int i=table.getSelectedRow();
							i++;
							int row = e.getFirstRow();
					        int column = e.getColumn();
					        TableModel model = (TableModel)e.getSource();
					        String columnName = model.getColumnName(column);
					        Object data = model.getValueAt(row, column);
					        txt=txt+columnName+"='"+data.toString()+"' where ";
							Connection conn=dbsam.login();
							try {
								String primary=null;
								DatabaseMetaData dmd = conn.getMetaData();
								String tableName=comboBox.getSelectedItem().toString();
								ResultSet rser = dmd.getPrimaryKeys(null, null, tableName);
							//	lblupdate.setText("Cannot perform delete for this table ");
								int k=0;
								while(rser.next()){
								if(k==0){
									primary=rser.getString("COLUMN_NAME");
									
										txt=txt+rser.getString("COLUMN_NAME")+" =";
										
										ResultSet rs=dbsam.gettable(comboBox.getSelectedItem().toString());
										
										int j=1;
										
										while(rs.next()){
											if(j==i){
												String sam=rs.getString(primary);
												txt=txt+" "+"'"+sam+"'";
											}
											j++;
										}
								
										lblupdate.setText(txt);
								k++;
								}
								
								else{
									primary=rser.getString("COLUMN_NAME");
									
									txt=txt+" AND "+rser.getString("COLUMN_NAME")+" =";
									
									ResultSet rs=dbsam.gettable(comboBox.getSelectedItem().toString());
									int j=1;
									
									while(rs.next()){
										if(j==i){
											String sam=rs.getString(primary);
											txt=txt+" "+"'"+sam+"'";
										}
										j++;
									}
							
									
								}
							}
								
								lblupdate.setText("");	
								String del=dbsam.operate_row(txt);
								if(del!=null){
									if(del.contains("You have an error in your SQL syntax")){
										
										JOptionPane.showMessageDialog(panel_1, "cannot update for this table");
										
									}
									else{
										JOptionPane.showMessageDialog(panel_1, del);	
									}
									
								}
								
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
							
							
						}
					});
				
				}
				
			});
			
			btnNewButton.setBounds(15, 123, 147, 29);
			panel.add(btnNewButton);
			
			lblupdate = new JLabel("New label");
			lblupdate.setBounds(32, 16, 644, 20);
			panel.add(lblupdate);
			ResultSetMetaData rsmd = rs1.getMetaData();
			int columnsNumber=dbsam.getcolumnnuber(comboBox.getSelectedItem().toString());
			panel_1.removeAll();
			int h=30;
			sqlStatement="insert into "+comboBox.getSelectedItem().toString()+"(";
			
			for(int i=0;i<columnsNumber;i++){
				JLabel lblNewLabel = new JLabel("New label");
				lblNewLabel.setBounds(40, 52+h, 100, 20);
				panel_1.add(lblNewLabel);
				textField_3 = new JTextField();
				textField_3.setBounds(135, 52+h, 146, 26);
				panel_1.add(textField_3);
				textField_3.setColumns(10);
				String text=rsmd.getColumnName(i+1);
				lblNewLabel.setText("Enter "+text);
				sqlStatement=sqlStatement+text;
				textField_3.setText(text);
				h=h+30;
			
				if(i<columnsNumber-1){
					sqlStatement=sqlStatement+",";
				}
				
			}
			Component[] info = panel_1.getComponents();
			
			for(Component c : info){
				
				if (c instanceof JTextField) {
				 JTextField text=(JTextField) c;   
				text.addFocusListener(new FocusListener() {
					
					@Override
					public void focusLost(FocusEvent e) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void focusGained(FocusEvent e) {
						// TODO Auto-generated method stub
						text.setText("");
					}
				});
				 
				}
			}
			sqlStatement=sqlStatement+") values ( ";
			String oldsql=sqlStatement;
			ArrayList al = new ArrayList();
			JButton btnNewButton_1 = new JButton("Insert into Table");
			
			btnNewButton_1.setBounds(390, 52, 140, 29);
			panel_1.add(btnNewButton_1);
			
			JPanel panel_2 = new JPanel();
			tabbedPane.addTab("Search the DB", null, panel_2, null);
			panel_2.setLayout(null);
			
			JScrollPane scrollPane_1 = new JScrollPane();
			scrollPane_1.setBounds(44, 44, 104, 120);
			panel_2.add(scrollPane_1);
			JList list = new JList(model);
			scrollPane_1.setViewportView(list);
			JComboBox comboBox_2 = new JComboBox();
			comboBox_2.setBounds(496, 43, 55, 26);
			panel_2.add(comboBox_2);
			comboBox_2.addItem(" > ");
			comboBox_2.addItem(" = ");
			comboBox_2.addItem(" < ");
			textField_4 = new JTextField();
			textField_4.setBounds(566, 43, 146, 26);
			panel_2.add(textField_4);
			textField_4.setColumns(10);
			JComboBox comboBox_3 = new JComboBox();
			comboBox_3.setBounds(329, 145, 140, 26);
			panel_2.add(comboBox_3);
					 list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
					 JComboBox comboBox_1 = new JComboBox();
						comboBox_1.setBounds(329, 43, 140, 26);
						DefaultListModel col=new DefaultListModel();
					 	list.addListSelectionListener(new ListSelectionListener() {
							public void valueChanged(ListSelectionEvent e) {
								// TODO Auto-generated method stub
								col.clear();
								comboBox_1.removeAllItems();
								comboBox_3.removeAllItems();
								Object[] i=list.getSelectedValues();
								ArrayList<String> sample=new ArrayList<String>();
								sample=dbsam.multiplecolumns(i);
								for(int j=0;j<sample.size();j++){
									col.addElement(sample.get(j));
									comboBox_1.addItem(sample.get(j));
									comboBox_3.addItem(sample.get(j));
								}
							}
						});


						JScrollPane scrollPane_2 = new JScrollPane();
						scrollPane_2.setBounds(188, 44, 126, 141);
						panel_2.add(scrollPane_2);
						JList list_1 = new JList();
						scrollPane_2.setViewportView(list_1);
						list_1.setModel(col);
						
						list_1.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
						JRadioButton rdbtnAnd = new JRadioButton("AND");
						rdbtnAnd.setBounds(396, 95, 76, 29);
						panel_2.add(rdbtnAnd);
						
						JRadioButton rdbtnOr = new JRadioButton("OR");
						rdbtnOr.setBounds(493, 95, 58, 29);
						panel_2.add(rdbtnOr);

						JComboBox comboBox_4 = new JComboBox();
						comboBox_4.setBounds(503, 145, 48, 26);
						panel_2.add(comboBox_4);
						comboBox_4.addItem(" > ");
						comboBox_4.addItem(" = ");
						comboBox_4.addItem(" < ");
						ButtonGroup group=new ButtonGroup();
						group.add(rdbtnAnd);
						group.add(rdbtnOr);
						textField_5 = new JTextField();
						textField_5.setBounds(568, 145, 146, 26);
						panel_2.add(textField_5);
						textField_5.setColumns(10);
						
						JButton btnRetrieve = new JButton("Retrieve");
						btnRetrieve.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent arg0) {
								
								Object[] tablename=list.getSelectedValues();
								Object[] columnname=list_1.getSelectedValues();
								String query="SELECT ";
								for(int i=0;i<columnname.length;i++){
									query=query+columnname[i];
									if(i<columnname.length-1){
										query=query+",";
									}
								}
								query=query+" from ";
								for(int i=0;i<tablename.length;i++){
									query=query+tablename[i];
									if(i<tablename.length-1){
										query=query+",";
									}
								}
								query=query+" where "+comboBox_1.getSelectedItem().toString()+" "+comboBox_2.getSelectedItem().toString();
								int p=0;
								for(int i=0;i<tablename.length;i++){
									if(textField_4.getText().contains(tablename[i].toString())){
										p++;
									}
								}
								if(p>0){
								
									query=query+textField_4.getText();
									
								}
								if(p==0){
								
								query=query+"'"+textField_4.getText()+"'";
								}
								if(rdbtnAnd.isSelected()){
									query=query+" AND ";
									query=query+comboBox_3.getSelectedItem().toString()+" "+comboBox_4.getSelectedItem().toString();
									for(int i=0;i<tablename.length;i++){
										if(textField_5.getText().contains(tablename[i].toString())){
											p++;
										}
									}
									if(p>0){
									
										query=query+textField_5.getText();
										
									}
									if(p==0){
									
									query=query+"'"+textField_5.getText()+"'";
									}}
								if(rdbtnOr.isSelected()){
									query=query+" OR ";
									query=query+comboBox_3.getSelectedItem().toString()+" "+comboBox_4.getSelectedItem().toString();
									for(int i=0;i<tablename.length;i++){
										if(textField_5.getText().contains(tablename[i].toString())){
											p++;
										}
									}
									if(p>0){
									
										query=query+textField_5.getText();
										
									}
									if(p==0){
									
									query=query+"'"+textField_5.getText()+"'";
									}								}
							lblSuccess.setText("");
							group.clearSelection();
							ResultSet rs=dbsam.getresult(query);
							table_1.setModel(DbUtils.resultSetToTableModel(rs));
							}
						});
						btnRetrieve.setBounds(144, 218, 115, 29);
						panel_2.add(btnRetrieve);
						
						JScrollPane scrollPane_3 = new JScrollPane();
						scrollPane_3.setBounds(15, 272, 699, 233);
						panel_2.add(scrollPane_3);
						
						table_1 = new JTable();
						scrollPane_3.setViewportView(table_1);
						
						panel_2.add(comboBox_1);
						
						JLabel lblSelectTable = new JLabel("Select Table");
						lblSelectTable.setBounds(43, 16, 105, 20);
						panel_2.add(lblSelectTable);
						
						JLabel lblNewLabel_1 = new JLabel("Select Column");
						lblNewLabel_1.setBounds(178, 16, 126, 20);
						panel_2.add(lblNewLabel_1);
						
						JLabel lblWherre = new JLabel("Where");
						lblWherre.setBounds(329, 16, 69, 20);
						panel_2.add(lblWherre);
						JLabel lblValue = new JLabel("Value");
						lblValue.setBounds(566, 16, 69, 20);
						panel_2.add(lblValue);
						
						JLabel label = new JLabel("Value");
						label.setBounds(566, 109, 69, 20);
						panel_2.add(label);
						
						JPanel panel_3 = new JPanel();
						tabbedPane.addTab("SQL Query", null, panel_3, null);
						panel_3.setLayout(null);
						
						textField_6 = new JTextField();
						textField_6.setBounds(38, 119, 676, 44);
						panel_3.add(textField_6);
						textField_6.setColumns(10);
						
						JLabel lblEnterTheQuery = new JLabel("Enter the query");
						lblEnterTheQuery.setBounds(38, 74, 115, 29);
						panel_3.add(lblEnterTheQuery);
						
						JButton btnRetrieve_1 = new JButton("Retrieve");
						btnRetrieve_1.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent arg0) {
								String query=textField_6.getText();
								ResultSet rs=dbsam.getresult(query);
								table_2.setModel(DbUtils.resultSetToTableModel(rs));
								
							}
						});
						btnRetrieve_1.setBounds(247, 186, 115, 29);
						panel_3.add(btnRetrieve_1);
						
						JScrollPane scrollPane_4 = new JScrollPane();
						scrollPane_4.setBounds(38, 243, 676, 229);
						panel_3.add(scrollPane_4);
						
						table_2 = new JTable();
						scrollPane_4.setViewportView(table_2);
						
			panel_1.updateUI();	
			
			btnNewButton_1.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					Component[] info = panel_1.getComponents();
					
					for (Component c : info) {
					    if (c instanceof JTextField) {
					        JTextField textfield = (JTextField) c;
					          textfield.setEditable(true);
					          al.add(textfield.getText()); 
					    }
					  }
					for(int i=0;i<al.size();i++){
						sqlStatement=sqlStatement+"'"+al.get(i)+"'";
						if(i<al.size()-1){
							sqlStatement=sqlStatement+",";
						}
					}
					al.clear();
						sqlStatement=sqlStatement+" )";
						lblSuccess.setText(sqlStatement);

						String ss=dbsam.operate_row(sqlStatement);
						ResultSet result=dbsam.gettable(comboBox.getSelectedItem().toString());
						table.setModel(DbUtils.resultSetToTableModel(result));
						if(ss!=null){
							JOptionPane.showMessageDialog(panel_1, ss);
						}
						sqlStatement=oldsql;
					}
			
			
			});
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String nn=comboBox.getSelectedItem().toString();
				try {
					ResultSet rs = dbsam.gettable(comboBox.getSelectedItem().toString());
					
					ResultSetMetaData rsmd = rs.getMetaData();

					int columnsNumber = dbsam.getcolumnnuber(comboBox.getSelectedItem().toString());


					ResultSet rs1 = dbsam.gettable(comboBox.getSelectedItem().toString());
					
					table.setModel(DbUtils.resultSetToTableModel(rs1));
					lblSuccess.setText("");
					panel_1.removeAll();
					sqlStatement="insert into "+comboBox.getSelectedItem().toString()+"(";
					
					int h=30;
					for(int i=0;i<columnsNumber;i++){
						JLabel lblNewLabel = new JLabel("New label");
						lblNewLabel.setBounds(40, 52+h, 100, 20);
						panel_1.add(lblNewLabel);
						textField_3 = new JTextField();
						textField_3.setBounds(135, 52+h, 146, 26);
						panel_1.add(textField_3);
						textField_3.setColumns(10);
						String text=rsmd.getColumnName(i+1);
						lblNewLabel.setText("Enter "+text);
						sqlStatement=sqlStatement+text;
						textField_3.setText(text);
						h=h+30;
						panel_1.repaint();;
						if(i<columnsNumber-1){
							sqlStatement=sqlStatement+",";
						}
					}
					
					Component[] info = panel_1.getComponents();
					sqlStatement=sqlStatement+") values ( ";
					String oldsql=sqlStatement;
					ArrayList al = new ArrayList();
					for(Component c : info){
						
						if (c instanceof JTextField) {
						 JTextField text=(JTextField) c;   
						text.addFocusListener(new FocusListener() {
							
							@Override
							public void focusLost(FocusEvent e) {
								// TODO Auto-generated method stub
								
							}
							
							@Override
							public void focusGained(FocusEvent e) {
								// TODO Auto-generated method stub
								text.setText("");
							}
						});
						 
						}
					}
					JButton btnNewButton_1 = new JButton("Insert into Table");
					btnNewButton_1.setBounds(390, 52, 140, 29);
					panel_1.add(btnNewButton_1);
					
					btnNewButton_1.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
										
							  for (Component c : info) {
								    if (c instanceof JTextField) {
								        JTextField textfield = (JTextField) c;
								          textfield.setEditable(true);
								    al.add(textfield.getText()); 
								    }
								  }
							  
							  for(int i=0;i<al.size();i++){
									sqlStatement=sqlStatement+"'"+al.get(i)+"'";
									if(i<al.size()-1){
										sqlStatement=sqlStatement+",";
									}
								}
							  al.clear();
							  sqlStatement=sqlStatement+" )";
								lblSuccess.setText("");
								String del=dbsam.operate_row(sqlStatement);
								ResultSet result=dbsam.gettable(comboBox.getSelectedItem().toString());
								table.setModel(DbUtils.resultSetToTableModel(result));
								if(del!=null){
									JOptionPane.showMessageDialog(panel_1, del);
								}
								sqlStatement=oldsql;
					
								table.getModel().addTableModelListener(new TableModelListener() {
									
									@Override
									public void tableChanged(TableModelEvent e) {
										// TODO Auto-generated method stub
										String txt="UPDATE "+comboBox.getSelectedItem().toString()+" SET ";
										int i=table.getSelectedRow();
										i++;
										int row = e.getFirstRow();
								        int column = e.getColumn();
								        TableModel model = (TableModel)e.getSource();
								        String columnName = model.getColumnName(column);
								        Object data = model.getValueAt(row, column);
								        txt=txt+columnName+"='"+data.toString()+"' where ";
										Connection conn=dbsam.login();
										try {
											String primary=null;
											DatabaseMetaData dmd = conn.getMetaData();
											String tableName=comboBox.getSelectedItem().toString();
											ResultSet rser = dmd.getPrimaryKeys(null, null, tableName);
										//	lblupdate.setText("Cannot perform delete for this table ");
											int k=0;
											while(rser.next()){
											if(k==0){
												primary=rser.getString("COLUMN_NAME");
												
													txt=txt+rser.getString("COLUMN_NAME")+" =";
													
													ResultSet rs=dbsam.gettable(comboBox.getSelectedItem().toString());
													
													int j=1;
													
													while(rs.next()){
														if(j==i){
															String sam=rs.getString(primary);
															txt=txt+" "+"'"+sam+"'";
														}
														j++;
													}
											
													lblupdate.setText(txt);
											k++;
											}
											
											else{
												primary=rser.getString("COLUMN_NAME");
												
												txt=txt+" AND "+rser.getString("COLUMN_NAME")+" =";
												
												ResultSet rs=dbsam.gettable(comboBox.getSelectedItem().toString());
												int j=1;
												
												while(rs.next()){
													if(j==i){
														String sam=rs.getString(primary);
														txt=txt+" "+"'"+sam+"'";
													}
													j++;
												}
										
												
											}
										}
											
											lblupdate.setText("");	
											String del=dbsam.operate_row(txt);
											if(del!=null){
												if(del.contains("You have an error in your SQL syntax")){
													
													JOptionPane.showMessageDialog(panel_1, "cannot update for this table");
													
												}
												else{
													JOptionPane.showMessageDialog(panel_1, del);	
												}
											}
											
										} catch (SQLException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}
										
										
										
									}
								});
						
						
						}
					
					});
					table.getModel().addTableModelListener(new TableModelListener() {
						
						@Override
						public void tableChanged(TableModelEvent e) {
							// TODO Auto-generated method stub
							String txt="UPDATE "+comboBox.getSelectedItem().toString()+" SET ";
							int i=table.getSelectedRow();
							i++;
							int row = e.getFirstRow();
					        int column = e.getColumn();
					        TableModel model = (TableModel)e.getSource();
					        String columnName = model.getColumnName(column);
					        Object data = model.getValueAt(row, column);
					        txt=txt+columnName+"='"+data.toString()+"' where ";
							Connection conn=dbsam.login();
							try {
								String primary=null;
								DatabaseMetaData dmd = conn.getMetaData();
								String tableName=comboBox.getSelectedItem().toString();
								ResultSet rser = dmd.getPrimaryKeys(null, null, tableName);
							//	lblupdate.setText("Cannot perform delete for this table ");
								int k=0;
								while(rser.next()){
								if(k==0){
									primary=rser.getString("COLUMN_NAME");
									
										txt=txt+rser.getString("COLUMN_NAME")+" =";
										
										ResultSet rs=dbsam.gettable(comboBox.getSelectedItem().toString());
										
										int j=1;
										
										while(rs.next()){
											if(j==i){
												String sam=rs.getString(primary);
												txt=txt+" "+"'"+sam+"'";
											}
											j++;
										}
								
										lblupdate.setText(txt);
								k++;
								}
								
								else{
									primary=rser.getString("COLUMN_NAME");
									
									txt=txt+" AND "+rser.getString("COLUMN_NAME")+" =";
									
									ResultSet rs=dbsam.gettable(comboBox.getSelectedItem().toString());
									int j=1;
									
									while(rs.next()){
										if(j==i){
											String sam=rs.getString(primary);
											txt=txt+" "+"'"+sam+"'";
										}
										j++;
									}
							
									
								}
							}
								
								lblupdate.setText("");	
								String del=dbsam.operate_row(txt);
								if(del!=null){
									if(del.contains("You have an error in your SQL syntax")){
										
										JOptionPane.showMessageDialog(panel_1, "Cannot Update for this table");
									}
									else{
									JOptionPane.showMessageDialog(panel_1, del);
								
									}
									
								}
								
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
							
							
						}
					});
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		
		
		table.getModel().addTableModelListener(this);
		}

	

	@Override
	public void tableChanged(TableModelEvent arg0) {
		// TODO Auto-generated method stub
		String txt="UPDATE "+comboBox.getSelectedItem().toString()+" SET ";
		int i=table.getSelectedRow();
		i++;
		int row = arg0.getFirstRow();
        int column = arg0.getColumn();
        TableModel model = (TableModel)arg0.getSource();
        String columnName = model.getColumnName(column);
        Object data = model.getValueAt(row, column);
        txt=txt+columnName+"='"+data.toString()+"' where ";
		Connection conn=dbsam.login();
		try {
			String primary=null;
			DatabaseMetaData dmd = conn.getMetaData();
			String tableName=comboBox.getSelectedItem().toString();
			ResultSet rser = dmd.getPrimaryKeys(null, null, tableName);
			//lblupdate.setText("Cannot perform delete for this table ");
			int k=0;
			while(rser.next()){
			if(k==0){
				primary=rser.getString("COLUMN_NAME");
				
					txt=txt+rser.getString("COLUMN_NAME")+" =";
					
					ResultSet rs=dbsam.gettable(comboBox.getSelectedItem().toString());
					int j=1;
					
					while(rs.next()){
						if(j==i){
							String sam=rs.getString(primary);
							txt=txt+" "+"'"+sam+"'";
						}
						j++;
					}
			
					lblupdate.setText("");
			k++;
			}
			
			else{
				primary=rser.getString("COLUMN_NAME");
				
				txt=txt+" AND "+rser.getString("COLUMN_NAME")+" =";
				
				ResultSet rs=dbsam.gettable(comboBox.getSelectedItem().toString());
				int j=1;
				
				while(rs.next()){
					if(j==i){
						String sam=rs.getString(primary);
						txt=txt+" "+"'"+sam+"'";
					}
					j++;
				}
		
				
			}
		}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		lblupdate.setText("");
		String del=dbsam.operate_row(txt);
		//lblupdate.setText("update "+up);
		if(del!=null){
			if(del.contains("You have an error in your SQL syntax")){
				
				JOptionPane.showMessageDialog(panel_1, "Cannot Update for this table");
			}
			
			else{
			JOptionPane.showMessageDialog(panel_1, del);
		
			}
		}
      	}
	
	
	public class menuaction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

			String menuitem=e.getActionCommand().toString();
			lblupdate.setText(menuitem);
			if(menuitem=="New"){
				form.setVisible(true);
			}
			
			if(menuitem=="Undo"){
				ResultSet commit=dbsam.undochanges(comboBox.getSelectedItem().toString());
				
			}
		
		
			table.getModel().addTableModelListener(new TableModelListener() {
				
				@Override
				public void tableChanged(TableModelEvent e) {
					// TODO Auto-generated method stub
					String txt="UPDATE "+comboBox.getSelectedItem().toString()+" SET ";
					int i=table.getSelectedRow();
					i++;
					int row = e.getFirstRow();
			        int column = e.getColumn();
			        TableModel model = (TableModel)e.getSource();
			        String columnName = model.getColumnName(column);
			        Object data = model.getValueAt(row, column);
			        txt=txt+columnName+"='"+data.toString()+"' where ";
					Connection conn=dbsam.login();
					try {
						String primary=null;
						DatabaseMetaData dmd = conn.getMetaData();
						String tableName=comboBox.getSelectedItem().toString();
						ResultSet rser = dmd.getPrimaryKeys(null, null, tableName);
						int k=0;
						while(rser.next()){
						if(k==0){
							primary=rser.getString("COLUMN_NAME");
							
								txt=txt+rser.getString("COLUMN_NAME")+" =";
								
								ResultSet rs=dbsam.gettable(comboBox.getSelectedItem().toString());
								
								int j=1;
								
								while(rs.next()){
									if(j==i){
										String sam=rs.getString(primary);
										txt=txt+" "+"'"+sam+"'";
									}
									j++;
								}
						
								lblupdate.setText("");
						k++;
						}
						
						else{
							primary=rser.getString("COLUMN_NAME");
							
							txt=txt+" AND "+rser.getString("COLUMN_NAME")+" =";
							
							ResultSet rs=dbsam.gettable(comboBox.getSelectedItem().toString());
							int j=1;
							
							while(rs.next()){
								if(j==i){
									String sam=rs.getString(primary);
									txt=txt+" "+"'"+sam+"'";
								}
								j++;
							}
					
							
						}
					}
						
						lblupdate.setText("");	
						String del=dbsam.operate_row(txt);
						if(del!=null){
							if(del.contains("You have an error in your SQL syntax")){
							
								JOptionPane.showMessageDialog(panel_1, "Cannot Update for this table");
							}
							else{
							JOptionPane.showMessageDialog(panel_1, del);
						
							}
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					
					
				
				}
			});
		}

	}
	}

