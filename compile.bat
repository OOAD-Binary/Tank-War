IF NOT EXIST bin (mkdir bin)
javac -d bin model/*.java
javac -d bin view/*.java
javac -d bin controller/*.java
javac -d bin Main.java