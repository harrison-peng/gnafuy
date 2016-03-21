package gnafuy;

import java.util.ArrayList;

public class Word {
	String value;
	ArrayList<String> partOfSpeech;
	ArrayList<String> meaning;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public ArrayList<String> getPartOfSpeech() {
		return partOfSpeech;
	}

	public void setPartOfSpeech(ArrayList<String> partOfSpeech) {
		this.partOfSpeech = partOfSpeech;
	}

	public ArrayList<String> getMeaning() {
		return meaning;
	}

	public void setMeaning(ArrayList<String> meaningList) {
		this.meaning = meaningList;
	}

}
