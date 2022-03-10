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
        addNewSlangWord(filename);
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
    
    //Them mot slang word moi vao file
    public static void addNewSlangWord(String filename) throws IOException {
        String key = null;
        String definition = null;
        ArrayList<String> defineList = new ArrayList<>();
        int choose = 0; 
        boolean isValid = true;
        
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
                isValid = true;
                
                System.out.print("Ban chon: ");
                
                try{
                    choose = scanner.nextInt();
                    

                }catch(Exception e){
                    isValid = false; 
                }
                
                //Loai bo dau enter (tuong tu lenh ignore() trong c++)
                scanner.nextLine();

                if(isValid == false || (choose != 0 && choose != 1)){
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
}
