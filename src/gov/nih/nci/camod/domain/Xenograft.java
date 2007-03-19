/**
 * 
 * $Id: Xenograft.java,v 1.13 2007-03-19 18:56:11 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.12  2006/04/19 17:37:37  pandyas
 * Removed TODO text
 *
 * Revision 1.11  2006/04/17 19:13:46  pandyas
 * caMod 2.1 OM changes and added log/id header
 *
 * Revision 1.10  2006/01/18 14:23:31  georgeda
 * TT# 376 - Updated to use new Java 1.5 features
 *
 * Revision 1.9  2005/11/11 16:27:26  pandyas
 * added javadocs
 *
 * 
 */
package gov.nih.nci.camod.domain;

import gov.nih.nci.camod.service.impl.QueryManagerSingleton;
import gov.nih.nci.camod.util.HashCodeUtil;
import java.util.*;

/**
 * @author rajputs
 */
public class Xenograft extends AbstractCancerModel implements Comparable
{
    private static final long serialVersionUID = 3257445453799404851L;

    private String xenograftName;    
    private String geneticManipulation;
    private String modificationDescription;
    private String parentalCellLineName;
    private String atccNumber;
    private String cellAmount;
    private String growthPeriod;
    private String graftType;
    private String graftTypeUnctrlVocab;
    private String administrativeSite;
    private String adminSiteUnctrlVocab;  
    private String conditioningRegime;
    private String condRegimeUnctrlVocab;      
    private TumorCode tumorCode;
    private Set<InvivoResult> invivoResultCollection = new TreeSet<InvivoResult>();
    private Species donorSpecies;
    private Organ organ;

    
    /**
     * @return Returns the administrativeSite.
     */
    public String getAdministrativeSite()
    {
        return administrativeSite;
    }

    /**
     * @param administrativeSite The administrativeSite to set.
     */
    public void setAdministrativeSite(String administrativeSite)
    {
        this.administrativeSite = administrativeSite;
    }
    /**
     * @return Returns the adminSiteUnctrlVocab.
     */
    public String getAdminSiteUnctrlVocab()
    {
        return adminSiteUnctrlVocab;
    }

    /**
     * @param adminSiteUnctrlVocab The adminSiteUnctrlVocab to set.
     */
    public void setAdminSiteUnctrlVocab(String adminSiteUnctrlVocab)
    {
        this.adminSiteUnctrlVocab = adminSiteUnctrlVocab;
    }  
    
    
    /**
     * @return Returns the invivoResultCollection.
     * @throws Exception 
     */
    public List getInvivoResultCollectionByNSC(String inNSCNumber) throws Exception
    {
        return QueryManagerSingleton.instance().getInvivoResultCollectionByNSC(inNSCNumber, getId().toString());
    }

    /**
     * @return Returns the organ.
     */
    public Organ getOrgan()
    {
        return organ;
    }

    /**
     * @param organ The organ to set.
     */
    public void setOrgan(Organ organ)
    {
        this.organ = organ;
    }

    /**
     * @return Returns the donorSpecies.
     */
    public Species getDonorSpecies()
    {
        return donorSpecies;
    }

    /**
     * @param donorSpecies The donorSpecies to set.
     */
    public void setDonorSpecies(Species donorSpecies)
    {
        this.donorSpecies = donorSpecies;
    }

    /**
     * @return Returns the atccNumber.
     */
    public String getAtccNumber()
    {
        return atccNumber;
    }

    /**
     * @param atccNumber The atccNumber to set.
     */
    public void setAtccNumber(String atccNumber)
    {
        this.atccNumber = atccNumber;
    }

    /**
     * @return Returns the cellAmount.
     */
    public String getCellAmount()
    {
        return cellAmount;
    }

    /**
     * @param cellAmount The cellAmount to set.
     */
    public void setCellAmount(String cellAmount)
    {
        this.cellAmount = cellAmount;
    }

    /**
     * @return Returns the graftType.
     */
    public String getGraftType()
    {
        return graftType;
    }

    /**
     * @param graftType The graftType to set.
     */
    public void setGraftType(String graftType)
    {
        this.graftType = graftType;
    }

    /**
     * @return Returns the graftTypeUnctrlVocab.
     */
    public String getGraftTypeUnctrlVocab()
    {
        return graftTypeUnctrlVocab;
    }

