/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/camod/LICENSE.txt for details.
 */

/**
 * 
 * $Id: CellLineManagerImpl.java,v 1.21 2009-06-08 15:30:48 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.20  2007/09/12 19:36:03  pandyas
 * modified debug statements for build to stage tier
 *
 * Revision 1.19  2007/06/20 17:55:13  pandyas
 * Fixed save and edit for cell line
 *
 * Revision 1.18  2007/06/13 20:20:09  pandyas
 * Modified code for EVS trees after formal testing
 *
 * Revision 1.17  2007/04/30 20:09:43  pandyas
 * Implemented species specific vocabulary trees from EVSTree
 *
 * Revision 1.16  2006/10/17 16:13:46  pandyas
 * modified during development of caMOD 2.2 - various
 *
 * Revision 1.15  2006/05/08 13:41:31  georgeda
 * Use data for fetching organ, not object
 *
 * Revision 1.14  2006/05/08 13:32:34  georgeda
 * Clean up warnings
 *
 * Revision 1.13  2006/04/19 17:38:26  pandyas
 * Removed text
 *
 * Revision 1.12  2006/04/17 19:11:06  pandyas
 * caMod 2.1 OM changes
 *
 * Revision 1.11  2006/01/18 14:24:24  georgeda
 * TT# 376 - Updated to use new Java 1.5 features
 *
 * Revision 1.10  2005/11/09 00:17:16  georgeda
 * Fixed delete w/ constraints
 *
 * Revision 1.9  2005/11/03 21:47:48  georgeda
 * Changed EVS api
 *
 * Revision 1.8  2005/11/01 18:14:28  schroedn
 * Implementing 'Enter Publication' for CellLines and Therapy, fixed many bugs with Publication. Remaining known bug with "Fill in Fields" button
 *
 * Revision 1.7  2005/10/20 20:26:09  pandyas
 * EVSTree (organ) functions properly
 *
 * 
 */
package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.domain.CellLine;
import gov.nih.nci.camod.domain.Organ;
import gov.nih.nci.camod.service.CellLineManager;
import gov.nih.nci.camod.webapp.form.CellLineData;

import java.util.List;

/**
 * Impementation of CellLineManager. Used to persist CellLine objects.
 */
public class CellLineManagerImpl extends BaseManager implements CellLineManager {
	/**
	 * Get all CellLine objects
	 * 
	 * @return the matching CellLine objects
	 * 
	 * @exception Exception
	 *                when anything goes wrong.
	 */
	public List getAll() throws Exception {
		log.trace("In CellLineManagerImpl.getAll");
		return super.getAll(CellLine.class);
	}

	/**
	 * Get a specific CellLine by id
	 * 
	 * @param id
	 *            the unique id for a CellLine
	 * 
	 * @return the matching CellLine object, or null if not found.
	 * 
	 * @exception Exception
	 *                when anything goes wrong.
	 */
	public CellLine get(String id) throws Exception {
		log.trace("In CellLineManagerImpl.get");
		return (CellLine) super.get(id, CellLine.class);
	}

	/**
	 * Save an CellLine object
	 * 
	 * @param inCellLine
	 *            the CellLine object to save
	 * 
	 * @exception Exception
	 *                if an error occurred
	 */
	public void save(CellLine inCellLine) throws Exception {
		log.trace("In CellLineManagerImpl.save");
		super.save(inCellLine);
	}

	/**
	 * Remove a CellLine object
	 * 
	 * @param inId
	 *            the ID of the CellLine to remove
	 * @param inAnimalModel
	 *            the AnimalModel the CellLine is being deleted from.
	 * 
	 * @exception Exception
	 *                if an error occurred
	 */
	public void remove(String inId, AnimalModel inAnimalModel) throws Exception {
		log.trace("In CellLineManagerImpl.remove");

		inAnimalModel.getCellLineCollection().remove(get(inId));
		super.save(inAnimalModel);
	}

