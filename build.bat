@echo off
echo ===========================
echo Compilando o projeto com Maven...
cd /d "%~dp0"
mvn clean package

if errorlevel 1 (
    echo [ERRO] A compilacao falhou.
    exit /b 1
)

echo [OK] Build concluido com sucesso.
