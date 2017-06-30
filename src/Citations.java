import java.util.Arrays;

public class Citations {

	private Citation[] citations;

	public void Citations() {

	}

	public String toString() {
		return "citations : " + Arrays.toString(this.citations);
	}

	public Citation[] getCitations() {
		return citations;

	}
}
