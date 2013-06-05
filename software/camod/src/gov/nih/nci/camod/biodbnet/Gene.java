package gov.nih.nci.camod.biodbnet;

import java.io.Serializable;
import java.util.Collection;

public class Gene  implements Serializable
{
    /**
    * An attribute to allow serialization of the domain objects
    */
    private static final long serialVersionUID = 1234567890L;

    /**
	* caBIG Grid Identifier
	**/
    
    private String bigid;
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
	* The cluster identification for the gene.
	**/
    
    private Long clusterId;
    /**
	* Retrieves the value of the clusterId attribute
	* @return clusterId
	**/

    public Long getClusterId(){
        return clusterId;
    }

    /**
	* Sets the value of clusterId attribute
	**/

    public void setClusterId(Long clusterId){
        this.clusterId = clusterId;
    }
    /**
	* The title of the gene.
	**/
    
    private String fullName;
    /**
	* Retrieves the value of the fullName attribute
	* @return fullName
	**/

    public String getFullName(){
        return fullName;
    }

    /**
	* Sets the value of fullName attribute
	**/

    public void setFullName(String fullName){
        this.fullName = fullName;
    }
    /**
	* A unique symbol (or abbreviated name) for a known human gene approved by the HUGO Gene Nomenclature Committee (http://www.genenames.org/aboutHGNC.html).
	**/
    
    private String hugoSymbol;
    /**
	* Retrieves the value of the hugoSymbol attribute
	* @return hugoSymbol
	**/

    public String getHugoSymbol(){
        return hugoSymbol;
    }

    /**
	* Sets the value of hugoSymbol attribute
	**/

    public void setHugoSymbol(String hugoSymbol){
        this.hugoSymbol = hugoSymbol;
    }
    /**
	* The identifier attached to this record in the caBIO database
	**/
    

    private Long id;
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
	* A short name that is used to identify a particular gene.
	**/
    
    private String symbol;
    /**
	* Retrieves the value of the symbol attribute
	* @return symbol
	**/

    public String getSymbol(){
        return symbol;
    }

    /**
	* Sets the value of symbol attribute
	**/

    public void setSymbol(String symbol){
        this.symbol = symbol;
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
	* An associated GeneOntology object's collection 
	**/
         
    private Collection<GeneOntology> geneOntologyCollection;
    /**
	* Retrieves the value of the geneOntologyCollection attribute
	* @return geneOntologyCollection
	**/

    public Collection<GeneOntology> getGeneOntologyCollection(){
        return geneOntologyCollection;
    }

    /**
	* Sets the value of geneOntologyCollection attribute
	**/

    public void setGeneOntologyCollection(Collection<GeneOntology> geneOntologyCollection){
        this.geneOntologyCollection = geneOntologyCollection;
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
    * Compares <code>obj</code> to it self and returns true if they both are same
    *
    * @param obj
    **/
    public boolean equals(Object obj)
    {
        if(obj instanceof Gene) 
        {
            Gene c =(Gene)obj;              
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
