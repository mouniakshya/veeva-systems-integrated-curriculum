@echo off
echo =======================================================
echo Starting E-Commerce Web Server on http://localhost:8088
echo =======================================================
if not exist "bin" mkdir "bin"
dir /s /b src\*.java > sources.txt
javac -encoding UTF-8 -d bin @sources.txt
del sources.txt
echo Opening browser...
start http://localhost:8088
java -cp bin com.ecommerce.web.EcommerceWebServer 8088
pause
