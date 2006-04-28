/**
 * $Id: 
 * 
 * $Log: 
 *  
 */

package gov.nih.nci.camod.webapp.taglib;

import gov.nih.nci.camod.Constants;

import java.io.IOException;
import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTag;
import javax.servlet.jsp.tagext.Tag;

public class HighlightTextTag implements Tag, BodyTag, Serializable {
    
    private static final long serialVersionUID = 5618297483211863400L;
    private PageContext myPageContext = null;
    private BodyContent myBodyContent;
    private Tag myParent = null;
    private String myKeyword;
    
    //private final int NOT_STARTED = 99999999;
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
     * Sets the keyword attribute. This is included in the tld file.
     * 
     * @jsp.attribute description="The keyword attribute is used to determine which words of the text to highlight
     * 
     * required="true"
     * rtexprvalue="false"
     */
    public void setKeyword(String inKeyword) {
        try {            
            myKeyword = inKeyword;                        
            System.out.println( "HighlightTextTag.java: myKeyword=>>" + myKeyword +"<<");            
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
            
            myKeyword = (String) myPageContext.getSession().getAttribute( Constants.KEYWORD_HIGHLIGHT );
            //System.out.println( "HighlightTextTag.java: myKeyword=>>" + myKeyword +"<<");
            
            try {     

                if( !myKeyword.equals( "" ) && !myKeyword.equals( " " ) && myKeyword != null )  
                {                   
                    // Create a pattern to match cat
                    Pattern p = Pattern.compile("(?i)("+ myKeyword + ")");
                    
                    // Create a matcher with an input string
                    Matcher m = p.matcher( theBody );
                    
                    StringBuffer sb = new StringBuffer();
                    
                    boolean result = m.find();
                    
                    // Loop through and create a new String 
                    // with the replacements
                    while(result) {
                        m.appendReplacement(sb, "<font class='keywordHighlight'>$1</font>");
                        result = m.find();
                    }
                    
                    // Add the last segment of input to 
                    // the new String
                    m.appendTail(sb);
                    
                    theBody = sb.toString();                
                }
                
            } catch (Exception e) {
                out.println(theBody);
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