/*
Define a Course class that include the course name and course time.
 */
public class Course {
    private String course_name;
    private String[] course_time;
    private int size; //define the size of how many different time of each course

    /*
    Constructor
     */
    public Course(String course_name, String[] course_time) {
        this.course_name = course_name;
        this.course_time = course_time;
        this.size = course_time.length;
    }


    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getCourse_time(int count) {
        return course_time[count];
    }

    public void setCourse_time(String[] course_time) {
        this.course_time = course_time;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
