import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


public class plane {

	 
	public void setModel(String model) {
		this.model = model;
	}
	 

	public void setOrigin(String origin) {
		this.origin = origin;
	}
	 
	 
	
	public void setCl(String cl) {
		this.cl = cl;
	}
	 
	
	public void setRadar(Boolean radar) {
		this.radar = radar;
	}
	
	public void setLenght(Double lenght) {
		this.lenght = lenght;
	}
	
	public void setWidth(Double width) {
		this.width = width;
	}
	 
	public void setHeight(Double height) {
		this.height = height;
	}

	String model;
	String origin;
	String cl;
	Boolean radar;
	Double lenght;
	Double width;
	Double height;

	public String getModel() {
		return model;
	}

	public String getOrigin() {
		return origin;
	}

	public String getCl() {
		return cl;
	}

	public Boolean getRadar() {
		return radar;
	}

	public Double getLenght() {
		return lenght;
	}

	public Double getWidth() {
		return width;
	}

	public Double getHeight() {
		return height;
	}

	plane() {
	}

	plane(String model, String origin, String cl, Boolean radar, Double lenght,
			Double width, Double height) {
		this.model = model;
		this.origin = origin;
		this.cl = cl;
		this.radar = radar;
		this.lenght = lenght;
		this.width = width;
		this.height = height;

	}

	public String toString() {

		return "------------------------\nModel:" + model + "\nOrigin:"
				+ origin + "\nClass:" + cl + "\nRadar:" + radar + "\nParams:"
				+ lenght + ";" + height + ";" + width + "\n";
	}

}
