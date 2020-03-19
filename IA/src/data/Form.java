package data;


/**
 * The class Form is composed of any figure
 * 
 * 
 * @author gimardthibault, Nathan VIRAYIE
 */
public class Form{
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
	 * @param formType forme à placer (carré,triangle etc)
	 * 
	 * @see Form#formType
	 */
	public Form(String formType) {
		this.formType = formType;
	}

	/**
	 * Get the type of the actual form
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

	public <E> E accept(ElementVisitor<E> elem) {
		return elem.visit(this);
	}
}
