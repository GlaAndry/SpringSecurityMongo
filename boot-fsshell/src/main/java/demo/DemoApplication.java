package demo;

import demo.controller.HDFSController;
import org.apache.hadoop.fs.FileStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.hadoop.fs.FsShell;

@SpringBootApplication
public class DemoApplication {

	@Autowired
	HDFSController hdfsController;

	@Bean
	public String rn(){
		System.out.println(hdfsController.prova());
		return "ok";
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
