# LargeRDFBench

LargeRDFBench is a comprehensive benchmark encompasses real data and real queries (i.e., showing typical requests) of varying complexities, suite for testing and analyzing both the efficiency and effectiveness of federated query processing over multiple SPARQL endpoints. 



### Benchmark Datasets Statistics

In the following we provide information about the datasets used in LargeRDFBench along with download links, both for data-dumps and Virtuso-7.10 SPARQL endpoints. 


| *Dataset*   | *#Triples* | *#Distinct Subjects* | *#Distinct Predicates* | *#Distinct Objects* | *#Classes* | *#Links* | *Structuredness* |
|-------------|------------|----------------------|------------------------|---------------------|------------|----------|-------------------------------|
|LinkedTCGA-M|415030327|83006609|6|166106744|1|-|1|
|LinkedTCGA-E|344576146|57429904|7|84403422 |1|-|1|
|LinkedTCGA-A|35329868|5782962|383|8329393|23|251.3k|0.998|
|ChEBI|4772706|50477|28|772138 |1|-| 0.340|
|DBPedia-Subset|42849609|9495865|1063|13620028|248|65.8k|0.196|
|DrugBank|517023|19693|119|276142 |8|10.8k|0.726|
|Geo Names|107950085|7479714|26|35799392|1|118k|0.518|
|Jamendo|1049647|335925|26|440686 |11|1.7k|0.961|
|KEGG|1090830|34260|21|939258 |4|1.3k|0.919|
|Linked MDB|6147996|694400|222|2052959 |53|63.1k |0.729|
|New York Times|335198|21666|36|191538|2|31.7k|0.731|
|Semantic Web Dog Food|103595|11974|118|37547|103|2.3k|0.426|
|Affymetrix|44207146|1421763|105|13240270|3|246.3k|0.506|
| *Total* | *1003960176* | *165785212* | *2160* | *326209517* | *459* | *792.3k* | *Avg. 0.696*|

