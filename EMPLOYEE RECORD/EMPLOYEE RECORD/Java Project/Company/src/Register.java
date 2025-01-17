import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Calendar;
import java.util.GregorianCalendar;


@SuppressWarnings("serial")
public class Register extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldUN;
	private JPasswordField passwordField;
	private JLabel label;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Register frame = new Register();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	Connection connection = null;
	public Register() {
		connection = sqliteConnection.dbConnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(150, 100, 718, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel Image = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/login.png")).getImage();
		
		label = new JLabel("");
		label.setForeground(Color.RED);
		label.setBounds(40, 323, 219, 30);
		contentPane.add(label);
		Image.setIcon(new ImageIcon(img));
		Image.setBounds(40, 41, 200, 200);
		contentPane.add(Image);
		
		JLabel label_UserName = new JLabel("UserName");
		label_UserName.setFont(new Font("Times New Roman", Font.BOLD, 14));
		label_UserName.setBounds(300, 71, 71, 30);
		contentPane.add(label_UserName);
		
		JLabel lblRegisterSystem = new JLabel("Register System");
		lblRegisterSystem.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegisterSystem.setForeground(Color.RED);
		lblRegisterSystem.setFont(new Font("Times New Roman", Font.BOLD, 22));
		lblRegisterSystem.setBounds(126, 0, 200, 50);
		contentPane.add(lblRegisterSystem);
		
		textFieldUN = new JTextField();
		textFieldUN.setColumns(10);
		textFieldUN.setBounds(400, 71, 200, 23);
		contentPane.add(textFieldUN);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(400, 112, 200, 23);
		contentPane.add(passwordField);
		
		JLabel label_Password = new JLabel("Password");
		label_Password.setFont(new Font("Times New Roman", Font.BOLD, 14));
		label_Password.setBounds(300, 112, 71, 30);
		contentPane.add(label_Password);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Login login = new Login();
				login.setVisible(true);
				
			}
		});
		btnLogin.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnLogin.setBounds(330, 170, 97, 30);
		contentPane.add(btnLogin);
		
		JButton BtnRegister = new JButton("Register");
		BtnRegister.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				try{
					String query="insert into User (Username, Password) values (?, ?) ";
					PreparedStatement pst = connection.prepareStatement(query);					
					pst.setString(1, textFieldUN.getText());
					pst.setString(2, passwordField.getText());
									
					pst.execute();					
					JOptionPane.showMessageDialog(null, "Successfull Register!");					
					pst.close();	
				}catch(Exception e){
					JOptionPane.showMessageDialog(null, e);
				}
				
				Login login = new Login();
				login.setVisible(true);
			}
		});
		BtnRegister.setFont(new Font("Times New Roman", Font.BOLD, 16));
		BtnRegister.setBounds(470, 170, 97, 30);
		contentPane.add(BtnRegister);
		
		JLabel label_Design = new JLabel("Designed for: Webskitters Academy");
		label_Design.setHorizontalAlignment(SwingConstants.CENTER);
		label_Design.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		label_Design.setBounds(250, 323, 400, 23);
		contentPane.add(label_Design);
		
		JLabel Image_Background = new JLabel("");
		Image img1 = new ImageIcon(this.getClass().getResource("/background.jpg")).getImage();
		Image_Background.setIcon(new ImageIcon(img1));
		Image_Background.setBounds(0, 0, 780, 500);
		contentPane.add(Image_Background);
		
		Clock();
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
					
					label.setText("Time " + hour +" : "+ minute + " : " + second +" Date " + year + " / " + month + " / " + day );
					sleep(1000);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		clock.start();
	}

}