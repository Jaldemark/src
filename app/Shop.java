package app;

import java.util.ArrayList;
import java.util.Arrays;

import testProject.Node;

public class Shop {

	static String[] s = {"And Gates: ", "Or Gates: "};
	static int[] itemCount = {0,0};//Array with amount of items
	static int orCount=0;
	static int currentSize=1;	//How many different items there are
	static int andCount=0;
	static int count;

	/**
	 * Add Items to the shop
	 * @param r The Node added to the grid
	 */
	public static void addToShop(Node a){		
			switch(a.getType()){
				case "AndGate":	andCount++; 
								setShop(andCount, orCount);
					break;
				case "OrGate": 	orCount++;
								setShop(andCount,orCount);
					break;
			}
	}
	/**
	 * Remove the node that got removed from the shop
	 * @param The node which got removed
	 */
	public static void removeFromShop(Node a){
		
		switch(a.getType()){
		case "AndGate": andCount--;
						setShop(andCount, orCount);
						break;
		case "OrGate": 	orCount--;
						setShop(andCount, orCount);
						break;
		}
	}
	/**
	 * 
	 * @return all items in our shop
	 */
	public static String getShop(){
		String all ="";
		count =0;
		if(s.length!=0){
			while(count!=s.length){
				all = all + s[count]+System.lineSeparator();
				count++;
			}
		}
		return all;
	}
	public static void setShop(int and, int or){
		if(and==0 && or==0){
			andCount=and;
			orCount=or;
		}
		for(int i=0;i<s.length;i++){
			switch(i){
				case 0: 
						s[0]="And Gate: " + and;
					
				case 1:	
						s[1]="Or Gates: " + or;
			}
		}
	}
	public static void clearShop(){
		 setShop(0,0);
	}
	public static void updateShop(ArrayList<Node> nodes) {
		
		for(Node n : nodes){
			switch(n.getType()){
				case("AndGate"):
					andCount++;
					break;
				case("OrGate"):
					orCount++;
					break;
			}
		}
		setShop(andCount,orCount);
		
	}
}
