
package gov.nih.nci.semantic.app;


import gov.nih.nci.evs.query.*;
import gov.nih.nci.semantic.util.Configuration;
import gov.nih.nci.system.dao.EVSDAOFactory;
import gov.nih.nci.system.applicationservice.*;

import gov.nih.nci.system.delegator.*;
import gov.nih.nci.evs.domain.*;
import gov.nih.nci.common.net.*;
import org.apache.log4j.*;
import java.util.*;

public class EVSImpl {

    private ArrayList evsValues;
    private static String vocabularyName;
    private boolean flag = false;
    private EVSQuery evsQuery;
    private String serverURL;

    private ArrayList possibleOptions1;
    private ArrayList separateWords1;
    private Set possibleOptions;
    private Set separateWords;
    ApplicationService appService;
    private static Logger log = Logger.getLogger(EVSImpl.class.getName());

	public EVSImpl(){

		vocabularyName = Configuration.getVocabularyName();
		serverURL = Configuration.getServerURL();
		evsValues = new ArrayList();
		evsQuery = new EVSQueryImpl();

		possibleOptions = new HashSet();
		separateWords = new HashSet();
		ApplicationServiceProvider.getApplicationService();
	}

	/**
	 * get tagged values
	 * @param umlEntities
	 * @return
	 */
	public ArrayList getTaggedValues(ArrayList umlEntities) throws Exception
	{
		try
		{
			HashMap map = null;

			for(int i=0; i<umlEntities.size(); i++)
			{
				map = (HashMap)umlEntities.get(i);
				String columnName = (String)map.get(Configuration.getUMLEntityCol());

				evaluateString(columnName);

				//populate modifieddate, verified fields
				map.put(Configuration.getModifiedDateCol(), null);
				map.put(Configuration.getVerifiedFlagCol(), "0");

				//get evs values
				getEVSValues(map);

				//add this map to the list
				evsValues.add(map);

				possibleOptions.clear();
				separateWords.clear();
			}
			//debug
			//testOutput(evsValues);

		}
		catch(Exception e)
		{
		    log.error("Exception in getTaggedValues: "+e.getMessage());
			throw new Exception("Exception in getTaggedValues: "+e.getMessage());
		}
		return evsValues;
	}


	/**
	 * copy values to new hash
	 * @param entitiesMap
	 * @param newMap
	 */
	private void copyHash(HashMap entitiesMap, HashMap newMap) {

		for(Iterator iter=entitiesMap.keySet().iterator(); iter.hasNext();)
		{
			String key = (String)iter.next();
			//System.out.println("entitiesMap key: " + key);
			newMap.put(key, entitiesMap.get(key));
		}
	}


	/**
	 * Gets concept code for the given name
	 * @param name
	 * @return
	 */
    private String getConceptCode(String name) throws Exception
    {
    	//System.out.println("EVSImpl - getConceptCode..."+name);
    	String code = null;
    	try
    	{
			//getting concept code
    		evsQuery = new EVSQueryImpl();
    		evsQuery.getConceptCodeByName(vocabularyName,name);
    		code = (String)getObject(evsQuery);
			//System.out.println("Getting concept code for "+name);
    		if(code!=null){
    			//System.out.println("Concept code = "+ code );
    			}
    		else{
    			//System.out.println("Cannot locate concept code...");
    			}

    	}
    	catch(Exception e)
    	{
    	   log.error("Exception occured while getting concept code: "+e.getMessage());
    		throw new Exception("Exception occured while getting concept code: "+e.getMessage());
    	}

    	return code;
    }


