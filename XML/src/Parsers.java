import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class Parsers {


	
	public static void byDom(String path,ArrayList<plane> planes){
		
		try
        {
            File xmlFile = new File(path);
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(xmlFile);
            document.getDocumentElement().normalize();


            NodeList nodeList = document.getElementsByTagName(document.getDocumentElement().getChildNodes().item(1).getNodeName());

            plane temp;
            for(int tmp = 0; tmp < nodeList.getLength(); tmp++)
            {
                Node node = nodeList.item(tmp);
                if(node.getNodeType() == Node.ELEMENT_NODE)
                {
                    Element element = (Element)node;
                  temp = new plane(element.getElementsByTagName("model").item(0).getChildNodes().item(0).getNodeValue(),
                            element.getElementsByTagName("origin").item(0).getChildNodes().item(0).getNodeValue(),
                            element.getElementsByTagName("class").item(0).getChildNodes().item(0).getNodeValue(),
                            Boolean.parseBoolean(element.getElementsByTagName("radar").item(0).getChildNodes().item(0).getNodeValue()),
                           Double.parseDouble(element.getElementsByTagName("length").item(0).getChildNodes().item(0).getNodeValue()),
                           Double.parseDouble(element.getElementsByTagName("width").item(0).getChildNodes().item(0).getNodeValue()),
                           Double.parseDouble(element.getElementsByTagName("height").item(0).getChildNodes().item(0).getNodeValue()) );
                
                                   
               planes.add(temp);

                }
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getLocalizedMessage());
            e.printStackTrace();
        }
	}
	/*
	public static void byJaxb(String path,ArrayList<plane> planes){
		

		try {
			
			@XmlRootElement(name = "plane")
 			class pplane{
				
				String model;
				@XmlElement
				public void setWidth(String model) {
					this.model = model;
				}
				
				public String toString(){
					return model;
				}
							
				
			}
			
			File file = new File(path);
			//File file = new File("jaxb.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(pplane.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			pplane p =  (pplane) jaxbUnmarshaller.unmarshal(file);
			System.out.println(p);
			
			
		} catch (JAXBException jaxbe) {
			System.out.println(jaxbe.getLocalizedMessage());
			jaxbe.printStackTrace();
		}
		
	}*/
	
	public static void bySax(String path,ArrayList<plane> planes){
		
		try
        {
            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
            SAXParser saxParser = saxParserFactory.newSAXParser();
 
            DefaultHandler defaultHandler = new DefaultHandler()
            {
                       
            
                boolean bmodel = false;
                boolean borigin = false;
                boolean bclas = false;
                boolean bradar   = false;           
                boolean blength = false;
                boolean bwidth = false;
                boolean bheight = false;
                
                
                
                String tmodel;
                String torigin;
                String tclas;
                Boolean tradar;           
                Double tlength;
                Double twidth;
                Double theight;
 
                public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException{
                    if(qName.equalsIgnoreCase("model")){
                        bmodel = true;
                    }
                    
                    if(qName.equalsIgnoreCase("origin")){
                        borigin = true;
                    }
                    
                    if(qName.equalsIgnoreCase("clas")){
                        bclas = true;
                    }
                    
                    if(qName.equalsIgnoreCase("radar")){
                        bradar = true;
                    }
                    
                    if(qName.equalsIgnoreCase("lenght")){
                        blength = true;
                    }
                    
                    if(qName.equalsIgnoreCase("width")){
                        bwidth = true;
                    }
                    
                    if(qName.equalsIgnoreCase("height")){
                        bheight = true;
                    }
                }
 
                public void endElement(String uri, String localName, String qName) throws SAXException{
                  
                	 if(qName.equalsIgnoreCase("plane")){
                         
                		 planes.add(new plane(tmodel,  torigin,   tclas,    tradar,  tlength,  twidth, theight));
                		 
                		  }
                }
 
                public void characters(char ch[], int start, int length) throws SAXException{
                    if(bmodel){
                        tmodel = new String(ch, start, length);
                        bmodel = false;
                    }
                    
                    if(borigin){
                        torigin =  new String(ch, start, length);
                        borigin = false;
                    }
                    if(bclas){
                        tclas =  new String(ch, start, length);
                        bclas = false;
                    }
                    if(bradar){
                        tradar = Boolean.parseBoolean(new String(ch, start, length));
                       
                        bradar = false;
                    }
                    if(bwidth){
                    	twidth= Double.parseDouble(new String(ch, start, length));
                    	
                        bwidth = false;
                    }
                    
                    if(blength){
                        tlength =  Double.parseDouble(new String(ch, start, length));
                        blength = false;
                    }
                    if(bheight){
                        theight = Double.parseDouble(new String(ch, start, length));
                        bheight = false;
                    }
                  
                    
                   
                }
            };
 
            saxParser.parse(path, defaultHandler);
        }
        catch (Exception ex)
        {
            System.out.println(ex.getLocalizedMessage());
            ex.printStackTrace();
        }

		
		
    }

	
	public static void toJson(String path,ArrayList<plane> planes){
		 try(FileWriter writer = new FileWriter(path)){
			 
			 JSONObject main = new JSONObject();
			 JSONArray ar = new JSONArray();
			 
			for(int i=0;i<planes.size();i++){
				JSONObject obj = new JSONObject();
				obj.put("id",Integer.toString(i));
				obj.put("model",planes.get(i).getModel());
				obj.put("origin",planes.get(i).getOrigin());
				
				JSONObject chars=new JSONObject();
				chars.put("radar",planes.get(i).getRadar().toString());
				chars.put("class",planes.get(i).getCl());
				obj.put("chars",chars);
				
				JSONObject pars=new JSONObject();
				pars.put("width",planes.get(i).getWidth().toString());
				pars.put("lenght",planes.get(i).getLenght().toString());
				pars.put("height",planes.get(i).getHeight().toString());
				obj.put("parameters",pars);
				
				ar.add(obj);
	     
			
		 }
			main.put("plane", ar);
			writer.write(main.toString());
		 }
			catch(Exception e){
				System.out.println("Vse ploho");
			}

	    
	}
	
	public static void fromJson(String path,ArrayList<plane> planes){

		try {
			plane temp;
			String tmodel;
            String torigin;
            String tclas;
            Boolean tradar;           
            Double tlength;
            Double twidth;
            Double theight;
			
			FileReader reader = new FileReader(path);

			JSONParser jsonParser = new JSONParser();

			JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
			
			JSONArray pl = (JSONArray) jsonObject.get("plane");
		
			for (int i = 0; i < pl.size(); i++) {

				JSONObject innerObj = (JSONObject) pl.get(i);
				
				tmodel=(String) innerObj.get("model");
				torigin=(String) innerObj.get("origin");
				
				JSONObject c = (JSONObject) innerObj.get("chars");
				
				tclas=(String) c.get("class");
				
				tradar = Boolean.parseBoolean((String) c.get("radar"));
				
				JSONObject p = (JSONObject) innerObj.get("parameters");
				
				tlength = Double.parseDouble((String)p.get("length"));
				twidth = Double.parseDouble((String)p.get("width"));
				theight = Double.parseDouble((String)p.get("height"));
				
			
				planes.add(new plane(tmodel,  torigin,   tclas,    tradar,  tlength,  twidth, theight));
			}

		
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		
	}
	
    
	
	
	
	
}
