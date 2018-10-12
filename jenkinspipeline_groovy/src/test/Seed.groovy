package test;

seedJobs()

def seedJobs() {
	def parser = new XmlParser()
	def xmlFile = readFileFromWorkspace("\\resources\\jobs.xml")
	def doc = parser.parse(xmlFile);

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
		parameters {
			stringParam('BUILD_WORKSPACE', job.workspace[0].text(), 'Build workspace')
		}
	}
}
return this
