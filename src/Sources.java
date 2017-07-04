import java.util.Arrays;

public class Sources {

	private Source[] sources;

	public void Sources() {

	}

	public String toString() {
		return "sources : " + Arrays.toString(this.sources);
	}

	public Source[] getSources() {
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
	
}
