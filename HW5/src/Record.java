
public class Record {//to store the information from input
	String Operation = "";//first element"add activity etc"
	String Detail = "";//third element 
	int month;
	int day;
	int hour;
	int minute;
	String time = "";//second element, time or date
	public Record(){};
	public Record(String oper, String time, String detail){
		this.Operation = oper;
		this.Detail = detail;
		if(time.length() == 0)
		{
			this.month = 0;
			this.day = 0;
		}
		else{
			this.month = 10 * (time.charAt(0) - '0') + time.charAt(1) - '0';
			this.day = 10 * (time.charAt(2) - '0') + time.charAt(3) - '0';
		}
		if(time.length() <= 4){
			this.hour = 0;
			this.minute = 0;
		}else{
			this.hour = 10 * (time.charAt(4) - '0') + time.charAt(5) - '0';
			this.minute = 10 * (time.charAt(6) - '0') + time.charAt(7) - '0';
		}
		this.time = time;
	}	
	public String getOperation() {
		return Operation;
	}
	public void setOperation(String operation) {
		Operation = operation;
	}
	public String getDetail() {
		return Detail;
	}
	public void setDetail(String detail) {
		Detail = detail;
	}
	public String getTime(){
		return time;
	}
	public void setTime(String time){
		this.month = 10 * (time.charAt(0) - '0') + time.charAt(1) - '0';
		this.day = 10 * (time.charAt(2) - '0') + time.charAt(3) - '0';
		if(time.length() == 4){
			this.hour = 0;
			this.minute = 0;
		}else{
		this.hour = 10 * (time.charAt(4) - '0') + time.charAt(5) - '0';
		this.minute = 10 * (time.charAt(6) - '0') + time.charAt(7) - '0';
		}
	}
	
	public Boolean isLaterthan(Record r){
		Boolean set = false;
		if(this.month > r.month)
			set = true;
		else if(this.month == r.month && this.day > r.day)
			set = true;
		else if(this.month == r.month && this.day == r.day && this.hour > r.hour)
			set = true;
		else if(this.month == r.month && this.day == r.day && this.hour == r.hour && this.minute > r.minute)
			set = true;
		return set;
	}
	
	public String toString(){
		return Operation +" " + time + " " + Detail + "\n";
	}
}
