/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/camod/LICENSE.txt for details.
 */

/**
 * 
 * $Id: SpeciesData.java,v 1.1 2006-04-17 19:09:19 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * 
 */


package gov.nih.nci.camod.webapp.form;

public interface SpeciesData
{
    public String getScientificName();
    public void setScientificName( String scientificName ); 
    public String getCommonName(); 
    public void setCommonName( String commonName ); 
 
}
