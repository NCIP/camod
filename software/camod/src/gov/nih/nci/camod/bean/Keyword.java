package gov.nih.nci.camod.bean;


import java.io.Serializable;
import java.util.Collection;
/**
* A small set of words designed to convey the subject of a technical article.	**/
public class Keyword  implements Serializable
{
/**
* An attribute to allow serialization of the domain objects
*/
private static final long serialVersionUID = 1234567890L;


	/**
* One or more characters used to identify, name, or characterize the nature, properties, or contents of a thing.	**/
private Long id;
/**
* Retreives the value of id attribute
* @return id
**/

public Long getId(){
	return id;
}

/**
* Sets the value of id attribue
**/

public void setId(Long id){
	this.id = id;
}

	/**
* The words or language units by which a thing is known.	**/
private String name;
/**
* Retreives the value of name attribute
* @return name
**/

public String getName(){
	return name;
}

/**
* Sets the value of name attribue
**/

public void setName(String name){
	this.name = name;
}

/**
* An associated gov.nih.nci.cananolab.domain.common.File object's collection 
**/
		
private Collection<File> fileCollection;
/**
* Retreives the value of fileCollection attribue
* @return fileCollection
**/

public Collection<File> getFileCollection(){
	return fileCollection;
}

/**
* Sets the value of fileCollection attribue
**/

public void setFileCollection(Collection<File> fileCollection){
	this.fileCollection = fileCollection;
}
	
/**
* Compares <code>obj</code> to it self and returns true if they both are same
*
* @param obj
**/
public boolean equals(Object obj)
{
	if(obj instanceof Keyword) 
	{
		Keyword c =(Keyword)obj; 			 
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
