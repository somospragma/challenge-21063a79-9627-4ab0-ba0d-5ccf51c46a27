#!/bin/bash
set -e

HEALTH_ENDPOINT="${HEALTH_ENDPOINT:-http://localhost:8080/actuator/health}"
METRICS_ENDPOINT="${METRICS_ENDPOINT:-http://localhost:8080/actuator/prometheus}"
MAX_RETRIES="${MAX_RETRIES:-30}"
RETRY_INTERVAL="${RETRY_INTERVAL:-2}"

echo "=== Health Check Script ==="
echo "Target: $HEALTH_ENDPOINT"
echo "Max retries: $MAX_RETRIES"
echo "Retry interval: $RETRY_INTERVAL seconds"
echo ""

check_health() {
    local response
    response=$(curl -sf "$HEALTH_ENDPOINT" 2>/dev/null || echo "")
    
    if [ -z "$response" ]; then
        return 1
    fi
    
    local status
    status=$(echo "$response" | grep -o '"status":"[^"]*"' | cut -d'"' -f4 || echo "UNKNOWN")
    
    if [ "$status" = "UP" ]; then
        echo "✓ Application is UP"
        echo "Response: $response"
        return 0
    else
        echo "✗ Application status: $status"
        echo "Response: $response"
        return 1
    fi
}

check_readiness() {
    local response
    response=$(curl -sf "${HEALTH_ENDPOINT}/ready" 2>/dev/null || echo "")
    
    if [ -z "$response" ]; then
        return 1
    fi
    
    local status
    status=$(echo "$response" | grep -o '"status":"[^"]*"' | cut -d'"' -f4 || echo "UNKNOWN")
    
    if [ "$status" = "UP" ]; then
        echo "✓ Application is READY"
        return 0
    else
        echo "✗ Application not ready: $status"
        return 1
    fi
}

check_liveness() {
    local response
    response=$(curl -sf "${HEALTH_ENDPOINT}/live" 2>/dev/null || echo "")
    
    if [ -z "$response" ]; then
        return 1
    fi
    
    local status
    status=$(echo "$response" | grep -o '"status":"[^"]*"' | cut -d'"' -f4 || echo "UNKNOWN")
    
    if [ "$status" = "UP" ]; then
        echo "✓ Application is ALIVE"
        return 0
    else
        echo "✗ Application not alive: $status"
        return 1
    fi
}

echo "Starting health check..."
echo ""

retry_count=0
while [ $retry_count -lt $MAX_RETRIES ]; do
    if check_health; then
        echo ""
        echo "=== Additional Checks ==="
        check_liveness || true
        check_readiness || true
        
        if curl -sf "$METRICS_ENDPOINT" >/dev/null 2>&1; then
            echo "✓ Metrics endpoint available"
        else
            echo "⚠ Metrics endpoint not available"
        fi
        
        echo ""
        echo "=== Health Check Passed ==="
        exit 0
    fi
    
    retry_count=$((retry_count + 1))
    echo "Attempt $retry_count/$MAX_RETRIES - Waiting ${RETRY_INTERVAL}s..."
    sleep $RETRY_INTERVAL
done

echo ""
echo "=== Health Check Failed ==="
echo "Application failed to become healthy after $MAX_RETRIES attempts"
exit 1