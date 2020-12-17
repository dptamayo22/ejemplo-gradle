def call(){
  
  script{

  	stage('Compile') {
    	sh './mvnw clean compile -e'
    }
    stage('Unit') {
        sh './mvnw clean test -e'
    }
    stage('Jar') {
        sh './mvnw clean package -e'
    }
    stage('Run') {
        sh 'nohup bash ./mvnw spring-boot:run &'
    }
    stage('Test') {
        sleep(time: 10, unit: "SECONDS")
        sh 'curl -X GET "http://localhost:8081/rest/mscovid/test?msg=testing"'
    }
  }
}
return this;