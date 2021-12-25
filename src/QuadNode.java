import java.awt.Point;
import java.util.ArrayList;

/**
 * 
 */

/**
 * @author Disha
 *
 */
public interface QuadNode {

	/**
	 * Adds an point to the tree
	 * @param point: point to be inserted
	 * @param originX: current x value being checked in the tree
	 * @param originY: current y value being check in the tree
	 * @param quadWidth: split length of the region in the tree
	 * @return
	 */
	public abstract QuadNode insertPoint(QuadPoint point, int originX,
	        int originY, int quadWidth);
	
	
	/**
	 * 
	 * @param x : x coordinated of the point to be removed
	 * @param y : y coordinate of the point to be removed
	 * @param originX : current x value being checked in the tree
	 * @param originY : current y value being checked in the tree
	 * @param quadWidth : split length of the region in the tree
	 * @param resPointd : Point that has been removed
	 * @return point which is deleted.
	 */
	public abstract QuadNode removePoint(int x, int y, int originX,
	        int originY, int quadWidth, ArrayList<QuadPoint> resPointd);
	
	
	/**
	 * 
	 * @param duplicatePoints : ArrayList to store the duplicate points.
	 * @return : the list of duplicate points in the ArrayList.
	 */
	public abstract ArrayList<Point> checkDuplicatePoints(ArrayList<Point> duplicatePoints);
	
	/**
	 * 
	 * @param point : is the point to be searched
	 * @return
	 */
	public abstract QuadNode searchPoint(QuadPoint point, int originX,
	        int originY, int quadWidth, ArrayList<QuadPoint> resPointd);
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @param quadWidth
	 * @return
	 */
	public abstract QuadNode printNode(int x, int y, int quadWidth, int depth);
	
	public abstract QuadNode areaSearch(int x, int y, int width, int height, int originX, int originY, int quadWidth,
			ArrayList<QuadPoint> pointInRegion);
	
	
}
