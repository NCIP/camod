/*
 * $Id: Curateable.java,v 1.5 2006-04-19 17:37:37 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.4  2006/04/17 19:13:46  pandyas
 * caMod 2.1 OM changes and added log/id header
 *
 */
package gov.nih.nci.camod.domain;

import java.io.Serializable;

/**
 * @author rajputs
 */
public interface Curateable extends Serializable
{
    public String getState();

    public void setState(String state);
}
