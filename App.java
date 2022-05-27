package com.samsung.scrapper;

import static java.lang.System.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.logging.Logger;
import com.gargoylesoftware.htmlunit.*;
import com.gargoylesoftware.htmlunit.html.*;

public class App 
{
    public static void main( String[] args ) throws FailingHttpStatusCodeException, MalformedURLException, IOException
    {
        getData();
    }
    
    static void getData() throws FailingHttpStatusCodeException, MalformedURLException, IOException
    {
    	 // Disable unnecessary logs
        Logger.getLogger("com.gargoylesoftware.htmlunit")
        .setLevel(java.util.logging.Level.OFF);
        Logger.getLogger("org.apache.http").setLevel(
        java.util.logging.Level.OFF);
  	  
  	   final WebClient webClient = new WebClient((BrowserVersion.CHROME));	
  	    webClient.setAjaxController(
               new NicelyResynchronizingAjaxController());
  	    webClient.getOptions().setThrowExceptionOnScriptError(false);
  	    webClient.getOptions().setUseInsecureSSL(false);
  	    webClient.getOptions().setCssEnabled(true);
  	    webClient.getOptions().setJavaScriptEnabled(true);   
  	    webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);	
  	  
  	   final HtmlPage page = webClient.getPage("https://en.wikipedia.org/wiki/List_of_countries_and_dependencies_by_population");
  	   final HtmlTable table  = (HtmlTable) page.getFirstByXPath("//*[@id='mw-content-text']/div[1]/table");
       
  	   for (final HtmlTableRow row : table.getRows()) {  	    
  	    for (final HtmlTableCell cell : row.getCells()) {  	    	
  	    	List<HtmlElement> list = cell.getElementsByTagName("img");
  	    	int size = list.size();
  	       out.print(((size>0 ? list.get(0).getAttribute("src") : 
  	    			   cell.asNormalizedText().equals("")? "NA": cell.asNormalizedText()))+",");
  	    }
  	    out.println();
  	}
    }
}
