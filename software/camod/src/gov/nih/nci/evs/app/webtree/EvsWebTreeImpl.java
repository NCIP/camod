package gov.nih.nci.evs.app.webtree;

import gov.nih.nci.evs.app.util.SafeHTMLUtil;
import gov.nih.nci.evs.app.util.*;
import gov.nih.nci.evs.app.webtree.WebNode;
import gov.nih.nci.evs.app.webtree.WebTree;
import gov.nih.nci.lexbig.ext.TreeNode;
import java.util.*;
import javax.swing.tree.*;
import net.sf.ehcache.*;
import org.LexGrid.LexBIG.DataModel.Collections.SortOptionList;
import org.LexGrid.LexBIG.DataModel.Core.CodingSchemeVersionOrTag;
import org.LexGrid.LexBIG.DataModel.Core.ResolvedConceptReference;
import org.LexGrid.LexBIG.LexBIGService.CodedNodeSet;
import org.LexGrid.LexBIG.LexBIGService.LexBIGService;
import org.LexGrid.LexBIG.Utility.Constructors;
import org.LexGrid.concepts.Concept;
import org.apache.log4j.Logger;
import org.springframework.aop.framework.Advised;
import gov.nih.nci.evs.app.util.RecursiveTreeBuilder;
import gov.nih.nci.evs.app.util.RemoteServerUtil;
import org.LexGrid.LexBIG.DataModel.Collections.ResolvedConceptReferenceList;
import org.LexGrid.LexBIG.Exceptions.LBException;
import org.LexGrid.LexBIG.LexBIGService.CodedNodeSet.SearchDesignationOption;
import org.LexGrid.LexBIG.Utility.LBConstants.MatchAlgorithms;
import org.LexGrid.LexBIG.LexBIGService.CodedNodeSet.ActiveOption;

public class EvsWebTreeImpl extends WebTree { 
	
  private static Logger log = Logger.getLogger("EvsWebTreeImpl");	
  
  private int nodeCounter = 0;  
  private static TreeConfiguration config = null;  
  private TreeCacheManager cache = null;   
  private boolean cmdLineCaching = false;     
 
  // tree params   
  private String vocabularyName = null;  
  private String rootNode;  
  private String skinName = null;
  private String formName;  
  private String conceptName = null;  
  private String conceptCode = null;  
  private String displayName = null;  
  private String fieldsToBlank = "";  
  private String roleType = null;  
  private boolean onlyLeaf = true;  
  private String postMsg = null;  
  private boolean cacheFlag = false;  
  private String key = null;  
  private String treeNameKey = "";  
  


  /**
   * Constructor
   *
   * @throws Exception
   */  
  public EvsWebTreeImpl() throws Exception {
    // load tree configuration from properties file (singleton)
    if (config == null) {  
    	System.out.println("EvsWebTreeImpl config == null. " );
      try {
        config = new TreeConfiguration();
        config.loadProperties();
      } catch (Exception e) {      
        System.out.println(this.getClass().getName()      
         + ": Exception occured: " + e.getMessage());
        e.printStackTrace();
        throw new Exception("Exception occured processing evs tree properties - "
                + e.getMessage());
      }                 
    }      
        
    // get singleton instance of treeCacheManager
    cache = TreeCacheManager.getInstance(); 
  }
  
