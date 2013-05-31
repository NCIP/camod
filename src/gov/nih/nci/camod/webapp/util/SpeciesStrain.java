/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/camod/LICENSE.txt for details.
 */

package gov.nih.nci.camod.webapp.util;

import java.util.List;

public class SpeciesStrain {
	List strainList;
	String speciesName;
	
	public void setSpeciesName( String species ){ speciesName = species; }
	
	public void setStrainList( List strains) { strainList = strains; }
	
	public String getSpeciesName(){ return speciesName; }
	
	public List getStrainList() { return strainList; }
}
