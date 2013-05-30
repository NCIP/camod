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

import gov.nih.nci.lexbig.ext.TreeNode;

import java.lang.reflect.Method;
import java.util.Enumeration;

import javax.swing.tree.DefaultMutableTreeNode;

import org.LexGrid.LexBIG.DataModel.Collections.AssociationList;
import org.LexGrid.LexBIG.DataModel.Collections.CodingSchemeRenderingList;
import org.LexGrid.LexBIG.DataModel.Collections.ExtensionDescriptionList;
import org.LexGrid.LexBIG.DataModel.Collections.LocalNameList;
import org.LexGrid.LexBIG.DataModel.Collections.NameAndValueList;
import org.LexGrid.LexBIG.DataModel.Collections.ResolvedConceptReferenceList;
import org.LexGrid.LexBIG.DataModel.Core.AssociatedConcept;
import org.LexGrid.LexBIG.DataModel.Core.Association;
import org.LexGrid.LexBIG.DataModel.Core.CodingSchemeSummary;
import org.LexGrid.LexBIG.DataModel.Core.CodingSchemeVersionOrTag;
import org.LexGrid.LexBIG.DataModel.Core.NameAndValue;
import org.LexGrid.LexBIG.DataModel.Core.ResolvedConceptReference;
import org.LexGrid.LexBIG.DataModel.InterfaceElements.CodingSchemeRendering;
import org.LexGrid.LexBIG.DataModel.InterfaceElements.ExtensionDescription;
import org.LexGrid.LexBIG.Exceptions.LBException;
import org.LexGrid.LexBIG.Extensions.Generic.LexBIGServiceConvenienceMethods;
import org.LexGrid.LexBIG.LexBIGService.CodedNodeGraph;
import org.LexGrid.LexBIG.LexBIGService.CodedNodeSet;
import org.LexGrid.LexBIG.LexBIGService.LexBIGService;
import org.LexGrid.LexBIG.LexBIGService.LexBIGServiceManager;
import org.LexGrid.LexBIG.Utility.ConvenienceMethods;
import org.LexGrid.codingSchemes.CodingScheme;
import org.LexGrid.commonTypes.EntityDescription;
import org.LexGrid.concepts.Concept;

/**
 * The Class PrintUtility.
 *
 * @author <a href="https://cabig-kc.nci.nih.gov/Vocab/KC/index.php/LexBig_and_LexEVS">The LexEVS Team</a>
 */
public class PrintUtility {



	public static void printTree(DefaultMutableTreeNode treeNode){
		printChildren(treeNode, 0);
	}

	public static void printChildren(DefaultMutableTreeNode treeNode, int level){
		Object obj = treeNode.getUserObject();
		TreeNode node = (TreeNode)obj;
		Concept c = node.getConcept();
		System.out.println(getIndent(level) + c.getEntityCode() + "\t EntityDescription: " + c.getEntityDescription().getContent());
		for(int i=0;i<treeNode.getChildCount();i++){
			DefaultMutableTreeNode dmtn = (DefaultMutableTreeNode)treeNode.getChildAt(i);
			printChildren(dmtn, level + 1);
		}
	}

	private static String getIndent(int level){
		//System.out.println("Entered getIndent");
		String indent = "";
		for(int i=0;i<level;i++){
			indent = indent + " -> ";
		}
		return indent;
	}

	/**
	 * Prints the.
	 *
	 * @param assocs the assocs
	 */
	public static void print(AssociationList assocs){
		print(assocs, 0);
	}

	/**
	 * Prints the.
	 *
	 * @param assocs the assocs
	 * @param depth the depth
	 */
	public static void print(AssociationList assocs, int depth){
		for(Association assoc : assocs.getAssociation()){
			System.out.println(buildPrefix(depth) + "Association: " + assoc.getAssociationName());
			for(AssociatedConcept concept : assoc.getAssociatedConcepts().getAssociatedConcept()){
				if(concept !=null) {
					print(concept, depth+1);
				}
			}
		}
	}

	/**
	 * Prints the.
	 *
	 * @param ref the ref
	 */
	public static void print(ResolvedConceptReference ref){
		print(ref, 0);
	}

	/**
	 * Prints the.
	 *
	 * @param ref the ref
	 * @param depth the depth
	 */
	private static void print(ResolvedConceptReference ref, int depth){
		String code = ref.getConceptCode();

		String description;
		if(ref.getEntityDescription() != null){
			description = ref.getEntityDescription().getContent();
		} else {
			description = "";
		}

		System.out.println(buildPrefix(depth) + "Code: " + code + ", Description: " + description + " Hash: " + ref.hashCode());
		if(ref.getSourceOf() != null){
			print(ref.getSourceOf(), depth+1);
		}
		if(ref.getTargetOf() != null){
			print(ref.getTargetOf(), depth+1);
		}
	}

