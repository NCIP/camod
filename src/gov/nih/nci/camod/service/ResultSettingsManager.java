package gov.nih.nci.camod.service;

import gov.nih.nci.camod.domain.ResultSettings;

public interface ResultSettingsManager
{
    public void save(ResultSettings rs) throws Exception;
    
    public void remove(String id) throws Exception;
    
    public ResultSettings get(String id) throws Exception;
    
    public ResultSettings getByUsername(String username) throws Exception;
    
    public void update(ResultSettings rs) throws Exception;
    
}
