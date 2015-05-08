import org.jdom2.*;
import org.jdom2.output.*;
import org.jdom2.input.*;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

 class JDOM {


 public static void byJDOM(ArrayList<plane> planes, String fileName) {
  
  try {
	  		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	  		DocumentBuilder dBuilder;
	  		dBuilder = dbFactory.newDocumentBuilder();
	  		Node doc =  dBuilder.parse(new File(fileName));
	  		DOMBuilder domBuilder = new DOMBuilder();
	  
	  		org.jdom2.Document jdomDoc;
            jdomDoc = domBuilder.build((org.w3c.dom.Document) doc);
            Element root = jdomDoc.getRootElement();
            List<Element> empListElements = root.getChildren("plane");
            
            for (Element empElement : empListElements) {
             
                plane emp= new plane(empElement.getChildText("model"),
                		empElement.getChildText("origin"),
                		empElement.getChildText("class"),
                        Boolean.parseBoolean(empElement.getChildText("radar")),
                       5.5,
                       5.5, //?
                       5.5);
                
                System.out.println();
                planes.add(emp);
            }
           
            
        } catch (Exception e) {
            e.printStackTrace();
        }



 }
 

}