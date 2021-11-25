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

stage("Quality Gate"){
  timeout(time: 1, unit: 'HOURS') { 
    def qg = waitForQualityGate()
    if (qg.status != 'OK') {
      error "Pipeline aborted due to quality gate failure: ${qg.status}"
    }
  } 
}


