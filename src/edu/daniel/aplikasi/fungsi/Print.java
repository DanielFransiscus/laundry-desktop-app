/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.daniel.aplikasi.fungsi;

import edu.daniel.aplikasi.view.FrmMain;
import static edu.daniel.aplikasi.view.FrmTransaksi.txtNoOrder3;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JRPrintPage;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author DANIELFRANS
 */
public class Print {

    public void cetakPaket() {
        HashMap a = new HashMap();
        Locale locale = new Locale("id", "ID");
        a.put(JRParameter.REPORT_LOCALE, locale);
        try {
            JasperReport jreport = JasperCompileManager.compileReport(getClass().getResourceAsStream("/edu/daniel/aplikasi/laporan/DaftarPaket.jrxml"));
            JasperPrint jprint = JasperFillManager.fillReport(jreport, a, Koneksi.getConnection());
            List<JRPrintPage> pages = jprint.getPages();
            if (pages.isEmpty()) {
                System.out.print("Tidak menemukan data");
            }
            JasperViewer.viewReport(jprint, false);
        } catch (JRException ex) {
            Logger.getLogger(FrmMain.class
                    .getName()).log(Level.SEVERE, (String) null, (Throwable) ex);
        }
    }

    public void cetakTransaksi(String noOrder) {
        if (!"".equals(txtNoOrder3.getText())) {
            HashMap b = new HashMap();
            Locale locale = new Locale("id", "ID");
            b.put(JRParameter.REPORT_LOCALE, locale);
            b.put("no_order", noOrder);
            try {
                JasperReport jreport = JasperCompileManager.compileReport(getClass().getResourceAsStream("/edu/daniel/aplikasi/laporan/CetakInvoice.jrxml"));
                JasperPrint jprint = JasperFillManager.fillReport(jreport, b, Koneksi.getConnection());
                List<JRPrintPage> pages = jprint.getPages();
                if (pages.isEmpty()) {
                    System.out.print("Tidak menemukan data");
                }
                JasperViewer.viewReport(jprint, false);
            } catch (JRException ex) {
                Logger.getLogger(FrmMain.class.getName()).log(Level.SEVERE, (String) null, (Throwable) ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Anda harus memasukkan nomor order", "Peringatan", JOptionPane.WARNING_MESSAGE);
        }
    }

}
