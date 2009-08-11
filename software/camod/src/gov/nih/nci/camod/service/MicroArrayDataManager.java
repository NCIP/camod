/*
 * $Id: MicroArrayDataManager.java,v 1.6 2008-08-14 06:25:16 schroedn Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.5  2006/04/19 17:37:51  pandyas
 * Removed TODO text
 *
 * Revision 1.4  2006/04/17 19:13:16  pandyas
 * caMod 2.1 OM changes
 *
 */
package gov.nih.nci.camod.service;

import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.domain.MicroArrayData;
import gov.nih.nci.camod.webapp.form.MicroArrayDataData;

import java.util.List;

/**
 * @author rajputs
 */
public interface MicroArrayDataManager {
	public List getAll() throws Exception;
	public MicroArrayData get(String id) throws Exception;
	public MicroArrayData create(MicroArrayDataData inMicroArrayData) throws Exception;
	public void save(MicroArrayData microArrayData) throws Exception;
	public void remove(String inId, AnimalModel inAnimalModel) throws Exception;   
    public void update(MicroArrayDataData inMicroArrayDataData, MicroArrayData inMicroArrayData) throws Exception;
}