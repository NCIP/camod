/*
 * Created on Jun 24, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.webapp.form;

import java.io.Serializable;

import org.apache.struts.upload.FormFile;

/**
 * @author rajputs
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TargetedModificationForm extends BaseForm implements Serializable, TargetedModificationData {
    
    private static final long serialVersionUID = 3257085453799404851L;
    
	/**
	 * Default empty constructor
	 * @author rajputs
	 *
	 * TODO To change the template for this generated type comment go to
	 * Window - Preferences - Java - Code Style - Code Templates
	 */
	public TargetedModificationForm() {}
	
	protected String name;
	protected String modificationType;
	protected String otherModificationType;
	protected String geneId;
	protected String esCellLineName;
	protected String blastocystName;
	protected String conditionedBy;
	protected String description;
	protected String comments;
	protected String numberMGI;
	protected String fileServerLocation;	
	protected FormFile fileLocation;	
	protected String title;
	protected String descriptionOfConstruct;
	
	/**
	 * @return Returns the name.
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}	
	/**
	 * @return Returns the modificationType.
	 */
	public String getModificationType() {
		return modificationType;
	}
	/**
	 * @param modificationType The modificationType to set.
	 */
	public void setModificationType(String modificationType) {
		this.modificationType = modificationType;
	}
	/**
	 * @return Returns the otherModificationType.
	 */
	public String getOtherModificationType() {
		return otherModificationType;
	}
	/**
	 * @param otherModificationType The otherModificationType to set.
	 */
	public void setOtherModificationType(String otherModificationType) {
		this.otherModificationType = otherModificationType;
	}	
	/**
	 * @return Returns the geneId.
	 */
	public String getGeneId() {
		return geneId;
	}
	/**
	 * @param geneId The geneId to set.
	 */
	public void setGeneId(String geneId) {
		this.geneId = geneId;
	}	
	/**
	 * @return Returns the esCellLineName.
	 */
	public String getEsCellLineName() {
		return esCellLineName;
	}
	/**
	 * @param esCellLineName The esCellLineName to set.
	 */
	public void setEsCellLineName(String esCellLineName) {
		this.esCellLineName = esCellLineName;
	}	
	/**
	 * @return Returns the blastocystName.
	 */
	public String getBlastocystName() {
		return blastocystName;
	}
	/**
	 * @param blastocystName The blastocystName to set.
	 */
	public void setBlastocystName(String blastocystName) {
		this.blastocystName = blastocystName;
	}
	/**
	 * @return Returns the conditionedBy.
	 */
	public String getConditionedBy() {
		return conditionedBy;
	}
	/**
	 * @param conditionedBy The conditionedBy to set.
	 */
	public void setConditionedBy(String conditionedBy) {
		this.conditionedBy = conditionedBy;
	}
	/**
	 * @return Returns the description.
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description The description to set.
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return Returns the comments.
	 */
	public String getComments() {
		return comments;
	}
	/**
	 * @param comments The comments to set.
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}
	/**
	 * @return Returns the numberMGI.
	 */
	public String getNumberMGI() {
		return numberMGI;
	}
	/**
	 * @param numberMGI The numberMGI to set.
	 */
	public void setNumberMGI(String numberMGI) {
		this.numberMGI = numberMGI;
	}
	public String getFileServerLocation() {
		return fileServerLocation;
	}
	public void setFileServerLocation(String fileServerLocation) {
		this.fileServerLocation = fileServerLocation;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescriptionOfConstruct() {
		return descriptionOfConstruct;
	}
	public void setDescriptionOfConstruct(String descriptionOfConstruct) {
		this.descriptionOfConstruct = descriptionOfConstruct;
	}
	public FormFile getFileLocation() {
		return fileLocation;
	}
	public void setFileLocation(FormFile fileLocation) {
		this.fileLocation = fileLocation;
	}

}
