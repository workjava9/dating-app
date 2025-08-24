set -euo pipefail
BASE_URL=${BASE_URL:-http://localhost:8080}

echo "Registering users..."
A=$(curl -s -X POST "$BASE_URL/api/auth/register" -H 'Content-Type: application/json' \
  -d '{"email":"a@example.com","password":"pass","name":"Alice"}')
B=$(curl -s -X POST "$BASE_URL/api/auth/register" -H 'Content-Type: application/json' \
  -d '{"email":"b@example.com","password":"pass","name":"Bob"}')

AT=$(echo "$A" | sed -n 's/.*"token":"\([^"]*\)".*/\1/p')
BT=$(echo "$B" | sed -n 's/.*"token":"\([^"]*\)".*/\1/p')

echo "Tokens extracted."
echo "Simulate swipe A->B like"
curl -s -X POST "$BASE_URL/swipes" -H "Authorization: Bearer $AT" -H 'Content-Type: application/json' \
  -d '{"toUserId":"00000000-0000-0000-0000-000000000000","like":true}' >/dev/null || true
echo "Note: toUserId must be real UUID from DB; run manual match flow using requests.http."
echo "Done."
