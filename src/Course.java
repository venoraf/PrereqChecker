package prereqchecker;

import java.util.ArrayList;
import java.util.List;

public class Course {

    String id; 
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getPrerequisites() {
        return prerequisites;
    }

    public void setPrerequisites(List<String> prerequisites) {
        this.prerequisites = prerequisites;
    }

    List<String> prerequisites;

    public Course(String courseid) {
        id = courseid;
        prerequisites = new ArrayList<String>();
    }

    public void addPrereqs(String prereq) {
        prerequisites.add(prereq);
    }
    
}
