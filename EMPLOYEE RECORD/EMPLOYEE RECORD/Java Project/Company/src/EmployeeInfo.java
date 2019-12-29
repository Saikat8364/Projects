import java.awt.EventQueue;
import java.awt.Image;
import java.sql.*;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import net.proteanit.sql.DbUtils;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JScrollPane;
import java.awt.*;
import java.awt.event.*;


@SuppressWarnings("serial")
public class EmployeeInfo extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JComboBox<String> comboBoxName;
	private JList<String> listName;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBoxSelect;
	private JLabel lblClock;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeeInfo frame = new EmployeeInfo();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void Clock(){
		
		Thread clock = new Thread()
		{
			public void run(){
				try {
					while(true){
					Calendar cal = new GregorianCalendar();
					int day = cal.get(Calendar.DAY_OF_MONTH);
					int month = cal.get(Calendar.MONTH);
					int year = cal.get(Calendar.YEAR);
					
					int second = cal.get(Calendar.SECOND);
					int minute = cal.get(Calendar.MINUTE);
					int hour = cal.get(Calendar.HOUR);
					
					lblClock.setText("Time " + hour +" : "+ minute + " : " + second +"                 Date " + day + " / " + month + " / " + year );
					sleep(1000);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		clock.start();
	}

	/**
	 * Create the frame.
	 */
	Connection connection = null;
	private JLabel lblNewLabel;
	private final JLabel lblDesignedByMr = new JLabel("Designed for: Webskitters Academy");
	private JLabel lblID;
	private JLabel lblName;
	private JLabel lblSurname;
	private JLabel lblAge;
	private JLabel lblDoB;
	private JLabel lblSalary;
	private JTextField textFieldEID;
	private JTextField textFieldName;
	private JTextField textFieldSurname;
	private JTextField textFieldAge;
	private JButton btnSave;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JLabel lblNewLabel_3;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;
	private JScrollPane jsp;
	private JTextField textFieldSearch;
	private JButton btnSearch;
	private JTextField textFieldDoB;
	private JTextField textFieldSalary;
	private JFrame frame;
	
	
	
	
	
	public void refreshTable(){
		
		try {
			String query = "select EID, Name, Surname, Age, DoB, Salary from Employeeinfo";
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			pst.close();
			rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public void fillComboBox(){
		try {
			String query ="select * from Employeeinfo";
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			while(rs.next()){
				comboBoxName.addItem(rs.getString("Name"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public EmployeeInfo() {
		
		connection = sqliteConnection.dbConnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setSize(Toolkit.getDefaultToolkit().getScreenSize());
		setVisible(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnLoadTable = new JButton("Load Data");
		btnLoadTable.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnLoadTable.setForeground(new Color(30, 144, 255));
		btnLoadTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String query = "select EID, Name, Surname, Age, DoB, Salary from Employeeinfo";
					PreparedStatement pst = connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					pst.close();
					rs.close();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		textFieldSearch = new JTextField();
		textFieldSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				//Search();
			}
		});
		
		btnSearch = new JButton("Search");
		btnSearch.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Search();
			}
		});
		
		lblClock = new JLabel("");
		lblClock.setBounds(150, 910, 600, 44);
		lblClock.setFont(new Font("Times New Roman", Font.ITALIC, 30));
		lblClock.setForeground(Color.WHITE);
		contentPane.add(lblClock);
		
		comboBoxSelect = new JComboBox();
		comboBoxSelect.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		comboBoxSelect.setModel(new DefaultComboBoxModel(new String[] {"EID", "Name", "Surname", "Age","DoB","Salary"}));
		comboBoxSelect.setBounds(670, 130, 200, 30);
		contentPane.add(comboBoxSelect);
		btnSearch.setBounds(1300, 130, 84, 30);
		btnSearch.setForeground(new Color(30,144,255));
		contentPane.add(btnSearch);
		textFieldSearch.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		textFieldSearch.setBackground(Color.WHITE);
		textFieldSearch.setForeground(Color.BLACK);
		textFieldSearch.setBounds(890, 130, 390, 30);
		contentPane.add(textFieldSearch);
		textFieldSearch.setColumns(10);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(550, 690, 832, 200);
		contentPane.add(scrollPane_1);
		
		listName = new JList<String>();
		listName.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				refreshTable();
			}
		});
		listName.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		scrollPane_1.setViewportView(listName);
		btnLoadTable.setBounds(550, 130, 104, 30);
		contentPane.add(btnLoadTable);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(550, 200, 835, 400);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				try {
					int row = table.getSelectedRow();
					String EID = (table.getModel().getValueAt(row, 0).toString());				
					String query = "select * from Employeeinfo where EID = '" + EID + "' ";
					PreparedStatement pst = connection.prepareStatement(query);					
					ResultSet rs = pst.executeQuery();					
					while(rs.next()){
						textFieldEID.setText(rs.getString("EID"));
						textFieldName.setText(rs.getString("Name"));
						textFieldSurname.setText(rs.getString("Surname"));
						textFieldAge.setText(rs.getString("Age"));
						textFieldDoB.setText(rs.getString("DoB"));
						textFieldSalary.setText(rs.getString("Salary"));
					}
					pst.close();					
					
				} catch (Exception e) {
					e.printStackTrace();
				}				
			}
		});
		
		lblNewLabel = new JLabel("The Employee Information System");
		lblNewLabel.setForeground(new Color(255, 0, 0));
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 48));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(430, 15, 1080, 90);
		contentPane.add(lblNewLabel);
		lblDesignedByMr.setFont(new Font("Times New Roman", Font.ITALIC, 30));
		lblDesignedByMr.setHorizontalAlignment(SwingConstants.CENTER);
		lblDesignedByMr.setBounds(625, 910, 1080, 40);
		lblDesignedByMr.setForeground(Color.WHITE);
		contentPane.add(lblDesignedByMr);
		
		lblID = new JLabel("EID");
		lblID.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblID.setForeground(new Color(255,255,75));
		lblID.setBounds(150, 220, 65, 31);
		contentPane.add(lblID);
		
		lblName = new JLabel("Name");
		lblName.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblName.setForeground(new Color(255,255,75));
		lblName.setBounds(150, 290, 65, 31);
		contentPane.add(lblName);
		
		lblSurname = new JLabel("Surname");
		lblSurname.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblSurname.setForeground(new Color(255,255,75));
		lblSurname.setBounds(150, 360, 100, 31);
		contentPane.add(lblSurname);
		
		lblAge = new JLabel("Age");
		lblAge.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblAge.setForeground(new Color(255,255,75));
		lblAge.setBounds(150, 430, 65, 31);
		contentPane.add(lblAge);
		
		lblDoB = new JLabel("DoB");
		lblDoB.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblDoB.setForeground(new Color(255,255,75));
		lblDoB.setBounds(150, 500, 65, 31);
		contentPane.add(lblDoB);
		
		lblSalary = new JLabel("Salary");
		lblSalary.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblSalary.setForeground(new Color(255,255,75));
		lblSalary.setBounds(150, 570, 100, 31);
		contentPane.add(lblSalary);
		
		textFieldEID = new JTextField();
		textFieldEID.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		textFieldEID.setBounds(270, 220, 250, 30);
		contentPane.add(textFieldEID);
		textFieldEID.setColumns(10);
		
		textFieldName = new JTextField();
		textFieldName.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		textFieldName.setBounds(270, 290, 250, 30);
		contentPane.add(textFieldName);
		textFieldName.setColumns(10);
		
		textFieldSurname = new JTextField();
		textFieldSurname.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		textFieldSurname.setBounds(270, 360, 250, 30);
		contentPane.add(textFieldSurname);
		textFieldSurname.setColumns(10);
		
		textFieldAge = new JTextField();
		textFieldAge.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		textFieldAge.setBounds(270, 430, 250, 30);
		contentPane.add(textFieldAge);
		textFieldAge.setColumns(10);
		
		textFieldDoB = new JTextField();
		textFieldDoB.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		textFieldDoB.setBounds(270, 500, 250, 30);
		contentPane.add(textFieldDoB);
		textFieldDoB.setColumns(10);
		
		textFieldSalary = new JTextField();
		textFieldSalary.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		textFieldSalary.setBounds(270, 570, 250, 30);
		contentPane.add(textFieldSalary);
		textFieldSalary.setColumns(10);
		
		btnSave = new JButton("Save");
		btnSave.setBackground(Color.YELLOW);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					String query = "insert into Employeeinfo (EID, Name, SurName, Age, DoB, Salary) values (?, ?, ?, ?, ?, ?) ";
					PreparedStatement pst = connection.prepareStatement(query);					
					pst.setString(1, textFieldEID.getText());
					pst.setString(2, textFieldName.getText());
					pst.setString(3, textFieldSurname.getText());
					pst.setString(4, textFieldAge.getText());
					pst.setString(5, textFieldDoB.getText());
					pst.setString(6, textFieldSalary.getText());
					pst.execute();					
					JOptionPane.showMessageDialog(null, "Data Saved");					
					pst.close();					
				} catch (Exception e) {
					e.printStackTrace();
				}
				refreshTable();
				Reset();
			}
		});
		btnSave.setFont(new Font("Times New Roman", Font.BOLD, 30));
		btnSave.setBounds(550, 620, 185, 45);
		contentPane.add(btnSave);
		
		btnUpdate = new JButton("Update");
		btnUpdate.setForeground(Color.BLUE);
		btnUpdate.setBackground(Color.GREEN);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					String query = "update Employeeinfo set EID = '" + textFieldEID.getText() + "', Name = '" + textFieldName.getText() + "', Surname = '" + textFieldSurname.getText() +"', Age = '" + textFieldAge.getText() +"', DoB = '" + textFieldDoB.getText() +"', Salary = '" + textFieldSalary.getText() + "' where EID = '" + textFieldEID.getText() + "'";
					PreparedStatement pst = connection.prepareStatement(query);									
					pst.execute();				
					JOptionPane.showMessageDialog(null, "Data Updated");	
					pst.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				refreshTable();
				Reset();
			}
		});
		btnUpdate.setFont(new Font("Times New Roman", Font.BOLD, 30));
		btnUpdate.setBounds(767, 620, 185, 45);
		contentPane.add(btnUpdate);
		
		btnDelete = new JButton("Delete");
		btnDelete.setBackground(Color.RED);
		btnDelete.setForeground(Color.DARK_GRAY);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int action = JOptionPane.showConfirmDialog(null, "Do you want to delete!", "Delete", JOptionPane.YES_NO_OPTION);
				if(action == 0){
				try {
					String query = "delete from Employeeinfo where EID = '" + textFieldEID.getText() + "' ";
					PreparedStatement pst = connection.prepareStatement(query);
					pst.execute();
					JOptionPane.showMessageDialog(null, "Data Deleted");
					pst.close();	
				} catch (Exception e) {
					e.printStackTrace();
				}
				refreshTable();
				Reset();
				}
			}
		});
		btnDelete.setFont(new Font("Times New Roman", Font.BOLD, 30));
		btnDelete.setBounds(983, 620, 185, 45);
		contentPane.add(btnDelete);
		
		comboBoxName = new JComboBox<String>();
		comboBoxName.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		comboBoxName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					String query = "select * from Employeeinfo where name = ? ";
					PreparedStatement pst = connection.prepareStatement(query);
					
					pst.setString(1, (String)comboBoxName.getSelectedItem());
					ResultSet rs = pst.executeQuery();
					
					while(rs.next()){
						textFieldEID.setText(rs.getString("EID"));
						textFieldName.setText(rs.getString("Name"));
						textFieldSurname.setText(rs.getString("Surname"));
						textFieldAge.setText(rs.getString("Age"));
						textFieldDoB.setText(rs.getString("DoB"));
						textFieldSalary.setText(rs.getString("Salary"));
					}
					pst.close();	
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		comboBoxName.setBounds(150, 130, 370, 30);
		contentPane.add(comboBoxName);
		
		JButton btnReset = new JButton("New");
		btnReset.setForeground(Color.WHITE);
		btnReset.setBackground(Color.BLUE);
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Reset();
			}
		});
		btnReset.setFont(new Font("Times New Roman", Font.BOLD, 30));
		btnReset.setBounds(1200, 620, 185, 45);
		contentPane.add(btnReset);
		
		lblNewLabel_3 = new JLabel("");
		Image img2 = new ImageIcon(this.getClass().getResource("/admin.png")).getImage();
		lblNewLabel_3.setIcon(new ImageIcon(img2));
		lblNewLabel_3.setBounds(33, 10, 122, 130);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_1 = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/background.jpg")).getImage();
		lblNewLabel_1.setIcon(new ImageIcon(img));
		lblNewLabel_1.setSize(1900, 1080);
		contentPane.add(lblNewLabel_1);
		
		
			
		refreshTable();
		fillComboBox();
		LoadList();
		Clock();
	}
	public void Reset(){
		textFieldEID.setText("");
		textFieldName.setText("");
		textFieldSurname.setText("");
		textFieldAge.setText("");
		textFieldDoB.setText("");
		textFieldSalary.setText("");
	}
	public void LoadList(){
		try {
			String query = "select * from Employeeinfo ";
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			
			DefaultListModel<String> DLM = new DefaultListModel<String>();
			
			while(rs.next()){
				DLM.addElement(rs.getString("Name"));
			}
			listName.setModel(DLM);
			pst.close();	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void Search(){
		try {
			String selection = (String)comboBoxSelect.getSelectedItem();
            String query = "select EID, Name, Surname, Age, DoB, Salary from Employeeinfo where " + selection +" = ? ";
			PreparedStatement pst = connection.prepareStatement(query);
			
			pst.setString(1, textFieldSearch.getText());
			ResultSet rs = pst.executeQuery();
			
			table.setModel(DbUtils.resultSetToTableModel(rs));
			pst.close();	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