  /**
   * getTree() - Gets tree from EVS
   *
   * @param paramsHT
   *            Hastable
   * @return DefaultMutableTreeNode
   */
  public DefaultMutableTreeNode getTree(Hashtable paramsHT) throws Exception {
    DefaultMutableTreeNode root = null;
    DefaultMutableTreeNode tree = null;
    
    try {         
      if ((paramsHT != null) && (paramsHT.size() > 0)) {            
        // set evs concept tree parameters
        setTreeParams(paramsHT);             
        
        System.out.println("treeNameKey="+treeNameKey);
        
        // build concept tree key
        key = buildConceptKey(vocabularyName, rootNode, treeNameKey);
        
        // set web tree parameters        
        formName = (String) paramsHT.get("formName");                
        conceptName = (String) paramsHT.get("conceptName");
        conceptCode = (String) paramsHT.get("conceptCode");
        displayName = (String) paramsHT.get("displayName");
        fieldsToBlank = (String) paramsHT.get("fieldsToBlank");
        postMsg = (String) paramsHT.get("postMsg");             

        // check if a web tree already exists in cache
        String webTreeKey = key + "$" + onlyLeaf + "$" + formName + "$" + fieldsToBlank;
        
        // first, try to get web tree from cache
        if (cache.getWebTreesCache() != null && cache.getWebTreesCache().isKeyInCache(webTreeKey)) {        
          
          Element treeElement = cache.getWebTreesCache().get(webTreeKey);
          if (treeElement != null) {            
            root = (DefaultMutableTreeNode) treeElement.getValue();
          }
        }
                
        if (root == null) {
          // ** GET CONCEPT TREE (tree) **
          // try to get concept tree from cache
          // determine cacheName from treeNameKey
          Cache conceptTreeCache = null;
          if (treeNameKey != null) {
            if (cache.getCacheManager().cacheExists(treeNameKey)) {
              conceptTreeCache = cache.getCacheManager().getCache(treeNameKey);
            }
            else {
            	cache.getCacheManager().addCache(treeNameKey);
              conceptTreeCache = cache.getCacheManager().getCache(treeNameKey);
            }
           
            Iterator it = conceptTreeCache.getKeys().iterator();
            while (it.hasNext()) {
              String theKey = (String) it.next();           
            }         

            if (conceptTreeCache != null && conceptTreeCache.isKeyInCache(key)) {
              Element treeElement = conceptTreeCache.get(key);
              if (treeElement != null) {              
                tree = (DefaultMutableTreeNode) treeElement.getValue();
              }
            }
          }    
          
           // if tree not loaded from cache, query for it
          if (tree == null) {
            tree = getTreeFromEvs(vocabularyName, rootNode);

//            PrintUtility.printTree(tree);
            
            // add new tree to cache
            if (cacheFlag && tree != null && conceptTreeCache != null) {                     
              Element treeElement = new Element(key, tree);
              conceptTreeCache.put(treeElement);

              // flush cache to disk
              conceptTreeCache.flush();                 
            }
          }                        
           
          // build new WEB TREE and cache it 
          if (tree != null) {    
            root = buildWebTree(tree, onlyLeaf);
            
            // add new web tree to cache
            Element treeElement = new Element(webTreeKey, root);
            cache.getWebTreesCache().put(treeElement);

            // flush web tree cache to disk
            cache.getWebTreesCache().flush();          
          }
        }    
      }
      
      //shutdownCache();
      
    } catch (NumberFormatException nfe) {
    	System.out.println(this.getClass().getName()
      + ": Number format Exception: " + nfe.getMessage());
      throw new Exception("Number format exception: " + nfe.getMessage());
    } catch (Exception e) {
      
      System.out.println(this.getClass().getName()
      + ": Exception occured: " + e.getMessage());
      // System.err.println("Error in getTree() - "+e.toString());
     e.printStackTrace();
      throw new Exception("Exception occured while getting tree - "
              + e.getMessage());
    }
    return root;    
  }
  
  
  private void setTreeParams(Hashtable paramsHT) throws Exception {  
    Hashtable properties = config.getAllConfigs();    
    
    // look for treeName key    
    treeNameKey = (String) paramsHT.get("treeNameKey");    
        
    if (treeNameKey != null && properties.containsKey(treeNameKey)) {
      // use tree properties from config file
      TreeConfiguration treeConfig = (TreeConfiguration) properties.get(treeNameKey);
      vocabularyName = treeConfig.getVocabularyName();
      rootNode = treeConfig.getConceptSearchName();      
      onlyLeaf = treeConfig.getOnlyLeaf();      
      cacheFlag = treeConfig.getCacheFlag();
    } else {
       System.err.println("treeNameKey of "+treeNameKey+" not found in tree configurations." +
               "Attempting to build tree using dynamic URL parameters...");
    }
    
    // set or over-ride tree params from parameters passed in if they exist
    if (paramsHT.get("vocabularyName") != null) {
      vocabularyName = (String) paramsHT.get("vocabularyName");            
    }
      
    if (paramsHT.get("rootConceptNode") != null) {
      rootNode = (String) paramsHT.get("rootConceptNode");            
    }
    
    if (paramsHT.get("cache") != null) {
      cacheFlag = (new Boolean((String) paramsHT.get("cache"))).booleanValue();        
    }
    
    if (paramsHT.get("onlyLeaf") != null) {
      onlyLeaf = (new Boolean((String) paramsHT.get("onlyLeaf"))).booleanValue();        
    }

        
    // clean strings
    rootNode = rootNode.trim();
    vocabularyName = vocabularyName.trim();
    
    // generate error msg for missing required parameters
    String errorMsg = "";
    boolean errorFlag = false;
    if (vocabularyName.length() == 0) {
      errorFlag = true;
      errorMsg += "vocabularyName\n";
    }
    
    if (rootNode.length() == 0) {
      errorFlag = true;
      errorMsg += "rootConceptNode\n";
    }
    
    if (errorFlag) {   
      throw new Exception("required parameters missing or not defined: \n"+errorMsg);
    }
     
  }
  
  
  private String buildConceptKey(String vocabularyName, String rootNode, String treeNameKey) throws Exception {
    String key = null;
    
    key = treeNameKey + "$" + vocabularyName + "$" + rootNode + "$"  +  roleType;

    return key;
    
  }
  
  
  /**
   * Parse the input string (Input String is strings continated with ",")
   *
   * @param configValue
   * @return
   * @throws Exception
   */
  private Vector parseString(String configValue) throws Exception {
    Vector roles = new Vector();
    if (configValue != null) {
      StringTokenizer st = new StringTokenizer(configValue, ",");
      while (st.hasMoreElements()) {
        roles.add((String) st.nextElement());
      }
    } else
      roles = null;
    
    return roles;
  }
  
 
  /**
   * this method calls cabio get tree
   *
   * @param vocabularyName
   * @param conceptSearchName
   * @param direction
   * @param depthLevel
   * @param roles
   * @return
   * @throws Exception
   */
  private DefaultMutableTreeNode getTreeFromEvs(String vocabularyName,
          String conceptSearchName) throws Exception {
    
    DefaultMutableTreeNode tree = null;
    
      try {
        // New utility to get tree one level at a time        
        RecursiveTreeBuilder treeBuilder = new RecursiveTreeBuilder(RemoteServerUtil.createLexBIGService());
        tree = treeBuilder.getTree(vocabularyName, conceptSearchName);
        
        //PrintUtility.printTree(tree);      
		
      } catch (Exception e) {
        // e.printStackTrace();
    	  System.out.println(this.getClass().getName()
        + ": Exception occured: " + e.getMessage());
        System.err.println("Error in getTree() - " + e.toString());
        throw new Exception(e.getMessage());
      }
    return tree;
  }
  
  
  /**
   * Returns the underlying object that the specified proxy is advising.
   *
   * @param proxy the proxy
   *
   * @return the object
   *
   * @throws Exception the exception
   */
  private Object unwrap(Object proxy) throws Exception {
	  if(proxy instanceof Advised){
		  Advised advised = (Advised)proxy;
		  Object realObject = advised.getTargetSource().getTarget();
		  return realObject;
	  } else {
		  return proxy;
	  }
  }
   
