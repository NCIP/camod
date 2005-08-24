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

import gov.nih.nci.camod.domain.Curateable;
import gov.nih.nci.camod.service.CurationManager;

import java.util.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.*;

/**
 * AbstractCurationManager: Abstract implementation of the CurationManager.
 */
public abstract class AbstractCurationManager implements CurationManager {

	protected class State {

		private String myName = null;

		private String mySymbolicName = null;

		private List myActions = new ArrayList();

		private Map myNextValidStates = new HashMap();

		public State(String inName, String inSymbolicName) {
			myName = inName;
			mySymbolicName = inSymbolicName;
		}

		public String getName() {
			return myName;
		}

		public String getSymbolicName() {
			return mySymbolicName;
		}

		public String getNextState(String inEvent) {

			String theNextState = "";
			if (myNextValidStates.containsKey(inEvent)) {
				theNextState = (String) myNextValidStates.get(inEvent);
			}

			// Invalid event. Map to the "all" event
			if (theNextState.equals("")) {

				inEvent = "all";

				if (myNextValidStates.containsKey(inEvent)) {
					theNextState = (String) myNextValidStates.get(inEvent);
				}
			}

			return theNextState;
		}

		public List getActions() {
			return myActions;
		}

		public void addAction(String inAction) {
			myActions.add(inAction);
		}

		public void addNextValidState(String inState, String inMatchEvent) {
			myNextValidStates.put(inMatchEvent, inState);
		}
	}

	protected HashMap myStates = new HashMap();

	private String myDefaultState = null;

	/**
	 * AbstractCurationManager::init()
	 */
	protected void init(String inPath) {

		Document theCurationConfig = null;

		// --------------------------------------------------------------------------------------
		// Read in Curation Configuration file.
		// --------------------------------------------------------------------------------------
		try {

			// Create an instance of the DocumentBuilderFactory
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
					.newInstance();

			// Retrieve the DocumentBuilder from the factory.
			DocumentBuilder documentBuilder = documentBuilderFactory
					.newDocumentBuilder();

			// Turn it into an in-memory object
			// TODO: make sure we validate
			theCurationConfig = documentBuilder.parse(inPath
					+ "/config/CurationConfig.xml");

			// /////////////////////////////////////////////////////////////
			// Map state tree and Set the Default State.
			// NOTE: this document will have already been verified by
			// the xsd
			// /////////////////////////////////////////////////////////////

			Element theCurationStates = theCurationConfig.getDocumentElement();
			NodeList theStateList = theCurationStates
					.getElementsByTagName("state");

			for (int i = 0; i < theStateList.getLength(); i++) {
				Element theStateElement = (Element) theStateList.item(i);

				// Note, these will always be there
				String theName = (String) theStateElement.getElementsByTagName(
						"name").item(0).getFirstChild().getNodeValue();
				String theSymbolicName = (String) theStateElement
						.getElementsByTagName("symbolic-name").item(0)
						.getNodeValue();

				if ("true".equals(theStateElement.getAttribute("default"))) {
					myDefaultState = theName;
				}

				State theState = new State(theName, theSymbolicName);

				NodeList theActionsList = theStateElement
						.getElementsByTagName("actions");

				// Should only be one
				if (theActionsList.getLength() == 1) {
					NodeList theActionNodes = theActionsList.item(0)
							.getChildNodes();

					for (int j = 0; j < theActionNodes.getLength(); j++) {

						if ("action".equals(theActionNodes.item(j)
								.getNodeName())) {
							theState.addAction(theActionNodes.item(j)
									.getFirstChild().getNodeValue());
						}
					}
				}

				NodeList theNextValidStatesList = theStateElement
						.getElementsByTagName("next-valid-states");

				// Should only be one
				if (theNextValidStatesList.getLength() == 1) {
					NodeList theValidStateNodes = theNextValidStatesList
							.item(0).getChildNodes();

					for (int j = 0; j < theValidStateNodes.getLength(); j++) {

						Node theValidStateNode = theValidStateNodes.item(j);

						NodeList theChildNodes = theValidStateNode
								.getChildNodes();

						String theEvent = "all";
						String theStateName = null;

						for (int k = 0; k < theChildNodes.getLength(); k++) {

							Node theChildNode = theChildNodes.item(k);

							if ("name".equals(theChildNode.getNodeName())) {
								theStateName = theChildNode.getFirstChild()
										.getNodeValue();
							} else if ("match-event".equals(theChildNode
									.getNodeName())) {
								theEvent = theChildNode.getFirstChild()
										.getNodeValue();
							}
						}

						if (theStateName != null) {
							theState.addNextValidState(theStateName, theEvent);
						}
					}
				}

				myStates.put(theName, theState);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * AbstractCurationManager::getDefaultState()
	 */
	public String getDefaultState() {
		return myDefaultState;
	}

	/**
	 * AbstractCurationManager::changeState()
	 */
	public Curateable changeState(Curateable inCurateableObj, String inEvent) {

		String theCurrentStateName = inCurateableObj.getState();

		if (myStates.containsKey(theCurrentStateName)) {

			State theCurrentState = (State) myStates.get(theCurrentStateName);

			String theNextStateName = theCurrentState.getNextState(inEvent);

			if (theNextStateName.equals("")) {
				System.out.println("Cannot leave current state: "
						+ theCurrentStateName);
			} else if (myStates.containsKey(theNextStateName)) {
				State theNextState = (State) myStates.get(theNextStateName);

				List theActions = theNextState.getActions();

				Iterator theIterator = theActions.iterator();

				while (theIterator.hasNext()) {
					System.out.println("Executing action: "
							+ (String) theIterator.next());
				}

				System.out.println("Changing to state: " + theNextStateName);
				inCurateableObj.setState(theNextStateName);
			} else {
				throw new IllegalArgumentException("Unknown next state: "
						+ theNextStateName);
			}
		} else {
			throw new IllegalArgumentException("Unknown current state: "
					+ theCurrentStateName);
		}

		return inCurateableObj;
	}
}
