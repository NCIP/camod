/**
 * $Id: ShortenTextTag.java,v 1.2 2005-11-28 17:56:39 georgeda Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.1  2005/11/28 13:52:03  georgeda
 * Defect #193.  Initial revision
 *
 * 
 */
package gov.nih.nci.camod.webapp.taglib;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTag;
import javax.servlet.jsp.tagext.Tag;

/**
 * Custom tag for handling text in the left menu bar
 */
public class ShortenTextTag implements BodyTag, Serializable {

	private static final long serialVersionUID = 5618297483211863400L;

	private BodyContent myBodyContent;
	private Tag myParent = null;
	private int myLength = 25;

	public void setPageContext(PageContext inPageContext) {
	}

	public void setParent(Tag inParent) {
		myParent = inParent;
	}

	public Tag getParent() {
		return myParent;
	}

	/**
	 * Sets the length attribute. This is included in the tld file.
	 * 
	 * @jsp.attribute description="The length attribute is used to determine how
	 *                much of the text to display"
	 * 
	 * required="false"
	 * 
	 * rtexprvalue="false"
	 */
	public void setLength(String inLength) {
		try {
			myLength = Integer.parseInt(inLength);
		} catch (Exception e) {
			// ignore
		}
	}

	public void setBodyContent(BodyContent inBodyContent) {
		myBodyContent = inBodyContent;
	}

	public int doStartTag() throws JspException {
		return EVAL_BODY_BUFFERED;
	}

	public int doAfterBody() throws JspTagException {

		try {
			// Make sure we put anything in the output stream in the
			// body to the output stream of the JSP
			JspWriter out = myBodyContent.getEnclosingWriter();
			String theBody = myBodyContent.getString();
			if (theBody.length() > myLength) {
				String theAnchor = "<span onMouseOver=\"stm(['', '"
						+ theBody
						+ "'],['white','black','#475b82','#E3E3EB','','','','','','','center','','','',200,'',2,2,10,10,'','','','',''])\" onMouseOut=\"htm();\">";
				theBody = theAnchor + theBody.substring(0, myLength) + "..." + "</span>";
			}
			out.println(theBody);
		} catch (IOException ioe) {
			throw new JspTagException("Error in ShortenTextTag tag doAfterBody " + ioe);
		}

		return SKIP_BODY;
	}

	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}

	public void doInitBody() {
	}

	public void release() {
		myParent = null;
	}
}
