package sample;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Types;

import javax.swing.*;
public class scrolllist implements ActionListener {
	DefaultListModel<String> l1 = new DefaultListModel<>();
	JList<String> l;
	 String data[]=new String[50];
	 String username;
	 JLabel l9;
	 JLabel k=new JLabel();
    int count;
	 int i=0;
scrolllist()
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
	//	DefaultListModel<String> l1 = new DefaultListModel<>();
		//step4 execute query  
		ResultSet rs=stmt.executeQuery("select food_name from food order by food_name asc");  
		while(rs.next())  
		{
			String s= rs.getString(1);
			 l1.addElement(s);  
		} 
	}
	
	catch(Exception e){ System.out.println(e);}  
}
public void execute(String user){
	JFrame f=new JFrame();
	JPanel p=new JPanel();
	username=user;
//	String [] listentries={"apple","banana","lemon","orange","mango","potato","carrot"};
	l=new JList<>(l1);
	JScrollPane s=new JScrollPane(l);
	s.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	s.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
    l9=new JLabel("select the food you have consumed today");
    p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
    p.add(l9);
	p.add(s);
	
    l.setVisibleRowCount(4);
    l.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    f.add(p);
    //JLabel k=new JLabel();
    //k.setText(data[--i]);
    p.setBounds(10,10,500,500);
    //k.setBounds(50, 100, 100, 30);
    //p.add(k);
    f.setSize(600, 600);
    f.setVisible(true);
    JButton b=new JButton("add food");
    JButton b1=new JButton("done");
    b.setBounds(50,50,100,30);
    b1.setBounds(200,200,100,30);
    b.addActionListener(new ActionListener(){  
		 public void actionPerformed(ActionEvent e){  
			 //String data[]=new String[50]; 
			 //int i=0;
             if (l.getSelectedIndex() != -1) {                       
                data[i++]=l.getSelectedValue();
      //          JLabel k=new JLabel();
             //   k.setText(data[--i]);
        //        k.setBounds(50, 100, 100, 30);
          //      p.add(k);
             }
             count=i;
		 }
    });
    b1.addActionListener(new ActionListener(){  
		 public void actionPerformed(ActionEvent e){  
	//h=new JLabel[count];
	//t=new JTextField[count];
		//k.setText("no of "+count+" "+data[0]);	 
	/*int j=0;
	while(j<count)
    { 
		h[j]=new JLabel();
		h[j].setText(data[j]);
	    h[j].setBounds(50,200+(i+1)*50,100,30);	
	    j++;
	}*/
		//ironcal c=new ironcal();
		ironcal1();
		 }
    });
    p.add(b1);
    p.add(b);
    /*int j=0;
   while(j<count)
    {
    	p.add(h[j]);
        j++;
    }*/
        
}
void ironcal1()
{
	ironcall c=new ironcall();
	c.ironcal1(this);
}
public class ironcall extends JFrame implements ActionListener {
	  JLabel[] h;
	  JLabel l;
	  String username;
	  String[] data;
	  int count1;
	  int[] ironval;
	     JTextField[] t;
	     ironcall()
	     {
	    	 
	     }
	    public void ironcal1(scrolllist s)
	     {
	    	l=new JLabel();
	    	username=s.username;
	    	 h=new JLabel[s.count];
	    	 t=new JTextField[s.count];
	    	 data=new String[s.count];
	    	 int j=0;
	    	 count1=s.count;
	    	 ironval=new int[count1];
	    	 while(j<s.count)
	    	 {
	    		 h[j]=new JLabel();
	    		 t[j]=new JTextField();
	    		 h[j].setText(s.data[j]);
	    		 data[j]=s.data[j];
	    		 h[j].setBounds(10,(j+1)*50,200,30);
	    		 t[j].setBounds(230,(j+1)*50,30,30);
	    		 add(h[j]);
	    		 add(t[j]);
	    		 j++;
	    	 }
	    	 l.setBounds(50,(count+2)*50,300,30);
	    	 JButton b=new JButton("submit");
	    	 b.setBounds(200,(count+1)*50,100,30);
	    	 b.addActionListener(new ActionListener(){  
	    		 public void actionPerformed(ActionEvent e){
	    	int j=0;
	    	String temp;
			 while(j<count1){
   			temp=t[j].getText();
   			ironval[j]=Integer.parseInt(temp);
   			j++;
			 }
	    	try{  
	    		//step1 load the driver class  
	    		Class.forName("oracle.jdbc.driver.OracleDriver");  
	    		  
	    		//step2 create  the connection object  
	    		Connection con=DriverManager.getConnection(  
	    		"jdbc:oracle:thin:@sys2:1521:xe","system","dharni747");  
	    		  
	    		//step3 create the statement object  
	    		int d=0;
	    		while(d<count1){
	    		CallableStatement cstmt = con.prepareCall ("{call insert_food(?,?,?)}");
	    		//step5 close the connection object   
	    		cstmt.setString(1,username);
	    		cstmt.setString(2,data[d]);
	    		cstmt.setInt(3,ironval[d]);
	    		cstmt.execute();
	    		cstmt.close();
	    		   d++;
	    		}
	    		CallableStatement cs = con.prepareCall("{? = call insert_totaliron(?)}");
	    		cs.registerOutParameter(1, Types.INTEGER);
				cs.setString(2,username);
				cs.execute();
				int p = cs.getInt(1);
				cs.close();

	    		CallableStatement cse = con.prepareCall("{? = call retiron(?)}");
	    		cse.registerOutParameter(1, Types.INTEGER);
				cse.setString(2,username);
				cse.execute();
				int m = cse.getInt(1);
				cse.close();
				
				CallableStatement csq = con.prepareCall("{? = call missfun(?)}");
	    		csq.registerOutParameter(1, Types.INTEGER);
				csq.setString(2,username);
				csq.execute();
				int v = csq.getInt(1);
				csq.close();
				
				if(p<m)
				{
					message(p,v);
					//l.setText("you have failed to consume the required amount of iron");
				}
				else
				{
					message1(p);
				}
	    		con.close();
	    		}catch(Exception f){ System.out.println(f);}  
	  

	    		 }
	    	 });
	    	
	    	 add(b);
	    	 add(l);
	    	 setSize(400,400);
	    	 setLayout(null);
	    	 setVisible(true);
	     }
		void message(int p,int v)
		{   if(v>1)
		{
			JOptionPane.showMessageDialog(this,"Failed to reach your daily target\nyou have consumed "+p+"\nconsecutively ur consuming less iron content food for"+v+"days");
		}
		else
		JOptionPane.showMessageDialog(this,"Failed to reach your daily target\nyou have consumed "+p);
		}
		void message1(int p)
		{
			JOptionPane.showMessageDialog(this,"Reached your daily target\nyou have consumed "+p);
		}
	
}
	/*public static void main(String[] args)
	{
		scrolllist s =new scrolllist();
		s.execute();
		int i=0;
	    while(i<s.count)
	    	
	    {
	    	System.out.println(s.data[i]);
	    	i++;
	    }
	}*/
}

