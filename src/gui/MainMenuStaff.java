package gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import main.Main;
import model.User;

public class MainMenuStaff extends JFrame implements ActionListener {

	private JLabel title;
	private JPanel panel;
	
	private JLabel id;
	private JLabel pass;
		
	private JTextField idfield;
	private JPasswordField passfield;
	
	private JButton login;
	
	private JOptionPane errorLogin;
	
	public MainMenuStaff() {
		init();
		login.addActionListener(this);
	}
	
	public void init() {
		setTitle("OJT Library");
		setSize(1000, 640);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(true);
		setLocationRelativeTo(null);
		
		panel = fullPanel(gridId(), gridPassword(), gridStartRegister());
		add(panel);
		
		setVisible(true);
	}
	
	public JPanel gridId() {
		panel = new JPanel(new GridLayout(1,4,5,5));
		id         = new JLabel("Username:");
		id.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		idfield = new JTextField();
		idfield.setFont(new Font("Comic Sans Ms", Font.PLAIN, 18));
		
		panel.add(new JPanel());
		panel.add(id);
		panel.add(idfield);
		panel.add(new JPanel());
		
		return panel;
	}
	
	public JPanel gridPassword() {
		panel = new JPanel(new GridLayout(1,4,5,5));
		pass        = new JLabel("Password:");
		pass.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		passfield = new JPasswordField();
		passfield.setFont(new Font("Comic Sans Ms", Font.PLAIN, 18));
		
		panel.add(new JPanel());
		panel.add(pass);
		panel.add(passfield);
		panel.add(new JPanel());
		
		return panel;
	}
	
	public JPanel gridStartRegister() {
		panel = new JPanel(new GridLayout(1,6,5,5));
		login      = new JButton("Login");
		login.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		
		panel.add(new JPanel());
		panel.add(login);
		panel.add(new JPanel());
		
		return panel;
	}
	
	public JPanel fullPanel(JPanel i, JPanel p, JPanel s) {
		
		JPanel panel = new JPanel(new GridLayout(9,1,5,5));
		
		title = new JLabel("OJT Library", JLabel.CENTER);
		title.setFont(new Font("Times New Roman", Font.BOLD, 30));
		title.setPreferredSize(new Dimension(400,50));
		
		panel.add(new JPanel());
		panel.add(title);
		panel.add(new  JPanel());
		panel.add(i);
		panel.add(new  JPanel());
		panel.add(p);
		panel.add(new  JPanel());
		panel.add(s);
		panel.add(new  JPanel());
		
		return panel;
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == login) {
			// check id with input
			for (Map.Entry<String, User> u : Main.getUsers().entrySet()) {
				if (idfield.getText().equals(u.getKey())) {
					Main.currRole = u.getValue().getRole();
					Main.currUser = idfield.getText();
					Main.currPwd = u.getValue().getPassword();
					break;
				}
			}
			
			String pwdtemp = new String(passfield.getPassword());
			if (pwdtemp.isEmpty() || idfield.getText().isEmpty()) {
				errorLogin.showMessageDialog(null, "Username and Password Can't be Empty","Alert",JOptionPane.WARNING_MESSAGE);  
			}
			else if(pwdtemp.equals(Main.currPwd)) {
				dispose();
				
			}
			else {
				errorLogin = new JOptionPane();
				errorLogin.showMessageDialog(null, "Username or Password Incorrect","Alert",JOptionPane.WARNING_MESSAGE);  
			}
		}
		else {
			
		}
		
	}
	
}
