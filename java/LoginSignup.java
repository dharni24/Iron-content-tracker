package sample;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class LoginSignup extends JFrame implements ActionListener{
	JLabel l,l1,l2;
	JButton b1,b2;
	LoginSignup()
	{
		
	}
	void LoginSignup1()
	{
	 l=new JLabel("IRON CONTENT TRACKER");
	 l1=new JLabel("already have an account?");
	 l2=new JLabel("create your new account");
	 b1=new JButton("LOGIN");
	 b2=new JButton("SIGNUP");
	 b2.addActionListener(new ActionListener(){  
		 public void actionPerformed(ActionEvent e){  
	     Signup s=new Signup();
	     s.Signup1();
	     CloseFrame();
	     
	}
	 });
	 b1.addActionListener(new ActionListener(){  
		 public void actionPerformed(ActionEvent e){  
	     Login p=new Login();
	     p.Login1();
	     CloseFrame();
	}
	 });
	 l.setBounds(100,50,200,50);
     l1.setBounds(50,100,200,50);
	 b1.setBounds(50,150,100,30);
	 l2.setBounds(50,200,200,50);
	 b2.setBounds(50,250,100,30);
	  add(l);
	  add(l1);
	  add(b1);
	  add(l2);
	  add(b2);
	  setLayout(null);
	  setSize(500,500);
	  setVisible(true);
	}
	public void CloseFrame()
	{
		super.dispose();
	}
}
