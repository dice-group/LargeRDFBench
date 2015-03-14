## <font color='blue'>LargeRDFBench: A Billion Triples Benchmark for SPARQL Query Federation </font> ##

LargeRDFBench is a comprehensive benchmark encompasses real data and real queries (i.e., showing typical requests) of varying complexities, suite for testing and analyzing both the efficiency and effectiveness of federated query processing over multiple SPARQL endpoints.


### Benchmark Datasets Statistics ###

In the following we provide information about the datasets used in LargeRDFBench along with download links, both for data-dumps and Virtuso-7.10 SPARQL endpoints.

| **Dataset** | **#Triples** | **#Distinct Subjects** | **#Distinct Predicates** | **#Distinct Objects** | **#Classes** | **#Links** | **Structuredness** |
|:------------|:-------------|:-----------------------|:-------------------------|:----------------------|:-------------|:-----------|:-------------------|
|LinkedTCGA-M|415030327|83006609|6 |166106744|1 |- |1 |
|LinkedTCGA-E|344576146|57429904|7 |84403422 |1 |- |1 |
|LinkedTCGA-A|35329868|5782962|383|8329393|23|251.3k|0.998|
|ChEBI|4772706|50477|28|772138 |1 |- | 0.340|
|DBPedia-Subset|42849609|9495865|1063|13620028|248|65.8k|0.196|
|DrugBank|517023|19693|119|276142 |8 |10.8k|0.726|
|Geo Names|107950085|7479714|26|35799392|1 |118k|0.518|
|Jamendo|1049647|335925|26|440686 |11|1.7k|0.961|
|KEGG|1090830|34260|21|939258 |4 |1.3k|0.919|
|Linked MDB|6147996|694400|222|2052959 |53|63.1k |0.729|
|New York Times|335198|21666|36|191538|2 |31.7k|0.731|
|Semantic Web Dog Food|103595|11974|118|37547|103|2.3k|0.426|
|Affymetrix|44207146|1421763|105|13240270|3 |246.3k|0.506|
| **Total** | **1003960176** | **165785212** | **2160** | **326209517** | **459** | **792.3k** | **Avg. 0.696**|

Duan et al. " Apples and Oranges: A Comparison of RDF Benchmarks and Real RDF Datasets" introduced the notion of structuredness or choerence, which indicates
whether the instances in a dataset have only a few or all attributes of their
types set. They show that artificial datasets are typically highly structured and
“real” datasets are less structured.
<font color='red'>
The complete details along with type coverages can be found <a href='http://goo.gl/XpSSPN'>here</a>.  The LargeRDFBench java utility to calculate the dataset structuredness can be found <a href='http://goo.gl/TgVg2G'>here</a> along with usage examples. </font>

### Datasets Availability ###

All the datasets and corresponding virtuoso SPARQL endpoints can be downloaded from the links given below. For SPARQL endpoint federation systems, we strongly recommend to directly download the endpoints as some of the datadumps are quite big and require a lot of upload time. You may start a SPARQL endpoint from bin/start.bat (for windows) and bin/start\_virtuoso.sh (for linux). Please note that LinkedTCGA-M(Mehtylation), LinkedTCGA-E(Exon),  LinkedTCGA-A(All others), and DBpedia-subset are subsets of the live SPARQL endpoints. Further, the TCGA live SPARQL endpoints are not aligned with Affymetrix, Drugbank, and DBpedia.

