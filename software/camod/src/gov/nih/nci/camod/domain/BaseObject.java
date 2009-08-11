package gov.nih.nci.camod.domain;

import java.io.Serializable;

import gov.nih.nci.camod.util.HashCodeUtil;
import gov.nih.nci.camod.util.GUIDGenerator;

/**
 * Base class for Persisted Domain objects.
 *
 * @author piparom
 */

public abstract class BaseObject implements Comparable, Serializable
{

    private Long id = null;

    // private internal non-persistent properties
    // used for equality evaluations of newly created transient
    // domain objects (null id objects)

    // assign new global unique identifier
    private final String internalGUID = GUIDGenerator.getInstance().genNewGuid();

    // initialize persisted state to false
    private boolean persisted = false;


    private void setId(Long id)
    {
        this.id = id;

        // pkid assigned by hibernate - set perstisted state
        this.persisted = true;
    }

    public Long getId()
    {
        return id;
    }

    public boolean equals(Object o)
    {
        if (this == o)
            return true;
        if ((o == null) || (!(o instanceof BaseObject)))
            return false;

        final BaseObject obj = (BaseObject) o;

        if (this.persisted)
        {
            if (HashCodeUtil.notEqual(this.getId(), obj.getId()))
                return false;
        }
        else
        {
            if (!(this.internalGUID.equals(obj.internalGUID)))
                return false;
        }

        return true;
    }

    public int hashCode()
    {
        int result = HashCodeUtil.SEED;
        if (this.persisted)
        {
            result = HashCodeUtil.hash(result, this.getId());
        }
        else
        {
            result = HashCodeUtil.hash(result, this.internalGUID);
        }
        return result;
    }

    public String toString()
    {
        String result = "ID: ";
        if (id != null)
        {
            result += id.toString();
        }
        else
        {
            result += "Null";
            if (!this.persisted)
            {
                result += " - GUID (temporary): " + this.internalGUID;
            }
        }
        return result;
    }

    public int compareTo(Object o)
    {
        if (o instanceof BaseObject)
        {
            BaseObject tgt = (BaseObject) o;
            if (this.getId() == null && tgt.getId() == null)
                return -1;
            if (this.getId() != null && tgt.getId() == null)
                return 1;
            if (this.getId() == null && tgt.getId() != null)
                return -1;

            return this.getId().compareTo(tgt.getId());
        }
        return 0;
    }

}
