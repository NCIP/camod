package gov.nih.nci.camod.service.impl;

import java.util.List;

import gov.nih.nci.camod.domain.Curateable;
import gov.nih.nci.camod.service.CurateableAction;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Base class for all curatable actions. Currently only used for the logging.
 * 
 * @author georgeda
 */
public abstract class BaseCurateableAction implements CurateableAction {

    protected final Log log = LogFactory.getLog(BaseCurateableAction.class);

    public abstract CurateableAction create();

    public abstract void execute(List inArgs, Curateable inObject);
}

/*
 *  $Log: not supported by cvs2svn $
 */
