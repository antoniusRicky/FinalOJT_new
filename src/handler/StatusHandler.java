package handler;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyVetoException;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import main.Main;
import model.Book;
import model.Status;

public class StatusHandler implements MouseListener {
	
	private Vector<Object> row;
	private Vector<Object> column;
	private JTable tbl;
	private DefaultTableModel dtm;
	private JButton btnReturn;
	private List<Status> StatusTemp;
	private JInternalFrame bookList;
	
	public JInternalFrame showViewBookForm(List<Status> listStatus) {
		
		  bookList = new JInternalFrame();
		  bookList.setVisible(true);
		  bookList.setSize(800, 400);
		  bookList.setLocation(100, 70);
		  
		  JLabel title = new JLabel("OJT Library",JLabel.CENTER);
		  title.setFont(new Font("Times New Roman",Font.BOLD,30));
		  
		  JLabel subTitle = new JLabel("Return List",JLabel.CENTER);
		  subTitle.setFont(new Font("Times New Roman",Font.BOLD,20));
		  
		  JScrollPane pane;
		  
		  column = new Vector<>();
		  column.add("ID");
		  column.add("Borrower");
		  column.add("Book Code");
		  column.add("Date");
		  
		  
		  dtm = new DefaultTableModel(column,0);
		  tbl = new JTable(dtm);
		  tbl.getTableHeader().setReorderingAllowed(Boolean.FALSE);
		  tbl.setAutoCreateRowSorter(true);
		  StatusTemp = listStatus;
		  refreshTable(listStatus);
		  pane = new JScrollPane(tbl);
		  
		  btnReturn = new JButton("Return Book");	  
		  
		  JPanel titlePanel = new JPanel(new GridLayout(4,3,5,5));
		  titlePanel.add(new JPanel());
		  titlePanel.add(new JPanel());
		  titlePanel.add(new JPanel());
		  
		  titlePanel.add(new JPanel());
		  titlePanel.add(title);
		  titlePanel.add(new JPanel());
		  
		  titlePanel.add(new JPanel());
		  titlePanel.add(subTitle);
		  titlePanel.add(new JPanel());
		  
		  JPanel pnlBtnReturn = new JPanel(new GridLayout(1, 6, 5, 5));
		  pnlBtnReturn.add(new JPanel());
		  pnlBtnReturn.add(btnReturn);
		  pnlBtnReturn.add(new JPanel());
		  
		  JPanel mainPanel = new JPanel(new BorderLayout(5, 5));
		  mainPanel.add(titlePanel,BorderLayout.NORTH);
		  mainPanel.add(pane,BorderLayout.CENTER);
		  mainPanel.add(pnlBtnReturn,BorderLayout.SOUTH);
		  mainPanel.setBorder(new EmptyBorder(10, 15, 15, 15));
		  
		  
		  bookList.add(mainPanel);
		  bookList.setClosable(true);
		  btnReturn.addMouseListener(this);
		  return bookList;
		 }
	
	public void refreshTable(List<Status> listStatus) {
		
		dtm = new DefaultTableModel(column,0);
		for (int i=0;i<listStatus.size();i++) {
			// buat row baru
			row = new Vector<Object>();
			row.add(listStatus.get(i).getStatusCode());
			row.add(listStatus.get(i).getBorrower());
			row.add(listStatus.get(i).getBookCode());
			row.add(listStatus.get(i).getDate());
		
			
			// ini masukkin row ke model
			dtm.addRow(row);
			tbl.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		}
		
		// mengubah tblnya jadi model yang baru yaitu ketambahan satu row
		tbl.setModel(dtm);		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == btnReturn) {
			int index = tbl.getSelectedRow();
			
			if (index == -1) new JOptionPane().showMessageDialog(null, "Choose To Return!");
			else {
				Status s = StatusTemp.get(index);
				String bookCode = Main.status.get(s.getStatusCode()).getBookCode();
				// update book stock
				int tempStock = Main.books.get(bookCode).getStock();
				Main.books.get(bookCode).setStock(tempStock+1);				
				
				Main.status.remove(s.getStatusCode());
				new JOptionPane().showMessageDialog(null, "Book Returned!");
				refreshTable(StatusTemp);
				try {
					bookList.setClosed(true);
				} catch (PropertyVetoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
