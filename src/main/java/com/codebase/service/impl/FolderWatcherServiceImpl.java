package com.codebase.service.impl;

import com.codebase.service.FolderWatcherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;

/**
 * Service to monitor a specific folder and log any newly added files.
 */
@Service
public class FolderWatcherServiceImpl implements FolderWatcherService {
    private static final Logger logger = LoggerFactory.getLogger(FolderWatcherServiceImpl.class);

    // Define the folder path to be monitored (Change this to your actual directory)
    private final Path folderPath = Paths.get("D:/test-monitor-folder");

    /**
     * Starts a new thread to watch the folder for file creation events.
     */
    public void startWatching() {
        Thread thread = new Thread(this::watchFolder);
        thread.setDaemon(true); // Ensures it stops when the app stops
        thread.start();
    }

    /**
     * Watches the specified folder and logs newly created files.
     */
    private void watchFolder() {
        try (WatchService watchService = FileSystems.getDefault().newWatchService()) {
            // Register the folder with the watch service to monitor file creation events
            folderPath.register(watchService, ENTRY_CREATE);

            logger.info("Watching folder: " + folderPath);

            while (true) {
                // Wait for a file event (blocks until an event occurs)
                WatchKey key = watchService.take();

                for (WatchEvent<?> event : key.pollEvents()) {
                    if (event.kind() == ENTRY_CREATE) {
                        // Get the created file's name
                        Path createdFile = folderPath.resolve((Path) event.context());
                        logger.info("New file added: " + createdFile);
                    }
                }

                // Reset the key to continue watching for new events
                boolean valid = key.reset();
                if (!valid) {
                    logger.warn("WatchKey is no longer valid. Stopping folder watch.");
                    break;
                }
            }
        } catch (IOException | InterruptedException e) {
            logger.error("Error watching folder", e);
        }
    }
}
