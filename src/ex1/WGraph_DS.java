package ex1;

import java.io.Serializable;
import java.util.*;



public class WGraph_DS implements weighted_graph, Serializable {
	

	
	private static final long serialVersionUID = 6478537265894511825L;
	private int mc; //mode count
	private int ec; //edge count
	
	private HashMap<Integer, node_info> graph; // { n0.key = n0, n1.key = n1 } -> connect(n0, n1)
	private HashMap<Integer, HashMap<Integer, Double>> neighbors; // { n0.key = {n1.key}, n1.key = {n0.key} }
	
	class node implements node_info, Comparable<node_info>, Serializable {

		/**
		 * 
		 */
		public static final long serialVersionUID = 1933802431026155648L;
		/**
		 * 
		 */

		private int key; //PK
		private String info; //black or white 
		private double tag; // the weight of the edge
		
		public node(int key) {
			this.key = key;
			this.info = "";
			this.tag = 0.0;
		}
		
		@Override
		public int getKey() {
			return this.key;
		}

		@Override
		public String getInfo() {
			return this.info;
		}

		@Override
		public void setInfo(String s) {
			this.info = s;
			
		}

		@Override
		public double getTag() {
			return this.tag;
		}

		@Override
		public void setTag(double t) {
			this.tag = t;
			
		}

		@Override
		public int compareTo(node_info other) {
			
			Double w1 = this.getTag();
			Double w2 = other.getTag();
			
			if (w1 > w2) // o1 > o2
				return 1;
			else if (w1 < w2) // o1 < o2
				return -1;
			
			return 0; // o1 = o2
		}
		
		@Override
		public String toString() {
			
			return "[" + this.getKey() + ", " + this.getTag()
					+ ", " + this.getInfo() + "]";
			
		}
	}
	
	public WGraph_DS() {
		this.mc = 0;
		this.ec = 0;
		
		this.graph = new HashMap<>();
		this.neighbors = new HashMap<>();
	}
	
	
    /**
     * return the node_data by the node_id,
     * @param key - the node_id
     * @return the node_data by the node_id, null if none.
     */
	@Override
	public node_info getNode(int key) {
		
		if (this.graph.containsKey(key))
			return this.graph.get(key); // o(1)
		
		return null;
	}
	
    /**
     * return true iff (if and only if) there is an edge between node1 and node2
     * Note: this method should run in O(1) time.
     * @param node1
     * @param node2
     * @return
     */
	@Override
	public boolean hasEdge(int node1, int node2) {
		
		if(this.neighbors.containsKey(node1) && this.neighbors.containsKey(node2)
				&& this.graph.containsKey(node1) && this.graph.containsKey(node2)) {
			
			if (node1 != node2) {
			
				if (getNode(node1) != null && getNode(node2) != null)
					return this.neighbors.get(node1).containsKey(node2) 
							&& this.neighbors.get(node2).containsKey(node1);
			}	
		}
		
		return false;
	}

    /**
     * return the weight if the edge (node1, node2) exist. In case
     * there is no such edge - should return -1
     * Note: this method should run in O(1) time.
     * @param node1
     * @param node2
     * @return
     */
	@Override
	public double getEdge(int node1, int node2) {
		
		if (this.neighbors.containsKey(node1) && this.neighbors.containsKey(node2)) {
			
			if (hasEdge(node1, node2)) {
				
				this.mc++;
				
				return this.neighbors.get(node1).get(node2); //Double weight n2 -> n1
				
			}
			
		}
		
		return -1;
	}

    /**
     * add a new node to the graph with the given key.
     * Note: this method should run in O(1) time.
     * Note2: if there is already a node with such a key -> no action should be performed.
     * @param key
     */
	@Override
	public void addNode(int key) {
		
		if (!this.graph.containsKey(key) && !this.neighbors.containsKey(key)) {
			
			this.graph.put(key, new node(key)); //add to vertex list
			this.neighbors.put(key, new HashMap<>()); //create adj list to vertex
			
			this.mc++;
		}
		
	}

