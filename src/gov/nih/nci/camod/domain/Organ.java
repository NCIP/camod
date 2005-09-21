/*
 * Created on May 5, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.domain;

import gov.nih.nci.evs.domain.Property;
import gov.nih.nci.evs.query.EVSQuery;
import gov.nih.nci.evs.query.EVSQueryImpl;
import gov.nih.nci.system.applicationservice.ApplicationService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.*;

/**
 * @author rajputs
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class Organ extends BaseObject implements Serializable {

    private static final long serialVersionUID = 3259095453799404851L;

    private Long id;
    private String name;
    private String conceptCode;
    private List histopathologyCollection = new ArrayList();

    /**
     * @return Returns the histopathologyCollection.
     */
    public List getHistopathologyCollection() {
        return histopathologyCollection;
    }

    /**
     * @param histopathologyCollection
     *            The histopathologyCollection to set.
     */
    public void setHistopathologyCollection(List histopathologyCollection) {
        this.histopathologyCollection = histopathologyCollection;
    }

    /**
     * @param histopatholgy
     *            The histopathology to add.
     */
    public void addHistopathology(Histopathology histopathology) {
        histopathology.setOrgan(this);
        histopathologyCollection.add(histopathology);
    }

    /**
     * @return Returns the conceptCode.
     */
    public String getConceptCode() {
        return conceptCode;
    }

    /**
     * @return Returns the conceptCode.
     */
    public String getEVSPreferredDescription() {

        String thePreferedDesc = "";
        
        ApplicationService appService = ApplicationService
                .getRemoteInstance("http://cabio.nci.nih.gov/cacore30/server/HTTPServer");

        EVSQuery theQuery = new EVSQueryImpl();
        theQuery.getConceptNameByCode("NCI_Thesaurus", conceptCode);
        try {
            List evsResults = (List) appService.evsSearch(theQuery);

            if (evsResults.size() > 0) {
                System.out.println("List: " + evsResults);
                
                String theDisplayName = (String) evsResults.get(0);
                
                EVSQuery theQuery2 = new EVSQueryImpl();
                theQuery2.getPropertyValues("NCI_Thesaurus", theDisplayName, "Display_Name");
                
                List theSecondList = (List) appService.evsSearch(theQuery2);
                
                if (theSecondList.size() > 0)
                {
                    thePreferedDesc = (String) theSecondList.get(0);
                }
            }

        } catch (Exception e) {
            System.out.println("E: " + e);
        }
        return thePreferedDesc;
    }

    /**
     * @param conceptCode
     *            The conceptCode to set.
     */
    public void setConceptCode(String conceptCode) {
        this.conceptCode = conceptCode;
    }

    /**
     * @return Returns the id.
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     *            The id to set.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return Returns the name.
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof Organ)) {
            return false;
        }
        Organ rhs = (Organ) object;
        return new EqualsBuilder().append(this.histopathologyCollection, rhs.histopathologyCollection).append(
                this.name, rhs.name).append(this.conceptCode, rhs.conceptCode).append(this.id, rhs.id).isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-5760707, 335803633).append(this.histopathologyCollection).append(this.name).append(
                this.conceptCode).append(this.id).toHashCode();
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("name", this.name).append("conceptCode", this.conceptCode).append("id",
                this.id).append("histopathologyCollection", this.histopathologyCollection).toString();
    }
}
