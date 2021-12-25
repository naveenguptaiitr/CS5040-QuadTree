import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * 
 */

/**
 * @author Disha
 *
 */
public class QuadTree {
	
	private QuadNode root;
	private static QuadNode flyWeight;
	private int elements;
	
	private static int nodeCounter = 0;
	
	/**
     * Default constructor for quadtree, 
     */
    public QuadTree() {
        flyWeight = new EmptyNode();
        root = flyWeight;
        elements = 0;
    }
    
	public void insert(QuadPoint element) {
			
		root = root.insertPoint(element, 0, 0, 1024);
		elements++;	
	}
	
	public QuadPoint remove(int x, int y) {
		
		ArrayList<QuadPoint> resPoint = new ArrayList<QuadPoint>();
		root = root.removePoint(x, y, 0, 0, 1024, resPoint);
		if (resPoint.size() > 0) {
			return resPoint.get(0);
		}
		return null;
	}
	
	public ArrayList<Point> duplicate(){
		
		ArrayList<Point> duplicatePoints = new ArrayList<Point>();
		root.checkDuplicatePoints(duplicatePoints);
		
		return duplicatePoints;
	}
	
	public void dump() {
		System.out.println("QuadTree Dump:");
		root = root.printNode(0, 0, 1024, 0);
		System.out.println("QuadTree size: " + nodeCounter + " QuadTree Nodes Printed");
		nodeCounter = 0;
	}
	
	public QuadPoint search(QuadPoint element) {
		
		ArrayList<QuadPoint> resPoint = new ArrayList<QuadPoint>();
		root = root.searchPoint(element, 0, 0, 1024, resPoint);
		if (resPoint.size() > 0) {
			return resPoint.get(0);
		}
		return null;
	}
	
	public void regionSearch(int x, int y, int width, int height) {

		ArrayList<QuadPoint> pointsInRegion = new ArrayList<QuadPoint>();
		System.out.println("Points Intersecting Region: " + String.format(
                "(%1$s, %2$s, %3$s, %4$s)", x, y, width, height) );
		root = root.areaSearch(x, y, width, height, 0, 0, 1024, pointsInRegion);
		System.out.println(nodeCounter + " QuadTree Nodes Visited");

		nodeCounter = 0;
	}
	
	private static class EmptyNode implements QuadNode {
			
			private static EmptyNode nodeState = null;
			
			private EmptyNode() {
	
			}
	
			public static EmptyNode getInstance() {
				if (nodeState == null) {
					return new EmptyNode();
				}
				return nodeState;
			}
			
			@Override
			public QuadNode insertPoint(QuadPoint point, int originX, int originY, int quadWidth) {
				
				QuadNode newPoint = new VarLeafNode();
				newPoint.insertPoint(point, originX, originY, quadWidth);
				return newPoint;
			}
			
			@Override
			public QuadNode removePoint(int x, int y, int originX, int originY, int quadWidth, ArrayList<QuadPoint> resPoint){
				return this;
			}
			
			@Override
			public ArrayList<Point> checkDuplicatePoints(ArrayList<Point> duplicatePoints) {
				
				return duplicatePoints;
			}

			@Override
			public QuadNode printNode(int x, int y, int quadWidth, int depth) {
				nodeCounter++;
				String indent = "";
				for (int i = 0; i < depth; i++)
	                indent = indent + "  ";
				System.out.println(indent + "Node at " + String.valueOf(x) + ", " + String.valueOf(y) + ", "
						+ String.valueOf(quadWidth) + ": Empty");
				return this;
			}
			
			@Override
			public QuadNode searchPoint(QuadPoint point, int originX,
			        int originY, int quadWidth, ArrayList<QuadPoint> resPointd) {
				return this;
			}
			
			@Override
			public QuadNode areaSearch(int x, int y, int width, int height, int originX, int originY, int quadWidth,
					ArrayList<QuadPoint> pointInRegion) {
				nodeCounter++;
				return this;
			}

	}
	
	private static class VarLeafNode implements QuadNode {
			
