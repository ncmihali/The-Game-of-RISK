#!/bin/bash

./gradlew build || exit 1
./gradlew cloverAggregateReport || exit 1
ls -l
scripts/coverage_summary.sh
ls -l /
ls -l /coverage-out/
cp -r build/reports/clover/html/* /coverage-out/ || exit 1
