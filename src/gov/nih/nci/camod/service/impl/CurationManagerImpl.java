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
 * 2005 August 18  Sumeet Rajput	  Integrated with caMOD codebase.
 *
 */

package gov.nih.nci.camod.service.impl;

/**
 * CurationManager implementation.
 */
public class CurationManagerImpl extends AbstractCurationManager {
	
	public CurationManagerImpl() {
		super.init();
	}

    public String processEvent(org.w3c.dom.Element curationState) {
		final int INCOMPLETE_EVENT = new Integer((String)events.get("INCOMPLETE_EVENT")).intValue();
		final int COMPLETE_NOT_SCREENED_EVENT = new Integer((String)events.get("COMPLETE_NOT_SCREENED_EVENT")).intValue();
		final int SCREENER_ASSIGNED_EVENT = new Integer((String)events.get("SCREENER_ASSIGNED_EVENT")).intValue();
		final int SCREENER_APPROVED_EVENT = new Integer((String)events.get("SCREENER_APPROVED_EVENT")).intValue();
		final int SCREENER_REJECTED_EVENT = new Integer((String)events.get("SCREENER_REJECTED_EVENT")).intValue();
		final int REVIEWER_ASSIGNED_EVENT = new Integer((String)events.get("REVIEWER_ASSIGNED_EVENT")).intValue();
		final int REVIEWER_APPROVED_EVENT = new Integer((String)events.get("REVIEWER_APPROVED_EVENT")).intValue();
		final int REVIEWER_REJECTED_EVENT = new Integer((String)events.get("REVIEWER_REJECTED_EVENT")).intValue();
		final int COMPLETE_EVENT = new Integer((String)events.get("COMPLETE_EVENT")).intValue();

		int event = new Integer(curationState.getAttribute("eventValue")).intValue();

        if (event == INCOMPLETE_EVENT) {
              System.out.println("==> INCOMPLETE received.");
              System.out.println("==> The new state is " + curationState.getAttribute("symbolicName") );
              System.out.println("\n");
              return curationState.getAttribute("symbolicNameValue");
		}
		else if (event == COMPLETE_EVENT) {
              System.out.println("==> COMPLETE_EVENT received.");
              System.out.println("==> The new state is " + curationState.getAttribute("symbolicName") );
              System.out.println("\n");
              return curationState.getAttribute("symbolicNameValue");
		}
		else if (event == COMPLETE_NOT_SCREENED_EVENT) {
              System.out.println("==> COMPLETE_NOT_SCREENED_EVENT received.");
              System.out.println("==> The new state is " + curationState.getAttribute("symbolicName") );
              System.out.println("\n");
              return curationState.getAttribute("symbolicNameValue");
		}
		else if (event == SCREENER_ASSIGNED_EVENT) {
              System.out.println("==> SCREENER_ASSIGNED_EVENT received.");
              System.out.println("==> Sending email to assigned screener..." );
              System.out.println("==> The new state is " + curationState.getAttribute("symbolicName") );
              System.out.println("\n");
              return curationState.getAttribute("symbolicNameValue");
		}
		else if (event == SCREENER_APPROVED_EVENT) {
              System.out.println("==> SCREENER_APPROVED_EVENT received.");
              System.out.println("==> The new state is " + curationState.getAttribute("symbolicName") );
              System.out.println("\n");
              return curationState.getAttribute("symbolicNameValue");
		}
		else if (event == SCREENER_REJECTED_EVENT) {
              System.out.println("==> SCREENER_REJECTED_EVENT received.");
              System.out.println("==> The new state is " + curationState.getAttribute("symbolicName") );
              System.out.println("\n");
              return curationState.getAttribute("symbolicNameValue");
		}
		else if (event == REVIEWER_ASSIGNED_EVENT) {
              System.out.println("==> REVIEWER_ASSIGNED_EVENT received.");
              System.out.println("==> Sending email to assigned reviewer..." );
              System.out.println("==> The new state is " + curationState.getAttribute("symbolicName") );
              System.out.println("\n");
              return curationState.getAttribute("symbolicNameValue");
		}
		else if (event == REVIEWER_APPROVED_EVENT) {
              System.out.println("==> REVIEWER_APPROVED_EVENT received.");
              System.out.println("==> The new state is " + curationState.getAttribute("symbolicName") );
              System.out.println("\n");
              return curationState.getAttribute("symbolicNameValue");
		}
		else if (event == REVIEWER_REJECTED_EVENT) {
              System.out.println("==> REVIEWER_REJECTED_EVENT received.");
              System.out.println("==> The new state is " + curationState.getAttribute("symbolicName") );
              System.out.println("\n");
              return curationState.getAttribute("symbolicNameValue");
        }
        else 
        	return "Error: Illegal State";
	}
}