			private ArrayList<QuadPoint> record;
			
			
			public VarLeafNode() {
				record = new ArrayList<QuadPoint>();
			}
			
			@SuppressWarnings("unused")
			public VarLeafNode(ArrayList<QuadPoint> newRecord) {
				
				record = newRecord;
			}
			
		
		@Override
		public QuadNode insertPoint(QuadPoint point, int originX, int originY, int quadWidth) {
			
			if (record.size() == 0) {
				
				record.add(point);
				return this;
				
			}
			else if (record.size() < 3) {
				
				record.add(point);
				return this;
				
			}
			else if (record.size() >= 3) {
				
				int same = 0;
				for (int i = 0; i < record.size(); i++) {
					
					if ((record.get(i)).getPointObject().x == point.getPointObject().x && 
							(record.get(i)).getPointObject().y == point.getPointObject().y) {
						
						same++;
						
					}
				}
				if (same == record.size()) {
					
					record.add(point);
					return this;
					
				}
			}
			
			QuadNode newInternalNode = new VarInternalNode();
			for (int i = 0; i < record.size(); i++) {
				
				newInternalNode = newInternalNode.insertPoint(record.get(i), originX, originY, quadWidth);
				
			}
			newInternalNode = newInternalNode.insertPoint(point, originX, originY, quadWidth);
			return newInternalNode;
			
		}
		
		@Override
		public QuadNode removePoint(int x, int y, int originX, int originY, int quadWidth, ArrayList<QuadPoint> resPoint) {
			
			int index;
			
			for (index = 0; index < record.size(); index++) {
				
				if (x == record.get(index).getPointObject().x && y == record.get(index).getPointObject().y) {
					//resPoint[0] = record.get(index);
					resPoint.add(record.get(index));
					record.remove(index);
					break;
				}
			}
			
			if(record.size() == 0) {
				return flyWeight;
			}
			else {
				return this;
			}
			
		}
		
		@Override
		public ArrayList<Point> checkDuplicatePoints(ArrayList<Point> duplicatePoints) {
			
			for (int i = 0; i < record.size(); i++) {
				for (int j = i + 1; j < record.size(); j++) {
					Point temp1 = record.get(i).getPointObject();
					Point temp2 = record.get(j).getPointObject();
					if (temp1.equals(temp2)) {
						duplicatePoints.add(temp2);
						
					}
				}
			}
			return duplicatePoints;
		}
		
		@Override
		public QuadNode areaSearch(int x, int y, int width, int height, int originX, int originY, int quadWidth,
				ArrayList<QuadPoint> pointInRegion) {

			Rectangle region = new Rectangle(x, y, width, height);
			Rectangle quadrant = new Rectangle(originX, originY, quadWidth, quadWidth);

			if (region.intersects(quadrant)) {

				for (int i = 0; i < record.size(); i++) {

					Point currPoint = record.get(i).getPointObject();

					if (region.contains(currPoint)) {

						System.out.println("Point Found: " + record.get(i).toString());
					}
				}
			}
			nodeCounter++;
			return this;
		}

		
		@Override
		public QuadNode printNode(int x, int y, int quadWidth, int depth) {

			String indent = "";
			for (int i = 0; i < depth; i++)
                indent = indent + "  ";
			System.out.println(indent + "Node at " + String.valueOf(x) + ", " + String.valueOf(y) + ", "
					+ String.valueOf(quadWidth) + ": ");
			nodeCounter++;
			for (int i = 0; i < record.size(); i++) {
				System.out.println(indent + record.get(i).toString());
			}
			return this;
		}

		@Override
		public QuadNode searchPoint(QuadPoint point, int originX,
		        int originY, int quadWidth, ArrayList<QuadPoint> resPointd) {
			
			int index;
			
			for (index = 0; index < record.size(); index++) {
				
				if (point.equals(record.get(index))) {
					resPointd.add(record.get(index));
					break;
				}
			}
			
			if(record.size() == 0) {
				return flyWeight;
			}
			else {
				return this;
			}
		}
		
	}
	
	private static class VarInternalNode implements QuadNode{
		
