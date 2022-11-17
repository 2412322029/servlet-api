package com.example.demo;

import com.example.demo.db.User;
import com.example.demo.db.UserDao;
import com.example.demo.db.data;
import com.example.demo.db.datadao;

import java.util.List;

public class test {

    public static void main(String args[]){
        datadao u=new datadao();
        UserDao x=new UserDao();
        data sss=new data();
//        sss.setdataId(3);
//        sss.setTitle("测试文本");
//        sss.setContent("long long text");
//        u.insert(sss);

        List<User> a= x.selectalluserId();

        for(User pers : a)
        {
            System.out.print(pers.getUserId()+pers.getUsername()+pers.getPassword()+"\n");
        }
    }
}
