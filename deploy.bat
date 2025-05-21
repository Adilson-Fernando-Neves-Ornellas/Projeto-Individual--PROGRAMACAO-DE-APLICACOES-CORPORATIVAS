@echo off

echo ===========================
echo Executando build...
call build.bat
if errorlevel 1 (
    echo [ERRO] O build falhou. Abortando deploy.
    exit /b 1
)

set CATALINA_HOME=C:\dev\apache-tomcat-10.1.39

echo ===========================
echo Parando o Tomcat (caso esteja rodando)...
cd /d "%CATALINA_HOME%\bin"
call shutdown.bat >nul 2>&1

echo ===========================
echo Verificando se o arquivo WAR foi gerado...
cd /d "%~dp0"
if not exist ".\target\projeto_adilson.war" (
    echo [ERRO] Arquivo WAR nao encontrado: .\target\projeto_adilson.war
    exit /b 1
)

echo ===========================
echo Copiando WAR para o Tomcat...
copy ".\target\projeto_adilson.war" "%CATALINA_HOME%\webapps" /Y
if errorlevel 1 (
    echo [ERRO] Falha ao copiar o WAR para o Tomcat.
    exit /b 1
)

echo ===========================
echo Iniciando o Tomcat...
cd /d "%CATALINA_HOME%\bin"
call catalina.bat run

echo ===========================
echo [OK] Deploy concluido e finalizado com sucesso.
