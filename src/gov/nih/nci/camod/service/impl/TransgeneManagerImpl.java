/*
 * Created on Jun 17, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.domain.Transgene;
import gov.nih.nci.camod.service.TransgeneManager;
import java.util.List;

/**
 * @author rajputs
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TransgeneManagerImpl extends BaseManager implements TransgeneManager {
	
	public List getTransgenes() {		
		List transgenes = null;		
		return transgenes;
	}
	
	public Transgene getTransgene(String id) {
		Transgene transgene = null;
		return transgene;
    }

    public void saveTransgene(Transgene transgene) {    	
    }

    public void removeTransgene(String id) {    	
    }
}
