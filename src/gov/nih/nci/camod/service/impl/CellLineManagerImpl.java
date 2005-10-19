/*
 * Created on Jun 17, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.domain.CellLine;
import gov.nih.nci.camod.domain.Organ;
import gov.nih.nci.camod.service.CellLineManager;
import gov.nih.nci.camod.webapp.form.CellLineData;

import java.util.List;

/**
 * @author rajputs
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CellLineManagerImpl extends BaseManager implements CellLineManager {
	
	public List getAll() throws Exception {
		log.trace("In CellLineManagerImpl.getAll");
		return super.getAll(CellLine.class);
	}
	
	public CellLine get(String id) throws Exception {
		log.trace("In CellLineManagerImpl.get");
		return (CellLine) super.get(id, CellLine.class);
	}

	public void save(CellLine cellLine) throws Exception {
		log.trace("In CellLineManagerImpl.save");
		super.save(cellLine);    	     
	}

	public void remove(String id) throws Exception {		
		log.trace("In CellLineManagerImpl.remove");		
		super.remove(id, CellLine.class);
	}

    public CellLine create(CellLineData inCellLineData) throws Exception {

        log.debug("Entering CellLineManagerImpl.create");

        CellLine theCellLine = new CellLine();

        populateCellLine(inCellLineData, theCellLine);
        log.debug("Exiting CellLineManagerImpl.create");        

        return theCellLine;
    }

    public void update(CellLineData inCellLineData, CellLine inCellLine)
    throws Exception {

    	log.debug("Entering CellLineManagerImpl.update");
    	log.debug("Updating CellLineForm: " + inCellLine.getId());

    	// Populate w/ the new values and save
    	populateCellLine(inCellLineData, inCellLine);
    	save(inCellLine);

    	log.debug("Exiting CellLineManagerImpl.update");
    }    

	private void populateCellLine( CellLineData inCellLineData, CellLine inCellLine) 
	throws Exception {

		log.debug( "Entering populateCellLine" );

		inCellLine.setName(inCellLineData.getCellLineName());
		inCellLine.setExperiment( inCellLineData.getExperiment() );
		inCellLine.setResults( inCellLineData.getResults() );
		inCellLine.setComments(inCellLineData.getComments());
	
        /*
         * Add a Organ to AnimalModel with correct ID, conceptCode 
        */ 
        System.out.println("Saving: getOrgan=" + inCellLineData.getOrgan() 
        		+ " Not Saving organTissueName=" + inCellLineData.getOrganTissueName());

        if (inCellLine.getOrgan() == null) {
        	System.out.println("<populateCellLine> inCellLine.getOrgan() == null loop ");
        	inCellLine.setOrgan(new Organ());
        }
        inCellLine.getOrgan().setName(inCellLineData.getOrganTissueName());
        System.out.println("<populateCellLine> getOrganTissueName= " +inCellLineData.getOrganTissueName());
        
        inCellLine.getOrgan().setConceptCode(inCellLineData.getOrganTissueCode());
        System.out.println("<populateCellLine> getConceptCode= " +inCellLineData.getOrganTissueCode());
        
		log.debug("Exiting populateCellLine");
        }
}
