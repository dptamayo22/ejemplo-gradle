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
        
            slackSend (color: '#00FF00', message: "Build Success: ${user} '${env.JOB_NAME} [${env.BUILD_NUMBER}]' ${params.CHOICE} Ejecución exitosa")
        }
        failure {
            
            slackSend channel: 'U01DD6F014K', color: '#FF0000', message: [$BUILD_USER][$JOB_NAME][params.eleccion] Ejecucion fallida en stage [env.STAGE_NAME], teamDomain: 'dipdevopsusach2020', tokenCredentialId: 'slack'

        }
    }
}



