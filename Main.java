package gnafuy;

import java.io.IOException;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		YahooDictionary yahooDictionary = new YahooDictionary();

		Scanner sc = new Scanner(System.in);
		String keyword = sc.next();

		yahooDictionary.connect(keyword);
		sc.close();
	}

}
