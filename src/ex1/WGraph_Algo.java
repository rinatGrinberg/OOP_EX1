package ex1;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.*;

public class WGraph_Algo implements weighted_graph_algorithms {
	
	private weighted_graph graph;
	
	private HashMap<Integer, Integer> prev; // { n0.key = null, n1.key = n0.key, n2.key = n0.key }
	private HashMap<Integer, Double> dist; // { n0.key = n0 distance from source }
	
	/**
     * Init the graph on which this set of algorithms operates on.
     * @param g
     */
	@Override
	public void init(weighted_graph g) {
		this.graph = g;
		this.prev = new HashMap<>();
		this.dist = new HashMap<>();
	}

    /**
     * Return the underlying graph of which this class works.
     * @return
     */
	@Override
	public weighted_graph getGraph() { //shallow copy
		return this.graph;
	}

    /**
     * Compute a deep copy of this weighted graph.
     * @return
     */
	@Override
	public weighted_graph copy() {

		weighted_graph new_graph = new WGraph_DS();
		
		for (node_info vertex : this.graph.getV()) {
			new_graph.addNode(vertex.getKey()); //new copy of vertex (node)
			
			new_graph.getNode(vertex.getKey()).setInfo(vertex.getInfo());
			new_graph.getNode(vertex.getKey()).setTag(vertex.getTag());
		}
		
		for (node_info v : this.graph.getV()) { //for each node in graph
			for (node_info neighbor : this.graph.getV(v.getKey())) { //for each neighbor of node
				
				new_graph.connect(v.getKey(), neighbor.getKey(), this.graph.getEdge(v.getKey(), neighbor.getKey()));
				
			}
			
		}
		
		return new_graph;
	}
	
	public void djikstra(int source) { 
		
		PriorityQueue<node_info> q = new PriorityQueue<>();
		
		for (node_info node : this.graph.getV()) {
			
			if(node.getKey() == source) {
				this.dist.put(node.getKey(), 0.0);
				this.prev.put(node.getKey(), node.getKey());
				node.setTag(0.0); 
			}
			else {
				
				this.dist.put(node.getKey(), Double.MAX_VALUE);
				this.prev.put(node.getKey(), null);
				node.setTag(Double.MAX_VALUE); 
				
			}
			
			q.add(node);
		}
		
		
//		q.add(this.graph.getNode(source));
		
		while (!q.isEmpty()) {
			
			node_info u = q.poll();
			
			for (node_info neighbor : this.graph.getV(u.getKey())) {
				
				double alt = this.dist.get(u.getKey()) + this.graph.getEdge(u.getKey(), neighbor.getKey());
				
				if (alt < this.dist.get(neighbor.getKey())) {
					
					this.dist.put(neighbor.getKey(), alt);
					this.prev.put(neighbor.getKey(), u.getKey());
					
					neighbor.setTag(alt);
					
					//q.decrease_priority
					
					q.remove(neighbor);
					q.add(neighbor);
				}
				
			}

		}
		
	}
	
    /**
     * Returns true if and only if (iff) there is a valid path from EVREY node to each
     * other node. NOTE: assume ubdirectional graph.
     * @return
     */
	@Override
	public boolean isConnected() {
		
		if (this.graph.nodeSize() == 0 || this.graph.nodeSize() == 1)
			return true;
		
		if (this.graph.edgeSize() == 0 && this.graph.nodeSize() > 0)
			return false;
		
		for (node_info node_info : this.graph.getV()) {
			djikstra(node_info.getKey());
			break;
		}
		
		for (Double dist : this.dist.values()) {
			if (dist == Double.MAX_VALUE)
				return false;
		}
		
		return true;
	}

    /**
     * returns the length of the shortest path between src to dest
     * Note: if no such path --> returns -1
     * @param src - start node
     * @param dest - end (target) node
     * @return
     */
	@Override
	public double shortestPathDist(int src, int dest) {
		djikstra(src);
		return this.dist.get(dest);
	}

    /**
     * returns the the shortest path between src to dest - as an ordered List of nodes:
     * src--> n1-->n2-->...dest
     * see: https://en.wikipedia.org/wiki/Shortest_path_problem
     * Note if no such path --> returns null;
     * @param src - start node
     * @param dest - end (target) node
     * @return
     */
	@Override
	public List<node_info> shortestPath(int src, int dest) {
		
		djikstra(src);
		
		List<node_info> res = new ArrayList<>();
		
		// res = [dest,      ]
		
		res.add(this.graph.getNode(dest));
		
		int next = dest;
		
		while(true) {
			
			res.add(this.graph.getNode(this.prev.get(next)));
			next = this.prev.get(next);
			
			 if (next == src)
				 break;
			
		}
		
		List<node_info> back_res = new ArrayList<>();
		
		for (int i = res.size()-1; i >= 0; i--) {
			back_res.add(res.get(i));
		}
		
		return back_res;
	}

    /**
     * Saves this weighted (undirected) graph to the given
     * file name
     * @param file - the file name (may include a relative path).
     * @return true - iff the file was successfully saved
     */
	@Override
	public boolean save(String file) {
		// TODO Auto-generated method stub
		
        try {
        	 
            FileOutputStream fileOut = new FileOutputStream(file);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            
            objectOut.writeObject(copy());
            
            objectOut.close();
            fileOut.close();
            System.out.println("The Object  was succesfully written to a file");
            return true;
 
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }

	}

	/**
     * This method load a graph to this graph algorithm.
     * if the file was successfully loaded - the underlying graph
     * of this class will be changed (to the loaded one), in case the
     * graph was not loaded the original graph should remain "as is".
     * @param file - file name
     * @return true - iff the graph was successfully loaded.
     */
	@Override
	public boolean load(String file) {
        try {
        	 
            FileInputStream fileIn = new FileInputStream(file);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
 
            weighted_graph gr = (weighted_graph) objectIn.readObject();
            
            System.out.print("The Object has been read from the file successfully");
            
            objectIn.close();
            fileIn.close();
            return true;
 
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
	}
	
}
