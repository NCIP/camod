package gov.nih.nci.camod.webapp.servlet;

import gov.nih.nci.common.persistence.Search;

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.*;

public class RedirectOldRequestsServlet extends HttpServlet {

    private static final long serialVersionUID = 3257296453788404151L;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String theRedirectUrl = request.getContextPath() + "/searchSimple.do";

        // Get the modelId attribute. If it's not null, we will map it to
        // the new ID and
        // forward to the new view page, otherwise we simply send them to
        // the search page.
        // to the model id page.
        String theModelId = request.getParameter("modelId");

        if (theModelId != null) {

            ResultSet theResultSet = null;
            try {

                String theSQLString = "select abs_cancer_model_id from abs_cancer_model_tmp where modeluid = ?";

                Object[] theParams = new Object[1];
                theParams[0] = theModelId;

                theResultSet = Search.query(theSQLString, theParams);

                if (theResultSet.next()) {
                    long theNewModelId = theResultSet.getLong(1);

                    theRedirectUrl = request.getContextPath() + "/ViewModelAction.do?aModelID="
                            + Long.toString(theNewModelId) + "&unprotected_method=populateModelCharacteristics";
                }

            } catch (Exception e) {
                // Not able to do anything

            } finally {
                if (theResultSet != null) {
                    try {
                        theResultSet.close();
                    } catch (Exception e) {
                    }
                }
            }
        }

        response.sendRedirect(theRedirectUrl);

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
