package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.domain.ResultSettings;
import gov.nih.nci.camod.service.ResultSettingsManager;

import java.util.List;

/**
 * Implementation of the ResultSettingsManager interface. Creates/saves/updates the
 * ResultSettings based on the specific interface passed in
 */
public class ResultSettingsManagerImpl  extends BaseManager implements ResultSettingsManager
{
    /**
     * Save ResultSettings
     * 
     * @param inResultSettings
     *            the ResultSettings to save
     * 
     * @exception Exception
     *                when anything goes wrong.
     */
    public void save(ResultSettings inResultSettings) throws Exception
    {
        log.info( "Entering ResultSettingsManagerImpl.save" );
        
        // Save to db
        super.save(inResultSettings);
    }
    
    /**
     * Remove a specific ResultSettings by id
     * 
     * @param id
     *            the unique id for a ResultSettings
     * 
     * @exception Exception
     *                when anything goes wrong.
     */
    public void remove(String id) throws Exception
    {
        log.info( "Entering ResultSettingsManagerImpl.remove" );
        
        //delete from db
        super.remove( id, ResultSettings.class );      
    }
    
    /**
     * Get a specific ResultSettings by id
     * 
     * @param id
     *            the unique id for a ResultSettings
     * 
     * @exception Exception
     *                when anything goes wrong.
     */
    public ResultSettings get(String id) throws Exception
    {     
        log.info( "Entering ResultSettingsManagerImpl.get" );  
        
        ResultSettings theResultSettings = (ResultSettings) super.get(id, ResultSettings.class );
        return theResultSettings;
    }
    
    /**
     * Remove a specific ResultSettings by username
     * 
     * @param username
     *            the unique username for a ResultSettings
     * 
     * @exception Exception
     *                when anything goes wrong.
     */
    public ResultSettings getByUsername(String username) throws Exception
    {     
        log.info( "Entering ResultSettingsManagerImpl.get" );       
        
        List theResultSettingsList = QueryManagerSingleton.instance().getResultSettingsByUsername( username );        
        
        if ( theResultSettingsList != null &&  theResultSettingsList.size() > 0 )
        {
            return (ResultSettings) theResultSettingsList.get(0);
        }
        else 
        {
            log.error("ResultSettings not found for " + username );
            return null;        
        }
    }
    
    /**
     * Update a specific ResultSettings
     * 
     * @param inResultSettings
     *            the ResultSettings to update
     * 
     * @exception Exception
     *                when anything goes wrong.
     */
    public void update(ResultSettings inResultSettings) throws Exception
    {
        log.info( "Entering ResultSettingsManagerImpl.update" );
        
        // Save to db
        super.save(inResultSettings);       
    }
}
