/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/camod/LICENSE.txt for details.
 */

package gov.nih.nci.camod.webapp.form;

public interface TransgeneData
{

    public String getConstructSequence();

    public void setConstructSequence(String constructSequence);
    
	public String getMgiId();
	
	public void setMgiId(String mgiId);

	public String getRgdId();

	public void setRgdId(String rgdId);

	public String getZfinId();

	public void setZfinId(String zfinId);    
}
