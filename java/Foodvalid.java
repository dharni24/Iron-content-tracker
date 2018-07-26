package sample;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;  

import javax.swing.*;
public class Foodvalid extends JFrame{
	Foodvalid()
	{
		try{  
			//step1 load the driver class  
			Class.forName("oracle.jdbc.driver.OracleDriver");  
			  
			//step2 create  the connection object  
			Connection con=DriverManager.getConnection(  
			"jdbc:oracle:thin:@sys2:1521:xe","system","dharni747");  
			  
			//step3 create the statement object  
			Statement stmt=con.createStatement();  
		//	getContentPane().setLayout(new FlowLayout()); 
			DefaultListModel<String> l1 = new DefaultListModel<>();
			//step4 execute query  
			ResultSet rs=stmt.executeQuery("select food_name from food order by food_name asc");  
			while(rs.next())  
			{
				String s= rs.getString(1);
				 l1.addElement(s);  
			} 

	        JPanel contentPane = new JPanel();
			JList<String> list = new JList<>(l1); 
			//list.setBounds(100,100); 
			list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		//	list.setLayoutOrientation(JList.VERTICAL);
		 
			JScrollPane menuScrollPane = new JScrollPane();
	//		menuScrollPane.setMinimumSize(new Dimension(100, 50));
			//list.setVisibleRowCount(4);
			//JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,       menuScrollPane,list);
		      //JScrollBar scrollableTextArea = new JScrollBar();  
		        
		        menuScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		        menuScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		        contentPane.add(menuScrollPane);
		        list.setVisibleRowCount(4);
		        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		        add(contentPane);
		       
		        //list.add(scrollableTextArea);
		         //add(list);
		      // getContentPane().add(scrollableTextArea); 
			   //add(list);
			//menuScrollPane.setViewportView(list);
			   
			   //add(splitPane);
			 //contentPane.add(menuScrollPane);
			 //add(contentPane);
		//contentPane.setVisible(true);
//			 add(list);
		       setSize(400,400);  
		       setLayout(null);  
		       setVisible(true);
			}catch(Exception e){ System.out.println(e);}  
	}  
  


}