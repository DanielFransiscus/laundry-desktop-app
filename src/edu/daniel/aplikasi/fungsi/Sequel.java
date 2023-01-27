/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.daniel.aplikasi.fungsi;

import static edu.daniel.aplikasi.view.FrmDaftarOrder.jTableOrderDetail2;
import static edu.daniel.aplikasi.view.FrmDaftarOrder.labelCountAllOrder1;
import static edu.daniel.aplikasi.view.FrmMain.jTableAllOrder;
import static edu.daniel.aplikasi.view.FrmMain.jTableAllTransaksi;
import static edu.daniel.aplikasi.view.FrmMain.jTablePaket;
import static edu.daniel.aplikasi.view.FrmMain.jTablePelanggan;
import static edu.daniel.aplikasi.view.FrmMain.labelAlamatOutlet;
import static edu.daniel.aplikasi.view.FrmMain.labelCountAllOrder;
import static edu.daniel.aplikasi.view.FrmMain.labelCountAllTransaksi;
import static edu.daniel.aplikasi.view.FrmMain.labelCountPaket;
import static edu.daniel.aplikasi.view.FrmMain.labelCountPelanggan;
import static edu.daniel.aplikasi.view.FrmMain.labelDiambilKu;
import static edu.daniel.aplikasi.view.FrmMain.labelKontak;
import static edu.daniel.aplikasi.view.FrmMain.labelNamaPemilik;
import static edu.daniel.aplikasi.view.FrmMain.labelNamaUsaha;
import static edu.daniel.aplikasi.view.FrmMain.labelNameTempatUsaha;
import static edu.daniel.aplikasi.view.FrmMain.labelProsesKu;
import static edu.daniel.aplikasi.view.FrmMain.labelSelesaiKu;

import static edu.daniel.aplikasi.view.FrmMain.txtNoPaket;
import static edu.daniel.aplikasi.view.FrmMain.txtNoPelanggan;
import static edu.daniel.aplikasi.view.FrmOrder.cmbJenisPaket2;
import static edu.daniel.aplikasi.view.FrmOrder.dummyField;
import static edu.daniel.aplikasi.view.FrmOrder.jTableOrderDetail;
import static edu.daniel.aplikasi.view.FrmOrder.labelCountOrder;
import static edu.daniel.aplikasi.view.FrmOrder.labelNamaPelanggan2;
import static edu.daniel.aplikasi.view.FrmOrder.labelNoPaket2;
import static edu.daniel.aplikasi.view.FrmOrder.labelTotalHarga;
import static edu.daniel.aplikasi.view.FrmOrder.txtHarga2;
import static edu.daniel.aplikasi.view.FrmOrder.txtNoOrder2;
import static edu.daniel.aplikasi.view.FrmPelanggan.jTablePelanggan2;
import static edu.daniel.aplikasi.view.FrmPelanggan.labelCountPelanggan2;
import static edu.daniel.aplikasi.view.FrmTransaksi.dummyField2;
import static edu.daniel.aplikasi.view.FrmTransaksi.jTableTransaksi;
import static edu.daniel.aplikasi.view.FrmTransaksi.labelCountTransaksi;
import static edu.daniel.aplikasi.view.FrmTransaksi.labelTotal;
import static edu.daniel.aplikasi.view.FrmTransaksi.txtNoTransaksi;
import edu.daniel.aplikasi.view.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author DANIELFRANS
 */
public class Sequel {

    Connection conn = Koneksi.getConnection();
    ResultSet rs = null;
    PreparedStatement pst = null;
    Statement stmt = null;

    private String SQL;
    int userID = User.getUserID();

    private DefaultTableModel modelPengeluaran;
    private DefaultTableModel modelPelanggan;
    private DefaultTableModel modelPelanggan2;
    private DefaultTableModel modelPaket;
    private DefaultTableModel modelOrder1;
    private DefaultTableModel modelOrderDetail;
    private DefaultTableModel modelAllTransaksi;
    private DefaultTableModel modelTransaksi1;
    private DefaultTableModel modelAllOrder;
    private DefaultTableModel modelKategori;

    private final String ubahPassword = "UPDATE admin SET username = ?,  password = ? WHERE id_admin = ?";
    private final String passwordExists = "SELECT * FROM admin where id_admin = ? AND password = ?";



    private final String simpanPaket = "INSERT INTO paket (no_paket, nama_paket, harga) VALUES (?,?,?)";
    private final String ubahPaket = "UPDATE paket SET nama_paket = ?, harga = ? WHERE no_paket = ?";
    private final String hapusPaket = "DELETE FROM paket WHERE  no_paket= ?";
    private final String cariPaket = "SELECT * FROM paket WHERE (no_paket LIKE ? OR nama_paket LIKE ?)";
    private final String autoNumberPaket = "select max(right(no_paket,4)) as no from paket";
    private final String showPaket = "SELECT * FROM paket";
    private final String isPaketExists = "SELECT * FROM paket WHERE  no_paket = ?";
    private final String isPaketExists2 = "SELECT * FROM order_detail WHERE no_order = ? AND no_paket = ?";
    private final String showNoLabelPaket = "SELECT * FROM paket WHERE nama_paket = ?";

