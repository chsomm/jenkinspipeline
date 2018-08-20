def modules = [:]

pipeline {
    agent any

	stages{
		stage('Build') {
			steps {
               bat 'mvn clean package'
               script{
               	 	modules.first = load "jenkinspipeline_groovy/src/test/test.groovy"
               	 	modules.first.test1()
               	 	modules.first.test2()
               }
           	}
           	post {
            	success {
                   echo 'Now Archiving...'
                   archiveArtifacts artifacts: '**/target/*.war'
               	}
          	}
		}
		stage ('Deploy to Staging') {
			steps {
				build job: 'deploy-to-staging'                     
			}
       	}
       	stage('Deploy to Production') {
       		steps {
				timeout(time:5, unit:'DAYS') {
  					input message: 'Approve PRODUCTION Deployment?'
				}
                build job: 'deploy-to-prod'    
       		}
            post {
     			success{
					echo 'Code deployed to Production'
     			}
				 failure{
				    echo 'Deployment failed' 
				 }

            }
         
       	                    
     	}

	 }
}