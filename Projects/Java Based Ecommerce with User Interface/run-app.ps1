Write-Host "Compiling and Starting E-Commerce Interactive CLI..." -ForegroundColor Cyan
if (!(Test-Path "bin")) { New-Item -ItemType Directory -Path "bin" | Out-Null }
$sources = (Get-ChildItem -Path "src" -Recurse -Filter "*.java").FullName
javac -encoding UTF-8 -d bin $sources
if ($LASTEXITCODE -eq 0) {
    java -cp bin com.ecommerce.ECommerceApp
} else {
    Write-Host "Compilation failed with code $LASTEXITCODE" -ForegroundColor Red
}
