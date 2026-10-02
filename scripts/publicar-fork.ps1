# Cria fork no GitHub (via gh), configura origin e envia a branch main.
# Execute na raiz do projeto: .\scripts\publicar-fork.ps1

$ErrorActionPreference = "Stop"
$Root = Split-Path -Parent (Split-Path -Parent $MyInvocation.MyCommand.Path)
Set-Location $Root

$Gh = Join-Path $Root ".tools\bin\gh.exe"
if (-not (Test-Path $Gh)) {
    Write-Host "GitHub CLI nao encontrado em .tools\bin\gh.exe" -ForegroundColor Red
    exit 1
}

& $Gh auth status 2>$null
if ($LASTEXITCODE -ne 0) {
    Write-Host "Faca login no GitHub (abrira codigo no navegador):" -ForegroundColor Yellow
    & $Gh auth login -h github.com -p https -w
}

Write-Host "Criando fork de merito-es/vaga-junior na sua conta..." -ForegroundColor Cyan
& $Gh repo fork merito-es/vaga-junior --clone=false
if ($LASTEXITCODE -ne 0) {
    Write-Host "Falha ao criar fork (talvez ja exista). Verifique no GitHub." -ForegroundColor Yellow
}

$userRepo = & $Gh api user -q .login
$originUrl = "https://github.com/$userRepo/vaga-junior.git"

if (git remote get-url origin 2>$null) {
    git remote set-url origin $originUrl
} else {
    git remote add origin $originUrl
}

Write-Host "Enviando commits para $originUrl ..." -ForegroundColor Cyan
git push -u origin main

Write-Host ""
Write-Host "Pronto! Envie este link na vaga:" -ForegroundColor Green
Write-Host "https://github.com/$userRepo/vaga-junior" -ForegroundColor Green
