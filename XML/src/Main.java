import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.swing.text.html.HTMLDocument.Iterator;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;








import javax.xml.stream.*;

import java.util.ArrayList;




public class Main {
	
	
	
	
	public static void main(String[] args)
    {
    	String path="C:\\Users\\Alexander\\git\\Parse\\XML\\src\\One.xml";     		//после залива на гит не работало по другому 
    	String path2="C:\\Users\\Alexander\\git\\Parse\\XML\\src\\Copy of One.xml";//- извиняюсь за хардкодинг
    	String jpath="C:\\Users\\Alexander\\git\\Parse\\XML\\src\\One.json";
    	String jpathTO="C:\\Users\\Alexander\\git\\Parse\\XML\\src\\Two.json";
       ArrayList<plane> planes = new ArrayList<plane>();
       ArrayList<plane> planes2 = new ArrayList<plane>();


       Parsers.bySax(path,planes2);
        
        System.out.println(planes2);

    //  System.out.println(planes2);
    	
  /*  	fromJson(jpath,planes);
    	
    	System.out.println(planes);
    	
    	bySax(path,planes2);
    	
    	System.out.println(planes2);
    	
    	toJson(jpathTO,planes);
    	
    	*/
    	
      
    }
}
