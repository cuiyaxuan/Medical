package entity;

import javax.persistence.*;

@Entity
public class MUser {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private long id;
  @Column(name = "realname")
  private String realname;
  @Column(name = "headimg")
  private String headimg;
  @Column(name = "sex")
  private String sex;
  @Column(name = "loginid")
  private String loginid;
  @Column(name = "departmentid")
  private long departmentid;
  @Column(name = "type")
  private long type;
  @Column(name = "role")
  private long role;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getRealname() {
    return realname;
  }

  public void setRealname(String realname) {
    this.realname = realname;
  }


  public String getHeadimg() {
    return headimg;
  }

  public void setHeadimg(String headimg) {
    this.headimg = headimg;
  }


  public String getSex() {
    return sex;
  }

  public void setSex(String sex) {
    this.sex = sex;
  }


  public String getLoginid() {
    return loginid;
  }

  public void setLoginid(String loginid) {
    this.loginid = loginid;
  }


  public long getDepartmentid() {
    return departmentid;
  }

  public void setDepartmentid(long departmentid) {
    this.departmentid = departmentid;
  }


  public long getType() {
    return type;
  }

  public void setType(long type) {
    this.type = type;
  }


  public long getRole() {
    return role;
  }

  public void setRole(long role) {
    this.role = role;
  }

}
