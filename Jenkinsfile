pipeline {
    agent any
	parameters {
	    string(name: 'tomcat_dev', defaultValue: 'ec2-18-223-112-135.us-east-2.compute.amazonaws.com', description: 'Staging Server')
	    string(name: 'tomcat_prod', defaultValue: 'ec2-13-59-206-81.us-east-2.compute.amazonaws.com', description: 'Production Server')
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
                    	echo '${params.tomcat_dev}'
                        echo "${params.tomcat_dev}"
                    }
                }

                stage ("Deploy to Production"){
                    steps {
                    	echo '${params.tomcat_prod}'
                        echo "${params.tomcat_prod}"
                    }
                }
            }
        }
    }
}