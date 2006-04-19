/**
 * @author pandyas
 * 
 * $Id: CellLineManagerImpl.java,v 1.13 2006-04-19 17:38:26 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
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

import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.domain.CellLine;
import gov.nih.nci.camod.domain.Organ;
import gov.nih.nci.camod.service.CellLineManager;
import gov.nih.nci.camod.util.EvsTreeUtil;
import gov.nih.nci.camod.webapp.form.CellLineData;

import java.util.List;

/**
 * @author rajputs
 */
public class CellLineManagerImpl extends BaseManager implements CellLineManager
{
    public List getAll() throws Exception
    {
        log.trace("In CellLineManagerImpl.getAll");
        return super.getAll(CellLine.class);
    }

    public CellLine get(String id) throws Exception
    {
        log.trace("In CellLineManagerImpl.get");
        return (CellLine) super.get(id, CellLine.class);
    }

    public void save(CellLine cellLine) throws Exception
    {
        log.trace("In CellLineManagerImpl.save");
        super.save(cellLine);
    }

    public void remove(String id,
                       AnimalModel inAnimalModel) throws Exception
    {
        log.trace("In CellLineManagerImpl.remove");

        inAnimalModel.getCellLineCollection().remove(get(id));
        super.save(inAnimalModel);
    }

    public CellLine create(CellLineData inCellLineData) throws Exception
    {
        log.debug("Entering CellLineManagerImpl.create");

        CellLine theCellLine = new CellLine();

        populateCellLine(inCellLineData, theCellLine);
        log.debug("Exiting CellLineManagerImpl.create");

        return theCellLine;
    }

    public void update(CellLineData inCellLineData,
                       CellLine inCellLine) throws Exception
    {
        log.debug("Entering CellLineManagerImpl.update");
        log.debug("Updating CellLineForm: " + inCellLine.getId());

        // Populate w/ the new values and save
        populateCellLine(inCellLineData, inCellLine);
        save(inCellLine);

        log.debug("Exiting CellLineManagerImpl.update");
    }

    private void populateCellLine(CellLineData inCellLineData,
                                  CellLine inCellLine) throws Exception
    {
        log.debug("Entering populateCellLine");

        inCellLine.setName(inCellLineData.getCellLineName());
        inCellLine.setExperiment(inCellLineData.getExperiment());
        inCellLine.setResults(inCellLineData.getResults());
        inCellLine.setComments(inCellLineData.getComments());

        /*
         * Add a Organ to CellLine with correct IDs, conceptCode 
         */
        Organ theOrgan = null;
        //new submission - organ will be null
        if (inCellLine.getOrgan() == null)
        {
            System.out.println("Creating new Organ object");
            inCellLine.setOrgan(new Organ());
        }

        String newConceptCode = inCellLineData.getOrganTissueCode();
        System.out.println("newConceptCode: " + newConceptCode);

        String oldConceptCode = inCellLine.getOrgan().getConceptCode();
        System.out.println("oldConceptCode: " + oldConceptCode);

        if (!newConceptCode.equals(oldConceptCode))
        {
            System.out.println("Organ is new or was modified so retrieve attributes");
            //always get/store organ name through the concept code - never deal with converting name back and forth
            String preferedOrganName = EvsTreeUtil.getEVSPreferedDescription(inCellLineData.getOrganTissueCode());

            System.out.println("preferedOrganName: " + preferedOrganName);
            inCellLine.getOrgan().setName(preferedOrganName);

            System.out.println("populateCellLine - getOrgan().setConceptCode - OrganTissueCode: " + inCellLineData.getOrganTissueCode());
            inCellLine.getOrgan().setConceptCode(inCellLineData.getOrganTissueCode());
        }

        log.debug("Exiting populateCellLine");
    }
}
