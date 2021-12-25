//import student.TestCase;
//
///**
// * @author Disha Bhan dishab2124
// * 
// * @version 2021-09-26
// *
// */
//public class Rectangle1Test extends TestCase {
//
//    /**
//     * setUp()method
//     */
//    public void setUp() {
//        // Comment
//    }
//
//
//    /**
//     * Testing Main Method of the Rectangle Class
//     */
//
//    @SuppressWarnings("static-access")
//    public void testRectangleMain() {
//        String expectedResult = "Invalid file";
//        Rectangle1.main(new String[] { "Data/P1test1.txt" });
//        assertTrue(getName(), true);
//        assertTrue(equalsRegex(systemOut().getHistory(), "[\\s\\S]*"));
//        Rectangle1.main(new String[] { "Data/P1test2.txt" });
//        Rectangle1.main(new String[] { " " });
//        assertTrue(systemOut().getHistory().contains(expectedResult));
//        Rectangle1.main(new String[] { "Data/P1test3.txt" });
//        assertTrue(systemOut().getHistory().contains(expectedResult));
//
//        Rectangle1 rect1 = new Rectangle1();
//        rect1.main(new String[] { "./Data/P1test1.txt" });
//        assertTrue(equalsRegex(systemOut().getHistory(), "[\\s\\S]*"));
//        rect1.main(new String[] { "./Data/P1test2.txt" });
//
//    }
//}