| **Dataset** | **Data-dump** | **Windows Endpoint** | **Linux Endpoint** | **Local Endpoint Url**| **Live Endpoint Url**|
|:------------|:--------------|:---------------------|:-------------------|:----------------------|:---------------------|
| [LinkedTCGA-M](http://tcga.deri.ie/) |[Download](https://drive.google.com/file/d/0B_MUFqryVpByQ0J2NFAtNVlzMUk/edit?usp=sharing/) | [Download](https://drive.google.com/file/d/0B_MUFqryVpByNDRLQkhucXE2U00/edit?usp=sharing/) | [Download](https://drive.google.com/file/d/0B1tUDhWNTjO-ako3bHhwY0lVTm8/edit?usp=sharing/) | your.system.ip.address:8887/sparql |http://digital3.path.uab.edu/sparql |
| [LinkedTCGA-E](http://tcga.deri.ie/) |[Download](https://drive.google.com/file/d/0B_MUFqryVpByOGdJWU8xNXR0aUE/edit?usp=sharing/) | [Download](https://drive.google.com/file/d/0B_MUFqryVpByNjd4TjVGS1dRbnc/edit?usp=sharing/) | [Download](https://drive.google.com/file/d/0B1tUDhWNTjO-RE5FaHp1eXFSenM/edit?usp=sharing/) | your.system.ip.address:8888/sparql | http://digital2.path.uab.edu/sparql|
| [LinkedTCGA-A](http://tcga.deri.ie/) |[Download](https://drive.google.com/file/d/0B_MUFqryVpByd2FVQ2gzOXhIemc/edit?usp=sharing/) | [Download](https://drive.google.com/file/d/0B_MUFqryVpBydUpVWjBMX2FGNEE/edit?usp=sharing/) | [Download](https://drive.google.com/file/d/0B_MUFqryVpByYXdtbGhNYjdCZ3M/edit?usp=sharing/) |your.system.ip.address:8889/sparql | http://digital.path.uab.edu/sparql|
| [ChEBI](https://www.ebi.ac.uk/chebi/) |[Download](https://drive.google.com/file/d/0B1tUDhWNTjO-Vk81dGVkNVNuY1E/edit?usp=sharing/)| [Download](https://drive.google.com/file/d/0B1tUDhWNTjO-TUR6RF9jX2xoMFU/edit?usp=sharing/)|[Download](https://drive.google.com/file/d/0B1tUDhWNTjO-Wk5LeHBzMUd3VHc/edit?usp=sharing)|your.system.ip.address:8890/sparql | http://chebi.bio2rdf.org/sparql |
| [DBPedia-Subset](http://DBpedia.org/)|[Download](https://drive.google.com/file/d/0B1tUDhWNTjO-QWk5MVJud3cxUXM/edit?usp=sharing/)|  [Download](https://drive.google.com/file/d/0B1tUDhWNTjO-WjNkZEZrTTZzbW8/edit?usp=sharing/)|[Download](https://drive.google.com/file/d/0B1tUDhWNTjO-OEgyXzBUVmlMQlk/edit?usp=sharing)|your.system.ip.address:8891/sparql |http://dbpedia.org/sparql |
| [DrugBank](http://www.drugbank.ca/)|[Download](https://drive.google.com/file/d/0B1tUDhWNTjO-cVp5QV9VUWRuYkk/edit?usp=sharing/) | [Download](https://drive.google.com/file/d/0B1tUDhWNTjO-QmMyOE9RWV9oNHM/edit?usp=sharing/)| [Download](https://drive.google.com/file/d/0B1tUDhWNTjO-U0V5Y0xDWXhzam8/edit?usp=sharing/)|your.system.ip.address:8892/sparql | http://wifo5-04.informatik.uni-mannheim.de/drugbank/sparql|
| [Geo Names](http://www.geonames.org/)|[Download](https://drive.google.com/file/d/0B1tUDhWNTjO-WEZZb2VwOG5vZkU/edit?usp=sharing/) | [Download](https://drive.google.com/file/d/0B1tUDhWNTjO-VC1HWmhBMlFncWc/edit?usp=sharing/) | [Download](https://drive.google.com/file/d/0B_MUFqryVpByd3hJcHBPeHZhejA/edit?usp=sharing/) |your.system.ip.address:8893/sparql | http://factforge.net/sparql|
| [Jamendo](http://dbtune.org/jamendo/) |[Download](https://drive.google.com/file/d/0B1tUDhWNTjO-cWpmMWxxQ3Z2eVk/edit?usp=sharing/) | [Download](https://drive.google.com/file/d/0B1tUDhWNTjO-YXV6U0ZzLUF0S0k/edit?usp=sharing/) | [Download](https://drive.google.com/file/d/0B1tUDhWNTjO-V3JMZjdfRkZxLUU/edit?usp=sharing/) |your.system.ip.address:8894/sparql  | http://dbtune.org/jamendo/sparql/|
| [KEGG](http://www.genome.jp/kegg/) |[Download](https://drive.google.com/file/d/0B1tUDhWNTjO-TUdUcllRMGVJaHM/edit?usp=sharing/) | [Download](https://drive.google.com/file/d/0B1tUDhWNTjO-c1BNQ0dVWTVkUEU/edit?usp=sharing/) | [Download](https://drive.google.com/file/d/0B1tUDhWNTjO-R1dKbDlHNXZ6blk/edit?usp=sharing/) |your.system.ip.address:8895/sparql |http://cu.kegg.bio2rdf.org/sparql |
| [Linked MDB](http://linkedmdb.org/) |[Download](https://drive.google.com/file/d/0B1tUDhWNTjO-bU5VN25NLXZXU0U/edit?usp=sharing/) | [Download](https://drive.google.com/file/d/0B1tUDhWNTjO-eXpVSjd2Y25PaVk/edit?usp=sharing/) | [Download](https://drive.google.com/file/d/0B1tUDhWNTjO-NjVTVERvajJUcGc/edit?usp=sharing/) |your.system.ip.address:8896/sparql |http://www.linkedmdb.org/sparql |
| [New York Times](http://data.nytimes.com/) |[Download](https://drive.google.com/file/d/0B1tUDhWNTjO-dThoTm9DSmY4Wms/edit?usp=sharing/) | [Download](https://drive.google.com/file/d/0B1tUDhWNTjO-VDhmNWJmZVcybm8/edit?usp=sharing/) | [Download](https://drive.google.com/file/d/0B1tUDhWNTjO-RG9GeVdxbDR4YjQ/edit?usp=sharing/) |your.system.ip.address:8897/sparql | - |
| [Semantic Web Dog Food](http://data.semanticweb.org/) |[Download](https://drive.google.com/file/d/0B1tUDhWNTjO-RjBWZXYyX2FDT1E/edit?usp=sharing/) | [Download](https://drive.google.com/file/d/0B1tUDhWNTjO-c2h4al9VREF6bDg/edit?usp=sharing/) | [Download](https://drive.google.com/file/d/0B1tUDhWNTjO-UW5HaF9rekdialU/edit?usp=sharing/) |your.system.ip.address:8898/sparql | http://data.semanticweb.org/sparql|
| [Affymetrix](http://download.bio2rdf.org/release/2/affymetrix/affymetrix.html)| [Download](https://drive.google.com/file/d/0B1tUDhWNTjO-eHVlZ1RyVVFJQU0/edit?usp=sharing/)| [Download](https://drive.google.com/file/d/0B1tUDhWNTjO-RnV4SWtKelJTb0U/edit?usp=sharing/)|[Download](https://drive.google.com/file/d/0B1tUDhWNTjO-Tm9oazNUdV9Cb1k/edit?usp=sharing)|your.system.ip.address:8899/sparql |http://cu.affymetrix.bio2rdf.org/sparql |

### Datasets Connectivity ###

![https://sites.google.com/site/saleemsweb/home/connectivity.png](https://sites.google.com/site/saleemsweb/home/connectivity.png)

### Benchmark Queries ###

LargeRDFBench comprise of a total of 32 queries (both SPARQL 1.0 and SPARQL 1.1 versions) for SPARQL endpoint federation approaches. The 32 queries are divided into three different types : 14 simple queries (from [FedBench](https://code.google.com/p/fbench/)), 10 complex queries, and 8 large data queries. The detail of these queries is given in table below. All of the queries can be downloaded from ([SPARQL 1.0](http://goo.gl/Z57W8P), [SPARQL 1.1](http://goo.gl/hnJ8D3)). The queries full results can be downloaded from [here](http://goo.gl/8tX1Pa).

|<th>LargeRDFBench SPARQL Endpoint Federation Queries (<a href='http://goo.gl/Z57W8P'>SPARQL 1.0</a>, <a href='http://goo.gl/hnJ8D3'>SPARQL 1.1</a>), Complete Queries <a href='http://goo.gl/8tX1Pa'>Results</a> <br>
<tr><td> <b>Query</b>  </td><td> <b>Query Type</b> </td><td> <b>#Triple Patterns</b> </td><td> <b>#Sources Span</b> </td><td> <b>#Results</b> </td><td>  <b>#Join Vertices</b> </td><td> <b>Mean Join Vertex Degree</b> </td><td> <b>Mean Triple Pattern Selectivity</b> </td><td>  <b>Used SPARQL Clauses</b> </td></tr>
<tr><td> S1 </td><td> Simple </td><td> 3 </td><td> 2 </td><td> 90 </td><td> 1 </td><td> 2 </td><td> 0.333334927 </td><td> UNION </td></tr>
<tr><td> S2 </td><td> Simple </td><td> 3 </td><td> 2 </td><td> 1 </td><td> 2  </td><td>2 </td><td>0.007391659</td><td> X </td></tr>
<tr><td> S3 </td><td> Simple </td><td> 5 </td><td> 5 </td><td> 2 </td><td> 2  </td><td>3 </td><td>0.008889571</td><td> X  </td></tr>
<tr><td> S4 </td><td> Simple </td><td> 5 </td><td> 5 </td><td> 1 </td><td> 5  </td><td>2 </td><td>0.019295158</td><td>X </td></tr>
<tr><td> S5 </td><td> Simple </td><td> 4 </td><td> 5 </td><td> 2 </td><td> 3  </td><td>2 </td><td>0.006169376</td><td>X </td></tr>
<tr><td> S6 </td><td> Simple </td><td> 4 </td><td> 4 </td><td> 11 </td><td> 3 </td><td>2 </td><td>0.019395937</td><td>X </td></tr>
<tr><td> S7 </td><td> Simple </td><td> 4 </td><td> 5 </td><td> 1 </td><td> 3  </td><td>2 </td><td>0.020198747</td><td>X </td></tr>
<tr><td> S8 </td><td> Simple </td><td> 2 </td><td> 2 </td><td> 1159 </td><td>0 </td><td>NA</td><td>0.00112084</td><td> UNION</td></tr>
<tr><td> S9 </td><td> Simple </td><td> 3 </td><td> 4 </td><td> 333 </td><td>1 </td><td>2 </td><td>0.3335448</td><td> UNION</td></tr>
<tr><td> S10 </td><td> Simple </td><td> 5 </td><td> 2 </td><td> 9054 </td><td>3 </td><td>2.33</td><td>0.016010968</td><td>X </td></tr>
<tr><td> S11 </td><td> Simple </td><td> 7 </td><td> 2 </td><td> 3 </td><td> 4</td><td>2.5</td><td>0.006628492</td><td>X </td></tr>
<tr><td> S12 </td><td> Simple </td><td> 6 </td><td> 3 </td><td> 393 </td><td> 4</td><td>2.25</td><td>0.01217423</td><td>X </td></tr>
<tr><td> S13 </td><td> Simple </td><td> 5 </td><td> 3 </td><td> 28 </td><td> 3</td><td>2.33</td><td>0.014815076</td><td>X </td></tr>
<tr><td> S14 </td><td> Simple </td><td> 5 </td><td> 3 </td><td> 1620 </td><td> 3</td><td>2 </td><td>0.012452045</td><td>OPTIONAL</td></tr>
<tr><td> C1 </td><td> Complex </td><td> 8 </td><td> 5 </td><td> 1000 </td><td> 4</td><td>2.5</td><td>0.010779222</td><td> DISTINCT, FILTER, OPTIONAL, LIMIT </td></tr>
<tr><td> C2 </td><td> Complex </td><td> 8 </td><td> 5 </td><td> 4 </td><td> 4</td><td>2.25</td><td>0.00972466</td><td> OPTIONAL, FILTER</td></tr>
<tr><td> C3 </td><td> Complex </td><td> 8 </td><td> 3 </td><td> 9 </td><td> 4</td><td>2.25</td><td>0.020214493</td><td> DISTINCT, OPTIONAL</td></tr>
<tr><td> C4 </td><td> Complex </td><td> 12 </td><td> 8 </td><td> 50 </td><td> 2</td><td>6 </td><td>0.012412695</td><td> DISTINCT, OPTIONAL, LIMIT</td></tr>
<tr><td> C5 </td><td> Complex </td><td> 8 </td><td> 8 </td><td> 500 </td><td> 5</td><td>2.4</td><td>0.018655228</td><td> FILTER, LIMIT</td></tr>
<tr><td> C6 </td><td> Complex </td><td> 9 </td><td> 2 </td><td> 148 </td><td> 5</td><td>2.8</td><td>0.022971132</td><td> ORDER BY</td></tr>
<tr><td> C7 </td><td> Complex </td><td> 9 </td><td> 2 </td><td> 112 </td><td> 6</td><td>2.33</td><td>0.014815749</td><td>DISTINCT, OPTIONAL</td></tr>
<tr><td> C8 </td><td> Complex </td><td> 11 </td><td> 3 </td><td> 3067 </td><td> 4</td><td>3.25</td><td>0.012449596</td><td>DISTINCT, OPTIONAL</td></tr>
<tr><td> C9 </td><td> Complex </td><td> 9 </td><td> 3</td><td> 100 </td><td> 4</td><td>2.75</td><td>0.01107262</td><td> OPTIONAL, ORDER BY, LIMIT</td></tr>
<tr><td> C10 </td><td> Complex </td><td> 10 </td><td> 3 </td><td> 102 </td><td> 5</td><td>2.8</td><td>0.002712048</td><td> DISTINCT</td></tr>
<tr><td> L1 </td><td> Large Data </td><td> 6</td><td> 3 </td><td> 227192</td><td> 4</td><td>2 </td><td>0.19222037</td><td> UNION </td></tr>
<tr><td> L2 </td><td> Large Data </td><td> 6 </td><td> 3 </td><td> 152899</td><td> 2</td><td>3.5</td><td>0.286786739</td><td>DISTINCT, FILTER</td></tr>
<tr><td> L3 </td><td> Large Data </td><td> 7 </td><td> 3 </td><td> 257158 </td><td> 3</td><td>3 </td><td>0.245822714</td><td> FILTER, ORDER BY</td></tr>
<tr><td> L4 </td><td> Large Data </td><td> 8 </td><td> 4 </td><td> 397204</td><td> 4</td><td>2.5</td><td>0.305675513</td><td>UNION, FILTER, REGEX</td></tr>
<tr><td> L5 </td><td> Large Data </td><td> 11 </td><td> 4 </td><td> 190575 </td><td> 5</td><td>3 </td><td>0.485882296</td><td>FILTER</td></tr>
<tr><td> L6 </td><td> Large Data </td><td> 10 </td><td> 4 </td><td> 282154</td><td> 5</td><td>2.8</td><td>0.349528975</td><td>FILTER, DISTINCT</td></tr>
<tr><td> L7 </td><td> Large Data </td><td> 5 </td><td> 4 </td><td> 80460 </td><td> 3</td><td>2.33</td><td>0.20069629</td><td>DISTINCT, FILTER</td></tr>
<tr><td> L8 </td><td> Large Data </td><td> 8 </td><td> 3 </td><td> 306705 </td><td> 4</td><td>2.5</td><td>0.278093563</td><td>UNION, FILTER</td></tr></tbody></table>

<font color='red'>
Further advanced queries features can be found [here](http://goo.gl/eeW5W0) and discussed in the LargeRDFBench paper. The mean triple pattern selectivities along with complete details, for all of the LargeRDFBench queries can be found [here](http://goo.gl/fDNXj9).
The LargeRDFBench java utility to calculate all these queries features can be found [here](http://goo.gl/TgVg2G) along with usage examples.</font>

<h3>Usage Information ###

In the following we explain how one can setup the LargeRDFBench evaluation framework and measure the performance of the federation engine.

#### SPARQL Endpoints Setup ####
  1. The first step is to download the SPARQL endpoints (portable Virtuoso SAPRQL endpoints from second table above) on different machines, i.e., computers. Best would be one SPARQL endpoint per machine. Therefore, you need a total of 13 machines. However, you can start more than one SPARQL endpoints per machine.
  1. The next step is to start the SPARQL endpoint from bin/start.bat (for windows) or bin/start\_virtuoso.sh (for Linux). Make a list of the 13 SPARQL endpoints URL's ( required as input for index-free SPARQL query federation engines, i.e., FedX). It is important to note that index-assisted federation engines (e.g., SPLENDID, DARQ, ANAPSID) usually stores the endpoint URL's in its index. The local SPARQL endpoints URL's are given above in second table.

#### Running SPARQL Queries ####
Provides the list of SPARQL endpoints URL's, and a LargeRDFBench query to the underlying federation engine as input and calculate the LargeRDFBench metrics (explained next). The query evaluation start-up files for the selected systems (which you can checkout from https://bigrdfbench.googlecode.com/svn/trunk/) are given below.

----------FedX-original-----------------------

package : package org.aksw.simba.start;

File:QueryEvaluation.java

----------FedX-HiBISCuS-----------------------

package : package org.aksw.simba.fedsum.startup;

File:QueryEvaluation.java

----------SPLENDID-original-----------------------

package : package de.uni\_koblenz.west.evaluation;

File:QueryProcessingEval.java

----------SPLENDID-HiBISCuS-----------------------

package : package de.uni\_koblenz.west.evaluation;

File:QueryProcessingEval.java

----------ANAPSID-----------------------

Follow the instructions given at https://github.com/anapsid/anapsid to configure the system and then use anapsid/ivan-scripts/runQuery.sh to run a query.

#### Running SPARQL 1.1 Queries ####
Both ANAPSID, FedX provides support for SPARQL 1.1 queries. The procedure for running SPARQL 1.1 queries on these two systems remain the same. You can also directly run SPARQL 1.1 queries of LargeRDFBench from SPARQL endpoint online interface (see Local endpoints URL's from second table given above).

While running SPARQL 1.1 federation queries with online interface of Virtuoso SPARQL endpoint, you may encounter the following error

<font color='red'>Virtuoso 42000 Error SQ200: Must have select privileges on view DB.DBA.SPARQL_SINV_2 </font>

You can solve this problem by opening Virtuoso conductor from http://your.system.ip.address:portno/conductor/isql.vspx (e.g., http://localhost:8888/conductor/isql.vspx). Type both user id and password as "dba". Once login, execute the following two commands.

<font color='red'>grant select on "DB.DBA.SPARQL_SINV_2" to "SPARQL";<br>
<br>
grant execute on "DB.DBA.SPARQL_SINV_IMP" to "SPARQL"; </font>

You should be able to run all of the benchmark SPARQL 1.1 queries by using online virtuoso online query interface. Please dont set the default named graph at virtuoso online query interface, otherwise, you may get no results.


#### How to calculate LargeRDFBench metrics? ####
LargeRDFBench makes use of 6 -- #ASK, #TP. Sources, Source selection time, Query runtime, Results completeness, Results correctness -- main metrics (See paper for details). The first 4 can directly be computed from the source code (checkout the selected systems to see how we calculated these 4 metrics) of the underlying federation engine.  While for the later 2, we provided a  [java tool](http://goo.gl/rO2yvb) which computes the precision, recall, F1-score of the results retrieved by the federation engine for a given benchmark query.




### Evaluation Results and Runtime Errors ###

We have compared 5 - [FedX](https://www.mpi-inf.mpg.de/~khose/publications/ISWC2011.pdf), [SPLENDID](http://ceur-ws.org/Vol-782/GoerlitzAndStaab_COLD2011.pdf), [ANAPSID](http://link.springer.com/chapter/10.1007%2F978-3-642-25073-6_2#page-1), [FedX+HiBISCuS](http://svn.aksw.org/papers/2014/HiBISCuS_ESWC/public.pdf), [SPLENDID+HiBISUCuS](http://svn.aksw.org/papers/2014/HiBISCuS_ESWC/public.pdf) - state-of-the-art SPARQL endpoint federation systems with LargeRDFBench. Our complete evaluation results can be downloaded from [here](http://goo.gl/d7LxPN) and the runtime errors thrown by the federation systems can be downloaded from [here](https://drive.google.com/file/d/0BzemFAUFXpqOUUtvTDBUR2JMUms/edit?usp=sharing).


### SPARQL Endpoints Specifications ###
Following are the specifications of the machines used in the evaluation to host SPARQL endpoints.

![https://sites.google.com/site/saleemsweb/home/sparqlspecs.png](https://sites.google.com/site/saleemsweb/home/sparqlspecs.png)



### Benchmark Contributors ###

  * [Muhammad Saleem](https://sites.google.com/site/saleemsweb/) (AKSW, University of Leipzig)
  * [Ali Hasnain](https://www.deri.ie/users/ali-hasnain/) (INSIGHT @ NUI Galway)
  * [Axel-Cyrille Ngonga Ngomo](http://aksw.org/AxelNgonga.html) (AKSW, University of Leipzig)
  * [Aftab Iqbal](http://www.deri.ie/users/aftab-iqbal/) (INSIGHT @ NUI Galway)
  * [Shanmukha Sampath](http://ts-eurotrain.eu/index.php/training-programme/fellows/sampath) (Democritus University of Thrace, Alexandroupoli, Greece)
  * [Bade Iriabho](https://sites.google.com/a/mathbiol.org/badeiriabho/) (University of Alabama at Birmingham)
  * [Jonas S. Almeida](http://jonasalmeida.info/) (University of Alabama at Birmingham)
  * [Helena F. Deus](http://lenadeus.info/) (Foundation Medicine)
  * [Sarven Capadisli ](http://csarven.ca)
  * [Maulik Kamdar](http://maulik-kamdar.com/) (Standford University)

We are especially thankful to Helena Deus (Foundations Medicine,  Cambridge, MA, USA) and Shanmukha Sampath (Democritus University of Thrace, Alexandroupoli, Greece)
for providing real use case large data queries and useful discussions regarding large data sets selection. We are also thankful to Jonas S. Almeida (University of Alabama at Birmingham), Bade Iriaboho (University of Alabama at Birmingham), Sarven Capadisli, Maulik Kamdar (Standford University), and Aftab Iqbal (INSIGHT @ NUI Galway) for their contributions. Finally, we are very much thankful to Andreas Schwarte (fluid Operations, Germany), Maria-Esther Vidal ( Universidad Simón Bolívar), Olaf Görlitz (University Koblenz, Germany) and Gabriela Montoya (Nantes M´etropole) for all their email conversations, feedbacks, and explanations.



