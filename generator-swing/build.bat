cd ..
mvn install
cd generator-swing
mvn assembly:assembly
mkdir bin
copy src\main\java\resources bin
copy target\hsweb-generator-jar-with-dependencies.jar bin\hsweb-generator.run.jar
