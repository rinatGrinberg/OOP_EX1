package ex1.tests;
import java.util.Collection;
import static org.junit.Assert.*;

import org.junit.Test;

import ex1.src.WGraph_DS;
import ex1.src.node_info;

public class WGraph_DSTest {

	

	@Test
	public void testGetNode() {

        WGraph_DS graph= new WGraph_DS();
        graph.addNode(2);
        assertEquals(graph.getNode(100), null);
	}
	@Test
	public void testHasEdge() {
        WGraph_DS graph = new WGraph_DS();
        graph.addNode(2);
        graph.addNode(3);
        graph.connect(2,3,6);
        assertEquals(graph.hasEdge(2,3), true);
	}

	
	@Test
	public void testGetEdge() {
        WGraph_DS graph = new WGraph_DS();
        graph.addNode(2);
        graph.addNode(3);
        graph.connect(2,3,7);
        graph.removeEdge(2,3);
        assertEquals((graph.getEdge(2,3)),-1,0.000001);
       
	}

	@Test
	public void testAddNode() {
		    WGraph_DS graph = new WGraph_DS();
	        assertEquals(graph.getNode(2),null);
	}

	@Test
	public void testConnect() {
	       WGraph_DS graph = new WGraph_DS();
	        graph.addNode(2);
	        graph.addNode(3);
	        graph.connect(2,3,2);
	        graph.connect(2,3,5);
	        assertEquals(graph.getEdge(2,3),5,0.000001);
	}

	@Test
	public void testGetV() {
		 WGraph_DS graph = new WGraph_DS();
	        graph.addNode(2);
	        graph.addNode(3);

	        Collection<node_info> ans  = graph.getV();
	        assertEquals(ans.size(),2);
	}

	@Test
	public void testGetVInt() {
		 WGraph_DS graph = new WGraph_DS();
		 	graph.addNode(0);
	        graph.addNode(1);
		 	graph.addNode(2);
	        graph.addNode(3);
	        graph.connect(2, 0, 7);
	        graph.connect(2, 1, 5);
	        Collection<node_info> ans  = graph.getV(2);
	        assertEquals( ans.contains(graph.getNode(1)),true);
	}

	@Test
	public void testRemoveNode() {
        WGraph_DS graph = new WGraph_DS();
        graph.removeNode(2);
        assertEquals(graph.getNode(2),null);
	}

	@Test
	public void testRemoveEdge() {
        WGraph_DS graph = new WGraph_DS();
        graph.addNode(2);
        graph.addNode(3);
        graph.connect(2,3,89);
        graph.removeEdge(2,3);
        assertEquals(graph.hasEdge(2,3),false);
	}

	@Test
	public void testNodeSize() {
		  WGraph_DS graph = new WGraph_DS();
	        graph.addNode(2);
	        graph.addNode(3);
	        graph.addNode(4);
	        graph.removeNode(4);
	        assertEquals(graph.nodeSize(),2);
	}

	@Test
	public void testEdgeSize() {
		 WGraph_DS graph = new WGraph_DS();
         graph.addNode(2);
         graph.addNode(3);
         graph.connect(2,3,5);
         graph.connect(2,3,5);
         assertEquals(graph.edgeSize(),1);
	}

	@Test
	public void testGetMC() {
		  WGraph_DS graph = new WGraph_DS();
	        graph.addNode(2);
	        graph.addNode(3);
	        graph.connect(2,3,5);
	        graph.connect(2,3,3);
	        graph.removeEdge(2,3);
	        assertEquals(graph.getMC(),5);
	}

	@Test
	public void testEqualsWGraph_DS() {
		  WGraph_DS graph = new WGraph_DS();
	        graph.addNode(2);
	        graph.addNode(3);
	        graph.connect(2,3,5);
	        graph.connect(2,3,3);
	        graph.removeEdge(2,3);
	        WGraph_DS g1 = new WGraph_DS();
	        g1.addNode(1);
	        g1.addNode(2);
	        g1.addNode(3);
	        g1.connect(2,1,5);
	        g1.removeEdge(2,1);
	        assertEquals(graph.equals(g1), false);
	}

}
