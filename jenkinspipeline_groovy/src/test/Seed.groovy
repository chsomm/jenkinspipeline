package test;

seedJobs()

def seedJobs() {
	def parser = new XmlParser()
	def workspace = this.getProperty('binding').getVariable('WORKSPACE')
	
	def file = new File("${workspace}\\uploaded_jobs.xml")
	
	def doc
	
	if(file.exists()) {
		doc = parser.parse(file);
	} else {
		doc = parser.parse("${workspace}\\jenkinspipeline_groovy\\resources\\jobs.xml");
	}
	
	
	//def doc = parser.parse("..\\jenkinspipeline_groovy\\resources\\jobs.xml");

	println doc
	
	for (job in doc) {
		println "${job['@name']}"
		println job["workspace"].text()
		createJob(job)
	}
	
//	for (job in doc) {
//		print("Job Name:")
//		println "${job['@name']}"
//
//		print("Job Workspace:")
//		println "${job.workspace[0].text()}"
//
//		println("*******************************")
//		createJob(job)
//	}
}

def createJob(def job) {
	pipelineJob("${job['@name']}") {
		parameters {
			stringParam('BUILD_WORKSPACE', job["workspace"].text(), 'Build workspace')
		}
	}
}
return this
