import java.util.ArrayList;

public class Task {
	private ArrayList<String> taskid=new ArrayList<String>();
	private ArrayList <String> taskname = new ArrayList<String>();
	
	public void setTaskID(String id) {
		taskid.add(id);
	}
	
	public void setTaskName(String name) {
		taskname.add(name);
	}
	
	public ArrayList<String> getTaskID() {
		return taskid;
	}
	
	public ArrayList<String> getTaskName(){
		return taskname;
	}
	
	public void clear() {
		taskid.clear();
		taskname.clear();
	}
	
	public String searchIDbyName(String name) {
		int i = taskname.indexOf(name);
		return taskid.get(i);
	}
}
