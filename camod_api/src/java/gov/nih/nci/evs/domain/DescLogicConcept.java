package gov.nih.nci.evs.domain;

import java.util.*;

/**
 * The DescLogicConcept class represents the fundermental vocabulary entity in
 * the NCI Thesaurus.
 * 
 */
public class DescLogicConcept implements java.io.Serializable {
	private static final long serialVersionUID = 1234567890L;

	/**
	 * Specifies the name of the concept.
	 */

	private java.lang.String name;

	/**
	 * Returns the name for this DescLogicConcept
	 * 
	 * @return - name
	 */
	public java.lang.String getName() {
		return name;
	}

	/**
	 * Sets the specified name for this DescLogicConcept
	 * 
	 * @param -
	 *            name
	 */
	public void setName(java.lang.String name) {
		this.name = name;
	}

	/**
	 * The namespaceId is used to identify a set of concepts within a
	 * terminology such as the NCI Thesaurus.
	 */

	private int namespaceId;

	/**
	 * Returns the namespaceId for this DescLogicConcept
	 * 
	 * @return - namespaceId
	 */
	public int getNamespaceId() {
		return namespaceId;
	}

	/**
	 * Sets the specified namespaceId for this DescLogicConcept
	 * 
	 * @param -
	 *            namespaceId
	 */
	public void setNamespaceId(int namespaceId) {
		this.namespaceId = namespaceId;
	}

	/**
	 * Sets this value to 'true' if a concept is retired.
	 */

	private java.lang.Boolean isRetired;

	/**
	 * Returns the isRetired value for this DescLogicConcept
	 * 
	 * @return - isRetired
	 */
	public java.lang.Boolean getIsRetired() {
		return isRetired;
	}

	/**
	 * @deprecated - The preffered way to do this is by using the getIsRetired
	 *             method
	 */
	public java.lang.Boolean isRetired() {
		return isRetired;
	}

	/**
	 * Sets the isRetired value for this DescLogicConcept
	 * 
	 * @param -
	 *            isRetired
	 */
	public void setIsRetired(java.lang.Boolean isRetired) {
		this.isRetired = isRetired;
	}

	/**
	 * This value is set to 'true' if a concept has a parent
	 */

	private java.lang.Boolean hasParents;

	/**
	 * Returns the hasParent value for this DescLogicConcept
	 * 
	 * @return - hasParents
	 */
	public java.lang.Boolean getHasParents() {
		return hasParents;
	}

	/**
	 * @deprecated - The preffered way to do this is by using the getHasParents
	 *             method
	 */
	public java.lang.Boolean hasParents() {
		return hasParents;
	}

	/**
	 * Sets the specified hasParent value for this DescLogicConcept
	 * 
	 * @param -
	 *            hasParents
	 */
	public void setHasParents(java.lang.Boolean hasParents) {
		this.hasParents = hasParents;
	}

	/**
	 * Sets this value to 'true' if a concept has children
	 */

	private java.lang.Boolean hasChildren;

	/**
	 * Returns the hasChildren value for this DescLogicConcept
	 * 
	 * @return - hasChildren
	 */
	public java.lang.Boolean getHasChildren() {
		return hasChildren;
	}

	/**
	 * @deprecated - The preffered way to do this is by using the getHasChildren
	 *             method
	 */
	public java.lang.Boolean hasChildren() {
		return hasChildren;
	}

	/**
	 * Sets the specified hasChildren value for this DescLogicConcept
	 * 
	 * @param -
	 *            hasChildren
	 */
	public void setHasChildren(java.lang.Boolean hasChildren) {
		this.hasChildren = hasChildren;
	}

	/**
	 * This is an unique code associated with a concept within the specified
	 * vocabulary.
	 */

	private java.lang.String code;

	/**
	 * Returns the concept code for this DescLogicConcept
	 * 
	 * @return - code
	 */
	public java.lang.String getCode() {
		return code;
	}

	/**
	 * Sets the specified concept code for this DescLogicConcept
	 * 
	 * @param -
	 *            code
	 */
	public void setCode(java.lang.String code) {
		this.code = code;
	}

	/**
	 * Stores the inverse relationship between two concepts
	 */

	private java.util.Vector inverseRoleCollection = new Vector();

	/**
	 * Returns the inverse role value for this DescLogicConcept
	 * 
	 * @return - inverseRoleCollection
	 */
	public java.util.Vector getInverseRoleCollection() {
		return inverseRoleCollection;
	}

	/**
	 * Sets the specified inverse role value for this DescLogicConcept
	 * 
	 * @param -
	 *            inverseRoleCollection
	 */
	public void setInverseRoleCollection(java.util.Vector inverseRoleCollection) {
		this.inverseRoleCollection = inverseRoleCollection;
	}

