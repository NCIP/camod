/**
 * @author dgeorge
 * 
 * $Id: AbstractCurationManager.java,v 1.13 2006-10-17 16:13:47 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.12  2006/05/08 13:32:15  georgeda
 * Clean up warnings
 *
 * Revision 1.11  2006/01/18 14:24:23  georgeda
 * TT# 376 - Updated to use new Java 1.5 features
 *
 * Revision 1.10  2005/12/02 14:36:50  georgeda
 * Defect #246, use a linked has map to preserve insertion order when getting keys
 *
 * Revision 1.9  2005/11/28 13:43:26  georgeda
 * Defect #192, handle back arrow for curation changes
 *
 * Revision 1.8  2005/11/14 14:17:47  georgeda
 * Cleanup
 *
 * Revision 1.7  2005/09/19 13:08:28  georgeda
 * Slight change to interface
 *
 */
package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.domain.Curateable;
import gov.nih.nci.camod.service.CurateableAction;
import gov.nih.nci.camod.service.CurateableActionFactory;
import gov.nih.nci.camod.service.CurationManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * AbstractCurationManager: Abstract implementation of the CurationManager.
 */
public abstract class AbstractCurationManager implements CurationManager
{
    protected final Log log = LogFactory.getLog(AbstractCurationManager.class);

    private static final String ACTION_TOKENS = ":,=";

    // Inner class used for mainting the state configuration from the xml file
    private class State
    {
        private String myName = null;
        private List<String> myActions = new ArrayList<String>();
        private Map<String, String> myNextValidStates = new HashMap<String, String>();

        public State(String inName)
        {
            myName = inName;
        }

        public String getName()
        {
            return myName;
        }

        public String getNextState(String inEvent)
        {
            String theNextState = "";
            if (myNextValidStates.containsKey(inEvent))
            {
                theNextState = myNextValidStates.get(inEvent);
            }

            // Invalid event. 
            if (theNextState.equals(""))
            {
                throw new IllegalArgumentException("No matching state for event: " + inEvent);
            }

            return theNextState;
        }

        public List<String> getActions()
        {
            return myActions;
        }

        public void addAction(String inAction)
        {
            myActions.add(inAction);
        }

        public void addNextValidState(String inState,
                                      String inMatchEvent)
        {
            myNextValidStates.put(inMatchEvent, inState);
        }
    }

    // Superclass adds action factory for configurable
    // behavior
    protected CurateableActionFactory myActionFactory = new CurateableActionFactoryImpl();

    protected Map<String, State> myStates = new LinkedHashMap<String, State>();

    private String myDefaultState = null;

