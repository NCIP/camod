/*******************************************************************************
 * Copyright: (c) 2004-2009 Mayo Foundation for Medical Education and 
 * Research (MFMER). All rights reserved. MAYO, MAYO CLINIC, and the
 * triple-shield Mayo logo are trademarks and service marks of MFMER.
 * 
 * Except as contained in the copyright notice above, or as used to identify 
 * MFMER as the author of this software, the trade names, trademarks, service
 * marks, or product names of the copyright holder shall not be used in
 * advertising, promotion or otherwise in connection with this software without
 * prior written authorization of the copyright holder.
 *   
 * Licensed under the Eclipse Public License, Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at 
 *   
 *  		http://www.eclipse.org/legal/epl-v10.html
 * 
 *  		
 *******************************************************************************/

package gov.nih.nci.evs.app.util;

import gov.nih.nci.evs.app.Constants;
import javax.swing.tree.DefaultMutableTreeNode;
import gov.nih.nci.lexbig.ext.TreeNode;
import gov.nih.nci.system.client.ApplicationServiceProvider;
import org.LexGrid.LexBIG.DataModel.Collections.AssociatedConceptList;
import org.LexGrid.LexBIG.DataModel.Collections.AssociationList;
import org.LexGrid.LexBIG.DataModel.Collections.LocalNameList;
import org.LexGrid.LexBIG.DataModel.Collections.ResolvedConceptReferenceList;
import org.LexGrid.LexBIG.DataModel.Core.AssociatedConcept;
import org.LexGrid.LexBIG.DataModel.Core.Association;
import org.LexGrid.LexBIG.DataModel.Core.CodingSchemeVersionOrTag;
import org.LexGrid.LexBIG.DataModel.Core.ResolvedConceptReference;
import org.LexGrid.LexBIG.Exceptions.LBException;
import org.LexGrid.LexBIG.LexBIGService.CodedNodeSet;
import org.LexGrid.LexBIG.LexBIGService.LexBIGService;
import org.LexGrid.LexBIG.Utility.Constructors;
import org.LexGrid.LexBIG.Utility.ConvenienceMethods;
import org.LexGrid.commonTypes.EntityDescription;
import org.LexGrid.concepts.Concept;
import org.apache.log4j.Logger;

/**
 * Utility class to build a Tree from a root node, following to the closure of the graph. 
 * 
 * @author <a href="mailto:kevin.peterson@mayo.edu">Kevin Peterson</a>
 *
 */
public class RecursiveTreeBuilder {

	private static Logger log = Logger.getLogger(RecursiveTreeBuilder.class.getName());
	private static LexBIGService lbSvc;
	static DefaultMutableTreeNode finalTree;
	String url = "http://lexevsapi60.nci.nih.gov/lexevsapi60";
	
	public RecursiveTreeBuilder(LexBIGService lbs) throws Exception{
		this.lbSvc = 	(LexBIGService) ApplicationServiceProvider.getApplicationServiceFromUrl(url, "EvsServiceInfo");
	}
	
	/**
	 * Generates the entire Tree from a given root node. Levels are added by recursive calls to EVS, 
	 * so return limits and memory issues may be avoided when building very large trees.
	 * 
	 * @param vocabularyName
	 * @param rootCode
	 * @return
	 * @throws Exception
	 * 
	 * ResolvedConceptReference = Resolved information for a concept. Maintained for backward compatibility, 
	 * but no longer enhanced in favor of the more flexible ResolvedCodedNodeReference. 
	 * ResolvedCodedNodeReference = Resolved information for an entity identified by coding scheme and code. 
	 */
	public DefaultMutableTreeNode getTree(String vocabularyName, String rootCode) throws Exception {
		//System.out.println("RTB getTree building tree for root node: " + rootCode + ".");		
		CodedNodeSet cns = lbSvc.getCodingSchemeConcepts(vocabularyName, null);
		cns = cns.restrictToCodes(Constructors.createConceptReferenceList(rootCode));
		ResolvedConceptReference returnTree = cns.resolve(null, null, null).next();
		
		buildTree(vocabularyName, returnTree);
		
		return finalTree;
	}


	/**
	 * Recursively builds the Tree, adding one level at a time.
	 * 
	 * @param vocabularyName
	 * @param rootCode
	 * @return
	 * @throws Exception
	 * 
	 * AssociatedConcept = A concept reference that is the source or target of an association. 
	 * AssociatedConceptList = A list of AssociatedConcept's
	 * Association = The representation of a particular association as it appears in a CodedNode
	 * CodedNode = 
	 */

