package app;

import javax.swing.JTextField;

import testProject.*;

public class TextField extends JTextField{

	public JTextField shopping;
	//String s;
	public TextField(int size, String s) {
		shopping = new JTextField(size);
		shopping.setText("s");
	}
	
	public void setShop(String theItem){
		shopping.setText(/*getShop() +", "+*/ theItem);
	}
	public String getShop(){
		return shopping.getText();
	}
}
