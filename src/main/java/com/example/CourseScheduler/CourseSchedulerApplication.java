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
		String[] courses = new String[] {"AOE","ALCE","AAEC","ALS","AINS","APSC","APS","AHRM","ARBC","ARCH","AAD","ART","BDS","BCHM","BIOL","BSE","BMVS","BMSP","BMES","BC","BUS","BIT","EDCT","CHE","CHEM","CHN","CINE","CEE","CLA","COS","COMM","CMST","CMDA","CS","CEM","CONS","CEP","CRIM","CSES","DASC","DANC","ECON","EDCO","EDCI","EDEP","ECE","ENGR","ENGE","ESM","ENGL","ENT","ENSC","FCS","FMD","FIN","FNAD","FA","FIW","FST","FL","FREC","VT","FR","GEOG","GEOS","GER","GR","HEB","HIST","HORT","HTM","HD","HNFE","HUM","ISE","IDS","EDIT","ISC","ITDS","IS","ITAL","JPN","JMC","JUD","KOR","LAR","LAT","LDRS","LAHS","MGT","MKTG","MSE","MATH","ME","MTRG","MN","AROTC","MS","AS","MINE","MUS","NANO","NR","NEUR","NSEG","PSVP","PHIL","PPE","PHYS","PPWS","PSCI","PHS","PORT","PM","PSYC","PR","REAL","RLCL","RED","RUS","SPES","SPIA","STS","STL","SOC","SPAN","STAT","SUMA","SBIO","SYSB","EDTE","TA","TBMH","UNIV","UH","REG","UAP","WATR","WGS"};
		RegistrationTimetable rt = new RegistrationTimetable("2023");
		/*
		add stuff
		 */
		System.out.println("Data creation complete...");
	}

	// READ
	// 1. Show all the data
	public void showAllGradeDistributionItems() {
		gradeDistributionItemRepo.findAll().forEach(item -> System.out.println(getItemDetails(item)));
	}

	// 2. Get item by name
	public void getGradeDistributionItemByCourseNo(String courseNo) {
		System.out.println("Getting item by course number: " + courseNo);
		GradeDistributionItem item = gradeDistributionItemRepo.findItemByCourseNo(courseNo);
		System.out.println(getItemDetails(item));
	}

	// 3. Get name and quantity of a all items of a particular category
	public void getItemsByInstructor(String instructor) {
		System.out.println("Getting items for the instructor: " + instructor);
		List<GradeDistributionItem> list = gradeDistributionItemRepo.findAll(instructor);

		list.forEach(item -> System.out.println("Course Number: " + item.getCourseNo() + ", GPA: " + item.getGPA()));
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

	public void updateCategoryName(String instructor) {

		// Change to this new value
		String newInstructor = "Chung";

		// Find all the items with the instructor
		List<GradeDistributionItem> list = gradeDistributionItemRepo.findAll(instructor);

		list.forEach(item -> {
			// Update the category in each document
			item.setInstructor(newInstructor);
		});

		// Save all the items in database
		List<GradeDistributionItem> itemsUpdated = gradeDistributionItemRepo.saveAll(list);

		if (itemsUpdated != null)
			System.out.println("Successfully updated " + itemsUpdated.size() + " items.");
	}
	// DELETE
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


		System.out.println("\n----------------SHOW ALL ITEMS---------------------------\n");

		showAllGradeDistributionItems();

		System.out.println("\n--------------GET ITEM BY COURSE NUMBER-----------------------------------\n");

		getGradeDistributionItemByCourseNo("5814");

		System.out.println("\n-----------GET ITEMS BY INSTRUCTOR---------------------------------\n");

		getItemsByInstructor("Cooper");

		System.out.println("\n-----------UPDATE INSTRUCTOR NAME COOPER----------------\n");

		updateCategoryName("Cooper");

		//showAllGradeDistributionItems();

		System.out.println("\n----------DELETE AN ITEM----------------------------------\n");

		deleteGradeDistributionItem("ENSC 3124");

		System.out.println("\n------------FINAL COUNT OF ITEMS-------------------------\n");

		findCountOfItems();

		System.out.println("\n-------------------THANK YOU---------------------------");

	}
}
