package test;

seedJobs()

def seedJobs() {
	def parser = new XmlParser()
	def doc = parser.parse("${workspace}\\jenkinspipeline_groovy\\resources\\jobs.xml");

	doc.jobs.each{ bk->
		print("Job Name:")
		println "${bk['@name']}"

		print("Job Workspace:")
		println "${bk.workspace[0].text()}"

		println("*******************************")
		createJob(bk)
	}
}

def createJob(def job) {
	pipelineJob("${job['@name']}") {
		parameters {
			stringParam('BUILD_WORKSPACE', "${job.workspace[0].text()}", 'Build workspace')
		}
	}
}
return this