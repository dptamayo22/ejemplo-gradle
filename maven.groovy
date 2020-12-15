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
	stage('Nexus Upload'){
        nexusArtifactUploader(
        nexusVersion: 'nexus3',
        protocol: 'http',
        nexusUrl: 'localhost:8081',
        groupId: 'com.devopsusach2020',
        version: '1.0.1',
        repository: 'test-nexus',
        credentialsId: 'nexus',
        artifacts: [
            [artifactId: 'DevOpsUsach2020',
            classifier: '',
            file: 'build/DevOpsUsach2020-0.0.1.jar',
            type: 'jar']
        ])
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