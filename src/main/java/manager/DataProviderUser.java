package manager;

import model.User;
import org.testng.annotations.DataProvider;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviderUser {
    @DataProvider
    public static Iterator<Object[]> loginDataCls(){

        List<Object[]> list = new ArrayList<>();

        list.add(new Object[]{"noa@gmail.com","Nnoa12345$"});
        list.add(new Object[]{"sonya@gmail.com","Ss12345$"});
        list.add(new Object[]{"noa@gmail.com","Nnoa12345$"});

        return list.iterator();

    }


    @DataProvider
    public static Iterator<Object[]> loginDataUser(){
        List<Object[]> list = new ArrayList<>();

        list.add(new Object[]{User.builder().email("noa@gmail.com").password("Nnoa12345$").build()});
        list.add(new Object[]{User.builder().email("sonya@gmail.com").password("Ss12345$").build()});
        list.add(new Object[]{User.builder().email("noa@gmail.com").password("Nnoa12345$").build()});

        return list.iterator();

    }
    @DataProvider
    public static Iterator<Object[]> loginDataUserFromFile() throws IOException {
        List<Object[]> list = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("src/test/resources/Data base - phoneBook.csv")));
        String  line =bufferedReader.readLine();
        while(line!=null){
            String[] split = line.split(",");
            list.add(new Object[]{User.builder().email(split[0]).password(split[1]).build()});
            line =bufferedReader.readLine();
        }

        return list.iterator();
    }
    @DataProvider
    public static Iterator<Object[]> RegistrDataFromFile() throws IOException {
        List<Object[]> list = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("src/test/resources/Data base - phoneBook.csv")));
        String  line =bufferedReader.readLine();
        while(line!=null){
            String[] split = line.split(",");
            list.add(new Object[]{User.builder().email(split[0]).password(split[1]).build()});
            line =bufferedReader.readLine();}
        System.out.println(list);
        return list.iterator();

    }

}