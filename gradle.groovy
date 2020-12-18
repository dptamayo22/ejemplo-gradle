def call(){
  
  script {
  	stage('gradle'){
        script {
        	stage('build & test') {
            env.paso_fallido = 'Build y test'
            	sh 'gradfle clean build;'
            }
            stage('sonar') {
              env.paso_fallido = 'sonar'
              def scannerHome = tool 'sonar';
    			    withSonarQubeEnv('sonar') {
      				  sh "${scannerHome}/bin/sonar-scanner -Dsonar.projectKey=ejemplo-gradle -Dsonar.java.binaries=build"
    			    }
            }
            stage('run') {
              env.paso_fallido = 'run'
                sh 'nohup bash gradlew bootRun &'
            }
        }
    }
  }

}

return this;