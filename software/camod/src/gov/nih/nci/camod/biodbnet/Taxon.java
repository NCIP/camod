package gov.nih.nci.camod.biodbnet;

import java.io.Serializable;
import java.util.Collection;

public class Taxon  implements Serializable
{
    /**
    * An attribute to allow serialization of the domain objects
    */
    private static final long serialVersionUID = 1234567890L;

    /**
	* The abbreviation of the taxon.
	**/
    
    public String abbreviation;
    /**
	* Retrieves the value of the abbreviation attribute
	* @return abbreviation
	**/

    public String getAbbreviation(){
        return abbreviation;
    }

    /**
	* Sets the value of abbreviation attribute
	**/

    public void setAbbreviation(String abbreviation){
        this.abbreviation = abbreviation;
    }
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
	* The common name of the taxon.
	**/
    
    public String commonName;
    /**
	* Retrieves the value of the commonName attribute
	* @return commonName
	**/

    public String getCommonName(){
        return commonName;
    }

    /**
	* Sets the value of commonName attribute
	**/

    public void setCommonName(String commonName){
        this.commonName = commonName;
    }
    /**
	* The ethinicity strain of the taxon.
	**/
    
    public String ethnicityStrain;
    /**
	* Retrieves the value of the ethnicityStrain attribute
	* @return ethnicityStrain
	**/

    public String getEthnicityStrain(){
        return ethnicityStrain;
    }

    /**
	* Sets the value of ethnicityStrain attribute
	**/

    public void setEthnicityStrain(String ethnicityStrain){
        this.ethnicityStrain = ethnicityStrain;
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
	* The scientific name of the taxon.
	**/
    
    public String scientificName;
    /**
	* Retrieves the value of the scientificName attribute
	* @return scientificName
	**/

    public String getScientificName(){
        return scientificName;
    }

    /**
	* Sets the value of scientificName attribute
	**/

    public void setScientificName(String scientificName){
        this.scientificName = scientificName;
    }
    /**
	* An associated Pathway object's collection 
	**/
         
    private Collection<Pathway> pathwayCollection;
    /**
	* Retrieves the value of the pathwayCollection attribute
	* @return pathwayCollection
	**/

    public Collection<Pathway> getPathwayCollection(){
        return pathwayCollection;
    }

    /**
	* Sets the value of pathwayCollection attribute
	**/

    public void setPathwayCollection(Collection<Pathway> pathwayCollection){
        this.pathwayCollection = pathwayCollection;
    }



    /**
	* An associated Gene object's collection 
	**/
         
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
        if(obj instanceof Taxon) 
        {
            Taxon c =(Taxon)obj;              
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
