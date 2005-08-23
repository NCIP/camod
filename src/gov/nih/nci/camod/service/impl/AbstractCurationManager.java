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

import gov.nih.nci.camod.service.CurationManager;
import gov.nih.nci.camod.domain.Curateable;
import java.util.*;
import javax.xml.parsers.*;

/**
 * AbstractCurationManager: Abstract implementation of the CurationManager.
 */
public abstract class AbstractCurationManager implements CurationManager {
	protected HashMap events = null;
	protected String state = null;
	protected org.w3c.dom.Document curationConfig = null;
    protected org.w3c.dom.Element curationStates = null;
    protected org.w3c.dom.Element curationState = null;
    protected org.w3c.dom.NodeList stateList = null;

    /**
     * AbstractCurationManager::init()
     */
    protected void init() {
		// --------------------------------------------------------------------------------------
        // Read in Curation Configuration file.
		// --------------------------------------------------------------------------------------
        try {

			// Create an instance of the DocumentBuilderFactory
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();

			// Retrieve the DocumentBuilder from the factory.
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

			// Turn it into an in-memory object
			curationConfig = documentBuilder.parse("config/CurationConfig.xml");

		} catch (Exception e) {
			e.printStackTrace();
		}

		// --------------------------------------------------------------------------------------
        // Identify Events and Set the Default State.
		// --------------------------------------------------------------------------------------

		events = new HashMap();
        curationStates = curationConfig.getDocumentElement();
        stateList = curationStates.getElementsByTagName("State");
        
        for (int x=0; x < stateList.getLength(); x++ ) {
			curationState = (org.w3c.dom.Element)stateList.item(x);
			events.put(curationState.getAttribute("eventName"), curationState.getAttribute("eventValue"));

			if ( curationState.getAttribute("default").equals("true")) {
				this.setDefaultState(curationState.getAttribute("symbolicNameValue"));
			}
		}
    }

    /**
     * AbstractCurationManager::setDefaultState()
     */
    private void setDefaultState(String p_state) {
		this.state = p_state;

    }

    /**
     * AbstractCurationManager::getDefaultState()
     */
    public String getDefaultState() {
		return this.state;
    }

    /**
     * AbstractCurationManager::changeState()
     */
    public Curateable changeState(Curateable obj, String newState) {
		ArrayList validStates = null;
		StringTokenizer tokenizer = null;

		String currentState = obj.getState();

		if (currentState == null) {
			obj.setState(this.getDefaultState());
			return obj;
		}

		if (!currentState.equals(newState))	{
			// Determine the next possible states.
			validStates = new ArrayList();
			
        	for (int x=0; x < stateList.getLength(); x++ ) {
				curationState = (org.w3c.dom.Element)stateList.item(x);
				
				if (curationState.getAttribute("symbolicNameValue").equals(currentState)) {
					tokenizer = new StringTokenizer(curationState.getAttribute("nextValidState"), ",");
					
					while (tokenizer.hasMoreTokens()) {
						String token = tokenizer.nextToken();
						validStates.add(token);
					}
				}
			}

			// If the new state is valid, process the event and return the new state.
			if (validStates.contains(newState))	{
				// Get New State Config
        		for (int x=0; x < stateList.getLength(); x++ ) {
					curationState = (org.w3c.dom.Element)stateList.item(x);
					if (curationState.getAttribute("symbolicNameValue").equals(newState)) {
						// Process Event
						obj.setState(processEvent(curationState));
						break;
					}
				}
			}
		}
		return obj;
    }

    /**
     * AbstractCurationManager::processEvent()
     */
    public abstract String processEvent(org.w3c.dom.Element curationState);
}
