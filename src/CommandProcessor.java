//import java.awt.Rectangle;
//
///**
// * The purpose of this class is to parse a text file into its appropriate, line
// * by line commands for the format specified in the project spec.
// * 
// * @author Disha Bhan dishab2124
// * 
// * @version 2021-09-26
// */
//public class CommandProcessor {
//
//    // the database object to manipulate the
//    // commands that the command processor
//    // feeds to it
//    private Database data;
//
//    /**
//     * The constructor for the command processor requires a database instance to
//     * exist, so the only constructor takes a database class object to feed
//     * commands to.
//     * 
//     * 
//     */
//    public CommandProcessor() {
//        data = new Database();
//    }
//
//
//    /**
//     * This method identifies keywords in the line and calls methods in the
//     * database as required. Each line command will be specified by one of the
//     * keywords to perform the actions within the database required. These
//     * actions are performed on specified objects and include insert, remove,
//     * region search, search, intersections, and dump.
//     * If the command in the file line is not
//     * one of these, an appropriate message will be written in the console. This
//     * processor method is called for each line in the file. Note that the
//     * methods called will themselves write to the console, this method does
//     * not, only calling methods that do.
//     * 
//     * @param line
//     *            a single line from the text file
//     */
//    public void processor(String line) {
//
//        String[] wordArray = line.split("\\s+");
//
//        KVPair<String, Rectangle> objKVPair;
//
//        if (wordArray[0].equals("insert")) {
//
//            int xPoint = Integer.parseInt(wordArray[2]);
//            int yPoint = Integer.parseInt(wordArray[3]);
//            int rectangleWidth = Integer.parseInt(wordArray[4]);
//            int rectangleHeight = Integer.parseInt(wordArray[5]);
//
//            Rectangle rect = new Rectangle(xPoint, yPoint, rectangleWidth,
//                rectangleHeight);
//
//            objKVPair = new KVPair<String, Rectangle>(wordArray[1], rect);
//
//            data.insert(objKVPair);
//
//        }
//        else if (wordArray[0].equals("remove")) {
//
//            if (wordArray.length == 2) {
//
//                data.remove(wordArray[1]);
//
//            }
//            else {
//
//                int xPoint = Integer.parseInt(wordArray[1]);
//                int yPoint = Integer.parseInt(wordArray[2]);
//                int rectangleWidth = Integer.parseInt(wordArray[3]);
//                int rectangleHeight = Integer.parseInt(wordArray[4]);
//
//                data.remove(xPoint, yPoint, rectangleWidth, rectangleHeight);
//            }
//        }
//        else if (wordArray[0].equals("regionsearch")) {
//
//            int xPoint = Integer.parseInt(wordArray[1]);
//            int yPoint = Integer.parseInt(wordArray[2]);
//            int rectangleWidth = Integer.parseInt(wordArray[3]);
//            int rectangleHeight = Integer.parseInt(wordArray[4]);
//            data.regionsearch(xPoint, yPoint, rectangleWidth, rectangleHeight);
//        }
//
//        else if (wordArray[0].equals("intersections")) {
//
//            data.intersections();
//        }
//
//        else if (wordArray[0].equals("search")) {
//
//            data.search(wordArray[1]);
//
//        }
//
//        else if (wordArray[0].equals("dump")) {
//
//            data.dump();
//
//        }
//
//    }
//
//}
