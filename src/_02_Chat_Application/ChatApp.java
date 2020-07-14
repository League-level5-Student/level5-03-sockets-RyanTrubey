package _02_Chat_Application;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/*
 * Using the Click_Chat example, write an application that allows a server computer to chat with a client computer.
 */

public class ChatApp extends JFrame{
	
	Server server = new Server(8080, this);
	Client client;
	
	JTextField textField = new JTextField("Enter message here");
	JButton send = new JButton("Send Message");
	JPanel panel = new JPanel();
	String messages = "";
	
	public static void main(String[] args) {
		new ChatApp();
	}
	
	public ChatApp() {
		int response = JOptionPane.showConfirmDialog(null, "Would you like to host a connection?", "Chat App", JOptionPane.YES_NO_OPTION);
		if(response == JOptionPane.YES_OPTION){
			setTitle("SERVER");
			JOptionPane.showMessageDialog(null, "Server started at: " + server.getIPAddress() + "\nPort: " + server.getPort());
			send.addActionListener((e)->{
				server.sendMessage(textField.getText());
				textField.setText("");
			});
			panel.add(send);
			panel.add(textField);
			add(panel);
			pack();
			setVisible(true);
			setSize(400, 300);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			server.start();
			
		}else{
			setTitle("CLIENT");
			String ipStr = JOptionPane.showInputDialog("Enter the IP Address");
			String prtStr = JOptionPane.showInputDialog("Enter the port number");
			int port = Integer.parseInt(prtStr);
			client = new Client(ipStr, port, this);
			send.addActionListener((e)->{
				client.sendMessage(textField.getText());
				textField.setText("");
			});
			panel.add(send);
			panel.add(textField);
			add(panel);
			pack();
			setVisible(true);
			setSize(400, 300);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			client.start();
		}
	}
	
	public void setMessage(String s) {
		textField.setText(s);
	}
}
