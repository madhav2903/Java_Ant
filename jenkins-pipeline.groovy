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

          steps('Sonarqube Analysis') {
        
              //sh "ant sonar -v"
              //sh "ant sonar -Dsonar.login=yourAuthenticationToken"
             // sh "antlib:org.sonar.ant:sonar"

        snykSecurity(

          snykInstallation: 'snyk@latest',

         snykTokenId: 'ab763548-654a-4eb2-b9a5-30a0368044b0',

          // place other parameters here

        )
                
          }
       }
            

            
        

        stage('Deploy') {
                
           steps {
                   deploy adapters: [tomcat9(credentialsId: 'TomJen', path: '', url: 'http://localhost:8081// opt/tomcat/webapps/')], contextPath: 'null', war: '**/*.jar'
                    
          }

       }

        
    }
}
