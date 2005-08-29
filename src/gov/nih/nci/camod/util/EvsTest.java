/**
 * 
 */
package gov.nih.nci.camod.util;

/**
 * @author guruswamis
 * Test harness to get the display name of a concept from EVS 
 */

import gov.nih.nci.ncicb.evs.EvsUtil;

public class EvsTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String[] codes = new String[] { "C18875430", "C18875436", "C18874941", "C18875722", "C18875726" };
		try {
			EvsUtil e = new EvsUtil("evs.properties");
			long start = System.currentTimeMillis();
			final int len = codes.length;
			for (int i=1; i<100; i++) {
				for(int j=0; j<len; j++) {
					com.apelon.dts.client.Concept c = e.getConceptByCode(codes[j]);
					System.out.println(c);
				}
			}
			long end = System.currentTimeMillis();
			System.out.println("500 iterations took:" + (end-start));
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
}
