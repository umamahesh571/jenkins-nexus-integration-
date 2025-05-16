pipeline {
    agent any

    environment {
        NEXUS_USER = 'admin'
        NEXUS_PASS = 'admin'
        NEXUS_REPO_URL = 'http://13.250.96.10:8081/repository/maven-nexus-release/'
        GROUP_ID = 'com.evolve'
        ARTIFACT_ID = 'evolve-technologies'
        VERSION = '1.0'
        PACKAGING = 'war'
        FINAL_NAME = 'evolve-technologies'
    }

    stages {
        stage('Checkout Code') {
            steps {
                git url: 'https://github.com/umamahesh571/springboot.4.0.git', branch: 'main'
            }
        }

        stage('Build WAR') {
            steps {
                sh 'mvn clean deploy -DskipTests'
            }
        }

    post {
        success {
            echo '✅ WAR file built and uploaded to Nexus successfully.'
        }
        failure {
            echo '❌ Failed to build or upload the WAR file to Nexus.'
        }
    }
}
