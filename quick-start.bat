@echo off
echo [INFO] ȷ��Ĭ��JDK�汾ΪJDK6.0�����ϰ汾,������JAVA_HOME.

set MVN=mvn
set ANT=ant
set MAVEN_OPTS=%MAVEN_OPTS% -XX:MaxPermSize=128m

rem if exist "tools\maven\apache-maven-3.0.3\" set MVN="%cd%\tools\maven\apache-maven-3.0.3\bin\mvn.bat"
rem if exist "tools\ant\apache-ant-1.8.2\" set ANT="%cd%\tools\ant\apache-ant-1.8.2\bin\ant.bat"
echo Maven����Ϊ%MVN%
echo Ant����Ϊ%ANT%

echo [Step 1] ����H2���ݿ�.
cd tools/h2
start "H2" %MVN% exec:java
cd ..\..\

echo [Step 2] Ϊmvc-basic ��ʼ�����ݿ�, ����Jetty.
cd examples\mvc-basic
call %ANT% -f bin/db/build.xml init-db 
if errorlevel 1 goto error
start "mvc-basic" %MVN% jetty:run -Djetty.port=8080
cd ..\..\

echo [INFO] SpringSide4.0 �����������.
echo [INFO] �ɷ���������ʾ��ַ:
echo [INFO] http://localhost:8080/mvc-basic

goto end
:error
echo "�д�����"
:end
pause