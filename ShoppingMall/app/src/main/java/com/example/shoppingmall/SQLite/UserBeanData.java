package com.example.shoppingmall.SQLite;

//用户数据Bean
public class UserBeanData {

    private int id;
  private String username;
  private int age;
  private String desc;
  private String sex;
  private String password;
  private String e_mail;

    public UserBeanData() {
    }

    public UserBeanData(int _id, String username, int age, String desc, String sex, String password, String e_mail) {
        this.id = id;
        this.username = username;
        this.age = age;
        this.desc = desc;
        this.sex = sex;
        this.password = password;
        this.e_mail = e_mail;
    }

    public int get_id() {
        return id;
    }

    public void set_id(int _id) {
        this.id = _id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getE_mail() {
        return e_mail;
    }

    public void setE_mail(String e_mail) {
        this.e_mail = e_mail;
    }

    @Override
    public String toString() {
        return "UserBeanData{" +
                "_id=" + id +
                ", username='" + username + '\'' +
                ", age=" + age +
                ", desc='" + desc + '\'' +
                ", sex='" + sex + '\'' +
                ", password='" + password + '\'' +
                ", e_mail='" + e_mail + '\'' +
                '}';
    }
}
