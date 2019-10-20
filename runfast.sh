#!/bin/bash
mvn package
java -jar target/huffman-1.0.jar compress docs/testes/teste2.txt destiny/rato.edz destiny/rato.edt
java -jar target/huffman-1.0.jar extract destiny/rato.edz destiny/rato.edt destiny/rato.txt