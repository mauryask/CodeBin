import static java.lang.System.*;
import static java.nio.file.StandardCopyOption.*;
import java.util.*;
import java.text.*;
import java.io.*;
import java.nio.file.*;
import java.time.*; 

public class DBDump 
{
	static void dumpDB() throws Exception
	{
		 //Check if today is monday
		 Calendar cal = Calendar.getInstance();
		 boolean isMonday = 
		 cal.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY;
		 
		 //Check if it is 11:00 AM
		 Date date = new Date();
		 SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
		 boolean isTime = 
		 dateFormat
		 .parse(dateFormat.format(date))
		 .equals(dateFormat.parse("22:27"));
		 
		 if(isMonday && isTime)
		 {
			 dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");  	         			  			
			 out.println("Date: "+LocalDate.now().getDayOfWeek().name()+", "+dateFormat.format(date));	
			 out.println("Creating database dump..");
			 createDumpFile();			 
			 out.println("Database dump created successfully..");
			 out.println("Copying dump to USB..");
			 copyDumpToUSB();
			 out.println("----------------------");
			 //Give the delay of 2 minutes
			 //to preven to create sump multiple times
			 Thread.sleep(120000);
		 } 
 	}
	
	//Creating database dump
	static synchronized void createDumpFile() throws Exception
	{
			ProcessBuilder builder =  
			new ProcessBuilder("cmd.exe", "/c",
			"mysqldump -u root sample > sample.sql");
			builder.redirectErrorStream(true);
			Process p = builder.start();
			//Since the above process will be asynchronous
			//hence wait for the process to be completed
			//then move to next statement
			p.waitFor();
			//destroy the above process
			p.destroy();
	}
	
	static void copyDumpToUSB() throws IOException
	{
		//Source path
		File src = new File("C:\\Users\\Shubham Maurya\\Desktop\\DB Dump\\sample.sql");
        //Destination path (USB)
		File dest = new File("H:\\dump\\sample.sql");
		//Copy dump file
        Path temp = Files.copy(src.toPath(), dest.toPath(), REPLACE_EXISTING);
       
	    if(temp != null)
			out.println("Dump copied successfully..");
		else
			out.println("Failed to copy the dump!!");
	}
	
	public static void main(String [] args)
	{
		try{
			out.println("Hi, There..");
			//Run an infinite loop to keep the program alive
		    while(true)
				dumpDB();			
		}
		catch(Exception ex){ex.printStackTrace();}		
	}
}
