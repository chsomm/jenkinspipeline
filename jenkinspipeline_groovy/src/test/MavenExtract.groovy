package test

def xml = """<?xml version="1.0" encoding="UTF-8"?>
<metadata>
  <groupId>alignmentrule</groupId>
  <artifactId>align0</artifactId>
  <versioning>
    <release>1.1.0</release>
    <versions>
      <version>1.0.1</version>
      <version>1.1.0</version>
    </versions>
    <lastUpdated>20171206232337</lastUpdated>
  </versioning>
</metadata>"""

def version = (xml =~ "(?<=\\<release\\>)(\\s*.*\\s*)(?=\\<\\/release\\>)")

println version.find() ? version.group() : "no"

