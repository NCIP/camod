package gov.nih.nci.camod.webapp.taglib;

import java.io.IOException;
import java.io.Serializable;
import java.util.ResourceBundle;

import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.Tag;

/**
 * Custom tag for context sensitive help.
 */
public class ContextSensitiveHelpTag implements Tag, Serializable {

	private static final long serialVersionUID = 5618297483211863400L;

	private PageContext myPageContext = null;

	private Tag myParent = null;

	// Tag attributes
	private String myKey = null;

	private String myLabelName = null;

	private String myHref = null;

	private String myBundle = "ContextSensitiveHelp";

	public void setPageContext(PageContext inPageContext) {
		myPageContext = inPageContext;
	}

	public void setParent(Tag inParent) {
		myParent = inParent;
	}

	public Tag getParent() {
		return myParent;
	}

	/**
	 * Sets the key attribute. This is included in the tld file.
	 * 
	 * @jsp.attribute description="The key attribute used to look up the value
	 *                in the properties file"
	 * 
	 * required="true"
	 * 
	 * rtexprvalue="false"
	 */
	public void setKey(String inKey) {
		myKey = inKey;
	}

	public String getKey() {
		return myKey;
	}

	/**
	 * Sets the text attribute. This is included in the tld file.
	 * 
	 * @jsp.attribute description="The text the CS help will be for"
	 * 
	 * required="true"
	 * 
	 * rtexprvalue="false"
	 */
	public void setText(String inLabelName) {
		myLabelName = inLabelName;
	}

	public String getText() {
		return myLabelName;
	}

	public String getHref() {
		return myHref;
	}

	/**
	 * Sets the href attribute. This is included in the tld file.
	 * 
	 * @jsp.attribute description="Where to go when the text is clicked.
	 *                Currently not implemented"
	 * 
	 * required="false"
	 * 
	 * rtexprvalue="false"
	 */
	public void setHref(String inHref) {
		this.myHref = inHref;
	}

	public String getBundle() {
		return myBundle;
	}

	/**
	 * Sets the bundle attribute. This is included in the tld file.
	 * 
	 * @jsp.attribute description="What bundle to use for the key lookup.
	 *                Currently defaults to ContextSensitiveHelp.properties"
	 * 
	 * required="false"
	 * 
	 * rtexprvalue="false"
	 */
	public void setBundle(String inBundle) {
		this.myBundle = inBundle;
	}

	public int doStartTag() throws JspException {

		try {

			// Get the text
			ResourceBundle theBundle = ResourceBundle.getBundle(myBundle);
			String theText = theBundle.getString(myKey);

			// Process optional attributes
			String theHref = "";
			if (myHref != null) {
				theHref = "href=\"" + myHref + "\"";
			}

			myPageContext.getOut().write(
					"<a " + theHref + " onMouseOver=\"stm(" + theText
							+ ",Style[0])\" onMouseOut=\"htm();\">"
							+ myLabelName + "</a>");

		} catch (IOException e) {
			throw new JspTagException("An IOException occurred.");
		}

		return SKIP_BODY;
	}

	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}

	public void release() {
		myPageContext = null;
		myParent = null;
		myKey = null;
	}
}
