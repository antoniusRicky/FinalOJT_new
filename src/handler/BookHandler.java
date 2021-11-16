package handler;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import model.Book;

public class BookHandler {
	
	private Vector<Object> row;
	 private Vector<Object> bookColumn;
	 private JTable tbl;
	 private DefaultTableModel dtm;
//	 private JButton btnDelete;
//	 private List<Book> bookView;
//	 private JPanel panel;
	
	public JInternalFrame showViewBookForm(List<Book> listBook) {
		
		  JInternalFrame bookList = new JInternalFrame();
		  bookList.setVisible(true);
		  bookList.setSize(800, 400);
		  bookList.setLocation(100, 70);
		  
		  JLabel title = new JLabel("OJT Library",JLabel.CENTER);
		  title.setFont(new Font("Times New Roman",Font.BOLD,30));
		
		  JButton back = new JButton("Back");
		  
		  JLabel subTitle = new JLabel("Book List",JLabel.CENTER);
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
//		  bookView = listBook;
		  refreshTableBook(listBook);
		  pane = new JScrollPane(tbl);
		  
		  
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
		  
		  JPanel mainPanel = new JPanel(new BorderLayout(5, 5));
		  mainPanel.add(titlePanel,BorderLayout.NORTH);
		  mainPanel.add(pane,BorderLayout.CENTER);
		  mainPanel.setBorder(new EmptyBorder(10, 15, 15, 15));
		  
		  
		  bookList.add(mainPanel);
		  bookList.setClosable(true);
		 
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
}