	/**
	 * Create a CellLine object from the data being passed in
	 * 
	 * @param inCellLineData
	 *            the data used to ceate the CellLine
	 * 
	 * @return the newly created CellLine object
	 * @exception Exception
	 *                if an error occurred
	 */
	public CellLine create(CellLineData inCellLineData) throws Exception {
		log.debug("Entering CellLineManagerImpl.create");

		CellLine theCellLine = new CellLine();

        // Populate w/ the new values and save
        populateOrgan(inCellLineData,
                      theCellLine);            
		populateCellLine(inCellLineData, theCellLine);
		log.debug("Exiting CellLineManagerImpl.create");

		return theCellLine;
	}

	/**
	 * Update a CellLine object with the data being passed in
	 * 
	 * @param inCellLineData
	 *            the data used to ceate the CellLine
	 * @param inCellLine
	 *            the CellLine object to update
	 * 
	 * @exception Exception
	 *                if an error occurred
	 */
	public void update(CellLineData inCellLineData, CellLine inCellLine)
			throws Exception {
		log.debug("Entering CellLineManagerImpl.update");
		log.debug("Updating CellLineForm: " + inCellLine.getId());

        // Populate w/ the new values and save
        populateOrgan(inCellLineData,
                      inCellLine);        
		// Populate w/ the new values and save
		populateCellLine(inCellLineData, inCellLine);
		save(inCellLine);

		log.debug("Exiting CellLineManagerImpl.update");
	}
    
    private void populateOrgan(CellLineData inCellLineData,  CellLine inCellLine) throws Exception {

        log.debug("<CellLineManagerImpl> Entering populateOrgan");

        // Update loop handled separately for conceptCode = 000000
        if (inCellLineData.getOrganTissueCode().equals(Constants.Dropdowns.CONCEPTCODEZEROS)){
            log.debug("Organ update loop for text: " + inCellLineData.getOrgan()); 
            inCellLine.setOrgan(new Organ());
            inCellLine.getOrgan().setName(inCellLineData.getOrgan());   
            inCellLine.getOrgan().setConceptCode(
                    Constants.Dropdowns.CONCEPTCODEZEROS);            
        } else {            
            // Using trees loop, new save loop and update loop
            if (inCellLineData.getOrganTissueCode() != null && inCellLineData.getOrganTissueCode().length() > 0) {
                log.debug("OrganTissueCode: " + inCellLineData.getOrganTissueCode());
                log.debug("OrganTissueName: " + inCellLineData.getOrganTissueName()); 
                
                log.debug("OrganTissueCode() != null - getOrCreate method used");
                // when using tree, organTissueName populates the organ name entry
                Organ theNewOrgan = OrganManagerSingleton.instance().getOrCreate(
                        inCellLineData.getOrganTissueCode(),
                        inCellLineData.getOrganTissueName());
                
                log.debug("theNewOrgan: " + theNewOrgan);
                inCellLine.setOrgan(theNewOrgan); 
            } else {
                // text entry loop = new save
                log.debug("Organ (text): " + inCellLineData.getOrgan()); 
                inCellLine.setOrgan(new Organ());
                inCellLine.getOrgan().setName(inCellLineData.getOrgan());                
                inCellLine.getOrgan().setConceptCode(
                        Constants.Dropdowns.CONCEPTCODEZEROS); 
                log.debug("Organ: " + inCellLine.getOrgan().toString());
            }           
            
        }        
        
    }

	// Common method used to populate a CellLine object
	private void populateCellLine(CellLineData inCellLineData,
			CellLine inCellLine) throws Exception {
		log.debug("Entering populateCellLine");

		inCellLine.setName(inCellLineData.getCellLineName());
		inCellLine.setExperiment(inCellLineData.getExperiment());
		inCellLine.setResults(inCellLineData.getResults());
		inCellLine.setComments(inCellLineData.getComments());

		log.debug("Exiting populateCellLine");
	}
}
