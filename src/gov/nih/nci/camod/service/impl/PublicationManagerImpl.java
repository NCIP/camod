/*
 * $Id: PublicationManagerImpl.java,v 1.12 2006-01-18 14:24:23 georgeda Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.11  2005/11/29 22:12:29  georgeda
 * Defect #163, either PMID or Title must be entered
 *
 * Revision 1.10  2005/11/14 14:18:58  georgeda
 * Handle delete of child publications
 *
 * Revision 1.9  2005/11/09 00:17:16  georgeda
 * Fixed delete w/ constraints
 *
 * Revision 1.8  2005/11/01 18:14:28  schroedn
 * Implementing 'Enter Publication' for CellLines and Therapy, fixed many bugs with Publication. Remaining known bug with "Fill in Fields" button
 *
 * Revision 1.7  2005/10/27 12:52:50  georgeda
 * Refactor of publication manager
 *
 * 
 */
package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.domain.CellLine;
import gov.nih.nci.camod.domain.Publication;
import gov.nih.nci.camod.domain.PublicationStatus;
import gov.nih.nci.camod.domain.Therapy;
import gov.nih.nci.camod.service.PublicationManager;
import gov.nih.nci.camod.webapp.form.PublicationData;
import gov.nih.nci.common.persistence.Search;

import java.util.List;

/**
 * Manager for Publication objects
 */
public class PublicationManagerImpl extends BaseManager implements PublicationManager
{

    public Publication create(PublicationData inPublicationData) throws Exception
    {
        Publication thePublication = new Publication();
        populate(inPublicationData, thePublication);

        return thePublication;
    }

    public void update(PublicationData inPublicationData,
                       Publication inPublication) throws Exception
    {
        populate(inPublicationData, inPublication);
        super.save(inPublication);
    }

    public void removeCellLinePublication(String id,
                                          CellLine inCellLine) throws Exception
    {
        inCellLine.getPublicationCollection().remove(get(id));
        super.save(inCellLine);
    }

    public void removeTherapyPublication(String id,
                                         Therapy inTherapy) throws Exception
    {
        inTherapy.getPublicationCollection().remove(get(id));
        super.save(inTherapy);
    }

    public void addCellLinePublication(PublicationData inPublicationData,
                                       CellLine inCellLine) throws Exception
    {
        Publication inPublication = create(inPublicationData);
        inCellLine.addPublication(inPublication);
        super.save(inCellLine);
    }

    public void addTherapyPublication(PublicationData inPublicationData,
                                      Therapy inTherapy) throws Exception
    {
        Publication inPublication = create(inPublicationData);
        inTherapy.addPublication(inPublication);
        super.save(inTherapy);
    }

    private void populate(PublicationData inPublicationData,
                          Publication inPublication) throws Exception
    {
        inPublication.setAuthors(inPublicationData.getAuthors());
        inPublication.setTitle(inPublicationData.getTitle());
        inPublication.setJournal(inPublicationData.getJournal());
        inPublication.setVolume(inPublicationData.getVolume());

        String strPub = inPublicationData.getPmid();

        if (strPub != null && strPub.trim().length() > 0)
        {
            inPublication.setPmid(Long.valueOf(strPub.trim()));
        }
        else
        {
            inPublication.setPmid(null);
        }

        strPub = inPublicationData.getStartPage().trim();
        if (strPub.length() > 0)
        {
            inPublication.setStartPage(Long.valueOf(strPub));
        }
        else
        {
            inPublication.setStartPage(null);
        }

        strPub = inPublicationData.getEndPage().trim();
        if (strPub.length() > 0)
        {
            inPublication.setEndPage(Long.valueOf(strPub));
        }
        else
        {
            inPublication.setEndPage(null);
        }

        strPub = inPublicationData.getYear().trim();
        if (strPub.length() > 0)
        {
            inPublication.setYear(Long.valueOf(strPub));
        }
        else
        {
            inPublication.setYear(null);
        }

        if (inPublicationData.getFirstTimeReported() != null && inPublicationData.getFirstTimeReported().equals("yes"))
        {
            inPublication.setFirstTimeReported(new Boolean(true));
        }
        else
        {
            inPublication.setFirstTimeReported(new Boolean(false));
        }

        PublicationStatus pubStatus = PublicationManagerSingleton.instance().getPublicationStatusByName(inPublicationData.getName());
        inPublication.setPublicationStatus(pubStatus);
    }

    public List getAll() throws Exception
    {
        log.trace("In PublicationManagerImpl.getAll");
        return super.getAll(Publication.class);
    }

    public Publication get(String id) throws Exception
    {
        log.trace("In PublicationManagerImpl.get");
        return (Publication) super.get(id, Publication.class);
    }

    public void save(Publication publication) throws Exception
    {
        log.trace("In PublicationManagerImpl.save");
        super.save(publication);
    }

    public PublicationStatus getPublicationStatusByName(String inName) throws Exception
    {
        log.trace("In PublicationManagerImpl.getPublicationStatusByName");

        PublicationStatus pubStatus = null;

        // The following two objects are needed for eQBE.
        PublicationStatus thePubStatus = new PublicationStatus();
        thePubStatus.setName(inName);

        List theList = Search.query(thePubStatus);

        if (theList != null && theList.size() > 0)
        {
            pubStatus = (PublicationStatus) theList.get(0);
        }

        return pubStatus;
    }

    public void remove(String id,
                       AnimalModel inAnimalModel) throws Exception
    {
        log.trace("In PublicationManagerImpl.remove");
        inAnimalModel.getPublicationCollection().remove(get(id));
        super.save(inAnimalModel);
    }
}
