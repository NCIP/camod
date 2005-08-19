package gov.nih.nci.camod.webapp.taglib;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.Tag;
// TODO: Add comments
public class ContextSensitiveHelpTag implements Tag, Serializable {

	private static final long serialVersionUID = 5618297483211863400L;

	private PageContext myPageContext = null;

	private Tag myParent = null;

	// Tag attributes
	private String myKey = null;

	private String myLabelName = null;

	private String myHref = null;

	private String myBundle = null;

	public void setPageContext(PageContext inPageContext) {
		myPageContext = inPageContext;
	}

	public void setParent(Tag inParent) {
		myParent = inParent;
	}

	public Tag getParent() {
		return myParent;
	}

	public void setKey(String inKey) {
		myKey = inKey;
	}

	public String getKey() {
		return myKey;
	}

	public void setText(String inLabelName) {
		myLabelName = inLabelName;
	}

	public String getText() {
		return myLabelName;
	}

	public String getHref() {
		return myHref;
	}

	public void setHref(String inHref) {
		this.myHref = inHref;
	}

	public String getBundle() {
		return myBundle;
	}

	public void setBundle(String inBundle) {
		this.myBundle = inBundle;
	}

	public int doStartTag() throws JspException {

		try {

			// Process optional attributes
			String theHref = "";
			if (myHref != null) {
				theHref = "href=\"" + myHref + "\"";
			}

			myPageContext.getOut().write(
					"<a " + theHref + " onMouseOver=\"stm(Text['" + myKey
							+ "'],Style[0])\" onMouseOut=\"htm();\">" + myLabelName
							+ "</a>");

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
