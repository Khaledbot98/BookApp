package firstApp;

import java.awt.EventQueue;
import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class FirstApp {

	private JFrame frame;
	private JTable table;
	private JTextField txtbname;
	private JTextField txtedition;
	private JTextField txtprice;
	private JTextField txtid;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FirstApp window = new FirstApp();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FirstApp() {
		initialize();
		Connect();
		table_load();
	}

	
	
	
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	
	
	public void Connect(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/bookshop", "root", "");
		}
		catch(ClassNotFoundException ex)
		{
			
		}
		catch (SQLException ex) {
			
		}
	}
	
	
	
	public void table_load() {
		try {
			pst = con.prepareStatement("select * from book");
			rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	

	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 998, 604);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Book Shop");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel.setBounds(421, 26, 141, 59);
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Regestration '", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 75, 428, 230);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Book Name:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1.setBounds(21, 53, 137, 25);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Edition:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1_1.setBounds(21, 123, 137, 25);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Price:");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1_2.setBounds(21, 186, 137, 25);
		panel.add(lblNewLabel_1_2);
		
		txtbname = new JTextField();
		txtbname.setBounds(191, 51, 205, 34);
		panel.add(txtbname);
		txtbname.setColumns(10);
		
		txtedition = new JTextField();
		txtedition.setColumns(10);
		txtedition.setBounds(191, 114, 205, 34);
		panel.add(txtedition);
		
		txtprice = new JTextField();
		txtprice.setColumns(10);
		txtprice.setBounds(191, 177, 205, 34);
		panel.add(txtprice);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(448, 75, 500, 327);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				String bname , edition , price;
				
				bname = txtbname.getText();
				edition = txtedition.getText();
				price = txtprice.getText();
				
				try {
					pst =con.prepareStatement("insert into book (name , edition , price) values (?,?,?)");
					pst.setString(1, bname);
					pst.setString(2, edition);
					pst.setString(3, price);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record Added");
					table_load();
					txtbname.setText("");
					txtedition.setText("");
					txtprice.setText("");
					txtbname.requestFocus();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
				
				
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnNewButton.setBounds(30, 334, 106, 48);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.exit(0);
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnExit.setBounds(160, 334, 106, 48);
		frame.getContentPane().add(btnExit);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtbname.setText("");
				txtedition.setText("");
				txtprice.setText("");
				txtbname.requestFocus();
				
			}
		});
		btnClear.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnClear.setBounds(306, 334, 106, 48);
		frame.getContentPane().add(btnClear);
		
		JButton btnUpdate_1 = new JButton("Update");
		btnUpdate_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				
String bname , edition , price ,bid;
				
				bname = txtbname.getText();
				edition = txtedition.getText();
				price = txtprice.getText();
				bid = txtid.getText();
				
				try {
					pst =con.prepareStatement("update book set name =? , edition=? , price=? where id =?");
					pst.setString(1, bname);
					pst.setString(2, edition);
					pst.setString(3, price);
					pst.setString(4 ,bid);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record Updated");
					table_load();
					txtbname.setText("");
					txtedition.setText("");
					txtprice.setText("");
					txtbname.requestFocus();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
				
				
				
			}
		});
		btnUpdate_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnUpdate_1.setBounds(456, 423, 106, 48);
		frame.getContentPane().add(btnUpdate_1);
		
		JButton btnUpdate = new JButton("Delete");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
String bid;
				
				
				bid = txtid.getText();
				
				try {
					pst =con.prepareStatement("delete from book where id =?");
		
					pst.setString(1 ,bid);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record Deleted");
					table_load();
					txtbname.setText("");
					txtedition.setText("");
					txtprice.setText("");
					txtbname.requestFocus();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
				
				
				
				
				
				
				
			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnUpdate.setBounds(646, 423, 106, 48);
		frame.getContentPane().add(btnUpdate);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 423, 396, 89);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1_3 = new JLabel("ID:");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1_3.setBounds(10, 32, 71, 25);
		panel_1.add(lblNewLabel_1_3);
		
		txtid = new JTextField();
		txtid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					String id = txtid.getText();
					pst = con.prepareStatement("select name , edition ,price from book where id =?");
					pst.setString(1, id);
					ResultSet rs =pst.executeQuery();
					
					if(rs.next()==true) {
						String bname = rs.getString(1);
						String edition = rs.getString(2);
						String price = rs.getString(3);
						
						txtbname.setText(bname);
						txtedition.setText(edition);
						txtprice.setText(price);
						
					}else {
						txtbname.setText("");
						txtedition.setText("");
						txtprice.setText("");
					}
					
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		txtid.setColumns(10);
		txtid.setBounds(91, 23, 205, 34);
		panel_1.add(txtid);
	}
}
