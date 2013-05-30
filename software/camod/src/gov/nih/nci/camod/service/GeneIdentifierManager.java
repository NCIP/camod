/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/camod/LICENSE.txt for details.
 */

/**
 * 
 * $Id: GeneIdentifierManager.java,v 1.1 2008-01-22 18:38:01 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 *  
 */

package gov.nih.nci.camod.service;

import gov.nih.nci.camod.domain.GeneIdentifier;


public interface GeneIdentifierManager
{

    public GeneIdentifier getOrCreate(String inEntrezGeneId) throws Exception;      
}
