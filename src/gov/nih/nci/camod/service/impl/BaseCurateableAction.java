/**
 * 
 * $Id: BaseCurateableAction.java,v 1.4 2006-04-17 19:11:06 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * 
 */

package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.domain.Curateable;
import gov.nih.nci.camod.service.CurateableAction;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Base class for all curatable actions. Currently only used for the logging.
 * 
 * @author georgeda
 */
public abstract class BaseCurateableAction implements CurateableAction
{
    protected final Log log = LogFactory.getLog(BaseCurateableAction.class);

    public abstract CurateableAction create();

    public abstract void execute(Map inArgs,
                                 Curateable inObject);
}

/*
 * $Log: not supported by cvs2svn $
 * Revision 1.3  2006/01/18 14:24:24  georgeda
 * TT# 376 - Updated to use new Java 1.5 features
 *
 * Revision 1.2  2005/09/13 20:44:54  georgeda
 * More changes
 * Revision 1.1 2005/09/12 18:22:04 georgeda
 * Curation changes and addition of e-mail
 */
