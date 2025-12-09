@echo off
echo ========================================
echo Starting Food Ordering Backend...
echo ========================================
cd /d "%~dp0"
java -jar target\*.jar
pause
