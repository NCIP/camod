/**
 * 
 * $Id: ContextSensitiveHelpTag.java,v 1.8 2006-04-28 19:31:20 schroedn Exp $
 * 
 * $Log: not supported by cvs2svn $
 *
 */

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

	private String myImage = null;

	private String myLabelName = null;

	private String myHref = null;

	private String myMapId = null;

	private String myBundle = "ContextSensitiveHelp";

	private String myStyleClass = "style_0";

	private String myJavascriptKey = "help_javascript";

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
	 * Sets the Image attribute. This is included in the tld file.
	 * 
	 * @jsp.attribute description="The key attribute used to look up the value
	 *                in the properties file"
	 * 
	 * required="false"
	 * 
	 * rtexprvalue="false"
	 */
	public void setImage(String inKey) {
		myImage = inKey;
	}

	public String getImage() {
		return myImage;
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
		myHref = inHref;
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
		myBundle = inBundle;
	}

	public String getStyleClass() {
		return myStyleClass;
	}

	/**
	 * Sets the styleClass. This is included in the tld file.
	 * 
	 * @jsp.attribute description="What style to use for the popup. Currently
	 *                defaults to style_0"
	 * 
	 * required="false"
	 * 
	 * rtexprvalue="false"
	 */
	public void setStyleClass(String inStyleClass) {
		myStyleClass = inStyleClass;
	}

	public int doStartTag() throws JspException {

		try {
			String theHref = "";
			try {
				// Get the text
				ResourceBundle theBundle = ResourceBundle.getBundle(myBundle);

				// Process optional attributes

				if (myMapId != null) {

					String theMapId;
					try {
						theMapId = theBundle.getString(myMapId);
					} catch (Exception e) {
						theMapId = myMapId;
					}

					String theJavascript = theBundle.getString(myJavascriptKey);
					theHref = "href=\"" + theJavascript + theMapId + ")\"";
				} else if (myHref != null) {
					theHref = "href=\"" + myHref + "\"";
				}

				String theText = theBundle.getString(myKey);
				String theStyleClass = theBundle.getString(myStyleClass);

				if (myImage != null) {
					myPageContext.getOut().write(
							"<a " + theHref + " onMouseOver=\"stm(" + theText + "," + theStyleClass
									+ ")\" onMouseOut=\"htm();\"><img src=\"" + myImage + "\" border=\"0\"/>" + "</a>");

				} else {
					myPageContext.getOut().write(
							"<a " + theHref + " onMouseOver=\"stm(" + theText + "," + theStyleClass
									+ ")\" onMouseOut=\"htm();\">" + myLabelName + "</a>");
				}

			} catch (Exception e) {
				e.printStackTrace();

				// Can't get bundle. Ignore tooltip
				myPageContext.getOut().write("<a " + theHref + " \">" + myLabelName + "</a>");
			}

		} catch (IOException e) {
			throw new JspTagException("An IOException occurred.");
		} catch (Exception e) {
			throw new JspTagException("An unknown exception occurred.");
		}

		return SKIP_BODY;
	}

	/**
	 * @return Returns the mapId.
	 */
	public String getMapId() {
		return myMapId;
	}

	/**
	 * @param inMapId
	 *            The mapId to set.
	 */
	public void setMapId(String inMapId) {
		this.myMapId = inMapId;
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
