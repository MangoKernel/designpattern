package Algorithm.ExternalSorting;

import org.springframework.util.StringUtils;

import java.io.*;
import java.util.*;

/**
 * Created by MangoKernel on 2018/12/13.
 * description:外部排序-二路合并排序
 *   模拟场景：
 * 服务器内存过小，每次只能处理1000条数据排序，但目前需求是对一个有10000条数据的文件进行排序，
 * 且只能在此内存中进行排序操作，不可借助其他环境或工具
 */
public class ExternalSort {
    private static final int MEMORY_BUFFER_LIMIT_MAX = 1000;

    private static int splitFileCount = 0;

    /**
     * 打乱文件顺序
     * @throws IOException
     */
    public static void unorderedFile() throws IOException  {
        Map<String,String> map = new HashMap();
        InputStream inputStream = new FileInputStream("src" + File.separator +"main"
                + File.separator + "resources"
                + File.separator + "sort"
                + File.separator + "tenThousand.txt");
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader reader = new BufferedReader(inputStreamReader);
        String tmp;
        while ((tmp = reader.readLine()) != null) {
            map.put(tmp, tmp);
        }
        OutputStream outputStream = new FileOutputStream("unorderedTenThousand.txt",true);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
        BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
        Iterator iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry =(Map.Entry<String,String>)iterator.next() ;
            bufferedWriter.write(entry.getValue());
            bufferedWriter.newLine();
        }
        bufferedWriter.flush();
    }

    /**
     * 切割成10个小文件
     * @throws IOException
     */
    public static void splitFile() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("unorderedTenThousand.txt")));
        int splitNumberTotal = 0;
        int index = 0;
        while (splitNumberTotal < 10000) {
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("splitFile" + ++splitFileCount)));
            List<Integer> tempList = new ArrayList<>();
            while (index < MEMORY_BUFFER_LIMIT_MAX) {
                tempList.add(Integer.parseInt(bufferedReader.readLine()));
                splitNumberTotal++;
                index++;
            }
            sortList(tempList);
            for (int i = 0; i < tempList.size(); i++) {
                bufferedWriter.write(tempList.get(i).toString());
                if (i < MEMORY_BUFFER_LIMIT_MAX - 1) {
                    bufferedWriter.newLine();
                }
            }
            index = 0;
            bufferedWriter.flush();
        }
    }

    /**
     * list文件排序
     * @param sortList
     */
    public static void sortList(List<Integer> sortList) {
        for (int i = 0; i < sortList.size() - 1; i++) {
            for (int j = 0; j < sortList.size() - i - 1; j++) {
                int temp;
                if (sortList.get(j) > sortList.get(j + 1).intValue()) {
                    temp = sortList.get(j);
                    sortList.set(j,sortList.get(j + 1));
                    sortList.set(j + 1, temp);
                }
            }
        }
    }

    /**
     * 对两个文件进行排序
     */
     public static void sortTwoFile(String ins1FileName,String ins2FileName,String resultFileName) throws IOException {
         BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(resultFileName,true)));
         BufferedReader buff1 = new BufferedReader(new InputStreamReader(new FileInputStream(ins1FileName)));
         BufferedReader buff2 = new BufferedReader(new InputStreamReader(new FileInputStream(ins2FileName)));
         String s1 = buff1.readLine();
         String s2 = buff2.readLine();
         int index = 0;
         while (!(StringUtils.isEmpty(s1) && StringUtils.isEmpty(s2))) {
             index++;
             if (StringUtils.isEmpty(s1) && !StringUtils.isEmpty(s2)) {
                 bufferedWriter.write(s2);
                 bufferedWriter.newLine();
                 s2 = buff2.readLine();
                 continue;
             }
             if (StringUtils.isEmpty(s2) && !StringUtils.isEmpty(s1)) {
                 bufferedWriter.write(s1);
                 bufferedWriter.newLine();
                 s1 = buff1.readLine();
                 continue;
             }
             if (StringUtils.isEmpty(s1) && StringUtils.isEmpty(s2)) {
                 bufferedWriter.flush();
             }
             if (Integer.parseInt(s1) > (Integer.parseInt(s2))) {
                 bufferedWriter.write(s2);
                 bufferedWriter.newLine();
                 s2 = buff2.readLine();
             }else{
                 bufferedWriter.write(s1);
                 bufferedWriter.newLine();
                 s1 = buff1.readLine();
             }
             if (index >= MEMORY_BUFFER_LIMIT_MAX) {
                 index = 0;
                 bufferedWriter.flush();
                 bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(resultFileName,true)));
             }
         }
         bufferedWriter.flush();
     }
}
