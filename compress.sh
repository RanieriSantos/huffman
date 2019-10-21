#!/bin/bash
export JAVA_TOOL_OPTIONS=-Dfile.encoding=ISO-8859-15
mvn package
java -jar target/huffman-1.0.jar compress docs/lolla.txt destiny/com.edz destiny/map.edt