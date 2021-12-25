import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * This class is responsible for interfacing between the command processor and
 * the SkipList. The responsibility of this class is to further interpret
 * variations of commands and do some error checking of those commands. This
 * class further interpreting the command means that the two types of remove
 * will be overloaded methods for if we are removing by name or by coordinates.
 * Many of these methods will simply call the appropriate version of the
 * SkipList method after some preparation.
 * 
 * @author Disha Bhan dishab2124
 * 
 * @version 2021-09-26
 */
public class Database {

    // this is the SkipList object that we are using
    // a string for the name of the rectangle and then
    // a rectangle object, these are stored in a KVPair,
    // see the KVPair class for more information
    private SkipList<String, QuadPoint> list;
    private QuadTree quadTree; // contains the quadtree

    /**
     * The constructor for this class initializes a SkipList object with String
     * and Rectangle a its parameters.
     */
    public Database() {
        list = new SkipList<String, QuadPoint>();
        quadTree = new QuadTree();
    }


    /**
     * Inserts the KVPair in the SkipList if the rectangle has valid coordinates
     * and dimensions, that is that the coordinates are non-negative and that
     * the rectangle object has some area (not 0, 0, 0, 0). This insert will
     * insert the KVPair specified into the sorted SkipList appropriately
     * 
     * @param pair
     *            the KVPair to be inserted
     */
    public void insert(QuadPoint pair) {

        // Getting the information of rectangle coordinates and its width and
        // height.
        
        if (pointCheck(pair.getPointObject().x, pair.getPointObject().y)) {
        	KVPair<String, QuadPoint> objKVPair = new KVPair<String, QuadPoint>(pair.getPointName(), pair);
        	list.insert(objKVPair);
        	quadTree.insert(pair);
        	System.out.println("Point Inserted: "  + 
                    pair.toString());
        }
        else {
        	System.out.println("Point Rejected: " +
                    pair.toString() );
        }

    }


    /**
     * Removes a rectangle with the name "name" if available. If not an error
     * message is printed to the console.
     * 
     * @param name
     *            the name of the rectangle to be removed
     */
    public void remove(String name) {

        KVPair<String, QuadPoint> removedPoint = list.remove(name);
        
        if (removedPoint != null) {
      
            quadTree.remove(removedPoint.getValue().getPointObject().x, removedPoint.getValue().getPointObject().y);
            System.out.println("Point "  + removedPoint.getValue().toString()  + " Removed");

        } 
        else {
            System.out.println("Point not removed: " 
                +name ); 
        }

    }


    /**
     * Removes a rectangle with the specified coordinates if available. If not
     * an error message is printed to the console.
     * 
     * @param x
     *            x-coordinate of the rectangle to be removed
     * @param y
     *            x-coordinate of the rectangle to be removed
     * @param w
     *            width of the rectangle to be removed
     * @param h
     *            height of the rectangle to be removed
     */
    public void remove(int x, int y) {
    	
    	if (!pointCheck(x, y)) {
            System.out.println("Point Rejected: " +  String.format(
                    ("(%1$s, %2$s)"), x, y));
        }

        else {
            QuadPoint point = quadTree.remove(x, y);
            
            if (point == null) {
            	System.out.println("Point Not Removed: " +  String.format(
                        ("(%1$s, %2$s)"), x, y));
            }
            else {
            	list.removeByValue(point);
            	System.out.println("Point " +  String.format(
                        ("(%1$s, %2$s)"), x, y) + " Removed");
            }
        }
    }

    /**
     * 
     */
    public void duplicates() {
        System.out.println("Duplicate Points:");

        ArrayList<Point> result = quadTree.duplicate();
        for (int i = 0; i < result.size(); i++) {
            System.out.println("("+ result.get(i).x + ", " + result.get(i).y + ")");
        }
    }

    /**
     * Check a rectangle with the specified coordinates if it is
     * a good rectangle based on defined conditions
     * 
     * @param xPoint
     *            x-coordinate of the rectangle to be checked
     * @param yPoint
     *            y-coordinate of the rectangle to be checked
     * @param width
     *            width of the rectangle to be checked
     * @param height
     *            height of the rectangle to be checked
     * 
     * @return boolean flag
     *         it returns true if the rectangle is valid
     */
    public boolean checkRectangle(
        int xPoint,
        int yPoint,
        int width,
        int height) {

        boolean flag = true;

        if (xPoint < 0 || yPoint < 0 || width <= 0 || height <= 0 || (xPoint
            + width) > 1024 || (yPoint + height) > 1024) {
            flag = false;
            return flag;
        }

        return flag;
    }


    /**
     * Displays all the rectangles inside the specified region. The rectangle
     * must have some area inside the area that is created by the region,
     * meaning, Rectangles that only touch a side or corner of the region
     * specified will not be said to be in the region. You will need a SkipList
     * Iterator for this
     * 
     * @param x
     *            x-Coordinate of the region
     * @param y
     *            y-Coordinate of the region
     * @param w
     *            width of the region
     * @param h
     *            height of the region
     */
    public void regionsearch(int x, int y, int w, int h) {


        if (h <= 0 || w <= 0) {

            System.out.println("Rectangle Rejected: " + String.format(
                "(%1$s, %2$s, %3$s, %4$s)", x, y, w, h));
        }
        else {
            	quadTree.regionSearch(x, y, w, h);
            }

    }

  
    /**
     * Prints out all the rectangles with the specified name in the SkipList.
     * This method will delegate the searching to the SkipList class completely.
     * 
     * @param name
     *            name of the Rectangle to be searched for
     */
    
    public void search(String name) {
    	
	    ArrayList<KVPair<String, QuadPoint>> searchResult = list.search(name);
	
	    if (searchResult != null) {
	
	        System.out.print("Point Found: ");
	        for (KVPair<String, QuadPoint> point : searchResult) {
	        	QuadPoint resPoint = quadTree.search(point.getValue());
	        	System.out.println(resPoint.toString());
	        }
	    }
	    else {
	
	        System.out.println("Point Not Found: " + name);
	    }
}


    /**
     * Prints out a dump of the SkipList which includes information about the
     * size of the SkipList and shows all of the contents of the SkipList. This
     * will all be delegated to the SkipList.
     */
    public void dump() {
        list.dump();
        quadTree.dump();
    }
    
    public boolean pointCheck(int x, int y) {
        return (x < 1024 && x >= 0 && y < 1024 && y >= 0);

    }

}
