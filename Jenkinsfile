// Define variable
def myVariable = "foo"

pipeline {
    agent any

    options {
      timeout(time: 120, unit: 'SECONDS') 
    }
    stages {
        stage('Pipeline') {
            steps {
                script {
                    if (params.eleccion =='gradle'){
                        echo 'gradle'
                        def ejecucion_gradle = load 'gradle.groovy'
                        ejecucion_gradle.call();

                    } else if (params.eleccion =='maven'){

                        echo 'maven'
                        def ejecucion_maven =  load 'maven.groovy'
                        ejecucion_maven.call();
                    }
                }
            }
        }
    }
    post {
        success {
         
            slackSend (channel: 'U01DD6F014K', color: '#00FF00', message: "Build Success: [Dianela Tamayo] [${env.JOB_NAME}] [${params.eleccion}]", teamDomain: 'dipdevopsusach2020', tokenCredentialId: 'slack')
        
        }
        failure {
    
            slackSend (channel: 'U01DD6F014K', color: '#FF0000', message: "Build Failure: [Dianela Tamayo] [${env.JOB_NAME}] [${params.eleccion}] stage [${myVariable}]", teamDomain: 'dipdevopsusach2020', tokenCredentialId: 'slack')
    
        }
    }
}