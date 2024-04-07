package org.example;
import org.springframework.http.converter.json.GsonBuilderUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
public class Program {

    public static void main (String[]args ){
        Scanner sc = new Scanner (System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        List<Product> list = new ArrayList<>();

        System.out.print("Enter the number of products: ");
        int n = sc.nextInt();
        for (int i=1; i<=n; i++ ){
            System.out.println("Product #" + i + " data:");
            System.out.print("Common, used or imported (c/u/i)? ");
            char ch = sc.next().charAt(0);
            System.out.print("Name: ");
            sc.nextLine();
            String name = sc.nextLine();
            System.out.print("Price: ");
            double price = sc.nextDouble();
            if (ch == 'i'){
                System.out.print("Customs fee: ");
                double customsFee = sc.nextDouble();
                Product pi = new ImportedProduct(name, price, customsFee);
                list.add(pi);
            }
            else if (ch == 'u'){
                System.out.print("Manufactured Date (DD/MM/YYYY): ");
                sc.nextLine();
                String manufacturedDate = sc.nextLine();
                Product pu = new UsedProduct(name, price, manufacturedDate);
                list.add(pu);
            }
            else {
                Product pc = new Product(name, price);
                list.add(pc);
            }

            }
        System.out.println();
        System.out.println("PRICE TAGS: ");
        for (Product pciu : list){
            System.out.println(pciu.toString());

        }


        sc.close();
    }
}
