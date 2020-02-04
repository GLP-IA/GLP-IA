package data;

public class Form extends Element{
	private String formType;

	public Form(int coordX, int coordY, String formType) {
		super(coordX, coordY);
		this.formType = formType;
	}

	public String getFormType() {
		return formType;
	}

	public void setFormType(String formType) {
		this.formType = formType;
	}
	

}