    private final String simpanPelanggan = "INSERT INTO pelanggan (no_pelanggan, nama_pelanggan, kontak) VALUES (?,?,?)";
    private final String ubahPelanggan = "UPDATE pelanggan SET nama_pelanggan = ?, kontak = ? WHERE no_pelanggan = ?";
    private final String hapusPelanggan = "DELETE FROM pelanggan WHERE no_pelanggan = ?";
    private final String cariPelanggan = "SELECT * FROM pelanggan WHERE (no_pelanggan LIKE ? OR nama_pelanggan LIKE ?)";

    private final String autoNumberPelanggan = "select max(right(no_pelanggan,4)) as no from pelanggan";
    private final String showPelanggan = "SELECT * FROM pelanggan";
    private final String showNoPelanggan = "SELECT * FROM pelanggan WHERE no_pelanggan = ?";

    private final String simpanOrder = "INSERT INTO orderku (no_order, id_admin, no_pelanggan, nama_pelanggan, tgl_pesan, status_pengerjaan) VALUES (?,?,?,?,?,?)";
    private final String simpanOrderDetail = "INSERT INTO order_detail (no_order, no_paket, nama_paket, berat, harga, sub_total) VALUES (?,?,?,?,?,?)";
    private final String ubahOrder = "UPDATE order_detail SET nama_paket = ?, berat = ?, harga = ?, sub_total = ? WHERE no_paket = ?";
    private final String hapusOrder = "DELETE FROM order_detail WHERE  no_paket = ?";
    private final String batalOrder = "DELETE FROM orderku WHERE  no_order = ?";
    private final String autoNumberOrder = "select max(right(no_order,4)) as no from orderku";
    private final String orderExists = "SELECT * FROM orderku WHERE id_admin = ? AND no_order = ?";

//    private final String orderExists2 = "SELECT no_order FROM transaksi WHERE id_admin = ? AND no_order = ?";
    private final String showOrder = "SELECT * FROM orderku WHERE id_admin = ? order by tgl_pesan";
    private final String showOrder2 = "SELECT * FROM order_detail WHERE no_order = ?";

    private final String cariOrder = "SELECT * FROM orderku WHERE (no_order LIKE ? OR nama_pelanggan LIKE ?)";
    private final String tampilTabelOrderDetail = "SELECT * FROM orderku WHERE id_admin = ?";

    private final String ubahStatus = "UPDATE orderku SET  id_admin = ?, status_pengerjaan= ? WHERE no_order = ?";
    private final String showStatus = "SELECT * FROM orderku WHERE id_admin = ? AND status_pengerjaan = ?";
    private final String showTotalStatus = "SELECT COUNT(status_pengerjaan) as total  FROM orderku where status_pengerjaan = ?";

    private final String simpanTransaksi = "INSERT INTO transaksi (no_transaksi, no_order, id_admin, tgl_transaksi, total_harga, dibayar, kembali, keluhan) VALUES (?,?,?,?,?,?,?,?)";
    private final String ubahTransaksi = "UPDATE transaksi SET  no_order=?, id_admin=?, tgl_transaksi= ?, total_harga= ?, dibayar = ?, kembali = ?, keluhan = ? WHERE no_transaksi = ?";
    private final String hapusTransaksi = "DELETE FROM transaksi WHERE  no_transaksi = ?";
    private final String transaksiExists = "SELECT * FROM transaksi WHERE no_transaksi = ? AND id_admin = ?";
    private final String cariTransaksi = "SELECT * FROM transaksi WHERE (no_transaksi LIKE ? OR no_order LIKE ?)";
    private final String showTransaksi = "SELECT * FROM transaksi WHERE id_admin = ? order by tgl_transaksi";

    private final String totalHarga = "SELECT SUM(sub_total) as total FROM order_detail where no_order = ?";
    private final String showHarga = "SELECT * FROM paket WHERE nama_paket = ?";

    private final String showProfil = "SELECT * FROM profil WHERE id_admin = ?";
    private final String ubahProfil = "UPDATE profil SET nama_outlet = ?,pemilik = ?, alamat = ?,kontak = ? WHERE id_admin = ?";



    int paket, tambahan, total, i, dialog, Option, dialogResult;

    DecimalFormat kursIndonesia;
    DecimalFormatSymbols formatRupiah;

    public Sequel() {
        kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        formatRupiah = new DecimalFormatSymbols();
        formatRupiah.setCurrencySymbol("Rp. ");
        formatRupiah.setMonetaryDecimalSeparator(',');
        formatRupiah.setGroupingSeparator('.');
        kursIndonesia.setDecimalFormatSymbols(formatRupiah);

    }

