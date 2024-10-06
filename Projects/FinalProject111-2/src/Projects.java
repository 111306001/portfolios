import java.util.ArrayList;

public class Projects {
	private ArrayList<String> projects;

	
	
	public Projects() {
		projects= new ArrayList<String>();
	}
	
	
	public void add(String pName) {
		projects.add(pName);
	}
	
	
	public ArrayList<String> getProjects(){
		return projects;
	}
	
	public boolean checkExist(String newProject) { //用於確認是否有同名schedule
		Boolean exist=false;
		for(String sc : projects) {
			if (sc.equals(newProject)){
				exist=true;
			}
		}
		return exist;
	}
	

	}
	
	


