package app;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

import testProject.Node;

public class Shop implements Serializable {

	static String[] s = {"And Gates: ", "Or Gates: ","Resistors: ","Batterys: "};
	static int[] itemCount = {0,0,0,0};//Array with amount of items
	static int orCount=0;
	static int andCount=0;
	static int resistorCount =0;
	static int batteryCount =0;
	static int count;

	/**
	 * Add Items to the shop
	 * @param r The Node added to the grid
	 */
	public static void addToShop(Node a){		
		switch(a.getType()){
		case "AndGate": 
			andCount++;
			setShop(andCount, orCount,resistorCount,batteryCount);
			break;
		case "OrGate": 	orCount++;
			setShop(andCount, orCount,resistorCount, batteryCount);
			break;
		case "Resistor":resistorCount++;
			setShop(andCount, orCount,resistorCount, batteryCount);
			break;
		case "Battery": batteryCount++;
			setShop(andCount, orCount,resistorCount, batteryCount);
			break;
			
		
		}
	}
	/**
	 * Remove the node that got removed from the shop
	 * @param The node which got removed
	 */
	public static void removeFromShop(Node a){
		
		switch(a.getType()){
		case "AndGate": 
			andCount--;
			setShop(andCount, orCount,resistorCount,batteryCount);
			break;
		case "OrGate": 	
			orCount--;
			setShop(andCount, orCount,resistorCount, batteryCount);
			break;
		case "Resistor":
			resistorCount--;
			setShop(andCount, orCount,resistorCount, batteryCount);
			break;
		case "Battery": 
			batteryCount--;
			setShop(andCount, orCount,resistorCount, batteryCount);
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
	public static void setShop(int and, int or, int resistor, int battery){
		//if(and==0 && or==0 ){
			andCount=and;
			orCount=or;
			resistorCount=resistor;
			batteryCount=battery;
		//}
		for(int i=0;i<s.length;i++){
			switch(i){
				case 0: 
					s[0]="And Gate: " + and;
					
				case 1:	
					s[1]="Or Gates: " + or;
				
				case 2: 
					s[2]="Resistors: " + resistor;
						
				case 3: 
					s[3]="Batterys: " + battery;
			}
		}
	}
	public static void clearShop(){
		 setShop(0,0,0,0);
	}
	public static void updateShop(ArrayList<Node> nodes) {
		clearShop(); // kanske såhär??
		for(Node n : nodes){
			switch(n.getType()){
				case("AndGate"):
					andCount++;
					break;
				case("OrGate"):
					orCount++;
					break;
				case("Resistor"):
					resistorCount++;
					break;
				case("Battery"):
					batteryCount++;
					break;
				
			}
		}
		setShop(andCount,orCount,resistorCount,batteryCount);
		
	}
}
