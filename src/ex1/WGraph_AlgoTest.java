package ex1;

import static org.junit.Assert.*;


import java.util.*;

import org.junit.Test;


public class WGraph_AlgoTest {

	@Test
	public void testInit() {
		WGraph_Algo graph_ =new WGraph_Algo();
    	WGraph_DS g= new WGraph_DS();
    	graph_.init(g);
    	graph_.getGraph().addNode(2);
    	assertEquals(2, graph_.getGraph().getNode(2).getKey());
	}

	@Test
	public void testGetGraph() {
		WGraph_Algo graph_ =new WGraph_Algo();
    	WGraph_DS g= new WGraph_DS();
    	graph_.init(g);
    	graph_.getGraph().addNode(1);
    	graph_.getGraph().addNode(2);
    	graph_.getGraph().connect(1, 2, 8);
    	assertEquals(8 ,graph_.getGraph().getEdge(1, 2),00000.1);	
	}

	@Test
	public void testCopy() {
		WGraph_Algo graph_ =new WGraph_Algo();
    	WGraph_DS g0=new WGraph_DS();
    	graph_.init(g0);
    	g0.addNode(1);
    	g0.addNode(2);
    	g0.addNode(3);
    	WGraph_DS g1=new WGraph_DS();
    	g1= (WGraph_DS) graph_.copy();
    	assertEquals(2, g1.getNode(2).getKey());	
	}

	@Test
	public void testDjikstra() {
		WGraph_Algo graph_ =new WGraph_Algo();
    	WGraph_DS g= new WGraph_DS();
    	graph_.init(g);
    	graph_.getGraph().addNode(1);
    	graph_.getGraph().addNode(2);
    	graph_.getGraph().addNode(3);
    	graph_.getGraph().addNode(4);
    	graph_.getGraph().addNode(5);
    	graph_.getGraph().addNode(6);
    	graph_.getGraph().connect(1, 2, 8);
    	graph_.getGraph().connect(1, 0, 6);
    	graph_.getGraph().connect(1, 3, 5);
    	graph_.getGraph().connect(2, 3, 2);
    	graph_.getGraph().connect(3, 5, 7);
    	graph_.getGraph().connect(4, 2, 12);
    	graph_.getGraph().connect(5, 6, 1);
    	graph_.getGraph().connect(1, 6, 4);
    	graph_.djikstra(1);
    	assertEquals(1,1);	
	}

	@Test
	public void testIsConnected() {
		WGraph_Algo graph_ =new WGraph_Algo();
    	WGraph_DS g0=new WGraph_DS();
    	graph_.init(g0);
    	g0.addNode(0);
    	g0.addNode(1);
    	g0.addNode(2);
    	g0.addNode(3);
    	g0.addNode(4);
    	g0.connect(3, 4, 6);
    	g0.connect(2, 3, 6);

    	assertEquals(graph_.isConnected(), false);	
	}

	@Test
	public void testShortestPathDist() {
	
    	WGraph_Algo graph_ =new WGraph_Algo();
    	WGraph_DS g= new WGraph_DS();
    	graph_.init(g);
    	graph_.getGraph().addNode(0);
    	graph_.getGraph().addNode(1);
    	graph_.getGraph().addNode(2);
    	graph_.getGraph().addNode(3);
    	graph_.getGraph().addNode(4);
    	graph_.getGraph().addNode(5);
    	graph_.getGraph().addNode(6);
    	graph_.getGraph().connect(1, 2, 8);
    	graph_.getGraph().connect(1, 0, 6);
    	graph_.getGraph().connect(1, 3, 90);
    	graph_.getGraph().connect(2, 3, 2);
    	graph_.getGraph().connect(1, 4, 23);
    	graph_.getGraph().connect(3, 5, 7);
    	graph_.getGraph().connect(4, 5, 10);
    	graph_.getGraph().connect(2, 4, 3);
    	graph_.getGraph().connect(5, 6, 9);
    	graph_.getGraph().connect(1, 6, 3);
  
    	
    	assertEquals(graph_.shortestPathDist(1, 5) ,12 ,0.01);
	}

	@Test
	public void testShortestPath() {
		WGraph_Algo graph_ =new WGraph_Algo();
    	WGraph_DS g= new WGraph_DS();
    	graph_.init(g);
    	graph_.getGraph().addNode(1);
    	graph_.getGraph().addNode(2);
    	graph_.getGraph().addNode(3);
    	graph_.getGraph().addNode(4);
    	graph_.getGraph().addNode(5);
    	graph_.getGraph().addNode(6);
    	graph_.getGraph().connect(1, 2, 8);
    	graph_.getGraph().connect(1, 0, 6);
    	graph_.getGraph().connect(1, 3, 5);
    	graph_.getGraph().connect(2, 3, 2);
    	graph_.getGraph().connect(3, 5, 7);
    	graph_.getGraph().connect(4, 2, 12);
    	graph_.getGraph().connect(5, 6, 1);
    	graph_.getGraph().connect(1, 6, 4);
    	List<node_info> sp = new ArrayList<>();
    	sp = graph_.shortestPath(1, 6);
    	String dis_of_node="";
    	for (node_info node : sp) {
    		dis_of_node=dis_of_node+ node.getKey() +"->";
		}
    	
    	assertEquals(dis_of_node, "1->6->");
	}

	@Test
	public void testSave() {
		WGraph_Algo graph_ =new WGraph_Algo();
    	WGraph_DS g= new WGraph_DS();
    	graph_.init(g);
    	graph_.getGraph().addNode(1);
    	graph_.getGraph().addNode(2);
    	graph_.getGraph().addNode(3);
    	graph_.getGraph().addNode(4);
    	graph_.getGraph().addNode(5);
    	graph_.getGraph().addNode(6);
	
		//String fileNameString fileNameString=""
		assertEquals(graph_.save(""), false);
		
	}

	@Test
	public void testLoad() {
		WGraph_Algo graph_ =new WGraph_Algo();
    	WGraph_DS g= new WGraph_DS();
    	graph_.init(g);
    	graph_.getGraph().addNode(1);
    	graph_.getGraph().addNode(2);
    	graph_.getGraph().addNode(3);
    	graph_.getGraph().addNode(4);
    	graph_.getGraph().addNode(5);
    	graph_.getGraph().addNode(6);
	
		//String fileNameString fileNameString=""
		assertEquals(graph_.save(""), false);
		
	}

}
