import java.util.ArrayList;

public class Sources {

	private ArrayList<Source> sources;

	public void Sources() {

	}

	public String toString() {
		return "sources : " + this.sources.toString();
	}

	public ArrayList<Source> getSources() {
		return sources;

	}
	
	public Source getById(int id){
		for(Source o : this.sources){
			if(o.getId() == id){
				return o;
			}
		}
		return (new Source());
	}

	
	public ArrayList<Source> getSourcesByAuteurId(int id) {
		ArrayList<Source> ss = new ArrayList<Source>();
		for(Source s : this.sources){
			if(s.getAuteurId() == id){
				ss.add(s);
			}
		}
		return (ss);
	}

	public void init() {
		for(Source s : this.sources){
			s.init();
		}
		
	}
	
}
