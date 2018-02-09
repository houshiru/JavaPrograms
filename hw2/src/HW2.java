/*
  Author: Shiru Hou
  Email: shou2015@my.fit.edu
  Course: CSE2010
  Section: 03
  Description:Automate schedule the course time. Find the “best” schedule that
  has the largest number of courses (first priority) with the most
  preferred courses (second priority) and time slots (third priority).
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class HW2

{
    //store the each line read from the file
    static ArrayList<String> arrayList = new ArrayList<String>();
    //Used to save the temp schedule time
    static String[] saveTime;
    //save the final schedule time
    static String[] result;
    //store the size of file
    static int size;
    //store each course with course name and time
    static ArrayList<Course> course_list = new ArrayList<Course>();

    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(args[0]);
        String fileName = scanner.nextLine();
        File file = new File(fileName);
        try {
            Scanner scannerFile = new Scanner(file);
            while (scannerFile.hasNextLine()){
                String line = scannerFile.nextLine();
                arrayList.add(line);
            }
            size = arrayList.size();
            saveTime = new String[size];
            result = new String[size];
            readLine();
            //call recursively method from the first class begin
            recursively(0);
            outputResult();
        } catch (FileNotFoundException e) {
            System.out.println("There is no file!");
        }

    }

    /*
     * Read the file, split each line into course name and course time.
     * Then create a Course object to store each course information.
     * Use the arrayList to store each course information
     */
    public static void readLine(){
        for (int i = 0; i < arrayList.size(); i++) {
            String[] strings = arrayList.get(i).split(" ");
            String course_name = strings[0];
            int size = strings.length - 1;
            String courseTime[] = new String[size];
            for (int j = 1; j < strings.length; j++) {
                courseTime[j-1] = strings[j];
            }
            Course course = new Course(course_name,courseTime);
            course_list.add(course);
        }
    }

    //course_index stand for the index of course that stored in the course_list
    //time_index stand for which time of the course
    public static void recursively(int course_index){
        if(course_index == course_list.size()){
            return;
        }
        //get course_index
        Course course = course_list.get(course_index);
        //travel all course time
        for (int i = 0; i < course.getSize(); i++) {
            String time = course.getCourse_time(i);
            //if time was not conflict with scheduled time, save it
            if(!isConflictWithSaveTime(course_index,time)){
                saveTime[course_index] = time;
                //get the largest number of classes time
                if(sizeOf(saveTime) > sizeOf(result)){
                    copyResult();
                }
                //recurse to next class
                recursively(course_index+1);
                saveTime[course_index] = null;
            }
        }
        //recurse to next class
        recursively(course_index+1);
    }

    //Determine whether the course time conflict
    public static boolean isConflict(String time1 , String time2){
        if(time2 == null){
            return false;
        }
        if(time1.startsWith("MW") && time2.startsWith("MW")
                || time1.startsWith("TR") && time2.startsWith("TR")){
            DateFormat df = new SimpleDateFormat("HHmm");
            try {
                Date date1 = df.parse(split_time(time1));
                Date date2 = df.parse(split_time(time2));
                int time1_5= 5400000;//1.5hour
                if(Math.abs(date1.getTime()-date2.getTime()) < time1_5){
                    return true;
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    //determine whether the time conflict with the time in the saveTime
    public static boolean isConflictWithSaveTime(int index,String time){
        for (int i = 0; i < index; i++) {
            if(isConflict(time,saveTime[i])){
                return true;
            }
        }
        return false;
    }

    //extract the course time, for example MWF0900 -> 0900
    public static String split_time(String time){
        if(time.startsWith("MWF")){
            return time.substring(3,7);
        }else if(time.startsWith("MW")){
            return time.substring(2,6);
        }else if(time.startsWith("TR")){
            return time.substring(2,6);
        }
        return null;
    }

    //count the number of non-empty element in the array
    public static int sizeOf(String[] str){
        int count = 0;
        for (int i = 0; i < str.length; i++) {
            if(str[i] != null){
                count+=1;
            }
        }
        return count;
    }

    //copy the schedule time in saveTime array to result array that used to make sure
    //schedule has the largest number of courses
    public static void  copyResult(){
        for (int i = 0; i < result.length; i++) {
            result[i] = saveTime[i];
        }
    }

    //Output
    public static void outputResult(){
        System.out.println("---Course schedule---");
        for (int i = 0; i < result.length; i++) {
            if(result[i] != null){
                System.out.println(course_list.get(i).getCourse_name() +
                        " " + result[i]);
            }
        }
        System.out.println("---Courses with a time conflict---");
        for (int i = 0; i < result.length; i++) {
            if(result[i] == null){
                System.out.println(arrayList.get(i));
            }
        }
    }
}
