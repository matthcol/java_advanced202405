package xmldemo;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import java.io.File;
public class Main {
    public static void main(String[] args) throws Exception {
        DocumentBuilder parser = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document document = parser.parse(new File("movie.xml"));
        System.out.println(document);
        System.out.println(document.getDocumentElement());

    }
}
