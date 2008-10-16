package gov.nih.nci.camod.domain;

import java.io.Serializable;

public class CellLinePublication extends BaseObject implements Serializable
{
    private static final long serialVersionUID = 3258745453799404851L;

    private long cellLineId;
    private long publicationId;
    
	/**
	 * @return the cellLineId
	 */
	public long getCellLineId() {
		return cellLineId;
	}
	/**
	 * @param cellLineId the cellLineId to set
	 */
	public void setCellLineId(long cellLineId) {
		this.cellLineId = cellLineId;
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
