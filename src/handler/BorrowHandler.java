package handler;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyVetoException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

public class BorrowHandler implements MouseListener {
	
	private Vector<Object> row;
	 private Vector<Object> bookColumn;
	 private JTable tbl;
	 private DefaultTableModel dtm;
	 private JButton btnBorrow;
	 private List<Book> bookTemp;
	 private JInternalFrame bookList;
	
	public JInternalFrame showViewBookForm(List<Book> listBook) {
		
		  bookList = new JInternalFrame();
		  bookList.setVisible(true);
		  bookList.setSize(800, 400);
		  bookList.setLocation(100, 70);
		  
		  JLabel title = new JLabel("OJT Library",JLabel.CENTER);
		  title.setFont(new Font("Times New Roman",Font.BOLD,30));
		  
		  JLabel subTitle = new JLabel("Borrow Book",JLabel.CENTER);
		  subTitle.setFont(new Font("Times New Roman",Font.BOLD,20));
		  
		  JScrollPane pane;
		  
		  bookColumn = new Vector<>();
		  bookColumn.add("ID");
		  bookColumn.add("Name");
		  bookColumn.add("Genre");
		  bookColumn.add("Author");
		  bookColumn.add("Stock");
		  
		  dtm = new DefaultTableModel(bookColumn,0);
		  tbl = new JTable(dtm);
		  tbl.getTableHeader().setReorderingAllowed(Boolean.FALSE);
		  tbl.setAutoCreateRowSorter(true);
		  bookTemp = listBook;
		  refreshTableBook(listBook);
		  pane = new JScrollPane(tbl);
		  
		  btnBorrow = new JButton("Borrow Selected Book");
		  
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
		  
		  JPanel pnlBtnBorrow = new JPanel(new GridLayout(1, 6, 5, 5));
		  pnlBtnBorrow.add(new JPanel());
		  pnlBtnBorrow.add(btnBorrow);
		  pnlBtnBorrow.add(new JPanel());
		  
		  JPanel mainPanel = new JPanel(new BorderLayout(5, 5));
		  mainPanel.add(titlePanel,BorderLayout.NORTH);
		  mainPanel.add(pane,BorderLayout.CENTER);
		  mainPanel.add(pnlBtnBorrow,BorderLayout.SOUTH);
		  mainPanel.setBorder(new EmptyBorder(10, 15, 15, 15));
		  
		  
		  bookList.add(mainPanel);
		  bookList.setClosable(true);
		  
		  btnBorrow.addMouseListener(this);
		 
		  return bookList;
		 }
	
	public void refreshTableBook(List<Book> listBook) {
		
		dtm = new DefaultTableModel(bookColumn,0);
		for (int i=0;i<listBook.size();i++) {
			// buat row baru
			row = new Vector<Object>();
			row.add(listBook.get(i).getCode());
			row.add(listBook.get(i).getName());
			row.add(listBook.get(i).getGenre());
			row.add(listBook.get(i).getCreator());
			row.add(listBook.get(i).getStock());
		
			
			// ini masukkin row ke model
			dtm.addRow(row);
			tbl.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		}
		
		// mengubah tblnya jadi model yang baru yaitu ketambahan satu row
		tbl.setModel(dtm);		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == btnBorrow) {
			int index = tbl.getSelectedRow();
			
			if (index == -1) new JOptionPane().showMessageDialog(null, "Choose To Borrow!");
			else {
				
				Book b = bookTemp.get(index);
				
				if (b.getStock() <= 0) {
					new JOptionPane().showMessageDialog(null, "Cant Borrow, Stock is Empty!");
					return;
				}
				
				// make new status
//				Main.status.get(b.getCode()).setStock(b.getStock() - 1);
				
				Main.books.get(b.getCode()).setStock(b.getStock() - 1);
				new JOptionPane().showMessageDialog(null, "Success Borrow Book!");
				refreshTableBook(bookTemp);
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
