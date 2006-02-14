package gov.nih.nci.system.proxy;

import javax.servlet.http.*;
import javax.servlet.*;
import javax.swing.text.html.HTMLDocument;
import javax.xml.transform.Source;
import javax.xml.transform.Templates;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.apache.log4j.Logger;
import java.util.*;
import java.net.*;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import gov.nih.nci.common.util.*;
import gov.nih.nci.system.webservice.*;
import gov.nih.nci.common.util.*;

import org.apache.log4j.Logger;
import org.dom4j.dom.DOMDocument;
import org.jdom.*;
import org.jdom.input.*;
import org.jdom.output.*;
import org.jdom.transform.JDOMResult;
import org.jdom.transform.JDOMSource;
import gov.nih.nci.system.applicationservice.*;

/**
 * <!-- LICENSE_TEXT_START -->
 */
/**
 * @author Shaziya Muhsin
 * @version 3.1
 */


/**
 * The HTTPQuery servlet interpretes a query request and makes appropriate calls to the caCORE Server.  
 * The results are sent back to the user as an XML or HTML document based on the type of request made.  
 * XQuery like syntax is used to generate the query.
 * Syntax:
 * http://server:port/servlet/queryType?query=ReturnDomainObject&DomainObject[@attribute=value][association[@attribute=value]]
 * Please refer to the cacore documentation for more information on generating a query request.
 */
public class HTTPQuery extends HttpServlet{

    private static Logger log= Logger.getLogger(PrintUtils.class.getName());
    private static Properties properties = new Properties();
    private String cacoreStyleSheet = null;
    private String evsStyleSheet = null;
    private int pageSize = 1000;
   
