import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/*
Tree structure
 */
public class Tree {
    TreeNode root;
    //constructor
    public Tree(String rootValue) {
        root = new TreeNode(rootValue);
        root.setParent(null);
    }
    //insert child
    public void insertChild(TreeNode parent, TreeNode child){
        parent.insertChild(child);
        child.setParent(parent);
    }
    //append child
    public void appendChild(TreeNode parent, TreeNode child){
        parent.appendChild(child);
        child.setParent(parent);
    }
    //get parent
    public TreeNode getParent(TreeNode node) {
        return node.getParent();
    }
    //get children
    public List<TreeNode> getChildren(TreeNode node){
        return node.getChildren();
    }
    //used to output
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(root.getValue() + "\n");
        List<TreeNode> temp = root.getChildren();
        for (int i = 0; i < temp.size() ; i++) {
            sb.append("\t" + temp.get(i).getValue() + "\n");
            List<TreeNode> events = temp.get(i).getChildren();
            for (TreeNode event: events) {
                sb.append("\t\t" + event.getValue() + "\n");
                List<TreeNode> winners = event.getChildren();
                for (TreeNode winner : winners) {
                    sb.append("\t\t\t" + winner.getValue() + "\n");
                }
            }
        }
        return sb.toString();
    }
    //find node by value
    public TreeNode FindNodeByValue(String value){
        List<TreeNode> nodes = new ArrayList<TreeNode>();
        nodes.add(root);
        while (nodes.size() > 0){
            TreeNode node = nodes.remove(0);
            if (node.getValue().equals(value)){
                return node;
            }
            for (TreeNode child: node.getChildren()) {
                nodes.add(child);
            }
        }

        return null;
    }
    //get athlete with most medals
    public List<String> GetAthleteWithMostMedals(){
        List<String> athletes = new ArrayList<String>();//store athletes
        List<Integer> medals = new ArrayList<Integer>();//store num of medals
        List<TreeNode> nodes = new ArrayList<TreeNode>();
        nodes.add(root);
        while (nodes.size() > 0){
            TreeNode node = nodes.remove(0);
            if (node.getValue().contains(":")){
                if (athletes.contains(node.getValue())){
                    medals.set(athletes.indexOf(node.getValue()),
                            medals.get(athletes.indexOf(node.getValue())) + 1);
                }else {
                    athletes.add(node.getValue());
                    medals.add(1);
                }
            }
            for (TreeNode child: node.getChildren()) {
                nodes.add(child);
            }
        }
        //get the most medals
        List<String> results = new ArrayList<String>();
        int max = 1;
        for (int i = 0; i < medals.size(); i++) {
            if (medals.get(i) == max){
                results.add(athletes.get(i));
            }else if (medals.get(i)>max){
                max = medals.get(i);
                results.clear();
                results.add(athletes.get(i));
            }
        }
        //sort
        Collections.sort(results);
        results.add(0, max + "");
        return results;
    }
    //get athlete with most gold medals
    public List<String> GetAthleteWithMostGoldMedals(){
        List<String> athletes = new ArrayList<String>();
        List<Integer> medals = new ArrayList<Integer>();
        List<TreeNode> nodes = new ArrayList<TreeNode>();
        nodes.add(root);
        while (nodes.size() > 0){
            TreeNode node = nodes.remove(0);
            if (node.getValue().contains(":")){
                if (athletes.contains(node.getValue())){
                    medals.set(athletes.indexOf(node.getValue()),
                            medals.get(athletes.indexOf(node.getValue())) + 1);
                }else {
                    athletes.add(node.getValue());
                    medals.add(1);
                }
                nodes.remove(0);
                nodes.remove(0);
            }
            for (TreeNode child: node.getChildren()) {
                nodes.add(child);
            }
        }
        //get the most medals
        List<String> results = new ArrayList<String>();
        int max = 1;
        for (int i = 0; i < medals.size(); i++) {
            if (medals.get(i) == max){
                results.add(athletes.get(i));
            }else if (medals.get(i) > max){
                max = medals.get(i);
                results.clear();
                results.add(athletes.get(i));
            }
        }
        Collections.sort(results);
        results.add(0, max + "");
        return results;
    }
    //get coutry with most medals
    public List<String> GetCountryWithMostMedals() {
        List<String> countries = new ArrayList<String>();
        List<Integer> medals = new ArrayList<Integer>();
        List<TreeNode> nodes = new ArrayList<TreeNode>();
        nodes.add(root);
        while(nodes.size() > 0) {
            TreeNode node = nodes.remove(0);
            if (node.getValue().contains(":")) {
                String country = node.getValue().split(":")[1];
                if (countries.contains(country)) {
                    medals.set(countries.indexOf(country), medals.get(countries.indexOf(country)) + 1);
                } else {
                    countries.add(country);
                    medals.add(1);
                }
            }
            for (TreeNode child: node.getChildren()) {
                nodes.add(child);
            }
        }
        List<String> results = new ArrayList<String>();
        int max = 1;
        for (int i = 0; i < medals.size(); i++) {
            if (medals.get(i) == max) {
                results.add(countries.get(i));
            } else if (medals.get(i) > max) {
                max = medals.get(i);
                results.clear();
                results.add(countries.get(i));
            }
        }
        Collections.sort(results);
        results.add(0, max + "");
        return results;
    }
    //get country with most gold medals
    public List<String> GetCountryWithMostGoldMedals() {
        List<String> countries = new ArrayList<String>();
        List<Integer> medals = new ArrayList<Integer>();
        List<TreeNode> nodes = new ArrayList<TreeNode>();
        nodes.add(root);
        while(nodes.size() > 0) {
            TreeNode node = nodes.remove(0);
            if (node.getValue().contains(":")) {
                String country = node.getValue().split(":")[1];
                if (countries.contains(country)) {
                    medals.set(countries.indexOf(country), medals.get(countries.indexOf(country)) + 1);
                } else {
                    countries.add(country);
                    medals.add(1);
                }
                nodes.remove(0);
                nodes.remove(0);
            }
            for (TreeNode child: node.getChildren()) {
                nodes.add(child);
            }
        }
        List<String> results = new ArrayList<String>();
        int max = 1;
        for (int i = 0; i < medals.size(); i++) {
            if (medals.get(i) == max) {
                results.add(countries.get(i));
            } else if (medals.get(i) > max) {
                max = medals.get(i);
                results.clear();
                results.add(countries.get(i));
            }
        }
        Collections.sort(results);
        results.add(0, max + "");
        return results;
    }
    //get sport and event by athlete
    public List<String> GetSportAndEventByAthlete(String athlete) {
        List<String> sportsAndEvents = new ArrayList<String>();
        List<TreeNode> nodes = new ArrayList<TreeNode>();
        nodes.add(root);
        while(nodes.size() > 0) {
            TreeNode node = nodes.remove(0);
            if (node.getValue().contains(":")) {
                if (node.getValue().split(":")[0].equals(athlete)) {
                    TreeNode event = node.getParent();
                    TreeNode sport = event.getParent();
                    sportsAndEvents.add(sport.getValue() + ":" + event.getValue());
                }
            }
            for (TreeNode child: node.getChildren()) {
                nodes.add(child);
            }
        }
        Collections.sort(sportsAndEvents);
        return sportsAndEvents;
    }
}