	protected void buildTree(String vocabularyName, ResolvedConceptReference rootCode) throws Exception {
		//System.out.println("Entered buildTree with rootCode: " + rootCode.getConceptCode());

		DefaultMutableTreeNode branch = new DefaultMutableTreeNode();
		finalTree = createRootNode(rootCode);	
		
		AssociatedConceptList conceptList = getChildrenOfNode(rootCode.getConceptCode(), lbSvc, vocabularyName, null);
		//System.out.println(rootCode + " has " + conceptList.getAssociatedConceptCount() + " children. " );
			
				for (int j = 0; j < conceptList.getAssociatedConceptCount(); j++) {				
					AssociatedConcept assocConcept = conceptList.getAssociatedConcept(j);
					//1. Construct a next level concept based on an AssociatedConcept
					//2. Construct a TreeNode called child_node and attached concept to TreeNode as a member variable
					//3. Create a child_dmtn based on the user object child_node
					//4. Insert child_dmtn to the original dmtn as a child node
					TreeNode child_node = new TreeNode();
					Concept concept = new Concept();
					concept.setEntityDescription(assocConcept.getEntityDescription());
					concept.setEntityCode(assocConcept.getConceptCode());
					child_node.setConcept(concept);
					DefaultMutableTreeNode child_dmtn = new DefaultMutableTreeNode(child_node);
					
					populateTreeNode(vocabularyName, assocConcept, child_dmtn);					
				}						
	}
	
	protected void populateTreeNode(String vocabularyName, AssociatedConcept assocConcept, DefaultMutableTreeNode parent_dmtn) throws Exception {
		//System.out.println("Enter populateTreeNode for code: " + assocConcept.getConceptCode());
		// 1. find children for node
		// 2. create child nodes and add them to the parent node
		// 3. recursively call this method			
			AssociatedConceptList conceptList = getChildrenOfNode(assocConcept.getConceptCode(), lbSvc, vocabularyName, null);
			//System.out.println(assocConcept.getConceptCode() + " has " + conceptList.getAssociatedConceptCount() + " children in populateTreeNode.");
			
			for (int i = 0; i < conceptList.getAssociatedConceptCount(); i++) {				
				AssociatedConcept aConcept = conceptList.getAssociatedConcept(i);	
				TreeNode child_node = new TreeNode();
				Concept concept = new Concept();
				concept.setEntityDescription(aConcept.getEntityDescription());
				concept.setEntityCode(aConcept.getConceptCode());
				//System.out.println("populateTreeNode working on code: " + aConcept.getConceptCode());
				child_node.setConcept(concept);
				DefaultMutableTreeNode  child_dmtn = new DefaultMutableTreeNode(child_node);
				
				if(hasChildren(aConcept.getConceptCode(), lbSvc, vocabularyName, null)) {
					//System.out.println("populateTreeNode: " + aConcept.getConceptCode() + " has children.");
					populateTreeNode(vocabularyName, aConcept, child_dmtn);	
				}
					
				parent_dmtn.insert(child_dmtn, i);
			}
		finalTree.add(parent_dmtn);
	}

	
	/**
	 * Display relations from the given code to other concepts.
	 * @param code
	 * @param relation
	 * @param lbSvc
	 * @param scheme
	 * @param csvt
	 * @throws LBException
	 */
	protected static AssociatedConceptList getChildrenOfNode(String code, LexBIGService lbSvc, String scheme, CodingSchemeVersionOrTag csvt)
		throws LBException
	{		
		//System.out.println("Entered getChildrenOfNode() for scheme: " + scheme);
		String[] associationsToNavigate;
		
		if(scheme.equals(Constants.ZEBRAFISH_SCHEMA)){
			//System.out.println("getChildrenOfNode() for scheme (Zebrafish) loop: " + scheme);
			associationsToNavigate = new String[3];
			associationsToNavigate[0] = "subClassOf";
			associationsToNavigate[1] = "is_a";
			associationsToNavigate[2] = "part_of";
		} else {
			//System.out.println("getChildrenOfNode() for scheme (NCI_Thesaurus): " + scheme);
			associationsToNavigate = new String[2];
			associationsToNavigate[0] = "subClassOf";
			associationsToNavigate[1] = "R82";
			//associationsToNavigate[1] = "Anatomic_Structure_Is_Physical_Part_Of";	
		}
			
		// Perform the query ...
		AssociatedConceptList conceptList = new AssociatedConceptList();
		
		ResolvedConceptReferenceList matches =
			lbSvc.getNodeGraph(scheme, csvt, null)
		    .restrictToAssociations(Constructors.createNameAndValueList(associationsToNavigate), null)
			//.restrictToAssociations(nvList, null)
				.resolveAsList(ConvenienceMethods.createConceptReference(code, scheme),
					false, true, 1, 1, new LocalNameList(), null, null, 1024);
		
		// Analyze the result ...
		if (matches.getResolvedConceptReferenceCount() > 0) {
			ResolvedConceptReference ref =
				(ResolvedConceptReference) matches.enumerateResolvedConceptReference().nextElement();
			//System.out.println("getChildrenOfNode() ref.getCode(): " + ref.getCode());
			
			// Print the associations - added this not null check for Rat Diagnosis - *** Find out why later ***
			if(ref.getTargetOf() !=null){
				AssociationList targetof = ref.getTargetOf();
				Association[] associations = targetof.getAssociation();
				for (int i = 0; i < associations.length; i++) {
					Association assoc = associations[i];
					AssociatedConcept[] acl = assoc.getAssociatedConcepts().getAssociatedConcept();
					for (int j = 0; j < acl.length; j++) {
						AssociatedConcept ac = acl[j];
						// Tried to filter out anonymous equivalent classes for human diagnosis (skipped) (add in LexEVS 6.0)
						conceptList.addAssociatedConcept(ac);												
						//EntityDescription ed = ac.getEntityDescription();
						//System.out.println("\t\t Code: " + code + " has child: "+ ac.getConceptCode() + "/"+ (ed == null?"**No Description**":ed.getContent()));
					}
				}
			}
		} 
		return conceptList;
	}	
	
