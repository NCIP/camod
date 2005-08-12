/*
 * Created on Jun 17, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.service;

import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.domain.Availability;
import gov.nih.nci.camod.domain.ContactInfo;
import gov.nih.nci.camod.domain.Person;
import gov.nih.nci.camod.domain.Phenotype;
import gov.nih.nci.camod.domain.SexDistribution;
import gov.nih.nci.camod.domain.Taxon;

import java.util.List;

/**
 * @author rajputs
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface AnimalModelManager {
	public List getAnimalModels();
	
	public List getAnimalModels(String username);
	
	public AnimalModel getAnimalModel(String id);
    
	public void saveAnimalModel(AnimalModel animalModel);
    
	public Long saveAnimalModel(
    	Person person,
		ContactInfo contactInfo,
		AnimalModel animalModel,
		Taxon taxon,
		Phenotype phenotype,
		SexDistribution sexDistribution,
		Availability availability);
    
	public void removeAnimalModel(String id);
}
