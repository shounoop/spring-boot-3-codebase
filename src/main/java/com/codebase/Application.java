package com.codebase;

import com.codebase.model.primary.entity.Student;
import com.codebase.repository.primary.StudentRepository;
import com.codebase.service.FolderWatcherService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.List;
import java.util.stream.IntStream;

@SpringBootApplication
@RequiredArgsConstructor
@EnableScheduling
public class Application implements CommandLineRunner {

    private final FolderWatcherService folderWatcherService;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    /**
     * Runs the folder watcher service as soon as the application starts.
     */
    @Override
    public void run(String... args) {
        folderWatcherService.startWatching();
    }

    @Bean
    CommandLineRunner initDatabase(StudentRepository studentRepository) {
        return args -> {
            if (studentRepository.count() == 0) { // Avoid duplicate entries on restart
                List<Student> students = IntStream.rangeClosed(1, 1000)
                        .mapToObj(i -> new Student("Student" + i,
                                "student" + i + "@example.com",
                                "012345678" + (i % 10)))
                        .toList();

                studentRepository.saveAll(students);
                System.out.println("100 Students initialized.");
            }
        };
    }
}
