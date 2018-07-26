package sample;
import java.awt.event.ActionListener;

import javax.swing.*;
public class ironcal extends JFrame implements ActionListener {
	  JLabel[] h;
	     JTextField[] t;
	     ironcal()
	     {
	    	 
	     }
	    public void ironcal1()
	     {
	    	 h=new JLabel[5];
	    	 int j=0;
	    	 while(j<5)
	    	 {
	    		 h[j]=new JLabel();
	    		 h[j].setText("hi");
	    		 h[j].setBounds(50,(j+1)*50,100,30);
	    		 add(h[j]);
                 j++;
	    	 }
	    	 setSize(400,400);
	    	 setVisible(true);
	     }
		
	public static void main(String[] args)
	{
		ironcal i = new ironcal();
		i.ironcal1();
}
}