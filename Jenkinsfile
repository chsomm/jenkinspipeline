pipeline {
    agent any
	parameters {
	    string(name: 'tomcat_dev', defaultValue: 'D:/Dev/Server/apache-tomcat-8.5.32-staging/webapps', description: 'Staging Server')
	    string(name: 'tomcat_prod', defaultValue: 'D:/Dev/Server/apache-tomcat-8.5.32-prod/webapps', description: 'Production Server')
    }

    triggers {
         pollSCM('* * * * *')
     }

	stages{
        stage('Build'){
            steps {
                bat 'mvn clean package'
            }
            post {
                success {
                    echo 'Now Archiving...'
                    archiveArtifacts artifacts: '**/target/*.war'
                }
            }
        }

        stage ('Deployments'){
            parallel{
                stage ('Deploy to Staging'){
                    steps {
                        bat "copy **/target/*.war ${params.tomcat_dev}"
                    }
                }

                stage ("Deploy to Production"){
                    steps {
                        bat "copy  **/target/*.war ${params.tomcat_prod}"
                    }
                }
            }
        }
    }
}