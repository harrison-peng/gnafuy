package gnafuy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class YahooDictionary implements Parser {
	Word resultOfWord = new Word();
	ArrayList<String> pOSList = new ArrayList<>();
	ArrayList<String> meaningList = new ArrayList<>();

	@Override
	public String connect(String input) throws IOException {
		URL url = new URL("http://tw.dictionary.search.yahoo.com/search?p=" + input + "&fr2=dict");
		URLConnection conn = url.openConnection();
		conn.setRequestProperty("user-agent", "Chrome/7.0.517.44");

		InputStream in = conn.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(in, "utf-8"));

		String retVal = "";

		String line = null;
		while ((line = br.readLine()) != null) {
			retVal = retVal + line + "\n";
		}
		parse(retVal);
		return retVal;
	}

	@Override
	public void parse(String retVal) throws IOException {

		Document doc = Jsoup.parse(retVal);

		if (doc.select("#term").hasText()) {

			String value = doc.select("#term").text();

			resultOfWord.setValue(value);
			System.out.println(resultOfWord.getValue());

			Elements numOfClasses = doc.select("span.fz-s.mb-10");
			for (int i = 0; i < numOfClasses.size(); i++) {

				/**
				 * String partOfSpeech =
				 * doc.select("span.fz-s.mb-10").get(i).text();
				 * pOSList.add(partOfSpeech);
				 * resultOfWord.setPartOfSpeech(pOSList);
				 **/

				/**
				 * String meaning =
				 * doc.select("ul.compArticleList.mb-15.ml-10").get(i).text();
				 * meaningList.add(meaning);
				 * resultOfWord.setMeaning(meaningList);
				 **/

				String partOfSpeech = doc.select("span.fz-s.mb-10").get(i).text();
				meaningList.add(partOfSpeech);

				Elements numOfMeaning = doc.select("ul.compArticleList.mb-15.ml-10").get(i).select("h4");
				for (int j = 0; j < numOfMeaning.size(); j++) {

					String meaning = doc.select("ul.compArticleList.mb-15.ml-10").get(i).select("h4").get(j).text()
							+ "\n" + doc.select("ul.compArticleList.mb-15.ml-10").get(i).select("li").get(j)
									.select("#example").text();
					meaningList.add(meaning);

				}
				resultOfWord.setMeaning(meaningList);

			}

			/**
			 * for (int i = 0; i < numOfClasses.size(); i++) {
			 * System.out.println(resultOfWord.getPartOfSpeech().get(i));
			 * System.out.println(resultOfWord.getMeaning().get(i)); }
			 **/

			for (int i = 0; i < meaningList.size(); i++) {
				System.out.println(resultOfWord.getMeaning().get(i));
			}

		} else {
			System.out.println("Not Found");
		}
	}

}
