import student.TestCase;



public class commandFileTest extends TestCase {
	
	private CommandFile cmdFile;
	
	public void setUp() {
		
		cmdFile = new CommandFile();
	}
	
	public void testMain() {
		
		
		cmdFile.processor("insert pts1 10 50");
		assertEquals("Point Inserted: (pts1, 10, 50)\n", SystemOut().getHistory());
		SystemOut.clearHistory();
		
		cmdFile.processor("insert pts3 15 15");
		assertEquals("Point Inserted: (pts3, 15, 15)\n", SystemOut.getHistory());
		SystemOut.clearHistory();
		
		cmdFile.processor("insert pts4 300 300");
		assertEquals("Point Inserted: (pts4, 300, 300)\n", SystemOut.getHistory());
		SystemOut.clearHistory();
		
		cmdFile.processor("insert pts5 300 300");
		assertEquals("Point Inserted: (pts5, 300, 300)\n", SystemOut.getHistory());
		SystemOut.clearHistory();
		
		cmdFile.processor("insert pts2 -10 50");
		assertEquals("Point Rejected: (pts2, -10, 50)\n", SystemOut().getHistory());
		SystemOut.clearHistory();
		
		cmdFile.processor("remove 10 50");
		assertEquals("Point: (pts1, 10, 50) Removed\n", SystemOut().getHistory());
		SystemOut.clearHistory();
		
		cmdFile.processor("remove -1 5");
		assertEquals("Point Rejected: (-1, 5)\n", SystemOut.getHistory());
		SystemOut.clearHistory();
		
		cmdFile.processor("remove 20 100");
		assertEquals("Point Not Found: (20, 100)\n", SystemOut.getHistory());
		SystemOut.clearHistory();
		
		cmdFile.processor("remove pts50");
		assertEquals("Point Not Found: pts50\n", SystemOut.getHistory());
		SystemOut.clearHistory();
		
		cmdFile.processor("search pts3");
		assertEquals("Point Found (pts3, 15, 15)\n", SystemOut.getHistory());
		SystemOut.clearHistory();
		
		cmdFile.processor("search pts50");
		assertEquals("Point Not Found: pts50\n", SystemOut.getHistory());
		SystemOut.clearHistory();
		
		cmdFile.processor("regionsearch 200 200 500 400");
		assertEquals("Point Found: (pts4, 300, 300)\n", SystemOut.getHistory());
		SystemOut.clearHistory();
		
		cmdFile.processor("duplicates");
		assertEquals("(pts4, 300, 300)\n", SystemOut.getHistory());
		SystemOut.clearHistory();
		
	}

}
