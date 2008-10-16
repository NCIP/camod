package gov.nih.nci.camod.domain;

import java.io.Serializable;

public class AbsCanModPublication extends BaseObject implements Serializable
{
    private static final long serialVersionUID = 3258745453799404851L;

    private long absCancerModelId;
    private long publicationId;
    
	/**
	 * @return the absCancerModelId
	 */
	public long getAbsCancerModelId() {
		return absCancerModelId;
	}
	/**
	 * @param absCancerModelId the absCancerModelId to set
	 */
	public void setAbsCancerModelId(long absCancerModelId) {
		this.absCancerModelId = absCancerModelId;
	}
	/**
	 * @return the publicationId
	 */
	public long getPublicationId() {
		return publicationId;
	}
	/**
	 * @param publicationId the publicationId to set
	 */
	public void setPublicationId(long publicationId) {
		this.publicationId = publicationId;
	}
    

}
