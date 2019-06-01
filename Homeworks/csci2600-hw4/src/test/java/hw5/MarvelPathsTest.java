package hw5;

import static org.junit.Assert.*;

import org.junit.Test;

public final class MarvelPathsTest{

	private static MarvelPaths test = new MarvelPaths();


	//createNewGraph *****************************************************************************************************************************
	@Test
	public void testsingle_node(){
		test.createNewGraph("data/single_node.csv");
		assertEquals(1, test.numNodes());
		assertEquals(0, test.numEdges());
		assertEquals(1, test.numBooks());
	}

	@Test
	public void testtwo_connected_nodes(){
		test.createNewGraph("data/two_connected_nodes.csv");
		assertEquals(2, test.numNodes());
		assertEquals(2, test.numEdges());
		assertEquals(1, test.numBooks());
	}

	@Test
	public void testsingle_book(){
		test.createNewGraph("data/single_book.csv");
		assertEquals(6, test.numNodes());
		assertEquals(30, test.numEdges());
		assertEquals(1, test.numBooks());
	}

	@Test
	public void testsingle_line(){
		test.createNewGraph("data/single_line.csv");
		assertEquals(7, test.numNodes());
		assertEquals(12, test.numEdges());
		assertEquals(6, test.numBooks());
	}

	@Test
	public void testtest_small(){
		test.createNewGraph("data/test_small.csv");
		assertEquals(8, test.numNodes());
		assertEquals(40, test.numEdges());
		assertEquals(2, test.numBooks());
	}

	@Test
	public void testlarge_test(){
		test.createNewGraph("data/large_test.csv");
		assertEquals(26, test.numNodes());
		assertEquals(54, test.numEdges());
		assertEquals(12, test.numBooks());
	}


	// findPath **********************************************************************************************************************************
	@Test
	public void testUnknown(){
		test.clear();
		test.addCharacter("A", "1");
		String fake1 = "X";
		String fake2 = "Y";
		String expected1 = "unknown character " + fake1 + "\n";
		String expected2 = "unknown character " + fake2 + "\n";
		
		assertEquals(expected2, test.findPath("A", fake2));
		
		assertEquals(expected1, test.findPath(fake1, "A"));
		
		assertEquals(expected1, test.findPath(fake1, fake1));
		
		assertEquals(expected1 + expected2, test.findPath(fake1, fake2));
		
	}


	@Test
	public void testReflexive(){
		test.clear();
		for(int i = 0; i < 6; i++){
			test.addCharacter("A"+i, "1"+i);
			String expected = "path from " + "A"+i + " to " + "A"+i + ":\n";
			
			assertEquals(expected, test.findPath("A"+i, "A"+i));
		}

		test.clear();
		for(int i = 0; i < 6; i++){
			test.addCharacter("A", "1"+i);
			String expected = "path from " + "A" + " to " + "A" + ":\n";
			
			assertEquals(expected, test.findPath("A", "A"));
		}

		test.clear();
		for(int i = 0; i < 6; i++){
			test.addCharacter("A"+i, "1");
			String expected = "path from " + "A"+i + " to " + "A"+i + ":\n";
			
			assertEquals(expected, test.findPath("A"+i, "A"+i));
		}
	}


	@Test
	public void testSameBookPath(){
		test.clear();
		for(int i = 0; i < 6; i++){
			test.addCharacter("A"+i, "1");
		}

		for(int i = 0; i < 6; i++){
			for(int j = 0; j < 6; j++){
				if(i == j){
					continue;
				}
				String expected = "path from " + "A"+i + " to " + "A"+j + ":\n"
									+ "A"+i + " to " + "A"+j + " via " + "1" + "\n";
				
				assertEquals(expected, test.findPath("A"+i, "A"+j));
			}
		}
	}


