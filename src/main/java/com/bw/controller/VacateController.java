package com.bw.controller;

import com.bw.Tree.QQTree;
import com.bw.entity.QQDept;
import com.bw.entity.QQFriends;
import com.bw.mapper.DeptMapper;
import com.bw.entity.JYUser;
import com.bw.entity.Redis;
import com.bw.mapper.FriendMapper;
import com.bw.mapper.QQMapper;
import com.bw.mapper.ReadisMapper;
import com.bw.mapper.UserMapper;
import com.bw.util.MD5Util;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by lenovo on 2017/7/11.
 */
@Controller
public class VacateController {

    //创建QQ好友管理的mapper对象
    @Autowired
    private QQMapper qqMapper;

    //创建usermapper用户加密的对象
    @Autowired
    private UserMapper userMapper;

    //创建QQ好友FriendMapper对象
    @Autowired
    private FriendMapper friendMapper;

    //readismapper对象
    @Autowired
    private ReadisMapper readisMapper;

    //创建readis的存储数据的对象
    @Autowired
    private RedisTemplate redisTemplate;

    //创建对配置文件的密码加密属性
    @Autowired
    private StringEncryptor stringEncryptor;

    //创建QQ好友类别对象
    @Autowired
    private DeptMapper deptMapper;

    /**
     * 负载均衡，即价格项目打成jar包的形式，通过在配置文件中修改端口号
     * 来创建不同的项目，然后在通过clean和install来清除和打包项目，然后在将
     * target下边的打包好的项目放到一个位置，通过修改包的名称来访问不同的端口，同时运行多个项目
     * 将一个项目分成多个服务器来响应
     * 在左侧的vacate下边有clean和install，页可以在IDear的左下方的黑窗口Termina下
     * 通过命令来清除和打包醒目
     * 命令如下：mvn clean intall
     * 然后是dir-->cd target-->java -jar 包名（就这样就将项目启动了）
     *
     */

    /**
     * session共享
     */
    @RequestMapping("/uid")
    @ResponseBody
    public String uid(HttpSession session) {
        UUID uid = (UUID) session.getAttribute("uid");
        System.out.println("=========>>"+uid);
        if (uid == null) {
            uid = UUID.randomUUID();
        }
        session.setAttribute("uid", uid);
        return "hello 11111111111111111"+session.getId();
    }


    @RequestMapping("hello")
    @ResponseBody
    public  String hello(){
        return  "hello 11111111111111111";

    }


    /**
     * 分页查询
     */
    @RequestMapping("pageable")
    public String pageables(Map<String ,Object> map){
        System.out.println("============分页查询=============");
        //需要查出总共多少条
        int count = (int) friendMapper.count();
        System.out.println("QQ好友一共有:"+count+"个");
        //定义出每页多少条
        Integer num = 3;
        //查询出总页数
        int page = (count%num==0)?(count/num):(count/num)+1;
        System.out.println("该数据库一共有"+page+"页");
        //定义排序贵规则
        Sort sort = new Sort(Sort.Direction.DESC,"id");
        //Pageable是spring封装的分页实现类,时好用的时候需要传入页数,每页的条数,排序规则
        Pageable pageable = new PageRequest(page,num,sort);
        //调用分页查询的方法
        Page<QQFriends> all = friendMapper.findAll(pageable);
        //遍历集合
        for (QQFriends f: all){
            System.out.println(f);
        }
        map.put("friendlist",all);

        return "showFriend";
    }


    /**
     * 修改好修的信息
     */
    @RequestMapping("updateFriends")
    public String updateFriends(QQFriends friends){
        System.out.println("======修改好修的信息========");
        System.out.println(friends);
        friendMapper.modifyById(friends.getQname(),friends.getMessage(),friends.getTalks(),friends.getLogintime(),friends.getDept().getId(),friends.getId());
        return "redirect:friendslist";

    }


    /**
     * 根据id查询对象
     */
    @RequestMapping("hxFriends")
    @ResponseBody
    public Map<String,Object> hxFriends(Integer id){
        System.out.println("=======根据id查询对象===========");
        QQFriends one = friendMapper.findOne(id);
        List<QQDept> dlist = deptMapper.findAll();
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("one",one);
        map.put("dlist",dlist);
        System.out.println(one);
        return  map;
    }

    /**
     * 删除好友
     */
    @RequestMapping("deleteUser")
    public String deleteUser(Integer id){
        System.out.println("==========删除方法=====");
        //调用删除的方法
        friendMapper.delete(id);
        //重定向到查询方法
        return  "redirect:friendslist";
    }



