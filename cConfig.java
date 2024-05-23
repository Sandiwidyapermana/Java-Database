package com.config;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;






public class cConfig {

    // Ini untuk mendefinisikan koneksi database kita
    // private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/db_sandi";
    private static final String USER = "root";
    private static final String PASS = "";

    // Ini untuk instansiasi object dari class yang sudah diimpor
    private static Connection connect;
    private static Statement statement;
    private  static ResultSet resultData;

    // Ini adalah method static connection
    private static void connection()
    {
    // Method untuk melakukan koneksi ke database
    try {
    // Registrasi driver yang akan dipakai
    // Class.forName(JDBC_DRIVER); *1
    // DriverManager.registerDriver(new com.mysql.jdbc.Driver() ); *2

    // Buat koneksi ke database
    connect = DriverManager.getConnection(DB_URL, USER, PASS);
    } catch (Exception e) {
        // Kalau ada error saat koneksi
        // Maka tampilkan error nya 
        e.printStackTrace();
    }
    }


    public static String getALLData()
    {
        connection();

        // Isi nila dari default variabel data
        String data = "Data Tidak Ada";

        try {
            
            // Buat objek statement yang ambil dari koneksi
            statement = connect.createStatement();

            // Quey select all data from database
            String query = "SELECT id_barang, nama_barang FROM tbl_barang WHERE is_aktif = '1'";

            // Eksekusi query nya
            resultData = statement.executeQuery(query);

            // Set variabel data jadu null
            data = "";

            int count = 0;
            // Looping pengisian variabel data
            while (resultData.next() ) {
               data += "ID Barang : " + resultData.getInt("id_barang") + ", Nama Barang : " +resultData.getString("nama_barang") + "\n";
            count++;
            }

            if ( count == 0 ){
                data = "Data Tidak Ditemukan";
            }

            // Close statement dan connection   
            statement.close();
            connect.close();


        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }


    public static String detailData( int id)
    {
        // Panggil method static connection
        connection();
        // Nilai default yang dikembalikan
        String data = "Data Tidak Ditemukan";

        try {

            // Siapin objek untuk statement
            statement = connect.createStatement();

            // Buat query nya
            String query = "SELECT * FROM tbl_barang WHERE is_aktif = '1' AND id_barang = " + id;

            // Eksekusi query nya dan simpan ke result set
            resultData = statement.executeQuery(query);

            // Set variabel data jadi null dulu
            data = "";

            int count = 0;
            // looping untuk pengisian variabel data
            while( resultData.next() ){
                data += "- ID Barang : " + resultData.getInt("id_barang")
                + "\n- Nama Barang : " + resultData.getString("nama_barang")
                + "\n- Stok Barang : " + resultData.getInt("stok_kg")
                + " kg\n- Harga Barang : Rp." + resultData.getInt("harga_kg");
                count++;
            
            }
            if ( count == 0 ){
                data = "Data Tidak Ditemukan";
            }

           // Close statement dan koneksinya
           statement.close();
           connect.close();
            
        } catch (Exception e) {
            // Tampilkan errornya kalau ada error
            e.printStackTrace();
        }


        // Pengembalian string dari method ini
        return data;

    }
    public static String cariData( String keyword )
{
    connection();

    String data = "Data Tidak Ditemukan";

    try {

        statement = connect.createStatement();

        String query = "SELECT * FROM tbl_barang WHERE is_aktif = '1'AND ( id_barang LIKE '%" + keyword + "%' OR nama_barang LIKE '%" + keyword + "%' OR harga_kg LIKE '%" + keyword + "%' OR stok_kg LIKE '%" + keyword + "%')";

        resultData = statement.executeQuery(query);

        data = "";

        int count = 0;
        while( resultData.next()){
            data += "- ID Barang : " + resultData.getInt("id_barang")
            + "\n- Nama Barang : " + resultData.getString("nama_barang")
            + "\n- stok Barang : " + resultData.getInt("stok_kg")
            + " kg\n- Harga Barang : Rp." + resultData.getInt("harga_kg");
            data += "\n==============================\n";
            count++;
        }

        if ( count == 0 ){
            data = "Data Tidak Ditemukan";
        }
        
    // Close statement dan koneksinya
    statement.close();
    connect.close();

    } catch (Exception e) {
        e.printStackTrace();
    }

    return data;

}

    public static boolean tambahData( String nama_barang, int stok_kg, int harga_kg )
    {
        connection();
        boolean data = false;
        int total= stok_kg * harga_kg;

    try {

        statement = connect.createStatement();

        String query = "INSERT INTO `tbl_barang` VALUES ("+null+",'"+nama_barang+"',"+stok_kg+","+harga_kg+","+total+", '1')";

        if (statement.execute(query)){
            data = true;
        }


        // Close statement dan koneksi
        statement.close();
        connect.close();
    
    } catch (Exception e) {
    e.printStackTrace();
    }

        return data;
    }
    public static boolean updateData (int id_barang, String nama_barang, int stok_kg, int harga_kg )
    {

        connection();
        boolean data = false;

        try {

            statement = connect.createStatement();

            String queryCek= "SELECT * FROM tbl_barang WHERE id_barang = " + id_barang;

            resultData = statement.executeQuery(queryCek);
            // Siapin variabel untuk menampung data pada field  satu row
            String namaCek = "";
            int stokCek = 0, hargaCek = 0;

            while ( resultData.next() ){
                namaCek = resultData.getString("nama_barang");
                stokCek = resultData.getInt("stok_kg");
                hargaCek = resultData.getInt("harga_kg");
            }

            // Validasi jika yang diisi diconsole kosong
            if ( !nama_barang.equalsIgnoreCase("") ){
                namaCek = nama_barang;
            }
            if ( stok_kg != 0 ){
                stokCek = stok_kg;
            }
            if ( harga_kg != 0 ){
                hargaCek = harga_kg;
            }

            String queryUpdate = "UPDATE tbl_barang SET nama_barang = '" + namaCek + "', stok_kg = " + stokCek + ", harga_kg = " + hargaCek + " WHERE id_barang = " + id_barang ;
            
            if( !statement.execute(queryUpdate) ){
            data = true;
            }

        // Close statement dan close koneksi
        statement.close();
        connect.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;

    }

    public static boolean deleteData( int id_barang )
    {
        connection();
        boolean data = false;

        try {

            statement = connect.createStatement();

            // String query = "DELETE FROM tbl_barang WHERE id_barang = " + id_barang;
            String query = "UPDATE tbl_barang SET is_aktif = '0' WHERE id_barang = " + id_barang;

            if ( !statement.execute(query) ){
                data = true;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }


}
