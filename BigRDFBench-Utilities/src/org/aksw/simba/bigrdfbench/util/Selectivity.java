package org.aksw.simba.bigrdfbench.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import org.aksw.sparql.query.algebra.helpers.BGPGroupGenerator;
import org.openrdf.query.MalformedQueryException;
import org.openrdf.query.QueryEvaluationException;
import org.openrdf.query.QueryLanguage;
import org.openrdf.query.TupleQuery;
import org.openrdf.query.TupleQueryResult;
import org.openrdf.query.algebra.StatementPattern;
import org.openrdf.repository.Repository;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.http.HTTPRepository;

/**
 * Dataset-restricted Filtered/Simple Triple pattern selectivities. 
 * @author Saleem
 *
 */
public class Selectivity {
	
	 static HashMap<String, String> stmtFilters = new  HashMap<String, String>(); // A hashmap contains triple patter as key corresponding filter clause as value for those triple patterns whose selectivity is depended upon Filter clause. load this map from file /queryfilters/stp.txt (strict format is must, should be done via coding)
	 static HashMap<String, String> epToResults = new  HashMap<String, String>(); // endpoint url to total number of results.
	 static List<String> endpoints =  Arrays.asList(
			    // "http://localhost:8890/sparql",
				 "http://localhost:8891/sparql",
//				 "http://localhost:8892/sparql",
//				 "http://localhost:8893/sparql",
//				 "http://localhost:8894/sparql",
//				 "http://localhost:8895/sparql",
//				 "http://localhost:8896/sparql",
//				 "http://localhost:8897/sparql",
//				 "http://localhost:8898/sparql",
				 "http://localhost:8899/sparql",
				 "http://localhost:8887/sparql",
				 "http://localhost:8888/sparql",
				 "http://localhost:8889/sparql"
			   
			);
	private static RepositoryConnection con = null;
	 
