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

                sh 'ant -f build.xml'

            }

        }

        stage('Test') {

            steps('SonarQube Analysis') {
                
                withSonarQubeEnv('sonarserver') {

                    sh "ant sonar:sonar"

                }

            }
        }

        stage('Deploy') {
                
            steps {
                    deploy adapters: [tomcat9(credentialsId: 'TomJen', path: '', url: 'http://localhost:8081// opt/tomcat/webapps/')], contextPath: 'null', war: '**/*.jar'
                    
            }

        }

        
    }
}