	private void getEVSValues(HashMap entitiesMap) throws Exception {

		String umlClass = (String)entitiesMap.get(Configuration.getUMLClassCol());
		String umlEntity = (String)entitiesMap.get(Configuration.getUMLEntityCol());
		String classification = null;
		boolean found = false, isAlreadyInList=false, first=true, isConceptCodeInMap=false, isConceptNameInMap=false;
		HashMap newMap = null;
		//if the umlclass and uml entity are equal, then it is class.. else it is attribute
		if(umlClass.equalsIgnoreCase(umlEntity))
			classification = Configuration.getClassTag();
		else
		classification = Configuration.getAttributeTag();

		// search for all possibleOptions
		String name="";
		Iterator itr = possibleOptions.iterator();
		while( itr.hasNext())
		{
			Object temp = (Object)itr.next();
			name = temp.toString();
			String conceptCode = getConceptCode(name);

			System.out.println("\nWhole word term = " + name + ", conceptCode = " + conceptCode  );
			if(conceptCode != null)
			{
				found = true;
				entitiesMap.put(Configuration.getConceptCodeCol(), conceptCode);
				entitiesMap.put(Configuration.getConceptName(), name);
				entitiesMap.put(Configuration.getClassificationCol(), classification);
				getProperties(name, entitiesMap);

				//Future: multiple code enhancement..
				//Assuming that we get the secondary concept codes from the properties
			}
			else
			{
				System.out.println("getting synonyms for term = " + name);
				String[] concepts = getSynonyms(name);
				first = true;
				newMap = null;

				for(int k=0; k<concepts.length; k++)
				{
					name = concepts[k];
					//get conceptcode for each synonym concept
					conceptCode = getConceptCode(name);

					System.out.println("synonym term = " + name + ", concept code = " + conceptCode  );
					if(conceptCode != null)
					{

						if(first && !found)
						{
						  //add first one to the existing map
						  entitiesMap.put(Configuration.getConceptCodeCol(), conceptCode);
						  entitiesMap.put(Configuration.getConceptName(), name);
						  entitiesMap.put(Configuration.getClassificationCol(), classification);
						  getProperties(name, entitiesMap);
						  first = false;
						}
						else
						{
							//since, multiple synonyms, create new map, and add to evsValues List
							//if it's not already in the List and the entitiesMap
							newMap = new HashMap();
							//copy all other values from the above entitiesMap
							copyHash(entitiesMap, newMap);
							newMap.put(Configuration.getConceptCodeCol(), conceptCode);
							newMap.put(Configuration.getConceptName(), name);
							newMap.put(Configuration.getClassificationCol(), classification);
							getProperties(name, newMap);
							isConceptCodeInMap = entitiesMap.containsValue(conceptCode);
							isConceptNameInMap = entitiesMap.containsValue(name);
							isAlreadyInList = evsValues.contains(newMap);
							//System.out.println("isAlreadyInMap " + isConceptCodeInMap + " " + isConceptNameInMap + "isAlreadyInList: " + isAlreadyInList);
							if(!isAlreadyInList && !(isConceptCodeInMap && isConceptNameInMap))
								evsValues.add(newMap);
						}
						found = true;
					}
				} // end for loop
			} // end if/else statement

		} // end while loop

		// search individual term if not found in possibleOptions

		if(!found)
		{
			System.out.println("\nSearch for individual term if there is any.");
			Iterator iterator = separateWords.iterator();
			first = true; found = false;
			while(iterator.hasNext())
			{
				Object temp = (Object)iterator.next();
				name = temp.toString();
				String conceptCode = getConceptCode(name);
				newMap = null;
				System.out.println("individual term = " + name + ", conceptCode = " + conceptCode );
				if(conceptCode != null)
				{
					if(first && !found)
					{
						entitiesMap.put(Configuration.getConceptCodeCol(), conceptCode);
						entitiesMap.put(Configuration.getConceptName(), name);
						entitiesMap.put(Configuration.getClassificationCol(), classification);
						getProperties(name, entitiesMap);
						first = false;
					}
					else
					{
						//since, multiple synonyms, create new map, and add to evsValues List
						//if it's not already in the List and the entitiesMap
						newMap = new HashMap();
						//copy all other values from the above entitiesMap
						copyHash(entitiesMap, newMap);
						newMap.put(Configuration.getConceptCodeCol(), conceptCode);
						newMap.put(Configuration.getConceptName(), name);
						newMap.put(Configuration.getClassificationCol(), classification);
						getProperties(name, newMap);
						isConceptCodeInMap = entitiesMap.containsValue(conceptCode);
						isConceptNameInMap = entitiesMap.containsValue(name);
						isAlreadyInList = evsValues.contains(newMap);
						//System.out.println("isAlreadyInMap " + isConceptCodeInMap + " " + isConceptNameInMap + "isAlreadyInList: " + isAlreadyInList);
						if(!isAlreadyInList && !(isConceptCodeInMap && isConceptNameInMap))
							evsValues.add(newMap);
					}
					found = true;

					//Future: multiple code enhancement..
					//Assuming that we get the secondary concept codes from the properties
				}
				else
				{
					System.out.println("getting synonyms for term = " + name);
					String[] concepts = getSynonyms(name);
					for(int k=0; k<concepts.length; k++)
					{
						name = concepts[k];
						//get conceptcode for each synonym
						conceptCode = getConceptCode(name);

						System.out.println("synonym term = "+ name + ", conceptCode = " + conceptCode );
						if(conceptCode != null)
						{
							if(first && !found)
							{
							  entitiesMap.put(Configuration.getConceptCodeCol(), conceptCode);
							  entitiesMap.put(Configuration.getConceptName(), name);
							  entitiesMap.put(Configuration.getClassificationCol(), classification);
							  getProperties(name, entitiesMap);
							  first = false;
							}
							else
							{
								//since, multiple synonyms, create new map, and add to evsValues List
								//if it's not already in the List and the entitiesMap
								newMap = new HashMap();
								//copy all other values from the above entitiesMap
								copyHash(entitiesMap, newMap);
								newMap.put(Configuration.getConceptCodeCol(), conceptCode);
								newMap.put(Configuration.getConceptName(), name);
								newMap.put(Configuration.getClassificationCol(), classification);
								getProperties(name, newMap);
								isConceptCodeInMap = entitiesMap.containsValue(conceptCode);
								isConceptNameInMap = entitiesMap.containsValue(name);
								isAlreadyInList = evsValues.contains(newMap);
								//System.out.println("isAlreadyInMap " + isConceptCodeInMap + " " + isConceptNameInMap + "isAlreadyInList: " + isAlreadyInList);
								if(!isAlreadyInList && !(isConceptCodeInMap && isConceptNameInMap))
									evsValues.add(newMap);
							}
						}
					} // end for loop
				} // end if/else statement
			} // end while loop
		} // end if statement
	}



