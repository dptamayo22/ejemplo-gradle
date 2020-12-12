# Getting Started

## OSX

### Build (compile,test,jar)
* gradle clean build

###Test Code
* gradle test

### Run Jar
* Local: gradle bootRun
* Background: nohup gradle bootRun &

### Testing Application
* curl -X GET 'http://localhost:8081/rest/mscovid/test?msg=testing'
