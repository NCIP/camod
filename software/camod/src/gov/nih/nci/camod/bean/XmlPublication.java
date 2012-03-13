package gov.nih.nci.camod.bean;

import gov.nih.nci.camod.domain.Publication;

import java.io.Serializable;
import java.util.Collection;
/**
* 	**/
public class XmlPublication extends File implements Serializable
{
/**
* An attribute to allow serialization of the domain objects
*/
private static final long serialVersionUID = 1234567890L;

/**
* 	**/
private String digitalObjectId;
/**
* Retreives the value of digitalObjectId attribute
* @return digitalObjectId
**/

public String getDigitalObjectId(){
	return digitalObjectId;
}

/**
* Sets the value of digitalObjectId attribue
**/

public void setDigitalObjectId(String digitalObjectId){
	this.digitalObjectId = digitalObjectId;
}
	/**
* 	**/
private String endPage;
/**
* Retreives the value of endPage attribute
* @return endPage
**/

public String getEndPage(){
	return endPage;
}

/**
* Sets the value of endPage attribue
**/

public void setEndPage(String endPage){
	this.endPage = endPage;
}

	/**
* 	**/
private String journalName;
/**
* Retreives the value of journalName attribute
* @return journalName
**/

public String getJournalName(){
	return journalName;
}

/**
* Sets the value of journalName attribue
**/

public void setJournalName(String journalName){
	this.journalName = journalName;
}

	/**
* 	**/
private Long pubMedId;
/**
* Retreives the value of pubMedId attribute
* @return pubMedId
**/

public Long getPubMedId(){
	return pubMedId;
}

/**
* Sets the value of pubMedId attribue
**/

public void setPubMedId(Long pubMedId){
	this.pubMedId = pubMedId;
}

	

	/**
* 	**/
private String startPage;
/**
* Retreives the value of startPage attribute
* @return startPage
**/

public String getStartPage(){
	return startPage;
}

/**
* Sets the value of startPage attribue
**/

public void setStartPage(String startPage){
	this.startPage = startPage;
}

	/**
* 	**/
private String status;
/**
* Retreives the value of status attribute
* @return status
**/

public String getStatus(){
	return status;
}

/**
* Sets the value of status attribue
**/

public void setStatus(String status){
	this.status = status;
}

	/**
* 	**/
private String volume;
/**
* Retreives the value of volume attribute
* @return volume
**/

public String getVolume(){
	return volume;
}

/**
* Sets the value of volume attribue
**/

public void setVolume(String volume){
	this.volume = volume;
}

	/**
* 	**/
private Integer year;
/**
* Retreives the value of year attribute
* @return year
**/

public Integer getYear(){
	return year;
}

/**
* Sets the value of year attribue
**/

public void setYear(Integer year){
	this.year = year;
}

/**
* An associated gov.nih.nci.cananolab.domain.common.Author object's collection 
**/
		
private Collection<Author> authorCollection;
/**
* Retreives the value of authorCollection attribue
* @return authorCollection
**/

public Collection<Author> getAuthorCollection(){
	return authorCollection;
}

/**
* Sets the value of authorCollection attribue
**/

public void setAuthorCollection(Collection<Author> authorCollection){
	this.authorCollection = authorCollection;
}
	
/**
* Compares <code>obj</code> to it self and returns true if they both are same
*
* @param obj
**/
public boolean equals(Object obj)
{
	if(obj instanceof Publication) 
	{
		Publication c =(Publication)obj; 			 
		if(getId() != null && getId().equals(c.getId()))
			return true;
	}
	return false;
}
	
/**
* Returns hash code for the primary key of the object
**/
public int hashCode()
{
	if(getId() != null)
		return getId().hashCode();
	return 0;
}

}
