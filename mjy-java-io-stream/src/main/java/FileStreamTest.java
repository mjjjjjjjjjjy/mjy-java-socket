import java.io.*;

/**
 * @Author: Mo Jianyue
 * @Description
 * @Date: 2022/5/23 上午11:14
 * @Modified By
 */
public class FileStreamTest {

    public static void main(String[] args) throws IOException {
        String property = System.getProperty("user.dir");
        String readme = property+File.separator+ "testfile"+File.separator+"testStream.md";
        FileInputStream fileInputStream = new FileInputStream(readme);
        int read = fileInputStream.read();
        System.out.println(read);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
        String s = bufferedReader.readLine();
        System.out.println(s);
    }

    public static class 只读取部分数据并且放到数组中{
        public static void main(String[] args) throws IOException {
            String property = System.getProperty("user.dir");
            String readme = property+File.separator+"testfile"+File.separator+"testStream.md";
            FileInputStream fileInputStream = new FileInputStream(readme);
            byte[] bytes = new byte[3];
            int count = 0;
            while ((count = fileInputStream.read(bytes)) != -1){
                System.out.print(new String(bytes));
            }
            System.out.println("");
            if (fileInputStream != null){
                fileInputStream.close();
            }
        }
    }

    public static class 拷贝文件{
        public static void main(String[] args) throws IOException {
            String property = System.getProperty("user.dir");
            String readme = property+File.separator+"testfile"+File.separator+"testStream.md";
            String readme2 = property+File.separator+"testfile"+File.separator+"testStream.md2";
            FileInputStream fileInputStream = new FileInputStream(readme);
            FileOutputStream copy = new FileOutputStream(readme2);
            byte[] bytes = new byte[3];
            int count = 0;
            while ((count = fileInputStream.read(bytes)) != -1){
                copy.write(bytes,0,count);
                System.out.print(new String(bytes));
            }
            System.out.println("");
            if (fileInputStream != null){
                fileInputStream.close();
            }
            if (copy != null){
                copy.close();
            }
        }
    }


    /**
     * 什么是缓存区？
     * 定义：缓存区相当于缓存，它是存在内存中的
     * 写操作：
     * 没有使用缓存区：CPU读取每个字节之后直接操作磁盘（性能比较底）进行写完，写操作的瓶颈就会出现，因为每个字节都会操作一次磁盘
     * 使用缓冲区：那么每次会将字符放入缓存区（内存），等缓冲区满了之后，才一次性写入磁盘
     * 因为内存的操作速度远远大于磁盘，因此带缓冲区的输入流和输出流实现的效率就非常高（比如扔垃圾，一次性扔完和一次次扔肯定消耗的时间是有很大差距的）
     * ————————————————
     * 版权声明：本文为CSDN博主「你这家伙」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
     * 原文链接：https://blog.csdn.net/wkh18891843165/article/details/115310276
     */
    public static class 带缓冲的字节流操作{
        public static void main(String[] args) throws IOException {
            String property = System.getProperty("user.dir");
            String readme = property+File.separator+"testfile"+File.separator+"testStream.md";
            String readme2 = property+File.separator+"testfile"+File.separator+"testStream.md2";
            BufferedInputStream fileInputStream = new BufferedInputStream(new FileInputStream(readme));
            BufferedOutputStream copy = new BufferedOutputStream(new FileOutputStream(readme2));
            byte[] bytes = new byte[3];
            int count = 0;
            while ((count = fileInputStream.read(bytes)) != -1){
                copy.write(bytes,0,count);
                System.out.print(new String(bytes));
            }
            System.out.println("");
            if (fileInputStream != null){
                fileInputStream.close();
            }
            if (copy != null){
                copy.close();
            }
        }
    }
}