    // Initialize the curation model
    protected void init(String inWorkflowFile)
    {
        Document theCurationConfig = null;

        // ///////////////////////////////////////////////////
        // Read in Curation Configuration file.
        // ///////////////////////////////////////////////////
        try
        {
            // Create an instance of the DocumentBuilderFactory
            DocumentBuilderFactory theDocumentBuilderFactory = DocumentBuilderFactory.newInstance();
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

            for (int i = 0; i < theStateList.getLength(); i++)
            {
                Element theStateElement = (Element) theStateList.item(i);

                // Note, these will always be there
                String theName = (String) theStateElement.getElementsByTagName("name").item(0).getFirstChild().getNodeValue();

                if ("true".equals(theStateElement.getAttribute("default")))
                {
                    myDefaultState = theName;
                }

                State theState = new State(theName);

                NodeList theActionsList = theStateElement.getElementsByTagName("actions");

                // Should only be one
                if (theActionsList.getLength() == 1)
                {
                    NodeList theActionNodes = theActionsList.item(0).getChildNodes();

                    for (int j = 0; j < theActionNodes.getLength(); j++)
                    {

                        if ("action".equals(theActionNodes.item(j).getNodeName()))
                        {
                            theState.addAction(theActionNodes.item(j).getFirstChild().getNodeValue());
                        }
                    }
                }

                NodeList theNextValidStatesList = theStateElement.getElementsByTagName("next-valid-states");

                // Should only be one
                if (theNextValidStatesList.getLength() == 1)
                {
                    NodeList theValidStateNodes = theNextValidStatesList.item(0).getChildNodes();

                    for (int j = 0; j < theValidStateNodes.getLength(); j++)
                    {
                        Node theValidStateNode = theValidStateNodes.item(j);

                        NodeList theChildNodes = theValidStateNode.getChildNodes();

                        String theEvent = "all";
                        String theStateName = null;

                        for (int k = 0; k < theChildNodes.getLength(); k++)
                        {
                            Node theChildNode = theChildNodes.item(k);

                            if ("name".equals(theChildNode.getNodeName()))
                            {
                                theStateName = theChildNode.getFirstChild().getNodeValue();
                            }
                            else if ("match-event".equals(theChildNode.getNodeName()))
                            {
                                theEvent = theChildNode.getFirstChild().getNodeValue();
                            }
                        }

                        if (theStateName != null)
                        {
                            theState.addNextValidState(theStateName, theEvent);
                        }
                    }
                }

                myStates.put(theName, theState);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Get the name of all states defined in the xml
     * 
     * @returns a list containing the names of all the states
     */
    public List getAllStateNames()
    {
        log.trace("Entering getAllStateNames");

        List<String> theStateList = new ArrayList<String>();
        Iterator theKeys = myStates.keySet().iterator();

        while (theKeys.hasNext())
        {
            String theKey = (String) theKeys.next();
            State theState = (State) myStates.get(theKey);
            theStateList.add(theState.getName());
        }

        log.debug("State list: " + theStateList);

        log.trace("Exiting getAllStateNames");

        return theStateList;
    }

    /**
     * Get the name of the state that's defined as the "default" state
     * 
     * @returns a list containing the names of all the states
     */
    public String getDefaultState()
    {
        log.trace("In getDefaultState");
        return myDefaultState;
    }

    /**
     * Change to the next state based on the current state and the event
     * 
     * @param inCurateableObj
     *            the curatable object to apply the action to
     * 
     * @param inEvent
     *            the event being applied to the object
     */
    public void changeState(Curateable inCurateableObj,
                            String inEvent)
    {
        log.info("Entering changeState");
        log.info("inEvent: " + inEvent);
        
        String theCurrentStateName = inCurateableObj.getState();
        log.info("Current state: (" + theCurrentStateName + ")");

        if (myStates.containsKey(theCurrentStateName))
        {
            State theCurrentState = (State) myStates.get(theCurrentStateName);
            log.info("Current state: (" + theCurrentStateName + ")");
            
            String theNextStateName = theCurrentState.getNextState(inEvent);
            log.info("theNextStateName: (" + theNextStateName + ")");
            

            if (theNextStateName.equals(""))
            {
                log.debug("Cannot leave current state: " + theCurrentStateName);
            }
            else if (myStates.containsKey(theNextStateName))
            {
                log.info("Setting to state: " + theNextStateName);
                inCurateableObj.setState(theNextStateName);
            }
            else
            {
                throw new IllegalArgumentException("Unknown next state: " + theNextStateName);
            }
        }
        else
        {
            throw new IllegalArgumentException("Unknown current state: " + theCurrentStateName);
        }

        log.trace("Exiting changeState");
    }

    /**
     * Apply any actions associated w/ the state of the curatable object
     * 
     * @param inCurateableObj
     *            is the curatable object to apply actions for
     */
    public void applyActionsForState(Curateable inCurateableObj,
                                     Map<String, Object> inArgs)
    {
        log.info("Entering applyActionsForState");

        String theCurrentStateName = inCurateableObj.getState();

        log.info("Current state: (" + theCurrentStateName + ")");

        if (myStates.containsKey(theCurrentStateName))
        {
            State theCurrentState = (State) myStates.get(theCurrentStateName);
            List theActions = theCurrentState.getActions();
            Iterator theIterator = theActions.iterator();

            while (theIterator.hasNext())
            {
                String theActionEntry = (String) theIterator.next();
                StringTokenizer theTokenizer = new StringTokenizer(theActionEntry, ACTION_TOKENS);
                String theActionName = theTokenizer.nextToken();

                // Get the default arguments for the action
                while (theTokenizer.hasMoreTokens())
                {
                    String theArg = theTokenizer.nextToken();

                    // Break this down into a tag/value pair and add to the
                    // input map
                    StringTokenizer theArgTokenizer = new StringTokenizer(theArg, ACTION_TOKENS);

                    String theTag = theArgTokenizer.nextToken();
                    String theValue = "";

                    if (theArgTokenizer.hasMoreTokens())
                    {
                        theValue = theArgTokenizer.nextToken();
                    }

                    // Add to the input map
                    inArgs.put(theTag, theValue);
                }

                CurateableAction theAction = myActionFactory.getAction(theActionName);

                // Execute the action
                if (theAction != null)
                {
                    log.info("Applying action: " + theActionEntry);

                    // Actions by default are not show stoppers if they
                    // don't work
                    try
                    {
                        theAction.execute(inArgs, inCurateableObj);
                    }
                    catch (Exception e)
                    {
                        log.warn("Unable to process action", e);
                    }
                }
            }

        }
        else
        {
            throw new IllegalArgumentException("Unknown current state: " + theCurrentStateName);
        }

        log.info("Exiting applyActionsForState");

        return;
    }
}
