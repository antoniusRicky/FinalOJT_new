package main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;

import gui.LoginMenu;
import model.Book;
import model.Role;
import model.Status;
import model.User;

public class Main {
	
	Scanner scan = new Scanner(System.in);
	public static HashMap<String, User> users;
	public static HashMap<String, Book> books;
	public static HashMap<String, Status> status;
	public static HashMap<String, Role> roles;
	
	public static String currUser = "";
	public static String currPwd = "";
	public static String currRole = "";
	
	public static void main(String[] args) {
		new Main();
	}
	
	public Main() {
		// init
		users = new HashMap<String, User>();
		books = new HashMap<String, Book>();
		status = new HashMap<String, Status>();
		roles = new HashMap<String, Role>();
		
		currUser = "";
		currPwd = "";
		currRole = "";
	
		// datas
		listData();
		
		// login menu
		LoginMenu start = new LoginMenu();
//		
//		while (true) {
//			clr();
//			loginMenu();
//			if (validationMenu()) break;
//		}
		updateText();
	}
	
	public boolean validationMenu() {
		if (currRole.equals("rl-4")) {
			clr();
			System.out.println("Need Approval To Login");
			scan.nextLine();
			currUser = "";currRole = "";currPwd = "";
			return false;
		}
		else {
			int inputa = 0;
			
			while(inputa != 5) {
				clr();
				mainMenu();
				inputa = scan.nextInt();
				inputProc(inputa);
				if (inputa == 4) break;
			}
		}
		return true;
	}
	
	public void listData() {
		// user
		readUser();
		
		// books
		readBooks();
		
		// status
		readTrans();
		
		// roles
		readRoles();
	}
	
