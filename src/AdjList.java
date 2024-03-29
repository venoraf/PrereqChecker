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
 * AdjListOutputFile name is passed through the command line as args[1]
 * Output to AdjListOutputFile with the format:
 * 1. c lines, each starting with a different course ID, then
 * listing all of that course's prerequisites (space separated)
 */

public class AdjList {
    public static void main(String[] args) {

        if (args.length < 2) {
            StdOut.println(
                    "Execute: java -cp bin prereqchecker.AdjList <adjacency list INput file> <adjacency list OUTput file>");
            return;
        }

        Hashtable<String, Course> courses = new Hashtable<String, Course>();
        StdIn.setFile(args[0]);
        int numberofCourses = StdIn.readInt();
        StdOut.setFile(args[1]);

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

        for (String courseid : courses.keySet()) {
            StdOut.print(courseid);
            StdOut.print(" ");
            Course course = courses.get(courseid);
            for (String prereqid : course.prerequisites) {
                StdOut.print(prereqid);
                StdOut.print(" ");
            }
            StdOut.println();
        }

    }
}