    private String[] getConceptCodes(String name) throws Exception
    {
    	//System.out.println("EVSImpl - getConceptCodes...");
    	String[] codes = null;
    	try
    	{
			//getting concept code
    		evsQuery.getConceptCodeByName(vocabularyName,name);
			//System.out.println("Getting concept code for "+name);
    		codes = (String[])getObjects(evsQuery);

    	}
    	catch(Exception e)
    	{
    	    log.error("Exception occured while getting concept code: "+e.getMessage());
    		throw new Exception("Exception occured while getting concept code: "+e.getMessage());
    	}

    	return codes;
    }



    /**
     * get synonyms
     * @param name
     * @return
     */
    public String[] getSynonyms(String name) throws Exception
    {
    	//System.out.println("getSynonyms...");
    	String[] concepts = null;

    	try
		{
    		//evsQuery.getConceptWithPropertyMatching(vocabularyName, "Synonym", name, Configuration.getLimit());

    	    evsQuery.getConceptWithPropertyMatching(vocabularyName, "Synonym", name,3);

    	    //List results = getResults(evsQuery);
    	    Object[] results = getObjects(evsQuery);
    	    concepts = new String[results.length];
    	    for(int i=0; i<results.length; i++){
    	        concepts[i]= (String)results[i];
    	        //System.out.println(concepts[i]);
    	    }


		}
    	catch(Exception e)
		{
    	    log.error("Exception occured while getting synonyms: "+e.getMessage());
    		throw new Exception("Exception occured while getting synonyms: "+e.getMessage());
		}
    	return concepts;
    }



