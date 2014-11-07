package org.aksw.simba.bigrdfbench.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.aksw.simba.hibiscus.hypergraph.HyperGraph.HyperEdge;
import org.aksw.simba.hibiscus.hypergraph.HyperGraph.Vertex;
import org.aksw.sparql.query.algebra.helpers.BGPGroupGenerator;
import org.openrdf.query.MalformedQueryException;
import org.openrdf.query.QueryEvaluationException;
import org.openrdf.query.algebra.StatementPattern;
import org.openrdf.repository.RepositoryException;

/**
 * Generate various query statistics. 
 * @author Saleem
 *
 */
public class QueryStatistics {
	
	 @SuppressWarnings("unused")
	private static final int boundObjLiteralCount = 0;
	@SuppressWarnings("unused")
	private static long boundSbjCount, boundPredCount, boundObjURICount, BoundObjLiteralCount,grandTotalTriplePatterns = 0 ; 
	 public static void main(String[] args) throws RepositoryException, MalformedQueryException, QueryEvaluationException, IOException {
		
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
//		System.out.println("\n\n--------------% triple pattern bound tuples distribution over all queries--------------------------------");
//		System.out.println("Bound subject:" + (100*(double)boundSbjCount/grandTotalTriplePatterns));
//		System.out.println("Bound Predicate:" + (100*(double)boundPredCount/grandTotalTriplePatterns));
//		System.out.println("Bound Object URI:" + (100*(double)boundObjLiteralCount/grandTotalTriplePatterns));
//		System.out.println("Bound Object Literal:" + (100*(double)boundObjLiteralCount/grandTotalTriplePatterns));

}
	
	/**
	 * Print query statistics
	 * @param query SPARQL query
	 * @param queryName Query Name
	 * @throws MalformedQueryException
	 * @throws QueryEvaluationException 
	 * @throws RepositoryException 
	 */
	public static void printQueryStats(String query, String queryName) throws MalformedQueryException, RepositoryException, QueryEvaluationException {
		 HashMap<Integer, List<StatementPattern>> bgpGrps =  BGPGroupGenerator.generateBgpGroups(query);
		 System.out.println("Basic Graph Patterns (BGPs): " +bgpGrps.size());
		 long totalTriplePatterns = 0;
		 HashSet<Vertex> joinVertices = new HashSet<Vertex>();
		 HashSet<Vertex> vertices = new HashSet<Vertex>();
		    for(int DNFkey:bgpGrps.keySet())  //DNFgrp => bgp
			  {
					 HashSet<Vertex> V = new HashSet<Vertex>();   //--Set of all vertices used in our hypergraph. each subject, predicate and object of a triple pattern is one node until it is repeated
					 List<StatementPattern>	 stmts =  bgpGrps.get(DNFkey);
					 totalTriplePatterns = totalTriplePatterns + stmts.size();
				for (StatementPattern stmt : stmts) 
				{		
					   String sbjVertexLabel, objVertexLabel, predVertexLabel;
						Vertex sbjVertex, predVertex,objVertex ;
						//--------add vertices---
						sbjVertexLabel = getSubjectVertexLabel(stmt);
						predVertexLabel = getPredicateVertexLabel(stmt);
						objVertexLabel = getObjectVertexLabel(stmt);
						sbjVertex = new Vertex(sbjVertexLabel);
						predVertex = new Vertex(predVertexLabel);
						objVertex = new Vertex(objVertexLabel);
						if(!vertexExist(sbjVertex,V))
						V.add(sbjVertex);
						if(!vertexExist(predVertex,V))
						V.add(predVertex);
						if(!vertexExist(objVertex,V))
						V.add(objVertex);
						//--------add hyperedges
						 HyperEdge hEdge = new HyperEdge(sbjVertex,predVertex,objVertex);
						 if(!(getVertex(sbjVertexLabel,V)==null))
							 sbjVertex = getVertex(sbjVertexLabel,V);
						 if(!(getVertex(predVertexLabel,V)==null))
							 predVertex = getVertex(predVertexLabel,V);
						 if(!(getVertex(objVertexLabel,V)==null))
							 objVertex = getVertex(objVertexLabel,V);
						 sbjVertex.outEdges.add(hEdge); predVertex.inEdges.add(hEdge); objVertex.inEdges.add(hEdge);
				}
				vertices.addAll(V) ;
				joinVertices.addAll(getJoinVertexCount(V));
				// V.clear();
			  }
		    grandTotalTriplePatterns = grandTotalTriplePatterns + totalTriplePatterns;
			  System.out.println("Triple Patterns: " +totalTriplePatterns);
			  System.out.println("Total Vertices:"+vertices.size() + " ==> "+vertices);
			  System.out.println("Join Vertices: " +joinVertices.size()+" ==> "+joinVertices);
			  System.out.println("Join Vertices to Total Vertices ratio: " +(double)joinVertices.size()/(double)vertices.size());
			  double meanJoinVertexDegree = 0;
			  String joinVertexType = "" ;   // {Star, path, hybrid, sink}
			  for(Vertex jv:joinVertices)
			  {
				  long joinVertexDegree = (jv.inEdges.size() + jv.outEdges.size());
				  if(jv.inEdges.size()==0)
					  joinVertexType = "Star" ; 
				  else if (jv.outEdges.size()==0)
					  joinVertexType = "Sink" ; 
				  else if (jv.inEdges.size()==1 &&jv.outEdges.size()==1 )
					  joinVertexType = "path" ; 
				  else
					  joinVertexType = "Hybrid" ; 
				  System.out.println("     " + jv+ " Join Vertex Degree: " + joinVertexDegree + ", Join Vertex Type: " + joinVertexType);
				  meanJoinVertexDegree = meanJoinVertexDegree + joinVertexDegree;
			  }
			  
			  System.out.println("Mean Join Vertices Degree: " +(meanJoinVertexDegree/joinVertices.size()));
			  
	}
	