	 public static void main(String[] args) throws RepositoryException, MalformedQueryException, QueryEvaluationException, IOException {
		
		loadStatementFilters("../BigRDFBench-Utilities/queryfilters/stp.txt");
		loadEPtoRSfromFile("../BigRDFBench-Utilities/datasetsizes/sizes.txt");
		String inputDir= "../BigRDFBench-Utilities/queries/";
		File folder = new File(inputDir);
		File[] listOfFiles = folder.listFiles();
		long count = 1; 
		for (File qryFile : listOfFiles)
		{	
		BufferedReader br = new BufferedReader(new FileReader(inputDir+qryFile.getName()));
		String line;
		String queryStr="";
		while ((line = br.readLine()) != null) {
		   queryStr= queryStr+" "+line;
		}
		br.close();
		
			 System.out.println("--------------------------------------------------------------\n"+count+ ": "+qryFile.getName()+" Query: " + queryStr);
		     printQueryStats(queryStr,qryFile.getName());
	   	     count++;
		}

}
	 /**
	  * Load endpoint to results map from a file containing endpoint urls and corresponding results. This is good for big datasets where on-the-fly total results calculation need a lot of time. 
	  * @param file File containing resultset sizes of endpoints
	 * @throws IOException 
	  */
	private static void loadEPtoRSfromFile(String file) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line;
		while ((line = br.readLine()) != null) {
			  String prts [] = line.split("\t");
			  //System.out.println(prts[0]);
			 // System.out.println(prts[1]);
			  epToResults.put(prts[0],prts[1]);
			}
			br.close();	
	}
	/**
	 * Load those triple patterns whose selectivity is depended upon Filter clause into a set
	 * @param file File containing filter information. note: strict formating required
	 * @throws IOException
	 */
	private static void loadStatementFilters(String file) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line;
		while ((line = br.readLine()) != null) {
			  String prts [] = line.split("\t");
			  //System.out.println(prts[0]);
			 // System.out.println(prts[1]);
			  stmtFilters.put(prts[0],prts[1]);
			}
			br.close();
	
	}
	/**
	 * Print Triple pattern selectivities
	 * @param query SPARQL query
	 * @param queryName Query Name
	 * @throws MalformedQueryException
	 * @throws QueryEvaluationException 
	 * @throws RepositoryException 
	 */
	public static void printQueryStats(String query, String queryName) throws MalformedQueryException, RepositoryException, QueryEvaluationException {
		 HashMap<Integer, List<StatementPattern>> bgpGrps =  BGPGroupGenerator.generateBgpGroups(query);
		 List<Double> meanTPSelectivities = new ArrayList<Double>()  ;
		 System.out.println("Basic Graph Patterns (BGPs): " +bgpGrps.size());
		 long totalTriplePatterns = 0;
		 double meanQrySel =0 ; //mean of the avg triple pattern selectivity of all the triple patterns in a query
		    for(int DNFkey:bgpGrps.keySet())  //DNFgrp => bgp
			  {
					 List<StatementPattern>	 stmts =  bgpGrps.get(DNFkey);
					 totalTriplePatterns = totalTriplePatterns + stmts.size();
				for (StatementPattern stmt : stmts) 
				{		
					   String sbjVertexLabel, objVertexLabel, predVertexLabel;
						sbjVertexLabel = getSubjectVertexLabel(stmt);
						predVertexLabel = getPredicateVertexLabel(stmt);
						objVertexLabel = getObjectVertexLabel(stmt);
						String tp = queryName+":"+sbjVertexLabel+"_"+predVertexLabel+"_"+objVertexLabel;
					double meanTripleSel = 	getTriplePatternDatasetMeanSelectivity(stmt,tp);
					meanQrySel  = meanQrySel+ meanTripleSel;
					meanTPSelectivities.add(meanTripleSel);
					System.out.println("Average (across all datasets) Triple pattern selectivity: "+ meanTripleSel);
				}
			  }
		    meanQrySel = meanQrySel/totalTriplePatterns;
		    System.out.println("\nMean query selectivity (average of of the mean triple pattern selectivities): " + meanQrySel);
		   // System.out.println(meanTPSelectivities);
		    double stdv = getStandardDeviation(meanTPSelectivities,meanQrySel);
		    System.out.println("Query Selectivities standard deviation: " + stdv );
		    System.out.println("Triple Patterns: " +totalTriplePatterns);
	}
	/**
	 * Calculate standard deviation of given set of selectivities
	 * @param meanTPSelectivities Set of mean triple patterns selectivities
	 * @param mean Average of selectivities
	 * @return Standard Deviation
	 */
	private static double getStandardDeviation(List<Double> meanTPSelectivities, double mean) {
		   
		      // sd is sqrt of sum of (values-mean) squared divided by n - 1
		      // Calculate the mean
		    //  double mean = 0;
		      final int n = meanTPSelectivities.size();
		      if ( n < 2 )
		         {
		         return Double.NaN;
		         }
	      // calculate the sum of squares
		      double sum = 0;
		      for ( int i=0; i<n; i++ )
		         {
		         final double v = meanTPSelectivities.get(i) - mean;
		         sum += v * v;
		         }
		      // Change to ( n - 1 ) to n if you have complete data instead of a sample.
		      return Math.sqrt( sum / ( n - 1 ) );
		      }
	/**
	 * Get filtered Triple pattern mean selectivity
	 * @param stmt Statement pattern
	 * @param tp  triples representation of statement pattern
	 * @return Selectivity of the filtered triple pattern
	 * @throws RepositoryException
	 * @throws MalformedQueryException
	 * @throws QueryEvaluationException
	 */
	public static double getTriplePatternDatasetMeanSelectivity(StatementPattern stmt,
			String tp) throws RepositoryException, MalformedQueryException, QueryEvaluationException {
		double selectivity = 0 ;
		double meanSelectivity = 0;
		double selSum = 0;
		long resultSize = 0;
		long capableSources = 0;
		String triplePattern = getTriplePattern(stmt);
		if (stmt.getSubjectVar().getValue()==null && stmt.getPredicateVar().getValue()==null && stmt.getObjectVar().getValue()==null )
		{
			  selectivity = 1; 
			  System.out.println("\n Triple pattern: " + triplePattern +  ", \n  for all endpoints   Selectivity: " + selectivity);
			  meanSelectivity = 1;
		}
		else
		{
		String queryString = getQueryString(stmt,tp);
		System.out.println("\nTriple pattern: " + triplePattern );
		//System.out.println(queryString);
		for(String endpoint:endpoints)
		{
			 initializeRepoConnection(endpoint);
		     TupleQuery tupleQuery = con.prepareTupleQuery(QueryLanguage.SPARQL, queryString);
		     TupleQueryResult res = tupleQuery.evaluate();
		   while(res.hasNext())
		  	   resultSize = Long.parseLong(res.next().getValue("size").stringValue().toString());
		  long datasetSize =  Long.parseLong(epToResults.get(endpoint));
		  if(resultSize>0)
		  {
		    selectivity = (double) resultSize/ datasetSize;
		    selSum = selSum + selectivity;
		    capableSources++;
		   System.out.println("    endpoint:  " + endpoint+ "  (matched results: "+ resultSize +  ", total results: "+ datasetSize + ", selectivity: " + selectivity+")" );
		  }
		}
		meanSelectivity = selSum/capableSources;
		}
		return meanSelectivity;
	}
	/**
	 * Get triple pattern from statement pattern
	 * @param stmt Statement pattern
	 * @return triplePattern Triple pattern
	 */
	private static String getTriplePattern(StatementPattern stmt) {
		String subject = getSubject(stmt);
		String object = getObject(stmt);
		String predicate = getPredicate(stmt);
		String triplePattern = subject + predicate + object ;
		return triplePattern;
	}
	/**
	 * Get query to the count of results against triple pattern
	 * @param stmt Triple pattern
	 * @param tp Triple pattern as key for stmtFilters hash map. For checking whether stmt should contain Filter clause as well
	 * @return query SPARQL query
	 */
	private static String getQueryString(StatementPattern stmt, String tp) {
		String query = "";
	   String triplePattern = getTriplePattern(stmt);
		if(stmtFilters.containsKey(tp))
			query = "SELECT COUNT(*) AS ?size WHERE { "+triplePattern+ " \n"+ stmtFilters.get(tp)   +"} ";
		else
			query  = "SELECT COUNT(*) AS ?size WHERE { "+triplePattern +"} ";
		
		//System.out.println(query);
		return query;
		
	}

