/**
 * 
 * $Id: FtpUtil.java,v 1.2 2006-04-17 19:10:50 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * 
 */


package gov.nih.nci.camod.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class FtpUtil {

    /**
     * Upload a file to a FTP server. A FTP URL is generated with the following syntax:
     * <code>ftp://user:password@host:port/filePath;type=i</code>.
     * 
     * @param ftpServer FTP server address (incl. optional port ':portNumber').
     * @param user Optional user name to login.
     * @param pwd Optional password for <i>user</i>.
     * @param fileName Destination file name on FTP server (with optional preceeding relative path, e.g. "one/two/three.txt").
     * @param source Source file to upload.
     * @throws MalformedURLException, IOException on error.
     */
    public void upload(String ftpServer, String user, String pwd, String fileName, File source) throws MalformedURLException, IOException {
        if (ftpServer != null && fileName != null && source != null) {
            StringBuffer sb = new StringBuffer("ftp://");
            if (user != null && pwd != null) { //need authentication?
                sb.append(user);
                sb.append(':');
                sb.append(pwd);
                sb.append('@');
            }//else: anonymous access
            sb.append(ftpServer);
            sb.append('/');
            sb.append(fileName);
            sb.append(";type=i"); //a=ASCII mode, i=image (binary) mode, d= file directory listing
            
            System.out.println( "<FtpUtil.java> StringBuffer:" + sb );
            
            BufferedInputStream bis = null;
            BufferedOutputStream bos = null;
            try {
                URL url = new URL(sb.toString());
                URLConnection urlc = url.openConnection();
    
                bos = new BufferedOutputStream(urlc.getOutputStream());
                bis = new BufferedInputStream(new FileInputStream( source.getPath() ) );
                
                int i;
                while ((i = bis.read()) != -1) { //read next byte until end of stream
                    bos.write(i);
                }//next byte
            } finally {
                if (bis != null) try { bis.close(); } catch (IOException ioe) { /* ignore*/ }
                if (bos != null) try { bos.close(); } catch (IOException ioe) { /* ignore*/ }
            }
        } //else: input unavailable
    } //upload()
}
