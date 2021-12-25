import java.awt.Point;

/**
 * 
 */

/**
 * @author Disha/Naveen
 *
 */
@SuppressWarnings("serial")
public class QuadPoint extends Point {
	
	private String pointName;
	private Point pt;
	
	
	public QuadPoint(int xCoordinate, int yCoordinate, String pointName) {
		this.pointName = pointName;
		this.pt = new Point(xCoordinate, yCoordinate);
	}
	
	
	@Override
	public String toString() {
		
		 return "(" + pointName + ", " + pt.x + ", " + pt.y +  ")";  
	}
	
	public String getPointName() {
		
		return this.pointName;
		
	}
	
	public Point getPointObject() {
			
			return pt;
	}
}