    public void init(ServletConfig config)
    throws ServletException
  {
     super.init(config);  
     ServletContext context = config.getServletContext();
     String beanFiles = context.getInitParameter ("cacoreBeans.Properties");     
     cacoreStyleSheet = context.getInitParameter ("cacoreFormat.xsl");
     evsStyleSheet = context.getInitParameter ("evsFormat.xsl");
     try{
         String pageCount = context.getInitParameter("rowCounter");
         if( pageCount != null){
             pageSize = Integer.parseInt(pageCount);
         }
     }
     catch(Exception ex){}
     
     try{
         loadProperties(beanFiles);         
     }catch (Exception ex){
         throw new ServletException(ex.getMessage());
     }
  }
    
  
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        doGet(request, response);
    }
    
    public void destroy(){
        super.destroy();
    }
    
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        
        ServletOutputStream out = response.getOutputStream();
        out = response.getOutputStream();
        
        int resultCounter = 1000;
        int startIndex =0 ;
        Object[] resultSet = null;
        HashMap resultMap = new HashMap();        
        int pageNumber = 1;
        String packageName = null;
        try{
            String query = null;
           
            if(URLDecoder.decode(request.getQueryString(),"ISO-8859-1")!= null){
                query = URLDecoder.decode(request.getQueryString(),"ISO-8859-1");
            }
            else{
                throw new Exception("Query not defined"+ getQuerySyntax());
            }
             
            HTTPUtils httpUtils = new HTTPUtils(); 
            if(properties != null){
                httpUtils.setProperties(properties);
            }   
            String queryType = httpUtils.getQueryType(request.getRequestURL().toString());
            
            if(!validateQuery(query)){
                throw new ServletException("Invalid Query Syntax: "+ query + getQuerySyntax() );
            }
            httpUtils.setQueryArguments(query);
           
            if(httpUtils.getPageNumber()!= null){
                pageNumber = Integer.parseInt(httpUtils.getPageNumber());
            } 
            httpUtils.setServletName(queryType);
            if(httpUtils.getPageSize() != null){
                pageSize = Integer.parseInt(httpUtils.getPageSize());           
            }
            else{
                httpUtils.setPageSize(pageSize);
            }
            if(httpUtils.getStartIndex()!=null || !httpUtils.getStartIndex().equals("0")){
                startIndex = Integer.parseInt(httpUtils.getStartIndex());
            }
           
            
            boolean match = false;
            
            HttpSession session = request.getSession();
            HTTPUtils prop = (HTTPUtils)session.getAttribute("properties");
            
            if(!session.isNew() && prop != null){
               match = httpUtils.getMatch(prop);                
            }
            
         /**  
            try{ 
                if(match){
                    List results = (List)prop.getResults();
                    httpUtils.setResults(results);        
                    resultSet = httpUtils.getCachedResultSet();
                    
                }
                else{
                    resultSet = httpUtils.getResultSet();
                }
                 
                                    
            }catch(Exception ex){
                log.error(ex.getMessage());
                throw new ServletException(ex.getMessage());
            }
            **/
            /**/
            if(match){
                
                        if(httpUtils.getResultCounter()!=null){
                            resultCounter = Integer.parseInt(httpUtils.getResultCounter());
                        }
                        
                        List results = (List)prop.getResults();
                        httpUtils.setResults(results);
                        
                        int size = startIndex + resultCounter;                        
                        if(size > results.size()){
                            size = results.size();
                        }    
                       
                        resultSet = new Object[resultCounter];
                        for(int s=0, i = startIndex; i<size; i++, s++){
                            resultSet[s]= new Object();
                            resultSet[s]= results.get(i);
                        }
                 
                
                      
                }
            else{            
                           
                    try{                    
                        resultSet = httpUtils.getResultSet(); 
                                            
                    }catch(Exception ex){
                        log.error(ex.getMessage());
                        throw new ServletException(ex.getMessage()+ getQuerySyntax());
                    }             
                    
                             
                  

                }
            /**/
               try{       
                session.setAttribute("properties",httpUtils);
                XMLOutputter xout = new XMLOutputter();
                org.jdom.Document domDoc = httpUtils.getXMLDocument(resultSet, pageNumber);
                    
                if(queryType.endsWith("XML")){
                    response.setContentType("text/xml");                
                    xout.output(domDoc,out);                  
                }
                else{
                    response.setContentType("text/html");            
                    Document htmlDoc = null;
                    if(httpUtils.getTargetPackageName()!= null){
                     
                        if(httpUtils.getTargetPackageName().indexOf("nih.nci.evs.")>0 && evsStyleSheet != null){
                            htmlDoc = getHTMLDocument(domDoc, evsStyleSheet);   
                            }
                        else if(cacoreStyleSheet != null){                          
                            htmlDoc = getHTMLDocument(domDoc, cacoreStyleSheet);
                        }
                         
                          if(htmlDoc != null){                          
                                xout = new XMLOutputter();
                                xout.output(htmlDoc,out);              
                            }
                            else{                           
                                httpUtils.printResults(resultSet, response);
                            }
                        }
                    }

            }catch(Exception ex){
                log.error(ex.getMessage());               
                throw new Exception(ex.getMessage());
                }

        }catch(Exception ex){
            log.error(ex.getMessage());
            //throw new ServletException("Error: "+ ex.getMessage());
            out.println("<b><font size=6>"+"caCORE HTTP Servlet Error:<hr> <br><br><font size=4 color=red>"+ ex.getMessage()+"</b></font>");
        }
        
            }


    /**
     * Loads Class and Package information based on the UML Model
     * @param file1 Specifies Property file
     * @param file2 Specifies property file
     * @throws Exception
     */
    private void loadProperties(String files) throws Exception{
        
        List fileList = new ArrayList();
        fileList = this.getFileList(files);
        String fileName = null;
        for(int i=0; i<fileList.size(); i++){
            try{
                fileName = (String)fileList.get(i);
                if(fileName != null || fileName.length()>0){
                    properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName));
                }                
            }catch(Exception ex){
                log.error("Cannot locate file - "+ fileName + "\t"+ ex.getMessage());                
            }
        }
        
        
        
       
    }
    public List getFileList(String files){
        List fileList = new ArrayList();
        
        if(files.indexOf(";")>0){
            StringTokenizer st = new StringTokenizer(files, ";");
            while(st.hasMoreTokens()){
                fileList.add(st.nextToken());
            }            
        }
        else if(files.indexOf(",")>0){
            StringTokenizer st = new StringTokenizer(files, ",");
            while(st.hasMoreTokens()){
                fileList.add(st.nextToken());
            }
        }
        else{
            fileList.add(files);
        }
        
        return fileList;
    }
   
   /**
    * Generates an HTML Document for a given XML document with the given stylesheet specification
    * @param doc Specifies the XML document
    * @param styleSheet Specifies the stylesheet
    * @return
    * @throws Exception
    */ 
    public Document getHTMLDocument(Document doc, String styleSheet)throws Exception{
        Document htmlDoc = null;
        
        try{
            InputStream styleIn = Thread.currentThread().getContextClassLoader().getResourceAsStream(styleSheet);
            if(styleIn != null){
                htmlDoc = XSLTTransformer(doc, styleIn);                
            }
                                    
        }catch(Exception ex){
            log.error(ex.getMessage());
            throw new ServletException(ex.getMessage());
            }        
        return htmlDoc;
    }
    
    /**
     * Generates an XML or HTML document based on a given stylesheet
     * @param xmlDoc Specifies the xml document
     * @param styleIn specifies the stylesheet
     * @return
     * @throws Exception 
     */
    
    public Document XSLTTransformer(Document xmlDoc, InputStream styleIn) throws Exception{                 
        JDOMSource source = new JDOMSource(xmlDoc);        
        JDOMResult result = new JDOMResult();        
        try{
            if(styleIn != null){
                TransformerFactory tFactory = TransformerFactory.newInstance();       
                Templates stylesheet = tFactory.newTemplates(new StreamSource(styleIn));
                Transformer processor = stylesheet.newTransformer();
                processor.transform(source, result);            
            }
            
        }catch(Exception ex){
            log.error(ex.getMessage());
            throw new Exception("XSLTTransformer Exception: "+ex.getMessage());
        }        
            return result.getDocument();
        }
    /**
     * This method returns true if the query syntax is valid
     * @param query Specifies the http query 
     * @return return 
     */
    private boolean validateQuery(String query){
           boolean valid = true;
           try{
               if(query.indexOf("query")<0){
                   valid = false;
               }else if(query.indexOf("&")<0){
                   if(query.indexOf("=")<0){
                       valid = false;
                   }         
               }else if(query.substring("query=".length()).length()<0){
                   valid = false;
               }  
               else if(query.substring(query.indexOf("&")+1).length()<0){
                   valid = false;
               }
               
           }catch(Exception ex){
               valid = false;
           }
            return valid;
    }
    
    private String getQuerySyntax(){
        return "<br><br><font color=purple size=4>Syntax: <br>query=RequestDomainObject&DomainObject[@attribute=value][association[@attribute=value]]</font>";
    }
  
}
