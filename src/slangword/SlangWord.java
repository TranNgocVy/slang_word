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
//        addNewSlangWord(filename);
        deleteSlangWord(filename);
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
    
    //Them mot slang word moi vao file
    public static void addNewSlangWord(String filename) throws IOException {
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
        
        BufferedWriter bw = new BufferedWriter(new FileWriter(filename, true));
        bw.write(key + "`" + String.join("| ", defineList) + "\n");
        bw.close();
    }
    
    //Xoa mot slang word. Confirm truoc khi xoa.
    public static void deleteSlangWord(String filename) throws IOException{
        Scanner scanner = new Scanner(System.in);
        String key = null;
        boolean exist = false;
        int choose = 0;
        System.out.print("Ban muon xoa slang word nao: ");
        key = scanner.nextLine();
        
        ArrayList<slang_word> swList = readFile(filename);
        
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
                    
                    //Ghi lai vao file
                    BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
                    
                    for(slang_word temp: swList){
                        bw.write(temp.key + "`" + String.join("| ", temp.definition) + "\n");
                    }
                    bw.close();
                    
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
}
