package gov.nih.nci.camod.webapp.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gov.nih.nci.camod.Constants;

import gov.nih.nci.camod.service.AnimalModelManager;
import gov.nih.nci.camod.domain.AnimalModel;

public class ViewModelAction extends BaseAction {

	/**
	 * called from SubmitModels.jsp from list of models links
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		System.out.println("<ViewModelAction> modelID"
				+ request.getParameter("aModelID"));
		String modelID = request.getParameter("aModelID");

		AnimalModelManager animalModelManager = (AnimalModelManager) getBean("animalModelManager");
		AnimalModel am = animalModelManager.get(modelID);

		request.getSession().setAttribute(Constants.ANIMALMODEL, am);
		return mapping.findForward("viewModel");
	}
}
