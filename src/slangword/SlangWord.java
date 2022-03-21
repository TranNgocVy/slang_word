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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    static Scanner scanner;

    public static void main(String[] args) throws IOException {
        scanner = new Scanner(System.in);

        String filename = "slang1.txt";
        Map<String, ArrayList<String>> map = readFile(filename);
//        System.out.println(map.size());
//        for (Map.Entry<String, ArrayList<String>> entry : map.entrySet()) {
//            System.out.println(entry.getKey() + " = " + entry.getValue());
//        }
        
//        System.out.println("--------------Random--------------------");
//
//        for (int i = 0; i < 3; i++) {
//            String key = randromSlangWord(map);
//            System.out.println(key + " = " + map.get(key));
//
//        }

        // for (int i = 0; i < 3; i++) {
        // findBasedOnSlangWord(map);
        // showHistory();
        // }

        // for (int i = 0; i < 3; i++) {
        // findBasedOnDefinition(map);
        // }

        // slang_word randonslw = randromSlangWord(swList);
        // System.out.print("Ramdom slang word: " + randonslw.key + " = " +
        // randonslw.definition.get(0));
        // for(int i = 1; i < randonslw.definition.size(); i++){
        // System.out.print(", " + randonslw.definition.get(i));
        // }
        // System.out.println("");

//        for (int i = 0; i < 3; i++) {
//            addNewSlangWord(map);
//            for (Map.Entry<String, ArrayList<String>> entry : map.entrySet()) {
//                System.out.println(entry.getKey() + " = " + entry.getValue());
//            }
//        }

//        for (int i = 0; i < 3; i++) {
//            deleteSlangWord(map);
//            for (Map.Entry<String, ArrayList<String>> entry : map.entrySet()) {
//                System.out.println(entry.getKey() + " = " + entry.getValue());
//            }
//        }

//        for (int i = 0; i < 3; i++) {
//            editSlangWord(map);
//            for (Map.Entry<String, ArrayList<String>> entry : map.entrySet()) {
//                System.out.println(entry.getKey() + " = " + entry.getValue());
//            }
//        }

        for (int i = 0; i < 2; i++) {
            gameSlangWord(map);
        }

        for (int i = 0; i < 2; i++) {
            gameDefinition(map);
        }
        scanner.close();
    }

    // Doc file luu vao Map
    public static Map<String, ArrayList<String>> readFile(String filename) throws IOException {
        Map<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));

            String line = br.readLine();
            int point = line.indexOf('`');

            String key = line.substring(0, point);
            String defString = null;
            ArrayList<String> defList = new ArrayList<String>();

            // Cat lay phan dinh nghia cua slang word
            defString = line.substring(point + 1);

            do {
                // Xac dinh vi tri phan cach giua cac nghia (doi voi tu co nhieu nghia)
                int point1 = defString.indexOf('|');

                // Neu tu co nhieu nghia: Dua tung nghia vao ArrayList
                if (point1 >= 0) {
                    defList.add(defString.substring(0, point1));
                    defString = defString.substring(point1 + 2);
                } else {
                    // Dua nghia duy nhat (cuoi cung) vao ArrayList
                    defList.add(defString);
                    break;
                }
            } while (true);

            do {
                line = br.readLine();

                if (line != null) {
                    point = line.indexOf('`');

                    if (point < 0) {
                        defString = line;
                        do {
                            // Xac dinh vi tri phan cach giua cac nghia (doi voi tu co nhieu nghia)
                            int point1 = defString.indexOf('|');

                            // Neu tu co nhieu nghia: Dua tung nghia vao ArrayList
                            if (point1 >= 0) {
                                defList.add(defString.substring(0, point1));
                                defString = defString.substring(point1 + 2);
                            } else {
                                // Dua nghia duy nhat (cuoi cung) vao ArrayList
                                defList.add(defString);
                                break;
                            }
                        } while (true);

                    } else {
                        // Dua vao trong map
                        map.put(key, defList);

                        defList = new ArrayList<>();

                        key = line.substring(0, point);

                        // Cat lay phan dinh nghia cua slang word
                        defString = line.substring(point + 1);

                        do {
                            // Xac dinh vi tri phan cach giua cac nghia (doi voi tu co nhieu nghia)
                            int point1 = defString.indexOf('|');

                            // Neu tu co nhieu nghia: Dua tung nghia vao ArrayList
                            if (point1 >= 0) {
                                defList.add(defString.substring(0, point1));
                                defString = defString.substring(point1 + 2);
                            } else {
                                // Dua nghia duy nhat (cuoi cung) vao ArrayList
                                defList.add(defString);
                                break;
                            }
                        } while (true);
                    }
                } else {
                    map.put(key, defList);

                    br.close();
                    break;
                }
            } while (true);
        } catch (IOException e) {
        }
        return map;
    }

    // Nhap vao mot so nguyen tu man hinh console
    public static int getInt() {
        boolean isValid = true;
        int choose = 0;

        do {
            isValid = true;

            try {
                choose = scanner.nextInt();
            } catch (Exception e) {
                isValid = false;
            }

            // Loai bo dau enter (tuong tu lenh ignore() trong c++)
            scanner.nextLine();

            if (isValid == false) {
                System.out.println("Khong hop le. Hay nhap lai!!");
                System.out.print("Ban chon: ");

            } else {
                break;
            }
        } while (true);

        return choose;
    }

    // #01. Tim kiem theo slang word
    public static void findBasedOnSlangWord(Map<String, ArrayList<String>> map) throws IOException {
        String key = null;

        System.out.print("Nhap slang word can tim kiem: ");
        key = scanner.nextLine();

        if (map.containsKey(key)) {
            ArrayList<String> defList = map.get(key);
            System.out.print("Slang word can tim la: " + key + " = " + defList.get(0));

            for (int i = 1; i < defList.size(); i++) {
                System.out.print(", " + defList.get(i));
            }
            System.out.println("");

            // Them vao file history.txt
            BufferedWriter bw = new BufferedWriter(new FileWriter("history.txt", true));
            bw.write(key + "`" + String.join("| ", defList) + "\n");
            bw.close();
        } else {
            System.out.println("Khong ton tai slang word '" + key + "' trong danh sach.");
        }
    }

    // #02. Tim kiem theo definition
    public static void findBasedOnDefinition(Map<String, ArrayList<String>> map) throws IOException {
        String definition = null;
        Map<String, ArrayList<String>> resultMap = new HashMap<String, ArrayList<String>>();

        System.out.print("Nhap definition can tim kiem: ");
        definition = scanner.nextLine();

        // Chuyen definition ve dang chu thuong
        definition = definition.toLowerCase();

        for (Map.Entry<String, ArrayList<String>> entry : map.entrySet()) {
            for (String def : entry.getValue()) {
                if (def.toLowerCase().contains(definition)) {
                    resultMap.put(entry.getKey(), entry.getValue());
                }
            }
        }

        if (resultMap.size() == 0) {
            System.out.println("Khong ton tai slang word ma definition co chua: " + definition);
        } else {
            System.out.println("Slang word ma trong definition co chua '" + definition + "' la:");
            for (Map.Entry<String, ArrayList<String>> entry : resultMap.entrySet()) {
                System.out.print("\t" + entry.getKey() + " = " + entry.getValue().get(0));
                for (int i = 1; i < entry.getValue().size(); i++) {
                    System.out.print("| " + entry.getValue().get(i));
                }
                System.out.println("");
            }
        }

    }

    // #03. Hien thi lich su cac slang word da tra cuu
    public static void showHistory() throws IOException {
        Map<String, ArrayList<String>> map = readFile("history.txt");

        if (map.size() == 0) {
            System.out.println("Lich su rong.");
        } else {
            System.out.println("Danh sach ca slang word da tra la: ");
            for (Map.Entry<String, ArrayList<String>> entry : map.entrySet()) {
                System.out.print("\t + " + entry.getKey() + " = " + entry.getValue().get(0));

                for (int i = 1; i < entry.getValue().size(); i++) {
                    System.out.print("| " + entry.getValue().get(i));
                }

                System.out.println("");
            }
        }
    }

    // #04. Them mot slang word moi vao file
    public static void addNewSlangWord(Map<String, ArrayList<String>> map) {
        String key = null;
        String definition = null;
        ArrayList<String> defList = new ArrayList<>();
        int choose = 0;

        System.out.print("Slang word moi: ");
        key = scanner.nextLine();

        do {
            System.out.print("Nghia cua slang word moi: ");
            definition = scanner.nextLine();

            defList.add(definition);

            System.out.println("Con nghia nao khac nua hay khong?");
            System.out.println(" + Phim 1: Co");
            System.out.println(" + Phim 0: Khong");

            do {
                System.out.print("Ban chon: ");
                choose = getInt();

                if (choose != 0 && choose != 1) {
                    System.out.println("Khong hop le. Hay nhap lai!!");
                } else {
                    break;
                }
            } while (true);
        } while (choose == 1);

        map.put(key, defList);

        // BufferedWriter bw = new BufferedWriter(new FileWriter(filename, true));
        // bw.write(key + "`" + String.join("| ", defineList) + "\n");
        // bw.close();
    }

    // #05. Chinh sua mot slang word
    public static void editSlangWord(Map<String, ArrayList<String>> map) {
        String key = null;
        int choose = 0;

        System.out.print("Ban muon chinh sua slang word nao: ");
        key = scanner.nextLine();

        if (map.containsKey(key)) {
            ArrayList<String> defList = map.get(key);
            // Chinh slang word hay definition
            System.out.println("Ban muon chinh sua slang word hay definition?");
            System.out.println(" + Phim 1: slang word");
            System.out.println(" + Phim 2: definition");

            do {
                System.out.print("Ban chon: ");
                choose = getInt();

                if (choose != 1 && choose != 2) {
                    System.out.println("Khong hop le. Hay nhap lai!!");
                } else {
                    break;
                }
            } while (true);

            // Chinh sua slang word
            if (choose == 1) {
                System.out.print("Slang word moi: ");
                String newKey = scanner.nextLine();

                // Kiem tra slang word sau khi chinh da ton tai hay chua
                if (map.containsKey(newKey)) {
                    System.out.println("Trong danh sach da cho slang word '" + newKey
                            + "' nen khong the chinh sua slang word '" + key + "' thanh '" + newKey + "'");
                } else {
                    map.put(newKey, defList);
                    map.remove(key);
                    key = newKey;

                    System.out.println("Da chinh slang word '" + key + "' thanh '" + newKey + "'");
                }
            }
            // Chinh sua definition
            else {
                do {
                    // Lua chon definition chinh sua
                    System.out.println("Ban muon chinh sua definition nao cua slang word '" + key
                            + "' hay la them definition moi?");

                    System.out.println(" + Phim " + 0 + ": Them 1 definition");
                    for (int i = 1; i <= defList.size(); i++) {
                        System.out.println(" + Phim " + i + ": " + defList.get(i - 1));
                    }

                    do {
                        System.out.print("Ban chon: ");
                        choose = getInt();

                        if (choose < 0 || choose > defList.size()) {
                            System.out.println("Khong hop le. Hay nhap lai!!");
                        } else {
                            break;
                        }
                    } while (true);

                    if (choose == 0) {
                        System.out.print("Definition moi la: ");
                        defList.add(scanner.nextLine());
                    } else {
                        System.out.print("Chinh '" + defList.get(choose - 1) + "' thanh: ");
                        defList.set(choose - 1, scanner.nextLine());
                    }

                    // Co muon tiep tuc chinh sua definition hay ket thuc
                    System.out.println("Ban co muon tiep tuc chinh sua definition cua '" + key + "' khong?");
                    System.out.println(" + Phim 1: Co");
                    System.out.println(" + Phim 0: Khong");

                    do {
                        System.out.print("Ban chon: ");
                        choose = getInt();

                        if (choose != 1 && choose != 0) {
                            System.out.println("Khong hop le. Hay nhap lai!!");
                        } else {
                            break;
                        }
                    } while (true);

                } while (choose == 1);

                map.put(key, defList);
            }
        } else {
            System.out.println("Khong ton tai slang word '" + key + "' trong danh sach.");
        }
    }

    // // #06. Xoa mot slang word. Confirm truoc khi xoa.
    public static void deleteSlangWord(Map<String, ArrayList<String>> map) {
        String key = null;
        boolean exist = false;
        int choose = 0;
        System.out.print("Ban muon xoa slang word nao: ");
        key = scanner.nextLine();

        if(map.containsKey(key)){
            // Xac nhan co xoa slang word hay khong
                System.out.println("Ban co chac la muon xoa slang word '" + key + "' hay khong ? ");
                System.out.println(" + Phim 1: Xoa");
                System.out.println(" + Phim 0: Tro lai");

                do {
                    System.out.print("Ban chon: ");
                    choose = getInt();

                    if (choose != 0 && choose != 1) {
                        System.out.println("Khong hop le. Hay nhap lai!!");
                    }
                    else {
                        break;
                    }
                } while (true);

                // Khi xac nhan dong y xoa slang word
                if (choose == 1) {
                    // Xoa khoi ArrayList
                    map.remove(key);

                    System.out.println("Da xoa thanh cong slang word '" + key + "' ra khoi danh sach.");
                }
                // Khong dong y xoa slang word.
                else {
                    System.out.println("Thao tac xoa slang word '" + key + "' da huy bo.");
                }
        }
        // Neu slang word khong co trong danh sach
        else {
            System.out.println("Khong ton tai slang word '" + key + "' trong danh sach.");
        }
    }

    // #08. Ramdom 1 slang word(On this slang word)
    public static String randromSlangWord(Map<String, ArrayList<String>> map) {
        Random rd = new Random();
        List<String> keyList = new ArrayList<String> (map.keySet());
        return keyList.get(rd.nextInt(keyList.size()));
    }

    // #09. Do vui: Hien thi mot slang word va 4 dap an cho nguoi dung chon.
    public static void gameSlangWord(Map<String, ArrayList<String>> map) {
        // Random slang word ngau nhien.
        Random rd = new Random();
        Map<String, String> optionMap = new HashMap<>();
        
        // Chon vi tri cua dap an dung trong 4 dap an
        int trueId = rd.nextInt(4);
        String trueKey = null;
        
        // Bien lua chon dap an
        int choose = 0;
        
        //Tao de
        for (int i = 0; i < 4; i++) {
            do {
                String key = randromSlangWord(map);
                
                if (!optionMap.containsKey(key)) {
                    optionMap.put(key, map.get(key).get(rd.nextInt(map.get(key).size())));
                    if(i == trueId){
                        trueKey = key;
                    }
                           
                    break;
                }
            } while (true);
        }
        
        System.out.println("slang word: " + trueKey);
        
        List<String> defList = new ArrayList<>(optionMap.values());
        
        for(int i = 0; i < 4; i++){
            System.out.println("\tPhim " + (i + 1) + ". " + defList.get(i));
            
            //Vi khi dua vo map se khong theo thu tu nen ta can set lai chi so cua dap an dung
            if(optionMap.get(trueKey).equals(defList.get(i))){
                trueId = i + 1;
            }
        }

        do {
            System.out.print("Ban chon: ");
            choose = getInt();

            // Kiem tra tinh hop le cua dap an.
            if (choose < 1 || choose > 4) {
                System.out.println("Lua chon khong hop le. Hay chon lai.");
            } else {
                break;
            }
        } while (true);

        if (choose == trueId) {
            System.out.println("Chuc mung ban da chon dung: " + trueKey + " = " + optionMap.get(trueKey));
        } else {
            System.out.println("Ban da chon sai roi. Dap an dung ne: " + trueKey + " = " + optionMap.get(trueKey) + "(Phim " + trueId + ")");
        }
    }

    // #10. Do vui: Hien thi mot slang word va 4 dap an cho nguoi dung chon.
    public static void gameDefinition(Map<String, ArrayList<String>> map) {
        // Random slang word ngau nhien.
        Random rd = new Random();
        Map<String, String> optionMap = new HashMap<>();
        
        // Chon vi tri cua dap an dung trong 4 dap an
        int trueId = rd.nextInt(4);
        String trueDefinition = null;
        
        // Bien lua chon dap an
        int choose = 0;
        
        //Tao de
        for (int i = 0; i < 4; i++) {
            do {
                String key = randromSlangWord(map);
                
                if (!optionMap.containsKey(key)) {
                    optionMap.put(key, map.get(key).get(rd.nextInt(map.get(key).size())));
                    if(i == trueId){
                        trueDefinition = optionMap.get(key);
                    }
                           
                    break;
                }
            } while (true);
        }
        
        System.out.println("Definition: " + trueDefinition);
        
        List<String> keyList = new ArrayList<>(optionMap.keySet());
        
        for(int i = 0; i < 4; i++){
            System.out.println("\tPhim " + (i + 1) + ". " + keyList.get(i));
            
            //Vi khi dua vo map se khong theo thu tu nen ta can set lai chi so cua dap an dung
            if(trueDefinition.equals(optionMap.get(keyList.get(i)))){
                trueId = i + 1;
            }
        }

        do {
            System.out.print("Ban chon: ");
            choose = getInt();

            // Kiem tra tinh hop le cua dap an.
            if (choose < 1 || choose > 4) {
                System.out.println("Lua chon khong hop le. Hay chon lai.");
            } else {
                break;
            }
        } while (true);

        if (choose == trueId) {
            System.out.println("Chuc mung ban da chon dung: " + trueDefinition+ " = " + keyList.get(trueId - 1));
        } else {
            System.out.println("Ban da chon sai roi. Dap an dung ne: " + trueDefinition + " = " + keyList.get(trueId - 1) + "(Phim " + trueId + ")");
        }
     }

}
