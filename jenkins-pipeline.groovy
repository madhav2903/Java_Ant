pipeline {

    agent any

    stages {

        stage('pull-code') {

           steps {

               git credentialsId: 'Github', url: 'https://github.com/madhav2903/Java_Ant.git'

           }

       }

        //stage('Build') {

         //   steps {

          //      sh 'ant -version'
           //     sh 'ant'

          //  }

       // }

       // post {
        //always {
                // Archive the executable
              //  archiveArtifacts artifacts: 'build/*.jar', fingerprint: true
                
           // }
     //   }

        stage('Test') {

            steps('Snyk Analysis') {
                
               // withSonarQubeEnv('sonarserver') {

        snykSecurity(

          snykInstallation: 'snyk@latest',

          snykTokenId: '14c891cf-5828-4ebb-ab4e-c4c55b468cc1',

          // place other parameters here

        )
                

              //  }

            }
        }

       // stage('Deploy') {
                
        //    steps {
         //           deploy adapters: [tomcat9(credentialsId: 'TomJen', path: '', url: 'http://localhost:8081// opt/tomcat/webapps/')], contextPath: 'null', war: '**/*.jar'
                    
   //         }

    //    }

        
    }
}
