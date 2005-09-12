package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.domain.Curateable;
import gov.nih.nci.camod.service.*;

import java.util.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.*;

/**
 * AbstractCurationManager: Abstract implementation of the CurationManager.
 */
public abstract class AbstractCurationManager implements CurationManager {

    protected final Log log = LogFactory.getLog(MailUtilityImpl.class);

    private static final String ACTION_TOKENS = ":,";

    private class State {

        private String myName = null;
        private List myActions = new ArrayList();
        private Map myNextValidStates = new HashMap();

        public State(String inName) {
            myName = inName;
        }

        public String getName() {
            return myName;
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

    // Superclass adds action factory for configurable
    // behavior
    protected CuratableActionFactory myActionFactory = new CuratableActionFactoryImpl();

    protected HashMap myStates = new HashMap();

    private String myDefaultState = null;

    protected void init(String inWorkflowFile) {

        Document theCurationConfig = null;

        // ///////////////////////////////////////////////////
        // Read in Curation Configuration file.
        // ///////////////////////////////////////////////////
        try {

            // Create an instance of the DocumentBuilderFactory
            DocumentBuilderFactory theDocumentBuilderFactory = DocumentBuilderFactory.newInstance();

            // TODO: Get the validation working
            theDocumentBuilderFactory.setValidating(false);
            theDocumentBuilderFactory.setNamespaceAware(true);

            // Retrieve the DocumentBuilder from the factory.
            DocumentBuilder theDocumentBuilder = theDocumentBuilderFactory.newDocumentBuilder();

            // Turn it into an in-memory object
            theCurationConfig = theDocumentBuilder.parse(inWorkflowFile);

            // /////////////////////////////////////////////////////////////
            // Map state tree and Set the Default State.
            // NOTE: this document will have already been verified by
            // the xsd
            // /////////////////////////////////////////////////////////////

            Element theCurationStates = theCurationConfig.getDocumentElement();
            NodeList theStateList = theCurationStates.getElementsByTagName("state");

            for (int i = 0; i < theStateList.getLength(); i++) {
                Element theStateElement = (Element) theStateList.item(i);

                // Note, these will always be there
                String theName = (String) theStateElement.getElementsByTagName("name").item(0).getFirstChild()
                        .getNodeValue();

                if ("true".equals(theStateElement.getAttribute("default"))) {
                    myDefaultState = theName;
                }

                State theState = new State(theName);

                NodeList theActionsList = theStateElement.getElementsByTagName("actions");

                // Should only be one
                if (theActionsList.getLength() == 1) {
                    NodeList theActionNodes = theActionsList.item(0).getChildNodes();

                    for (int j = 0; j < theActionNodes.getLength(); j++) {

                        if ("action".equals(theActionNodes.item(j).getNodeName())) {
                            theState.addAction(theActionNodes.item(j).getFirstChild().getNodeValue());
                        }
                    }
                }

                NodeList theNextValidStatesList = theStateElement.getElementsByTagName("next-valid-states");

                // Should only be one
                if (theNextValidStatesList.getLength() == 1) {
                    NodeList theValidStateNodes = theNextValidStatesList.item(0).getChildNodes();

                    for (int j = 0; j < theValidStateNodes.getLength(); j++) {

                        Node theValidStateNode = theValidStateNodes.item(j);

                        NodeList theChildNodes = theValidStateNode.getChildNodes();

                        String theEvent = "all";
                        String theStateName = null;

                        for (int k = 0; k < theChildNodes.getLength(); k++) {

                            Node theChildNode = theChildNodes.item(k);

                            if ("name".equals(theChildNode.getNodeName())) {
                                theStateName = theChildNode.getFirstChild().getNodeValue();
                            } else if ("match-event".equals(theChildNode.getNodeName())) {
                                theEvent = theChildNode.getFirstChild().getNodeValue();
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

    public String getDefaultState() {
        return myDefaultState;
    }

    public Curateable changeState(Curateable inCurateableObj, String inEvent) {

        log.trace("Entering changeState");

        String theCurrentStateName = inCurateableObj.getState();

        log.debug("Current state: (" + theCurrentStateName + ")");

        if (myStates.containsKey(theCurrentStateName)) {

            State theCurrentState = (State) myStates.get(theCurrentStateName);

            String theNextStateName = theCurrentState.getNextState(inEvent);

            if (theNextStateName.equals("")) {
                log.debug("Cannot leave current state: " + theCurrentStateName);
            } else if (myStates.containsKey(theNextStateName)) {
                log.debug("Setting to state: " + theNextStateName);
                inCurateableObj.setState(theNextStateName);
            } else {
                throw new IllegalArgumentException("Unknown next state: " + theNextStateName);
            }
        } else {
            throw new IllegalArgumentException("Unknown current state: " + theCurrentStateName);
        }

        log.trace("Exiting changeState");

        return inCurateableObj;
    }

    /**
     * Apply any actions associated w/ the state of the curatable object
     * 
     * @param inCurateableObj is the curatable object to apply actions for
     */
    public void applyActionsForState(Curateable inCurateableObj) {

        log.trace("Entering applyActionsForState");

        String theCurrentStateName = inCurateableObj.getState();

        log.debug("Current state: (" + theCurrentStateName + ")");

        if (myStates.containsKey(theCurrentStateName)) {

            State theCurrentState = (State) myStates.get(theCurrentStateName);

            List theActions = theCurrentState.getActions();

            Iterator theIterator = theActions.iterator();

            while (theIterator.hasNext()) {

                String theActionEntry = (String) theIterator.next();

                StringTokenizer theTokenizer = new StringTokenizer(theActionEntry, ACTION_TOKENS);

                String theActionName = theTokenizer.nextToken();

                // Get the arguments for the action
                List theArgs = new ArrayList();
                while (theTokenizer.hasMoreTokens()) {
                    String theArg = theTokenizer.nextToken();
                    theArgs.add(theArg);
                }

                CurateableAction theAction = myActionFactory.getAction(theActionName);

                // Execute the action
                if (theAction != null) {

                    log.debug("Applying action: " + theActionEntry);
                    
                    // Actions by default are not show stoppers if they
                    // don't work
                    try {
                        theAction.execute(theArgs, inCurateableObj);
                    } catch (Exception e) {
                        log.warn("Unable to process action", e);
                    }
                }
            }

        } else {
            throw new IllegalArgumentException("Unknown current state: " + theCurrentStateName);
        }

        log.trace("Exiting applyActionsForState");

        return;
    }
}
