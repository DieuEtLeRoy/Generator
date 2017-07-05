import java.util.ArrayList;

public class Citations {

	private ArrayList<Citation> citations;

	public void Citations() {

	}

	public String toString() {
		return "citations : " + this.citations.toString();
	}

	public ArrayList<Citation> getCitations() {
		return citations;

	}

	public ArrayList<Citation> getCitationsBySourceId(int id) {
		ArrayList<Citation> cs = new ArrayList<Citation>();
		for(Citation c : this.citations){
			if(c.getSourceId() == id){
				cs.add(c);
			}
		}
		return (cs);
	}

}
