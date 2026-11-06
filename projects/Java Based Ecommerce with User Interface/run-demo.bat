@echo off
echo =======================================================
echo Compiling and Running E-Commerce Module Simulation Demo
echo =======================================================
if not exist "bin" mkdir "bin"
dir /s /b src\*.java > sources.txt
javac -encoding UTF-8 -d bin @sources.txt
del sources.txt
java -ea -cp bin com.ecommerce.demo.EcommerceSimulationDemo
pause
