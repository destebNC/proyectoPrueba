#!/bin/bash

echo "=========================================="
echo "    MULTI-LANGUAGE SDKs GENERATOR"
echo "=========================================="
echo

BASE_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
OPENAPI_SPEC="$BASE_DIR/src/main/resources/openAPI.yaml"

echo "OpenAPI specification: $OPENAPI_SPEC"
echo

# Check if specification exists
if [ ! -f "$OPENAPI_SPEC" ]; then
    echo "Error: OpenAPI specification not found at $OPENAPI_SPEC"
    exit 1
fi

# Check if openapi-generator-cli is installed
if ! command -v openapi-generator-cli &> /dev/null; then
    echo "Installing OpenAPI Generator CLI..."
    npm install -g @openapitools/openapi-generator-cli
fi

echo "Generating SDKs..."
echo

# TypeScript
echo "Generating TypeScript SDK..."
cd "$BASE_DIR/clients/typescript"
if [ -f "package.json" ]; then
    npm run generate
    echo "TypeScript SDK generated"
else
    echo "⚠️  Skipping TypeScript - package.json not found"
fi

# C#
echo
echo "Generating C# SDK..."
cd "$BASE_DIR/clients/csharp"
if [ -f "config.json" ]; then
    openapi-generator-cli generate \
        -i "$OPENAPI_SPEC" \
        -g csharp-netcore \
        -o . \
        -c config.json \
        --additional-properties=targetFramework=net6.0
    echo "C# SDK generated"
else
    echo "⚠️  Skipping C# - config.json not found"
fi

# PHP
echo
echo "Generating PHP SDK..."
cd "$BASE_DIR/clients/php"
if [ -f "composer.json" ]; then
    composer run generate
    echo "PHP SDK generated"
else
    echo "⚠️  Skipping PHP - composer.json not found"
fi

# Postman
echo
echo "Generating Postman collection..."
cd "$BASE_DIR/examples/postman"
if command -v openapi2postmanv2 &> /dev/null; then
    openapi2postmanv2 convert \
        --spec "$OPENAPI_SPEC" \
        --output inventory-api.postman_collection.json \
        --options folderStrategy=Tags,includeAuthInfoInExample=false
    echo "Postman collection generated"
else
    echo "⚠️  Skipping Postman - openapi2postmanv2 not installed"
    echo "   Install with: npm install -g openapi-to-postmanv2"
fi

echo
echo "=========================================="
echo "    GENERATION COMPLETED"
echo "=========================================="
echo
echo "SDKs generated in:"
echo "  • TypeScript: clients/typescript/"
echo "  • C#:         clients/csharp/"
echo "  • PHP:        clients/php/"
echo "  • Postman:    examples/postman/"
echo
echo "Ready to use!"
