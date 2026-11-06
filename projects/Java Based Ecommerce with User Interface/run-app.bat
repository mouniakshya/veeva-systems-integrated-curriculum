@echo off
echo =======================================================
echo Starting E-Commerce Interactive CLI Platform
echo =======================================================
if not exist "bin" mkdir "bin"
dir /s /b src\*.java > sources.txt
javac -encoding UTF-8 -d bin @sources.txt
del sources.txt
java -cp bin com.ecommerce.ECommerceApp
pause
