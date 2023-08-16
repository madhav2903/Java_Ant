pipeline {
    agent any
    stages {
        stage('check'){
            steps {
        git credentialsId: 'Github', url: 'https://github.com/madhav2903/Java_Ant.git'
            }
        }
        stage('build'){
            steps {
                sh 'npm install'
            }
        }
        stage('executeSonarqubeReport')
        {
            steps {
                sh "npm run sonar"
            }
        }
    }
}
