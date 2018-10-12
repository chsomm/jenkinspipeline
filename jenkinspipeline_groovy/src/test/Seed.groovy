package test;

seedJobs()

def seedJobs() {
	def parser = new XmlParser()
	def workspace = binding.variables.WORKSPACE
	def doc = parser.parse("${workspace}\\jenkinspipeline_groovy\\resources\\jobs.xml");

	doc.job.each{ job->
		print("Job Name:")
		println "${job['@name']}"

		print("Job Workspace:")
		println "${job.workspace[0].text()}"

		println("*******************************")
		createJob(job)
	}
}

def createJob(def job) {
	pipelineJob("${job['@name']}") {
		definition {
			cps {
				script(readFileFromWorkspace('project-a-workflow.groovy'))
			}
		}
	}
}
return this
