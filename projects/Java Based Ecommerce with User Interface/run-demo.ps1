Write-Host "Compiling and Running E-Commerce Module Simulation..." -ForegroundColor Cyan
if (!(Test-Path "bin")) { New-Item -ItemType Directory -Path "bin" | Out-Null }
$sources = (Get-ChildItem -Path "src" -Recurse -Filter "*.java").FullName
javac -encoding UTF-8 -d bin $sources
if ($LASTEXITCODE -eq 0) {
    java -ea -cp bin com.ecommerce.demo.EcommerceSimulationDemo
} else {
    Write-Host "Compilation failed with code $LASTEXITCODE" -ForegroundColor Red
}
