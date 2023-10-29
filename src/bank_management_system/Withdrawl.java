package bank_management_system;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import javax.swing.*;
import java.awt.event.*;
//import java.sql.Date;
import java.util.Date;


public class Withdrawl extends JFrame implements ActionListener{

	JTextField amount;
	JButton withdraw, back;
	String pinnumber;
	Withdrawl(String pinnumber){
		this.pinnumber = pinnumber;
		
		setLayout(null); //without doing this setBounds not work
		
		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
		Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
		ImageIcon i3 = new ImageIcon(i2);
		JLabel image = new JLabel(i3);
		image.setBounds(0, 0, 900, 900);
		add(image);
		
		JLabel text = new JLabel("Enter the amount you want to Withdraw");
		text.setForeground(Color.WHITE);
		text.setFont(new Font("System", Font.BOLD, 16));
		text.setBounds(190, 300, 400, 20);
		image.add(text);
		
		amount = new JTextField();
		amount.setFont(new Font("Raleway", Font.BOLD,22));
		amount.setBounds(170, 350, 320, 25);
		image.add(amount);
		
		withdraw = new JButton("Withdraw");
		withdraw.setBounds(355, 485, 150, 30);
		withdraw.addActionListener(this);
		image.add(withdraw);
		
		back = new JButton("Back");
		back.setBounds(355, 520, 150, 30);
		back.addActionListener(this);
		image.add(back);
		
		//About Frame
		setSize(900, 900);
		setLocation(300, 0);
		setVisible(true);
	}
	
	public static void main(String[] args) {
        new Withdrawl("");
	}

	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == withdraw) {
			String number = amount.getText(); // how much amount user filled
			Date date = new Date();
			if(number.equals("")) { // if user trying to deposit without filling any amount 
				JOptionPane.showMessageDialog(null, "Please enter the amount you want to withdraw");
			}else {
				try {
				Conn conn = new Conn();
				String query = "insert into bank values('"+pinnumber+"', '"+date+"', 'Withdrawl', '"+number+"')";
			    conn.s.executeUpdate(query);
			    JOptionPane.showMessageDialog(null, "Rs "+number+" Withdraw Successfully");
				setVisible(false);
				new Transaction(pinnumber).setVisible(true);
				} catch(Exception e) {
					System.out.println(e);
				}
			}
		}else if(ae.getSource() == back) {
			setVisible(false);
			new Transaction(pinnumber).setVisible(true);
		}
	}
}