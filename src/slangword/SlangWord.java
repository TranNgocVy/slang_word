/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package slangword;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

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
    }
    
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
                    System.out.println(line);
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
        return swList;
    }
}
