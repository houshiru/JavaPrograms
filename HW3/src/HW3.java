import java.io.File;
import java.util.List;
import java.util.Scanner;

/*
  Author: Shiru Hou
  Email: shou2015@my.fit.edu
  Course: cse2010
  Section: 03
  Description: Using tree store the olympic data. Olympic include multiple sports and
  each sports have multiple events, each event has three winners.
  Using ArrayList to store the children.
 */

public class HW3
{

    /*
      Description
    */
    public static void main(String[] args) throws Exception
    {
        if (args.length < 2) {
            throw new Exception("Invalid arguments!");
        }
        //read the input file of data
        Scanner scanner = new Scanner(new File(args[0]));
        String first = scanner.nextLine();
        String[] elements = first.split("\\s+");
        //apply a tree to store the winterOlympics2014 information
        Tree tree = new Tree(elements[0]);
        for (int i = 1; i < elements.length; i++) {
            tree.insertChild(tree.root, new TreeNode(elements[i]));
        }
        for (int i = 0; i < elements.length - 1; i++) {
            String[] temp = scanner.nextLine().split("\\s+");
            List<TreeNode> children = tree.root.getChildren();
            for (TreeNode child : children) {
                if (child.getValue().equals(temp[0])) {
                    for (int j = 1; j < temp.length; j++) {
                        tree.insertChild(child, new TreeNode(temp[j]));
                    }
                }
            }
        }
        while (scanner.hasNextLine()) {
            String[] temp = scanner.nextLine().split("\\s+");
            TreeNode node = tree.FindNodeByValue(temp[0]);
            for (int i = 1; i < temp.length; i++) {
                tree.appendChild(node, new TreeNode(temp[i]));
            }
        }
        scanner.close();
        //read the file of queries
        scanner = new Scanner(new File(args[1]));
        StringBuilder sb;
        while (scanner.hasNextLine()) {
            sb = new StringBuilder();
            String[] query = scanner.nextLine().split("\\s+");
            if (query[0].equals("GetEventsBySport")) {
                sb.append("GetEventsBySport " + query[1]);
                TreeNode node = tree.FindNodeByValue(query[1]);
                for (TreeNode child : node.getChildren()) {
                    sb.append(" " + child.getValue());
                }
                System.out.println(sb.toString());
            } else if (query[0].equals("GetWinnersAndCountriesBySportAndEvent")) {
                sb.append("GetWinnersAndCountriesBySportAndEvent " + query[1] + " " + query[2]);
                TreeNode node = tree.FindNodeByValue(query[2]);
                for (TreeNode child : node.getChildren()) {
                    sb.append(" " + child.getValue());
                }
                System.out.println(sb.toString());
            } else if (query[0].equals("GetGoldMedalistAndCountryBySportAndEvent")) {
                sb.append("GetGoldMedalistAndCountryBySportAndEvent " + query[1] + " " + query[2]);
                TreeNode node = tree.FindNodeByValue(query[2]);
                TreeNode gold = node.getChildren().get(0);
                sb.append(" " + gold.getValue());
                System.out.println(sb.toString());
            } else if (query[0].equals("GetAthleteWithMostMedals")) {
                sb.append("GetAthleteWithMostMedals ");
                List<String> results = tree.GetAthleteWithMostMedals();
                int number = Integer.parseInt(results.get(0));
                sb.append(number);
                for (int i = 1; i < results.size(); i++) {
                    sb.append(" " + results.get(i).split(":")[0]);
                }
                System.out.println(sb.toString());
            } else if (query[0].equals("GetAthleteWithMostGoldMedals")) {
                sb.append("GetAthleteWithMostGoldMedals ");
                List<String> results = tree.GetAthleteWithMostGoldMedals();
                int number = Integer.parseInt(results.get(0));
                sb.append(number);
                for (int i = 1; i < results.size(); i++) {
                    sb.append(" " + results.get(i).split(":")[0]);
                }
                System.out.println(sb.toString());
            } else if (query[0].equals("GetCountryWithMostMedals")) {
                sb.append("GetCountryWithMostMedals ");
                List<String> results = tree.GetCountryWithMostMedals();
                int number = Integer.parseInt(results.get(0));
                sb.append(number);
                for (int i = 1; i < results.size(); i++) {
                    sb.append(" " + results.get(i));
                }
                System.out.println(sb.toString());
            } else if (query[0].equals("GetCountryWithMostGoldMedals")) {
                sb.append("GetCountryWithMostGoldMedals ");
                List<String> results = tree.GetCountryWithMostGoldMedals();
                int number = Integer.parseInt(results.get(0));
                sb.append(number);
                for (int i = 1; i < results.size(); i++) {
                    sb.append(" " + results.get(i).split(":")[0]);
                }
                System.out.println(sb.toString());
            } else if (query[0].equals("GetSportAndEventByAthlete")) {
                sb.append("GetSportAndEventByAthlete " + query[1]);
                List<String> results = tree.GetSportAndEventByAthlete(query[1]);
                for (int i = 0; i < results.size(); i++) {
                    sb.append(" " + results.get(i));
                }
                System.out.println(sb.toString());
            }
        }
        scanner.close();
    }

}