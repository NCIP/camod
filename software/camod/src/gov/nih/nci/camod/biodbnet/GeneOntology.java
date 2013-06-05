package gov.nih.nci.camod.biodbnet;

import java.io.Serializable;
import java.util.Collection;

public class GeneOntology  implements Serializable
{
    /**
    * An attribute to allow serialization of the domain objects
    */
    private static final long serialVersionUID = 1234567890L;

    /**
	* caBIG Grid Identifier
	**/
    
    public String bigid;
    /**
	* Retrieves the value of the bigid attribute
	* @return bigid
	**/

    public String getBigid(){
        return bigid;
    }

    /**
	* Sets the value of bigid attribute
	**/

    public void setBigid(String bigid){
        this.bigid = bigid;
    }
    /**
	* The identifier attached to this record in the caBIO database
	**/
    

    public Long id;
    /**
	* Retrieves the value of the id attribute
	* @return id
	**/

    public Long getId(){
        return id;
    }

    /**
	* Sets the value of id attribute
	**/

    public void setId(Long id){
        this.id = id;
    }
    /**
	* The name for the Gene Ontology object.
	**/
    
    public String name;
    /**
	* Retrieves the value of the name attribute
	* @return name
	**/

    public String getName(){
        return name;
    }

    /**
	* Sets the value of name attribute
	**/

    public void setName(String name){
        this.name = name;
    }


       
    private Collection<Gene> geneCollection;
    /**
	* Retrieves the value of the geneCollection attribute
	* @return geneCollection
	**/

    public Collection<Gene> getGeneCollection(){
        return geneCollection;
    }

    /**
	* Sets the value of geneCollection attribute
	**/

    public void setGeneCollection(Collection<Gene> geneCollection){
        this.geneCollection = geneCollection;
    }


    /**
    * Compares <code>obj</code> to it self and returns true if they both are same
    *
    * @param obj
    **/
    public boolean equals(Object obj)
    {
        if(obj instanceof GeneOntology) 
        {
            GeneOntology c =(GeneOntology)obj;              
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