	/**
	 * Prints the.
	 *
	 * @param list the list
	 */
	public static void print(ResolvedConceptReferenceList list){
		for(ResolvedConceptReference ref : list.getResolvedConceptReference()){
			print(ref);
		}
	}

	public static void print(AssociatedConcept ref){
        print(ref, 0);
    }

	/**
	 * Prints the.
	 *
	 * @param ref the ref
	 * @param depth the depth
	 */
	private static void print(AssociatedConcept ref, int depth){
		String description;
		if(ref.getEntityDescription() == null) {
			description = "NOT AVAILABLE";
		} else {
			description = ref.getEntityDescription().getContent();
		}
		System.out.println(buildPrefix(depth) + "Code: " + ref.getCode() + ", Description: " + description + " Hash: " + ref.hashCode());
		print(ref.getAssociationQualifiers(), "Qualifiers", depth + 1);

		if(ref.getSourceOf() != null){
			print(ref.getSourceOf(), depth+1);
		}
		if(ref.getTargetOf() != null){
			print(ref.getTargetOf(), depth+1);
		}
	}

	private static void print(NameAndValueList nameAndValueList, String label, int depth){
	    if(nameAndValueList != null) {
	        System.out.println(buildPrefix(depth, false) + label + ":");
	        for(NameAndValue nv : nameAndValueList.getNameAndValue()) {
	            System.out.println(buildPrefix(depth + 1, false) + "/ Name: " + nv.getName());
	            System.out.println(buildPrefix(depth + 1, false) + "\\ Value: " + nv.getContent());
	        }
	    }
	}

	/**
	 * Prints the.
	 *
	 * @param summary the summary
	 */
	public static void print(CodingSchemeSummary summary){
		System.out.println("CodingScheme: " + summary.getLocalName());
		System.out.println(" - Formal Name: " + summary.getFormalName());
		System.out.println(" - Version: " + summary.getRepresentsVersion());
	}

	/**
	 * Prints the.
	 *
	 * @param csrl the csrl
	 */
	public static void print(CodingSchemeRenderingList csrl){
		for(CodingSchemeRendering rendering : csrl.getCodingSchemeRendering()){
			print(rendering.getCodingSchemeSummary());
		}
	}

	/**
	 * Prints the.
	 *
	 * @param ed the ed
	 */
	public static void print(ExtensionDescription ed){
		System.out.println("Extension Name: " + ed.getName());
		System.out.println(" - Description: " + ed.getDescription());
		System.out.println(" - Extension Base Class: " + ed.getExtensionBaseClass());
		System.out.println(" - Extension Implementing Class: " + ed.getExtensionClass());
	}

	/**
	 * Prints the.
	 *
	 * @param edl the edl
	 */
	public static void print(ExtensionDescriptionList edl){
		for(ExtensionDescription description : edl.getExtensionDescription()){
			print(description);
		}
	}

	/**
	 * Prints the.
	 *
	 * @param cs the cs
	 */
	public static void print(CodingScheme cs){
		System.out.println("CodingScheme: " + cs.getLocalName());
		System.out.println(" - Formal Name: " + cs.getFormalName());
		System.out.println(" - Version: " + cs.getRepresentsVersion());
	}

	/**
	 * Prints the.
	 *
	 * @param entity the entity
	 */
	public static void print(Concept entity){
		System.out.println("Code: " + entity.getEntityCode());
		System.out.println(" - Description: " + entity.getEntityDescription().getContent());
	}

	/**
	 * Prints the.
	 *
	 * @param obj the obj
	 */
	public static void print(Object obj){
		System.out.println("Result is: " + obj.toString());
	}

