package prereqchecker;

import java.util.Hashtable;

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
 * ValidPreReqInputFile name is passed through the command line as args[1]
 * Read from ValidPreReqInputFile with the format:
 * 1. 1 line containing the proposed advanced course
 * 2. 1 line containing the proposed prereq to the advanced course
 * 
 * Step 3:
 * ValidPreReqOutputFile name is passed through the command line as args[2]
 * Output to ValidPreReqOutputFile with the format:
 * 1. 1 line, containing either the word "YES" or "NO"
 */
public class ValidPrereq {

    public static void main(String[] args) {

        Hashtable<String, Course> courses = new Hashtable<String, Course>();

        if ( args.length < 3 ) {
            StdOut.println("Execute: java -cp bin prereqchecker.ValidPrereq <adjacency list INput file> <valid prereq INput file> <valid prereq OUTput file>");
            return;
        }
        
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
        String courseidone = StdIn.readString();
        String courseidtwo = StdIn.readString();

        Boolean answer = possiblePrereqs(courseidone, courseidtwo, courses); 

        if (answer) {
            StdOut.print("YES");
        }

        else {
            StdOut.print("NO");
        }
    }

    private static boolean possiblePrereqs(String courseidone, String courseidtwo, Hashtable<String, Course> courses) {
        
        boolean sunoo = true;
        Course course = courses.get(courseidtwo);
        
        if (course.prerequisites.contains(courseidone)) {
            sunoo = false;
        }

        else {
            for (int i = 0; i < course.prerequisites.size(); i++) {
                sunoo = possiblePrereqs(courseidone, course.prerequisites.get(i), courses);
            }
        }

        return sunoo;
    }
}
