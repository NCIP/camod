package gov.nih.nci.camod.domain;

import java.io.Serializable;

public class TherapyPublication extends BaseObject implements Serializable
{
    private static final long serialVersionUID = 3258745453799404851L;

    private long therapyId;
    private long publicationId;
    

	/**
	 * @return the therapyId
	 */
	public long getTherapyId() {
		return therapyId;
	}
	/**
	 * @param therapyId the therapyId to set
	 */
	public void setTherapyId(long therapyId) {
		this.therapyId = therapyId;
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
