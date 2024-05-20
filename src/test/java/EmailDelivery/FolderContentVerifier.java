package EmailDelivery;

import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class FolderContentVerifier {

    // Store the previous state of the folder
    private static Set<String> previousState = new HashSet<>();

    /**
     * Verifies if the contents of the specified folder have changed.
     *
     * @param folderPath The path of the folder to verify.
     * @return true if the contents have changed, false otherwise.
     */
    public static boolean hasFolderChanged(String folderPath) {
        // Get the current state of the folder
        Set<String> currentState = getCurrentState(folderPath);

        // Compare current state with previous state
        boolean hasChanged = !currentState.equals(previousState);

        // Update previous state
        previousState = currentState;

        return hasChanged;
    }

    /**
     * Retrieves the current state (file names) of the folder.
     *
     * @param folderPath The path of the folder.
     * @return A set containing the names of files in the folder.
     */
    private static Set<String> getCurrentState(String folderPath) {
        Set<String> currentState = new HashSet<>();
        File folder = new File(folderPath);

        if (folder.exists() && folder.isDirectory()) {
            // List files in the folder
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    // Add file name to the set
                    currentState.add(file.getName());
                }
            }
        }

        return currentState;
    }

//    public static void main(String[] args) {
//        String folderPath = "path/to/your/folder";
//        boolean changed = hasFolderChanged(folderPath);
//
//        if (changed) {
//            System.out.println("Folder contents have changed.");
//        } else {
//            System.out.println("Folder contents have not changed.");
//        }
//    }
}
