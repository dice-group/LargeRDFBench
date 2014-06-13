package org.aksw.simba.bigrdfbench.util;

import java.io.File;
import java.io.IOException;

import org.openrdf.query.MalformedQueryException;
import org.openrdf.query.QueryEvaluationException;
import org.openrdf.repository.Repository;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.sail.SailRepository;
import org.openrdf.rio.RDFFormat;
import org.openrdf.rio.RDFParseException;
import org.openrdf.sail.memory.MemoryStore;
/**
 * Load RDF results into in-memory index
 * @author Saleem
 *
 */
public class ResultsLoader {
	public static  RepositoryConnection con = null;
	/**
	 * Load RDF results into in-memory
	 * @param RDFresultsFile RDF results file
	 * @throws MalformedQueryException 
	 * @throws QueryEvaluationException 
	 */
	public static void loadResults(String RDFresultsFile) throws MalformedQueryException, QueryEvaluationException {
		File curfile = new File ("memorystore.data");
		curfile.delete();
		File fileDir = new File("memorystore");
		Repository myRepository = new SailRepository( new MemoryStore(fileDir) );
		try {
			myRepository.initialize();
		} catch (RepositoryException e) {
			e.printStackTrace();
		}
		    File file = new File(RDFresultsFile);
			
			try {
				con = myRepository.getConnection();
			} catch (RepositoryException e) {
				e.printStackTrace();
			}
			   try {
				con.add(file, "aksw.org.simba", RDFFormat.N3);
//				  TupleQuery tupleQuery = con.prepareTupleQuery(QueryLanguage.SPARQL, "SELECT * where { ?s ?p ?o}");
//		 		  TupleQueryResult res = tupleQuery.evaluate();
//		 		   while(res.hasNext())
//		 		   {
//		 			  System.out.println(res.next()); 
//		 		   }
			} catch (RDFParseException e) {
				e.printStackTrace();
			} catch (RepositoryException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			  
			
		}
	public static void main(String[] args) throws MalformedQueryException, QueryEvaluationException {
		//String results = "D:/BigRDFBench/completeness_correctness/results.n3";
	    //ResultsLoader.loadResults(results);

	}

}
