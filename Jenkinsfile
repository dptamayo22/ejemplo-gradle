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

                        def ejecucion_gradle = load 'gradle.groovy'
                        ejecucion_gradle.call();

                    } else if (params.eleccion =='maven'){

                        def ejecucion_maven =  load 'maven.groovy'
                        ejecucion_maven.call();
                }
            }
        }
    }
}