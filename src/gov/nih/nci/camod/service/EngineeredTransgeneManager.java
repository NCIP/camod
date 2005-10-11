package gov.nih.nci.camod.service;

import gov.nih.nci.camod.domain.Transgene;
import gov.nih.nci.camod.webapp.form.EngineeredTransgeneData;

import java.util.List;

public interface EngineeredTransgeneManager {
	
	public List getAll() throws Exception;

    public Transgene get(String id) throws Exception;

    public void save(Transgene engineeredGene) throws Exception;

    public void remove(String id) throws Exception;

    public Transgene create(EngineeredTransgeneData inEngineeredTransgeneData) throws Exception;

    public void update(EngineeredTransgeneData inEngineeredTransgeneData, Transgene inEngineeredTransgene) throws Exception;
}
