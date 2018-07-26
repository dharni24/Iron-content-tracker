package sample;
import javax.swing.*;
public class displayframe extends JFrame{
JLabel l[];
void display1(userpage u)
{
	l=new JLabel[u.count];
	int i=0;
	while(i<u.count)
	{   l[i]=new JLabel(u.data[i]+" : "+u.iron1[i]);
	    l[i].setBounds(100,50*(i+1),300,30);
	    add(l[i]);
	    i++;
	}
	setLayout(null);
	setSize(700,700);
	setVisible(true);
}
}
