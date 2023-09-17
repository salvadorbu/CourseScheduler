package com.example.CourseScheduler;

import com.example.CourseScheduler.model.GradeDistributionItem;
import org.apache.tomcat.util.file.ConfigurationSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.CourseScheduler.repository.ItemRepository;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@SpringBootApplication
@EnableMongoRepositories
public class CourseSchedulerApplication implements CommandLineRunner {
	@Autowired
	private ItemRepository gradeDistributionItemRepo;
	public static void main(String[] args) {
		SpringApplication.run(CourseSchedulerApplication.class, args);
	}

	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format("Hello %s!", name);
	}

	@GetMapping("/")
	public String home() {
		return "Dawg";
	}

	//CREATE
	//NOTE: If items are in the database already, then they will not be entered again. So if an item is deleted,
	//then it will be added to the database again at the end of the collection.
	void createGradeDistributionItems() throws IOException {
		System.out.println("Data creation started...");

		Map<String, List<GradeDistributionItem>> gdiMapping = GradeDistributionFactory.insertGPA();

		for (List<GradeDistributionItem> gdi : gdiMapping.values()) {
			for(GradeDistributionItem item : gdi){
				System.out.println(item.toString());
				gradeDistributionItemRepo.save(item);
			}
		}

		System.out.println("Data creation complete...");
	}

	public void run(String... args) throws IOException {
		System.out.println("-------------MONGODB STATS-------------------------------\n");
		System.out.println("UPDATE1");
		System.out.println("Database entry count: " + gradeDistributionItemRepo.count());
	}
}