	@Test
	public void testLine(){
		test.clear();
		test.addCharacter("A", "1");
		test.addCharacter("B", "1");
		test.addCharacter("B", "2");
		test.addCharacter("C", "2");
		test.addCharacter("C", "3");
		test.addCharacter("D", "3");
		
		String expected = "path from " + "A" + " to " + "D" + ":\n"
						+ "A" + " to " + "B" + " via " + "1" + "\n"
						+ "B" + " to " + "C" + " via " + "2" + "\n"
						+ "C" + " to " + "D" + " via " + "3" + "\n";
		
		assertEquals(expected, test.findPath("A", "D"));
	}

	@Test
	public void testEdgeAlphabetical(){
		test.clear();
		test.addCharacter("1", "B");
		test.addCharacter("2", "B");
		test.addCharacter("1", "A");
		test.addCharacter("2", "A");
		test.addCharacter("1", "D");
		test.addCharacter("2", "D");
		test.addCharacter("1", "C");
		test.addCharacter("2", "C");
		
		String expected = "path from " + "1" + " to " + "2" + ":\n"
						+ "1" + " to " + "2" + " via A\n";
		
		assertEquals(expected, test.findPath("1", "2"));
	}

	@Test
	public void testCharacterAlphabetical(){
		test.clear();
		test.addCharacter("X", "1");
		test.addCharacter("C", "1");
		test.addCharacter("D", "1");
		test.addCharacter("A", "1");
		test.addCharacter("B", "1");
		test.addCharacter("C", "2");
		test.addCharacter("D", "2");
		test.addCharacter("A", "2");
		test.addCharacter("B", "2");
		test.addCharacter("Y", "2");

		String expected = "path from X to Y:\nX to A via " + "1"
						+ "\nA to Y via " + "2" + "\n";
		
		assertEquals(expected, test.findPath("X", "Y"));
	}

	@Test
	public void testNoPath(){
		test.clear();
		test.addCharacter("A", "1");
		test.addCharacter("B", "2");
		
		String expected = "path from " + "A" + " to " + "B" + ":\nno path found\n";
		
		assertEquals(expected, test.findPath("A", "B"));
	}


	// numNodes, numEdges and numBooks ***********************************************************************************************************
	@Test
	public void testAccessors(){
		test.clear();
		assertEquals(0, test.numNodes());
		assertEquals(0, test.numEdges());
		assertEquals(0, test.numBooks());
		
		test.addCharacter("A", "1");
		
		assertEquals(1, test.numNodes());
		assertEquals(0, test.numEdges());
		assertEquals(1, test.numBooks());
		
		test.clear();
		test.addCharacter("A", "1");
		test.addCharacter("B", "1");
		
		assertEquals(2, test.numNodes());
		assertEquals(2, test.numEdges());
		assertEquals(1, test.numBooks());
	}

	
	
	// clear *************************************************************************************************************************************
	@Test
	public void testClear() {
		test.clear();
		assertEquals(0, test.numNodes());
		assertEquals(0, test.numEdges());
		assertEquals(0, test.numBooks());

		test.createNewGraph("data/large_test.csv");
		test.clear();
		assertEquals(0, test.numNodes());
		assertEquals(0, test.numEdges());
		assertEquals(0, test.numBooks());
	}


	
	// addCharacter ******************************************************************************************************************************
	@Test
	public void testAddCharacter(){
		test.clear();
		for(int i = 0; i < 6; i++){
			test.addCharacter("A"+i, "1"+i);
			assertEquals(i+1, test.numNodes());
			assertEquals(0, test.numEdges());
			assertEquals(i+1, test.numBooks());
		}

		test.clear();
		for(int i = 0; i < 6; i++) {
			test.addCharacter("A", "1"+i);
			assertEquals(1, test.numNodes());
			assertEquals(0, test.numEdges());
			assertEquals(i+1, test.numBooks());
		}

		test.clear();
		for(int i = 0; i < 6; i++){
			test.addCharacter("A"+i, "1");
			assertEquals(i+1, test.numNodes());
			assertEquals(i*(i+1), test.numEdges());
			assertEquals(1, test.numBooks());
		}

	}


}