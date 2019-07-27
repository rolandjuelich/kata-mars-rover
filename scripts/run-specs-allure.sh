#!/bin/bash
set -ev
mvn clean test allure:serve -Pallure