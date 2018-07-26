package sample;

import java.awt.Component;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Types;

import javax.swing.*;

public class userpage extends JFrame implements ActionListener{
JLabel l1,l2,l3,l4,l5,l6,l7,l8;
JMenuBar mb;    
JMenu view,view1,view2,view3;    
JMenuItem viewall,viewbmi,viewiron,tracker,day1,day2,cal1,cal2;  
String user1;
String firstname,lastname,gender;
JTextArea t;
int height,weight,age,bmi,iron;
String data[]=new String[20];
float iron1[]=new float[20];
int count;
userpage()
{
	
}
public void userpage1(String user)
{      user1=user;
l1=new JLabel();
l1.setBounds(200,50,300,30);
l2=new JLabel();
l2.setBounds(200,100,300,30);
l3=new JLabel();
l3.setBounds(200,150,300,30);
l4=new JLabel();
l4.setBounds(200,200,300,30);
l5=new JLabel();
l5.setBounds(200,250,300,30);
l6=new JLabel();
l6.setBounds(200,300,300,30);
l7=new JLabel();
l7.setBounds(200,350,300,30);
l8=new JLabel();
l8.setBounds(200,400,300,30);
t=new JTextArea(10,2);
t.setBounds(100,50,300,300);
	   JMenuBar mb=new JMenuBar();  
       view=new JMenu("view personal details");
       view1=new JMenu("iron misses");
       view2=new JMenu("tracker");
       view3=new JMenu("calculate for today");
       viewall=new JMenuItem("all details");
       viewbmi=new JMenuItem("BMI");

       viewiron=new JMenuItem("iron required");
       tracker=new JMenuItem("misses");
       day1=new JMenuItem("complete tracker");
       day2=new JMenuItem("today");
      // cal1=new JMenuItem("view already entered");
       cal2=new JMenuItem("new entry");
       view.add(viewall);
       view.add(viewbmi);
       view.add(viewiron);
       view1.add(tracker);
       view2.add(day1);
       view2.add(day2);
       //view3.add(cal1);
       view3.add(cal2);
       viewbmi.addActionListener(new ActionListener(){  
			 public void actionPerformed(ActionEvent e){  
					try{  
						
						//step1 load the driver class  
						Class.forName("oracle.jdbc.driver.OracleDriver");  
						  
						//step2 create  the connection object  
						Connection con=DriverManager.getConnection(  
						"jdbc:oracle:thin:@sys2:1521:xe","system","dharni747");  
						CallableStatement cst1 = con.prepareCall ("{? = call bmiret(?)}");
						//step5 close the connection object 
						cst1.registerOutParameter(1, Types.INTEGER);
						cst1.setString(2,user1);
						
						cst1.execute();
						int q = cst1.getInt(1);
						l1.setText("Your Bmi is "+q);
						 l2.setText("");
			             l3.setText("");
			             l4.setText("");
			             l5.setText("");
			             l6.setText("");
			             l7.setText("");
			             l8.setText("");			
					    
						cst1.close();
						
						
						   con.close();
						  
						}catch(Exception f){ System.out.println(f);}  
				}
       });
       viewiron.addActionListener(new ActionListener(){  
			 public void actionPerformed(ActionEvent e){  
					try{  
						
						//step1 load the driver class  
						Class.forName("oracle.jdbc.driver.OracleDriver");  
						  
						//step2 create  the connection object  
						Connection con=DriverManager.getConnection(  
						"jdbc:oracle:thin:@sys2:1521:xe","system","dharni747");  
						CallableStatement cst1 = con.prepareCall ("{? = call ironret(?)}");
						//step5 close the connection object 
						cst1.registerOutParameter(1, Types.INTEGER);
						cst1.setString(2,user1);
						
						cst1.execute();
						int q = cst1.getInt(1);
						l1.setText("Your daily target is "+q+" mg of iron");
						 l2.setText("");
			             l3.setText("");
			             l4.setText("");
			             l5.setText("");
			             l6.setText("");
			             l7.setText("");
			             l8.setText("");			
					    
						cst1.close();
						
						
						   con.close();
						  
						}catch(Exception f){ System.out.println(f);}  
				}
     });
       day2.addActionListener(new ActionListener(){  
			 public void actionPerformed(ActionEvent e){  
					try{  
						
						//step1 load the driver class  
						Class.forName("oracle.jdbc.driver.OracleDriver");  
						  
						//step2 create  the connection object  
						Connection con=DriverManager.getConnection(  
						"jdbc:oracle:thin:@sys2:1521:xe","system","dharni747");  
						CallableStatement cst1 = con.prepareCall ("{? = call ironcons(?)}");
						//step5 close the connection object 
						cst1.registerOutParameter(1, Types.INTEGER);
						cst1.setString(2,user1);
						
						cst1.execute();
						float q = cst1.getFloat(1);
						l1.setText("total iron consumption of today "+q+" mg of iron");
						 l2.setText("");
			             l3.setText("");
			             l4.setText("");
			             l5.setText("");
			             l6.setText("");
			             l7.setText("");
			             l8.setText("");			
					    
						cst1.close();
						
						
						   con.close();
						  
						}catch(Exception f){ System.out.println(f);}  
				}
   });
       viewall.addActionListener(new ActionListener(){  
  			 public void actionPerformed(ActionEvent e){  
  					try{  
  						
  						//step1 load the driver class  
  						Class.forName("oracle.jdbc.driver.OracleDriver");  
  						  
  						//step2 create  the connection object  
  						Connection con=DriverManager.getConnection(  
  						"jdbc:oracle:thin:@sys2:1521:xe","system","dharni747");  
  						  
  						//step3 create the statement object  
  						/*Statement stmt=con.createStatement();  
  						String code="select first_name,last_name,height,weight,age,gender,bmi,iron_required from person where username = '"+user1+"'";
  						//step4 execute query  
  						ResultSet rs=stmt.executeQuery(code);
  						*/
  						CallableStatement cstmt = con.prepareCall ("{call detailsdis(?,?,?,?,?,?,?,?,?)}");
  						//step5 close the connection object   
  						cstmt.registerOutParameter(2, Types.VARCHAR);
  						cstmt.registerOutParameter(3, Types.VARCHAR);
  						cstmt.registerOutParameter(4, Types.INTEGER);
  						cstmt.registerOutParameter(5, Types.INTEGER);
  						cstmt.registerOutParameter(6, Types.INTEGER);
  						cstmt.registerOutParameter(7, Types.VARCHAR);
  						cstmt.registerOutParameter(8, Types.VARCHAR);
  						cstmt.registerOutParameter(9, Types.VARCHAR);
  						
						cstmt.setString(1,user1);
						
						cstmt.execute();
		 
  					
  							firstname=cstmt.getString(2);	 
  						     lastname=cstmt.getString(3);    
  						     String a=cstmt.getString(4);
  						     String b=cstmt.getString(5);
  						     String c=cstmt.getString(6);
  						     height=Integer.parseInt(a);  
  						     weight=Integer.parseInt(b);
  						     age=Integer.parseInt(c);
  						     gender=cstmt.getString(7);
  						     String bmi1=cstmt.getString(8);
  						     String iron1=cstmt.getString(9);
  						     
  						     bmi=Integer.parseInt(bmi1);
  						     iron=Integer.parseInt(iron1);
  						   l1.setText("firstname : "+firstname);
				             l2.setText("lastname : "+lastname);
				             l3.setText("height : "+height);
				             l4.setText("weight : "+weight);
				             l5.setText("age : "+age);
				             l6.setText("\ngender : "+gender);
				             l7.setText("\nBMI : "+bmi);
				             l8.setText("\niron_required : "+iron);			
						        //l1.setBounds(200,200,300,500);


						cstmt.close();
  						   con.close();
  						  
  						}catch(Exception f){ System.out.println(f);}  
  				}
         });
       day1.addActionListener(new ActionListener(){  
			 public void actionPerformed(ActionEvent e){
				 int i=0;
				 try{  
		  
						Class.forName("oracle.jdbc.driver.OracleDriver");  				  
						Connection con=DriverManager.getConnection(  
						"jdbc:oracle:thin:@sys2:1521:xe","system","dharni747");  
						  
						//step3 create the statement object  
						Statement stmt=con.createStatement();  
						  
						//step4 execute query  
						ResultSet rs=stmt.executeQuery("select date1,total_iron_consumed from daily_database where username = '"+user1+"'");  
						while(rs.next())  
						{
							data[i]=rs.getString(1);
							iron1[i]=rs.getFloat(2);
							i++;
							count=i;
						}
						display();
						   con.close();
						  
						}catch(Exception k){ System.out.println(k);}  
						}  

			 });
        /*day1.addActionListener(new ActionListener(){  
		 public void actionPerformed(ActionEvent e){
			 int i=0;
			 try{  
	  
					Class.forName("oracle.jdbc.driver.OracleDriver");  				  
					Connection con=DriverManager.getConnection(  
					"jdbc:oracle:thin:@sys2:1521:xe","system","dharni747");  
					  
					//step3 create the statement object  
					Statement stmt=con.createStatement();  
					  
					//step4 execute query  
					ResultSet rs=stmt.executeQuery("select total_iron_consumed from daily_database where username = '"+user1+"'");  
					while(rs.next())  
					{
						data[i]=rs.getString(1);
						iron1[i]=rs.getFloat(2);
						i++;
						count=i;
					}
					display();
					   con.close();
					  
					}catch(Exception k){ System.out.println(k);}  
					}  

		 });
*/
       
       tracker.addActionListener(new ActionListener(){  
			 public void actionPerformed(ActionEvent e){  
				try{  
					Class.forName("oracle.jdbc.driver.OracleDriver");  
					  
					//step2 create  the connection object  
					Connection con=DriverManager.getConnection(  
					"jdbc:oracle:thin:@sys2:1521:xe","system","dharni747");  
				
					CallableStatement csk = con.prepareCall ("{? = call missesret(?)}");
					//step5 close the connection object 
					csk.registerOutParameter(1, Types.INTEGER);
					csk.setString(2,user1);
					
					csk.execute();
					int q = csk.getInt(1);
					csk.close();
					con.close();
					message2(q);
					
						}catch(Exception f){ System.out.println(f);}  
				}
   });
       cal2.addActionListener(new ActionListener(){  
			 public void actionPerformed(ActionEvent e){  
		        functionn(user1);
	     }
       });
       mb.add(view);mb.add(view1);mb.add(view2);
       mb.add(view3);
       add(mb);
       add(l1);
       add(l2);
       add(l3);
       add(l4);
       add(l5);
       add(l6);
       add(l7);
       add(l8);
       
       setJMenuBar(mb);  
       setLayout(null);    
       setSize(700,700);    
       setVisible(true); 
       
}
/*public void actionPerformed(ActionEvent e) {    
if(e.getSource()==viewall)    
{
	try{  
		
		//step1 load the driver class  
		Class.forName("oracle.jdbc.driver.OracleDriver");  
		  
		//step2 create  the connection object  
		Connection con=DriverManager.getConnection(  
		"jdbc:oracle:thin:@sys2:1521:xe","system","dharni747");  
		  
		//step3 create the statement object  
		Statement stmt=con.createStatement();  
		String code="select * from view1 where username ="+user1;
		//step4 execute query  
		ResultSet rs=stmt.executeQuery(code);
	    
		while(rs.next())  
		{
			firstname=rs.getString(2);	 
		     lastname=rs.getString(3);    
		     String a=rs.getString(4);
		     String b=rs.getString(5);
		     String c=rs.getString(6);
		     height=Integer.parseInt(a);  
		     weight=Integer.parseInt(b);
		     age=Integer.parseInt(c);
		     gender=rs.getString(7);
		     String bmi1=rs.getString(8);
		     String iron1=rs.getString(9);
		     
		     bmi=Integer.parseInt(bmi1);
		     iron=Integer.parseInt(iron1);
             l1.setText("firstname : "+firstname+"\nlastname : "+lastname+"\nheight : "+height+"\nweight : "+weight+"\nage : "+age+"\ngender : "+gender+"\nBMI : "+bmi+"\niron_required : "+iron);			
		     //l1.setBounds(200,200,300,500);
		} 
		   con.close();
		  
		}catch(Exception f){ System.out.println(f);}  
}
if(e.getSource()==viewbmi)    
{
	
}
if(e.getSource()==viewbmi)    
{
	
}
if(e.getSource()==tracker)    
{
	
}
if(e.getSource()==day1)    
{
	
}
if(e.getSource()==day2)    
{
	
}
if(e.getSource()==cal1)    
{
	
}
if(e.getSource()==cal2)    
{
	
}
} */  
 
/*public static void main(String[] args)
{
	userpage u = new userpage();
	u.userpage1("sandy");
}*/
void display()
{
	displayframe f=new displayframe();
	f.display1(this);
}
void message2(int q)
{
	JOptionPane.showMessageDialog(this,"you have consumed less iron food for "+q+" days");
}
void functionn(String user)
{
	scrolllist s=new scrolllist();
	s.execute(user);
	super.dispose();

}
}
