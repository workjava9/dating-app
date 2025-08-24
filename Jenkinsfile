pipeline {
  agent any
  environment {
    IMAGE = credentials('docker-image')       // string: myrepo/dating-app
    REG_USER = credentials('docker-user')     // usernamePassword
    SSH_KEY = credentials('ssh-key')          // sshUserPrivateKey
    DB_URL = credentials('db-url')            // secret text
    DB_USER = credentials('db-user')          // secret text
    DB_PASSWORD = credentials('db-password')  // secret text
    APP_JWT_SECRET = credentials('jwt-secret')// secret text
  }
  stages {
    stage('Checkout'){ steps { checkout scm } }
    stage('Test'){
      tools { jdk 'jdk21' }
      steps { sh './gradlew clean test --no-daemon' }
    }
    stage('Build JAR'){
      steps { sh './gradlew clean bootJar --no-daemon' }
    }
    stage('Build & Push Image'){
      steps {
        sh """
          echo ${REG_USER_PSW} | docker login -u ${REG_USER_USR} --password-stdin
          docker build -t ${IMAGE}:${env.BUILD_NUMBER} -t ${IMAGE}:latest .
          docker push ${IMAGE}:${env.BUILD_NUMBER}
          docker push ${IMAGE}:latest
        """
      }
    }
    stage('Deploy'){
      steps {
        sshagent (credentials: ['ssh-key']) {
          sh """
            ssh -o StrictHostKeyChecking=no ${SSH_KEY_USR}@${SSH_KEY_HOST} '
              mkdir -p ~/dating-app &&
              cd ~/dating-app &&
              echo IMAGE=${IMAGE}:latest > .env &&
              echo DB_URL=${DB_URL} >> .env &&
              echo DB_USER=${DB_USER} >> .env &&
              echo DB_PASSWORD=${DB_PASSWORD} >> .env &&
              echo APP_JWT_SECRET=${APP_JWT_SECRET} >> .env &&
              docker compose -f docker-compose.prod.yml pull || true &&
              docker compose -f docker-compose.prod.yml up -d --remove-orphans &&
              docker system prune -f
            '
          """
        }
      }
    }
  }
  post { always { junit 'build/test-results/test/*.xml' } }
}
