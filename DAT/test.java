package DAT;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import javax.crypto.Cipher;

public class test {
    private static void readfile_clientList (ArrayList <Client> dskh){
        String tmp = "";
        try {
            FileReader f = new FileReader("E:\\tài liệu CNTT\\JAVA\\PROJECTS\\danh sach khach hang");
            BufferedReader b = new BufferedReader(f);
            while (true){
                if (tmp == null){
                    break;
                }
                tmp = b.readLine();
                String[] word = tmp.split(" - ");
                Client newclient = new Client(word[0], word[1], word[2], word[3], word[4], word[5]);
                System.out.println(newclient.tostring());
                dskh.add(newclient);
            }
            b.close();
            f.close();
        } catch (Exception e) {
            
        }
    }

    public static void main(String[] args) {
        ArrayList <Client> dskh = new ArrayList<Client>();
        readfile_clientList(dskh);
        for (Client x : dskh){
            System.out.println(x.tostring());
        }
    }
}
