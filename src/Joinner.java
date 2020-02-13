import java.io.*;

public class Joinner {
    public static void main(String[] args) {
        try {
            byte[] b = new byte[100000];

            InputStreamReader ins = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(ins);

            System.out.println("Enter path of first part (Ex: e\\Test\\HmongWedding.mp4.001): ");
            String part_path = br.readLine();

            String file_name = part_path.substring(0, part_path.lastIndexOf("."));

            String part_no = part_path.substring(part_path.lastIndexOf(".") + 1);

            String file_extension = file_name.substring(file_name.lastIndexOf("."));

            System.out.println("Enter file name after joined with its path (Ex: e\\Test\\AfterJoinName): ");
            String new_path = br.readLine();

            File combine_file_path = new File(new_path);

            String path_parent = combine_file_path.getParent();

            int flag = 0;
            if (file_name.endsWith(file_extension)) {
                flag = 1;
            }

            File check_part_file_part = new File(part_path);
            File check_new_file_path = new File(path_parent);

            if (check_part_file_part.exists() && flag == 1 && check_new_file_path.exists()) {
                FileOutputStream fos = new FileOutputStream(new_path);

                int partIndex = 1;
                int read_bytes;
                String parts_name_path = "";

                while (true) {
                    parts_name_path = "";
                    if (part_no.startsWith("00")) {
                        parts_name_path = file_name + ".00" + partIndex;
                    } else {
                        parts_name_path = file_name + ".0" + partIndex;
                    }

                    File f = new File(parts_name_path);

                    if (f.exists()) {
                        FileInputStream fis = new FileInputStream(parts_name_path);

                        while (fis.available() != 0) {
                            read_bytes = fis.read(b, 0, 10000);
                            fos.write(b, 0, read_bytes);
                        }

                        System.out.println("Part " + partIndex + " joined!");
                        fis.close();
                        partIndex++;
                    } else {
                        System.out.println("File joined successfully!");
                        break;
                    }
                }
            } else if (!(check_new_file_path.exists())) {
                System.out.println("You write wrong path of new file!");
            } else if (flag == 0) {
                System.out.println("File extension does not match!");
            } else {
                System.out.println("File path or first part does not exist! ");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
