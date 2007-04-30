/**
 * 
 * $Id: AssociatedExpressionManagerImpl.java,v 1.6 2007-04-30 20:09:43 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.5  2006/04/17 19:11:05  pandyas
 * caMod 2.1 OM changes
 *
 * 
 */

package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.EngineeredGene;
import gov.nih.nci.camod.domain.ExpressionFeature;
import gov.nih.nci.camod.domain.ExpressionLevelDesc;
import gov.nih.nci.camod.domain.Organ;
import gov.nih.nci.camod.service.AssociatedExpressionManager;
import gov.nih.nci.camod.util.EvsTreeUtil;
import gov.nih.nci.camod.webapp.form.AssociatedExpressionData;

import java.util.List;

public class AssociatedExpressionManagerImpl extends BaseManager implements AssociatedExpressionManager
{
    public List getAll() throws Exception
    {
        log.trace("In AssociatedExpressionManagerImpl.getAll");
        return super.getAll(ExpressionFeature.class);
    }

    public ExpressionFeature get(String id) throws Exception
    {
        log.trace("In AssociatedExpressionManagerImpl.get");
        return (ExpressionFeature) super.get(id, ExpressionFeature.class);
    }

    public void save(ExpressionFeature expressionFeature) throws Exception
    {
        log.trace("In AssociatedExpressionManagerImpl.save");
        super.save(expressionFeature);
    }

    public void remove(String id,
                       EngineeredGene inEngineeredGene) throws Exception
    {
        log.trace("In AssociatedExpressionManagerImpl.remove");
        inEngineeredGene.getExpressionFeatureCollection().remove(get(id));
        super.save(inEngineeredGene);
    }

    public ExpressionFeature create(AssociatedExpressionData inAssociatedExpressionData) throws Exception
    {
        ExpressionFeature inExpressionFeature = new ExpressionFeature();
        populate(inAssociatedExpressionData, inExpressionFeature);
        return inExpressionFeature;
    }

    public void update(AssociatedExpressionData inAssociatedExpressionData,
                       ExpressionFeature inExpressionFeature) throws Exception
    {
        populate(inAssociatedExpressionData, inExpressionFeature);
        save(inExpressionFeature);
    }

    private void populate(AssociatedExpressionData inAssociatedExpressionData,
                          ExpressionFeature inExpressionFeature) throws Exception
    {
		// every submission - lookup organ or create one new
		if (inAssociatedExpressionData.getOrganTissueCode().equals(
				Constants.Dropdowns.CONCEPTCODEZEROS)) {
			log.info("inAssociatedExpressionData.getOrganTissueCode(): "
					+ inAssociatedExpressionData.getOrganTissueCode());
			// Create new organ with conceptCode = 000000, use name field
			inExpressionFeature.setOrgan(new Organ());
			inExpressionFeature.getOrgan().setConceptCode(
					Constants.Dropdowns.CONCEPTCODEZEROS);
			inExpressionFeature.getOrgan()
					.setName(inAssociatedExpressionData.getOrgan());
		} else if (inAssociatedExpressionData.getOrganTissueCode() != null){
			log.info("getOrCreate method used");
			Organ theNewOrgan = OrganManagerSingleton.instance().getOrCreate(
					inAssociatedExpressionData.getOrganTissueCode(),
					inAssociatedExpressionData.getOrganTissueName());
			inExpressionFeature.setOrgan(theNewOrgan);
		}

        ExpressionLevelDesc expLevelDesc = ExpressionLevelDescManagerSingleton.instance().getByName(
                                                                                                    inAssociatedExpressionData.getExpressionLevel());
        inExpressionFeature.setExpressionLevelDesc(expLevelDesc);
    }
}