	/**
	 * Prints the.
	 *
	 * @param cns the cns
	 */
	public static void print(CodedNodeSet cns){
		try {
			ResolvedConceptReferenceList rcrl = cns.resolveToList(null,null,null, -1);
			ResolvedConceptReference[] rcrArray = rcrl.getResolvedConceptReference();
			for(ResolvedConceptReference rcr: rcrArray){
				print(rcr);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Prints the.
	 *
	 * @param cng the cng
	 */
	public static void print(CodedNodeGraph cng) {

		ResolvedConceptReferenceList rcrl;
		try {
			rcrl = cng.resolveAsList(null, false, true, 0, 0, null, null, null,
					-1);
			ResolvedConceptReference[] rcrArray = rcrl
					.getResolvedConceptReference();
			for (ResolvedConceptReference rcr : rcrArray) {
				print(rcr);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Prints the.
	 *
	 * @param lbsm the lbsm
	 */
	public static void print(LexBIGServiceConvenienceMethods lbsm) {

		Method[] methods = lbsm.getClass().getDeclaredMethods();
		for (Method m : methods) {
			System.out.println(m.getName());
		}
	}

	/**
	 * Prints the.
	 *
	 * @param lbsm the lbsm
	 */
	public static void print(LexBIGServiceManager lbsm) {

		Method[] methods = lbsm.getClass().getDeclaredMethods();
		for (Method m : methods) {
			System.out.println(m.getName());
		}
	}

	/**
	 * Builds the prefix.
	 *
	 * @param depth the depth
	 *
	 * @return the string
	 */
	private static String buildPrefix(int depth){
		String prefix = "";
		for(int i=0;i<depth;i++){
			prefix = prefix + " -> ";
		}
		return prefix;
	}

	private static String buildPrefix(int depth, boolean buildArrows){
        String prefix = "";
        for(int i=0;i<depth;i++){
            String padding;
            if(buildArrows) {
                padding = " -> ";
            } else {
                padding = "    ";
            }

            prefix = prefix + padding;
        }
        return prefix;
    }


	protected static void printTo(String code, String relation, LexBIGService lbSvc, String scheme, CodingSchemeVersionOrTag csvt)
	throws LBException
{
	System.out.println("Points to ...");

	// Perform the query ...
	NameAndValue nv = new NameAndValue();
	NameAndValueList nvList = new NameAndValueList();
	nv.setName(relation);
	nvList.addNameAndValue(nv);

	ResolvedConceptReferenceList matches =
		lbSvc.getNodeGraph(scheme, csvt, null)
			.restrictToAssociations(nvList, null)
			.resolveAsList(
				ConvenienceMethods.createConceptReference(code, scheme),
				true, false, 1, 1, new LocalNameList(), null, null, 1024);

	// Analyze the result ...
	if (matches.getResolvedConceptReferenceCount() > 0) {
		ResolvedConceptReference ref =
			(ResolvedConceptReference) matches.enumerateResolvedConceptReference().nextElement();

		// Print the associations
		AssociationList sourceof = ref.getSourceOf();
		Association[] associations = sourceof.getAssociation();
		for (int i = 0; i < associations.length; i++) {
			Association assoc = associations[i];
			AssociatedConcept[] acl = assoc.getAssociatedConcepts().getAssociatedConcept();
			for (int j = 0; j < acl.length; j++) {
				AssociatedConcept ac = acl[j];
				EntityDescription ed = ac.getEntityDescription();
				System.out.println(
					"\t\t" + ac.getConceptCode() + "/"
						+ (ed == null?
								"**No Description**":ed.getContent()));
			}
		}
	}
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
	protected static void printFrom(String code, String relation, LexBIGService lbSvc, String scheme, CodingSchemeVersionOrTag csvt)
		throws LBException
	{
		// Perform the query ...
		System.out.println("Pointed at by ...");

		// Perform the query ...
		NameAndValue nv = new NameAndValue();
		NameAndValueList nvList = new NameAndValueList();
		nv.setName(relation);
		nvList.addNameAndValue(nv);

		ResolvedConceptReferenceList matches =
			lbSvc.getNodeGraph(scheme, csvt, null)
				.restrictToAssociations(nvList, null)
				.resolveAsList(
					ConvenienceMethods.createConceptReference(code, scheme),
					false, true, 1, 1, new LocalNameList(), null, null, 1024);

		// Analyze the result ...
		if (matches.getResolvedConceptReferenceCount() > 0) {
			ResolvedConceptReference ref =
				(ResolvedConceptReference) matches.enumerateResolvedConceptReference().nextElement();

			// Print the associations
			AssociationList targetof = ref.getTargetOf();
			Association[] associations = targetof.getAssociation();
			for (int i = 0; i < associations.length; i++) {
				Association assoc = associations[i];
				AssociatedConcept[] acl = assoc.getAssociatedConcepts().getAssociatedConcept();
				for (int j = 0; j < acl.length; j++) {
					AssociatedConcept ac = acl[j];
					EntityDescription ed = ac.getEntityDescription();
					System.out.println(
						"\t\t" + ac.getConceptCode() + "/"
							+ (ed == null?
									"**No Description**":ed.getContent()));
				}
			}
		}

	}
}
