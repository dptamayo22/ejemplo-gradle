def call(){
  
  script {
  	stage('gradle'){
        script {
        	stage('build & test') {
            	sh 'gradle clean build'
            }
            stage('sonar') {
                def scannerHome = tool 'sonar';
    			withSonarQubeEnv('sonar') {
      				sh "${scannerHome}/bin/sonar-scanner -Dsonar.projectKey=ejemplo-gradle -Dsonar.java.binaries=build"
    			}
            }
            stage('run') {
                sh 'nohup bash gradlew bootRun &'
            }
            stage('rest') {
   				sh 'curl -X GET http://localhost:8081/rest/mscovid/test?msg=testing'
            }
            stage('nexus'){
                stage('nexus') {
                    nexusArtifactUploader(
                        nexusVersion: 'nexus3',
                        protocol: 'http',
                        nexusUrl: 'localhost:8081',
                        groupId: 'com.devopsusach2020',
                        version: '0.0.1',
                        repository: 'test-nexus',
                        credentialsId: 'nexus',
                        artifacts: [
                            [artifactId: 'DevOpsUsach2020',
                            classifier: '',
                            file: 'build/libs/DevOpsUsach2020-0.0.1.jar',
                            type: 'jar']
                        ]
                        )
                        
                    }

            }
        }
    }
  }

}

return this;