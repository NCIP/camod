/**
 * 
 *   The caMOD Software License, Version 1.0
 *
 *   Copyright 2005-2006 SAIC. This software was developed in conjunction with the National Cancer
 *   Institute, and so to the extent government employees are co-authors, any rights in such works
 *   shall be subject to Title 17 of the United States Code, section 105.
 *
 *   Redistribution and use in source and binary forms, with or without modification, are permitted
 *   provided that the following conditions are met:
 *
 *   1. Redistributions of source code must retain the above copyright notice, this list of conditions
 *   and the disclaimer of Article 3, below.  Redistributions in binary form must reproduce the above
 *   copyright notice, this list of conditions and the following disclaimer in the documentation and/or
 *   other materials provided with the distribution.
 *
 *   2.  The end-user documentation included with the redistribution, if any, must include the
 *   following acknowledgment:
 *
 *   "This product includes software developed by the SAIC and the National Cancer
 *   Institute."
 *
 *   If no such end-user documentation is to be included, this acknowledgment shall appear in the
 *   software itself, wherever such third-party acknowledgments normally appear.
 *
 *   3. The names "The National Cancer Institute", "NCI" and "SAIC" must not be used to endorse or
 *   promote products derived from this software.
 *
 *   4. This license does not authorize the incorporation of this software into any third party proprietary
 *   programs.  This license does not authorize the recipient to use any trademarks owned by either
 *   NCI or SAIC.
 *
 *
 *   5. THIS SOFTWARE IS PROVIDED "AS IS," AND ANY EXPRESSED OR IMPLIED
 *   WARRANTIES, (INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
 *   MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE) ARE
 *   DISCLAIMED.  IN NO EVENT SHALL THE NATIONAL CANCER INSTITUTE, SAIC, OR
 *   THEIR AFFILIATES BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 *   EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 *   PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 *   PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY
 *   OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 *   NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 *   SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * $Id: OrganManagerImpl.java,v 1.12 2009-05-20 17:27:02 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.11  2007/09/12 19:36:03  pandyas
 * modified debug statements for build to stage tier
 *
 * Revision 1.10  2006/05/08 13:33:10  georgeda
 * Clean up warnings
 *
 * Revision 1.9  2006/04/21 13:41:50  georgeda
 * Cleanup
 *
 * Revision 1.8  2006/04/19 17:38:26  pandyas
 * Removed TODO text
 *
 * Revision 1.7  2006/04/17 19:11:06  pandyas
 * caMod 2.1 OM changes
 *
 * Revision 1.6  2006/01/18 14:24:24  georgeda
 * TT# 376 - Updated to use new Java 1.5 features
 *
 * Revision 1.5  2005/11/07 20:43:07  pandyas
 * modified getAll(), save(), rmove() and/or getByName (if applicable) to the current signature that throws exceptions and calls the super
 *
 * Revision 1.4  2005/11/03 18:55:44  pandyas
 * added Docs
 * 
 */
package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.domain.Organ;
import gov.nih.nci.camod.service.OrganManager;
import gov.nih.nci.camod.util.EvsTreeUtil;
import gov.nih.nci.common.persistence.Search;
import gov.nih.nci.common.persistence.exception.PersistenceException;
import gov.nih.nci.common.persistence.hibernate.eqbe.Evaluation;
import gov.nih.nci.common.persistence.hibernate.eqbe.Evaluator;

import java.util.List;

/**
 * @author rajputs
 */
public class OrganManagerImpl extends BaseManager implements OrganManager
{
    /**
     * Get all Organ objects
     * 
     * 
     * @return the matching Organ objects, or null if not found.
     * 
     * @exception Exception
     *                when anything goes wrong.
     */
    public List getAll() throws Exception
    {
        log.trace("In OrganManagerImpl.getAll");
        return super.getAll(Organ.class);
    }

    /**
     * Get a specific Organ by id
     * 
     * @param id
     *            the unique id for a Organ
     * 
     * @return the matching Organ object, or null if not found.
     * 
     * @exception Exception
     *                when anything goes wrong.
     */
    public Organ get(String id) throws Exception
    {
        log.trace("In OrganManagerImpl.get");
        return (Organ) super.get(id, Organ.class);
    }

    /**
     * Get a specific Organ by name
     * 
     * @param name
     *            the unique name for a Organ
     * 
     * @return the matching Organ, or null if not found.
     * 
     * @exception Exception
     *                when anything goes wrong.
     */
    public Organ getByName(String inName) throws Exception
    {
        Organ organ = null;

        if (inName != null && inName.length() > 0)
        {
            try
            {
                // The following two objects are needed for eQBE.
                Organ theOrgan = new Organ();
                theOrgan.setName(inName);

                // Apply evaluators to object properties
                Evaluation theEvaluation = new Evaluation();
                theEvaluation.addEvaluator("organ.name", Evaluator.EQUAL);

                List theList = Search.query(theOrgan, theEvaluation);

                if (theList != null && theList.size() > 0)
                {
                    organ = (Organ) theList.get(0);
                }
            }
            catch (PersistenceException pe)
            {
                log.error("PersistenceException in getByName", pe);
                throw pe;
            }
            catch (Exception e)
            {
                log.error("Exception in getByName", e);
                throw e;
            }
        }
        return organ;
    }

    /**
     * Get or create a specific organ
     * 
     * @param inConceptCode
     *            the concept code for an organ
     * @param inOrganName
     *            the name of the organ; used if we can't get the preferred desc. from EVS
     * 
     * @return the matching Organ, or create a new one if nothing matched
     * 
     * @exception Exception
     *                when anything goes wrong.
     */
    public Organ getOrCreate(String inConceptCode,
                             String inOrganName) throws Exception
    {
        log.debug("<OrganManagerImpl> Entering getOrCreate");

        Organ theQBEOrgan = new Organ();
        theQBEOrgan.setConceptCode(inConceptCode);

        Organ theOrgan = null;

        List theList = Search.query(theQBEOrgan);

        // Doesn't exist. Use the QBE organ since it has the same data
        if (theList != null && theList.size() > 0)
        {
            theOrgan = (Organ) theList.get(0);
        }
        else
        {
            theOrgan = theQBEOrgan;
            String thePreferredDiscription = EvsTreeUtil.getConceptDetails(null, inConceptCode);

            if (thePreferredDiscription != null && thePreferredDiscription.length() > 0)
            {
                theOrgan.setName(thePreferredDiscription);
            }
            else
            {
                theOrgan.setName(inOrganName);
            }
            theOrgan.setConceptCode(inConceptCode);
        }

        return theOrgan;
    }
}
