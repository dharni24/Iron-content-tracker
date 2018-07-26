package sample;
import javax.swing.*;
import java.awt.event.*;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;   
import java.sql.Types;
public class Signup extends JFrame implements ActionListener{
	JRadioButton b81,b82,b91,b92,b93,b94,b95;
	JPasswordField p1;
	JTextField t1,t3,t4,t5,t6,t7;
	JButton b;
	JLabel l;
	String username,password,firstname,lastname,gender,category;
	int height,weight,age,bmi,ir;
    Signup()
    {
    	
    }
	void Signup1()
	{
		JLabel l1 = new JLabel("username:");
		l1.setBounds(50,50,80,30);
		t1 = new JTextField();
		t1.setBounds(150,50,100,30);
        JLabel l2 = new JLabel("password:");
		l2.setBounds(50,100,80,30);
		p1 = new JPasswordField();
		p1.setBounds(150,100,100,30);
		JLabel l3 = new JLabel("Firstname:");
		l3.setBounds(50,150,80,30);
		t3 = new JTextField();
		t3.setBounds(150,150,100,30);
		JLabel l4 = new JLabel("Secondname:");
		l4.setBounds(50,200,80,30);
		t4 = new JTextField();
		t4.setBounds(150,200,100,30);
		JLabel l5 = new JLabel("height:");
		l5.setBounds(50,250,50,30);
		t5 = new JTextField();
		t5.setBounds(100,250,30,30);
		JLabel l6 = new JLabel("weight:");
		l6.setBounds(150,250,50,30);
		t6 = new JTextField();
		t6.setBounds(200,250,30,30);

		JLabel l7 = new JLabel("age:");
		l7.setBounds(50,300,50,30);
		t7 = new JTextField();
		t7.setBounds(100,300,30,30);
		JLabel l8 = new JLabel("Gender:");
		l8.setBounds(50,350,50,30);

		b81=new JRadioButton("Male");
		b81.setBounds(100,350,80,30);      
		b82=new JRadioButton("Female");    
		b82.setBounds(180,350,80,30);    
		ButtonGroup bg8=new ButtonGroup();    
		bg8.add(b81);bg8.add(b82);    
		JLabel l9 = new JLabel("category:");
		l9.setBounds(50,400,80,30);


		b91=new JRadioButton("infant");
		b91.setBounds(120,400,80,30);      
		b92=new JRadioButton("children");    
		b92.setBounds(200,400,80,30);    

		b93=new JRadioButton("adult");
		b93.setBounds(50,430,80,30);      
		b94=new JRadioButton("pregnant");    
		b94.setBounds(120,430,80,30); 
	      
			b95=new JRadioButton("lactating");    
			b95.setBounds(200,430,80,30);
		ButtonGroup bg9=new ButtonGroup();    
		bg9.add(b91);bg9.add(b93); 
		bg9.add(b92);bg9.add(b94);bg9.add(b95);


		   l=new JLabel();
		   l.setBounds(100,550,300,30);
		b=new JButton("submit");
		b.setBounds(100,500,80,30);
		 b.addActionListener(new ActionListener(){  
			 public void actionPerformed(ActionEvent e){  
				 username=t1.getText();  
			     firstname=t3.getText();	 
			     lastname=t4.getText();  
			     password= new String(p1.getPassword());  
			     String a=t5.getText();
			     String b=t6.getText();
			     String c=t7.getText();
			     height=Integer.parseInt(a);  
			     weight=Integer.parseInt(b);
			     age=Integer.parseInt(c);
				if(b81.isSelected()){    
			    gender="male";
			    
			    if(b91.isSelected()){    
			        category="infants";
			    }    
			    if(b92.isSelected()){    
			    	category="children";    
			    }  
			    if(b93.isSelected()){    
			    	category="male";
			    } 
			}    
			if(b82.isSelected()){    
			    gender="female";
			    if(b91.isSelected()){    
			        category="infants";
			    }    
			    if(b92.isSelected()){    
			    	category="children";    
			    }  
			    if(b93.isSelected()){    
			    	category="female";
			    }
			    if(b94.isSelected()){    
			    	category="pregnant";    
			    }  
			    if(b95.isSelected()){    
			    	category="lactating";
			    }
			}    

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
                        
                        if(k==1){
				//		System.out.println(username+" "+password+" "+height+" "+" "+weight);
						CallableStatement cstmt = con.prepareCall ("{call signup(?,?,?,?,?,?,?,?,?)}");
						//step5 close the connection object   
						cstmt.setString(1,username);
						cstmt.setString(2,firstname);
						cstmt.setString(3,lastname);
						cstmt.setString(4,password);
						cstmt.setInt(5,height);
						cstmt.setInt(6,weight);
						cstmt.setInt(7,age);
						cstmt.setString(8,gender);
						cstmt.setString(9,category);
						cstmt.execute();
						cstmt.close();
						   
						 /*   Statement st=con.createStatement();
						    String code="create view "+username+" as select username,first_name,last_name,height,weight,age,gender,bmi,iron_required from person where username ="+username;
						    st.executeUpdate(code);*/
						    con.close();
				//		  l.setText("created account successfully");
						  LoginSignup $=new LoginSignup();
						  $.LoginSignup1();
						  CloseFrame();
                        }
                        else
                        {
                        	l.setText("username already exist");
                        }
                        
						  
						}catch(Exception f){ System.out.println(f);}    
			         }  
			
			     }); 
		add(b);
		add(l1);
		add(t1);

		add(l2);
		add(p1);

		add(l3);
		add(t3);

		add(l4);
		add(t4);
		add(l5);
		add(t5);
		add(l6);
		add(t6);
		add(l7);
		add(t7);

		add(l8);
		
		add(l9);
		add(b81);
		add(b82);

		add(b95);
		add(b94);
		add(b93);

		add(b91);
		add(b92);
		add(l);
		setSize(700,700);
		setLayout(null);
		setVisible(true);
		
	}
	void CloseFrame()
	{
		JOptionPane.showMessageDialog(this,"created account successfully");
	super.dispose();
}
}