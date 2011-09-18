@echo off
echo [INFO] 确保默认JDK版本为JDK6.0及以上版本,已配置JAVA_HOME.

set MVN=mvn
set ANT=ant
set MAVEN_OPTS=%MAVEN_OPTS% -XX:MaxPermSize=128m

rem if exist "tools\maven\apache-maven-3.0.3\" set MVN="%cd%\tools\maven\apache-maven-3.0.3\bin\mvn.bat"
rem if exist "tools\ant\apache-ant-1.8.2\" set ANT="%cd%\tools\ant\apache-ant-1.8.2\bin\ant.bat"
echo Maven命令为%MVN%
echo Ant命令为%ANT%

echo [Step 1] 启动H2数据库.
cd tools/h2
start "H2" %MVN% exec:java
cd ..\..\

echo [Step 2] 为mvc-basic 初始化数据库, 启动Jetty.
cd examples\mvc-basic
call %ANT% -f bin/db/build.xml init-db 
if errorlevel 1 goto error
start "mvc-basic" %MVN% jetty:run -Djetty.port=8080
cd ..\..\

echo [INFO] SpringSide4.0 快速启动完毕.
echo [INFO] 可访问以下演示网址:
echo [INFO] http://localhost:8080/mvc-basic

goto end
:error
echo "有错误发生"
:end
pause