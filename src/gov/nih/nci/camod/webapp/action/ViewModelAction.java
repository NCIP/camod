package gov.nih.nci.camod.webapp.action;

import java.util.ArrayList;
import java.util.List;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.domain.EngineeredGene;
import gov.nih.nci.camod.domain.GenomicSegment;
import gov.nih.nci.camod.domain.InducedMutation;
import gov.nih.nci.camod.domain.TargetedModification;
import gov.nih.nci.camod.domain.Transgene;
import gov.nih.nci.camod.service.AnimalModelManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.MappingDispatchAction;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class ViewModelAction extends MappingDispatchAction {
    private static ApplicationContext ctx = null;

	/**
	 * sets the cancer model object in the session 
	 * @param request the httpRequest
	 */	
	private void setCancerModel(HttpServletRequest request) {
		String modelID = request.getParameter("aModelID");
		System.out.println("<setCancerModel> modelID" + modelID);
		AnimalModelManager animalModelManager = (AnimalModelManager) getBean("animalModelManager");
		AnimalModel am = animalModelManager.get(modelID);
		request.getSession().setAttribute(Constants.ANIMALMODEL, am);
	}

	/**
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward populateModelCharacteristics(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		setCancerModel(request);
		return mapping.findForward("viewModelCharacteristics");
	}

	/**
	 * 
	 */
	public ActionForward populateEngineeredGene(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		System.out.println("<populateEngineeredGene> modelID"
				+ request.getParameter("aModelID"));
		String modelID = request.getParameter("aModelID");

		AnimalModelManager animalModelManager = (AnimalModelManager) getBean("animalModelManager");
		AnimalModel am = animalModelManager.get(modelID);

		final List egc = am.getEngineeredGeneCollection();
		final int egcCnt = (egc != null)?egc.size():0;
		final List tgc = new ArrayList(); int tgCnt = 0;//Transgene
		final List gsc = new ArrayList(); int gsCnt = 0;//GenomicSegment
		final List tmc = new ArrayList(); int tmCnt = 0;//TargetedModification 
		final List imc = new ArrayList(); int imCnt = 0;//InducedMutation 
		for(int i=0; i<egcCnt; i++) {
			EngineeredGene eg = (EngineeredGene)egc.get(i);
			if ( eg instanceof Transgene ) {
				tgc.add(eg); tgCnt++;
			} else if (eg instanceof GenomicSegment ){
				gsc.add(eg); gsCnt++;
			} else if (eg instanceof TargetedModification ){
				tmc.add(eg); tmCnt++;
			} else if (eg instanceof InducedMutation ){
				imc.add(eg); imCnt++;
			}
		}
		System.out.println("<populateEngineeredGene> " +
					"egcCnt=" + egcCnt +
					"tgc=" + tgCnt +
					"gsc=" + gsCnt +
					"tmc=" + tmCnt +
					"imc=" + imCnt);
		request.getSession().setAttribute(Constants.ANIMALMODEL, am);
		request.getSession().setAttribute(Constants.TRANSGENE_COLL, tgc);
		request.getSession().setAttribute(Constants.GENOMIC_SEG_COLL, gsc);
		request.getSession().setAttribute(Constants.TARGETED_MOD_COLL, tmc);
		request.getSession().setAttribute(Constants.INDUCED_MUT_COLL, imc);
		System.out.println("<populateEngineeredGene> set attributes done.");

		return mapping.findForward("viewGeneticDescription");
	}
	/**
	 * Populate the session and/or request with the objects necessary to display 
	 * the page.
	 * 
	 * @param mapping the struts action mapping
	 * @param form the web form
	 * @param request HTTPRequest
	 * @param response HTTPResponse
	 * @return
	 * @throws Exception
	 */
	public ActionForward populateCarcinogenicInterventions(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		setCancerModel(request);
		return mapping.findForward("viewCarcinogenicInterventions");
	}

	/**
	 * Populate the session and/or request with the objects necessary to display 
	 * the page.
	 * 
	 * @param mapping the struts action mapping
	 * @param form the web form
	 * @param request HTTPRequest
	 * @param response HTTPResponse
	 * @return
	 * @throws Exception
	 */
	public ActionForward populatePublications(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		setCancerModel(request);
		return mapping.findForward("viewPublications");
	}

	/**
	 * Populate the session and/or request with the objects necessary to display 
	 * the page.
	 * 
	 * @param mapping the struts action mapping
	 * @param form the web form
	 * @param request HTTPRequest
	 * @param response HTTPResponse
	 * @return
	 * @throws Exception
	 */
	public ActionForward populateHistopathology(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		setCancerModel(request);
		return mapping.findForward("viewHistopathology");
	}

	/**
	 * Populate the session and/or request with the objects necessary to display 
	 * the page.
	 * 
	 * @param mapping the struts action mapping
	 * @param form the web form
	 * @param request HTTPRequest
	 * @param response HTTPResponse
	 * @return
	 * @throws Exception
	 */
	public ActionForward populateTherapeuticApproaches(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		setCancerModel(request);
		return mapping.findForward("viewTherapeuticApproaches");
	}

	/**
	 * Populate the session and/or request with the objects necessary to display 
	 * the page.
	 * 
	 * @param mapping the struts action mapping
	 * @param form the web form
	 * @param request HTTPRequest
	 * @param response HTTPResponse
	 * @return
	 * @throws Exception
	 */
	public ActionForward populateCellLines(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		setCancerModel(request);
		return mapping.findForward("viewCellLines");
	}
	/**
	 * Populate the session and/or request with the objects necessary to display 
	 * the page.
	 * 
	 * @param mapping the struts action mapping
	 * @param form the web form
	 * @param request HTTPRequest
	 * @param response HTTPResponse
	 * @return
	 * @throws Exception
	 */
	public ActionForward populateImages(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		setCancerModel(request);
		return mapping.findForward("viewImages");
	}
	
	/**
	 * Populate the session and/or request with the objects necessary to display 
	 * the page.
	 * 
	 * @param mapping the struts action mapping
	 * @param form the web form
	 * @param request HTTPRequest
	 * @param response HTTPResponse
	 * @return
	 * @throws Exception
	 */
	public ActionForward populateMicroarrays(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		setCancerModel(request);
		return mapping.findForward("viewMicroarrays");
	}
	
	/**
	 * Populate the session and/or request with the objects necessary to display 
	 * the page.
	 * 
	 * @param mapping the struts action mapping
	 * @param form the web form
	 * @param request HTTPRequest
	 * @param response HTTPResponse
	 * @return
	 * @throws Exception
	 */
	public ActionForward populateTransplantXenograft(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		setCancerModel(request);
		return mapping.findForward("viewTransplantXenograft");
	}

	/**
     * Convenience method to bind objects in Actions
     *
     * @param name
     * @return
     */
    public Object getBean(String name) {
        if (ctx == null) {
            ctx = WebApplicationContextUtils
                    .getRequiredWebApplicationContext(servlet.getServletContext());
        }
        return ctx.getBean(name);
    }
}
