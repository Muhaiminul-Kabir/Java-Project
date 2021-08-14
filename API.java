/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studycmp;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 *
 * @author ASUS
 */
public class API {

    public static void mainDir() throws IOException {
        File f1 = new File("C:/AUSTBase");

        //Creating a folder using mkdir() method  
        boolean bool = f1.mkdir();
        if (bool) {
            System.out.println("Folder is created successfully");
            dataIn("init", "C:/AUSTBase/app_state.txt", "pre_user");
        } else {
            System.out.println("exists");

        }

    }

    public static void overwriteFile(String path, String data) throws IOException {
        File myFoo = new File(path);
        FileWriter fooWriter = new FileWriter(myFoo, false); // true to append
        // false to overwrite.
        fooWriter.write(data);
        fooWriter.close();
    }

    public static void closeWindowOnButton(Button b) {
        // get a handle to the stage
        Stage stage = (Stage) b.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

    //inserts data to file
    public static void dataIn(String sl, String path, String temp) throws FileNotFoundException, IOException {
        FileOutputStream fout = new FileOutputStream(path);
        BufferedOutputStream bout = new BufferedOutputStream(fout);

        byte c[] = temp.getBytes();
        bout.write(c);
        bout.flush();
        bout.close();
        fout.close();

        System.out.println(sl + "success...");

    }

    //creates expexted file path
    public static String createFolderPath(String key, String flName) {
        String path = null;
        if (!flName.equals("")) {
            path = "C:/AUSTBase/" + key + "/" + flName;
        } else {
            path = "C:/AUSTBase/" + key;

        }
        System.out.println(path);
        return path;

    }

    //read a file as String
    public static String readFileAsString(String fileName) throws Exception {
        String data = "";
        data = new String(Files.readAllBytes(Paths.get(fileName)));
        return data;
    }

    //matches password
    public static String matchPass(String user) throws Exception {
        String data;
        String path = "C:/AUSTBase/" + user + "/password.txt";
        boolean exists = isUserExists(user);
        if (!exists) {
            data = "err";
        } else {
            data = readFileAsString(path);
        }

        return data;
    }

    //checks if the user exists
    public static boolean isUserExists(String user) throws Exception {

        boolean exists = true;

        String[] temp = getAvaliableFilesInDir("C:/AUSTBase");
        int i = 0;
        for (i = 0; i < temp.length; i++) {
            if (user.equals(temp[i])) {
                exists = true;
                break;
            }

        }
        if (i == temp.length) {
            exists = false;
        }

        return exists;

    }

    //gets extension of Profile Photo
    public static String getExtension(String user) {
        File folder = new File("C:/AUSTBase/" + user + "/Photos");
        File[] listOfFiles = folder.listFiles();
        String ext = null;
        for (File file : listOfFiles) {
            if (file.isFile()) {
                String[] filename = file.getName().split("\\.(?=[^\\.]+$)"); //split filename from it's extension
                if (filename[0].equalsIgnoreCase("user")) //matching defined filename
                {
                    System.out.println("File exist: " + filename[0] + "." + filename[1]); // match occures.Apply any condition what you need
                }
                ext = filename[1];
            }
        }
        return ext;
    }

    //renames a file 
    public static void rename(String patht, String pathr) {

        File file = new File(patht);

        File rename = new File(pathr);

        boolean flag = file.renameTo(rename);

        if (flag == true) {
            System.out.println("File Successfully Renamed");
        } else {
            System.out.println("Operation Failed");
        }

    }

    //gets targeted image and sets in a imageview
    public static void getSourceImage(String path, ImageView view) throws FileNotFoundException {

        FileInputStream input = new FileInputStream(path);
        Image image = new Image(input);
        view.setImage(image);
    }

    //returns list of files in a directory
    public static String[] getAvaliableFilesInDir(String path) {

        File source = new File(path);
        String[] fileList = source.list();
        return fileList;

    }

}
