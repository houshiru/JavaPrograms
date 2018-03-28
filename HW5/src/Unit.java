
public class Unit {//for a single presentation of a pair of activity's time and detail
	String time;
	String content;
	public Unit(String t, String c){
		this.time = t;
		this.content = c;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}	
	
	public boolean isLaterthan(Unit u){
		if(time.compareTo(u.time) > 0)
			return true;
		else
			return false;
	}
	public String toString(){
		return this.time + ":" + this.content;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(((Unit)obj).time.compareToIgnoreCase(time) == 0)
			return true;
		else
			return false;
	}
}