Duan et al. " Apples and Oranges: A Comparison of RDF Benchmarks and Real RDF Datasets" introduced the notion of structuredness or choerence, which indicates
whether the instances in a dataset have only a few or all attributes of their
types set. They show that artificial datasets are typically highly structured and
“real” datasets are less structured. 
<font color="red">
The complete details along with type coverages can be found [ here](http://goo.gl/XpSSPN).  The LargeRDFBench java utility to calculate the dataset structuredness can be found [ here](http://goo.gl/TgVg2G) along with usage examples. </font>

###Datasets Availability 

All the datasets and corresponding virtuoso SPARQL endpoints can be downloaded from the links given below. For SPARQL endpoint federation systems, we strongly recommend to directly download the endpoints as some of the datadumps are quite big and require a lot of upload time. You may start a SPARQL endpoint from bin/start.bat (for windows) and bin/start_virtuoso.sh (for linux). Please note that LinkedTCGA-M(Mehtylation), LinkedTCGA-E(Exon),  LinkedTCGA-A(All others), and DBpedia-subset are subsets of the live SPARQL endpoints. Further, the TCGA live SPARQL endpoints are not aligned with Affymetrix, Drugbank, and DBpedia. 

| *Dataset*  | *Data-dump*  | *Windows Endpoint*  | *Linux Endpoint*  | *Local Endpoint Url*  | *Live Endpoint Url*|
|------------|--------------|---------------------|-------------------|-----------------------|--------------------|
|[LinkedTCGA-M] (http://tcga.deri.ie/)  |[Download] (https://drive.google.com/file/d/0B_MUFqryVpByQ0J2NFAtNVlzMUk/edit?usp=sharing/) | [Download] (https://drive.google.com/file/d/0B_MUFqryVpByNDRLQkhucXE2U00/edit?usp=sharing/)  | [ Download](https://drive.google.com/file/d/0B1tUDhWNTjO-ako3bHhwY0lVTm8/edit?usp=sharing/) | your.system.ip.address:8887/sparql |http://digital3.path.uab.edu/sparql |
| [LinkedTCGA-E] (http://tcga.deri.ie/) |[Download](https://drive.google.com/file/d/0B_MUFqryVpByOGdJWU8xNXR0aUE/edit?usp=sharing/) | [Download](https://drive.google.com/file/d/0B_MUFqryVpByNjd4TjVGS1dRbnc/edit?usp=sharing/ ) | [Download](https://drive.google.com/file/d/0B1tUDhWNTjO-RE5FaHp1eXFSenM/edit?usp=sharing/ ) | your.system.ip.address:8888/sparql | http://digital2.path.uab.edu/sparql|
| [LinkedTCGA-A] (http://tcga.deri.ie/)  |[Download](https://drive.google.com/file/d/0B_MUFqryVpByd2FVQ2gzOXhIemc/edit?usp=sharing/ ) | [Download](https://drive.google.com/file/d/0B_MUFqryVpBydUpVWjBMX2FGNEE/edit?usp=sharing/) | [Download](https://drive.google.com/file/d/0B_MUFqryVpByYXdtbGhNYjdCZ3M/edit?usp=sharing/ ) |your.system.ip.address:8889/sparql | http://digital.path.uab.edu/sparql|
| [ ChEBI](https://www.ebi.ac.uk/chebi/) |[Download](https://drive.google.com/file/d/0B1tUDhWNTjO-Vk81dGVkNVNuY1E/edit?usp=sharing/ )| [Download](https://drive.google.com/file/d/0B1tUDhWNTjO-TUR6RF9jX2xoMFU/edit?usp=sharing/)|[Download](https://drive.google.com/file/d/0B1tUDhWNTjO-Wk5LeHBzMUd3VHc/edit?usp=sharing )|your.system.ip.address:8890/sparql | http://chebi.bio2rdf.org/sparql |
| [DBPedia-Subset] (http://DBpedia.org/) |[Download](https://drive.google.com/file/d/0B1tUDhWNTjO-QWk5MVJud3cxUXM/edit?usp=sharing/ )|  [Download](https://drive.google.com/file/d/0B1tUDhWNTjO-WjNkZEZrTTZzbW8/edit?usp=sharing/)|[Download](https://drive.google.com/file/d/0B1tUDhWNTjO-OEgyXzBUVmlMQlk/edit?usp=sharing )|your.system.ip.address:8891/sparql |http://dbpedia.org/sparql |
| [ DrugBank](http://www.drugbank.ca/)|[Download](https://drive.google.com/file/d/0B1tUDhWNTjO-cVp5QV9VUWRuYkk/edit?usp=sharing/ ) | [Download](https://drive.google.com/file/d/0B1tUDhWNTjO-QmMyOE9RWV9oNHM/edit?usp=sharing/ )| [Download](https://drive.google.com/file/d/0B1tUDhWNTjO-U0V5Y0xDWXhzam8/edit?usp=sharing/ )|your.system.ip.address:8892/sparql | http://wifo5-04.informatik.uni-mannheim.de/drugbank/sparql|
| [Geo Names](http://www.geonames.org/) |[Download](https://drive.google.com/file/d/0B1tUDhWNTjO-WEZZb2VwOG5vZkU/edit?usp=sharing/ ) | [Download](https://drive.google.com/file/d/0B1tUDhWNTjO-VC1HWmhBMlFncWc/edit?usp=sharing/ ) | [Download](https://drive.google.com/file/d/0B_MUFqryVpByd3hJcHBPeHZhejA/edit?usp=sharing/ ) |your.system.ip.address:8893/sparql | http://factforge.net/sparql|
| [Jamendo](http://dbtune.org/jamendo/ ) |[Download](https://drive.google.com/file/d/0B1tUDhWNTjO-cWpmMWxxQ3Z2eVk/edit?usp=sharing/ ) | [Download](https://drive.google.com/file/d/0B1tUDhWNTjO-YXV6U0ZzLUF0S0k/edit?usp=sharing/ ) | [Download](https://drive.google.com/file/d/0B1tUDhWNTjO-V3JMZjdfRkZxLUU/edit?usp=sharing/ ) |your.system.ip.address:8894/sparql  | http://dbtune.org/jamendo/sparql/|
| [KEGG](http://www.genome.jp/kegg/) |[Download](https://drive.google.com/file/d/0B1tUDhWNTjO-TUdUcllRMGVJaHM/edit?usp=sharing/ ) | [Download] (https://drive.google.com/file/d/0B1tUDhWNTjO-c1BNQ0dVWTVkUEU/edit?usp=sharing/ )| [Download](https://drive.google.com/file/d/0B1tUDhWNTjO-R1dKbDlHNXZ6blk/edit?usp=sharing/ ) |your.system.ip.address:8895/sparql |http://cu.kegg.bio2rdf.org/sparql |
| [Linked MDB](http://linkedmdb.org/) |[Download](https://drive.google.com/file/d/0B1tUDhWNTjO-bU5VN25NLXZXU0U/edit?usp=sharing/ ) | [Download] (https://drive.google.com/file/d/0B1tUDhWNTjO-eXpVSjd2Y25PaVk/edit?usp=sharing/ )| [Download](https://drive.google.com/file/d/0B1tUDhWNTjO-NjVTVERvajJUcGc/edit?usp=sharing/) |your.system.ip.address:8896/sparql |http://www.linkedmdb.org/sparql |
| [New York Times](http://data.nytimes.com/) |[ Download](https://drive.google.com/file/d/0B1tUDhWNTjO-dThoTm9DSmY4Wms/edit?usp=sharing/) | [Download](https://drive.google.com/file/d/0B1tUDhWNTjO-VDhmNWJmZVcybm8/edit?usp=sharing/ ) | [Download](https://drive.google.com/file/d/0B1tUDhWNTjO-RG9GeVdxbDR4YjQ/edit?usp=sharing/ ) |your.system.ip.address:8897/sparql | - |
| [Semantic Web Dog Food](http://data.semanticweb.org/) |[Download] (https://drive.google.com/file/d/0B1tUDhWNTjO-RjBWZXYyX2FDT1E/edit?usp=sharing/ )| [Download](https://drive.google.com/file/d/0B1tUDhWNTjO-c2h4al9VREF6bDg/edit?usp=sharing/ ) | [Download](https://drive.google.com/file/d/0B1tUDhWNTjO-UW5HaF9rekdialU/edit?usp=sharing/ ) |your.system.ip.address:8898/sparql | http://data.semanticweb.org/sparql|
| [Affymetrix](http://download.bio2rdf.org/release/2/affymetrix/affymetrix.html)| [Download](https://drive.google.com/file/d/0B1tUDhWNTjO-eHVlZ1RyVVFJQU0/edit?usp=sharing/ )| [ Download](https://drive.google.com/file/d/0B1tUDhWNTjO-RnV4SWtKelJTb0U/edit?usp=sharing/)|[Download](https://drive.google.com/file/d/0B1tUDhWNTjO-Tm9oazNUdV9Cb1k/edit?usp=sharing )|your.system.ip.address:8899/sparql |http://cu.affymetrix.bio2rdf.org/sparql |

### Datasets Connectivity

https://sites.google.com/site/saleemsweb/home/connectivity.png

### Benchmark Queries ===

LargeRDFBench comprise of a total of 32 queries (both SPARQL 1.0 and SPARQL 1.1 versions) for SPARQL endpoint federation approaches. The 32 queries are divided into three different types : 14 simple queries (from [https://code.google.com/p/fbench/ FedBench]), 10 complex queries, and 8 large data queries. The detail of these queries is given in table below. All of the queries can be downloaded from ([http://goo.gl/Z57W8P SPARQL 1.0], [http://goo.gl/hnJ8D3 SPARQL 1.1]). The queries full results can be downloaded from [http://goo.gl/8tX1Pa here].


| *Query*  | *Query Type* | *#Triple Patterns* | *#Sources Span* | *#Results* |  *#Join Vertices* | *Mean Join Vertex Degree* | *Mean Triple Pattern Selectivity* |  *Used SPARQL Clauses* | 
| --- | --- | ---| --- | --- | --- |---- | ---- | ---- |
| S1 | Simple | 3 | 2 | 90 | 1 | 2 | 0.333334927 | UNION |
| S2 | Simple | 3 | 2 | 1 | 2  |2|0.007391659| X |
| S3 | Simple | 5 | 5 | 2 | 2  |3|0.008889571| X  |
| S4 | Simple | 5 | 5 | 1 | 5  |2|0.019295158|X|
| S5 | Simple | 4 | 5 | 2 | 3  |2|0.006169376|X|
| S6 | Simple | 4 | 4 | 11 | 3 |2|0.019395937|X|
| S7 | Simple | 4 | 5 | 1 | 3  |2|0.020198747|X|
| S8 | Simple | 2 | 2 | 1159 |0|NA|0.00112084| UNION|
| S9 | Simple | 3 | 4 | 333 |1 |2|0.3335448| UNION|
| S10 | Simple | 5 | 2 | 9054 |3 |2.33|0.016010968|X|
| S11 | Simple | 7 | 2 | 3 | 4|2.5|0.006628492|X|
| S12 | Simple | 6 | 3 | 393 | 4|2.25|0.01217423|X|
| S13 | Simple | 5 | 3 | 28 | 3|2.33|0.014815076|X|
| S14 | Simple | 5 | 3 | 1620 | 3|2|0.012452045|OPTIONAL|
| C1 | Complex | 8 | 5 | 1000 | 4|2.5|0.010779222| DISTINCT, FILTER, OPTIONAL, LIMIT |
| C2 | Complex | 8 | 5 | 4 | 4|2.25|0.00972466| OPTIONAL, FILTER|
| C3 | Complex | 8 | 3 | 9 | 4|2.25|0.020214493| DISTINCT, OPTIONAL|
| C4 | Complex | 12 | 8 | 50 | 2|6|0.012412695| DISTINCT, OPTIONAL, LIMIT|
| C5 | Complex | 8 | 8 | 500 | 5|2.4|0.018655228| FILTER, LIMIT|
| C6 | Complex | 9 | 2 | 148 | 5|2.8|0.022971132| ORDER BY|
| C7 | Complex | 9 | 2 | 112 | 6|2.33|0.014815749|DISTINCT, OPTIONAL|
| C8 | Complex | 11 | 3 | 3067 | 4|3.25|0.012449596|DISTINCT, OPTIONAL|
| C9 | Complex | 9 | 3| 100 | 4|2.75|0.01107262| OPTIONAL, ORDER BY, LIMIT|
| C10 | Complex | 10 | 3 | 102 | 5|2.8|0.002712048| DISTINCT|
| L1 | Large Data | 6| 3 | 227192| 4|2|0.19222037| UNION |
| L2 | Large Data | 6 | 3 | 152899| 2|3.5|0.286786739|DISTINCT, FILTER|
| L3 | Large Data | 7 | 3 | 257158 | 3|3|0.245822714| FILTER, ORDER BY|
| L4 | Large Data | 8 | 4 | 397204| 4|2.5|0.305675513|UNION, FILTER, REGEX|
| L5 | Large Data | 11 | 4 | 190575 | 5|3|0.485882296|FILTER|
| L6 | Large Data | 10 | 4 | 282154| 5|2.8|0.349528975|FILTER, DISTINCT|
| L7 | Large Data | 5 | 4 | 80460 | 3|2.33|0.20069629|DISTINCT, FILTER|
| L8 | Large Data | 8 | 3 | 306705 | 4|2.5|0.278093563|UNION, FILTER|

<font color="red">
Further advanced queries features can be found [http://goo.gl/eeW5W0 here] and discussed in the LargeRDFBench paper. The mean triple pattern selectivities along with complete details, for all of the LargeRDFBench queries can be found [http://goo.gl/fDNXj9 here].
The LargeRDFBench java utility to calculate all these queries features can be found [http://goo.gl/TgVg2G here] along with usage examples.</font>

### Usage Information

|<th colspan="7" style="text-align: center;">LargeRDFBench SPARQL Endpoint Federation Queries ([http://goo.gl/Z57W8P SPARQL 1.0], [http://goo.gl/hnJ8D3 SPARQL 1.1]), Complete Queries [http://goo.gl/8tX1Pa Results] |

In the following we explain how one can setup the LargeRDFBench evaluation framework and measure the performance of the federation engine. 

### SPARQL Endpoints Setup 
 # The first step is to download the SPARQL endpoints (portable Virtuoso SAPRQL endpoints from second table above) on different machines, i.e., computers. Best would be one SPARQL endpoint per machine. Therefore, you need a total of 13 machines. However, you can start more than one SPARQL endpoints per machine. 
 # The next step is to start the SPARQL endpoint from bin/start.bat (for windows) or bin/start_virtuoso.sh (for Linux). Make a list of the 13 SPARQL endpoints URL's ( required as input for index-free SPARQL query federation engines, i.e., FedX). It is important to note that index-assisted federation engines (e.g., SPLENDID, DARQ, ANAPSID) usually stores the endpoint URL's in its index. The local SPARQL endpoints URL's are given above in second table. 

### Running SPARQL Queries 
Provides the list of SPARQL endpoints URL's, and a LargeRDFBench query to the underlying federation engine as input and calculate the LargeRDFBench metrics (explained next). The query evaluation start-up files for the selected systems (which you can checkout from https://bigrdfbench.googlecode.com/svn/trunk/) are given below. 

----------FedX-original-----------------------

package : package org.aksw.simba.start;

File:QueryEvaluation.java

----------FedX-HiBISCuS-----------------------

package : package org.aksw.simba.fedsum.startup;

File:QueryEvaluation.java

----------SPLENDID-original-----------------------

package : package de.uni_koblenz.west.evaluation;

File:QueryProcessingEval.java

----------SPLENDID-HiBISCuS-----------------------

package : package de.uni_koblenz.west.evaluation;

File:QueryProcessingEval.java

----------ANAPSID-----------------------

Follow the instructions given at https://github.com/anapsid/anapsid to configure the system and then use anapsid/ivan-scripts/runQuery.sh to run a query.

==== Running SPARQL 1.1 Queries ====
Both ANAPSID, FedX provides support for SPARQL 1.1 queries. The procedure for running SPARQL 1.1 queries on these two systems remain the same. You can also directly run SPARQL 1.1 queries of LargeRDFBench from SPARQL endpoint online interface (see Local endpoints URL's from second table given above). 

While running SPARQL 1.1 federation queries with online interface of Virtuoso SPARQL endpoint, you may encounter the following error

<font color="red">Virtuoso 42000 Error SQ200: Must have select privileges on view DB.DBA.SPARQL_SINV_2 </font>

You can solve this problem by opening Virtuoso conductor from http://your.system.ip.address:portno/conductor/isql.vspx (e.g., http://localhost:8888/conductor/isql.vspx). Type both user id and password as "dba". Once login, execute the following two commands. 

<font color="red">grant select on "DB.DBA.SPARQL_SINV_2" to "SPARQL";

grant execute on "DB.DBA.SPARQL_SINV_IMP" to "SPARQL"; </font>

You should be able to run all of the benchmark SPARQL 1.1 queries by using online virtuoso online query interface. Please dont set the default named graph at virtuoso online query interface, otherwise, you may get no results. 


#### How to calculate LargeRDFBench metrics? 
LargeRDFBench makes use of 6 -- #ASK, #TP. Sources, Source selection time, Query runtime, Results completeness, Results correctness -- main metrics (See paper for details). The first 4 can directly be computed from the source code (checkout the selected systems to see how we calculated these 4 metrics) of the underlying federation engine.  While for the later 2, we provided a  [http://goo.gl/rO2yvb java tool] which computes the precision, recall, F1-score of the results retrieved by the federation engine for a given benchmark query. 




###Evaluation Results and Runtime Errors
 
We have compared 5 - [https://www.mpi-inf.mpg.de/~khose/publications/ISWC2011.pdf FedX], [http://ceur-ws.org/Vol-782/GoerlitzAndStaab_COLD2011.pdf SPLENDID], [http://link.springer.com/chapter/10.1007%2F978-3-642-25073-6_2#page-1 ANAPSID], [http://svn.aksw.org/papers/2014/HiBISCuS_ESWC/public.pdf FedX+HiBISCuS], [http://svn.aksw.org/papers/2014/HiBISCuS_ESWC/public.pdf SPLENDID+HiBISUCuS] - state-of-the-art SPARQL endpoint federation systems with LargeRDFBench. Our complete evaluation results can be downloaded from [http://goo.gl/d7LxPN  here] and the runtime errors thrown by the federation systems can be downloaded from [https://drive.google.com/file/d/0BzemFAUFXpqOUUtvTDBUR2JMUms/edit?usp=sharing here]. 
 

###SPARQL Endpoints Specifications
Following are the specifications of the machines used in the evaluation to host SPARQL endpoints. 

https://sites.google.com/site/saleemsweb/home/sparqlspecs.png



###Benchmark Contributors

  * [ Muhammad Saleem](https://sites.google.com/site/saleemsweb/) (AKSW, University of Leipzig) 
  * [Ali Hasnain] (https://www.deri.ie/users/ali-hasnain/)(INSIGHT @ NUI Galway)
  *  Axel-Cyrille Ngonga Ngomo]([http://aksw.org/AxelNgonga.html) (AKSW, University of Leipzig)
  * [Aftab Iqbal] (http://www.deri.ie/users/aftab-iqbal/ )(INSIGHT @ NUI Galway)
  * [ Shanmukha Sampath](http://ts-eurotrain.eu/index.php/training-programme/fellows/sampath) (Democritus University of Thrace, Alexandroupoli, Greece)
  * [Bade Iriabho] (https://sites.google.com/a/mathbiol.org/badeiriabho/ )(University of Alabama at Birmingham)
  * [ Jonas S. Almeida](http://jonasalmeida.info/) (University of Alabama at Birmingham)
  * [ Helena F. Deus](http://lenadeus.info/) (Foundation Medicine)
  * [Sarven Capadisli ](http://csarven.ca )
  * [ Maulik Kamdar](http://maulik-kamdar.com/) (Standford University)
 
We are especially thankful to Helena Deus (Foundations Medicine,  Cambridge, MA, USA) and Shanmukha Sampath (Democritus University of Thrace, Alexandroupoli, Greece) 
for providing real use case large data queries and useful discussions regarding large data sets selection. We are also thankful to Jonas S. Almeida (University of Alabama at Birmingham), Bade Iriaboho (University of Alabama at Birmingham), Sarven Capadisli, Maulik Kamdar (Standford University), and Aftab Iqbal (INSIGHT @ NUI Galway) for their contributions. Finally, we are very much thankful to Andreas Schwarte (fluid Operations, Germany), Maria-Esther Vidal ( Universidad Simón Bolívar), Olaf Görlitz (University Koblenz, Germany) and Gabriela Montoya (Nantes M´etropole) for all their email conversations, feedbacks, and explanations. 	

<g:plusone size="large"></g:plusone> 	

			
