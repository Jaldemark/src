package app;

import javax.swing.*;

import testProject.*;

public class SimpleGraphEditor {
	public static void main(String[] args) {
		JFrame frame = new GraphFrame(new SimpleGraph());
		frame.setVisible(true);
	}
}
