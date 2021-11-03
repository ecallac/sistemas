/**
 * 
 */
package com.security.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.IOUtils;

/**
 * @author efrain.calla
 *
 */
public class DatabaseUtil {
	public static void back(){
        System.out.println("Now it's time"+new Date());
        String user = "root";
        String password = "ecalla";
        String database1 = "security common"; // Database name to be backed up
        Date currentDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String sdfDate = sdf.format(currentDate);
        String filepath = "C:\\Bluestarenergy\\dumps\\security_" + sdfDate + ".sql"; // Backup path address
        //Execute command
        String stmt = "\"C:\\Program Files\\MySQL\\MySQL Server 5.5\\bin\\mysqldump.exe\" -h localhost -u "+user+" -p"+password+" --databases "+database1+" > "+filepath;   
        System.out.println(stmt);
        try {
        	String command ="cmd /c "+stmt;
            System.out.println("Execute Command - " +command);
	        System.out.println("Processing.. "+ "STARTED " +sdf.format(new Date()));
	        Date sDate = new Date();
	        
	        ProcessBuilder pb = new ProcessBuilder(
	                new String[]{
	                    "cmd", 
	                    "/c",
	                    stmt
	                }
	        );
	        pb.redirectErrorStream(true);
	        Process runtimeProcess = pb.start();
	        int processComplete = runtimeProcess.waitFor();
	        BufferedReader reader=new BufferedReader(new InputStreamReader(
	        		runtimeProcess.getInputStream())); 
		      String line; 
		      while((line = reader.readLine()) != null) { 
		        System.out.println(line);
		      } 
	        System.out.println("Processing.. "+ "END " +sdf.format(new Date())+ " code "+processComplete);
	        Date eDate = new Date();
	        long duration =  eDate.getTime() - sDate.getTime();
	        int seconds=(int) ((duration/1000)%60);
	        long minutes=((duration-seconds)/1000)/60;
	        System.out.println("TOTAL TIME : " + minutes +" minutes :: ");
	        System.out.println(seconds +" seconds :: ");
	        System.out.println(duration +" milliseconds");

	//      NOTE: processComplete=0 if correctly executed, will contain other values if not
	        if (processComplete == 0) {
	            System.out.println("Backup Complete");
	        } else {
	            System.out.println("Backup Failure");
	        }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	public void restore() {
        String user = "root";
        String password = "root";
        String database = "tuser"; // Database name to be backed up
        System.out.println("Now it's time" + new Date());
        Runtime runtime = Runtime.getRuntime();

        String cmd = "mysql  -h localhost" + " -u " + user + " -p" + password + " " + database;
        System.out.println(cmd);
        try {
            String filePath =  "D:\\user_2017-12-25_15-42-10.sql"; // sql file path
            String stmt = cmd + " < " + filePath;
            String[] command = {"cmd", "/c", stmt};
            Process process = runtime.exec(command);
            //Output if there is error message
            InputStream errorStream = process.getErrorStream();
            System.out.println(IOUtils.toString(errorStream, "utf-8"));
            //Waiting for operation
            int processComplete = process.waitFor();
            if (processComplete == 0) {
                System.out.println("Restore successful.");
            } else {
                throw new RuntimeException("Restore database failed.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DatabaseUtil.back();
	}

}
