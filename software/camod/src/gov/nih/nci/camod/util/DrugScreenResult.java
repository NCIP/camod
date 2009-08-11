/**
 * 
 * $Id: DrugScreenResult.java,v 1.3 2006-05-08 13:34:45 georgeda Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.2  2006/04/17 19:10:50  pandyas
 * Added $Id: DrugScreenResult.java,v 1.3 2006-05-08 13:34:45 georgeda Exp $ and $log:$
 *
 * 
 */


package gov.nih.nci.camod.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DrugScreenResult
{
    public int strainCount = 0;
    public List<String> strains = new ArrayList<String>();
    public List<String> dosages = new ArrayList<String>();

    public static class Entry
    {
        public String strain;
        public String dosage;
        public float aveInh;
        public float diffInh;

        public String toString()
        {
            return dosage + ":" + aveInh + ":" + diffInh;
        }

        public float getAveInh()
        {
            return aveInh;
        }

        public float getDiffInh()
        {
            return diffInh;
        }

        public String getDosage()
        {
            return dosage;
        }

        public String getStrain()
        {
            return strain;
        }
    }
    public Map<String, List<Entry>> results = new HashMap<String, List<Entry>>();

    /**
     * adds values across appropriate strains
     */
    public void addEntry(String strain,
                         String dosage,
                         double aveInh,
                         double diffInh)
    {
        List<Entry> entries = results.get(strain);
        if (!strains.contains(strain))
        {
            strains.add(strain);
            strainCount++;
            entries = new ArrayList<Entry>();
            results.put(strain, entries);
        }
        if (!dosages.contains(dosage))
        {
            dosages.add(dosage);
        }
        Entry e = new Entry();
        e.strain = strain;
        e.dosage = dosage;
        e.aveInh = (float) aveInh;
        e.diffInh = (float) diffInh;
        entries.add(e);
    }

    public List getDosages()
    {
        return dosages;
    }

    public Map getResults()
    {
        return results;
    }

    public int getStrainCount()
    {
        return strainCount;
    }

    public List getStrains()
    {
        return strains;
    }

    public static void main(String[] args)
    {
        // simple test harness
        DrugScreenResult dsr = new DrugScreenResult();
        dsr.addEntry("cln2 rad14", "5", .1895, .009);
        dsr.addEntry("cln2 rad14", "15", .21895, .029);
        dsr.addEntry("mec2", "5", .185, .015);
        dsr.addEntry("mec2", "15", .1485, .015);
        dsr.addEntry("mgt1", "5", .185, .023);
        dsr.addEntry("mgt1", "15", .485, .034);
        dsr.addEntry("rad14", "5", .185, .055);
        dsr.addEntry("rad14", "15", .685, .063);
        dsr.addEntry("wt", "5", .34, .871);
        dsr.addEntry("wt", "15", .354, .726);
        System.out.println(dsr);
    }

    public String toString()
    {
        return results.toString();
    }
}
