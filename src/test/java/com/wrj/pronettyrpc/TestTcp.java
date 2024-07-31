//package com.wrj.pronettyrpc;
//
//import com.wrj.netty.client.ClientRequest;
//import com.wrj.netty.util.Response;
//import com.wrj.netty.client.TcpClient;
//import com.wrj.user.bean.User;
//import org.junit.Test;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotNull;
//
///**
// * @ClassName TestTcp
// * @Description TODO
// * @Author @O_o
// * @Date 2024-07-10 20:37
// * @Version 1.0
// */
//public class TestTcp {
//    @Test
//    public void testGetResponse() {
//        ClientRequest request = new ClientRequest();
//        request.setContent("测试tcp长连接请求");
//        Response resp = TcpClient.send(request);
//        System.out.println(resp.getResult());
//    }
//    @Test
//    public void testSaveUser() {
//        ClientRequest request = new ClientRequest();
//        User u = new User();
//        u.setId(1);
//        u.setName("张三");
//        request.setCommand("com.wrj.user.controller.UserController.saveUser");
//        request.setContent(u);
//        Response resp = TcpClient.send(request);
//        System.out.println(resp.getResult());
//    }
//
//    @Test
//    public void testSaveUsers() {
//        ClientRequest request = new ClientRequest();
//        List<User> users = new ArrayList<>();
//        User u = new User();
//        u.setId(1);
//        u.setName("张三");
//        users.add(u);
//        request.setCommand("com.wrj.user.controller.UserController.saveUsers");
//        request.setContent(users);
//        Response resp = TcpClient.send(request);
//        System.out.println(resp.getResult());
//    }
//
//}
