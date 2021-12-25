import static org.junit.Assert.assertNotEquals;
import java.awt.Rectangle;
import java.util.ArrayList;
import student.TestCase;

/**
 * 
 */

/**
 * @author Disha Bhan dishab2124
 * 
 * @version 2021-09-26
 * 
 * 
 */

public class SkipListTest extends TestCase {

    private SkipList<String, Rectangle> list;

    /**
     * setUp() method:
     */
    public void setUp() {
        list = new SkipList<String, Rectangle>();
    }


    /**
     * Test Case for the insert method of the Skip list.
     * Steps:
     * 1: Insert items in the skip list.
     * 2: Check the size: Give the expected size and check with the output ->
     * Should return true.
     * 3: Check the size: Give wrong value for expected size and check with the
     * output -> Should return false.
     * 4: Search for the value in the skip list
     * 
     * Coverage for positive and negative cases
     */
    public void testSkipListInsert() {
        Rectangle rect = new Rectangle(1, 1, 80, 100);
        KVPair<String, Rectangle> rectangleObject =
            new KVPair<String, Rectangle>("r1", rect);
        list.insert(rectangleObject);

        assertEquals(1, list.size()); // Checks the size of the skip list and
                                      // should return true.

        Rectangle rect1 = new Rectangle(5, 1, 800, 100);
        rectangleObject = new KVPair<String, Rectangle>("r2", rect1);
        list.insert(rectangleObject);

        Rectangle rect2 = new Rectangle(8, 1, 80, 1000);
        KVPair<String, Rectangle> rectangleObject2 =
            new KVPair<String, Rectangle>("s1", rect2);
        list.insert(rectangleObject2);

        Rectangle rect3 = new Rectangle(-1, -1, 180, 100);
        KVPair<String, Rectangle> rectangleObject3 =
            new KVPair<String, Rectangle>("t1", rect3);
        list.insert(rectangleObject3);

        // Checks the size of the skip list and should return true.
        assertEquals(4, list.size()); 
        
        // Checks the size of the skip list and should return false.
        assertNotEquals(5, list.size());  

        ArrayList<KVPair<String, Rectangle>> foundElement = list.search("s1");
        Rectangle rect4 = new Rectangle(-1, -1, 180, 100);
        KVPair<String, Rectangle> rectangleObject4 =
            new KVPair<String, Rectangle>("df1", rect4);

        assertEquals(true, 
            foundElement.contains(rectangleObject2)); 
                                                                        
        assertEquals(false, 
            foundElement.contains(rectangleObject4)); 
    
    }


    /**
     * Test Case for Search method of SkipList.
     *              Search the elements present in the list
     * 
     */
    public void testSkipListSearch() {
        Rectangle rect = new Rectangle(1, 1, 80, 100);
        KVPair<String, Rectangle> rectangleObject =
            new KVPair<String, Rectangle>("r1", rect);
        list.insert(rectangleObject);

        Rectangle rect1 = new Rectangle(5, 1, 800, 100);
        KVPair<String, Rectangle> rectangleObject1 =
            new KVPair<String, Rectangle>("r2", rect1);
        list.insert(rectangleObject1);

        Rectangle rect2 = new Rectangle(8, 1, 80, 1000);
        KVPair<String, Rectangle> rectangleObject2 =
            new KVPair<String, Rectangle>("s1", rect2);
        list.insert(rectangleObject2);

        ArrayList<KVPair<String, Rectangle>> foundElement = list.search("s1");
        Rectangle rect4 = new Rectangle(-1, -1, 180, 100);
        KVPair<String, Rectangle> rectangleObject4 =
            new KVPair<String, Rectangle>("df1", rect4);
        
        // searching for object in skip list: returns true
        assertEquals(true, foundElement.contains(rectangleObject2)); 
        // searching for object in skip list: returns false
        assertEquals(false, foundElement.contains(rectangleObject4)); 
    }


    /**
     * Test Case for Remove by Key method of SkipList.
     *          removes the elements present in the list.
     * 
     */
    public void testSkipListRemoveByKey() {
        Rectangle rect = new Rectangle(1, 1, 80, 100);
        KVPair<String, Rectangle> rectangleObject =
            new KVPair<String, Rectangle>("r1", rect);
        list.insert(rectangleObject);

        Rectangle rect2 = new Rectangle(8, 1, 80, 1000);
        KVPair<String, Rectangle> rectangleObject2 =
            new KVPair<String, Rectangle>("s1", rect2);
        list.insert(rectangleObject2);

        KVPair<String, Rectangle> item = list.remove("s1");
        assertEquals("s1", item.getKey());
        assertEquals(1, list.size()); // Checks the size of the skip list and
                                      // should return true.
        assertNotEquals(2, list.size()); // Checks the size of the skip list and
                                         // should return false.

        ArrayList<KVPair<String, Rectangle>> foundElement = list.search("s1");
        assertEquals(null, foundElement); // searching for object in skip list:
                                           // returns false

        item = list.remove("t1");
        assertEquals(null, item);

        item = list.remove("p1");
        assertEquals(null, item);

        item = list.remove("r1");
        item = list.remove("r1");
        assertEquals(null, item);

    }


    /**
     * Test Case for Remove by Value method of SkipList.
     *               removes the elements present in the list.
     * 
     */
    public void testSkipListRemoveByValue() {
        Rectangle rect = new Rectangle(1, 1, 80, 100);
        KVPair<String, Rectangle> rectangleObject =
            new KVPair<String, Rectangle>("r1", rect);
        list.insert(rectangleObject);

        Rectangle rect2 = new Rectangle(8, 1, 80, 1000);

        Rectangle rect3 = new Rectangle(-1, -1, 180, 100);
        KVPair<String, Rectangle> rectangleObject3 =
            new KVPair<String, Rectangle>("t1", rect3);
        list.insert(rectangleObject3);

        KVPair<String, Rectangle> item = list.removeByValue(rect3);
        assertEquals("t1", item.getKey());
        assertEquals(1, list.size()); // Checks the size of the skip list and
                                      // should return true.
        assertNotEquals(2, list.size()); // Checks the size of the skip list and
                                         // should return false.

        ArrayList<KVPair<String, Rectangle>> foundElement = list.search("t1");
        assertEquals(null, foundElement); // searching for object in skip list:
                                           // returns false

        item = list.removeByValue(rect2);
        assertEquals(null, item);

        item = list.removeByValue(rect);
        assertEquals("r1", item.getKey());
        item = list.removeByValue(rect);
        assertEquals(null, item);

    }

}