	/**
	 * Gets properties for the given name, and parse the properties.
	 * @param name
	 * @throws Exception
	 */
	private void getProperties(String name, HashMap entitiesMap) throws Exception
	{
		//System.out.println("EVSImpl getProperties...");

	  try{
	  	evsQuery = new EVSQueryImpl();
	  	evsQuery.getPropertiesByConceptName(vocabularyName, name);
	  	//System.out.println("Getting properties for "+name);

		//Property[] properties =  null;//(Property [])getObjects(evsQuery);
	  	List prop = getResults(evsQuery);
	  	Property[] properties = new Property[prop.size()];
	  	for(int i=0; i<prop.size(); i++){
	  		properties[i] = (Property)prop.get(i);
	  		}

		String propName = null;
		String propValue = null;
		HashMap definitionsHash = new HashMap();
		String preferredName = null;
		String hashKey = null;
		String hashValue = null;


		for(int i=0; i<properties.length; i++)
		{
			propName = properties[i].getName();
			propValue = properties[i].getValue();

			//System.out.println("Name: "+propName);
			//System.out.println("Value: "+propValue);
			/***********************************************************************
			 Future enhancement for multiple concept codes
			 Assuming that we get secondary concept codes with some
			 classifications to it.. then add this as a new entry in the list..
			**************************************************************************/

			Vector valueVector = null;
			//Definitions
			if(propName.equalsIgnoreCase("DEFINITION"))
			{
				hashKey = null;
				hashValue = null;
				valueVector = getPropertyElements(propValue);
				for (int j=0;j<valueVector.size();j++)
				{
					String key = (String) valueVector.elementAt(j);
					String value = getPropertyElementValue(key,propValue);
					//System.out.println("\t\tkey: "+key);
					//System.out.println("\t\tvalue: "+value);
					if(key.equalsIgnoreCase("DEF-SOURCE"))
						hashKey = value;
					else if(key.equalsIgnoreCase("DEF-DEFINITION"))
						hashValue = value;
				}
				definitionsHash.put(hashKey, hashValue);
			}

			//Preferred Name
			if(propName.equalsIgnoreCase("PREFERRED_NAME"))
			{
				entitiesMap.put(Configuration.getPreferredNameCol(), propValue);
			}
		}

		//add definition, definitionsource to the map
		String definition = null;
		String definitionSource = null;
		if(definitionsHash != null)
		{
			//If there is a NCI,, add it..
			//else add the next one..(whatever we got)
			if(definitionsHash.containsKey("NCI"))
			{
				definitionSource = "NCI";
				definition = (String)definitionsHash.get("NCI");
			}
			else
			{
				Iterator iter = definitionsHash.keySet().iterator();
				while(iter.hasNext())
				{
					definitionSource = (String)iter.next();
					definition = (String)definitionsHash.get(definitionSource);
					break;
				}
			}
			//add this to map
			entitiesMap.put(Configuration.getDefinitionSourceCol(), definitionSource);
			entitiesMap.put(Configuration.getDefinitionCol(), definition);
		}



		}catch(Exception e)
		{
		    log.error("Exception occured while getting properties: "+e.getMessage());
    		throw new Exception("Exception occured while getting properties: "+e.getMessage());
		}
	}


	/**
	 * Parse the value
	 * @param value
	 * @return
	 */
	private Vector getPropertyElements(String value)
	{
		   Vector vector = new Vector();
		   String s1 = value;
		   for(int i = s1.indexOf("</"); i != -1; i = s1.indexOf("</"))
		   {
			   s1 = s1.substring(i + 1);
			   int j = s1.indexOf(">");
			   String s2 = s1.substring(1, j);
			   vector.add(s2.trim());
		   }

		   return vector;

	}


