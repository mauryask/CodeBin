import static java.lang.System.*;
import java.util.*;
import java.text.*;
import java.io.*;
import java.nio.file.*;
import static java.nio.file.StandardCopyOption.*;

public class DBDump 
{
	static void dumpDB() throws Exception
	{
		 //Check if today is monday
		 Calendar cal = Calendar.getInstance();
		 boolean isMonday = cal.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY;
		 
		 //Check if it is 11:00 AM
		 Date date = new Date();
		 SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
		 boolean isTime = 
		 dateFormat
		 .parse(dateFormat.format(date))
		 .equals(dateFormat.parse("23:36"));
		 
		 if(isMonday && isTime)
		 {
			 out.println("Creating database dump...");
			 createDumpFile();			 
			 out.println("Database dump created successfully...");
			 out.println("Copying dump to USB...");
			 copyDumpToUSB();
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
		File src = new File("C:\\Users\\Shubham Maurya\\Desktop\\DB Dump\\sample.sql");
        File dest = new File("H:\\dump\\sample.sql");
        Path temp = Files.copy(src.toPath(), dest.toPath(), REPLACE_EXISTING);
       
	    if(temp != null)
			out.println("Dump copied successfully...");
		else
			out.println("Failed to copy the dump!!");
	}
	
	public static void main(String [] args)
	{
		try{
		    dumpDB();			
		}
		catch(Exception ex){ex.printStackTrace();}		
	}
}