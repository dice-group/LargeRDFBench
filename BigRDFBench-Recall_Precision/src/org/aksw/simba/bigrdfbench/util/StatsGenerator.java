package org.aksw.simba.bigrdfbench.util;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openrdf.query.BindingSet;
import org.openrdf.query.MalformedQueryException;
import org.openrdf.query.QueryEvaluationException;
import org.openrdf.query.QueryLanguage;
import org.openrdf.query.TupleQuery;
import org.openrdf.query.TupleQueryResult;
import org.openrdf.repository.RepositoryException;

import com.google.common.collect.Sets;

public class StatsGenerator {


	
	public static void main(String[] args) throws RepositoryException, MalformedQueryException, QueryEvaluationException {
		// TODO Auto-generated method stub
//		String results = "D:/BigRDFBench/completeness_correctness/results.nt";
//         ResultsLoader.loadResults(results);
//         getActualResults("S1");
	}

	public static String getFscores(String queryNo,TupleQueryResult res) throws QueryEvaluationException, RepositoryException, MalformedQueryException 
	{
		
		String Fscores = "" ;
		double precision, recall,F1;
		Set<String> curResults =  getCurrentResult(res) ;
		//System.out.println("current:"+ curResults);
		Set<String> actualResults = getActualResults(queryNo) ;
		//System.out.println("actual:" +actualResults);
		Set<String> diffSet = Sets.difference(actualResults, curResults);
		//System.out.println(diffSet);
		//System.out.println(Sets.difference( curResults,actualResults));
		double correctResults = actualResults.size()-diffSet.size();
		precision = (correctResults/curResults.size());
		recall = correctResults/actualResults.size();
		F1 = 2*(precision*recall)/(precision+recall);
		Fscores = "Precision: "+precision+", Recall: " + recall +", F1: "+F1;
		return Fscores;
		
	}
//	public static float getPrecision(String queryNo,TupleQueryResult res) throws QueryEvaluationException, RepositoryException, MalformedQueryException 
//	{
//		
//		float precision = 0 ;
//		Set<String> curResults =  getCurrentResult(res) ;
//		Set<String> actualResults = getActualResults(queryNo) ;
//		Set<String> diffSet = Sets.difference(actualResults, curResults);
//		precision = diffSet.size();
//		System.out.println(diffSet);
//		System.out.println(Sets.difference(curResults,actualResults));
//		return precision;
//		
//	}
	private static Set<String> getCurrentResult(TupleQueryResult res) throws QueryEvaluationException 
	{
		List<String> bindingNames = res.getBindingNames();
		Set<String> curResults = new HashSet<String>() ;
		while (res.hasNext()) {
			BindingSet result = res.next();
			String recordLine ="";
			for(int i = 0 ; i < bindingNames.size(); i++)
			{
				String bindingName = bindingNames.get(i);
				String bindingVal = result.getBinding(bindingName).getValue().toString().replaceAll("\n", " ").replace("]", "").replace("[", "");
				if(i< bindingNames.size()-1)					
				recordLine = recordLine+bindingName+"="+bindingVal+";";
				else
					recordLine = recordLine+bindingName+"="+bindingVal;	
				
			}
			curResults.add("["+recordLine+"]");
			}

		return curResults;
	}

	private static Set<String> getActualResults(String queryName) throws RepositoryException, MalformedQueryException, QueryEvaluationException {
		Set<String> actualResults = new HashSet<String>() ;
		
		String queryString = "PREFIX bigrdfbench: <http://bigrdfbench.aksw.org/schema/> \n"
				+ "			Select ?names ?values \n"
				+ "			WHERE \n"
				+ "			{\n"
				+ "			?s bigrdfbench:queryName <http://aksw.org/bigrdfbench/query/"+queryName+">.\n"
				+ "			<http://aksw.org/bigrdfbench/query/"+queryName+"> bigrdfbench:bindingNames ?names.\n"
				+ "			<http://aksw.org/bigrdfbench/query/"+queryName+"> bigrdfbench:bindingValues ?values\n"
				+ "			}";
		 
	 	    TupleQuery tupleQuery = ResultsLoader.con.prepareTupleQuery(QueryLanguage.SPARQL, queryString);
	 		 TupleQueryResult res = tupleQuery.evaluate();
	 		   while(res.hasNext())
	 		   {
	 			  BindingSet result = res.next();
	 		     String[] bindingNames =  result.getValue("names").stringValue().replace("'", "\"").replace("[", "").replace("]", "").split(";");
	 		     String[] bindingValues =  result.getValue("values").stringValue().replace("'", "\"").replace("[", "").replace("]", "").split(";");
	 		     String actualResult = "[";
	 		     for(int i=0 ; i < bindingNames.length;i++)
	 		     {
	 		    	if(i<bindingNames.length-1)
	 		    	 actualResult= actualResult+bindingNames[i]+"="+bindingValues[i]+";";
	 		    	else
	 		    		actualResult= actualResult+bindingNames[i]+"="+bindingValues[i]+"]";
	 		     }
	 		     actualResults.add(actualResult);
	 		  //   System.out.println(actualResult);
	 		   }
	 		//   System.out.println(actualResults.size());
		return actualResults;
	}
        
}