    /**
     * @param graftTypeUnctrlVocab The graftTypeUnctrlVocab to set.
     */
    public void setGraftTypeUnctrlVocab(String graftTypeUnctrlVocab)
    {
        this.graftTypeUnctrlVocab = graftTypeUnctrlVocab;
    }

    /**
     * @return Returns the growthPeriod.
     */
    public String getGrowthPeriod()
    {
        return growthPeriod;
    }

    /**
     * @param growthPeriod The growthPeriod to set.
     */
    public void setGrowthPeriod(String growthPeriod)
    {
        this.growthPeriod = growthPeriod;
    }

    /**
     * @return Returns the invivoResultCollection.
     */
    public Set getInvivoResultCollection()
    {
        return invivoResultCollection;
    }

    /**
     * @param invivoResultCollection The invivoResultCollection to set.
     */
    public void setInvivoResultCollection(Set<InvivoResult> invivoResultCollection)
    {
        this.invivoResultCollection = invivoResultCollection;
    }

    public void addInvivoResult(InvivoResult invivoResult)
    {
        invivoResultCollection.add(invivoResult);
    }

    /**
     * @return Returns the geneticManipulation.
     */
    public String getGeneticManipulation()
    {
        return geneticManipulation;
    }

    /**
     * @param geneticManipulation The geneticManipulation to set.
     */
    public void setGeneticManipulation(String geneticManipulation)
    {
        this.geneticManipulation = geneticManipulation;
    }

    /**
     * @return Returns the modificationDescription.
     */
    public String getModificationDescription()
    {
        return modificationDescription;
    }

    /**
     * @param modificationDescription The modificationDescription to set.
     */
    public void setModificationDescription(String modificationDescription)
    {
        this.modificationDescription = modificationDescription;
    }

    /**
     * @return Returns the xenograftName.
     */
    public String getXenograftName()
    {
        return xenograftName;
    }

    /**
     * @param xenograftName The xenograftName to set.
     */
    public void setXenograftName(String xenograftName)
    {
        this.xenograftName = xenograftName;
    }

    /**
     * @return Returns the parentalCellLineName.
     */
    public String getParentalCellLineName()
    {
        return parentalCellLineName;
    }

    /**
     * @param parentalCellLineName The parentalCellLineName to set.
     */
    public void setParentalCellLineName(String parentalCellLineName)
    {
        this.parentalCellLineName = parentalCellLineName;
    }

    /**
     * @return Returns the tumorCode.
     */
    public TumorCode getTumorCode()
    {
        return tumorCode;
    }

    /**
     * @param tumorCode The tumorCode to set.
     */
    public void setTumorCode(TumorCode tumorCode)
    {
        this.tumorCode = tumorCode;
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString()
    {
        String result = super.toString() + " - ";
        result += this.getXenograftName();
        return result;
    }

    public boolean equals(Object o)
    {
        if (!super.equals(o))
            return false;
        if (!(this.getClass().isInstance(o)))
            return false;
        final Xenograft obj = (Xenograft) o;
        if (HashCodeUtil.notEqual(this.getXenograftName(), obj.getXenograftName()))
            return false;
        return true;
    }

    public int hashCode()
    {
        int result = HashCodeUtil.SEED;
        result = HashCodeUtil.hash(result, this.getXenograftName());
        return result + super.hashCode();
    }

    public int compareTo(Object o)
    {
        if ((o instanceof Xenograft) && (this.getXenograftName() != null) && (((Xenograft) o).getXenograftName() != null))
        {
            int result = this.getXenograftName().compareTo(((Xenograft) o).getXenograftName());
            if (result != 0)
            {
                return result;
            }
        }

        return super.compareTo(o);
    }

    /**
     * @return Returns the conditioningRegime.
     */
    public String getConditioningRegime()
    {
        return conditioningRegime;
    }

    /**
     * @param conditioningRegime The conditioningRegime to set.
     */
    public void setConditioningRegime(String conditioningRegime)
    {
        this.conditioningRegime = conditioningRegime;
    }

    /**
     * @return Returns the condRegimeUnctrlVocab.
     */
    public String getCondRegimeUnctrlVocab()
    {
        return condRegimeUnctrlVocab;
    }

    /**
     * @param condRegimeUnctrlVocab The condRegimeUnctrlVocab to set.
     */
    public void setCondRegimeUnctrlVocab(String condRegimeUnctrlVocab)
    {
        this.condRegimeUnctrlVocab = condRegimeUnctrlVocab;
    }
}
