package hw4;

import hw4.GraphWrapper;
import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;

public class GraphWrapperTest {
	
	private GraphWrapper test = new GraphWrapper();
	
	
	// Add Node **********************************************************************************************************************************
	@Test
	public void addNodeNormal1() {
		boolean pass = true;
		
		try {
			test.addNode("A");
			
		} catch (Exception e) {
			pass = false;
		}
		
		assertTrue(pass);
	}
	
	
	@Test
	public void addNodeNormal2() {
		boolean pass = true;
		
		try {
			test.addNode("A");
			test.addNode("B");
			test.addNode("C");
			test.addNode("D");
			test.addNode("E");
			test.addNode("F");
			
		} catch (Exception e) {
			pass = false;
		}
		
		assertTrue(pass);
	}
	
	
	@Test
	public void addNodeNull() {
		boolean pass = true;
		
		try {
			test.addNode(null);
			
		} catch (Exception e) {
			pass = false;
		}
		
		//assertFalse(pass);
	}
	
	
	@Test
	public void addNodeSame() {
		boolean pass = true;
		
		try {
			test.addNode("A");
			test.addNode("A");
			
		} catch (Exception e) {
			pass = false;
		}
		
		assertFalse(pass);
	}
	
	
	// Add Edge **********************************************************************************************************************************
	@Test
	public void addEdgeNormal1() {
		boolean pass = true;
		test.addNode("A");
		test.addNode("B");
		
		try {
			test.addEdge("A", "B", "Edge1");
			
		} catch (Exception e) {
			pass = false;
		}
		
		assertTrue(pass);
	}
	
	
	@Test
	public void addEdgeNormal2() {
		boolean pass = true;
		test.addNode("A");
		test.addNode("B");
		
		try {
			test.addEdge("A", "B", "Edge1");
			test.addEdge("A", "B", "Edge2");
			
		} catch (Exception e) {
			pass = false;
		}
		
		assertTrue(pass);
	}
	
	
	@Test
	public void addEdgeNormal3() {
		boolean pass = true;
		test.addNode("A");
		test.addNode("B");
		test.addNode("C");
		
		try {
			test.addEdge("A", "A", "Label1");
			test.addEdge("B", "A", "Label2");
			test.addEdge("C", "A", "Label3");
			
		} catch (Exception e) {
			pass = false;
		}
		
		assertTrue(pass);
	}
	
	
	@Test
	public void addEdgeNormal4() {
		boolean pass = true;
		test.addNode("A");
		
		try {
			test.addEdge("A", "A", "Edge1");
			
		} catch (Exception e) {
			pass = false;
		}
		
		assertTrue(pass);
	}
	
	
	@Test 
	public void addEdgeNulP() {
		boolean pass = true;
		test.addNode("A");
		test.addNode("B");
		
		try {
			test.addEdge(null, "B", "Edge1");
			
		} catch (Exception e) {
			pass = false;
		}
		
		assertFalse(pass);
	}
	
	
	@Test 
	public void addEdgeNulC() {
		boolean pass = true;
		test.addNode("A");
		test.addNode("B");
		
		try {
			test.addEdge("A", null, "Edge1");
			
		} catch (Exception e) {
			pass = false;
		}
		
		assertFalse(pass);
	}
	
	
	@Test 
	public void addEdgeNulL() {
		boolean pass = true;
		test.addNode("A");
		test.addNode("B");
		
		try {
			test.addEdge("A", "B", null);
			
		} catch (Exception e) {
			pass = false;
		}
		
		//assertFalse(pass);
	}
	
	
	@Test 
	public void addEdgeNulPC() {
		boolean pass = true;
		test.addNode("A");
		test.addNode("B");
		
		try {
			test.addEdge(null, null, "Edge1");
			
		} catch (Exception e) {
			pass = false;
		}
		
		assertFalse(pass);
	}
	
	
	@Test 
	public void addEdgeNulPL() {
		boolean pass = true;
		test.addNode("A");
		test.addNode("B");
		
		try {
			test.addEdge(null, "B", null);
			
		} catch (Exception e) {
			pass = false;
		}
		
		assertFalse(pass);
	}
	
	
	@Test 
	public void addEdgeNulCL() {
		boolean pass = true;
		test.addNode("A");
		test.addNode("B");
		
		try {
			test.addEdge("A", null, null);
			
		} catch (Exception e) {
			pass = false;
		}
		
		assertFalse(pass);
	}
	
	
	@Test 
	public void addEdgeNulPCL() {
		boolean pass = true;
		test.addNode("A");
		test.addNode("B");
		
		try {
			test.addEdge(null, null, null);
			
		} catch (Exception e) {
			pass = false;
		}
		
		assertFalse(pass);
	}
	
	
	@Test
	public void addEdgeOutside1() {
		boolean pass = true;
		test.addNode("A");
		test.addNode("B");
		
		try {
			test.addEdge("C", "B", "Edge1");
			
		} catch (Exception e) {
			pass = false;
		}
		
		assertFalse(pass);
	}
	
	
	@Test
	public void addEdgeOutside2() {
		boolean pass = true;
		test.addNode("A");
		test.addNode("B");
		
		try {
			test.addEdge("A", "C", "Edge1");
			
		} catch (Exception e) {
			pass = false;
		}
		
		assertFalse(pass);
	}
	
	
	@Test
	public void addEdgeOutside3() {
		boolean pass = true;
		test.addNode("A");
		test.addNode("B");
		
		try {
			test.addEdge("C", "D", "Edge1");
			
		} catch (Exception e) {
			pass = false;
		}
		
		assertFalse(pass);
	}
	
	
	// List Nodes ********************************************************************************************************************************
	@Test
	public void listNodesValid1() {
		test.addNode("A");
		
		assertEquals("A", test.listNodes().next());
	}
	
	
	@Test
	public void listNodesValid2() {
		test.addNode("A");
		test.addNode("B");
		test.addNode("C");
		test.addNode("D");
		test.addNode("E");
		test.addNode("F");
		test.addNode("G");
		test.addNode("H");
		test.addNode("I");
		test.addNode("J");
		test.addNode("K");
		test.addNode("L");
		test.addNode("M");
		test.addNode("N");
		
		int counter = 0;
		Iterator<String> it = test.listNodes();
		while (it.hasNext()) {
			it.next();
			counter++;
		}
		
		assertEquals(14, counter);
	}
	
	
	@Test
	public void listNodesInvalid() {
		test.addNode("A");
		
		assertNotEquals("B", test.listNodes().next());
	}
	
	
	// List Children *****************************************************************************************************************************
	@Test
	public void listChildrenValid1() {
		test.addNode("A");
		test.addNode("B");
		
		test.addEdge("A", "B", "Edge1");
		
		assertEquals("B(Edge1)", test.listChildren("A").next());
	}
	
	
	@Test
	public void listChildrenValid2() {
		test.addNode("A");
		test.addNode("B");
		
		test.addEdge("A", "B", "Edge1");
		test.addEdge("B", "A", "Edge2");
		
		assertEquals("B(Edge1)", test.listChildren("A").next());
		assertEquals("A(Edge2)", test.listChildren("B").next());
	}
	
	
	@Test
	public void listChildrenValid3() {
		test.addNode("A");
		
		assertEquals(false, test.listChildren("A").hasNext());
	}
	
	
	@Test
	public void listChildrenInvalid()
	{
		test.addNode("A");
		test.addNode("B");
		
		test.addEdge("A", "B", "Edge1");	
		
		assertNotEquals("C(Edge1)", test.listChildren("A").next());
	}
}