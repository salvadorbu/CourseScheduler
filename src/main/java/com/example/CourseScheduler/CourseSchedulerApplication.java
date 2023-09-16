package com.example.CourseScheduler;

import com.example.CourseScheduler.model.GradeDistributionItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
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
	ItemRepository gradeDistributionItemRepo;
	public static void main(String[] args) {
		SpringApplication.run(CourseSchedulerApplication.class, args);
	}

	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format("Hello %s!", name);
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

	// 4. Get count of documents in the collection
	public void findCountOfItems() {
		long count = gradeDistributionItemRepo.count();
		System.out.println("Number of documents in the collection: " + count);
	}

	// Print details in readable form

	public String getItemDetails(GradeDistributionItem item) {

		System.out.println(
				"Course Number: " + item.getCourseNo() +
						", \nSubject: " + item.getSubject() +
						", \nInstructor: " + item.getInstructor() +
						", \nGPA: " + item.getGPA()
		);

		return "";
	}

	public void deleteGradeDistributionItem(String id) {
		gradeDistributionItemRepo.deleteById(id);
		System.out.println("Item with id " + id + " deleted...");
	}

	public void deleteAllItems(){
		gradeDistributionItemRepo.findAll().forEach(
				item -> gradeDistributionItemRepo.delete(item)
		);
	}

	public void run(String... args) throws IOException {
		System.out.println("-------------CREATE ITEMS-------------------------------\n");

		createGradeDistributionItems();

	}
}
