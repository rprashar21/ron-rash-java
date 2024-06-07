//package projects.compareJsons;
//
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import java.io.File;
//import java.io.IOException;
//
//public class JsonFolderComparator {
//
//    public static void main(String[] args) {
//        String folderPath = "path/to/folder";
//
//        File folder = new File(folderPath);
//        File[] files = folder.listFiles();
//
//        if (files != null) {
////            ObjectMapper mapper = new ObjectMapper();
//            for (int i = 0; i < files.length - 1; i++) {
//                File file1 = files[i];
//                for (int j = i + 1; j < files.length; j++) {
//                    File file2 = files[j];
//
//                    try {
//                        JsonNode json1 = mapper.readTree(file1);
//                        JsonNode json2 = mapper.readTree(file2);
//
//                        if (json1.equals(json2)) {
//                            System.out.println(file1.getName() + " and " + file2.getName() + " are identical.");
//                        } else {
//                            System.out.println(file1.getName() + " and " + file2.getName() + " are not identical.");
//                            // You can perform more specific comparison operations here if needed.
//                        }
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }
//    }
//}
//
