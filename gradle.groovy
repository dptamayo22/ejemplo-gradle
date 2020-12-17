def call(){
  
  script {
  	stage('gradle'){
        script {
        	stage('build & test') {
                ${myVariable} = 'gfds'
            	sh 'gradfle clean build;'
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
        }
    }
  }

}

return this;