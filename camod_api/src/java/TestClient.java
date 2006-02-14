/*
 * TestClient.java
 *
 * Created on February 9, 2006, 3:36 PM
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */


import gov.nih.nci.system.applicationservice.*;
import gov.nih.nci.camod.domain.*;
import java.util.List;

/**
 *
 * @author piparom
 */
 public class TestClient { 
  
     static ApplicationService appService = ApplicationService.getRemoteInstance("http://localhost:8084/camod-api/http/remoteService"); 
  
     public static void main(String[] args) { 
  
        System.out.println("*** TestClient..."); 
       try{ 
  
                camod(); 
       } 
           catch(Exception ex){ 
                ex.printStackTrace(); 
                System.out.println("Test client throws Exception = "+ ex); 
           } 
  
       } 
  
     static public void camod() 
     { 
          try{ 
                    SexDistribution st = new SexDistribution(); 
                    st.setId(new Long(5)); 
                    List results = (List)appService.search(SexDistribution.class, st); 
  
                    System.out.println("total return size = " + results.size()); 
                } 
                catch(Exception e) 
                { 
                 e.printStackTrace(); 
      } 
  
     } 
 } 
 
