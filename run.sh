#!/bin/sh
set -e
mkdir -p out data
javac -d out src/org/uob/a2/*.java src/org/uob/a2/engine/*.java src/org/uob/a2/parser/*.java src/org/uob/a2/model/*.java
java -cp out org.uob.a2.Main
