pipeline {
    agent any
    options {
      timeout(time: 120, unit: 'SECONDS') 
    }
    stages {
        stage('Pipeline') {
            steps {
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
                        
                    }
                    stage('nexus') {
                        
                    }
                }
            }
        }
    }
}