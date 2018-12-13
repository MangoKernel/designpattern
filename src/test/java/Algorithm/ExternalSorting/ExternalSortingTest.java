package Algorithm.ExternalSorting;

import org.junit.Test;

import java.io.IOException;

public class ExternalSortingTest {

    /**
     * 步骤1：
     * 将一个有序文件打乱成无序文件，模拟排序原材料
     * @throws IOException
     */
    @Test
    public void unorderedFileTest() throws IOException {
        ExternalSort.unorderedFile();
    }

    /**
     * 步骤2：
     * 切割成有序小文件
     * @throws IOException
     */
    @Test
    public void splitFileTest() throws IOException {
        ExternalSort.splitFile();
    }

    /**
     * 步骤3：
     * 对所有文件进行排序操作
     * @throws IOException
     */
    @Test
    public void SortingTest() throws IOException {
        String file1;
        String file2;
        String result;
        int resultIndex = 1;
        for (int i = 1; i < 11; i++) {
            if (i == 1) {
                result = "result" + resultIndex;
                file1 = "splitFile" + i;
                file2 = "splitFile" + ++i;
            } else {
                result = "result" + resultIndex;
                file1 = "result" + (resultIndex - 1);
                file2 = "splitFile" + i;
            }
            resultIndex++;
            if (i == 10) {
                result = "result";
            }
            ExternalSort.sortTwoFile(file1, file2, result);
        }
    }

}
