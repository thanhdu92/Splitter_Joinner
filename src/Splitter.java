import java.io.*;
import java.util.Scanner;

public class Splitter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            byte[] b = new byte[1000000];
            int partIndex = 1, readOverByte = 0, capacityAfterSplit;
            String splitFileName = "";
            InputStreamReader ins = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(ins);

            System.out.println("Enter max capacity (byte) of each file after split : ");
            capacityAfterSplit = Integer.parseInt(scanner.nextLine());

            System.out.println("Enter path of source or original file with extension (Ex:e:\\Test\\Hmongwedding.mp4): ");
            String path = br.readLine();

            FileInputStream fis = new FileInputStream(path);
            int read_bytes;
            while (fis.available() != 0) {
                readOverByte = 0;
                splitFileName = "";
                if (partIndex <= 9) {
                    splitFileName = path + ".00" + partIndex;
                } else {
                    splitFileName = path + ".0" + partIndex;
                }

                FileOutputStream fos = new FileOutputStream(splitFileName);

                while (readOverByte < capacityAfterSplit && fis.available() != 0) {
                    read_bytes = fis.read(b, 0, 1000000);
                    readOverByte = readOverByte + read_bytes;
                    fos.write(b, 0, read_bytes);
                }
                System.out.println("Part " + partIndex + " Created.");
                partIndex++;
            }

            System.out.println("File split successfully!");
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