  /**
   * this method caches all trees set for caching in config file
   *
   * @throws Exception
   */
  public void cacheTrees(boolean cmdLineFlag) throws Exception {
    try {               
      // if caching from command line, over-ride cacheOnly flag 
      // to enable evs queries   if (cmdLine) {
      
      cmdLineCaching = cmdLineFlag;  
      
      Hashtable properties = config.getAllConfigs(); 
      
      Enumeration enums = properties.keys();
      
      TreeConfiguration treeConfig = null;      
      while (enums.hasMoreElements()) {
        String treeNameKey = (String) enums.nextElement();
        
        treeConfig = (TreeConfiguration) properties.get(treeNameKey);
        
        System.out.println("cacheTrees Processing tree: "+treeNameKey);
        
        String vocabularyName = treeConfig.getVocabularyName().trim();
        String rootNode = treeConfig.getConceptSearchName().trim();      
        boolean onlyLeaf = treeConfig.getOnlyLeaf();       
        boolean cacheFlag = treeConfig.getCacheFlag(); 
        
        if (cacheFlag) {        
          Cache conceptTreeCache = null;
          if (cache.getCacheManager().cacheExists(treeNameKey)) {            
            conceptTreeCache = cache.getCacheManager().getCache(treeNameKey);            
          } else {
            // create cache (uses default cache settings)        
        	  cache.getCacheManager().addCache(treeNameKey);
            conceptTreeCache = cache.getCacheManager().getCache(treeNameKey);
          }          

          // if cache is empty rebuild tree from evs and cache
          if (conceptTreeCache != null & conceptTreeCache.getKeys().size() == 0) {    
            System.out.println(treeNameKey+" tree not found in cache. Re-building from EVS...");

            // build concept tree key
            String key = buildConceptKey(vocabularyName, rootNode, treeNameKey);             

            // build concept tree
            //DefaultMutableTreeNode tree = getTreeFromEvs(vocabularyName, rootNode, direction, flag, attributeSetting, depthLevel, roles);
            DefaultMutableTreeNode tree = getTreeFromEvs(vocabularyName, rootNode); 
            
            if (tree != null) {
              Element treeElement = new Element(key, unwrap(tree));
              conceptTreeCache.put(treeElement);

              // flush cache to disk
              conceptTreeCache.flush();
            }
            
            // a tree has been rebuilt from evs. we need clear any related trees out of the 
            // webTreesCache to out-of-sync issues.
            System.out.println("Checking for associated web trees...");
            Iterator webtree_it = cache.getWebTreesCache().getKeys().iterator();            
            while (webtree_it.hasNext()) {
                String webTreeKey = (String) webtree_it.next();
                if (webTreeKey.startsWith(key)) {
                	cache.getWebTreesCache().remove(webTreeKey);
                	cache.getWebTreesCache().flush();
                }
            }            
          }
          else {
            System.out.println(treeNameKey+" tree already cached. Skipping EVS re-build...\n"+
                    "*Remove cache files for this tree to force re-build from EVS.");
          }
        }
      } // end loop               
      
    } catch (Exception e) {
      // e.printStackTrace();
    	System.out.println(this.getClass().getName()
      + ": Exception occured: " + e.getMessage());
      // System.err.println("Error occured while caching tree() -
      // "+e.toString());
      throw new Exception("Error occured while caching tree() - "
              + e.getMessage());
    }
    
    cmdLineCaching = false;
  }  
  
