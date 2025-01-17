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
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.GregorianCalendar;


@SuppressWarnings("serial")
public class Login extends JFrame {

	private JPanel contentPane;
	private JPasswordField passwordField;
	private JTextField textFieldUN;
	private JLabel lblClock;

	/**
	 * Launch the application.
	 */
	Connection connection = null;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		connection = sqliteConnection.dbConnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 718, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel Image = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/login.png")).getImage();
		
		lblClock = new JLabel("");
		lblClock.setForeground(Color.RED);
		lblClock.setBounds(40, 323, 219, 30);
		contentPane.add(lblClock);
		Image.setIcon(new ImageIcon(img));
		Image.setBounds(40, 41, 200, 200);
		contentPane.add(Image);
		
		JLabel lblLoginSystem = new JLabel("Login System");
		lblLoginSystem.setHorizontalAlignment(SwingConstants.CENTER);
		lblLoginSystem.setForeground(Color.RED);
		lblLoginSystem.setFont(new Font("Times New Roman", Font.BOLD, 22));
		lblLoginSystem.setBounds(250, 0, 200, 50);
		contentPane.add(lblLoginSystem);
		
		JLabel label_UserName = new JLabel("UserName");
		label_UserName.setFont(new Font("Times New Roman", Font.BOLD, 14));
		label_UserName.setBounds(300, 71, 71, 30);
		contentPane.add(label_UserName);
		
		JLabel label_Password = new JLabel("Password");
		label_Password.setFont(new Font("Times New Roman", Font.BOLD, 14));
		label_Password.setBounds(300, 112, 71, 30);
		contentPane.add(label_Password);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {

				try{
					String query="select * from User where UserName = ? and Password = ?";
					PreparedStatement pst = connection.prepareStatement(query);
					pst.setString(1, textFieldUN.getText());
					pst.setString(2, passwordField.getText());
					
					ResultSet rs = pst.executeQuery();
					int count = 0;
					while(rs.next()){
						count = count + 1;
					}
					if (count == 1){
						JOptionPane.showMessageDialog(null, "UserName and Password is correct");
						EmployeeInfo Emplinfo = new EmployeeInfo();
						Emplinfo.setVisible(true);
					}
					else if(count > 1){
						JOptionPane.showMessageDialog(null, "Duplicate UserName and Password");
					}
					else{
						JOptionPane.showMessageDialog(null, "UserName and Password is not correct Try Again...");
					}
					
					rs.close();
					pst.close();
				}catch(Exception e){
					JOptionPane.showMessageDialog(null, e);
				}
			}
		});
		btnLogin.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnLogin.setBounds(330, 170, 97, 30);
		contentPane.add(btnLogin);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Register register = new Register();
				register.setVisible(true);
			}
		});
		btnRegister.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnRegister.setBounds(470, 170, 97, 30);
		contentPane.add(btnRegister);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(400, 112, 200, 23);
		contentPane.add(passwordField);
		
		textFieldUN = new JTextField();
		textFieldUN.setColumns(10);
		textFieldUN.setBounds(400, 71, 200, 23);
		contentPane.add(textFieldUN);
		
		JLabel label_Design = new JLabel("Designed for: Webskitters Academy");
		label_Design.setHorizontalAlignment(SwingConstants.CENTER);
		label_Design.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		label_Design.setBounds(250, 323, 400, 23);
		contentPane.add(label_Design);
		
		JLabel Background_Image = new JLabel("");
		Image img2 = new ImageIcon(this.getClass().getResource("/background.jpg")).getImage();
		Background_Image.setIcon(new ImageIcon(img2));
		Background_Image.setBounds(0, 0, 780, 500);
		contentPane.add(Background_Image);
		
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
					
					lblClock.setText("Time " + hour +" : "+ minute + " : " + second +" Date " + year + " / " + month + " / " + day );
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
