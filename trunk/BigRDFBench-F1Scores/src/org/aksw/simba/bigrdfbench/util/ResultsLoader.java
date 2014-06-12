package org.aksw.simba.bigrdfbench.util;

import java.io.File;
import java.io.IOException;

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
	 */
	public static void loadResults(String RDFresultsFile) {
		File curfile = new File ("memorystore.data");
		curfile.delete();
		File fileDir = new File("results");
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
			} catch (RDFParseException e) {
				e.printStackTrace();
			} catch (RepositoryException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			  
			
		}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
