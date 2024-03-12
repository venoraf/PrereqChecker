package prereqchecker;

import java.util.*;

/**
 * Steps to implement this class main method:
 * 
 * Step 1:
 * AdjListInputFile name is passed through the command line as args[0]
 * Read from AdjListInputFile with the format:
 * 1. a (int): number of courses in the graph
 * 2. a lines, each with 1 course ID
 * 3. b (int): number of edges in the graph
 * 4. b lines, each with a source ID
 * 
 * Step 2:
 * NeedToTakeInputFile name is passed through the command line as args[1]
 * Read from NeedToTakeInputFile with the format:
 * 1. One line, containing a course ID
 * 2. c (int): Number of courses
 * 3. c lines, each with one course ID
 * 
 * Step 3:
 * NeedToTakeOutputFile name is passed through the command line as args[2]
 * Output to NeedToTakeOutputFile with the format:
 * 1. Some number of lines, each with one course ID
 */
public class NeedToTake {
    public static void main(String[] args) {

        if ( args.length < 3 ) {
            StdOut.println("Execute: java NeedToTake <adjacency list INput file> <need to take INput file> <need to take OUTput file>");
            return;
        }

        Hashtable<String, Course> courses = new Hashtable<String, Course>();
        StdIn.setFile(args[0]);
        int numberofCourses = StdIn.readInt();
        StdOut.setFile(args[2]);
    
        for (int sunoo = 0; sunoo < numberofCourses; sunoo++) {
            String courseid = StdIn.readString();
            Course jungwon = new Course(courseid);
            courses.put(courseid, jungwon);
        }

        int prerequisitecount = StdIn.readInt();

        for (int i = 0; i < prerequisitecount; i++) {
            String courseid = StdIn.readString();
            String prereqcourse = StdIn.readString();
            Course sunoo = courses.get(courseid);
            sunoo.addPrereqs(prereqcourse);
        }

        StdIn.setFile(args[1]);
        String needToTakeId = StdIn.readString();
        int numberOfcourses = StdIn.readInt();

        List<String> heeseung = new ArrayList<String>();
        for (int i = 0; i < numberOfcourses; i++) {
            String courseid = StdIn.readString();

            if (!heeseung.contains(courseid)) {
                heeseung.add(courseid);
            }

            List<String> sunoo = listOfPrereqs(courseid, courses);
            
            for (int j = 0; j < sunoo.size(); j++) {
                if (!heeseung.contains(sunoo.get(j))) {
                    heeseung.add(sunoo.get(j));
                }
            }
        }

        List<String> needToTakePrereqs = listOfPrereqs(needToTakeId, courses);
        for (int i = 0; i < needToTakePrereqs.size(); i++) {
            String courseid = needToTakePrereqs.get(i);

            if (heeseung.contains(needToTakePrereqs.get(i))) {
                continue;
            }

            else {
                StdOut.println(courseid);
            }
        }
    }

    private static List<String> listOfPrereqs(String courseid, Hashtable<String, Course> courses) {
        
        ArrayList<String> sunoo = new ArrayList<String>();
        Course course = courses.get(courseid);
        
        for (int i = 0; i < course.prerequisites.size(); i++) {
            if (!sunoo.contains(course.prerequisites.get(i))) {
                sunoo.add(course.prerequisites.get(i));
            }

            List<String> list = listOfPrereqs(course.prerequisites.get(i), courses);
            for (int j = 0; j < list.size(); j++) {
                if (!sunoo.contains(list.get(j))) {
                    sunoo.add(list.get(j));
                }
            } 
        }

        return sunoo;
    }
}
