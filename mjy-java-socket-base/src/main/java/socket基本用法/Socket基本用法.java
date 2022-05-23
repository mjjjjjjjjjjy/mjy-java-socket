package socket基本用法;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * @Author: Mo Jianyue
 * @Description
 * @Date: 2022/5/23 上午9:49
 * @Modified By
 */
public class Socket基本用法 {

    public static class 服务端 {
        public static void main(String[] args) throws IOException {

            ServerSocket serverSocket = new ServerSocket(9988);
            while (true){
                //每一个链接，就会获取一个socket实例。等待下一个链接的时候，进程会被阻塞
                Socket accept = serverSocket.accept();
                new Thread(()->{
                    BufferedReader in = null;
                    PrintWriter out = null;
                    try {
                        in = new BufferedReader(new InputStreamReader(accept.getInputStream()));
                        out = new PrintWriter(accept.getOutputStream(), true);

                        System.out.println("客户端链接成功");
                        for(;;){
                            String s = in.readLine();
                            if (s != null){
                                System.out.println("获取输入："+s);

                                if (s.contains("finish")){
                                    System.out.println("客户端发送finish,结束连接");
                                    out.println("200");
                                    break;
                                }else {
                                    out.println("处理完成，等待下一个数据");
                                }
                            }

                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        System.out.println("关闭连接");
                        out.close();
                        if (in != null){
                            try {
                                in.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).start();

            }



        }
    }

    public static class 客户端 {
        public static void main(String[] args) throws IOException {
            String name = "客户端1";
            clientConnet(name);
        }
    }

    public static class 客户端2 {
        public static void main(String[] args) throws IOException {
            String name = "客户端2";
            clientConnet(name);
        }
    }

    private static void clientConnet(String name) throws IOException {
        BufferedReader in = null;
        PrintWriter out = null;
        Socket socket = new Socket("127.0.0.1", 9988);
        //自动刷新
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        try {
            for (;;){
                Scanner scanner = new Scanner(System.in);
                String s1 = scanner.nextLine();
                out.println(name +"-向服务端输入数据："+s1);
                String s = in.readLine();
                System.out.println("获取返回数据："+s);
                if (s.equals("200")){
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("客户端链接结束，关闭连接");
            out.close();
            in.close();
        }
    }


}
