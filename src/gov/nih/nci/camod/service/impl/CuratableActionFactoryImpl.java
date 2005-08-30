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
 * 2005 August 18  Sumeet Rajput      Integrated with caMOD codebase.
 *
 */
package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.service.CurateableAction;
import gov.nih.nci.camod.service.CuratableActionFactory;

import java.util.HashMap;

public class CuratableActionFactoryImpl implements CuratableActionFactory {

    private HashMap myActions = new HashMap();

    CuratableActionFactoryImpl() {
 
    }

    public void registerAction(String inActionName, CurateableAction inAction) {
        // All supported actions
        myActions.put(inActionName, inAction);
    }
    
    public CurateableAction getAction(String inActionName) {
        CurateableAction theAction = null;
        if (myActions.containsKey(inActionName)) {
            CurateableAction theTemplateAction = (CurateableAction) myActions.get(inActionName);
            theAction = theTemplateAction.create();
        }
        return theAction;
    }
}
