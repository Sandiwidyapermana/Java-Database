package com.view;

import java.util.Scanner;

import com.config.cConfig;

public class cView {

private static Scanner input = new Scanner(System.in);

public static void index()
{
    while(true){

        System.out.print("\n=== MENU ===\n"
        + "1. Lihat Semua Data Barang\n"
        + "2. Detail Data Barang\n"
        + "3. Cari Data Barang\n"
        + "4. Tambah Data Barang\n"
        + "5. Update Data Barang\n"
        + "6. Delete Data Barang\n"
        + "0. Exit\n"
        + "Pilih[1/2/3/4/5/0] : ");

        String pilihan = input.next();

        if( pilihan.equalsIgnoreCase("0") ){
            System.out.println("Terimakasih!!");
            break;
        }

        switch (pilihan) {
            case "1" :
                getALLData();
                break;
            case "2" :
                detailData();
                break;
            case "3" :
                cariData();
                break;
            case "4" :
                tambahData();
                break;
            case "5" :
                updateData();
                break;
            case "6" :
                deleteData();
                break;
            default:
                System.out.println("Pilihan salah!!");
                break;
        }

              
     }
}

public static void getALLData()

{
    // Pesan Header
    System.out.println("\n::: DATA BARANG :::");
    // Data semua barangnya
    System.out.println(cConfig.getALLData());

}

public static void detailData()
{
    System.out.println("\n::: DETAIL DATA BARANG :::");

    System.out.print("Masukkan ID Barang : ");
    int id = input.nextInt();

    System.out.println("Hasil Data");
    System.out.println(cConfig.detailData(id));
}

public static void cariData()
{
    System.out.println("\n::: CARI DATA :::");
    System.out.print("\nMasukkan Keyword : ");
    String keyword = input.nextLine();
    keyword = input.nextLine();

    System.out.println("\nHasil Data");
    System.out.println(cConfig.cariData(keyword));
}

public static void tambahData()
{

    System.out.println("\n::: TAMBAH DATA BARANG :::");
    System.out.print("Masukkan Nama Barang : ");
    String nama_barang = input.nextLine();
    nama_barang = input.nextLine();
    System.out.print("Masukkan Stok Barang : ");
    int stok_kg = input.nextInt();
    System.out.print("Masukkan Harga Barang : ");
    int harga_kg = input.nextInt();
    
    if ( !cConfig.tambahData(nama_barang, stok_kg, harga_kg) ){
        System.out.println("Data Barang Berhasil Ditambahkan!");
        cView.getALLData();
    } else{
        System.out.println("Data Barang Gagal Ditambahkan!");
    }

}


public static void updateData()
{
    System.out.println("\n::: UPDATE DATA BARANG :::");
    System.out.print("Masukkan ID Barang : ");
    int id_barang = input.nextInt();
    System.out.println("\nGanti Data Barang\n");
    System.out.print("Nama Barang (Kosongkan jika tidak ingin mengubah datanya) : ");
    String nama_barang = input.nextLine();
    nama_barang = input.nextLine();

    System.out.print("Stok Barang (isi 0 jika tidak ingin merubah data) : ");
    int stok_kg = input.nextInt();
    
    System.out.print("Harga Barang (isi 0 jika tidak ingin merubah data) : ");
    int harga_kg_kg = input.nextInt();

    if ( cConfig.updateData(id_barang, nama_barang, stok_kg, harga_kg_kg) ){
        System.out.println("Data Berhasil Dirubah!");
        getALLData();
    }else{
        System.out.println("Data Barang Gagal Dirubah !");
    }

}

public static void deleteData()
{
    System.out.println("\n::: DELETE DATA BARANG :::");
    System.out.print("Masukkan ID Barang : ");
    int id_barang = input.nextInt();

    if( cConfig.deleteData(id_barang) ){
        System.out.println("Data Barang Berhasil Dihapus!");
        getALLData();
    }else{
        System.out.println("Data Barang Gagal Dihapus!");
    }
}

}