  /**
   * Builds web tree with the given DefaultMutableTreeNode
   *
   * @param conceptTree
   *            DefaultMutableTreeNode
   * @return DefaultMutableTreeNode
   */
  private DefaultMutableTreeNode buildWebTree(
          DefaultMutableTreeNode conceptTree, boolean onlyLeaf) {
    DefaultMutableTreeNode resultRoot = new DefaultMutableTreeNode();
    nodeCounter = 0;
    
    // iterate thru conceptTree and create a result tree of WebNodes
    // set web node criteria such as id, name, action
    while (conceptTree.getUserObject() == null
            && conceptTree.getNextNode() != null) {
      conceptTree = conceptTree.getNextNode();
    }
    
    if (conceptTree.getUserObject() != null) {
    	Object conceptObj = conceptTree.getUserObject();
    	TreeNode node = (TreeNode)conceptObj;
    	Concept concept = node.getConcept();
    	
        //DescLogicConcept conceptObj = (DescLogicConcept) conceptTree.getUserObject();
        // buildWebNode(Concept concept, int nodeLevel, DefaultMutableTreeNode node, boolean onlyLeaf, boolean usePreferredName)
        WebNode myWebNode = buildWebNode(concept, 0, conceptTree,
                onlyLeaf);
      
        DefaultMutableTreeNode conceptRootWebNode = new DefaultMutableTreeNode(
                myWebNode);
        
        resultRoot.add(conceptRootWebNode);
        
        addChildren(conceptRootWebNode, conceptTree.children(), onlyLeaf);
    } else {
      System.err.println("conceptTree contains all null Concept objects!");
    }
    
    return resultRoot;
  }
  
