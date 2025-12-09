@REM Maven Wrapper startup script for Windows
@echo off
set MAVEN_PROJECTBASEDIR=%~dp0
set MAVEN_OPTS=%MAVEN_OPTS% -Xmx512m
cd %MAVEN_PROJECTBASEDIR%
if exist "%MAVEN_PROJECTBASEDIR%\.mvn\wrapper\maven-wrapper.jar" goto runMaven
echo Downloading Maven Wrapper...
powershell -Command "& {Invoke-WebRequest -Uri 'https://repo.maven.apache.org/maven2/org/apache/maven/wrapper/maven-wrapper/3.2.0/maven-wrapper-3.2.0.jar' -OutFile '%MAVEN_PROJECTBASEDIR%\.mvn\wrapper\maven-wrapper.jar'}"
:runMaven
java -jar "%MAVEN_PROJECTBASEDIR%\.mvn\wrapper\maven-wrapper.jar" %*
