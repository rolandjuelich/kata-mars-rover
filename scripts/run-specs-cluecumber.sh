#!/bin/bash
set -ev
mvn clean test
mvn cluecumber-report:reporting -Pcluecumber
open target/report/html/index.html
