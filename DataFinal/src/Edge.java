
import java.util.*;

public class Edge {
	int v,e,weight;

	public Edge(int v, int e ,int weight ) {
		this.v = v;
		this.e = e;
		this.weight=weight;
	}

	@Override
	public String toString() {
		return "Edge [v=" + v + "e=" + e + "]";
	}
	

}