	/**
	 * Parse the element value
	 * @param s
	 * @param s1
	 * @return
	 */
	private String getPropertyElementValue(String s, String s1)
	{
	    Vector vector;
	    Vector vector1;
	    s = s.trim();
	    vector = getPropertyElements(s1);
	    vector1 = parseXML(s1);
	    if(vector1.size() != vector.size())
	 	   return "";

	    int i;
	    i = -1;
		int j = 0;
		do
		{
		   if(j >= vector.size())
			   break;
		   if(s.equalsIgnoreCase((String)vector.elementAt(j)))
		   {
			   i = j;
			   break;
		   }
		   j++;
	     } while(true);
		 if(i == -1)
		   return "";
       return (String)vector1.elementAt(i);

	}


	/**
	 * Parse the string (string is in xml format)
	 * @param s
	 * @return
	 */
	private Vector parseXML(String s)
	{
	   Vector vector = new Vector();
	   StringTokenizer stringtokenizer = new StringTokenizer(s, "<");
	   boolean flag = true;
	   String s2;
	   String s1 = s2 = "";
	   try
	   {
		   while(stringtokenizer.hasMoreTokens())
		   {
			   String s3 = stringtokenizer.nextToken();
			   if(flag)
			   {
				   int i = s3.indexOf(">");
				   s2 = s3.substring(0, i);
				   s1 = s1 + s3.substring(i + 1);
				   String s4 = stringtokenizer.nextToken();
				   if(s4.startsWith("/"))
				   {
					   String s6 = s3.substring(i + 1);
					   vector.add(s6);
				   } else
				   {
					   s1 = s1 + "<" + s4;
					   flag = false;
				   }
			   } else
			   if(!s3.equalsIgnoreCase("/" + s2 + ">"))
			   {
				   String s5 = stringtokenizer.nextToken();
				   s1 = s1 + "<" + s3;
				   if(!s5.equalsIgnoreCase("/" + s2 + ">"))
				   {
					   s1 = s1 + "<" + s5;
				   } else
				   {
					   vector.add(s1);
					   flag = true;
				   }
			   } else
			   {
				   vector.add(s1);
				   flag = true;
			   }
		   }
	   }
	   catch(Exception exception)
	   {
	       //log.error("Exception: " + exception.getMessage());
		   exception.printStackTrace();
		   return null;
	   }
	   return vector;
	  }