  /**
   * Iterates through the given enumeration of children, add them to the given
   * node
   *
   * @param node
   *            DefaultMutableTreeNode
   * @param childrenEnum
   *            Enumeration
   */
  private void addChildren(DefaultMutableTreeNode node, Enumeration childrenEnum, boolean onlyLeaf) {
    Map theNodeList = new TreeMap();
    
    // iterate thru children recursively and add to node
    while (childrenEnum.hasMoreElements()) {
      DefaultMutableTreeNode myConceptNode = (DefaultMutableTreeNode) childrenEnum
              .nextElement();
 
      int nodeLevel = myConceptNode.getLevel();
      if(myConceptNode.getLevel() >0){
      }      
      
      //DescLogicConcept myConceptObj = (DescLogicConcept) myConceptNode.getUserObject();
      Object myConceptObj = myConceptNode.getUserObject();
      TreeNode treeNode = (TreeNode)myConceptObj;
      Concept concept = treeNode.getConcept();
      
      if (myConceptObj != null) {
      	// buildWebNode(DescLogicConcept concept, int nodeLevel,DefaultMutableTreeNode node, boolean onlyLeaf, boolean usePreferredName
          WebNode myNode = buildWebNode(concept, nodeLevel, myConceptNode, onlyLeaf);   
          
          DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(
                  myNode);
          
          theNodeList.put(myNode.getName(), childNode);
          addChildren(childNode, myConceptNode.children(), onlyLeaf);
        }
    }
    
    // Add the collection
    Iterator theIterator = theNodeList.keySet().iterator();
    
    while (theIterator.hasNext()) {
      String theKey = (String) theIterator.next();
      DefaultMutableTreeNode theNode = (DefaultMutableTreeNode) theNodeList.get(theKey);
      node.add(theNode);
    }
  }
  
  /**
   * Builds each node
   *
   * @param concept
   * @param nodeLevel
   * @param node
   * @return
   */
  private WebNode buildWebNode(Concept concept, int nodeLevel,
          DefaultMutableTreeNode node, boolean onlyLeaf) {
	  
    String id = concept.getEntityCode();
    String action = "";
    String name = "undefined";
    String info = "";
    
    try {
      
      // check if name was set (not default)
      if (name.equals("undefined")) {
        name = concept.getEntityDescription().getContent();
      }
      
      // set info for popup on hover
      info = concept.getEntityDescription().getContent() + " [" + id + "]";      
     
    } catch (Exception e) {
      System.err.println("Error getting display name - " + e.toString());
    }       
   
    int concept_id_count = 0;
    String idPath = "";

    // Only grab the one node we're interested in
    if (onlyLeaf) {
    	Object object = node.getUserObject();
    	TreeNode tree = (TreeNode)object;
    	Concept c = tree.getConcept();
    	
      //DescLogicConcept theUserObject = (DescLogicConcept) node.getUserObject();
      idPath = c.getEntityCode();
    } else {
      // build id path - get id's    
      Enumeration nodePathEnum = node.preorderEnumeration();     
      while (nodePathEnum.hasMoreElements()) {
        DefaultMutableTreeNode myChildNode = (DefaultMutableTreeNode) nodePathEnum
                .nextElement();
        //DescLogicConcept childObj = (DescLogicConcept) myChildNode.getUserObject();
        Object object = myChildNode.getUserObject();
    	TreeNode tree = (TreeNode)object;
    	Concept c = tree.getConcept();
        if (c != null) {
          String myId = c.getEntityCode();
          concept_id_count++;
          if (idPath.length() == 0) {
            idPath = myId;
          } else {
            idPath += "," + myId;
          }
        }
      }
    }
    
    // temporary fix for Oracle error if over 1000 concept ids in path
    if (concept_id_count <= 1000)
    {
      action = "confirmSelection('" + formName + "','"
              + fixSpace(conceptName) + "','" + fixSpace(concept.getEntityDescription().getContent())
              + "','" + idPath + "','" + conceptCode + "',' "
              + fixSpace(name) + "','" + fixSpace(displayName) + "','"
              + fieldsToBlank + "')";   
    } else {
      action = "alert('The selected term contains too many sub-items for the search to work properly. Please choose a lower level term.')";
    }
    
    // Blank out the top two levels if we're displaying only leaf nodes
    if (onlyLeaf == true && !node.isLeaf() && nodeLevel < 2) {
      action = "";
    }
    return new WebNode(String.valueOf(nodeCounter++), name, action, info);
  }
  
