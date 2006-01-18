/*
 * Created on May 4, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.domain;

import gov.nih.nci.camod.util.Duplicatable;

import java.io.Serializable;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @author rajputs
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class Treatment extends BaseObject implements Serializable, Duplicatable
{

    private static final long serialVersionUID = 3258485453799404851L;

    private String regimen;
    private String dosage;
    private String administrativeRoute;
    private String adminRouteUnctrlVocab;
    private String ageAtTreatment;
    private SexDistribution sexDistribution;

    private String route;

    /**
     * @return Returns the sexDistribution.
     */
    public SexDistribution getSexDistribution()
    {
        return sexDistribution;
    }

    /**
     * @param sexDistribution
     *            The sexDistribution to set.
     */
    public void setSexDistribution(SexDistribution sexDistribution)
    {
        this.sexDistribution = sexDistribution;
    }

    /**
     * @return Returns the administrativeRoute.
     */
    public String getAdministrativeRoute()
    {
        return administrativeRoute;
    }

    /**
     * @param administrativeRoute
     *            The administrativeRoute to set.
     */
    public void setAdministrativeRoute(String administrativeRoute)
    {
        this.administrativeRoute = administrativeRoute;
    }

    /**
     * @return Returns the adminRouteUnctrlVocab.
     */
    public String getAdminRouteUnctrlVocab()
    {
        return adminRouteUnctrlVocab;
    }

    /**
     * @param adminRouteUnctrlVocab
     *            The adminRouteUnctrlVocab to set.
     */
    public void setAdminRouteUnctrlVocab(String adminRouteUnctrlVocab)
    {
        this.adminRouteUnctrlVocab = adminRouteUnctrlVocab;
    }

    /**
     * @return Returns the ageAtTreatment.
     */
    public String getAgeAtTreatment()
    {
        return ageAtTreatment;
    }

    /**
     * @param ageAtTreatment
     *            The ageAtTreatment to set.
     */
    public void setAgeAtTreatment(String ageAtTreatment)
    {
        this.ageAtTreatment = ageAtTreatment;
    }

    /**
     * @return Returns the dosage.
     */
    public String getDosage()
    {
        String tmpDosage = dosage;

        //Trim leading Zeros
        if (tmpDosage == null)
        {
            return null;
        }

        char[] chars = tmpDosage.toCharArray();
        int index = 0;
        for (; index < tmpDosage.length(); index++)
        {
            if (chars[index] != '0')
            {
                break;
            }
        }
        tmpDosage = (index == 0) ? tmpDosage : tmpDosage.substring(index);

        //if mg/kg is contained in string, replace it with mg/kg/injections
        // Compile regular expression
        Pattern pattern = Pattern.compile("mg/kg");

        // Replace all occurrences of pattern in input
        Matcher matcher = pattern.matcher(tmpDosage);
        tmpDosage = matcher.replaceAll("mg/kg/injections");
        return tmpDosage;
    }

    /**
     * @param dosage
     *            The dosage to set.
     */
    public void setDosage(String dosage)
    {
        this.dosage = dosage;
    }

    /**
     * @return Returns the regimen.
     */
    public String getRegimen()
    {
        return regimen;
    }

    /**
     * @param regimen
     *            The regimen to set.
     */
    public void setRegimen(String regimen)
    {
        this.regimen = regimen;
    }

    public String getRoute()
    {
        StringTokenizer parser = new StringTokenizer(this.regimen, ",");
        route = parser.nextToken();

        while (parser.hasMoreTokens() && !route.startsWith("Drug given-"))
        {
            route = parser.nextToken();
        }
        // Compile regular expression
        Pattern pattern = Pattern.compile("Drug given-");

        // Replace all occurrences of pattern in input
        Matcher matcher = pattern.matcher(route);
        route = matcher.replaceAll("");

        return route;
    }

    public void setRoute(String route)
    {
        this.route = route;
    }

    public String getSchedule()
    {

        StringTokenizer parser = new StringTokenizer(this.regimen, ",");
        route = parser.nextToken();
        while (parser.hasMoreTokens() && !route.startsWith("Schedule-"))
        {
            route = parser.nextToken();
        }
        // Compile regular expression
        Pattern pattern = Pattern.compile("Schedule-");

        // Replace all occurrences of pattern in input
        Matcher matcher = pattern.matcher(route);
        route = matcher.replaceAll("");

        return route;
    }

    public String getVehicle()
    {

        StringTokenizer parser = new StringTokenizer(this.regimen, ",");
        route = "";
        while (parser.hasMoreTokens() && !route.startsWith(" Vehicle-"))
        {
            route = parser.nextToken();

        }
        // Compile regular expression
        Pattern pattern = Pattern.compile(" Vehicle-");

        // Replace all occurrences of pattern in input
        Matcher matcher = pattern.matcher(route);
        route = matcher.replaceAll("");

        return route;
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString()
    {
        String result = super.toString() + " - ";
        result += this.getRegimen() + " - " + this.getDosage();
        return result;
    }

    public boolean equals(Object o)
    {
        if (!super.equals(o))
            return false;
        if (!(this.getClass().isInstance(o)))
            return false;
        return true;
    }
}
