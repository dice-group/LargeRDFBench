package org.aksw.simba.bigrdfbench.util;

import java.io.UnsupportedEncodingException;
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
/**
 * Precision, Recall, and F1 generator
 * @author Saleem
 *
 */
public class StatsGenerator {


	
	public static void main(String[] args) throws RepositoryException, MalformedQueryException, QueryEvaluationException {
		// TODO Auto-generated method stub
//		String results = "D:/BigRDFBench/completeness_correctness/results.nt";
//         ResultsLoader.loadResults(results);
//         getActualResults("S1");
	}
/**
 * Get Precison, Recall, and F1 Score for the given query results
 * @param queryNo Query number for which the scores are required to be computed e.g. S1, B1 , C1 etc.
 * @param res ResultSet Iterator
 * @return Fscores Precision, Recall, and F1 scores
 * @throws QueryEvaluationException
 * @throws RepositoryException
 * @throws MalformedQueryException
 * @throws UnsupportedEncodingException 
 */
	public static String getFscores(String queryNo,TupleQueryResult res) throws QueryEvaluationException, RepositoryException, MalformedQueryException, UnsupportedEncodingException 
	{
		String Fscores = "" ;
		double precision, recall,F1;
		Set<String> curResults =  getCurrentResult(res) ;
		//System.out.println("current:"+ curResults);
		Set<String> actualResults = getActualResults(queryNo) ;
		//System.out.println("actual:" +actualResults);
		Set<String> diffSet = Sets.difference(actualResults, curResults);
		//Set<String> diff = differenceSet(actualResults, curResults);
		//System.out.println(diff.size());
		//System.out.println(diffSet.size());
		//System.out.println("Actual-Cur: "+ diffSet);
		//System.out.println("Cur-Actual: "+Sets.difference( curResults,actualResults));
		double correctResults = actualResults.size()-diffSet.size();
		precision = (correctResults/curResults.size());
		recall = correctResults/actualResults.size();
		F1 = 2*(precision*recall)/(precision+recall);
		Fscores = "Precision: "+precision+", Recall: " + recall +", F1: "+F1;
		return Fscores;
		
	}
//	/**
//	 * Calculate difference set of two sets
//	 * @param actualResults Set A
//	 * @param curResults Set B
//	 * @return diffSet Difference set of A and B
//	 * @throws UnsupportedEncodingException 
//	 */
//	public static Set<String> differenceSet(Set<String> actualResults,
//		Set<String> curResults) throws UnsupportedEncodingException {
//		Set<String> diffSet = new HashSet<String>();
//		for(String e:actualResults)
//		{
//			//e = new String(e.getBytes("UTF-8"), "UTF-8");
//			if(!curResults.contains(e))
//				diffSet.add(e);
//		}
//	return diffSet;
//}
	/**
	 * Get the list of missing results (if any) the query execution
	* @param queryNo Query number for which the scores are required to be computed e.g. S1, B1 , C1 etc.
	* @param res ResultSet Iterator
	* @return Fscores Precision, Recall, and F1 scores
	* @throws QueryEvaluationException
	* @throws RepositoryException
	* @throws MalformedQueryException
	 */
	public static Set<String> getMissingResults(String queryNo,TupleQueryResult res) throws QueryEvaluationException, RepositoryException, MalformedQueryException 
	{
		Set<String> curResults =  getCurrentResult(res) ;
		//System.out.println("current:"+ curResults);
		Set<String> actualResults = getActualResults(queryNo) ;
		//System.out.println("actual:" +actualResults);
		Set<String> diffSet = Sets.difference(actualResults, curResults);
		return diffSet;
		
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
	/**
	 * Get the current results of the query execution
	 * @param res ResultSet iterator
	 * @return curResults List of results
	 * @throws QueryEvaluationException
	 */
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
				try{
				String bindingVal = result.getBinding(bindingName).getValue().toString().replace("'", "\"").replaceAll("\n", " ").replace("]", "").replace("[", "");
				byte ptext[] = bindingVal.getBytes();
				bindingVal = new String(ptext, "UTF-8");
				if(i< bindingNames.size()-1)					
				recordLine = recordLine+bindingName+"="+bindingVal+"<===>";
				else
					recordLine = recordLine+bindingName+"="+bindingVal;	
				} catch (Exception e){
					String bindingVal ="\"null\"";
					if(i< bindingNames.size()-1)					
						recordLine = recordLine+bindingName+"="+bindingVal+"<===>";
						else
							recordLine = recordLine+bindingName+"="+bindingVal;	
					}
			}
			curResults.add("["+recordLine+"]");
			}

		return curResults;
	}
	/**
	 * Get the actual result of the given query
	 * @param queryName Name of the query e.g. S1, B1 , C1 etc. 
	 * @return actualResults List of actual results
	 * @throws RepositoryException
	 * @throws MalformedQueryException
	 * @throws QueryEvaluationException
	 */

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
	 		     String[] bindingNames =  result.getValue("names").stringValue().replace("\n", " ").replace("'", "\"").replace("[", "").replace("]", "").split("<===>");
	 		     String[] bindingValues =  result.getValue("values").stringValue().replace("\n", " ").replace("'", "\"").replace("[", "").replace("]", "").split("<===>");
	 		     String actualResult = "[";
	 		     for(int i=0 ; i < bindingNames.length;i++)
	 		     {
	 		    	if(i<bindingNames.length-1)
	 		    	 actualResult= actualResult+bindingNames[i]+"="+bindingValues[i]+"<===>";
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