    public boolean isPasswordExists(int userID, String password) {
        Boolean check = true;
        try {
            pst = conn.prepareStatement(passwordExists);
            pst.setInt(1, userID);
            pst.setString(2, password);
            rs = pst.executeQuery();
            if (rs.next()) {
                check = true;
            } else {
                check = false;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            check = false;
        }
        return check;
    }

    public void ubahPassword(String username, String password, int userID) {
        try {
            pst = conn.prepareStatement(ubahPassword);
            pst.setString(1, username);
            pst.setString(2, password);
            pst.setInt(3, userID);

            i = pst.executeUpdate();
            if (i > 0) {
                JOptionPane.showMessageDialog(null, "Password berhasil diubah", "Informasi", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Password gagal diubah" + "\nTerjadi Kesalahan", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }



    //    ==========================================Pelanggan==========================================
    public void autoNumberPelanggan() {
        try {
            pst = conn.prepareStatement(autoNumberPelanggan);
            rs = pst.executeQuery();
            while (rs.next()) {
                if (rs.first() == false) {
                    txtNoPelanggan.setText("CST0001");
                } else {
                    rs.last();
                    int set_no = rs.getInt(1) + 1;
                    String no = String.valueOf(set_no);
                    int no_next = no.length();
                    for (int a = 0; a < 4 - no_next; a++) {
                        no = "0" + no;
                    }
                    txtNoPelanggan.setText("CST" + no);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void showPelanggan() {
        modelPelanggan = new DefaultTableModel();
        modelPelanggan.addColumn("No");
        modelPelanggan.addColumn("Nama");
        modelPelanggan.addColumn("Kontak");
        jTablePelanggan.setModel(modelPelanggan);
        jTablePelanggan.setDefaultEditor(Object.class, null);
        try {
            pst = conn.prepareStatement(showPelanggan);
            rs = pst.executeQuery();
            while (rs.next()) {
                Object[] kolom = new Object[3];
                kolom[0] = rs.getString("no_pelanggan");
                kolom[1] = rs.getString("nama_pelanggan");
                kolom[2] = rs.getString("kontak");
                modelPelanggan.addRow(kolom);
            }
            int count = 0;
            if (rs.last()) {
                count = rs.getRow();
            }
            labelCountPelanggan.setText("" + count);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean isPelangganExists(String noPelanggan) {
        Boolean check = true;
        try {
            pst = conn.prepareStatement(showNoPelanggan);
            pst.setString(1, noPelanggan);
            rs = pst.executeQuery();
            if (rs.next()) {
                check = true;
            } else {
                check = false;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return check;
    }

    public void simpanPelanggan(String noPelanggan, String namaPelanggan, String kontak) {
        try {
            pst = conn.prepareStatement(simpanPelanggan);
            pst.setString(1, noPelanggan);
            pst.setString(2, namaPelanggan);
            pst.setString(3, kontak);
            i = pst.executeUpdate();
            if (i > 0) {
                JOptionPane.showMessageDialog(null, "Data Pelanggan" + "\nberhasil dimasukkan", "Informasi", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Data gagal disimpan" + "\nTerjadi Kesalahan", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void ubahPelanggan(String noPelanggan, String namaPelanggan, String kontak) {
        try {
            pst = conn.prepareStatement(ubahPelanggan);
            pst.setString(1, namaPelanggan);
            pst.setString(2, kontak);
            pst.setString(3, noPelanggan);
            i = pst.executeUpdate();
            if (i > 0) {
                JOptionPane.showMessageDialog(null, "Data Pelanggan" + "\nberhasil diubah", "Informasi", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Data gagal diubah" + "\nTerjadi Kesalahan", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void hapusPelanggan(String noPelanggan) {
        Option = JOptionPane.YES_NO_OPTION;
        dialogResult = JOptionPane.showConfirmDialog(null, "Apakah anda yakin menghapus data ini?", "Hapus", Option);
        if (dialogResult == 0) {
            try {
                pst = conn.prepareStatement(hapusPelanggan);
                pst.setString(1, noPelanggan);
                i = pst.executeUpdate();
                if (i > 0) {
                    JOptionPane.showMessageDialog(null, "Data pelanggan" + "\nberhasil dihapus", "Informasi", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Data gagal dihapus" + "\nTerjadi Kesalahan", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void cariPelanggan(String cari) {
        modelPelanggan.getDataVector().removeAllElements();
        modelPelanggan.fireTableDataChanged();
        try {
            pst = conn.prepareStatement(cariPelanggan);
            pst.setString(1, "%" + cari + "%");
            pst.setString(2, "%" + cari + "%");
            rs = pst.executeQuery();
            while (rs.next()) {
                Object[] kolom = new Object[3];
                kolom[0] = rs.getString("no_pelanggan");
                kolom[1] = rs.getString("nama_pelanggan");
                kolom[2] = rs.getString("kontak");
                modelPelanggan.addRow(kolom);
            }
            int count = 0;
            if (rs.last()) {
                count = rs.getRow();
            }
            labelCountPelanggan.setText("" + count);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

//    ==================================paket=====================================
    public void autoNumberPaket() {
        try {
            pst = conn.prepareStatement(autoNumberPaket);
            rs = pst.executeQuery();
            while (rs.next()) {
                if (rs.first() == false) {
                    txtNoPaket.setText("PKT0001");
                } else {
                    rs.last();
                    int set_no = rs.getInt(1) + 1;
                    String no = String.valueOf(set_no);
                    int no_next = no.length();
                    for (int a = 0; a < 4 - no_next; a++) {
                        no = "0" + no;
                    }
                    txtNoPaket.setText("PKT" + no);

                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void showPaket() {
        modelPaket = new DefaultTableModel();
        modelPaket.addColumn("Nomor Paket");
        modelPaket.addColumn("Nama Paket");
        modelPaket.addColumn("Harga");
        jTablePaket.setModel(modelPaket);
        jTablePaket.setDefaultEditor(Object.class, null);
        try {
            pst = conn.prepareStatement(showPaket);
            rs = pst.executeQuery();
            while (rs.next()) {
                Object[] kolom = new Object[3];
                kolom[0] = rs.getString("no_paket");
                kolom[1] = rs.getString("nama_paket");
                kolom[2] = rs.getInt("harga");
                modelPaket.addRow(kolom);
            }
            int count = 0;
            if (rs.last()) {
                count = rs.getRow();
            }
            labelCountPaket.setText("" + count);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean isPaketExists(String noPaket) {
        Boolean check = true;
        try {
            pst = conn.prepareStatement(isPaketExists);
            pst.setString(1, noPaket);
            rs = pst.executeQuery();
            if (rs.next()) {
                check = true;
            } else {
                check = false;

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return check;
    }

    public boolean isPaketExists2() {
        Boolean check = true;
        try {
            int baris = jTableOrderDetail.getSelectedRow();
            String noPaketTabel = jTableOrderDetail.getValueAt(baris, 1).toString();
            pst = conn.prepareStatement(isPaketExists2);
            pst.setString(1, txtNoOrder2.getText());
            pst.setString(2, noPaketTabel);
            rs = pst.executeQuery();
            if (rs.next()) {
                check = true;
            } else {
                check = false;

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return check;
    }

    public void simpanPaket(String noPaket, String namaPaket, int harga) {
        try {
            pst = conn.prepareStatement(simpanPaket);
            pst.setString(1, noPaket);
            pst.setString(2, namaPaket);
            pst.setInt(3, harga);
            i = pst.executeUpdate();
            if (i > 0) {
                JOptionPane.showMessageDialog(null, "Data paket" + "\nberhasil dimasukkan", "Informasi", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Data gagal disimpan" + "\nTerjadi Kesalahan", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void ubahPaket(String noPaket, String namaPaket, int harga) {
        try {
            pst = conn.prepareStatement(ubahPaket);
            pst.setString(1, namaPaket);
            pst.setInt(2, harga);
            pst.setString(3, noPaket);
            i = pst.executeUpdate();
            if (i > 0) {
                JOptionPane.showMessageDialog(null, "Data paket" + "\nberhasil diubah", "Informasi", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Data gagal diubah" + "\nTerjadi Kesalahan", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void hapusPaket(String noPaket) {
        Option = JOptionPane.YES_NO_OPTION;
        dialogResult = JOptionPane.showConfirmDialog(null, "Apakah anda yakin menghapus data ini?", "Hapus", Option);
        if (dialogResult == 0) {
            try {
                pst = conn.prepareStatement(hapusPaket);
                pst.setString(1, noPaket);
                i = pst.executeUpdate();
                if (i > 0) {
                    JOptionPane.showMessageDialog(null, "Data paket" + "\nberhasil dihapus", "Informasi", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Data gagal dihapus" + "\nTerjadi Kesalahan", "Error", JOptionPane.ERROR_MESSAGE);

                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void cariPaket(String cari) {
        modelPaket.getDataVector().removeAllElements();
        modelPaket.fireTableDataChanged();
        try {
            pst = conn.prepareStatement(cariPaket);
            pst.setString(1, "%" + cari + "%");
            pst.setString(2, "%" + cari + "%");
            rs = pst.executeQuery();
            while (rs.next()) {
                Object[] kolom = new Object[3];
                kolom[0] = rs.getString("no_paket");
                kolom[1] = rs.getString("nama_paket");
                kolom[2] = rs.getInt("harga");
                modelPaket.addRow(kolom);
            }
            int count = 0;
            if (rs.last()) {
                count = rs.getRow();
            }
            labelCountPaket.setText("" + count);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //    ==================================FrmOrder=====================================
    public boolean isOrderExists(String noOrder) {
        Boolean check = true;
        try {
            pst = conn.prepareStatement(orderExists);
            pst.setInt(1, userID);
            pst.setString(2, noOrder);
            rs = pst.executeQuery();
            if (rs.next()) {
                check = true;
            } else {
                check = false;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return check;
    }

//    public boolean isOrderExists2(String noOrder) {
//        Boolean check = true;
//        try {
//            pst = conn.prepareStatement(orderExists2);
//            pst.setInt(1, userID);
//            pst.setString(2, noOrder);
//            rs = pst.executeQuery();
//            if (rs.next()) {
//                check = true;
//            } else {
//                check = false;
//            }
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//            return false;
//        }
//        return check;
//    }
    public void simpanOrder(String noOrder, String noPelanggan, String namaPelanggan, String tglOrder, String status) {
        try {
            pst = conn.prepareStatement(simpanOrder);
            pst.setString(1, noOrder);
            pst.setInt(2, userID);
            pst.setString(3, noPelanggan);
            pst.setString(4, namaPelanggan);
            pst.setString(5, tglOrder);
            pst.setString(6, status);
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void simpanOrderDetail(String noOrder, String noPaket, String jenis, int berat, int harga, int subTotal) {
        try {
            pst = conn.prepareStatement(simpanOrderDetail);
            pst.setString(1, noOrder);
            pst.setString(2, noPaket);
            pst.setString(3, jenis);
            pst.setInt(4, berat);
            pst.setInt(5, harga);
            pst.setInt(6, subTotal);
            i = pst.executeUpdate();
            if (i > 0) {
                JOptionPane.showMessageDialog(null, "Data Order" + "\nberhasil dimasukkan", "Informasi", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Data gagal disimpan" + "\nTerjadi Kesalahan", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void ubahOrder(String noOrder, String noPaket, String jenis, int berat, int harga, int subTotal) {
        try {
            pst = conn.prepareStatement(ubahOrder);
            pst.setString(1, jenis);
            pst.setInt(2, berat);
            pst.setInt(3, harga);
            pst.setInt(4, subTotal);
            pst.setString(5, noPaket);
            i = pst.executeUpdate();
            if (i > 0) {
                JOptionPane.showMessageDialog(null, "Data Order" + "\nberhasil diubah", "Informasi", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Data gagal diubah" + "\nTerjadi Kesalahan", "Error", JOptionPane.ERROR_MESSAGE);

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void hapusOrder(String noPaket) {
        Option = JOptionPane.YES_NO_OPTION;
        dialogResult = JOptionPane.showConfirmDialog(null, "Apakah anda yakin menghapus paket ini ?", "Hapus", Option);
        if (dialogResult == 0) {
            try {
                pst = conn.prepareStatement(hapusOrder);
                pst.setString(1, noPaket);
                i = pst.executeUpdate();
                if (i > 0) {
                    JOptionPane.showMessageDialog(null, "Data Order" + "\nberhasil dihapus", "Informasi", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Data gagal dihapus" + "\nTerjadi Kesalahan", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void batalOrder(String noOrder) {
        Option = JOptionPane.YES_NO_OPTION;
        dialogResult = JOptionPane.showConfirmDialog(null, "Apakah anda yakin membatalkan orderan ini ?", "Hapus", Option);
        if (dialogResult == 0) {
            try {
                pst = conn.prepareStatement(batalOrder);
                pst.setString(1, noOrder);
                i = pst.executeUpdate();
                if (i > 0) {
                    JOptionPane.showMessageDialog(null, "Data Order" + "\nberhasil dibatalkan", "Informasi", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Data gagal dihapus" + "\nTerjadi Kesalahan", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void autoNumberOrder() {
        try {
            pst = conn.prepareStatement(autoNumberOrder);
            rs = pst.executeQuery();
            while (rs.next()) {
                if (rs.first() == false) {
                    txtNoOrder2.setText("ORD0001");
                } else {
                    rs.last();
                    int set_no = rs.getInt(1) + 1;
                    String no = String.valueOf(set_no);
                    int no_next = no.length();
                    for (int a = 0; a < 4 - no_next; a++) {
                        no = "0" + no;
                    }
                    txtNoOrder2.setText("ORD" + no);

                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void totalHarga(String noOrder) {
        int total = 0;
        try {
            pst = conn.prepareStatement(totalHarga);
            pst.setString(1, noOrder);
            rs = pst.executeQuery();
            if (rs.next()) {
                total = rs.getInt("total");
                dummyField.setText(Integer.toString(total));
                int total2 = Integer.parseInt(dummyField.getText().trim());
                labelTotalHarga.setText(kursIndonesia.format(total2));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void showOrder2(String noOrder) {
        modelOrderDetail = new DefaultTableModel();
        modelOrderDetail.addColumn("No Order");
        modelOrderDetail.addColumn("No Paket");
        modelOrderDetail.addColumn("Nama Paket");
        modelOrderDetail.addColumn("Berat");
        modelOrderDetail.addColumn("Harga");
        modelOrderDetail.addColumn("Sub Total");
        jTableOrderDetail.setModel(modelOrderDetail);
        jTableOrderDetail
                .setDefaultEditor(Object.class,
                        null);
        try {
            pst = conn.prepareStatement(showOrder2);
            pst.setString(1, noOrder);
            rs = pst.executeQuery();
            while (rs.next()) {
                Object[] kolom = new Object[6];
                kolom[0] = rs.getString("no_order");
                kolom[1] = rs.getString("no_paket");
                kolom[2] = rs.getString("nama_paket");
                kolom[3] = rs.getInt("berat");
                kolom[4] = rs.getInt("harga");
                kolom[5] = rs.getInt("sub_total");
                modelOrderDetail.addRow(kolom);
            }
            int count = 0;
            if (rs.last()) {
                count = rs.getRow();
            }
            labelCountOrder.setText("" + count);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void showOrder() {
        modelOrder1 = new DefaultTableModel();
        modelOrder1.addColumn("No Order");
        modelOrder1.addColumn("No Pelanggan");
        modelOrder1.addColumn("Nama Pelanggan");
        modelOrder1.addColumn("Tanggal Pesan");
        modelOrder1.addColumn("Status Pengejerjaan");
        jTableAllOrder.setModel(modelOrder1);
        jTableAllOrder
                .setDefaultEditor(Object.class,
                        null);
        try {
            pst = conn.prepareStatement(showOrder);
            pst.setInt(1, userID);
            rs = pst.executeQuery();
            while (rs.next()) {
                Object[] kolom = new Object[5];
                kolom[0] = rs.getString("no_order");
                kolom[1] = rs.getString("no_pelanggan");
                kolom[2] = rs.getString("nama_pelanggan");
                kolom[3] = rs.getDate("tgl_pesan");
                kolom[4] = rs.getString("status_pengerjaan");
                modelOrder1.addRow(kolom);
            }
            int count = 0;
            if (rs.last()) {
                count = rs.getRow();
            }
            labelCountAllOrder.setText("" + count);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void cariOrder(String cari) {
        modelOrder1.getDataVector().removeAllElements();
        modelOrder1.fireTableDataChanged();
        try {
            pst = conn.prepareStatement(cariOrder);
            pst.setString(1, "%" + cari + "%");
            pst.setString(2, "%" + cari + "%");
            rs = pst.executeQuery();
            while (rs.next()) {
                Object[] kolom = new Object[5];
                kolom[0] = rs.getString("no_order");
                kolom[1] = rs.getString("no_pelanggan");
                kolom[2] = rs.getString("nama_pelanggan");
                kolom[3] = rs.getDate("tgl_pesan");
                kolom[4] = rs.getString("status_pengerjaan");
                modelOrder1.addRow(kolom);
            }
            int count = 0;
            if (rs.last()) {
                count = rs.getRow();
            }
            labelCountAllOrder.setText("" + count);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public void showStatus(String status) {
        modelOrder1 = new DefaultTableModel();
        modelOrder1.addColumn("No Order");
        modelOrder1.addColumn("No Pelanggan");
        modelOrder1.addColumn("Nama Pelanggan");
        modelOrder1.addColumn("Tanggal Pesan");
        modelOrder1.addColumn("Status Pengejerjaan");
        jTableAllOrder.setModel(modelOrder1);
        jTableAllOrder
                .setDefaultEditor(Object.class,
                        null);
        try {
            pst = conn.prepareStatement(showStatus);
            pst.setInt(1, userID);
            pst.setString(2, status);
            rs = pst.executeQuery();
            while (rs.next()) {
                Object[] kolom = new Object[6];
                kolom[0] = rs.getString("no_order");
                kolom[1] = rs.getString("no_pelanggan");
                kolom[2] = rs.getString("nama_pelanggan");
                kolom[3] = rs.getDate("tgl_pesan");
                kolom[4] = rs.getString("status_pengerjaan");
                modelOrder1.addRow(kolom);
            }
            int count = 0;
            if (rs.last()) {
                count = rs.getRow();
            }
            labelCountAllOrder.setText("" + count);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void showProses() {
        try {
            pst = conn.prepareStatement(showTotalStatus);
            String statusPengerjaan = "proses";
            pst.setString(1, statusPengerjaan);
            rs = pst.executeQuery();
            if (rs.next()) {
                total = rs.getInt("total");
                labelProsesKu.setText(Integer.toString(total));

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void showSelesai() {
        try {
            pst = conn.prepareStatement(showTotalStatus);
            String statusPengerjaan = "selesai";
            pst.setString(1, statusPengerjaan);
            rs = pst.executeQuery();
            if (rs.next()) {
                total = rs.getInt("total");
                labelSelesaiKu.setText(Integer.toString(total));

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void showDiAmbil() {
        try {
            pst = conn.prepareStatement(showTotalStatus);
            String statusPengerjaan = "diambil";
            pst.setString(1, statusPengerjaan);
            rs = pst.executeQuery();
            if (rs.next()) {
                total = rs.getInt("total");
                labelDiambilKu.setText(Integer.toString(total));

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void ubahStatus(String status, String noOrder) {
        try {
            pst = conn.prepareStatement(ubahStatus);
            pst.setInt(1, userID);
            pst.setString(2, status);
            pst.setString(3, noOrder);
            i = pst.executeUpdate();
            if (i > 0) {
                JOptionPane.showMessageDialog(null, "Data Order" + "\nberhasil diubah", "Informasi", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Data gagal diubah" + "\nTerjadi Kesalahan", "Error", JOptionPane.ERROR_MESSAGE);

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //    ==================================FrmTransaksi=====================================
    public void simpanTransaksi(String noTransaksi, String noOrder, String tglTransaksi, String total, int bayar, int kembali, String keluhan) {
        try {
            pst = conn.prepareStatement(simpanTransaksi);
            pst.setString(1, noTransaksi);
            pst.setString(2, noOrder);
            pst.setInt(3, userID);
            pst.setString(4, tglTransaksi);
            pst.setString(5, total);
            pst.setInt(6, bayar);
            pst.setInt(7, kembali);
            pst.setString(8, keluhan);
            i = pst.executeUpdate();
            if (i > 0) {
                JOptionPane.showMessageDialog(null, "Data Transaksi" + "\nberhasil dimasukkan", "Informasi", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Data gagal disimpan" + "\nTerjadi Kesalahan", "Error", JOptionPane.ERROR_MESSAGE);

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void ubahTransaksi(String noTransaksi, String noOrder, String tglTransaksi, String total, int bayar, int kembali, String keluhan) {
        try {
            pst = conn.prepareStatement(ubahTransaksi);
            pst.setString(1, noOrder);
            pst.setInt(2, userID);
            pst.setString(3, tglTransaksi);
            pst.setString(4, total);
            pst.setInt(5, bayar);
            pst.setInt(6, kembali);
            pst.setString(7, keluhan);
            pst.setString(8, noTransaksi);
            i = pst.executeUpdate();
            if (i > 0) {
                JOptionPane.showMessageDialog(null, "Data Transaksi" + "\nberhasil diubah", "Informasi", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Data gagal diubah" + "\nTerjadi Kesalahan", "Error", JOptionPane.ERROR_MESSAGE);

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void hapusTransaksi(String noTransaksi) {
        Option = JOptionPane.YES_NO_OPTION;
        dialogResult = JOptionPane.showConfirmDialog(null, "Apakah anda yakin membatalkan transaksi ini?", "Batal", Option);
        if (dialogResult == 0) {
            try {
                pst = conn.prepareStatement(hapusTransaksi);
                pst.setString(1, noTransaksi);
                i = pst.executeUpdate();
                if (i > 0) {
                    JOptionPane.showMessageDialog(null, "Transaksi" + "\nberhasil dibatalkan", "Informasi", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Data gagal dihapus" + "\nTerjadi Kesalahan", "Error", JOptionPane.ERROR_MESSAGE);

                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void totalHarga2(String noOrder) {
        try {
            pst = conn.prepareStatement(totalHarga);
            pst.setString(1, noOrder);
            rs = pst.executeQuery();
            if (rs.next()) {
                total = rs.getInt("total");
                dummyField2.setText(Integer.toString(total));
                int total2 = Integer.parseInt(dummyField2.getText().trim());
                labelTotal.setText(kursIndonesia.format(total2));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void autoNumberTransaksi() {
        try {
            pst = conn.prepareStatement("select max(right(no_transaksi,4)) as no from transaksi");
            rs = pst.executeQuery();
            while (rs.next()) {
                if (rs.first() == false) {
                    txtNoTransaksi.setText("TRX0001");
                } else {
                    rs.last();
                    int set_no = rs.getInt(1) + 1;
                    String no = String.valueOf(set_no);
                    int no_next = no.length();
                    for (int a = 0; a < 4 - no_next; a++) {
                        no = "0" + no;
                    }
                    txtNoTransaksi.setText("TRX" + no);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void showOrder3(String noOrder) {
        modelTransaksi1 = new DefaultTableModel();
        modelTransaksi1.addColumn("No Order");
        modelTransaksi1.addColumn("No Paket");
        modelTransaksi1.addColumn("Nama Paket");
        modelTransaksi1.addColumn("Berat");
        modelTransaksi1.addColumn("Harga");
        modelTransaksi1.addColumn("Sub Total");
        jTableTransaksi.setModel(modelTransaksi1);
        jTableTransaksi
                .setDefaultEditor(Object.class,
                        null);
        try {
            pst = conn.prepareStatement(showOrder2);
            pst.setString(1, noOrder);
            rs = pst.executeQuery();
            while (rs.next()) {
                Object[] kolom = new Object[6];
                kolom[0] = rs.getString("no_order");
                kolom[1] = rs.getString("no_paket");
                kolom[2] = rs.getString("nama_paket");
                kolom[3] = rs.getInt("berat");
                kolom[4] = rs.getInt("harga");
                kolom[5] = rs.getInt("sub_total");
                modelTransaksi1.addRow(kolom);
            }
            int count = 0;
            if (rs.last()) {
                count = rs.getRow();
            }
            labelCountTransaksi.setText("" + count);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void cariTransaksi(String cari) {
        modelTransaksi1.getDataVector().removeAllElements();
        modelTransaksi1.fireTableDataChanged();
        try {
            pst = conn.prepareStatement(cariTransaksi);
            pst.setString(1, "%" + cari + "%");
            pst.setString(2, "%" + cari + "%");
            rs = pst.executeQuery();
            while (rs.next()) {
                Object[] kolom = new Object[7];
                kolom[0] = rs.getString("no_transaksi");
                kolom[1] = rs.getString("no_order");
                kolom[2] = rs.getDate("tgl_transaksi");
                kolom[3] = rs.getInt("total_harga");
                kolom[4] = rs.getInt("dibayar");
                kolom[5] = rs.getInt("kembali");
                kolom[6] = rs.getString("keluhan");
                modelTransaksi1.addRow(kolom);
            }
            int count = 0;
            if (rs.last()) {
                count = rs.getRow();
            }
            labelCountAllTransaksi.setText("" + count);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void showNoLabelPaket(String namaPaket) {
        try {
            pst = conn.prepareStatement(showNoLabelPaket);
            pst.setString(1, namaPaket);
            rs = pst.executeQuery();
            while (rs.next()) {
                String noPaket = rs.getString("no_paket");
                labelNoPaket2.setText(noPaket);

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void showComboPaket() {
        try {
            pst = conn.prepareStatement(showPaket);
            rs = pst.executeQuery();
            while (rs.next()) {
                String paket = rs.getString("nama_paket");
                cmbJenisPaket2.addItem(paket);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void showHarga(String namaPaket) {
        int harga = 0;
        try {
            pst = conn.prepareStatement(showHarga);
            pst.setString(1, namaPaket);
            rs = pst.executeQuery();
            while (rs.next()) {
                harga = rs.getInt("harga");
            }
            txtHarga2.setText(Integer.toString(harga));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

//    tambahan
    public void showTransaksi() {
        modelAllTransaksi = new DefaultTableModel();
        modelAllTransaksi.addColumn("No Transaksi");
        modelAllTransaksi.addColumn("No Order");
        modelAllTransaksi.addColumn("Tanggal Transaksi");
        modelAllTransaksi.addColumn("Total Harga");
        modelAllTransaksi.addColumn("Dibayar");
        modelAllTransaksi.addColumn("Kembali");
        modelAllTransaksi.addColumn("Keluhan");
        jTableAllTransaksi.setModel(modelAllTransaksi);

        try {
            pst = conn.prepareStatement(showTransaksi);
            pst.setInt(1, userID);
            rs = pst.executeQuery();
            while (rs.next()) {
                Object[] kolom = new Object[7];
                kolom[0] = rs.getString("no_transaksi");
                kolom[1] = rs.getString("no_order");
                kolom[2] = rs.getDate("tgl_transaksi");
                kolom[3] = rs.getInt("total_harga");
                kolom[4] = rs.getInt("dibayar");
                kolom[5] = rs.getInt("kembali");
                kolom[6] = rs.getString("keluhan");
                modelAllTransaksi.addRow(kolom);
            }
            int count = 0;
            if (rs.last()) {
                count = rs.getRow();
            }
            labelCountAllTransaksi.setText("" + count);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }



    public void showProfil() {
        String namaOutlet, pemilik, alamat, kontak;
        try {
            pst = conn.prepareStatement(showProfil);
            pst.setInt(1, userID);
            rs = pst.executeQuery();
            if (rs.next()) {
                namaOutlet = rs.getString("nama_outlet");
                pemilik = rs.getString("pemilik");
                alamat = rs.getString("alamat");
                kontak = rs.getString("kontak");
                labelNameTempatUsaha.setText("Nama tempat usaha anda adalah " + namaOutlet);
                labelNamaUsaha.setText(namaOutlet);
                labelNamaPemilik.setText("Nama anda adalah " + pemilik);
                labelAlamatOutlet.setText("Tempat usaha anda berlokasi di " + alamat);
                labelKontak.setText("Kontak anda yaitu " + kontak);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void ubahProfil(String namaTempat, String namaPemilik, String alamat, String kontak) {
        try {
            pst = conn.prepareStatement(ubahProfil);
            pst.setString(1, namaTempat);
            pst.setString(2, namaPemilik);
            pst.setString(3, alamat);
            pst.setString(4, kontak);
            pst.setInt(5, userID);
            i = pst.executeUpdate();
            if (i > 0) {
                JOptionPane.showMessageDialog(null, "Profil berhasil diubah", "Informasi", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Profil gagal diubah" + "\nTerjadi Kesalahan", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void tampilTabelOrderDetail() {
        modelAllOrder = new DefaultTableModel();
        modelAllOrder.addColumn("No Order");
        modelAllOrder.addColumn("No Pelanggan");
        modelAllOrder.addColumn("Nama Pelanggan");
        modelAllOrder.addColumn("Tanggal Order");
        jTableOrderDetail2.setModel(modelAllOrder);
        jTableOrderDetail2
                .setDefaultEditor(Object.class,
                        null);
        try {
            pst = conn.prepareStatement(tampilTabelOrderDetail);
            pst.setInt(1, userID);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Object[] kolom = new Object[4];
                kolom[0] = rs.getString("no_order");
                kolom[1] = rs.getString("no_pelanggan");
                kolom[2] = rs.getString("nama_pelanggan");
                kolom[3] = rs.getDate("tgl_pesan");
                modelAllOrder.addRow(kolom);
            }
            int count = 0;
            if (rs.last()) {
                count = rs.getRow();
            }
            labelCountAllOrder1.setText("" + count);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void cariOrder2(String cari2) {
        modelAllOrder.getDataVector().removeAllElements();
        modelAllOrder.fireTableDataChanged();
        try {
            pst = conn.prepareStatement(cariOrder);
            pst.setString(1, "%" + cari2 + "%");
            pst.setString(2, "%" + cari2 + "%");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Object[] kolom = new Object[5];
                kolom[0] = rs.getString("no_order");
                kolom[1] = rs.getString("no_pelanggan");
                kolom[2] = rs.getString("nama_pelanggan");
                kolom[3] = rs.getDate("tgl_pesan");
                kolom[4] = rs.getString("status_pengerjaan");
                modelAllOrder.addRow(kolom);
            }
            int count = 0;
            if (rs.last()) {
                count = rs.getRow();
            }
            labelCountAllOrder1.setText("" + count);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void tampilDataPelanggan() {
        modelPelanggan2 = new DefaultTableModel();
        modelPelanggan2.addColumn("No");
        modelPelanggan2.addColumn("Nama");
        modelPelanggan2.addColumn("Kontak");
        jTablePelanggan2.setModel(modelPelanggan2);
        jTablePelanggan2
                .setDefaultEditor(Object.class,
                        null);
        try {
            pst = conn.prepareStatement(showPelanggan);
            rs = pst.executeQuery();
            while (rs.next()) {
                Object[] kolom = new Object[3];
                kolom[0] = rs.getString("no_pelanggan");
                kolom[1] = rs.getString("nama_pelanggan");
                kolom[2] = rs.getString("kontak");
                modelPelanggan2.addRow(kolom);
            }
            int count = 0;
            if (rs.last()) {
                count = rs.getRow();
            }
            labelCountPelanggan2.setText("" + count);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void cariPelanggan2(String cari2) {
        modelPelanggan2.getDataVector().removeAllElements();
        modelPelanggan2.fireTableDataChanged();
        try {
            pst = conn.prepareStatement(cariPelanggan);
            pst.setString(1, "%" + cari2 + "%");
            pst.setString(2, "%" + cari2 + "%");
            rs = pst.executeQuery();
            while (rs.next()) {
                Object[] kolom = new Object[3];
                kolom[0] = rs.getString("no_pelanggan");
                kolom[1] = rs.getString("nama_pelanggan");
                kolom[2] = rs.getString("kontak");
                modelPelanggan2.addRow(kolom);
            }
            int count = 0;
            if (rs.last()) {
                count = rs.getRow();
            }
            labelCountPelanggan2.setText("" + count);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

        public void showNoPelanggan(String noPelanggan) {
        try {
            pst = conn.prepareStatement(showNoPelanggan);
            pst.setString(1, noPelanggan);
            rs = pst.executeQuery();
            if (rs.next()) {
                String namaPelanggan = rs.getString("nama_pelanggan");
                labelNamaPelanggan2.setText(namaPelanggan);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean isTransaksiExists(String noTransaksi) {
        Boolean check = true;
        try {
            pst = conn.prepareStatement(transaksiExists);
            pst.setString(1, noTransaksi);
            pst.setInt(2, userID);
            rs = pst.executeQuery();
            if (rs.next()) {
                check = true;
            } else {
                check = false;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            check = false;
        }
        return check;
    }

}