    /**
     * Connect an edge between node1 and node2, with an edge with weight >=0.
     * Note: this method should run in O(1) time.
     * Note2: if the edge node1-node2 already exists - the method simply updates the weight of the edge.
     */
	@Override
	public void connect(int node1, int node2, double w) {
		if (w >= 0) {
			
			if (node1 != node2) {
				
				if (this.graph.containsKey(node1) && this.graph.containsKey(node2)
						&& this.neighbors.containsKey(node1) && this.neighbors.containsKey(node2)) {
					
					if (this.graph.get(node1) != null && this.graph.get(node2) != null) {
					
						if(!hasEdge(node1, node2) && !hasEdge(node2, node1)) { //no edge between n1 -> n2
							
							this.neighbors.get(node1).put(node2, w);
							this.neighbors.get(node2).put(node1, w);
							
							this.mc++;
							this.ec++;
							
						}
						else { //there is an edge between n1 and n2 already 

							this.neighbors.get(node1).put(node2, w);
							this.neighbors.get(node2).put(node1, w);
							
							this.mc++;
						}
					}
					
				}
				
			}
			
		}
	}

    /**
     * This method return a pointer (shallow copy) for a
     * Collection representing all the nodes in the graph.
     * Note: this method should run in O(1) tim
     * @return Collection<node_data>
     */
	@Override
	public Collection<node_info> getV() {
		return this.graph.values();
	}

    /**
    *
    * This method returns a Collection containing all the
    * nodes connected to node_id
    * Note: this method can run in O(k) time, k - being the degree of node_id.
    * @return Collection<node_info>
    */
	@Override
	public Collection<node_info> getV(int node_id) {
//		return this.neighbors.get(node_id); //list<integer>
		List<node_info> collection = new ArrayList<>();
		
		if (this.graph.containsKey(node_id) && this.neighbors.containsKey(node_id)) {
			
			for (Map.Entry<Integer, Double> entry : this.neighbors.get(node_id).entrySet()) { // map { k0 = v0, k1 = v1 }
				collection.add(getNode(entry.getKey()));
			}
			
		}
		
		return collection;
	}

    /**
     * Delete the node (with the given ID) from the graph -
     * and removes all edges which starts or ends at this node.
     * This method should run in O(n), |V|=n, as all the edges should be removed.
     * @return the data of the removed node (null if none).
     * @param key
     */
	@Override
	public node_info removeNode(int key) {
		
		if (this.graph.containsKey(key) && this.neighbors.containsKey(key)) {
			
			for (node_info neighbor : getV(key)) { //worst case o(|v|)
				
				removeEdge(key, neighbor.getKey());

			}
			
			this.neighbors.remove(key); // possible clear() needed
			this.mc++;
			return this.graph.remove(key);
			
		}
		
		return null;
	}

    /**
     * Delete the edge from the graph,
     * Note: this method should run in O(1) time.
     * @param node1
     * @param node2
     */
	@Override
	public void removeEdge(int node1, int node2) {
		if (node1 != node2) {
			
			if (this.graph.containsKey(node1) && this.graph.containsKey(node2)
					&& this.neighbors.containsKey(node1) && this.neighbors.containsKey(node2)) {
				
				if (this.graph.get(node1) != null && this.graph.get(node2) != null) {
					
					if (hasEdge(node1, node2) && hasEdge(node2, node1)) {
						
						this.neighbors.get(node1).remove(node2);
						this.neighbors.get(node2).remove(node1);
						
						this.mc++;
						this.ec--;
					}
					
				}
			}
		}
	}

    /** return the number of vertices (nodes) in the graph.
     * Note: this method should run in O(1) time.
     * @return
     */
	@Override
	public int nodeSize() {
		return getV().size();
	}

    /**
     * return the number of edges (undirectional graph).
     * Note: this method should run in O(1) time.
     * @return
     */
	@Override
	public int edgeSize() {
		
		return this.ec;
	}

    /**
     * return the Mode Count - for testing changes in the graph.
     * Any change in the inner state of the graph should cause an increment in the ModeCount
     * @return
     */
	@Override
	public int getMC() {
		return this.mc;
	}

	@Override
	public String toString() {
		
		String res = "[";
		
		for (node_info node : getV()) {
			res += node.toString() + ",";
		}
		
		res += "]";
		
		return res;
		
	}
	public boolean equals(WGraph_DS g1)
	{
		if( this.edgeSize() != g1.edgeSize() || g1.nodeSize() != this.nodeSize()) return false;
		
		for (node_info nodes : this.getV()) {
			for (node_info nodes1 : g1.getV()) {
				if(nodes.getKey() == nodes1.getKey())
			{
				//check if all the neighbors are exist in the second node 
				for (node_info nodekey : this.getV(nodes.getKey())) {
					
					if(!this.neighbors.get(nodekey).containsKey(nodes1) || !g1.neighbors.get(nodes1).containsKey(nodekey))return false;
				}
		
				}
				
			}
			
		}
	return true;
	}

}
