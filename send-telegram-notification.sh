#!/bin/bash

# Telegram Notification Script for Mobile Test Automation
# Usage: ./send-telegram-notification.sh

# Load environment variables
if [ -f .env ]; then
    export $(cat .env | grep -v '^#' | xargs)
fi

# Get test results from Allure - only passed and failed tests
if [ -d "allure-results" ]; then
    PASSED_TESTS=$(find allure-results -name "*.json" -exec jq -r 'select(.status == "passed") | .status' {} \; 2>/dev/null | wc -l)
    FAILED_TESTS=$(find allure-results -name "*.json" -exec jq -r 'select(.status == "failed") | .status' {} \; 2>/dev/null | wc -l)
    TOTAL_TESTS=$((PASSED_TESTS + FAILED_TESTS))
else
    TOTAL_TESTS=0
    PASSED_TESTS=0
    FAILED_TESTS=0
fi

# Ensure variables are numbers
TOTAL_TESTS=$(echo "${TOTAL_TESTS:-0}" | tr -d '\n' | xargs)
PASSED_TESTS=$(echo "${PASSED_TESTS:-0}" | tr -d '\n' | xargs)
FAILED_TESTS=$(echo "${FAILED_TESTS:-0}" | tr -d '\n' | xargs)

# Calculate success rate
if [ "$TOTAL_TESTS" -gt 0 ]; then
    SUCCESS_RATE=$((PASSED_TESTS * 100 / TOTAL_TESTS))
else
    SUCCESS_RATE=0
fi

# Determine status
if [ "$TOTAL_TESTS" -eq 0 ]; then
    STATUS_COLOR="🟡"
    STATUS_TEXT="NO_TESTS"
elif [ "$FAILED_TESTS" -eq 0 ]; then
    STATUS_COLOR="🟢"
    STATUS_TEXT="SUCCESS"
else
    STATUS_COLOR="🔴"
    STATUS_TEXT="FAILED"
fi

# Build message for Telegram
MESSAGE="🚀 <b>Test Run Completed!</b>

📊 <b>Test Statistics:</b>
• <b>Total tests:</b> $TOTAL_TESTS
• <b>Passed:</b> $PASSED_TESTS ✅
• <b>Failed:</b> $FAILED_TESTS ❌
• <b>Success rate:</b> ${SUCCESS_RATE}%

🔗 <b>Links:</b>
• <b>Repository:</b> https://github.com/$GITHUB_REPOSITORY
• <b>Commit:</b> https://github.com/$GITHUB_REPOSITORY/commit/$GITHUB_SHA
• <b>Report:</b> https://$GITHUB_REPOSITORY_OWNER.github.io/$GITHUB_EVENT_REPOSITORY_NAME/

$STATUS_COLOR <b>Status:</b> $STATUS_TEXT"

# Check if Telegram credentials are provided
if [ -z "$TELEGRAM_BOT_TOKEN" ] || [ -z "$TELEGRAM_CHAT_ID" ]; then
    echo "⚠️  Telegram credentials not provided. Skipping notification."
    exit 0
fi

# Send message to Telegram
RESPONSE=$(curl -s -X POST "https://api.telegram.org/bot$TELEGRAM_BOT_TOKEN/sendMessage" \
  -H "Content-Type: application/json" \
  -d "{
    \"chat_id\": \"$TELEGRAM_CHAT_ID\",
    \"text\": \"$MESSAGE\",
    \"parse_mode\": \"HTML\",
    \"disable_web_page_preview\": false
  }")

# Check if message was sent successfully
if echo "$RESPONSE" | grep -q '"ok":true'; then
    echo "✅ Telegram notification sent successfully!"
else
    echo "❌ Failed to send Telegram notification:"
    echo "$RESPONSE"
    exit 1
fi

