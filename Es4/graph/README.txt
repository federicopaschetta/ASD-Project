****COMPILAZIONE***

Compiling Folder structure
--/src
      /*.java
--/classes
      /*.class

***COMPILING***

----TO COMPILE Graph DATA STRUCTURE IN PACKAGE graph---
1) place terminal in .../src
2) javac -d ../classes graph/Graph.java

---TO COMPILE UNIT TEST CLASSES IN PACKAGE graph---
1) place terminal .../src
2) javac -d ../classes -cp '.;../junit-4.12.jar;../hamcrest-core-1.3.jar' graph/*.java 

javac -d ../classes -cp .:../junit-4.12.jar:../hamcrest-core-1.3.jar graph/*.java


***RUNNING***


---TO RUN heap/HeapJavaTestsRunner---
1) place terminal .../classes 
2) java -cp ".;../junit-4.12.jar;../hamcrest-core-1.3.jar"  graph/GraphJavaTestsRunner


java -cp .:../junit-4.12.jar:../hamcrest-core-1.3.jar  graph/GraphJavaTestsRunner

