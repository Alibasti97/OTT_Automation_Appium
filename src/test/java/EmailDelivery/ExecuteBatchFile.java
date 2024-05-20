package EmailDelivery;

//package EmailDelivery;
// import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//
//public class SCPCommandExecutor {
//
//    public static void main() {
//        // Execute your test suite
//
//        // After the test suite execution, execute the SCP command batch file
//        batchExec("execute_scp.bat");
//    }
//
//    public static void batchExec(String batchFilePath) {
//        try {
//            ProcessBuilder pb = new ProcessBuilder(
//                    "scp",
//                    "-r",
//                    "-i", "C:/Users/T490/.ssh/id_rsa", // Specify the path to your identity file
//                    "-o", "ProxyJump=tamasha.ansible@119.160.95.237",
//                    "-o", "StrictHostKeyChecking=no",
//                    "-o", "UserKnownHostsFile=/dev/null",
//                    "C:/Users/T490/OneDrive/Desktop/Automation/test-output/*",
//                    "tamasha.ansible@192.168.20.52:/var/www/html/reports"
//            );
//            pb.redirectErrorStream(true); // Redirect error stream to standard output
//            Process process = pb.start();
//
//            // Read the output (standard output + standard error)
//            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
//                 BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()))) {
//
//                String line;
//                // Read and print standard output
//                while ((line = reader.readLine()) != null) {
//                    System.out.println(line); // Print each line of the output
//                }
//
//                // Read and print standard error
//                while ((line = errorReader.readLine()) != null) {
//                    System.err.println(line); // Print each line of the error output
//                }
//            }
//
//            int exitCode = process.waitFor();
//            if (exitCode == 0) {
//                System.out.println("SCP command executed successfully.");
//            } else {
//                System.err.println("SCP command execution failed with exit code: " + exitCode);
//            }
//        } catch (IOException | InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
//
//}

import com.jcraft.jsch.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExecuteBatchFile {
    public static void main() {
        String host = "43.156.226.142";
        int port = 22;
        String username = "qa_reports";
        String password = "dada@0007#U";
        String localFolderPath = "C:\\Users\\T490\\OneDrive\\Desktop\\Automation\\test-output";
        String remoteFolderPath = "/var/www/html";

        JSch jsch = new JSch();
        Session session = null;
        ChannelSftp channelSftp = null;
        try {
            // Create JSch session
            session = jsch.getSession(username, host, port);
            session.setPassword(password);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();

            // Create SFTP channel
            channelSftp = (ChannelSftp) session.openChannel("sftp");
            channelSftp.connect();

            // Upload files
            uploadFolder(channelSftp, localFolderPath, remoteFolderPath);

            System.out.println("Files uploaded successfully");
        } catch (JSchException | SftpException | FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            if (channelSftp != null) {
                channelSftp.disconnect();
            }
            if (session != null) {
                session.disconnect();
            }
        }
    }


    private static void uploadFolder(ChannelSftp channelSftp, String localFolderPath, String remoteFolderPath) throws SftpException, FileNotFoundException {
        File localFolder = new File(localFolderPath);
        File[] files = localFolder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    // Create directory on the remote server if it doesn't exist
                    try {
                        channelSftp.mkdir(remoteFolderPath + "/" + file.getName());
                    } catch (SftpException e) {
                        // Directory already exists
                    }
                    // Upload files and subdirectories recursively
                    uploadFolder(channelSftp, file.getAbsolutePath(), remoteFolderPath + "/" + file.getName());
                } else {
                    // Upload file to the remote server, overwriting if it already exists
                    channelSftp.put(new FileInputStream(file), remoteFolderPath + "/" + file.getName(), ChannelSftp.OVERWRITE);
                    System.out.println(file.getName());
                }
            }
        }
    }

        }





//
//                try {
//                    // Specify the path to Plink executable
//                    String plinkPath = "\"C:\\Program Files\\PuTTY\\plink.exe\"";
//
//                    // Specify the source and destination paths
//                    String sourcePath = "C:\\Users\\T490\\OneDrive\\Desktop\\Automation\\test-output\\*";
//                    String destination = "qa_reports@43.156.226.142:/";
//
//                    // Specify the password for the qa_reports user
//                    String password = "dada@0007#U";
//
//                    // Construct the Plink command
//                    String plinkCommand = String.format("%s -l qa_reports -pw %s -batch -C scp -r %s %s",
//                            plinkPath, password, sourcePath, destination);
//
//                    //plink -l qa_reports -pw dada@0007#U -batch -C scp -r  "C:\\Users\\T490\\OneDrive\\Desktop\\Automation\\test-output\\*" "qa_reports@43.156.226.142:/"
//                    // Execute the Plink command
//                    ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c", plinkCommand);
//                    pb.redirectErrorStream(true);
//                    Process p = pb.start();
//
//
//                    // Read the output of Plink and print it to the console
//                    InputStream is = p.getInputStream();
//                    BufferedReader br = new BufferedReader(new InputStreamReader(is));
//                    String line;
//                    while ((line = br.readLine()) != null) {
//                        System.out.println(line);
//                    }
//
//                    // Wait for the process to finish
//                    int exitCode = p.waitFor();
//
//                    // Print the exit code
//                    System.out.println("SCP transfer finished with exit code: " + exitCode);
//
//                } catch (IOException | InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }

//        try {
//            // Specify the path to your batch file
//            String batchFilePath = "C:\\Users\\T490\\OneDrive\\Desktop\\Automation";
//
//            File dir = new File("C:\\Users\\T490\\OneDrive\\Desktop\\Automation");
//            ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/C", "Start", "execute_scp.bat");
//            pb.directory(dir);
//            Thread.sleep(10000);
//
//            Process p = pb.start();
//
//            // Wait for the process to finish
//            int exitCode = p.waitFor();
//
//            // Print the exit code
//            System.out.println("Batch file execution finished with exit code: " + exitCode);
//
//        } catch (IOException | InterruptedException e) {
//            e.printStackTrace();
//        }


//
//        FileOutputStream fos=null;
//        try{
//
//            JSch jsch=new JSch();
//            Session session=jsch.getSession("qa_reports", "43.156.226.142", 22);
//
//            // username and password will be given via UserInfo interface.
//            UserInfo ui=new MyUserInfo();
//            session.setUserInfo(ui);
//            session.connect();
//
//
//
//            session.disconnect();
//
//            System.exit(0);
//        }
//        catch(Exception e){
//            System.out.println(e);
//            try{if(fos!=null)fos.close();}
//            catch(Exception ee){
//
//            }
//        }
//
//
//    }
//
//    public static class MyUserInfo implements UserInfo{
//        public String getPassword(){ return passwd; }
//        public boolean promptYesNo(String str){
//            Object[] options={ "yes", "no" };
//            //int foo=0;
//            return false;
//        }
//
//        String passwd="dada@0007#U";
//
//        public String getPassphrase(){ return null; }
//        public boolean promptPassphrase(String message){ return true; }
//        public boolean promptPassword(String message){
//
//                return false;
//
//        }
//        public void showMessage(String message){
//            //JOptionPane.showMessageDialog(null, message);
//        }
//


 //   }

//}




