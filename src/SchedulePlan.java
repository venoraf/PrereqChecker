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
 * SchedulePlanInputFile name is passed through the command line as args[1]
 * Read from SchedulePlanInputFile with the format:
 * 1. One line containing a course ID
 * 2. c (int): number of courses
 * 3. c lines, each with one course ID
 * 
 * Step 3:
 * SchedulePlanOutputFile name is passed through the command line as args[2]
 * Output to SchedulePlanOutputFile with the format:
 * 1. One line containing an int c, the number of semesters required to take the course
 * 2. c lines, each with space separated course ID's
 */
public class SchedulePlan {
    public static void main(String[] args) {

        if ( args.length < 3 ) {
            StdOut.println("Execute: java -cp bin prereqchecker.SchedulePlan <adjacency list INput file> <schedule plan INput file> <schedule plan OUTput file>");
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

        List<String> coursesToTake = new ArrayList<String>();
        List<String> needToTakePrereqs = listOfPrereqs(needToTakeId, courses);
        for (int i = 0; i < needToTakePrereqs.size(); i++) {
            String courseid = needToTakePrereqs.get(i);

            if (heeseung.contains(needToTakePrereqs.get(i))) {
                continue;
            }

            else {
                coursesToTake.add(courseid);
            }
        }

        int semesterCount = 0;
        String output = "";
        while (coursesToTake.size() > 0) {
            semesterCount++;
            ArrayList<String> semester = new ArrayList<String>();
            for (int i = 0; i < coursesToTake.size(); i++) {
                Course newCourse = courses.get(coursesToTake.get(i));
                Boolean sunoo = true;
                
                for (int j = 0; j < newCourse.prerequisites.size(); j++) {
                    if (!heeseung.contains(newCourse.prerequisites.get(j))) {
                        sunoo = false;
                    }
                }

                if (sunoo) {
                    semester.add(newCourse.getId());
                    output = output + (newCourse.getId()) + " ";
                }
            }
            coursesToTake.removeAll(semester);
            heeseung.addAll(semester); 
            output = output + "\r\n";
        }
        StdOut.println(semesterCount);
        StdOut.print(output);
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