/**
 * Get Predicate from triple pattern
 * @param stmt Triple pattern
 * @return tuple Subject tuple
 */
private static String getPredicate(StatementPattern stmt) {
	String tuple;
	if (stmt.getPredicateVar().getValue()!=null)
		tuple = " <"+stmt.getPredicateVar().getValue().stringValue()+"> ";
		else
		tuple =" ?"+stmt.getPredicateVar().getName(); 
	return tuple;
}
	/**
	 * Get object from triple pattern
	 * @param stmt Triple pattern
	 * @return tuple Subject tuple
	 */
	private static String getObject(StatementPattern stmt) {
		String tuple;
		if (stmt.getObjectVar().getValue()!=null && (stmt.getObjectVar().getValue().toString().startsWith("http://") || stmt.getObjectVar().getValue().toString().startsWith("ftp://")))
			tuple = " <"+stmt.getObjectVar().getValue().stringValue()+"> ";
		else if (stmt.getObjectVar().getValue()!=null)
			tuple = " '"+stmt.getObjectVar().getValue().stringValue()+"' ";
			else
			tuple =" ?"+stmt.getObjectVar().getName(); 
		return tuple;
	}
	/**
	 * Get subject from triple pattern
	 * @param stmt Triple pattern
	 * @return tuple Subject tuple
	 */
	private static String getSubject(StatementPattern stmt) {
		String tuple;
		if (stmt.getSubjectVar().getValue()!=null )
			tuple = "<"+stmt.getSubjectVar().getValue().stringValue() + "> ";
		else if (stmt.getSubjectVar().getValue()!=null )
			tuple = "'"+stmt.getSubjectVar().getValue().stringValue() + "' ";
			else
			tuple ="?"+stmt.getSubjectVar().getName(); 
		return tuple;
	}
	
	/**
	 * Get label for the object vertex of a triple pattern
	 * @param stmt triple pattern 
	 * @return label Vertex label
	 */
	private static String getObjectVertexLabel(StatementPattern stmt) {
		String label ; 
		if (stmt.getObjectVar().getValue()!=null)
		label = stmt.getObjectVar().getValue().stringValue();
		else
		label =stmt.getObjectVar().getName(); 
			return label;
		
	}
	/**
	 * Get label for the predicate vertex of a triple pattern
	 * @param stmt triple pattern 
	 * @return label Vertex label
	 */
	private static String getPredicateVertexLabel(StatementPattern stmt) {
		String label ; 
		if (stmt.getPredicateVar().getValue()!=null)
		label = stmt.getPredicateVar().getValue().stringValue();
		else
		label =stmt.getPredicateVar().getName(); 
			return label;
		
	}
	/**
	 * Get label for the subject vertex of a triple pattern
	 * @param stmt triple pattern 
	 * @return label Vertex label
	 */
	private static String getSubjectVertexLabel(StatementPattern stmt) {
		String label ; 
		if (stmt.getSubjectVar().getValue()!=null)
		label = stmt.getSubjectVar().getValue().stringValue();
		else
		label =stmt.getSubjectVar().getName(); 
			return label;
		
	}
	


/**
 * Initialize repository for a SPARQL endpoint
 * @param endpointUrl Endpoint Url
 * @throws RepositoryException
 */
	private static void initializeRepoConnection(String endpointUrl) throws RepositoryException {
		Repository repo = new HTTPRepository(endpointUrl, "my-repoid");
		repo.initialize();
		 con = repo.getConnection();
		
	}
}