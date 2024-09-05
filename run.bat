@echo off

echo Iniciando execução do projeto...

REM
echo Executando a API...
cd word-hierarchy-analyzer-api
call mvn clean install
echo Primeiro teste para saber se a API está funcionado...
call java -jar target/word-hierarchy-analyzer-0.0.1-SNAPSHOT.jar analyze --depth 2 "Eu amo papagaios" --verbose
pause

cd ..

REM
if exist word-hierarchy-analyzer-app (
    echo Executando o front-end (word-hierarchy-analyzer-app)...
    cd word-hierarchy-analyzer-app
    call npm install
    call npm run dev
) else (
    echo Diretório 'word-hierarchy-analyzer-app' não encontrado.
)

echo Projetos executando. Para parar o script, feche o terminal.
pause