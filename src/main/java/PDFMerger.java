import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.multipdf.PDFMergerUtility;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ywang on 29/09/17.
 */
public class PDFMerger {
    private final static String RESULT_FILE_NAME = "merged.pdf";

    public static void main(String[] args) {
        try {
            File currentFile = new File(PDFMerger.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
            File currentFolder = currentFile.getParentFile();
            if (!currentFolder.isDirectory()) {
                throw new Exception(currentFolder.getName() + " is not a folder");
            }

            // Get all PDF files of folder
            List<File> pdfFiles= new ArrayList<File>();
            for (File file: currentFolder.listFiles()) {
                if (isPdfFile(file)) {
                    pdfFiles.add(file);
                }
            }

            // Sort file names
            pdfFiles.sort((fileA, fileB) -> fileA.getName().compareTo(fileB.getName()));

            // Merge to one pdf
            mergePdfs(currentFolder.getAbsolutePath(), pdfFiles);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void mergePdfs(String folderPath, List<File> pdfFiles) throws IOException {
        PDFMergerUtility ut = new PDFMergerUtility();
        for (File pdfFile : pdfFiles) {
            ut.addSource(pdfFile);
        }
        ut.setDestinationFileName(RESULT_FILE_NAME);
        ut.mergeDocuments(MemoryUsageSetting.setupMainMemoryOnly());
    }

    private static boolean isPdfFile(File file) {
        String fileType = getFileType(file);
        if (fileType != null && fileType != "") {
            return "pdf".equalsIgnoreCase(fileType);
        }
        return false;
    }

    private static String getFileType(File file) {
        String fileName = file.getName();

        if (fileName == null || fileName.length() <= 0) {
            return "";
        }

        return fileName.substring(fileName.lastIndexOf('.')+1);
    }
}
