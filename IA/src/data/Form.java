package data;

/**
 * This class Form extends the class Element
 * 
 * The class Form is composed of any figure
 * 
 * @author gimardthibault
 *
 */
public class Form extends Element{
	/**
	 * The form's type
	 * 
	 * @see Form#Form
	 * @see Form#getFormType()
	 * @see Form#setFormType(String)
	 */
	private String formType;
	
	/**
	 * The constructor's class
	 * 
	 * @param coordX
	 * @param coordY
	 * @param formType
	 * 
	 * @see Form#formType
	 */
	public Form(int coordX, int coordY, String formType) {
		super(coordX, coordY);
		this.formType = formType;
	}

	/**
	 * Get the type of the actual forme
	 * 
	 * @return the formType of the class Form
	 */
	public String getFormType() {
		return formType;
	}

	/**
	 * Update the formType
	 * 
	 * @param formType
	 */
	public void setFormType(String formType) {
		this.formType = formType;
	}
	

}
