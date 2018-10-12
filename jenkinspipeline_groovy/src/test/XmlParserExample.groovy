package test

class XmlParserExample {
	static main(args) {
		def parser = new XmlParser()
		def doc = parser.parse("resources\\jobs.xml");

		doc.job.each{ bk->
			print("Job Name:")
			println "${bk['@name']}"

			print("Job Workspace:")
			println "${bk.workspace[0].text()}"

			println("*******************************")
		}
	}
}
