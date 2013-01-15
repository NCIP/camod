package gov.nih.nci.evs.app.webtree;

//import gov.nih.nci.evs.app.util.MessageLog;
import java.util.*;
import javax.swing.tree.*;
import javax.servlet.http.*;

/**
 * Service class for webtree
 * 
 * @author MashettS
 * 
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class WebTreeService {
	private DefaultMutableTreeNode root = null;
	private DefaultMutableTreeNode displayRoot = null;
	private static WebTree webTree = null;
	private Hashtable paramsHT = null;
	private Vector synonymsVec = null;
	private String skinForMessage = null;      

	/**
	 * Constructor
	 * 
	 * @param treeClassName
	 * @param treeClassParams
	 * @param skin
	 * @throws Exception
	 */
	public WebTreeService(String treeClassParams, String skin) throws Exception {

	  // build the root tree using the class name specified
	try { 
      webTree = (WebTree) new EvsWebTreeImpl();
      //webTree = (WebTree) (Class.forName(treeClassName).newInstance());      
			if (treeClassParams == null) {
				System.err.println("Null values passed to WebTreeService");
			}
			paramsHT = parseParameters(treeClassParams);
            
			root = webTree.getTree(paramsHT); 
			
			skinForMessage = skin;
			webTree.setSkin(skin);
		} catch (Exception e) {

			System.out.println(this.getClass().getName()
							+ " -WebTreeService-: Exception occured: "
							+ e.getMessage());
			throw new Exception(e.getMessage());
		}
	}

	/**
	 * Parses the given string and builds Hashtable
	 * 
	 * @param params
	 *            String
	 * @return Hashtable
	 * @throws Exception
	 */
	private Hashtable parseParameters(String params) throws Exception {
		// parses a string of parameters defined with the following syntax:
		// name1:value1;name2:value2;

		Hashtable results = new Hashtable();

		if (params != null && !params.equals("null")) {
			StringTokenizer st = new StringTokenizer(params, ";");
			while (st.hasMoreTokens()) {
				String pair = st.nextToken();

				StringTokenizer stPair = new StringTokenizer(pair, "=");
				String name = null;
				String value = null;
				if (stPair.countTokens() == 2) {
					name = stPair.nextToken();
					value = stPair.nextToken();
					results.put(name, value);

				} else {
					System.err.println("invalid 'name=value' pair parameter"
							+ name + value);
					throw (new Exception("invalid 'name=value' pair parameter"));
				}
			}
		}
		return results;
	}

	/**
	 * Gets Display tree based on treeAction, targetId, treeDirective
	 * 
	 * @param treeAction
	 * @param targetId
	 * @param userSession
	 * @param treeDirective
	 * @return
	 * @throws Exception
	 */
	public DefaultMutableTreeNode getDisplayTree(String treeAction,
			String targetId, HttpSession userSession) throws Exception {

		// no tree action, assume new tree to build in GUI, put root node in display
		// tree and expand on it
		if (treeAction == null) {
			DefaultMutableTreeNode rootNode = root;
			
			if (rootNode != null) {				
				if (rootNode.getChildCount() > 0) {
					// finding non null node object
					while (rootNode.getUserObject() == null && rootNode.getNextNode() != null) {
						rootNode = rootNode.getNextNode();
					}
					if (rootNode.getUserObject() == null)
						throw (new Exception(
								"root tree contains all null objects!"));

					// construct display tree
					WebNode myWebNode = (WebNode) rootNode.getUserObject();
					if (!rootNode.isLeaf())						
						myWebNode.setHasChildren(true);
					displayRoot = new DefaultMutableTreeNode((WebNode) rootNode.getUserObject());
				
					// default behavior is for tree to expand its root node,
					// however this behavior can be over-ridden through the 
					// declaration of a treeDirective.
					targetId = ((WebNode) rootNode.getUserObject()).getId();
					treeAction = "expand";

				} else {
					System.err.println("Error: Root Tree is Empty!");
					throw (new Exception("Root Tree is Empty"));
				}
			} else {
				System.err.println("Error: Root Tree is Null!");
				throw (new Exception("Root Tree is Null"));
			}
		}

		// Process Tree Actions
		if (treeAction.equals("expand")) {
			expandTree(targetId);
		} else if (treeAction.equals("collapse")) {
			collapseTree(targetId);
		} else if (treeAction.equals("highlight")) {
			expandToNode(targetId);
		}
		return displayRoot;
	}

	/**
	 * process tree directive
	 * 
	 * @param directive
	 * @return
	 */
	private boolean processDirective(String directive) {
		boolean success = false;
		// parse directive instructions
		try {
			Hashtable directivesHT = parseParameters(directive);

			if (directivesHT.containsKey("openToName")) {
				String value = (String) directivesHT.get("openToName");
				// ///////////////// Not sure about this directive
				// success = expandToNodeByName(value, true);
				// /////////////////////////////////////////////
			}
		} catch (Exception e) {
			System.err.println("Error parsing tree directive string!");
		}
		return success;
	}

	/**
	 * Displays children of node specified by targetId
	 * 
	 * @param targetId
	 */
	private void expandTree(String targetId) {
		// search root and root trees for targetId,
		// and copy 1st level children from root to displayRoot.
		Enumeration rootTreeEnum = root.preorderEnumeration();
		while (rootTreeEnum.hasMoreElements()) {
			DefaultMutableTreeNode myRootNode = (DefaultMutableTreeNode) rootTreeEnum
					.nextElement();
			WebNode myRootObj = (WebNode) myRootNode.getUserObject();

			if (myRootObj != null) {
				String myRootId = myRootObj.getId();

				// find in root tree
				if (myRootId.equals(targetId)) {
					// now find node in display tree
					Enumeration displayTreeEnum = displayRoot
							.preorderEnumeration();
					while (displayTreeEnum.hasMoreElements()) {
						DefaultMutableTreeNode myDisplayNode = (DefaultMutableTreeNode) displayTreeEnum
								.nextElement();
						WebNode myDisplayObj = (WebNode) myDisplayNode
								.getUserObject();
						if (myDisplayObj != null) {
							String myDisplayId = myDisplayObj.getId();
							if (myDisplayId.equals(targetId)) {
								// get children of this node from the root tree
								Enumeration rootChildrenEnum = myRootNode
										.children();
								while (rootChildrenEnum.hasMoreElements()) {
									DefaultMutableTreeNode myChildNode = (DefaultMutableTreeNode) rootChildrenEnum
											.nextElement();
									WebNode myChildObj = (WebNode) myChildNode
											.getUserObject();
									if (myChildObj != null) {
										// add child node webNode in the to display tree
										if (!myChildNode.isLeaf())
											myChildObj.setHasChildren(true);
										myDisplayNode
												.add(new DefaultMutableTreeNode(
														myChildObj));
									}
								} // end children while loop
							} // end match root targetId in display tree
						} // end null check
					} // end display tree while loop

				} // end root targetId match in root tree
			} // end obj null check
		} // end root tree while loop
	}

	/**
	 * collpase children of node specified by targetId
	 * 
	 * @param targetId
	 */
	private void collapseTree(String targetId) {
		// search display tree for node id and remove all children
		Enumeration displayTreeEnum = displayRoot.preorderEnumeration();
		while (displayTreeEnum.hasMoreElements()) {
			DefaultMutableTreeNode myDisplayNode = (DefaultMutableTreeNode) displayTreeEnum
					.nextElement();
			WebNode myDisplayObj = (WebNode) myDisplayNode.getUserObject();
			if (myDisplayObj != null) {
				String myDisplayId = myDisplayObj.getId();
				if (myDisplayId.equals(targetId)) {
					myDisplayNode.removeAllChildren();
				}
			}
		}
	}

	/**
	 * expands tree down to node specified by targetId
	 * 
	 * @param targetId
	 */
	private void expandToNode(String targetId) {
		// (used for search hi-lighting)
		// search root tree for node id
		
		Enumeration rootTreeEnum = root.preorderEnumeration();
		while (rootTreeEnum.hasMoreElements()) {
			DefaultMutableTreeNode myRootNode = (DefaultMutableTreeNode) rootTreeEnum
					.nextElement();
			WebNode myRootObj = (WebNode) myRootNode.getUserObject();
			if (myRootObj != null) {
				String myRootId = myRootObj.getId();
				if (myRootId.equals(targetId)) {
					// node found, get path to node, search displayRoot for each
					// node in path and expand up to WebNode Id
					TreeNode myPath[] = myRootNode.getPath();
					for (int node = 0; node < myPath.length; node++) {
						DefaultMutableTreeNode testNode = (DefaultMutableTreeNode) myPath[node];
						WebNode testRootObj = (WebNode) testNode
								.getUserObject();
						if (testRootObj != null) {
							String testRootId = testRootObj.getId();
							// check if this node is already in the display tree
							boolean foundNode = false;
							Enumeration displayTreeEnum = displayRoot
									.preorderEnumeration();
							while (displayTreeEnum.hasMoreElements()) {
								DefaultMutableTreeNode myDisplayNode = (DefaultMutableTreeNode) displayTreeEnum
										.nextElement();
								WebNode myDisplayObj = (WebNode) myDisplayNode
										.getUserObject();
								if (myDisplayObj != null) {
									String myDisplayId = myDisplayObj.getId();
									if (myDisplayId.equals(testRootId)) {
										foundNode = true;
									}
								}
							}

							// check if node was not found
							if (!foundNode) {
								// node not found in display tree, expand its
								// parent
								DefaultMutableTreeNode parentNode = (DefaultMutableTreeNode) testNode
										.getParent();
								if (parentNode != null) {
									WebNode parentRootObj = (WebNode) parentNode
											.getUserObject();
									if (parentRootObj != null) {
										expandTree(parentRootObj.getId());
									}
								}
							}

						}

					} // end for loop for parents
				}
			}
		} // end while loop for search root tree
	}

	public Vector searchTree(String searchTerm, String algorithm) {
		Vector searchResults = null;
		
		Vector searchTermsVec = new Vector();
		searchTermsVec.addElement(searchTerm);

		if (webTree != null) {
			// get alternate synonym search terms
			try {        
				synonymsVec = webTree.getSynonyms(searchTerm, algorithm);

				// add synonyms to the searchTermsVec
				if (synonymsVec != null) {
					Enumeration synonymsEnum = synonymsVec.elements();
					while (synonymsEnum.hasMoreElements()) {
						searchTermsVec.add((String) synonymsEnum.nextElement());
					}
				} else {

					searchResults = null;
				}
			} catch (Exception e) {
				System.err.println("Error in getSynonyms - " + e.toString());
				System.out.println(this.getClass().getName()
						+ "Erro in getSynonyms(): " + e.getMessage());
			}
		}

		// search root tree for search terms from LExEVS API
		if (root != null) {
			searchResults = new Vector();

			Enumeration rootTreeEnum = root.preorderEnumeration();
			while (rootTreeEnum.hasMoreElements()) {
				DefaultMutableTreeNode myRootNode = (DefaultMutableTreeNode) rootTreeEnum
						.nextElement();
				WebNode myWebNode = (WebNode) myRootNode.getUserObject();

				if (myWebNode != null) {
					String myName = myWebNode.getName().toLowerCase();
					String myId = myWebNode.getId();

					// loop through search terms vector
					Enumeration searchTermsEnum = searchTermsVec.elements();
					while (searchTermsEnum.hasMoreElements()) {
						String mySearchTerm = ((String) searchTermsEnum
								.nextElement()).toLowerCase();

						if (myName.indexOf(mySearchTerm) > -1) {
							// match found, add to results if not already in
							// there
							if (!searchResults.contains(myWebNode)) {
								searchResults.addElement(myWebNode);
							}
						}

					}
				}
			}
		}
		// returns Vector of matching terms in tree
		return searchResults;
	}

	public Vector getSynonyms() {
		return synonymsVec;
	}

	public String getPostSearchMsg() {
		String rootNode = null;
		String formName = null;
		String conceptName = null;
		String conceptCode = null;
		String displayName = null;
		String postMsg = null;
		String retPostMsg = null;
		try {

			formName = (String) paramsHT.get("formName");
			conceptName = (String) paramsHT.get("conceptName");
			conceptCode = (String) paramsHT.get("conceptCode");
			displayName = (String) paramsHT.get("displayName");
			postMsg = (String) paramsHT.get("postMsg");
			retPostMsg = webTree.getPostSearchMsg(formName, skinForMessage,
					conceptName, conceptCode, displayName, postMsg);

		} catch (Exception e) {
			System.out.println(this.getClass().getName()
					+ " - WebTreeService synonym search - Exception occured: "
					+ e.getMessage());

		}

		return retPostMsg;
	}

}
