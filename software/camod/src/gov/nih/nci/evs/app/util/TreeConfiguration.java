/*
 * Created on Oct 18, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package gov.nih.nci.evs.app.util;

/**
 * @author MashettS
 * 
 * This class loads properties from properties file. for the webtree.
 */

import java.util.*;

public class TreeConfiguration {

	private Properties _properties = new Properties();
  
  private Hashtable _allConfigs = null;

  // general properties
  
	private String serverURL = null;
  
  private boolean disableEVSQuery = false;    
  
  private int totalTrees = 0;
  
  // tree properties    

	private String _conceptSearchName = null;

	private String _vocabularyName = null;	

    private boolean _cacheFlag = false;
  
	private boolean _onlyLeaf = true;

	private static final String propertyFile = "evsconfig.properties";


  public void loadProperties() throws NumberFormatException, Exception {
	    System.out.println("Entered TreeConfiguration.loadProperties method" );
		System.out.println("reading property file: " + propertyFile);

		_properties.load(getClass().getResourceAsStream(propertyFile));

    // set general properties
    
		setServerURL((String) _properties.get("serverURL"));
    
    totalTrees = (new Integer((String) _properties.get("totalTrees"))).intValue();
		System.out.println("Total pre-configured trees = " + totalTrees);
    
    disableEVSQuery = (new Boolean((String) _properties.get("cacheOnly"))).booleanValue();       
    
    // load tree configuration(s)
    
    Hashtable allConfigs = new Hashtable();
    
    for (int i = 1; i <= totalTrees; i++) {
			String index = (new Integer(i)).toString();
			TreeConfiguration config = new TreeConfiguration();     
            
      String treeNameKey = ((String) _properties.get("treeNameKey" + index));
      
      System.out.println("tree #"+i+": "+treeNameKey);

      config.setConceptSearchName((String) _properties.get("rootConceptNode" + index));

      if (_properties.containsKey("onlyLeaf" + index)) {
        config.setLeafFlag((new Boolean((String) _properties.get("onlyLeaf"
            + index))).booleanValue());				
      }
    
      if (_properties.containsKey("cache" + index)) {
        config.setCacheFlag((new Boolean((String) _properties.get("cache"
            + index))).booleanValue());				
      }
      
      config.setVocabularyName((String) _properties.get("vocabularyName" + index));

      allConfigs.put(treeNameKey, config);        
		}     

		_allConfigs = allConfigs;
		
		System.out.println("Exited TreeConfiguration.loadProperties method" );
    
	}    	

	/**
	 * Parse the input string (Input String is strings continated with ",")
	 * 
	 * @param configValue
	 * @return
	 * @throws Exception
	 */
	private Vector parseString(String configValue, Vector v) throws Exception {
		System.out.println("Entered TreeConfiguration.parseString method" );
		v = new Vector();
		if (configValue != null) {
			StringTokenizer st = new StringTokenizer(configValue, ",");
			while (st.hasMoreElements()) {
				v.add((String) st.nextElement());
			}
		} else
			v = null;
		System.out.println("Exited TreeConfiguration.parseString method" );
		return v;
	}

	public String getServerURL() {
		return serverURL;
	}

	public String getVocabularyName() {
		return _vocabularyName;
	}
  
  public boolean getCacheFlag() {
		return _cacheFlag;
	}
  
  public void setCacheFlag(Boolean b1) {
		_cacheFlag = b1;
	}
    
	public String getConceptSearchName() {
		return _conceptSearchName;
	}

	public boolean getOnlyLeaf() {
		return _onlyLeaf;
	}

	public void setServerURL(String serverURL) {
		this.serverURL = serverURL;
	}

	public void setVocabularyName(String name) {
		_vocabularyName = name;
	}

	public void setConceptSearchName(String name) {
		_conceptSearchName = name;
	}

	public void setLeafFlag(boolean bool) {
		_onlyLeaf = bool;
	}

	public Hashtable getAllConfigs() {
		return _allConfigs;
	}
  
  public boolean isDisableEVSQuery() {
    return disableEVSQuery;
  }

}