		private QuadNode nW;
		private QuadNode nE;
		private QuadNode sE;
		private QuadNode sW;
		
		public VarInternalNode() {
			
			nW = (QuadNode)flyWeight;
			nE = (QuadNode)flyWeight;
			sE = (QuadNode)flyWeight;
			sW = (QuadNode)flyWeight;
		}
		
		
		@Override
		public ArrayList<Point> checkDuplicatePoints(ArrayList<Point> duplicatePoints) {
			
			nW.checkDuplicatePoints(duplicatePoints);
			nE.checkDuplicatePoints(duplicatePoints);
			sE.checkDuplicatePoints(duplicatePoints);
			sW.checkDuplicatePoints(duplicatePoints);
			
			return duplicatePoints;
		}
		
		@Override
		public QuadNode insertPoint(QuadPoint point, int originX, int originY, int quadWidth) {
			
			quadWidth = quadWidth/2;
			int xLim = originX + quadWidth;
			int yLim = originY + quadWidth;
			
			if(point.getPointObject().x < xLim && point.getPointObject().y < yLim) {
				
				nW = nW.insertPoint(point, originX, originY, quadWidth);
				return this;
			}
			else if(point.getPointObject().x >= xLim && point.getPointObject().y < yLim) {
				
				nE = nE.insertPoint(point, xLim, originY, quadWidth);
				return this;
			}
			else if(point.getPointObject().x < xLim && point.getPointObject().y >= yLim) {
				
				sW = sW.insertPoint(point, originX, yLim, quadWidth);
				return this;
			}
			else {
				sE = sE.insertPoint(point, xLim, yLim, quadWidth);
				return this;
			}
		}
		
		@Override
		public QuadNode removePoint(int x, int y, int originX, int originY, int quadWidth, ArrayList<QuadPoint> resPoint) {
			
			quadWidth = quadWidth/2;
			
			int xLim = originX + quadWidth;
			int yLim = originY + quadWidth;
			
			if(x < xLim && y < yLim) {
				nW = nW.removePoint(x, y, originX, originY, quadWidth, resPoint);
			}
			else if(x >= xLim && y < yLim) {
				nE = nE.removePoint(x, y, xLim, originY, quadWidth, resPoint);
			}
			else if(x < xLim && y >= yLim) {
				sW = sW.removePoint(x, y, originX, yLim, quadWidth, resPoint);
			}
			else {
				sE = sE.removePoint(x, y, xLim, yLim, quadWidth, resPoint);
			}
			
			
			
			return mergeNode();
		}
		
		private QuadNode mergeNode() {

            /*
             * Checks if there is only one node with things in it  
             */
            if (nW.getClass().getName().compareTo("QuadTree$VarLeafNode") == 0 
                && nE == flyWeight && sW == flyWeight && sE == flyWeight) {
                return nW;
            }
            else if (nW == flyWeight && 
                nE.getClass().getName().compareTo(
                    "QuadTree$LeafNode") == 0
                    && sW == flyWeight && sE == flyWeight) {
                return nE;  
            }
            else if (nW == flyWeight && nE == flyWeight && 
                sW.getClass().getName().compareTo(
                    "QuadTree$LeafNode") == 0
                    && sE == flyWeight) {
                return sW;
            }
            else if (nW == flyWeight && nE == flyWeight && sW == flyWeight && 
                sE.getClass().getName().compareTo(
                    "QuadTree$LeafNode") == 0) {
                return sE;
            }

            else {

            	ArrayList<QuadPoint> results = new ArrayList<QuadPoint>();

                if (nW.getClass().getName().compareTo(
                    "QuadTree$VarLeafNode") == 0) {
                	for (int i = 0; i < ((VarLeafNode)nW).record.size(); i++) {
                		
                		results.add(((VarLeafNode)nW).record.get(i));
                	}
                	
                }
                if (nE.getClass().getName().compareTo(
                    "QuadTree$VarLeafNode") == 0) {
                	
                	for (int i = 0; i < ((VarLeafNode)nE).record.size(); i++) {
                		
                		results.add(((VarLeafNode)nE).record.get(i));
                	}

                }
                if (sW.getClass().getName().compareTo(
                    "QuadTree$VarLeafNode") == 0) {
                	
                	for (int i = 0; i < ((VarLeafNode)sW).record.size(); i++) {
                		
                		results.add(((VarLeafNode)sW).record.get(i));
                	}
                	
                }
                if (sE.getClass().getName().compareTo(
                    "QuadTree$VarLeafNode") == 0) {
                	
                	for (int i = 0; i < ((VarLeafNode)sE).record.size(); i++) {
                		
                		results.add(((VarLeafNode)sE).record.get(i));
                	}
 
                }

                if (results.size() == 3) {
                    return new VarLeafNode(results);
                }
                return this;
            }

        }

