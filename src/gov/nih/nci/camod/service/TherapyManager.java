/*
 * Created on Jun 17, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.service;

import gov.nih.nci.camod.domain.Therapy;
import gov.nih.nci.camod.webapp.form.ChemicalDrugData;

import java.util.List;

/**
 * @author rajputs
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public interface TherapyManager {

    public List getAll() throws Exception;

    public Therapy get(String id) throws Exception;

    public void save(Therapy therapy) throws Exception;

    public void remove(String id) throws Exception;

    public Therapy create(ChemicalDrugData inChemicalDrugData);

    public void update(ChemicalDrugData inChemicalDrugData, Therapy inTherapy) throws Exception;
}
