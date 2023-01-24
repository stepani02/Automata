package dijkstra_algo;

public class main {
	public static void main(String [] args) {
		DirectedWeightedGraph g = new DirectedWeightedGraph();
		
		g.insertVertex("Manila");//0
		g.insertVertex("Quezon City");//1
		g.insertVertex("Makati");//2
		g.insertVertex("Caloocan");///3
		g.insertVertex("Pasig");//4
		g.insertVertex("Taguig");//5
		g.insertVertex("Pasay");//6
		g.insertVertex("Mandaluyong");//7
		g.insertVertex("Marikina");//8
		
		g.insertEdge("Manila","Caloocan", 11);
		g.insertEdge("Manila","Quezon City", 13);
		g.insertEdge("Manila","Pasig", 26);
		g.insertEdge("Quezon City","Pasig", 16);
		g.insertEdge("Makati","Quezon City", 17);
		g.insertEdge("Makati","Taguig", 7);
		g.insertEdge("Caloocan","Pasig", 26);
		g.insertEdge("Caloocan","Pasay", 33);
		g.insertEdge("Pasig","Taguig", 9);
		g.insertEdge("Pasig","Mandaluyong", 8);
		g.insertEdge("Taguig","Quezon City", 14);
		g.insertEdge("Pasay","Mandaluyong", 9);
		g.insertEdge("Mandaluyong","Caloocan", 28);
		g.insertEdge("Mandaluyong","Taguig", 10);
		g.insertEdge("Mandaluyong","Marikina", 14);
		g.insertEdge("Marikina","Taguig", 18);
		
		g.progStart();	
	}
}
	