  protected String fixApostrophe(String text) {
    
    StringBuffer sb = new StringBuffer(text);
    
    int start = 0;
    int found = 0;
    while ((found = sb.toString().indexOf('\'', start)) > -1) {
      sb.replace(found, found + 1, "\\%27");
      start = found + 2;
    }
    
    return sb.toString();
  }
  
  protected String fixSpace(String text) {
      text.trim();      
      return text;
    }  

	
	public Vector getStartsWith(String term, String assoc, LexBIGService lbSvc,
			String scheme, CodingSchemeVersionOrTag csvt) throws LBException {
			Vector matchVector = new Vector();
			
			if (scheme != null){				
				csvt = new CodingSchemeVersionOrTag();

			CodedNodeSet nodes = lbSvc.getCodingSchemeConcepts(scheme, csvt)
					.restrictToStatus(ActiveOption.ALL, null)
					.restrictToMatchingDesignations(
							term,
							SearchDesignationOption.ALL,
							MatchAlgorithms.startsWith
									.toString(), null);
			
			// Sort by search engine recommendation & code ...
			SortOptionList sortCriteria = Constructors
					.createSortOptionList(new String[] { "matchToQuery", "code" });

			// Analyze the result ...
			ResolvedConceptReferenceList matches = nodes.resolveToList(
					sortCriteria, null, null, 10);
			if (matches.getResolvedConceptReferenceCount() > 0) {
				for (Enumeration refs = matches
						.enumerateResolvedConceptReference(); refs
						.hasMoreElements();) {
					ResolvedConceptReference ref = (ResolvedConceptReference) refs
							.nextElement();
					matchVector.add(ref.getEntityDescription().getContent());
				}
			} else {
				System.out.println("No match found!");
			}
		}
		return matchVector;
	} 	
	
	public Vector getContains(String term, String assoc, LexBIGService lbSvc,
			String scheme, CodingSchemeVersionOrTag csvt) throws LBException {
			Vector matchVector = new Vector();
			
			if (scheme != null){				
				csvt = new CodingSchemeVersionOrTag();

			CodedNodeSet nodes = lbSvc.getCodingSchemeConcepts(scheme, csvt)
					.restrictToStatus(ActiveOption.ALL, null)
					.restrictToMatchingDesignations(
							term,
							SearchDesignationOption.ALL,
							MatchAlgorithms.contains
									.toString(), null);
			
			// Sort by search engine recommendation & code ...
			SortOptionList sortCriteria = Constructors
					.createSortOptionList(new String[] { "matchToQuery", "code" });

			// Analyze the result ...
			ResolvedConceptReferenceList matches = nodes.resolveToList(
					sortCriteria, null, null, 10);
			if (matches.getResolvedConceptReferenceCount() > 0) {
				for (Enumeration refs = matches
						.enumerateResolvedConceptReference(); refs
						.hasMoreElements();) {
					ResolvedConceptReference ref = (ResolvedConceptReference) refs
							.nextElement();
					matchVector.add(ref.getEntityDescription().getContent());
				}
			} else {
				System.out.println("No match found!");
			}
		}
		return matchVector;
	}  	
	
