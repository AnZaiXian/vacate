package com.bw;

//import com.bw.entity.JYUser;
import com.bw.entity.EntityUser;
import com.bw.mail.MailService;
import com.bw.mongodb.UserEntityMongoRepository;
import com.bw.servcice.MsgFutureServer;
import org.apache.tomcat.jni.Thread;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.Future;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VacateApplicationTests {

   /*
    发送邮件
    @Autowired
    private MailService mailService;
    */

    //nginx异步==>负载均衡
    @Autowired
    private MsgFutureServer msgFutureServer;

    /**
     * 在mongodb中进行增删改查
     */
    @Autowired
    private UserEntityMongoRepository userDao;

    /**
     * 添加用户
     */
   @Test
   public void testSaveUser(){
       EntityUser user=new EntityUser();
       user.setId(5);
       user.setUserName("小明");
       user.setPassWord("ggggooo123");
       userDao.saveUser(user);

   }
    /**
     * 查询用户
     */
    @Test
    public void findUserByUserName(){
        EntityUser user= userDao.findUserByUserNamae("小明");
        System.out.println("user is "+user);
    }
    /**
     * 修改用户
     */
    @Test
    public void updateUser(){
        EntityUser user=new EntityUser();
        user.setId(2);
        user.setUserName("天空111");
        user.setPassWord("fffxxxx6666");
        userDao.updateUser(user);
    }
    /**
     * 删除用户
     */
   @Test
   public void deleteUserById(){
       userDao.deleteUserById(3l);

   }


    /**
     * 负载均衡==>异步
     * @throws Exception
     */
    @Test
	public void contextLoads() throws Exception {

        long startTime = System.currentTimeMillis();

        Future<String> task1 = msgFutureServer.sendA();
        Future<String> task2 = msgFutureServer.sendB();

        while(true) {
            if(task1.isDone() && task2.isDone() ) {
                break;
            }
        }

        long endTime = System.currentTimeMillis();
        System.out.println("总耗时：" + (endTime - startTime));
    }


    /**
     * 发送邮件
     */

    //Thread thread = new Thread();
   /* for (int i=0;i<21;i++){
       // thread.sleep(10000);
     //  thread.wait(100000);
        //164548218@qq.com    邮箱
        mailService.sendSimpleMail("3496653736@qq.com","渣渣",
                "再见");

    }
   */

	/*	JYUser user = new JYUser();
        user.setPwd("123");
        String pwd = user.getPwd();
        System.out.println(pwd);*/





}
