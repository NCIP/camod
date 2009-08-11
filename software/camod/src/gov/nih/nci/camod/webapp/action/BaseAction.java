package gov.nih.nci.camod.webapp.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.MappingDispatchAction;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;


/**
 * Implementation of <strong>Action</strong> that contains base methods for
 * logging and convience methods. This class is intended to
 * be a base class for all Struts actions.
 * <p/>
 * <a href="BaseAction.java.html"><i>View Source</i></a>
 * </p>
 */
public class BaseAction extends MappingDispatchAction
{

    protected transient final Log log = LogFactory.getLog(getClass());
    private static ApplicationContext ctx = null;

    /**
     * Convenience method to bind objects in Actions
     *
     * @param name
     * @return
     */
    public Object getBean(String name)
    {
        if (ctx == null)
        {
            ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(servlet.getServletContext());
        }
        return ctx.getBean(name);
    }

    /**
     * Provides the mapping from resource key to method name
     *
     * @return Resource key / method name map
     */
    public Map<Integer, String> getKeyMethodMap()
    {
        return new HashMap<Integer, String>();
    }

    /**
     * Override the execute method in LookupDispatchAction to parse
     * URLs and forward to methods without parameters.  Also will
     * forward to unspecified method when no parameter is present.
     *
     * @param mapping  The ActionMapping used to select this instance
     * @param request  The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @param form     The optional ActionForm bean for this request (if any)
     * @return Describes where and how control should be forwarded.
     * @throws Exception if an error occurs
     */
    public ActionForward execute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) throws Exception
    {
        // Identify the request parameter containing the method name
        String theParameter = mapping.getParameter();

        String theMethodName = null;
        if (theParameter != null && theParameter.length() > 0) {
            theMethodName = request.getParameter(theParameter);
            return dispatchMethod(mapping, form, request, response, theMethodName);
        } 
        else 
        {
            return this.unspecified(mapping, form, request, response);
        }
    }
}
