pipeline {
    agent {
     docker {
            image 'maven:3.9.3-eclipse-temurin-21'
            label 'docker-agent-mvn'
        }
     }
     triggers {
        GenericTrigger(
            genericVariables: [
                [key: 'ref', value: '$.ref'],
                [key: 'pusher', value: '$.pusher.name']
            ],
            causeString: 'Push bởi $pusher lên $ref',
            tokenCredentialId: 'webhook-token',
            regexpFilterText: '$ref',
            regexpFilterExpression: '^refs/heads/main$',
            printContributedVariables: true,
            printPostContent: false
        )
     }
     environment {
        COMPOSE_PROJECT_NAME = 'life-manager'
     }
    options {
        timeout(time: 15, unit: 'MINUTES')
        buildDiscarder(logRotator(numToKeepStr: '10'))
    }
    stages {
        stage('Test') {
            steps {
                sh 'mvn -B verify'
            }
        }
        stage('Deploy') {
            steps {
                withCredentials([
                     string(credentialsId: 'db-password', variable: 'DB_PASSWORD'),
                     string(credentialsId: 'jwt-secret', variable: 'JWT_SECRET')
                ])
                {
                    sh 'docker compose up -d'
                }
            }
        }
    }
}