	/**
	 * This method adds underscore in between the words (eg: converts
	 *  GeneHomolog to Gene_Homolog)
	 * @param name
	 *
	 */
	private void evaluateString(String name) throws Exception
	{
		possibleOptions.add(name.trim());

		char firstChar = name.charAt(0);
		firstChar = Character.toUpperCase(firstChar);

		if(name.indexOf("_")>0){
			String temp = Character.toString(firstChar)+ name.substring(1);
			possibleOptions.add(temp.trim());
			}

		possibleOptions.add(firstChar+name.substring(1).toLowerCase());

		String evaluatedString = null;
		StringBuffer wholeWords = new StringBuffer();
		StringBuffer tempSeparateWord = new StringBuffer();
		char[] chars = name.toCharArray();
		StringBuffer sb = new StringBuffer();
		boolean first = true;
		int index = 0;
		for(int i=0; i<chars.length; i++)
		{
			if(Character.isUpperCase(chars[i]))
			{
				if((i > 1)&&((i-index)>1))
				{
				   first = false;
				 	sb.append("_");
				 	sb.append(chars[i]);

				   separateWords.add(tempSeparateWord);
				   tempSeparateWord = null;
				   tempSeparateWord = new StringBuffer();
				   tempSeparateWord.append(chars[i]);
				   wholeWords.append(" ");
				   wholeWords.append(chars[i]);
				}
				else
				{
					wholeWords.append(chars[i]);
					tempSeparateWord.append(chars[i]);
					sb.append(chars[i]);
				}

				index = i;
			}
			else
			{
				if(chars[i]!= '_'){
					sb.append(chars[i]);
					wholeWords.append(chars[i]);
					tempSeparateWord.append(chars[i]);
				}
			}
		}
		//System.out.println("Converted string: "+sb.toString());
		//if the string contains "_", then make the first character uppercase
		if(!first)
		{
			char c = Character.toUpperCase(sb.charAt(0));
			sb.deleteCharAt(0);
			sb.insert(0, c);
			char c1 = Character.toUpperCase(wholeWords.charAt(0));
			wholeWords.deleteCharAt(0);
			wholeWords.insert(0,c1);
		}

		possibleOptions.add(sb.toString().trim());
		if(sb.toString().compareToIgnoreCase(wholeWords.toString()) != 0)
			possibleOptions.add(wholeWords.toString().trim());

		if(separateWords.size() > 0)
		{
			if( tempSeparateWord != null)
			{
				String temp = tempSeparateWord.toString();
				separateWords.add(temp.trim());
			}
		}

	}


/////////////////testing////////////////////
  private void testOutput(ArrayList attList)
  {
		  for(int i=0; i<attList.size(); i++)
		  {
			  HashMap map = (HashMap)attList.get(i);
			  System.out.println("******** List of values***********");
			  for(Iterator iter=map.keySet().iterator(); iter.hasNext();)
			  {
				  String key = (String)iter.next();
				  String value = (String)map.get(key);
				  System.out.println(key+" = "+value);
			  }
		  }
  }

  private Object getObject(EVSQuery evsQuery) throws Exception{
  	//System.out.println("EVSImpl - getObject");
  	Object object = null;
  	try{
  	    List resultList = getResults(evsQuery);
  		if(resultList.size()>0){
  			object = resultList.get(0);
  			//System.out.println("Object - "+ object);
  			}

  	}catch(Exception ex){
  	    log.error("Exception: " + ex.getMessage());
  		throw new Exception(ex.getMessage());
  			}

  	return object;
  	}

private Object[] getObjects(EVSQuery evsQuery) throws Exception{
	//System.out.println("EVSImpl - getObjects");
  	Object[] objectArray = null;
  	try{
  	    List resultList = getResults(evsQuery);
  		objectArray = new Object[resultList.size()];

  		if(resultList.size()==1){
  			objectArray[0]=resultList.get(0);
  			}
  		else{
  			for(int i=0; i< resultList.size(); i++){
  	  		    if(resultList.get(i)!=null){
  	  		      objectArray[i] = resultList.get(i);
  	  		    }
  	  		}

  			}

  	}catch(Exception ex){
  	    log.error("Exception: " + ex.getMessage());
  		throw new Exception(ex.getMessage());
  			}
  	//System.out.println("Object array size = "+ objectArray.length);
  	return objectArray;
  	}

 public List getResults(EVSQuery evsQuery) throws Exception{
     List results = new ArrayList();
     try{


     	 if(serverURL == null){
     	     if(Configuration.getServerURL()==null){
     	         //System.out.println("Cannot locate serverURL in semantic properties");
     	         throw new Exception("Cannot locate serverURL in semantic properties");
     	         }
     	     serverURL = Configuration.getServerURL();
     	     }

     	 if(appService == null){

			 appService = ApplicationServiceProvider.getApplicationService();
		 }

		//System.out.println("calling appService.eveSearch()");
        results = appService.evsSearch(evsQuery);
         //System.out.println("Application service returned "+ results.size());

     }catch(Exception ex){
         log.error("Exception: " + ex.getMessage());
		 ex.printStackTrace();
         throw new Exception(ex.getMessage());
         }
     return results;

     }

}
