# PDFMerger
This utility allow to merge several PDF files to generate a merged.pdf file

### Usage
  - download PdfMerger.jar to your folder wich contains PDF files to merge
  - make sure your have Java installed and run command: 
      ```
      java -jar PdfMerger.jar
      ```
  - merged PDF file is ready and named "merged.pdf".
  
Note: Your PDF files are sorted by their file name, so you can rename your PDF files before merging to decide the order.
  
### For developpers
  - clone the project
  - mvn package
  - use the /target/PdfMerger-jar-with-dependencies.jar file
