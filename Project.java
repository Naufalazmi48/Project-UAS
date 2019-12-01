package Kelompok9;
import javax.swing.JOptionPane;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Project {
    public static void main(String[] args) {
        //Panggil fungsi Local Datedan simple date format
        LocalDateTime tanggal = LocalDateTime.now();
        DateTimeFormatter formatTanggal =  DateTimeFormatter.ofPattern("dd-MM-yyyy");// agar urutan waktunya tanggal-bulan-tahun
        //Deklarasi variable yang akan digunakan
        String idPelanggan,nama,alamat,wilayah,input,info,voucher = "",namaPaket = "",masaBerlaku;
        byte pil;
        int harga = 0,periode = 0,total = 0,diskon = 0,kesempatan = 0,jawab; 
        //Variable jawab digunakan untuk menampung nilai dari JPane option dialog
        boolean flag = true;
        
        //Tampilan awal
        JOptionPane.showMessageDialog(null, "Selamat datang di layanan Telkom ","Selamat datang",
                JOptionPane.INFORMATION_MESSAGE);
        //Input ID Pelanggan,Nama,Alamat,Wilayah
        input = JOptionPane.showInputDialog("Masukkan ID anda");
        idPelanggan = input;
        input = JOptionPane.showInputDialog("Masukkan Nama anda");
        nama = input;
        input = JOptionPane.showInputDialog("Masukkan Alamat anda");
        alamat = input;
        input = JOptionPane.showInputDialog("Masukkan nama Wilayah anda");
        wilayah = input;
        //Tampilkan layanan yang tersedia dan pilih
        info = "Hai "+nama+",Silahkan pilih paket yang anda inginkan"+
                "\n1.Paket Internet             Harga : Rp.200.000/bulan"+
                "\n2.Paket Telepon             Harga : Rp.100.000/bulan"+
                "\n3.Paket Channel TV       Harga : Rp.300.000/bulan\n";
        input = JOptionPane.showInputDialog(null,info,"Layanan menu",JOptionPane.INFORMATION_MESSAGE);
        pil = Byte.valueOf(input);
        //Masukkan lama penggunaan paket (hitungan bulan)
        input = JOptionPane.showInputDialog("Berapa bulan anda akan berlangganan?");
        periode = Integer.valueOf(input);
        tanggal = tanggal.plusMonths(periode);
        
        //Membuat switch case untuk pilihan
        do {
            switch (pil){
            case 1 :
                namaPaket = "Paket internet";
                harga = 200000;
                total = harga*periode;
                flag = false;
                break;
            case 2 :
                namaPaket = "Paket telepon";
                harga = 100000;
                total = harga*periode;
                flag = false;
                break;
            case 3 :
                namaPaket = "Paket Channel TV";
                harga = 300000;
                total = harga*periode;
                flag = false;
                break;
            default:
                JOptionPane.showMessageDialog(null,"Input yang anda masukkan salah","Peringatan",JOptionPane.WARNING_MESSAGE);
                break;
            }
        } while (flag);
        //Kembalikan nilai variable flag ke true karena akan digunakan lagi
        flag = true;
        //Masukkan kode voucher. 1.Susahsinyal (dapat diskon 30%),2.Lag(diskon 20%),3.Lemot (diskon 10%)
         jawab = JOptionPane.showOptionDialog(null, "Apakah anda memiliki voucher?",
                "Layanan menu",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,null,null);
         //Variable jawab = 0 (Jika menekan tombol yes)
         //Variable jawab = 1 (Jika menekan tombol no)
         
         //Jika menekan tombol yes
         if (jawab == 0){
             //Berikan variable kesempatan nilai 3 agar ketika salah memasukkan kode voucher lebih dari 3x akan keluar
             kesempatan = 3;
             //Masukkan kode voucher
             do {
             input = JOptionPane.showInputDialog("Masukkan kode voucher");
             voucher = input;
             voucher = voucher.toLowerCase();
                 switch (voucher){
                 case "susahsinyal":
                     JOptionPane.showMessageDialog(null, "Selamat anda mendapatkan diskon 30%");
                     diskon = (30*total)/100;
                     kesempatan = -1;
                     break;
                 case "lag":
                     JOptionPane.showMessageDialog(null, "Selamat anda mendapatkan diskon 20%");
                     diskon = (20*total)/100;
                     kesempatan = -1;
                     break;
                 case "lemot":
                     JOptionPane.showMessageDialog(null, "Selamat anda mendapatkan diskon 10%");
                     diskon = (10*total)/100;
                     kesempatan = -1;
                     break;
                 default:
                     JOptionPane.showMessageDialog(null,"Kode voucher yang anda masukkan salah ("+kesempatan+"x)","Peringatan",JOptionPane.WARNING_MESSAGE);
                     kesempatan--;
                }
             } while (kesempatan >= 0);
         }
             //Masukkan total harga setelah diskon
             total += diskon;
             //apakah ingin menampilkan detail transaksi?
             jawab = JOptionPane.showOptionDialog(null, "Apakah anda ingin menampilkan detail transaksi?",
                "Layanan menu",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,null,null);
             if(jawab == 0){
                 info = ("============================= Detail Transaksi =====================================\n"+
                         "ID Pelanggan               : "+idPelanggan+"\n"+
                         "Nama                             :"+nama+"\n"+
                         "Alamat                          : "+alamat+"\n"+
                         "Wilayah                        : "+wilayah+"\n"+
                         "Paket pilihan              : "+namaPaket+"\n"+
                         "Berlaku hingga          : "+tanggal.format(formatTanggal)+"\n"+
                         "Diskon                        : Rp."+diskon+"\n"+
                         "Total pembayaran   : Rp."+total+"\n"+
                         "==================================================================================");
                 JOptionPane.showMessageDialog(null, info);
             }
    }
}
