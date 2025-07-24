pipeline {
    agent any

    environment {
        SONARQUBE_URL = 'SonarQubeServer'   // Jenkins > Manage Jenkins > Configure System > SonarQube servers name
        SONAR_TOKEN_CREDENTIAL_ID = 'sonar-token' // Jenkins credentials ID for your SonarQube token
    }

    stages {
        stage('Checkout Code') {
            steps {
                git url: 'https://github.com/umamahesh571/jenkins-nexus-integration-.git', branch: 'main'
            }
        }

        stage('Build WAR') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('SonarQube Scan') {
            steps {
                withSonarQubeEnv("${SONARQUBE_URL}") {
                    sh 'mvn sonar:sonar -Dsonar.projectKey=evolve-technologies -Dsonar.host.url=$SONAR_HOST_URL -Dsonar.login=$SONAR_AUTH_TOKEN'
                }
            }
        }

        stage('Quality Gate') {
            steps {
                timeout(time: 5, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: true
                }
            }
        }
    }

    post {
        success {
            echo '✅ Build & Sonar scan completed successfully.'
        }
        failure {
            echo '❌ Build failed or Sonar quality gate not passed.'
        }
    }
}
