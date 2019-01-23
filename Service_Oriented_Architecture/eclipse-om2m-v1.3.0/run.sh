#!/bin/bash

cd mn-cse
rm -rf database
echo "mn database removed"
cd ../in-cse
rm -rf database
echo "in database removed"

./start.sh &
echo "in-cse started"
cd ../mn-cse
./start.sh &
echo "mn-cse started"

sleep 10

echo "opening OM2M..."
open http://localhost:8081/webpage
