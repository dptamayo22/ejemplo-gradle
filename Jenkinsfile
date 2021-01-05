pipeline {
    agent any
    options {
      timeout(time: 120, unit: 'SECONDS') 
    }
    stages {
        stage('Pipeline') {
            steps {
                script {
                    figlet 'palabra'
                    stage('build & test') {
                        sh 'env'
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
                    	nexusPublisher nexusInstanceId: 'nexus', nexusRepositoryId: 'test-nexus', packages: [[$class: 'MavenPackage', mavenAssetList: [[classifier: '', extension: 'jar', filePath: 'build/libs/DevOpsUsach2020-0.0.1.jar']], mavenCoordinate: [artifactId: 'DevOpsUsach2020', groupId: 'com.devopsusach2020', packaging: 'jar', version: '0.0.1']]]
                    }
                }
            }
        }
    }
}
