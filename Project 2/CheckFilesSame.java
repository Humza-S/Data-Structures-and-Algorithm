/*
    Name: Humza Salman
    NET ID: MHS180007
*/

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;

public class CheckFilesSame {
    public static void main(String[] args) throws IOException {
        File o1 = new File("o1.txt");
        File o2 = new File("o2.txt");
        File o3 = new File("o3.txt");
        File mo1 = new File("myo1.txt");
        File mo2 = new File("myo2.txt");
        File mo3 = new File("myo3.txt");
        byte[] f1 = Files.readAllBytes(o1.toPath());
        byte[] f2 = Files.readAllBytes(mo1.toPath());
        if(Arrays.equals(f1, f2))
            System.out.println("Correct");
        else
            System.out.println("Incorrect");


        f1 = Files.readAllBytes(o2.toPath());
        f2 = Files.readAllBytes(mo2.toPath());
        if(Arrays.equals(f1, f2))
            System.out.println("Correct");
        else
            System.out.println("Incorrect");
    
        f1 = Files.readAllBytes(o3.toPath());
        f2 = Files.readAllBytes(mo3.toPath());
        if(Arrays.equals(f1, f2))
            System.out.println("Correct");
        else
            System.out.println("Incorrect");
    }
}