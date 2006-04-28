package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.domain.ResultSettings;
import gov.nih.nci.camod.service.ResultSettingsManager;

import java.util.List;

public class ResultSettingsManagerImpl  extends BaseManager implements ResultSettingsManager
{
    public void save(ResultSettings rs) throws Exception
    {
        log.trace( "Entering ResultSettingsManagerImpl.save" );
        
        // Save to db
        super.save(rs);
    }
    
    public void remove(String id) throws Exception
    {
        log.trace( "Entering ResultSettingsManagerImpl.remove" );
        
        //delete from db
        super.remove( id, ResultSettings.class );      
    }
    
    public ResultSettings get(String id) throws Exception
    {     
        log.trace( "Entering ResultSettingsManagerImpl.get" );           
        ResultSettings theResultSettings = (ResultSettings) super.get(id, ResultSettings.class );
        return theResultSettings;
    }
 
    public ResultSettings getByUsername(String username) throws Exception
    {     
        log.trace( "Entering ResultSettingsManagerImpl.get" );           
        List theResultSettingsList = QueryManagerSingleton.instance().getResultSettingsByUsername( username );        
        
        if ( theResultSettingsList != null &&  theResultSettingsList.size() > 0 )
            return (ResultSettings) theResultSettingsList.get(0);
        else 
            return null;        
    }
    
    public void update(ResultSettings rs) throws Exception
    {
        log.trace( "Entering ResultSettingsManagerImpl.update" );
        
        // Save to db
        super.save(rs);       
    }
}