		@Override
		public QuadNode printNode(int x, int y, int quadWidth, int depth) {
			
			String indent = "";
			for (int i = 0; i < depth; i++)
                indent = indent + "  ";
			System.out.println(indent + "Node at " + String.valueOf(x) + ", " + String.valueOf(y) + ", "
					+ String.valueOf(quadWidth) + ": Internal");
			nodeCounter++;
			
			quadWidth = quadWidth / 2;
			int xLim = x + quadWidth;
			int yLim = y + quadWidth;

			nW.printNode(x, y, quadWidth, depth+1);
			nE.printNode(xLim, y, quadWidth, depth+1);
			sW.printNode(x, yLim, quadWidth, depth+1);
			sE.printNode(xLim, yLim, quadWidth, depth+1);

			return this;
		}

		@Override
		public QuadNode searchPoint(QuadPoint point, int originX,
		        int originY, int quadWidth, ArrayList<QuadPoint> resPointd) {
			
			quadWidth = quadWidth/2;
			int xLim = originX + quadWidth;
			int yLim = originY + quadWidth;
			
			if(point.getPointObject().x < xLim && point.getPointObject().y < yLim) {
				
				nW = nW.searchPoint(point, originX, originY, quadWidth, resPointd);
				return this;
			}
			else if(point.getPointObject().x >= xLim && point.getPointObject().y < yLim) {
				
				nE = nE.searchPoint(point, xLim, originY, quadWidth, resPointd);
				return this;
			}
			else if(point.getPointObject().x < xLim && point.getPointObject().y >= yLim) {
				
				sW = sW.searchPoint(point, originX, yLim, quadWidth, resPointd);
				return this;
			}
			else {
				sE = sE.searchPoint(point, xLim, yLim, quadWidth, resPointd);
				return this;
			}
		}
		
		@Override
		public QuadNode areaSearch(int x, int y, int width, int height, int originX, int originY, int quadWidth,
				ArrayList<QuadPoint> pointInRegion) {

			quadWidth = quadWidth / 2;
			int xLim = originX + quadWidth;
			int yLim = originY + quadWidth;

			Rectangle nwQuadrant = new Rectangle(originX, originY, quadWidth, quadWidth);
			Rectangle neQuadrant = new Rectangle(xLim, originY, quadWidth, quadWidth);
			Rectangle swQuadrant = new Rectangle(originX, yLim, quadWidth, quadWidth);
			Rectangle seQuadrant = new Rectangle(xLim, yLim, quadWidth, quadWidth);

			Rectangle queryRegion = new Rectangle(x, y, width, height);

			if (nwQuadrant.intersects(queryRegion)) {
				
				nW.areaSearch(x, y, width, height, originX, originY, quadWidth, pointInRegion);
			} else if (neQuadrant.intersects(queryRegion)) {

				nE.areaSearch(x, y, width, height, xLim, originY, quadWidth, pointInRegion);
			} else if (seQuadrant.intersects(queryRegion)) {

				sE.areaSearch(x, y, width, height, xLim, yLim, quadWidth, pointInRegion);
			} else {
				sW.areaSearch(x, y, width, height, originX, yLim, quadWidth, pointInRegion);
			}

			nodeCounter++;
			return this;
		}
		
	}

}
