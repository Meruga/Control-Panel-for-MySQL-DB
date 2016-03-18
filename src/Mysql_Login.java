import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;


public class Mysql_Login {

	private JFrame frame;
	private JTextField user;
	private JPasswordField pass;
	private JButton btnLoginIntoDb;
	private JLabel lblNewLabel_1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Mysql_Login window = new Mysql_Login();
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
	public Mysql_Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 768, 448);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		user = new JTextField();
		user.setBounds(205, 102, 187, 35);
		frame.getContentPane().add(user);
		user.setColumns(10);
		
		pass = new JPasswordField();
		pass.setBounds(205, 169, 187, 33);
		frame.getContentPane().add(pass);
		
		JLabel lblEnterUsername = new JLabel("Enter UserName");
		lblEnterUsername.setBounds(30, 109, 131, 20);
		frame.getContentPane().add(lblEnterUsername);
		
		JLabel lblNewLabel = new JLabel("Enter Password");
		lblNewLabel.setBounds(30, 182, 131, 20);
		frame.getContentPane().add(lblNewLabel);

		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(205, 234, 187, 20);
		frame.getContentPane().add(lblNewLabel_1);
		 btnLoginIntoDb = new JButton("Login into DB");
		 
		 
		btnLoginIntoDb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				lblNewLabel_1.setText("Invalid Credentials");
				String pas=pass.getText();
				ShowTable st=new ShowTable(user.getText(),pas,frame);
				user.setText("");
				 pass.setText("");
				frame.dispose();
				st.setVisible(true);
				
					}
		});
		btnLoginIntoDb.setBounds(159, 272, 142, 29);
		frame.getContentPane().add(btnLoginIntoDb);
		
	}
}