    /**
     * 查询所有的角色
     */
    @RequestMapping("getdeptlist")
    @ResponseBody
    public List<QQDept> getdeptlist(){
        System.out.println("=========查询所有的角色HttpSession session============");
        List<QQDept> dlist = deptMapper.findAll();
      //  session.setAttribute("dlist",dlist);
        for (QQDept d:dlist
             ) {
            System.out.println(d);
        }
        return dlist;
    }


    /**
     * 添加好友
     */
     @RequestMapping("addFriend")
     public String addFriend(QQFriends friends){
         System.out.println("=========添加好友===========");
         QQFriends save = friendMapper.save(friends);
         System.out.println(save);
         return "redirect:friendslist";
     }

    /**
     * 获取好友列表
     */
      @RequestMapping("friendslist")

      public String getFriends(Map<String ,Object> map){
          System.out.println("=======获取好友列表==========");
          //调用查询好友列表
          List<QQFriends> list = friendMapper.findAll();

          for (QQFriends f : list
               ) {
              System.out.println(f);
          }
          map.put("flist",list);

          return "showFriend";
      }



    /**
     * 给配置文件的密码加密==StringEncryptor
     * 在pom文件中导入Java Simplified Encryption加密的依赖
     * 次依赖提供了一个加密的类==>StringEncryptor,通过该类的encrypt方法给密码加密
     */
    @RequestMapping("ENC")
    @ResponseBody
    public String enc(){
     String pwd = stringEncryptor.encrypt("root");
      return  pwd;
    }


    /**
     * readis测试RedisTemplate
     */
    @RequestMapping("/selectUserByName")
    @ResponseBody
    public Redis selectUserByName(String name){
        Redis user = null;
       // 在RedisTemplate中，已经提供了一个工厂方法:opsForValue()。这个方法会返回一个默认的操作类
        //这给个类对redis中的数据进行一定的操作
        ValueOperations<String,Redis> operations = redisTemplate.opsForValue();
       //在RedisTemplate类中，提供了判断键是否在换粗中存在的方法
        Boolean exists = redisTemplate.hasKey("user");

        if(exists){
            //如果该键在缓存中存在就取出来
            user =  operations.get("user");
            System.out.println("exists is true" + user.getName());

        }else{

            //如果redis缓存中不存在,就在从数据库中进行查询,存到redis的缓存中
            user = readisMapper.findUserByName(name);
            //修改寸处对象的键值
            operations.set("user",user);
            System.out.println("exists is false");
        }
        return user;

    }

    //redis的第一次访问,即将查出来的数据存到redis缓存中
    @RequestMapping("/getUserName")
    @ResponseBody
    public Redis getUserName(String name){
        //第一次根据用户名查询,存到redis缓存中
        System.out.println(name);
        return  readisMapper.findUserByName(name);
    }



    /**
     * 登录
     */
     @RequestMapping("login")
     public String login(JYUser user){
         System.out.println("从前台获取的对象:"+user);
         String name = user.getName();
         String pwd = user.getPwd();
         //调用MD5对密码进行加密
         String p = MD5Util.MD5(pwd);
         //6of4420aoM44o32sF0341s53so5sM43s==>>5921
         System.out.println("加密后的密码为:"+p);
         //调用根据用户名和密码查询对象findByUserNameOrEmail
         JYUser u = userMapper.findByNameAndPwd(name,p);
         System.out.println(u);
         if(u!=null&&u.getPwd().equals(p)){

             return "toFreamSet";
         }else {
             return  "login";
         }
         }



    /**
     * 到注册页面
     */
    @RequestMapping("toregister")
    public String register(){
        return "register";
    }

    /**
     * 注册,调用加密的工具类给密码加密
     */
    @RequestMapping("jm")
    public String jm(JYUser user){
        System.out.println("========user"+user);
        String pwd = user.getPwd();
        //调用MD5进行加密
        String mpwd = MD5Util.MD5(pwd);
        user.setPwd(mpwd);
        System.out.println("加密后的密码为:"+mpwd);//6of4420aoM44o32sF0341s53so5sM43s
        userMapper.save(user);
        return  "login";
    }

    /**
     * 进入到创建页面布局的html
     */
    @RequestMapping("tree")
    public String totree(){
        return "toFreamSet";
    }

    /**
     * 查询所有的树
     */
    @RequestMapping("showTree")
    @ResponseBody
    public List<QQTree> showtree(){
        System.out.println("=========树的所有内容========");
      List<QQTree> list =  qqMapper.findAll();
        for (QQTree t:list
             ) {
            System.out.println(t);
        }
        return  list;
    }

}
