package com.internal.web.utils;

import javax.print.*;
import javax.print.attribute.*;
import javax.print.attribute.standard.PrinterIsAcceptingJobs;
import javax.print.attribute.standard.Sides;
import java.awt.print.PrinterJob;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

public class POSUtils {

    public static boolean checkAvailablePrinters()
    {
        PrintService[] ss = java.awt.print.PrinterJob.lookupPrintServices();
        for (int i=0;i<ss.length;i++) {
            Attribute[] att = ss[i].getAttributes().toArray();
            for (int j=0;j<att.length;j++) {
                if (att[j].equals(PrinterIsAcceptingJobs.ACCEPTING_JOBS)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String args []){

        DocFlavor flavor = DocFlavor.SERVICE_FORMATTED.PAGEABLE;
        PrintRequestAttributeSet patts = new HashPrintRequestAttributeSet();
        patts.add(Sides.DUPLEX);

        PrintService[] ps = PrintServiceLookup.lookupPrintServices(flavor,patts);
        if (ps.length==0){
            throw new IllegalStateException("No printer fund");
        }

        PrintService printServiceDef = PrintServiceLookup.lookupDefaultPrintService();
        System.out.println("Default printer:" +printServiceDef.getName());

        PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null,null);
        PrintService myServide = null;
        for (PrintService printService:printServices ) {
            System.out.println("\n"+printService.getName());
            //PrintServiceAttributeSet att = printService.getAttributes().;
            AttributeSet attributes = printService.getAttributes();
            for (Attribute a : attributes.toArray()) {
                String name = a.getName();
                String value = attributes.get(a.getClass()).toString();
                System.out.println(name + " : " + value);
            }

        }



        try {

//            //apache.pdfbox
//            PDDocument document = PDDocument.load(new File("C:/temp/example.pdf"));
//            PrinterJob job = PrinterJob.getPrinterJob();
//            job.setPageable(new PDFPageable(document));
//            job.setPrintService(myPrintService);
//            job.print();




//        FileInputStream expected = new FileInputStream(new File(""));
//        DocPrintJob job = myServide.createPrintJob();
//        Doc doc = new SimpleDoc(expected,DocFlavor.BYTE_ARRAY.AUTOSENSE,null);
//        job.print(doc,new HashPrintRequestAttributeSet());


        }catch (Exception e){

        }

    }
}
