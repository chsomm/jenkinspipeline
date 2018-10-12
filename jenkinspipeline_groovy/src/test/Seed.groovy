package test;

seedJobs()

def seedJobs() {
	def parser = new XmlParser()
	def doc = parser.parse("${env.WORKSPACE}\\jenkinspipeline_groovy\\resources\\jobs.xml");

	doc.job.each{ bk->
		print("Job Name:")
		println "${bk['@name']}"

		print("Job Workspace:")
		println "${bk.workspace[0].text()}"

		println("*******************************")
		createJob(bk)
	}
}

def createJob(def job) {
	pipelineJob("${bk['@name']}") {
		definition {
			cps {
				script(readFileFromWorkspace('project-a-workflow.groovy'))
			}
		}
	}
}
return this