	/**
	 * 
	 */

	private java.util.Vector semanticTypeVector = new Vector();

	/**
	 * @deprecated - The preferred way of getting a semantic type is by using
	 *             the getPropertyCollection method. 'Semantic_Type' is the name
	 *             of a property in a DescLogicConcept.
	 */

	/**
	 * Returns the SemanticTypes of this DescLogicConcept
	 * 
	 * @return - semanticTypeVector
	 */
	public java.util.Vector getSemanticTypeVector() {
		return semanticTypeVector;
	}

	/**
	 * Sets the specified Semantic Types for this DescLogicConcept
	 * 
	 * @param -
	 *            semanticTypeVector
	 */
	public void setSemanticTypeVector(java.util.Vector semanticTypeVector) {
		this.semanticTypeVector = semanticTypeVector;
	}

	/**
	 * This attribute holds association information between concepts
	 */

	private java.util.Vector inverseAssociationCollection = new Vector();

	/**
	 * Returns the inverse association information of this DescLogicConcept
	 * 
	 * @return - inverseAssociationCollection
	 */
	public java.util.Vector getInverseAssociationCollection() {
		return inverseAssociationCollection;
	}

	/**
	 * Sets the specified inverse association value for this DescLogicConcept
	 * 
	 * @param -
	 *            inverseAssociationCollection
	 */
	public void setInverseAssociationCollection(
			java.util.Vector inverseAssociationCollection) {
		this.inverseAssociationCollection = inverseAssociationCollection;
	}

	private gov.nih.nci.evs.domain.TreeNode treeNode;

	/**
	 * @deprecated - The preferred way of doing this is by using the
	 *             getEdgeProperties method
	 * @return - treeNode
	 */

	public gov.nih.nci.evs.domain.TreeNode getTreeNode() {
		return treeNode;
	}

	/**
	 * @deprecated - the preferred way of doing this is by using the
	 *             setEdgeProperties method
	 * @param -
	 *            treeNode
	 */

	public void setTreeNode(gov.nih.nci.evs.domain.TreeNode treeNode) {
		this.treeNode = treeNode;
	}

	private java.util.Vector associationCollection = new java.util.Vector();

	/**
	 * Returns the associations of this DescLogicConcept
	 * 
	 * @return - associationCollection
	 */

	public java.util.Vector getAssociationCollection() {
		return associationCollection;
	}

	/**
	 * Sets the specified associations for this DescLogicConcept
	 * 
	 * @return - associationCollection
	 */

	public void setAssociationCollection(java.util.Vector associationCollection) {

		this.associationCollection = associationCollection;
	}

	private java.util.Vector roleCollection = new java.util.Vector();

	/**
	 * Returns the roles of this DescLogicConcept
	 * 
	 * @return - roleCollection
	 */

	public java.util.Vector getRoleCollection() {
		return roleCollection;
	}

	/**
	 * Sets the specified roles for this DescLogicConcept
	 * 
	 * @return - roleCollection
	 */

	public void setRoleCollection(java.util.Vector roleCollection) {

		this.roleCollection = roleCollection;
	}

	private gov.nih.nci.evs.domain.EdgeProperties edgeProperties;

	/**
	 * Returns the edgeProperties for this DescLogicConcept.
	 * 
	 * @return - edgeProperties
	 */

	public gov.nih.nci.evs.domain.EdgeProperties getEdgeProperties() {
		return edgeProperties;
	}

	/**
	 * Sets the specified edgeProperties value for this DescLogicConcept when a
	 * tree is generated.
	 * 
	 * @param -
	 *            edgeProperties
	 */

	public void setEdgeProperties(
			gov.nih.nci.evs.domain.EdgeProperties edgeProperties) {
		this.edgeProperties = edgeProperties;
	}

	private java.util.Vector propertyCollection = new java.util.Vector();

	/**
	 * Returns the properties of this DescLogicConcept
	 * 
	 * @return - propertyCollection
	 */

	public java.util.Vector getPropertyCollection() {
		return propertyCollection;
	}

	/**
	 * Sets the specified properties for this DescLogicConcept
	 * 
	 * @return - propertyCollection
	 */

	public void setPropertyCollection(java.util.Vector propertyCollection) {

		this.propertyCollection = propertyCollection;
	}

	public boolean equals(Object obj) {
		boolean eq = false;
		if (obj instanceof DescLogicConcept) {
			DescLogicConcept c = (DescLogicConcept) obj;

			String thisKey = getCode();
			if (thisKey != null && thisKey.equals(c.getCode())) {
				eq = true;
			}

		}
		return eq;
	}

	public int hashCode() {
		int h = 0;
		if (getCode() != null) {
			h += getCode().hashCode();
		}
		return h;
	}

}
