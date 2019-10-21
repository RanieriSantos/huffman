#!/bin/bash
mvn package
java -jar target/huffman-1.0.jar compress docs/dicionario.txt destiny/com.edz destiny/map.edt