#!/bin/bash
set -ev
mvn clean test cluecumber-report:reporting
open target/report/html/index.html
