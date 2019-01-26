# TCXCorrector

Utility to correct invalid TCX files exported from Strava. Because the ordering of some elements is wrong
Garmin Connect will choke on them while importing. This utility fixes that.

It also trims the leading whitespace which may be present in the file and incorrect values of HRM which may cause issues.

# Usage

Build using:

> mvn package

(Requires Maven 3 and Java SDK to be installed)

Run with:

> java -jar target/tcxcorrector-1.0-SNAPSHOT-jar-with-dependencies.jar [TCX_FILE] [OUTPUT_FILE]

