package org.aksw.simba.bigrdfbench.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
/**
 * Make an RDF version of the results of queries given in text files
 * @author Saleem
 *
 */
public class ResultsRDFizer{

   public static BufferedWriter bw;
   /**
    * Convert text files tabular results into RDF
    * @param queriesLocation Query files directory
    * @param resultsLocation Query results directory
    * @param outputLocation Output directory
    * @param resultSeparator The specific character(s) used to separate results of bindings names in results txt files
    * @throws IOException
    */
public static void generateRDFResults(String queriesLocation, String resultsLocation, String outputLocation, String resultSeparator) throws IOException
 {
	// bw = new BufferedWriter(new FileWriter(new File(outputLocation+"results.nt")));
	  File outputFile =new File(outputLocation+"results.n3");
	  bw = new BufferedWriter (new OutputStreamWriter(new FileOutputStream(outputFile),"UTF-8"));
	//  bw = new BufferedWriter(new FileWriter(new File(outputLocation+"results.txt")));
	  bw.write("@prefix bigrdfbench:<http://bigrdfbench.aksw.org/schema/> . ");
	  File queriesDir = new File(queriesLocation);
	  File[] queryFiles = queriesDir.listFiles();
	  if (queryFiles != null) 
	  {
	    for (File queryFile : queryFiles)
	     	writeQueryResults(queryFile,resultsLocation,outputLocation,resultSeparator);
	        System.out.println("RDFized results are stored at "+outputLocation+"results.n3");
	    }
	  else
	      System.out.println("Directory Error");
	  bw.close();
	 //outputFile.renameTo(new File(outputLocation+"results.n3"));
 }
/**
 * Write query results into RDF file
 * @param queryFile Query file
 * @param resultsLocation Location where queryFile results are stored
 * @param outputLocation Output location
 * @param resultSeparator The specific character(s) used to separate results of bindings names in results txt files
 * @throws IOException
 */
	private static void writeQueryResults(File queryFile, String resultsLocation, String outputLocation, String resultSeparator) throws IOException
	{
		
		String queryString = getQueryString(queryFile.getCanonicalPath().toString());
    	String queryName = queryFile.getName().replace(".csv", "");
    	bw.newLine();
    	bw.write("<http://aksw.org/bigrdfbench/resource/"+queryName+"> bigrdfbench:queryName <http://aksw.org/bigrdfbench/query/"+queryName+"> .");
    	bw.newLine();
    	bw.write("<http://aksw.org/bigrdfbench/query/"+queryName+"> bigrdfbench:queryString \""+queryString.replace("\"", "'") +"\" .");
    	//System.out.println(queryString);
    	File file = new File(resultsLocation+queryName+".csv");
    	BufferedReader br  = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"));
    	//System.out.println(br.readLine());
  	    String[] bindingNames = br.readLine().split(resultSeparator);
  	    //String[] bindingValues = new String[bindingNames.length];
  	    String line="";
  	    System.out.println(queryFile.getName()+": conversion in pgrogress...");
  	        bw.newLine();
	        bw.write("<http://aksw.org/bigrdfbench/query/"+queryName+"> bigrdfbench:bindingNames \"[");   
	        for(int i = 0 ; i < bindingNames.length;i++)
	        {
	        	if(i<bindingNames.length-1)
	        		bw.write(bindingNames[i].toString()+"<===>");
	        	else
	        		bw.write(bindingNames[i].toString()+"]\" .");
	        }
  	          bw.newLine();
	         bw.write("<http://aksw.org/bigrdfbench/query/"+queryName+"> bigrdfbench:bindingValues \"[");    
	         ArrayList<String> resultsLines = new ArrayList<String> ();
 	   	  while (( line= br.readLine()) != null)
 	   	  {
 	   		  resultsLines.add(line.toString().replace("\"", "\'"));
 	   	  }
 	   	  
     	   	 for(int resNo=0; resNo < resultsLines.size();resNo++)	 
     	   	 {
     	   		String [] results = resultsLines.get(resNo).toString().split(resultSeparator);
  	    	   for(int i =0 ; i<results.length;i++)
	  	          {
    	   			 if(i<results.length-1)
    	   				 bw.write(results[i]+"<===>");
    	   			 else
    	   			 {
    	   				  if(resNo<resultsLines.size()-1)
    	   				 	 bw.write(results[i]+"]\", \"[" );
    	   				  else
    	   					 bw.write(results[i]+"]\" .");  
    	   			 }
	  	       }	
  	      }
br.close();
}
	/**
	 * Get SPARQL query from text file
	 * @param queryFile Query File
	 * @return SPARQLqry SPARQL query
	 * @throws IOException
	 */
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
//		// TODO Auto-generated method stub
//		System.out.println(System.getProperty("file.encoding"));
//		File file = new File("D:/BigRDFBench/completeness_correctness/results.csv");
//    	BufferedReader br  = new BufferedReader(new InputStreamReader(new FileInputStream(file),"ISO-8859-1"));
//    	 String line="";
//    	//BufferedWriter bw1 = new BufferedWriter(new FileWriter(new File("D:/BigRDFBench/completeness_correctness/s6test.csv")));
//    	//PrintStream ps = new PrintStream(System.out, true, "ISO-8859-1");
//    	
//    	  while (( line= br.readLine()) != null)
// 	   	  {
//    		// bw1.write(line);
//    		// bw1.newLine();
//    		  System.out.println(line);
//    		 // ps.println(line);
// 	   	  }
//    	//  bw1.close();
		 
      generateRDFResults("D:/BigRDFBench/completeness_correctness/queries/","D:/BigRDFBench/completeness_correctness/results/", "D:/BigRDFBench/completeness_correctness/","<===>");
	}

}
