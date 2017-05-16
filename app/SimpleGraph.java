package app;


import java.awt.*;

import testProject.*;

public class SimpleGraph extends Graph {
	public Node[] getNodePrototypes() {
		Node[] nodeTypes = { new AndGate(Color.GREEN), new OrGate(Color.BLUE) };
		return nodeTypes;
	}
}
