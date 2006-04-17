/**
 * 
 * $Id: PublicationManager.java,v 1.9 2006-04-17 19:13:16 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * 
 */


package gov.nih.nci.camod.service;

import gov.nih.nci.camod.domain.*;
import gov.nih.nci.camod.webapp.form.PublicationData;

import java.util.List;

public interface PublicationManager {

	public Publication create(PublicationData inPublicationData) throws Exception;

	public void update(PublicationData inPublicationData, Publication inPublication) throws Exception;

	public void addCellLinePublication( PublicationData inPublicationData, CellLine inCellLine ) throws Exception;

	public void addTherapyPublication( PublicationData inPublicationData, Therapy inTherapy ) throws Exception;
	
	public void removeCellLinePublication( String id, CellLine inCellLine ) throws Exception;

	public void removeTherapyPublication(String id, Therapy inTherapy ) throws Exception;
	
	public List getAll() throws Exception;

	public Publication get(String id) throws Exception;

	public void save(Publication publication) throws Exception;

	public void remove(String id, AnimalModel inAnimalModel) throws Exception;

	public PublicationStatus getPublicationStatusByName(String inName) throws Exception;
	
}
