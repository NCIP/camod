/**
 *  @author sguruswami
 *  
 *  $Id: ViewModelAction.java,v 1.4 2005-09-16 15:52:56 georgeda Exp $
 *  
 *  $Log: not supported by cvs2svn $
 *  
 */
package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.cabio.domain.impl.AgentImpl;
import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.*;
import gov.nih.nci.camod.service.AnimalModelManager;
import gov.nih.nci.system.applicationservice.ApplicationService;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.*;

public class ViewModelAction extends BaseAction {

    /**
     * sets the cancer model object in the session
     * 
     * @param request
     *            the httpRequest
     */
    private void setCancerModel(HttpServletRequest request) {
        String modelID = request.getParameter("aModelID");
        System.out.println("<setCancerModel> modelID" + modelID);
        AnimalModelManager animalModelManager = (AnimalModelManager) getBean("animalModelManager");

        try {
            AnimalModel am = animalModelManager.get(modelID);
            request.getSession().setAttribute(Constants.ANIMALMODEL, am);
        } catch (Exception e) {
            // TODO: Fix
        }

    }

    /**
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward populateModelCharacteristics(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        setCancerModel(request);
        return mapping.findForward("viewModelCharacteristics");
    }

    /**
     * 
     */
    public ActionForward populateEngineeredGene(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        System.out.println("<populateEngineeredGene> modelID" + request.getParameter("aModelID"));
        String modelID = request.getParameter("aModelID");

        AnimalModelManager animalModelManager = (AnimalModelManager) getBean("animalModelManager");
        AnimalModel am = animalModelManager.get(modelID);

        final List egc = am.getEngineeredGeneCollection();
        final int egcCnt = (egc != null) ? egc.size() : 0;
        final List tgc = new ArrayList();
        int tgCnt = 0;// Transgene
        final List gsc = new ArrayList();
        int gsCnt = 0;// GenomicSegment
        final List tmc = new ArrayList();
        int tmCnt = 0;// TargetedModification
        final List imc = new ArrayList();
        int imCnt = 0;// InducedMutation
        for (int i = 0; i < egcCnt; i++) {
            EngineeredGene eg = (EngineeredGene) egc.get(i);
            if (eg instanceof Transgene) {
                tgc.add(eg);
                tgCnt++;
            } else if (eg instanceof GenomicSegment) {
                gsc.add(eg);
                gsCnt++;
            } else if (eg instanceof TargetedModification) {
                tmc.add(eg);
                tmCnt++;
            } else if (eg instanceof InducedMutation) {
                imc.add(eg);
                imCnt++;
            }
        }
        System.out.println("<populateEngineeredGene> " + "egcCnt=" + egcCnt + "tgc=" + tgCnt + "gsc=" + gsCnt + "tmc="
                + tmCnt + "imc=" + imCnt);
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
     * @param mapping
     *            the struts action mapping
     * @param form
     *            the web form
     * @param request
     *            HTTPRequest
     * @param response
     *            HTTPResponse
     * @return
     * @throws Exception
     */
    public ActionForward populateCarcinogenicInterventions(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        setCancerModel(request);
        return mapping.findForward("viewCarcinogenicInterventions");
    }

    /**
     * Populate the session and/or request with the objects necessary to display
     * the page.
     * 
     * @param mapping
     *            the struts action mapping
     * @param form
     *            the web form
     * @param request
     *            HTTPRequest
     * @param response
     *            HTTPResponse
     * @return
     * @throws Exception
     */
    public ActionForward populatePublications(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        setCancerModel(request);
        return mapping.findForward("viewPublications");
    }

    /**
     * Populate the session and/or request with the objects necessary to display
     * the page.
     * 
     * @param mapping
     *            the struts action mapping
     * @param form
     *            the web form
     * @param request
     *            HTTPRequest
     * @param response
     *            HTTPResponse
     * @return
     * @throws Exception
     */
    public ActionForward populateHistopathology(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        setCancerModel(request);
        return mapping.findForward("viewHistopathology");
    }

    /**
     * Populate the session and/or request with the objects necessary to display
     * the page.
     * 
     * @param mapping
     *            the struts action mapping
     * @param form
     *            the web form
     * @param request
     *            HTTPRequest
     * @param response
     *            HTTPResponse
     * @return
     * @throws Exception
     */
    public ActionForward populateTherapeuticApproaches(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        setCancerModel(request);
        //
        // query caBIO and load clinical protocols information
        // store clinicalProtocol info in a hashmap keyed by NSC#
        //
        final HashMap clinProtocols = new HashMap();
        /*
         * // // This is the old way (2-tier app // String modelID =
         * request.getParameter("aModelID"); AnimalModelManager
         * animalModelManager = (AnimalModelManager)
         * getBean("animalModelManager"); AnimalModel am =
         * animalModelManager.get(modelID); final List therapyColl =
         * am.getTherapyCollection(); final int cc =
         * (therapyColl!=null)?therapyColl.size():0; for(int i=0; i<cc; i++) {
         * Therapy t = (Therapy)therapyColl.get(i); Agent a = t.getAgent(); if
         * (a != null) { Long nscNumber = t.getAgent().getNscNumber();
         * gov.nih.nci.caBIO.bean.ClinicalTrialProtocol[] clinicaltrialprotocols =
         * null; try { gov.nih.nci.caBIO.bean.SearchResult myAgentSearchResult =
         * null; gov.nih.nci.caBIO.bean.Agent agent = new
         * gov.nih.nci.caBIO.bean.Agent();
         * gov.nih.nci.caBIO.bean.AgentSearchCriteria agentSearchCriteria = new
         * gov.nih.nci.caBIO.bean.AgentSearchCriteria();
         * agentSearchCriteria.setAgentNSCNumber(nscNumber); myAgentSearchResult
         * =(gov.nih.nci.caBIO.bean.SearchResult)
         * agent.search(agentSearchCriteria); gov.nih.nci.caBIO.bean.Agent[]
         * agentResultSet = (gov.nih.nci.caBIO.bean.Agent[])
         * myAgentSearchResult.getResultSet(); for ( int q=0; q <
         * agentResultSet.length; q++ ) { clinicaltrialprotocols=
         * (agentResultSet[q]).getClinicalTrialProtocols(); }// end for
         * clinProtocols.put(nscNumber, clinicaltrialprotocols);
         * }catch(Exception ex) { ex.printStackTrace(); } } }
         */
        String modelID = request.getParameter("aModelID");
        AnimalModelManager animalModelManager = (AnimalModelManager) getBean("animalModelManager");
        AnimalModel am = animalModelManager.get(modelID);
        final List therapyColl = am.getTherapyCollection();
        final int cc = (therapyColl != null) ? therapyColl.size() : 0;
        log.info("Looking up clinical protocols for " + cc + " agents...");
        ApplicationService appService = ApplicationService
                .getRemoteInstance("http://cabio.nci.nih.gov/cacore30/server/HTTPServer");

        for (int i = 0; i < cc; i++) {
            Therapy t = (Therapy) therapyColl.get(i);
            Agent a = t.getAgent();
            if (a != null) {
                Long nscNumber = t.getAgent().getNscNumber();
                if (nscNumber != null) {
                    Collection protocols = null;
                    gov.nih.nci.cabio.domain.Agent agt = new AgentImpl();
                    agt.setNSCNumber(nscNumber);
                    try {
                        List resultList = appService.search(gov.nih.nci.cabio.domain.Agent.class, agt);
                        final int resultCount = (resultList != null) ? resultList.size() : 0;
                        log.info("Got " + resultCount + " results....");
                        for (Iterator resultsIterator = resultList.iterator(); resultsIterator.hasNext();) {
                            gov.nih.nci.cabio.domain.Agent returnedAgt = (gov.nih.nci.cabio.domain.Agent) resultsIterator
                                    .next();
                            log.info("Returned Agent: " + returnedAgt.getNSCNumber());
                            protocols = returnedAgt.getClinicalTrialProtocolCollection();
                            clinProtocols.put(nscNumber, protocols);
                            if (protocols != null) {
                                log.info("Agent:" + returnedAgt.getName() + "Protocols.size()" + protocols.size());
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        request.getSession().setAttribute(Constants.CLINICAL_PROTOCOLS, clinProtocols);

        return mapping.findForward("viewTherapeuticApproaches");
    }

    /**
     * Populate the session and/or request with the objects necessary to display
     * the page.
     * 
     * @param mapping
     *            the struts action mapping
     * @param form
     *            the web form
     * @param request
     *            HTTPRequest
     * @param response
     *            HTTPResponse
     * @return
     * @throws Exception
     */
    public ActionForward populateCellLines(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        setCancerModel(request);
        return mapping.findForward("viewCellLines");
    }

    /**
     * Populate the session and/or request with the objects necessary to display
     * the page.
     * 
     * @param mapping
     *            the struts action mapping
     * @param form
     *            the web form
     * @param request
     *            HTTPRequest
     * @param response
     *            HTTPResponse
     * @return
     * @throws Exception
     */
    public ActionForward populateImages(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        setCancerModel(request);
        return mapping.findForward("viewImages");
    }

    /**
     * Populate the session and/or request with the objects necessary to display
     * the page.
     * 
     * @param mapping
     *            the struts action mapping
     * @param form
     *            the web form
     * @param request
     *            HTTPRequest
     * @param response
     *            HTTPResponse
     * @return
     * @throws Exception
     */
    public ActionForward populateMicroarrays(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        setCancerModel(request);
        return mapping.findForward("viewMicroarrays");
    }

    /**
     * Populate the session and/or request with the objects necessary to display
     * the page.
     * 
     * @param mapping
     *            the struts action mapping
     * @param form
     *            the web form
     * @param request
     *            HTTPRequest
     * @param response
     *            HTTPResponse
     * @return
     * @throws Exception
     */
    public ActionForward populateTransplantXenograft(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        setCancerModel(request);
        return mapping.findForward("viewTransplantXenograft");
    }
}
