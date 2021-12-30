package framework;

import test.framework.reader.FileReader;
import test.framework.reader.FileUtils;

public class ReaderTest {

    public static void main(String[] args) {

        String fileContent = FileUtils.readFile("SomeFile.txt");

        String moreContent = new FileReader().readFile("SomeFile.txt");

    }


}
