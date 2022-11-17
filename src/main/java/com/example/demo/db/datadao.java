package com.example.demo.db;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class datadao {
    // 一. 往博客列表里插入一个博客.
    public void insert(data data){
        //JDBC 的基本代码
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            // 1. 和数据库建立连接
            connection = DBUtil.getConnection();
            // 2. 构造 sql 语句
            String sql = "insert into datas values(null,?,?,?,now())";
            statement = connection.prepareStatement(sql);
            statement.setString(1,data.getTitle());
            statement.setString(2,data.getContent());
            statement.setInt(3,data.getUserId());
            // 3. 执行 sql
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            // 4. 关闭资源
            DBUtil.close(connection,statement,null);
        }
    }

    public List<data> selectAll(){
        List<data> datas = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DBUtil.getConnection();
            //我们想要让最新的排在博客列表最上面
            String sql = "select * from datas order by postTime desc";
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while(resultSet.next()){
                data data = new data();
                data.setdataId(resultSet.getInt("Id"));
                data.setTitle(resultSet.getString("title"));
                //这里我们要针对长的内容进行截取
                String content = resultSet.getString("content");
                //自己设定长度
                if(content.length() > 50){
                    content = content.substring(0,50)+"...";
                }
                data.setContent(content);
                data.setUserId(resultSet.getShort("userId"));
                data.setPostTime(resultSet.getTimestamp("postTime"));
                datas.add(data);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBUtil.close(connection,statement,resultSet);
        }
        return datas;
    }

    //三. 能够根据博客 id 获取到指定的博客内容(用于在博客详情页)
    public data selectOne(int Id){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DBUtil.getConnection();
            String sql = "select * from datas where Id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1,Id);
            resultSet = statement.executeQuery();
            // 这里根据主键来查询的,查到的要么是 0,要么是 1..
            if(resultSet.next()){
                data data = new data();
                data.setdataId(resultSet.getInt("Id"));
                data.setTitle(resultSet.getString("title"));
                data.setContent(resultSet.getString("content"));
                data.setUserId(resultSet.getShort("userId"));
                data.setPostTime(resultSet.getTimestamp("postTime"));
                return data;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBUtil.close(connection,statement,resultSet);
        }
        return null;
    }

    //四. 从博客列表中,根据博客 id 删除博客
    public void delete(int dataId){
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DBUtil.getConnection();
            String sql = "delete from datas where Id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1,dataId);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBUtil.close(connection,statement,null);
        }
    }


    //五. 修改
    public void update(data data) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            // 1. 建立连接
            connection = DBUtil.getConnection();
            // 2. 拼装 SQL 语句
            String sql = "update datas set content = ? ,title = ? where Id = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, data.getContent());
            statement.setString(2, data.getTitle());

            statement.setInt(3, data.getdataId());
            // 3. 执行 SQL 语句
            int ret = statement.executeUpdate();
            if (ret == 1) {
                System.out.println("编辑成功");
            } else {
                System.out.println("编辑失败");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection, statement, null);
        }
    }

    //6. 计算个人文章的总数
    public static Integer selectTotal(int userId){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DBUtil.getConnection();
            String sql = "select count(userId) from datas where userId = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1,userId);
            resultSet = statement.executeQuery();
            if(resultSet.next()){

            }
            return resultSet.getInt(1);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBUtil.close(connection,statement,resultSet);
        }
        return null;
    }

    public static void main(String[] args) {

        Integer ret1 = selectTotal(2);
        System.out.println(ret1);
        datadao dataDao = new datadao();
        data data = new data();
        data.setUserId(1);
        data.setdataId(1);
        data.setTitle("hahah");
        data.setContent("fff");
        dataDao.update(data);
    }
}


