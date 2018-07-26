package sample;

import javax.swing.*;
import java.awt.event.*;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Types;

public class Login extends JFrame implements ActionListener{
	JTextField t1;
	JPasswordField p1;
	JLabel l,lk;
	String username,password;
	Login()
	{
		
	}
	void Login1()
	{

		JLabel l1 = new JLabel("username:");
		l1.setBounds(50,50,80,30);
		t1 = new JTextField();
		t1.setBounds(150,50,100,30);
        JLabel l2 = new JLabel("password:");
		l2.setBounds(50,100,80,30);
		p1 = new JPasswordField();
		p1.setBounds(150,100,100,30);
        JButton b=new JButton("login");
        b.setBounds(80,150,100,30);
        l=new JLabel();
        l.setBounds(50,200,200,30);
        lk=new JLabel();
        lk.setBounds(50,250,200,30);
        
        b.addActionListener(new ActionListener(){  
			 public void actionPerformed(ActionEvent e){  
				 username=t1.getText();  
			     password= new String(p1.getPassword());  
			     
				 try{  
						//step1 load the driver class  
						Class.forName("oracle.jdbc.driver.OracleDriver");  
						  
						//step2 create  the connection object  
						Connection con=DriverManager.getConnection(  
						"jdbc:oracle:thin:@sys2:1521:xe","system","dharni747");  
						  
						//step3 create the statement object  
					/*	Statement stmt=con.createStatement();  
						  
						//step4 execute query  
						ResultSet rs=stmt.executeQuery("select username from person");  
						while(rs.next())  
						System.out.println(rs.getString(1));  */
                       CallableStatement cst = con.prepareCall ("{? = call check_signup(?)}");
                       cst.registerOutParameter(1, Types.INTEGER);
                       cst.setString(2,username);
                       cst.execute();
                       int k = cst.getInt(1);
                       cst.close();
                       if(k==0){
						
						CallableStatement cstmt = con.prepareCall ("{? = call login(?,?)}");
						//step5 close the connection object 
						cstmt.registerOutParameter(1, Types.INTEGER);
						cstmt.setString(2,username);
						cstmt.setString(3,password);
						cstmt.execute();
						int p = cstmt.getInt(1);
						cstmt.close();
						   
						   if(p==1)
						   {

								CallableStatement cst1 = con.prepareCall ("{? = call datefun(?)}");
								//step5 close the connection object 
								cst1.registerOutParameter(1, Types.INTEGER);
								cst1.setString(2,username);
								
								cst1.execute();
								int q = cst1.getInt(1);
								cst1.close();
								System.out.println(q);
								 functname(username,q);
							/*if(q==1)
							{	lk.setText("you missed one day");
							    message(q);
							}
							else if(q>1)
							{
								lk.setText("you missed "+q+" days");
								message(q);
							}*/
						 // l.setText("successfully logged in");
                       }
						   else
						   {
							   l.setText("username password dint match");
						   }
                       }
                       else
                       {
                       	l.setText("username doesnt exist");
                       }
                con.close();
                      // functname(username);
						}catch(Exception f){ System.out.println(f);}    
			         }  

		
	});
        add(t1);
        add(l1);
        add(p1);
        add(l2);
        add(b);
        add(l);
        add(lk);
        setSize(400,500);
		setLayout(null);
		setVisible(true);
}
	void functname(String user,int q)
	{
		if(q==1)
			JOptionPane.showMessageDialog(this,"logged in successfully\nyou missed one day");
		else if(q>1)
			JOptionPane.showMessageDialog(this,"logged in successfully\nyou missed"+q+"days");
		else
			JOptionPane.showMessageDialog(this,"logged in successfully");
		super.dispose();
		userpage u=new userpage();
		u.userpage1(user);
	}
}