	public void readUser() {
		try {
			InputStream in = getClass().getResourceAsStream("/datas/user.txt");
			Reader fr = new InputStreamReader(in, "utf-8");
			int datas = fr.read();
			
			// temp for string
			String temp = "";
			while(datas != -1) {
				temp += (char) datas;
                datas = fr.read();  
			}
			
			// separated enter
			String[] splitEnter = temp.split("\n");
			for (int i = 0; i < splitEnter.length; i++) {
				String[] splitAt = splitEnter[i].split("@");
				splitAt[2] = splitAt[2].replaceAll("\r", "");
				users.put(splitAt[0], new User(splitAt[0], splitAt[1], splitAt[2]));
			}
			
			fr.close();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void readBooks() {
		try {
			InputStream in = getClass().getResourceAsStream("/datas/books.txt");
			Reader fr = new InputStreamReader(in, "utf-8");
			int datas = fr.read();
			
			// temp for string
			String temp = "";
			while(datas != -1) {
				temp += (char) datas;
                datas = fr.read();  
			}
			
			// separated enter
			String[] splitEnter = temp.split("\n");
			for (int i = 0; i < splitEnter.length; i++) {
				String[] splitAt = splitEnter[i].split("@");
				splitAt[4] = splitAt[4].replaceAll("\r", "");
				int stockTemp = Integer.parseInt(splitAt[4]);
				books.put(splitAt[0], new Book(splitAt[0], splitAt[1], splitAt[2], splitAt[3], stockTemp));
			}
		
			fr.close();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void readTrans() {
		try {
			InputStream in = getClass().getResourceAsStream("/datas/status.txt");
			Reader fr = new InputStreamReader(in, "utf-8");
			int datas = fr.read();
			
			// temp for string
			String temp = "";
			while(datas != -1) {
				temp += (char) datas;
                datas = fr.read();  
			}
			
			// separated enter
			String[] splitEnter = temp.split("\n");
			for (int i = 0; i < splitEnter.length; i++) {
				String[] splitAt = splitEnter[i].split("@");
				splitAt[3] = splitAt[3].replaceAll("\r", "");
				status.put(splitAt[0], new Status(splitAt[0], splitAt[1], splitAt[2], splitAt[3]));
			}
			
			fr.close();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void readRoles() {
		try {
			InputStream in = getClass().getResourceAsStream("/datas/roles.txt");
			Reader fr = new InputStreamReader(in, "utf-8");
			int datas = fr.read();
			
			// temp for string
			String temp = "";
			while(datas != -1) {
				temp += (char) datas;
                datas = fr.read();  
			}
			
			// separated enter
			String[] splitEnter = temp.split("\n");
			for (int i = 0; i < splitEnter.length; i++) {
				String[] splitAt = splitEnter[i].split("@");
				splitAt[1] = splitAt[1].replaceAll("\r", "");
				roles.put(splitAt[0], new Role(splitAt[0], splitAt[1]));
			}
			
			fr.close();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void updateText() {
		// user
		updateUser();
		
		// books
		updateBooks();
		
		// status
		updateStatus();
		
		// roles
		updateRoles();
	}
	
	public static void updateUser() {
		try {
			File usersF = new File("src/datas/user.txt");
			FileWriter fw = new FileWriter(usersF);
			String updateUser = "";
			
			for (Map.Entry<String, User> u : users.entrySet()) {
				User uu = u.getValue();
				String temp = uu.getId() + "@" + uu.getPassword() + "@" + uu.getRole() + "\n";
				updateUser += temp;
			}
			
			fw.write(updateUser);
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void updateBooks() {
		try {
			
			File booksF = new File("src/datas/books.txt");
			FileWriter fw = new FileWriter(booksF);
			String updatedBooks = "";
			
			for (Map.Entry<String, Book> u : books.entrySet()) {
				Book b = u.getValue();
				String temp = b.getCode() + "@" + b.getName() + "@" + b.getGenre() + "@" + b.getCreator() + "@" + b.getStock() + "\n";
				updatedBooks += temp;
			}
			
			fw.write(updatedBooks);
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void updateStatus() {
		try {
			File statusF = new File("src/datas/status.txt");
			FileWriter fw = new FileWriter(statusF);
			String updatedStatus = "";
			
			for (Map.Entry<String, Status> s : status.entrySet()) {
				Status ss = s.getValue();
				String temp = ss.getStatusCode() + "@" + ss.getBorrower() + "@" + ss.getBookCode() + "@" + ss.getDate() + "\n";
				updatedStatus += temp;
			}
			
			fw.write(updatedStatus);
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void updateRoles() {
		try {
			File roleF = new File("src/datas/roles.txt");
			FileWriter fw = new FileWriter(roleF);
			String updatedRole = "";
			
			for (Map.Entry<String, Role> r : roles.entrySet()) {
				Role rr = r.getValue();
				String temp = rr.getCode() + "@" + rr.getName() + "\n";
				updatedRole += temp;
			}
			
			fw.write(updatedRole);
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void loginMenu() {
		while (currUser.isEmpty()) {
			System.out.println("Please Login First");
			System.out.println("--------------------------------");
			
			// id
			while(currUser.isEmpty()) {
				System.out.print("Input Id : ");
				String id = scan.nextLine();
				
				for (Map.Entry<String, User> u : users.entrySet()) {
					if (id.equals(u.getKey())) {
						currRole = u.getValue().getRole();
						currUser = id;
						break;
					}
				}
			}
			
			// pwd
			String pwd = "";
			User tempUser = new User();
			// get the data
			for (Map.Entry<String, User> u : users.entrySet()) {
				if (currUser.equals(u.getKey())) {
					tempUser = u.getValue();
					break;
				}
			}
			
			while(currPwd.isEmpty()) {
				System.out.print("Input Password : ");
				pwd = scan.nextLine();
				
				// check pwd
				if (pwd.equals(tempUser.getPassword())) {
					currPwd = pwd;
					break;
				}
			}
		}
	}
	
	public void mainMenu() {
		System.out.println("Welcome To OJT Library");
		System.out.println("--------------------------------");
		System.out.println("1. Show Available Book");
		System.out.println("2. Borrow");
		System.out.println("3. Return");
		System.out.println("4. LogOut");
		System.out.println("5. Exit");
		System.out.print("Choose [1 - 5] : ");
		
	}
	
	public void inputProc(int input) {
		switch (input) {
		case 1:
			showAll();
			// exit show
			System.out.println("Press any key to continue...");
			scan.nextLine();scan.nextLine();
			break;
		case 2:
			borrowBook();
			break;
		case 3:
			returnBook();
			break;
		case 4:
			System.out.println("LogOut Success..");
			scan.nextLine();scan.nextLine();
			main(null);
			break;
		}
	}
	
	public void showAll() {
		clr();
		int numbs = 0;
		for (Map.Entry<String, Book> u : books.entrySet()) {
			Book b = u.getValue();
			if (b.getStock() == 0) continue;
			System.out.println(++numbs + ". " + b.getCode() + " | " + b.getName() + " | " + b.getGenre() + " | " + b.getCreator() + " | " + b.getStock());
		}
	}
	
	public void borrowBook() {
		// check is empty
		if (books.isEmpty()) {
			clr();
			System.out.println("No Books Available, Press any key to continue...");
			scan.nextLine();scan.nextLine();
			return;
		}
		
		showAll();
		
		String selected = "";
		scan.nextLine();
		while (selected.isEmpty()) {
			System.out.print("Input book code to borrow : ");
			String input = scan.nextLine();	
			for (Map.Entry<String, Book> u : books.entrySet()) {
				Book b = u.getValue();
				if (b.getStock() == 0) continue;
				if (input.equals(b.getCode())) {
					selected = input;
					// update stock
					books.replace(u.getKey(), new Book(b.getCode(), b.getName(), b.getGenre(), b.getCode(), b.getStock() - 1));
					
					// new status
					Random rand = new Random();
				    int upperbound = 999;
				    int int_random = rand.nextInt(upperbound);
				    String stsCode = "STS-" + int_random;
					status.put(stsCode, new Status(stsCode, currUser, b.getCode(), java.time.LocalDate.now().toString()));
					break;
				}
			}
		}
		
		System.out.println("Success...");
		scan.nextLine();
	}

	public void returnBook() {
		// check is empty
		if (status.isEmpty()) {
			clr();
			System.out.println("No book is borrowed, Press any key to continue...");
			scan.nextLine();scan.nextLine();
			return;
		}
		
		String selected = "";
		showTransaction();
		scan.nextLine();
		while(selected.isEmpty()) {
			System.out.print("Please input transaction code to return the book : ");
			String input = scan.nextLine();
			
			for (Map.Entry<String, Status> s : status.entrySet()) {
				Status ss = s.getValue();
				if (input.equals(ss.getStatusCode())) {
					// remove status
					status.remove(input);
					// update stock
					Book b = books.get(ss.getBookCode());
					books.replace(ss.getBookCode(), new Book(ss.getBookCode(), b.getName(), b.getGenre(), b.getCreator(), b.getStock() + 1));
					// selected
					selected = input;
					break;
				}
			}
		}
		
		System.out.println("Success...");
		scan.nextLine();
	}
	
	public void showTransaction() {
		clr();
		int numbs = 0;
		for (Map.Entry<String, Status> s : status.entrySet()) {
			Status ss = s.getValue();
			System.out.println(++numbs + ". " + ss.getStatusCode() + " | " + ss.getBorrower() + " | " + ss.getBookCode() + " | " + ss.getDate());
		}
	}
	
	public void clr() {
		for (int i = 0; i < 50; i++) {
			System.out.println("");
		}
	}

	public static HashMap<String, User> getUsers() {
		return users;
	}

	public static void setUsers(HashMap<String, User> users) {
		Main.users = users;
	}

	public static HashMap<String, Book> getBooks() {
		return books;
	}

	public static void setBooks(HashMap<String, Book> books) {
		Main.books = books;
	}

	public static HashMap<String, Status> getStatus() {
		return status;
	}

	public static void setStatus(HashMap<String, Status> status) {
		Main.status = status;
	}

	public static HashMap<String, Role> getRoles() {
		return roles;
	}

	public static void setRoles(HashMap<String, Role> roles) {
		Main.roles = roles;
	}
	
	
}
