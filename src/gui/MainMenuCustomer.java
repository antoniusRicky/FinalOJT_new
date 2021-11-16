package gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import handler.BookHandler;
import handler.BorrowHandler;
import handler.StatusHandler;
import main.Main;
import model.Book;
import model.Status;
import model.User;

public class MainMenuCustomer extends JFrame implements ActionListener {

	private JLabel title;
	private JPanel panel;
	
	private JButton showall;
	private JButton borrow;
	private JButton returns;
	private JButton logout;
	private JButton exit;
	
	private JDesktopPane pane;
	private BookHandler hBook;
	private StatusHandler hStat;
	private BorrowHandler hBorr;
	
	private JOptionPane errorLogin;
	
	public MainMenuCustomer() {
		init();
		showall.addActionListener(this);
		borrow.addActionListener(this);
		returns.addActionListener(this);
		logout.addActionListener(this);
		exit.addActionListener(this);
	}
	
	public void init() {
		setTitle("OJT Library");
		setSize(1000, 640);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(true);
		setLocationRelativeTo(null);
		
		panel = fullPanel(firstGrid(), secondGrid(), lgexGrid());
		add(panel);
		
		setVisible(true);
	}
	
	public JPanel firstGrid() {
		panel = new JPanel(new GridLayout(1,3,4,4));
		showall = new JButton("Show All Book");
		
		panel.add(new JPanel());
		panel.add(showall);
		panel.add(new JPanel());
		
		return panel;
	}
	
	public JPanel secondGrid() {
		panel = new JPanel(new GridLayout(1,4,4,4));
		borrow = new JButton("Borrow Book");
		returns = new JButton("Return Book");
		
		panel.add(new JPanel());
		panel.add(borrow);
		panel.add(returns);		
		panel.add(new JPanel());
		
		return panel;
	}
	
	public JPanel lgexGrid() {
		panel = new JPanel(new GridLayout(1,6,4,4));
		logout = new JButton("Logout");
		exit = new JButton("Exit");
		
		panel.add(logout);
		panel.add(new JPanel());
		panel.add(new JPanel());
		panel.add(new JPanel());
		panel.add(new JPanel());
		panel.add(exit);
		
		return panel;
	}
	
	public JPanel fullPanel(JPanel i, JPanel p, JPanel s) {
		
		JPanel panel = new JPanel(new GridLayout(8,1,4,4));
		
		title = new JLabel("Welcome To OJT Library", JLabel.CENTER);
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
//		panel.add(new  JPanel());
		
		return panel;
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == showall) {
			List<Book> listbook = new ArrayList<Book>();
			// get all list book
			for (Map.Entry<String, Book> bb : Main.getBooks().entrySet()) {
				listbook.add(bb.getValue());
			}
			
			pane = new JDesktopPane();
			hBook = new BookHandler();
			JInternalFrame frame = hBook.showViewBookForm(listbook);
			pane.add(frame);
		    setContentPane(pane);
		    add(panel);
		}
		else if (e.getSource() == borrow){
			List<Book> listbook = new ArrayList<Book>();
			// get all list book
			for (Map.Entry<String, Book> bb : Main.getBooks().entrySet()) {
				listbook.add(bb.getValue());
			}
			
			pane = new JDesktopPane();
			hBorr = new BorrowHandler();
			JInternalFrame frame = hBorr.showViewBookForm(listbook);
			pane.add(frame);
		    setContentPane(pane);
		    add(panel);
		}
		else if (e.getSource() == returns){
			List<Status> returnlistbook = new ArrayList<Status>();
			for (Map.Entry<String, Status> ss : Main.getStatus().entrySet()) {
				String temp = ss.getValue().getBorrower();
				if(temp.equals(Main.currUser)) {
					returnlistbook.add(ss.getValue());					
				}
			}
			
			if (returnlistbook.isEmpty()) {
				errorLogin = new JOptionPane();
				errorLogin.showMessageDialog(null, "No Book to Return","Alert",JOptionPane.WARNING_MESSAGE); 
			}
			else {
				pane = new JDesktopPane();
				hStat = new StatusHandler();
				JInternalFrame frame = hStat.showViewBookForm(returnlistbook);
				pane.add(frame);
				setContentPane(pane);
				add(panel);				
			}
		}
		else if (e.getSource() == logout){
			Main.updateText();
			dispose();
			Main.main(null);
		}
		else {
			Main.updateText();
			dispose();
		}
		
	}
	
}
