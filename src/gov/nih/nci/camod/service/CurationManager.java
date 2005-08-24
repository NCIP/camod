/**
 * Copyright (c) 2001, SAIC, its vendors, and suppliers. ALL RIGHTS RESERVED.
 *
 * @author: Johnita Beasley
 * @date: July 20, 2005
 *
 * Revision History
 * ----------------
 *
 * 2005 July 21    Johnita Beasley    Created and successfully compiled.
 * 2005 August 18  Sumeet Rajput      Integrated with caMOD codebase
 *
 */
package gov.nih.nci.camod.service;

import gov.nih.nci.camod.domain.Curateable;

/**
 * This interface describes a realized/implementing CurationManager.
 */
public interface CurationManager {
    public String getDefaultState();
    public Curateable changeState(Curateable obj, String newState);
}
