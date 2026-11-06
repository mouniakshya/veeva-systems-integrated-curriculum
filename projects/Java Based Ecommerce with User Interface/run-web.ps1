Write-Host "Compiling and Starting E-Commerce Web Server..." -ForegroundColor Cyan
if (!(Test-Path "bin")) { New-Item -ItemType Directory -Path "bin" | Out-Null }
$sources = (Get-ChildItem -Path "src" -Recurse -Filter "*.java").FullName
javac -encoding UTF-8 -d bin $sources
if ($LASTEXITCODE -eq 0) {
    Write-Host "Opening browser at http://localhost:8088..." -ForegroundColor Green
    Start-Process "http://localhost:8088"
    java -cp bin com.ecommerce.web.EcommerceWebServer 8088
} else {
    Write-Host "Compilation failed with code $LASTEXITCODE" -ForegroundColor Red
}
