package haotianyi.win;


import java.io.BufferedReader;
import java.io.File;

import java.io.FileReader;
import java.util.Scanner;

/**
 *  一个分析代码行数的utils，主要是as工程
 * @author HaoTianYi
 * @version v1.0
 */
public class LinesUtils {
    private static long lines;

    public static void main(String[] args) {
        String traget = input();
        File file = new File(traget);
        binaLi(file);
        System.out.println("总共有" + lines + "行");
    }

    /**
     * 遍历代码行数，用拼音我也是醉了
     * @param file
     */
    private static void binaLi(File file) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
                if (f.isDirectory()) {
                    binaLi(f);
                } else if (f.getName().endsWith(".xml") || f.getName().endsWith(".java")) {
                    lines += countLines(f);
                }
            }
        } else {
            if (file.getName().endsWith(".xml") || file.getName().endsWith(".java")) {
                lines += countLines(file);
            }
        }

    }

    /**
     * 计算每一个文件的代码行数
     * @param file
     * @return
     */
    private static long countLines(File file) {
        long index = 0;
        if (isNeed(file)){
            return 0;
        }

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                index++;
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return index;
    }

    /**
     * 判断是否需要这个文件，判断的是android studio工程
     * @param file
     * @return
     */
    private static boolean isNeed(File file) {
        return file.getAbsolutePath().contains("build")||file.getAbsolutePath().contains(".idea")||file.getAbsolutePath().contains(".gradle")||file.getAbsolutePath().contains("gradle");
    }

    /**
     * 设置输入的方法
     * @return   输入的文件夹
     */
    private static String input() {
        System.out.println("请你输入目标文件夹");
        Scanner scanner = new Scanner(System.in);
        String traget = scanner.nextLine().trim();
        scanner.close();
        return traget;
    }
}
