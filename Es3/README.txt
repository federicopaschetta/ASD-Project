****COMPILAZIONE***

Compiling Folder structure
--/src
      /*.java
--/classes
      /*.class

***COMPILING***

----TO COMPILE Heap DATA STRUCTURE IN PACKAGE heap---
1) place terminal in .../src
2) javac -d ../classes heap/Heap.java

---TO COMPILE UNIT TEST CLASSES IN PACKAGE heap---
1) place terminal .../src
2) javac -d ../classes -cp '.;../junit-4.12.jar;../hamcrest-core-1.3.jar' heap/*.java 

javac -d ../classes -cp .:../junit-4.12.jar:../hamcrest-core-1.3.jar heap/*.java


***RUNNING***


---TO RUN heap/HeapJavaTestsRunner---
1) place terminal .../classes 
2) java -cp '.;../junit-4.12.jar;../hamcrest-core-1.3.jar'  heap/HeapJavaTestsRunner


java -cp .:../junit-4.12.jar:../hamcrest-core-1.3.jar  heap/HeapJavaTestsRunner