	/**
	 * Get the the list of join vertices
	 * @param Vertices List of vertices in BGP
	 * @return V Collection of join vertices
	 */
	private static HashSet<Vertex> getJoinVertexCount(HashSet<Vertex> Vertices) {
		 HashSet<Vertex> V = new HashSet<Vertex>();
		for (Vertex vertex:Vertices)
		{
			long inDeg = vertex.inEdges.size();
			long outDeg = vertex.outEdges.size();
			long degSum = inDeg + outDeg;
			if(degSum>1)
				V.add(vertex);
		}
		return V;
	}
	/**
	 * Get label for the object vertex of a triple pattern
	 * @param stmt triple pattern 
	 * @return label Vertex label
	 */
	private static String getObjectVertexLabel(StatementPattern stmt) {
		String label ; 
		if (stmt.getObjectVar().getValue()!=null)
		{
		label = stmt.getObjectVar().getValue().stringValue();
		if(label.startsWith("http://") || label.startsWith("ftp://"))
			boundObjURICount++;
		else
			BoundObjLiteralCount++;
		}
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
		{
		label = stmt.getPredicateVar().getValue().stringValue();
		boundPredCount++;
		}
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
		{
		label = stmt.getSubjectVar().getValue().stringValue();
		boundSbjCount++;
		}
		else
		label =stmt.getSubjectVar().getName(); 
			return label;
		
	}
	
	/**
	 * Check if a  vertex already exists in set of all vertices
	 * @param sbjVertex
	 * @param V Set of all vertices
	 * @return 
	 * @return value Boolean value
	 */
public static  boolean vertexExist(Vertex sbjVertex, HashSet<Vertex> V) {
		for(Vertex v:V)
		{
			if(sbjVertex.label.equals(v.label))
				return true;
		}
		return false;
	}
/**
 * Retrieve a vertex having a specific label from a set of Vertrices
 * @param label Label of vertex to be retrieved
 * @param V Set of vertices
 * @return Vertex if exist otherwise null
 */
public static Vertex getVertex(String label, HashSet<Vertex> V) {
	for(Vertex v:V)
	{
		if(v.label.equals(label))
			return v;
	}
	return null;
}


}