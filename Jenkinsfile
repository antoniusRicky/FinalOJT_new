pipeline {
    agent any
    
    stages {
        stage('build') {
            steps {
                bat "mvn clean package -Dmaven.test.skip=true"
            }
        }
        
        stage('SonarQube Analysis') {
            
        }
        
    }
}
