
import java.util.*;


public class Node {
	int key;
	
	int endNode;
	
	
	int weight=1;

	public Node(int key,int endNode, int weight) {
		this.key=key;
		this.endNode=endNode;
		this.weight=weight;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + endNode;
		result = prime * result + key;
		result = prime * result + weight;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Node other = (Node) obj;
		if (endNode != other.endNode)
			return false;
		if (key != other.key)
			return false;
		if (weight != other.weight)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "("+key + " "+ endNode + ") " + weight;
	}

	
	
	
}
