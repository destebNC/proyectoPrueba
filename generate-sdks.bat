@echo off
echo ==========================================
echo     MULTI-LANGUAGE SDKs GENERATOR
echo ==========================================
echo.

set "BASE_DIR=%~dp0"
set "OPENAPI_SPEC=%BASE_DIR%src\main\resources\openAPI.yaml"

echo OpenAPI specification: %OPENAPI_SPEC%
echo.

REM Check if specification exists
if not exist "%OPENAPI_SPEC%" (
    echo Error: OpenAPI specification not found at %OPENAPI_SPEC%
    pause
    exit /b 1
)

REM Check if openapi-generator-cli is installed
openapi-generator-cli version >nul 2>&1
if %errorlevel% neq 0 (
    echo Installing OpenAPI Generator CLI...
    npm install -g @openapitools/openapi-generator-cli
)

echo Generating SDKs...
echo.

REM TypeScript
echo Generating TypeScript SDK...
cd "%BASE_DIR%clients\typescript"
if exist "package.json" (
    npm run generate
    echo TypeScript SDK generated
) else (
    echo Skipping TypeScript - package.json not found
)

REM C#
echo.
echo Generating C# SDK...
cd "%BASE_DIR%clients\csharp"
if exist "config.json" (
    openapi-generator-cli generate ^
        -i "%OPENAPI_SPEC%" ^
        -g csharp-netcore ^
        -o . ^
        -c config.json ^
        --additional-properties=targetFramework=net6.0
    echo C# SDK generated
) else (
    echo Skipping C# - config.json not found
)

REM PHP
echo.
echo Generating PHP SDK...
cd "%BASE_DIR%clients\php"
if exist "composer.json" (
    composer run generate
    echo PHP SDK generated
) else (
    echo Skipping PHP - composer.json not found
)

REM Postman
echo.
echo Generating Postman collection...
cd "%BASE_DIR%examples\postman"
openapi2postmanv2 version >nul 2>&1
if %errorlevel% equ 0 (
    openapi2postmanv2 convert ^
        --spec "%OPENAPI_SPEC%" ^
        --output inventory-api.postman_collection.json ^
        --options folderStrategy=Tags,includeAuthInfoInExample=false
    echo Postman collection generated
) else (
    echo Skipping Postman - openapi2postmanv2 not installed
    echo    Install with: npm install -g openapi-to-postmanv2
)

echo.
echo ==========================================
echo     GENERATION COMPLETED
echo ==========================================
echo.
echo SDKs generated in:
echo   • TypeScript: clients/typescript/
echo   • C#:         clients/csharp/
echo   • PHP:        clients/php/
echo   • Postman:    examples/postman/
echo.
echo Ready to use!
echo.
pause
