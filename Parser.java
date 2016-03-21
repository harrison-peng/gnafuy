package gnafuy;

import java.io.IOException;

public interface Parser {
	public String connect(String input) throws IOException;

	public void parse(String retVal) throws IOException;

}
