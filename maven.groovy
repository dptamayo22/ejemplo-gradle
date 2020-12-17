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
    stage('Run') {
        sh 'nohup bash ./mvnw spring-boot:run &'
    }
  }
}
return this;