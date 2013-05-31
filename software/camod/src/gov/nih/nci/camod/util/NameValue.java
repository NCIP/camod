/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/camod/LICENSE.txt for details.
 */

package gov.nih.nci.camod.util;

/**
 * A utility class for displaying name value pair in the jsp list
 *
 * @author Naveen Amiruddin
 * @version 1.0
 *
 */

public class NameValue
{
    private String name = null ;
    private String value = null ;

    public NameValue(String name, String value)
    {
       this.name =  name ;
       this.value = value ;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
