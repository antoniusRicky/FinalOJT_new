node {
  stage('SCM') {
    checkout scm
  }
  
  stage('build') {
    bat "mvn clean package -Dmaven.test.skip=true"
  }
  
  stage('SonarQube Analysis') {
    def scannerHome = tool 'sonarqube';
    withSonarQubeEnv() {
      bat "${scannerHome}/bin/sonar-scanner"
    }
  }
}


