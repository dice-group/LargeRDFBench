package org.aksw.simba.bigrdfbench.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ResultsRDFizer{

	public static BufferedWriter bw;
public static void generateRDFResults(String queriesLocation, String resultsLocation, String outputLocation) throws IOException
 {
	 bw = new BufferedWriter(new FileWriter(new File(outputLocation+"results.nt")));
	  bw.write("@prefix bigrdfbench:<http://bigrdfbench.aksw.org/schema/> . ");
	  File queriesDir = new File(queriesLocation);
	  File[] queryFiles = queriesDir.listFiles();
	  if (queryFiles != null) 
	  {
	    for (File queryFile : queryFiles)
	     	writeQueryResults(queryFile,resultsLocation,outputLocation);
	     System.out.println("RDFized results are stored at "+outputLocation+"results.nt");
	    }
	  else
	      System.out.println("Directory Error");
	  bw.close();
 }
	private static void writeQueryResults(File queryFile, String resultsLocation, String outputLocation) throws IOException
	{
		
		String queryString = getQueryString(queryFile.getCanonicalPath().toString());
    	String queryName = queryFile.getName().replace(".txt", "");
    	bw.newLine();
    	bw.write("<http://aksw.org/bigrdfbench/resource/"+queryName+"> bigrdfbench:queryName <http://aksw.org/bigrdfbench/query/"+queryName+"> .");
    	bw.newLine();
    	bw.write("<http://aksw.org/bigrdfbench/query/"+queryName+"> bigrdfbench:queryString \""+queryString +"\" .");
    	//System.out.println(queryString);
    	File file = new File(resultsLocation+queryName+".txt");

    	BufferedReader br  = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"));
  	    String[] bindingNames = br.readLine().split("\t");
  	    //String[] bindingValues = new String[bindingNames.length];
  	       String line="";
  	       System.out.println(queryFile.getName()+": consersion in pgrogress...");
  	        bw.newLine();
	        bw.write("<http://aksw.org/bigrdfbench/query/"+queryName+"> bigrdfbench:bindingNames \"[");   
	        for(int i = 0 ; i < bindingNames.length;i++)
	        {
	        	if(i<bindingNames.length-1)
	        		bw.write(bindingNames[i].toString()+";");
	        	else
	        		bw.write(bindingNames[i].toString()+"]\" .");
	        }
  	         bw.newLine();
	        bw.write("<http://aksw.org/bigrdfbench/query/"+queryName+"> bigrdfbench:bindingValues \"[");    
	        ArrayList<String> resultsLines = new ArrayList<String> ();
 	   	  while (( line= br.readLine()) != null)
 	   	  {
// 	   		byte ptext[] = line.getBytes();
// 	     	 line = new String(ptext, "UTF-8");     
 	   		  resultsLines.add(line.toString().replace("\"", "\'"));
 	   	  }
 	   	  
     	   	 for(int resNo=0; resNo < resultsLines.size();resNo++)	 
     	   	 {
     	   		String [] results = resultsLines.get(resNo).toString().split("\t");
  	    	   for(int i =0 ; i<results.length;i++)
	  	          {
    	   			 if(i<results.length-1)
    	   				 bw.write(results[i].toString().replace("\"", "'")+";");
    	   			 else
    	   			 {
    	   				  if(resNo<resultsLines.size()-1)
    	   				 	 bw.write(results[i].toString().replace("\"", "'")+"]\", \"[" );
    	   				  else
    	   					 bw.write(results[i].toString().replace("\"", "'")+"]\" .");  
    	   			 }
	  	       }	
  	      }
br.close();
}
	public static String getQueryString(String queryFile) throws IOException
	{
	    String SPARQLqry = "";
	 	FileInputStream fstream = new FileInputStream(queryFile);
	  // Get the object of DataInputStream
	    DataInputStream in = new DataInputStream(fstream);
	    @SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
	    String line="";
	   	  while (( line= br.readLine()) != null)
	   	  {
	   		 SPARQLqry = SPARQLqry+line; 
	   	  }
	return SPARQLqry;
    }
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
        generateRDFResults("D:/BigRDFBench/completeness_correctness/queries/","D:/BigRDFBench/completeness_correctness/results/", "D:/BigRDFBench/completeness_correctness/");
	}

}