	public Vector getExactMatch(String term, String assoc, LexBIGService lbSvc,
			String scheme, CodingSchemeVersionOrTag csvt) throws LBException {
			Vector matchVector = new Vector();
			
			if (scheme != null){				
				csvt = new CodingSchemeVersionOrTag();

			CodedNodeSet nodes = lbSvc.getCodingSchemeConcepts(scheme, csvt)
					.restrictToStatus(ActiveOption.ALL, null)
					.restrictToMatchingDesignations(
							term,
							SearchDesignationOption.ALL,
							MatchAlgorithms.exactMatch
									.toString(), null);

			// Sort by search engine recommendation & code ...
			SortOptionList sortCriteria = Constructors
					.createSortOptionList(new String[] { "matchToQuery", "code" });

			// Analyze the result ...
			ResolvedConceptReferenceList matches = nodes.resolveToList(
					sortCriteria, null, null, 10);
			if (matches.getResolvedConceptReferenceCount() > 0) {
				for (Enumeration refs = matches
						.enumerateResolvedConceptReference(); refs
						.hasMoreElements();) {
					ResolvedConceptReference ref = (ResolvedConceptReference) refs
							.nextElement();
					matchVector.add(ref.getEntityDescription().getContent());
				}
			} else {
				System.out.println("No match found!");
			}
		}
		return matchVector;
	}  	
  

	  public Vector getSynonyms(String term, String algorithm) throws Exception {
	    Vector synonymVector = new Vector();
	    
	    String serchText = SafeHTMLUtil.clean(term);
	    System.out.println("EvsWebTreeImpl cleaned serchText: " + serchText);
	    
	    try {
	    	LexBIGService lbSvc = RemoteServerUtil.createLexBIGService();    
	    	
	    	if(algorithm != null) {
		    	if(algorithm.equals("contains")){
		    		synonymVector = getContains(serchText, "subClassOf", lbSvc,	vocabularyName, null);	
		    	} else if(algorithm.equals("exactMatch")){
		    		synonymVector = getExactMatch(serchText, "subClassOf", lbSvc, vocabularyName, null);
		    	} else {
		    		synonymVector = getStartsWith(serchText, "subClassOf", lbSvc,	vocabularyName, null);	
		    	} 
	    	}	    	
	    } catch (Exception ex) {
	        log.debug(this.getClass().getName()
	        		+ ": Get EVS Results Synonym Exception: "
	        		+ ex.getMessage());
	        throw new Exception("Get EVS Resutls Synonym exception: "
	        		+ ex.getMessage());    
	    }	    
	    return synonymVector;	    
	  }
  
  /**
   * Implementation of getPostSearchMsg
   */
  public String getPostSearchMsg(String formName, String skin,
          String conceptName, String conceptCode, String formDisplayName,
          String postMsg) throws Exception          
  {
    // mesage displayed at end of search results
    String msg = "";
    try {
      
      if (postMsg != null && postMsg.equals("true")) {
        msg = "</BR><B><I>If you are unable to find a satisfactory diagnosis, you may manually submit one.&nbsp;&nbsp;<A target='_top' href=\"javascript:setManualDiagnosis('"
                + formName
                + "', '"
                + conceptName
                + "','"
                + conceptCode
                + "','"
                + formDisplayName
                + "')\"><img src='skins/"
                + skin
                + "/images/submitButton.gif' height='18' hspace='0' vspace='0' border='0' align='top' width='50'/></a></i></b>";
      } else {
        msg = "";
      }
    } catch (Exception ex) {
      System.out.println(this.getClass().getName()
      + ": Post Msg Exception: " + ex.getMessage());
      throw new Exception("Load Sub Concept exception: "
              + ex.getMessage());      
    }
    return msg;
  }

     
  /**
   * Sets the current skin name.
   * <p>
   *
   * @param skin
   *            A string which corresponds to a directory name in the
   *            webtree/skins directory.
   */
  public void setSkin(String skin) {
    skinName = skin;    
  }
  
  /**
   * Returns the current skin name
   * <p>
   *
   * @return A string containing the name of the current skin corresponding to
   *         the directory name in the webtree/skins directory.
   */
  public String getSkin() {
    return skinName;
  }


 
}
