import java.io.*;
import java.util.ArrayList;
import java.util.List;

/*

  Author: Shiru Hou
  Email: shou2015@my.fit.edu
  Course: cse2010
  Section: 03
 */

public class HW5
{

    /*
      Description
    */
    public static void main(String[] args) 
    {
    	//read input file
    	File file = new File(args[0]);
    	List<Record>reList = new ArrayList<Record>();
        BufferedReader reader = null;  
        try {  
            reader = new BufferedReader(new FileReader(file));  
            String tempString = null;  
            int line = 1;   
            while ((tempString = reader.readLine()) != null) {   
            	String []lines = tempString.split("\\s+");
            	Record record = new Record(lines[0], lines.length > 1? lines[1]:"",
						lines.length > 2? lines[2]:"");// store the inputs in a list
            	reList.add(record);
                System.out.println("line " + line + ": " + tempString);  
                line++;  
            }  
            reader.close();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            if (reader != null) {  
                try {  
                    reader.close();  
                } catch (IOException e1) {  
                }  
            }  
        }
        //output
        File file2 = new File("output.txt");
        BufferedWriter writer = null;
        try {
			writer = new BufferedWriter(new FileWriter(file2));
			SkipListMap skipListMap = new SkipListMap();
			for(Record r: reList){
				operate(skipListMap, r, writer);// operate for each command
			}
			writer.flush();
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {  
            if (writer != null) {  
                try {  
                	writer.close();  
                } catch (IOException e1) {  
                }  
            }  
        }
        
    }
    //select different operation
    static public void operate(SkipListMap skipListMap, Record record, BufferedWriter writer){
		if ("AddActivity".equals(record.Operation)) {
			Unit unit = new Unit(record.getTime(), record.Detail);
			skipListMap.put(record.getTime(), unit);
			try {
				writer.write(record.toString());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if ("GetActivity".equals(record.Operation)) {
			Unit unit = skipListMap.get(record.getTime());
			try {
				if (unit.time.compareToIgnoreCase(record.getTime()) == 0)
					writer.write("GetActivity " + record.getTime() + " " + unit.content + "\n");
				else
					writer.write("GetActivity " + record.getTime() + " none\n");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if ("RemoveActivity".equals(record.Operation)) {
			Unit unit = skipListMap.get(record.getTime());
			boolean result = skipListMap.remove(record.getTime());
			try {
				if (result)
					writer.write("RemoveActivity " + record.getTime() + " " + unit.content + "\n");
				else
					writer.write("RemoveActivity " + record.getTime() + " NoActivityError\n");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			;

		} else if ("GetActivitiesBetweenTimes".equals(record.Operation)) {
			String time1 = record.getTime();
			String time2 = record.Detail;
			List<Unit> uList = skipListMap.subMap(time1, time2);
			try {
				writer.write("GetActivitiesBetweenTimes " + time1 + " " + time2 + " ");
				for (Unit u : uList) {
					writer.write(u.toString() + " ");
				}
				writer.write("\n");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			;

		} else if ("GetActivitiesForOneDay".equals(record.Operation)) {
			String time = record.getTime();
			String date = time;
			String time1 = date + "0000";
			String time2 = date + "2359";
			List<Unit> uList = skipListMap.subMap(time1, time2);
			try {
				writer.write("GetActivitiesForOneDay " + record.getTime() + " ");
				if (uList.size() > 0) {
					for (Unit u : uList) {
						writer.write(u.toString() + " ");
					}
				} else
					writer.write("none");
				writer.write("\n");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			;

		} else if ("GetActivitiesFromEarlierInTheDay".equals(record.Operation)) {
			String time = record.getTime();
			String zero = time.substring(0, 4) + "0000";
			List<Unit> uList = skipListMap.subMap(time, zero);
			try {
				writer.write("GetActivitiesFromEarlierInTheDay " + record.getTime() + " ");
				if (uList.size() > 0) {
					for (Unit u : uList) {
						writer.write(u.toString() + " ");
					}
				} else {
					writer.write("none");
				}
				writer.write("\n");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			;

		} else if ("PrintSkipList".equals(record.Operation)) {
			try {
				writer.write("PrintSkipList\n");
				int count = 0;
				for (DoublyLinkedList<Unit> ddl : skipListMap.lList) {
					if (ddl.size() > 0) {
						count++;
					}
				}
				for (int i = count; i >= 0; i--) {
					writer.write(skipListMap.lList.get(i).toStringOfMap(i));
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
    }
}
