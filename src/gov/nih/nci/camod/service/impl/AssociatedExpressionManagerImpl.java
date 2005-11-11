package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.domain.*;
import gov.nih.nci.camod.service.AssociatedExpressionManager;
import gov.nih.nci.camod.util.EvsTreeUtil;
import gov.nih.nci.camod.webapp.form.AssociatedExpressionData;

import java.util.List;

public class AssociatedExpressionManagerImpl extends BaseManager implements AssociatedExpressionManager{

	public List getAll() throws Exception {

		log.trace("In AssociatedExpressionManagerImpl.getAll");

		return super.getAll(ExpressionFeature.class);
	}

	public ExpressionFeature get(String id) throws Exception {
		log.trace("In AssociatedExpressionManagerImpl.get");
		return (ExpressionFeature) super.get(id, ExpressionFeature.class);
	}

	public void save(ExpressionFeature expressionFeature) throws Exception {
		log.trace("In AssociatedExpressionManagerImpl.save");
		super.save(expressionFeature);
	}

	public void remove(String id, EngineeredGene inEngineeredGene) throws Exception {
		log.trace("In AssociatedExpressionManagerImpl.remove");
        inEngineeredGene.getExpressionFeatureCollection().remove(get(id));
		super.save(inEngineeredGene);
	}
	
	public ExpressionFeature create( AssociatedExpressionData inAssociatedExpressionData ) 
		throws Exception
	{
		ExpressionFeature inExpressionFeature = new ExpressionFeature();
		populate(inAssociatedExpressionData, inExpressionFeature);
		return inExpressionFeature;
	}
	
	public void update( AssociatedExpressionData inAssociatedExpressionData, ExpressionFeature inExpressionFeature )
		throws Exception
	{	
		populate(inAssociatedExpressionData, inExpressionFeature);
		save( inExpressionFeature );
	}
	
	private void populate(AssociatedExpressionData inAssociatedExpressionData, ExpressionFeature inExpressionFeature) 
		throws Exception 
	{
		String preferedOrganName = EvsTreeUtil.getEVSPreferedDescription(inAssociatedExpressionData.getOrganTissueCode());
		inExpressionFeature.setOrgan( new Organ() );
		inExpressionFeature.getOrgan().setName(preferedOrganName);
		inExpressionFeature.getOrgan().setConceptCode( inAssociatedExpressionData.getOrganTissueCode());
		
		ExpressionLevelDesc expLevelDesc = ExpressionLevelDescManagerSingleton.instance().getByName( inAssociatedExpressionData.getExpressionLevel() );		
		inExpressionFeature.setExpressionLevelDesc( expLevelDesc );		
	}
}
