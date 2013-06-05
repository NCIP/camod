package gov.nih.nci.camod.biodbnet;

import java.io.Serializable;
import java.util.Collection;

public class Pathway  implements Serializable
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
	* An individual or group of individuals that researches, evaluates and organizes new pathways 
	**/
    
    private String curator;
    /**
	* Retrieves the value of the curator attribute
	* @return curator
	**/

    public String getCurator(){
        return curator;
    }

    /**
	* Sets the value of curator attribute
	**/

    public void setCurator(String curator){
        this.curator = curator;
    }
    /**
	* The description of this pathway.
	**/
    
    public String description;
    /**
	* Retrieves the value of the description attribute
	* @return description
	**/

    public String getDescription(){
        return description;
    }

    /**
	* Sets the value of description attribute
	**/

    public void setDescription(String description){
        this.description = description;
    }
    /**
	* The pathway diagram for the pathway object.
	**/
    
    public String diagram;
    /**
	* Retrieves the value of the diagram attribute
	* @return diagram
	**/

    public String getDiagram(){
        return diagram;
    }

    /**
	* Sets the value of diagram attribute
	**/

    public void setDiagram(String diagram){
        this.diagram = diagram;
    }
    /**
	* The display value for the pathway object.
	**/
    
    public String displayValue;
    /**
	* Retrieves the value of the displayValue attribute
	* @return displayValue
	**/

    public String getDisplayValue(){
        return displayValue;
    }

    /**
	* Sets the value of displayValue attribute
	**/

    public void setDisplayValue(String displayValue){
        this.displayValue = displayValue;
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
	* The pathway's name for the pathway object.
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
    /**
	* An individual or group of individuals that provides feedback on and reviews a curated pathway 
	**/
    
    private String reviewer;
    /**
	* Retrieves the value of the reviewer attribute
	* @return reviewer
	**/

    public String getReviewer(){
        return reviewer;
    }

    /**
	* Sets the value of reviewer attribute
	**/

    public void setReviewer(String reviewer){
        this.reviewer = reviewer;
    }
    /**
	* Name of the source (project or information collection) that supplied the pathway data.
	**/
    
    private String source;
    /**
	* Retrieves the value of the source attribute
	* @return source
	**/

    public String getSource(){
        return source;
    }

    /**
	* Sets the value of source attribute
	**/

    public void setSource(String source){
        this.source = source;
    }
    /**
	* An associated Taxon object
	**/
         
    private Taxon taxon;
    /**
	* Retrieves the value of the taxon attribute
	* @return taxon
	**/
 
    public Taxon getTaxon(){
        return taxon;
    }
    /**
	* Sets the value of taxon attribute
	**/

    public void setTaxon(Taxon taxon){
        this.taxon = taxon;
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
        if(obj instanceof Pathway) 
        {
            Pathway c =(Pathway)obj;              
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
