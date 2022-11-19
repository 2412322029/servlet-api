package com.example.demo.db;


import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class datadao {
    public String insert(data data,User user) {
        //JDBC 的基本代码
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            UserDao userDao=new UserDao();
            if(userDao.selectByName(user.getUsername()).getPassword().equals(user.getPassword())){
                // 1. 和数据库建立连接
                connection = DBUtil.getConnection();

                String sql = "insert into datas values(null,?,?,?,now())";
                statement = connection.prepareStatement(sql);
                statement.setString(1, data.getTitle());
                statement.setString(2, data.getContent());
                statement.setInt(3, user.getUserId());
                // 3. 执行 sql
                int i = statement.executeUpdate();
                return "已更新" + i + "行";
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            // 4. 关闭资源
            DBUtil.close(connection, statement, null);
        }
        return "插入错误";
    }

    public List<data> selectAll() {
        List<data> datas = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DBUtil.getConnection();
            String sql = "select * from datas order by postTime desc";
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                data data = new data();
                data.setdataId(resultSet.getInt("Id"));
                data.setTitle(resultSet.getString("title"));
                //这里我们要针对长的内容进行截取
                String content = resultSet.getString("content");
                //自己设定长度
                if (content.length() > 200) {
                    content = content.substring(0, 200) + "...";
                }
                data.setContent(content);
                data.setUserId(resultSet.getShort("userId"));
                data.setPostTime(resultSet.getTimestamp("postTime"));
                datas.add(data);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBUtil.close(connection, statement, resultSet);
        }
        return datas;
    }


    public data selectOne(int Id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DBUtil.getConnection();
            String sql = "select * from datas where Id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, Id);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
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
        } finally {
            DBUtil.close(connection, statement, resultSet);
        }
        return null;
    }


    public void delete(int dataId,User user) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            UserDao userDao=new UserDao();
            if(userDao.selectByName(user.getUsername()).getPassword().equals(user.getPassword())) {
                connection = DBUtil.getConnection();
                String sql = "delete from datas where Id = ?";
                statement = connection.prepareStatement(sql);
                statement.setInt(1, dataId);
                statement.executeUpdate();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBUtil.close(connection, statement, null);
        }
    }


    public String update(data data,User user) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            UserDao userDao=new UserDao();
             if(userDao.selectByName(user.getUsername()).getPassword().equals(user.getPassword())){
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
                    return "编辑成功";
                } else {
                    return "编辑失败";
                }
            }else {
                 return "验证错误";
             }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection, statement, null);
        }
        return null;
    }


    public static Integer selectTotal(int userId) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DBUtil.getConnection();
            String sql = "select count(userId) from datas where userId = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, userId);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {

            }
            return resultSet.getInt(1);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBUtil.close(connection, statement, resultSet);
        }
        return null;
    }
    public String getlastdate() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DBUtil.getConnection();
            String sql = "select max(postTime) as last from datas;";
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            resultSet.next();
            Timestamp t=resultSet.getTimestamp("last");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return simpleDateFormat.format(t);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBUtil.close(connection, statement, resultSet);
        }
        return null;
    }

    public static void main(String[] args) {

        //dataid=9&title=post&content=11111&username=admin&password=123456
        datadao dataDao = new datadao();
        UserDao userDao=new UserDao();;
        data data = new data();
        User user=new User();
        user.setUsername("admin");
        user.setPassword("123456");
        data.setdataId(9);
        data.setTitle("post11111111");
        data.setContent("xxxxxxxxx");

        System.out.println(user.getPassword().equals("123456"));
        System.out.println(userDao.selectByName(user.getUsername()).getPassword().equals(user.getPassword()));

        System.out.println(dataDao.update(data,user));
    }
}


