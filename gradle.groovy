def call(){
  
  script {
  	stage('gradle'){
        def foo = "foo"
        script {
        	stage('build & test') {
            env.paso_fallido = 'Build y test'
            	sh 'gradfle clean build;'
              echo $env.paso_fallido
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