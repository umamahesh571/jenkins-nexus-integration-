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
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Upload WAR to Nexus') {
            steps {
                sh '''
                    FILE_PATH=target/${FINAL_NAME}.${PACKAGING}
                    GROUP_PATH=$(echo ${GROUP_ID} | tr '.' '/')
                    UPLOAD_URL=${NEXUS_REPO_URL}/${GROUP_PATH}/${ARTIFACT_ID}/${VERSION}/${ARTIFACT_ID}-${VERSION}.${PACKAGING}
                    
                    echo "Uploading WAR to: $UPLOAD_URL"
                    
                    curl -v -u ${NEXUS_USER}:${NEXUS_PASS} --upload-file $FILE_PATH $UPLOAD_URL
                '''
            }
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