	protected boolean hasChildren(String code, LexBIGService lbSvc, String scheme, CodingSchemeVersionOrTag csvt)
	throws LBException
{
	boolean hasChildren = false;
	String[] associationsToNavigate;
	
	if(scheme.equals(Constants.ZEBRAFISH_SCHEMA)){
		associationsToNavigate = new String[3];
		associationsToNavigate[0] = "subClassOf";
		associationsToNavigate[1] = "is_a";
		associationsToNavigate[2] = "part_of";
	} else {
		associationsToNavigate = new String[2];
		associationsToNavigate[0] = "subClassOf";
		//"Anatomic_Structure_Is_Physical_Part_Of" has a code of "R82".  Must use code.	
		associationsToNavigate[1] = "R82";
	}
	
	// Perform the query ...
	AssociatedConceptList conceptList = new AssociatedConceptList();
	
	ResolvedConceptReferenceList matches =
		lbSvc.getNodeGraph(scheme, csvt, null)
	    .restrictToAssociations(Constructors.createNameAndValueList(associationsToNavigate), null)
		//.restrictToAssociations(nvList, null)
			.resolveAsList(ConvenienceMethods.createConceptReference(code, scheme),
				false, true, 1, 1, new LocalNameList(), null, null, 1024);
	
	// Analyze the result ...
	if (matches.getResolvedConceptReferenceCount() > 0) {
		ResolvedConceptReference ref =
			(ResolvedConceptReference) matches.enumerateResolvedConceptReference().nextElement();
		//System.out.println("getChildrenOfNode() ref.getCode(): " + ref.getCode());
		
		// Print the associations - added this not null check for Rat Diagnosis - *** Find out why later ***
		if(ref.getTargetOf() !=null){
			AssociationList targetof = ref.getTargetOf();
			Association[] associations = targetof.getAssociation();
			for (int i = 0; i < associations.length; i++) {
				Association assoc = associations[i];
				AssociatedConcept[] acl = assoc.getAssociatedConcepts().getAssociatedConcept();
				for (int j = 0; j < acl.length; j++) {
					AssociatedConcept ac = acl[j];
					if(acl != null){
						// Tried to filter out anonymous equivalent classes for human diagnosis (add in LexEVS 6.0)
						hasChildren = true;
						//System.out.println("hasChildren: " + hasChildren);
						conceptList.addAssociatedConcept(ac);
							
						//EntityDescription ed = ac.getEntityDescription();
						//System.out.println("\t\t Code: " + code + " has child: "+ ac.getConceptCode() + "/"+ (ed == null?"**No Description**":ed.getContent()));
					}
				}
			}
		}
	} 
	return hasChildren;
}
	

	
	protected DefaultMutableTreeNode createRootNode(ResolvedConceptReference rootCode){
		
		Concept concept = new Concept();
		concept.setEntityCode(rootCode.getConceptCode());
		EntityDescription ed = new EntityDescription();
		ed.setContent(rootCode.getEntityDescription().getContent());
		concept.setEntityDescription(ed);
		TreeNode childNode = new TreeNode();
		childNode.setConcept(concept);
		DefaultMutableTreeNode tree = new DefaultMutableTreeNode(childNode);
		//System.out.println("createRootNode - created root node. "  );
		return tree;
	}




	
	
	


}
