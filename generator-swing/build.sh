#!/usr/bin/env bash
if [ ! -d "bin" ]; then
    mkdir -p bin/config
fi
cd ..
touch install.log
mvn install -Dmaven.test.skip > install.log
install_result=$(cat install.log | tail -10)
if [[ $install_result =~ "BUILD SUCCESS" ]];then
      cd generator-swing
      mvn assembly:assembly -Dmaven.test.skip > install.log
      install_result=$(cat install.log | tail -10)
      if [[ $install_result =~ "BUILD SUCCESS" ]];then
         cp -R src/main/java/resources/* bin
         cp target/hsweb-generator-jar-with-dependencies.jar bin/hsweb-generator.run.jar
         echo "构建成功"
         else
       cat install.log
      fi
   else
       cat install.log
fi