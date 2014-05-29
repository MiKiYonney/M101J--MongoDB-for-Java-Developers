package com.tengent;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

public class HelloWorldSparkFreemarkStyle {
	public static void main(String[] args) {
		final Configuration configuration = new Configuration();
		configuration.setClassForTemplateLoading(HelloWorldSparkFreemarkStyle.class, "/");
		Spark.get(new Route("/") {
			public Object handle(Request arg0, Response arg1) {
				StringWriter writer = new StringWriter();
				try {
					Template template = configuration.getTemplate("hello.ftl");
					Map<String,Object> helloMap = new HashMap<String, Object>();
					helloMap.put("name", "Freemarker");
					template.process(helloMap, writer);
					System.out.println(writer);
				} catch (Exception e) {
					halt(500);
					e.printStackTrace();
				} 
				return writer;
			}
		});
	}
	
}
