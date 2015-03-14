This page provides details about how one can use the LargeRDFBench java utility to calculate Precision, Recall, and F1 scores for a qiven SPARQL query. The main aim of this utility is to measure the completeness and correctness of results retrieved by the underlying federation engine for a given SPARQL query. First, we explain how this utility can be used with BigRDFBench, followed by the details how one can use it for any other benchmark.

> ### Using with LargeRDFBench ###

  1. Download the Java [LargeRDFBench\_Util.jar](http://goo.gl/8ql1UA) and include into the underlying federation engine jar libraries. The complete source code for this tool can also be checkout from SVN link http://bigrdfbench.googlecode.com/svn/trunk/.
  1. Download the LargeRDFBench actual queries [results](http://goo.gl/8tX1Pa) folder and store in your computer.
  1. You can compute the Precision, Recall, and F1 score by calling the function

```
StatsGenerator.getFscores(String queryAcutalResultsFile,TupleQueryResult res)
 OR
StatsGenerator.getFscores(String queryAcutalResultsFile, ArrayList<String> currentQueryResults).
```

  1. You can retrieve the results missed by federation engine (if any) by calling the function

```
StatsGenerator.getMissingResults(String queryAcutalResultsFile,TupleQueryResult res)
OR
StatsGenerator.getMissingResults(String queryAcutalResultsFile, ArrayList<String> currentQueryResults). 
```

A sample code (FedX based) for retrieving the Precision, Recall and F1 scores of LargeRDFBench query no S2 is given below.

```
String S2 = "SELECT ?party ?page  WHERE {  <http://dbpedia.org/resource/Barack_Obama> 
<http://dbpedia.org/ontology/party> ?party .
?x <http://data.nytimes.com/elements/topicPage> ?page .
?x <http://www.w3.org/2002/07/owl#sameAs> <http://dbpedia.org/resource/Barack_Obama> .}";

TupleQuery query = repo.getConnection().prepareTupleQuery(QueryLanguage.SPARQL, S2); 

TupleQueryResult res = query.evaluate();

System.out.println(StatsGenerator.getFscores("D://results/S2", res));
```

A sample output is given below.
```
Precision: 1.0, Recall: 1.0, F1: 1.0
```

**The above steps also applied to [FedBench](https://code.google.com/p/fbench/) federation benchmark.**

### Using with any other Benchmark ###
All of the steps remain the same for using this utility with any other benchmark. Just you need to get the actual results of the benchmark queries and store in separate files in a directory. The results should be one per line and the separator (we used <===> see [results](http://goo.gl/8tX1Pa)) between projection elements in a result should be constant for all queries results. You may use any other separator and change the code accordingly.

It is important to note that this utility is highly sensitive to character encoding (UTF-8 is used) specially query C8 contains many rare characters which needs proper encoding. Therefore, it is highly recommended to have a special look at the Precision, Recall, and F1 values for this query. It is also important to note that Precision, Recall, and F1 scores are not valid if SPARQL LIMIT clause is used in the query (C1,C4,C5,C9). This is because the results comes in random order until the specified LIMIT is reached.
If you encounter any issue/problem please use project issue tracker https://code.google.com/p/bigrdfbench/issues/list or directly contact at saleem.muhammd@gmail.com

