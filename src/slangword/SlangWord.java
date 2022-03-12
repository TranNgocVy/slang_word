/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package slangword;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author ASUS
 */
public class SlangWord {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        String filename = "slang.txt";
        ArrayList<slang_word> swList = readFile(filename);
        
        slang_word randonslw = randromSlangWord(swList);
        System.out.print("Ramdom slang word: " + randonslw.key + " = " + randonslw.definition.get(0));
        for(int i = 1; i < randonslw.definition.size(); i++){
            System.out.print(", " + randonslw.definition.get(i));
        }
        System.out.println("");
//        addNewSlangWord(swList);
//        for(slang_word sl: swList){
//            System.out.print(sl.key + ": ");
//            for(String str: sl.definition){
//                System.out.print(str + "| ");
//            }
//            System.out.println("");
//        }
//        deleteSlangWord(swList);
//        for(slang_word sl: swList){
//            System.out.print(sl.key + ": ");
//            for(String str: sl.definition){
//                System.out.print(str + "| ");
//            }
//            System.out.println("");
//        }
//        editSlangWord(swList);
//        for(slang_word sl: swList){
//            System.out.print(sl.key + ": ");
//            for(String str: sl.definition){
//                System.out.print(str + "| ");
//            }
//            System.out.println("");
//        }
//        for(int i =0; i< 20; i++){
//            gameSlangWord(swList);
//        }
//        for(int i =0; i < 15; i++){
//            gameDefinition(swList);
//        }
    }
    
    //Doc file luu vao ArrayList
    public static ArrayList<slang_word> readFile(String filename) throws IOException {
        ArrayList<slang_word> swList = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(filename));
        
        while(true){
            ArrayList<String> defineList = new ArrayList<String>();

            String line =  br.readLine();
            String defineString = null;
            if(line != null){
                int point = line.indexOf('`');
                
                if(point < 0){
                    defineString = line;
                    do{
                        //Xac dinh vi tri phan cach giua cac nghia (doi voi tu co nhieu nghia)
                        int point1 = defineString.indexOf('|');
                        
                        //Neu tu co nhieu nghia: Dua tung nghia vao ArrayList                        
                        if(point1 >= 0){
                            defineList.add(defineString.substring(0, point1));
                            defineString = defineString.substring(point1 + 2);
                        }
                        else{
                            //Dua nghia duy nhat (cuoi cung) vao ArrayList
                            defineList.add(defineString);
                            break;
                        }
                    }while(true);
                    
                    swList.get(swList.size() - 1).definition.addAll(defineList);
                }
                else{
                    //Cat lay phan dinh nghia cua slang word
                    defineString = line.substring(point + 1);
                    
                    do{
                        //Xac dinh vi tri phan cach giua cac nghia (doi voi tu co nhieu nghia)
                        int point1 = defineString.indexOf('|');
                        
                        //Neu tu co nhieu nghia: Dua tung nghia vao ArrayList
                        if(point1 >= 0){
                            defineList.add(defineString.substring(0, point1));
                            defineString = defineString.substring(point1 + 2);
                        }
                        else{
                            //Dua nghia duy nhat (cuoi cung) vao ArrayList
                            defineList.add(defineString);
                            break;
                        }
                    }while(true);
                    
                    slang_word sw = new slang_word(line.substring(0, point), defineList);
                    swList.add(sw);
                }
            }
            else{
                break;
            }
        }
        
        br.close();
        return swList;
    }
    
    //Nhap vao mot so nguyen tu man hinh console
    public static int getInt(){
        boolean isValid = true;
        int choose = 0;
        Scanner scanner = new Scanner(System.in);

        do{
                isValid = true;
                
                try{
                    choose = scanner.nextInt();
                }catch(Exception e){
                    isValid = false; 
                }
                
                //Loai bo dau enter (tuong tu lenh ignore() trong c++)
                scanner.nextLine();

                if(isValid == false){
                    System.out.println("Khong hop le. Hay nhap lai!!");             
                    System.out.print("Ban chon: ");

                }
                else{
                    break;
                }
        }while(true);
        return choose;
    }
    
    //#04. Them mot slang word moi vao file
    public static void addNewSlangWord(ArrayList<slang_word> swList) throws IOException {
        String key = null;
        String definition = null;
        ArrayList<String> defineList = new ArrayList<>();
        int choose = 0; 
        
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Slang word moi: ");
        key = scanner.nextLine();
        
        do{
            System.out.print("Nghia cua slang word moi: ");
            definition = scanner.nextLine();
            
            defineList.add(definition);
            
            System.out.println("Con nghia nao khac nua hay khong?");
            System.out.println(" + Phim 1: Co");
            System.out.println(" + Phim 0: Khong");
            
            do{
                System.out.print("Ban chon: ");
                choose = getInt();

                if(choose != 0 && choose != 1){
                    System.out.println("Khong hop le. Hay nhap lai!!");
                }
                else{
                    break;
                }
            }while(true);
        }while(choose == 1);
        
        swList.add(new slang_word(key, defineList));
        
//        BufferedWriter bw = new BufferedWriter(new FileWriter(filename, true));
//        bw.write(key + "`" + String.join("| ", defineList) + "\n");
//        bw.close();
    }
    
    // #05. Chinh sua mot slang word
    public static void editSlangWord(ArrayList<slang_word> swList) throws IOException{
        Scanner scanner = new Scanner(System.in);
        String key = null;
        String definiton = null;
        ArrayList<String> definitionList = new ArrayList<>();
        
        boolean exist = false;
        int choose = 0;
        
        System.out.print("Ban muon chinh sua slang word nao: ");
        key = scanner.nextLine();
        
        for(slang_word sw: swList){
            if(key.equals(sw.key)){
                exist = true;
                
                //Chinh slang word hay definition
                System.out.println("Ban muon chinh sua slang word hay definition?");
                System.out.println(" + Phim 1: slang word");
                System.out.println(" + Phim 2: definition");
                
                do{
                    System.out.print("Ban chon: ");
                    choose = getInt();

                    if(choose != 1 && choose != 2){
                        System.out.println("Khong hop le. Hay nhap lai!!");
                    }
                    else{
                        break;
                    }
                }while(true);
                
                //Chinh sua slang word
                if(choose == 1){
                    System.out.print("Slang word moi: ");
                    sw.key = scanner.nextLine();
                    
                    System.out.println("Da chinh slang word '" + key + "' thanh '" + sw.key + "'.");
                }
                //Chinh sua definition
                else{
                    //Neu chi cos 1 definition
                    if(sw.definition.size() == 1){
                        System.out.println("Definition hien tai cua slang word '" + key + "' la: " + sw.definition.get(0)); 
                        System.out.print("Definition moi la: ");
                        sw.definition.set(0, scanner.nextLine());
                    }
                    //Co nhieu definition
                    else{
                        do{
                            //Lua chon definition chinh sua
                            System.out.println("Ban muon chinh sua definition nao cua slang word '" + key + "'?");
                            
                            for(int i = 0; i < sw.definition.size(); i++){
                                System.out.println(" + Phim " + (i + 1) + ": " + sw.definition.get(i));
                            }
                            
                            do{
                                System.out.print("Ban chon: ");
                                choose = getInt();

                                if(choose < 1 || choose > sw.definition.size()){
                                    System.out.println("Khong hop le. Hay nhap lai!!");
                                }
                                else{
                                    break;
                                }
                            }while(true);

                            System.out.print("Chinh '" + sw.definition.get(choose - 1) + "' thanh: ");
                            sw.definition.set(choose - 1, scanner.nextLine());   
                            
                            //Co muon tiep tuc chinh sua definition hay ket thuc
                            System.out.println("Ban co muon tiep tuc chinh sua definition cua '" + key + "' khong?");
                            System.out.println(" + Phim 1: Co");
                            System.out.println(" + Phim 0: Khong");
                            
                            do{
                                System.out.print("Ban chon: ");
                                choose = getInt();

                                if(choose != 1 && choose != 0){
                                    System.out.println("Khong hop le. Hay nhap lai!!");
                                }
                                else{
                                    break;
                                }
                            }while(true);
                        
                        }while(choose == 1);
                    }
                }
                break;
            }
        }
        
        //Neu slang word khong co trong danh sach
        if(exist == false){
            System.out.println("Khong ton tai slang word '" + key + "' trong danh sach.");
        }        
    }
    
    // #06. Xoa mot slang word. Confirm truoc khi xoa.
    public static void deleteSlangWord(ArrayList<slang_word> swList) throws IOException{
        Scanner scanner = new Scanner(System.in);
        String key = null;
        boolean exist = false;
        int choose = 0;
        System.out.print("Ban muon xoa slang word nao: ");
        key = scanner.nextLine();
        
        
        for(slang_word sw: swList){
            if(key.equals(sw.key)){
                exist = true;
                //Xac nhan co xoa slang word hay khong
                System.out.println("Ban co chac la muon xoa slang word '" + key + "' hay khong?");
                System.out.println(" + Phim 1: Xoa");
                System.out.println(" + Phim 0: Tro lai");
                
                do{
                    System.out.print("Ban chon: ");
                    choose = getInt();

                    if(choose != 0 && choose != 1){
                        System.out.println("Khong hop le. Hay nhap lai!!");
                    }
                    else{
                        break;
                    }
                }while(true);
                
                //Khi xac nhan ddong y xoa slang word
                if(choose == 1){
                    //Xoa khoi ArrayList
                    swList.remove(sw);
                    
                    System.out.println("Da xoa thanh cong slang word '" + key + "' ra khoi danh sach.");
                }
                //Khong dong y xoa slang word.
                else{
                    System.out.println("Thao tac xoa slang word '" + key + "' da huy bo.");                    
                }
                break;
            }
        }
        
        //Neu slang word khong co trong danh sach
        if(exist == false){
            System.out.println("Khong ton tai slang word '" + key + "' trong danh sach.");
        }
    }
    
    // #08. Ramdom 1 slang word(On this slang word)
    public static slang_word randromSlangWord(ArrayList<slang_word> swList){
        Random rd = new Random();
        return swList.get(rd.nextInt(swList.size()));
    }
    
    // #09. Do vui: Hien thi mot slang word va 4 dap an cho nguoi dung chon.
    public static void gameSlangWord(ArrayList<slang_word> swList){
        //Random slang word ngau nhien.
        Random rd = new Random();
        int swId = rd.nextInt(swList.size());
        slang_word sw = swList.get(swId);
        
        //Bien lua chon dap an
        int choose = 0; 

        //Mang luu dap an
        String[] options = new String[4];
        
        //Chon vi tri cua dap an dung trong 4 dap an
        int rightId = rd.nextInt(4);
        
//        //Chon dap an dung (Truong hop slang word co nhieu nghia)
//        options[rightId] = sw.definition.get(rd.nextInt(sw.definition.size()));        
        
        //Tao cac dap an
        int tempId = swId + 1;
        for(int i = 0; i < 4; i++){
            if(i != rightId){
                //Cac dap an sai là cac slang word - definition ke tiep trong danh sach
                //Truong hop slang word dung o cuoi danh sach thi se lay cac phan tu phia truoc cua slang word - definition dung.
                if(tempId == swList.size()){
                    if(i > rightId)
                        tempId = swId - ( 4 - i);
                    else
                        tempId = swId - ( 3 - i);
                }
                
                options[i] = swList.get(tempId).definition.get(rd.nextInt(swList.get(tempId).definition.size()));
                tempId++;
            }
            else{
                //Chon dap an dung (Truong hop slang word co nhieu nghia)
                options[rightId] = sw.definition.get(rd.nextInt(sw.definition.size())); 
            }
        }
        
        System.out.println("slang word: " + sw.key);
        for(int i = 0; i< 4; i++){
            System.out.println("\tPhim " + (i +1) + ". " + options[i]);
        }

        do{
            System.out.print("Ban chon: ");
            choose = getInt();
            
            //Kiem tra tinh hop le cua dap an.
            if(choose < 1 || choose > 4){
                System.out.println("Lua chon khong hop le. Hay chon lai.");
            }
            else{
                break;
            }
        }while(true);
        
        if(choose - 1 == rightId){
            System.out.println("Chuc mung ban da chon dung: " + sw.key + " = " + options[rightId]);
        }
        else{
            System.out.println("Ban da chon '" + options[choose - 1] + "' sai roi. Dap an dung ne: " + sw.key + " = " + options[rightId] + "(Phim " + (rightId + 1) + ")");
        }
    }
    
    // #10. Do vui: Hien thi mot slang word va 4 dap an cho nguoi dung chon.
    public static void gameDefinition(ArrayList<slang_word> swList){
        //Random definition ngau nhien.
        Random rd = new Random();
        int defId = rd.nextInt(swList.size());
        slang_word def = swList.get(defId);
        
        //Definition duoc chon
        String definition = def.definition.get(rd.nextInt(def.definition.size()));
        
        //Bien lua chon dap an
        int choose = 0; 

        //Mang luu dap an
        String[] options = new String[4];
        
        //Chon vi tri cua dap an dung trong 4 dap an
        int rightId = rd.nextInt(4);
        
        //Tao cac dap an
        int tempId = defId + 1;
        for(int i = 0; i < 4; i++){
            if(i != rightId){
                //Cac dap an sai là cac slang word - definition ke tiep trong danh sach
                //Truong hop definition dung o cuoi danh sach thi se lay cac phan tu phia truoc cua slang word - definition dung.
                if(tempId == swList.size()){
                    if(i > rightId)
                        tempId = defId - ( 4 - i);
                    else
                        tempId = defId - ( 3 - i);
                }
                
                options[i] = swList.get(tempId).key;
                tempId++;
            }
            else{
                options[i] = def.key;
            }
        }
        
        System.out.println("Definition: " + definition);
        for(int i = 0; i< 4; i++){
            System.out.println("\tPhim " + (i +1) + ". " + options[i]);
        }

        do{
            System.out.print("Ban chon: ");
            choose = getInt();
            
            //Kiem tra tinh hop le cua dap an.
            if(choose < 1 || choose > 4){
                System.out.println("Lua chon khong hop le. Hay chon lai.");
            }
            else{
                break;
            }
        }while(true);
        
        if(choose - 1 == rightId){
            System.out.println("Chuc mung ban da chon dung: " + options[rightId] + " = " + definition);
        }
        else{
            System.out.println("Ban da chon '" + options[choose - 1] + "' sai roi. Dap an dung ne: " + options[rightId] + " = " + definition + "(Phim " + (rightId + 1) + ")");
        }
    }
    
    
    
}
