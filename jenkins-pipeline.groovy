pipeline {

    agent any

    stages {

        stage('pull-code') {

           steps {

               git credentialsId: 'Github', url: 'https://github.com/madhav2903/Java_Ant.git'

           }

       }

        stage('Build') {

            steps {

               sh 'ant -version'
               sh 'ant'

          }

        }

       // post {
        //always {
                // Archive the executable
              //  archiveArtifacts artifacts: 'build/*.jar', fingerprint: true
                
           // }
     //   }

      stage('Test') {

     //     steps('Sonarqube Analysis') {
        
       environment {
       SCANNER_HOME = tool 'SonarScanner'
    }
    steps {
    withSonarQubeEnv(credentialsId: 'SONJEN', installationName: 'sonarscanner') {
         sh '''$SCANNER_HOME/bin/sonar-scanner \
         -Dsonar.projectKey= \
         -Dsonar.projectName=projectName \
         -Dsonar.sources=src/ \
         -Dsonar.java.binaries=target/classes/ \
         -Dsonar.exclusions=src/test/java/****/*.java \
         -Dsonar.java.libraries=/var/lib/jenkins/.m2/**/*.jar \
         -Dsonar.projectVersion=${BUILD_NUMBER}-${GIT_COMMIT_SHORT}'''
       }
     }
}
   stage('SQuality Gate') {
     steps {
       timeout(time: 1, unit: 'MINUTES') {
       waitForQualityGate abortPipeline: true
       }
  }
              //sh "ant sonar -Dsonar.login=yourAuthenticationToken"
             // sh "antlib:org.sonar.ant:sonar"

      //  snykSecurity(

         // snykInstallation: 'snyk@latest',

        // snykTokenId: 'Snyk_api',
            //snykSecurity snykInstallation: 'snyk@latest', snykTokenId: 'Snyk_api'

        //  additionalArguments: '--all-projects --detection-depth=<DEPTH>'

          // place other parameters here

     //   )
                
      //    }
     //  }
            

            
        

        stage('Deploy') {
                
           steps {
                   deploy adapters: [tomcat9(credentialsId: 'TomJen', path: '', url: 'http://localhost:8081// opt/tomcat/webapps/')], contextPath: 'null', war: '**/*.jar'
                    
          }

       }

        
    }
}
