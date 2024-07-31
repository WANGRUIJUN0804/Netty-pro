//package com.wrj.pronettyrpc;
//
//import com.wrj.netty.annotation.RemoteInvoke;
//import com.wrj.netty.client.ClientRequest;
//import com.wrj.netty.client.TcpClient;
//import com.wrj.netty.util.Response;
//import com.wrj.user.bean.User;
//import com.wrj.user.remote.UserRemote;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * @ClassName RemoteInvokingTest
// * @Description TODO
// * @Author @O_o
// * @Date 2024-07-12 16:14
// * @Version 1.0
// */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = RemoteInvokingTest.class)
//@ComponentScan("com.wrj")
//public class RemoteInvokingTest {
//
//    @RemoteInvoke
//    private UserRemote userRemote;
//    @Test
//    public void testSaveUser() {
//        ClientRequest request = new ClientRequest();
//        User u = new User();
//        u.setId(1);
//        u.setName("张三");
//        userRemote.saveUser(u);
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
//        userRemote.saveUsers(users);
//    }
//}
