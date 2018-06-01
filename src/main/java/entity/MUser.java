package entity;

import javax.persistence.*;

@Entity
@Table(name="m_user")
public class MUser {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private String id;
  @Column(name = "realname")
  private String realname;
  @Column(name = "headimg")
  private String headimg;
  @Column(name = "sex")
  private String sex;
  @Column(name = "loginid")
  private String loginid;
  @Column(name = "departmentid")
  private String departmentid;
  @Column(name = "type")
  private String type;
  @Column(name = "role")
  private String role;
  @Column(name = "score")
  private String score;


  /**
   * Getter for property 'id'.
   *
   * @return Value for property 'id'.
   */
  public String getId() {
    return id;
  }

  /**
   * Setter for property 'id'.
   *
   * @param id Value to set for property 'id'.
   */
  public void setId(String id) {
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

    public String getDepartmentid() {
        return departmentid;
    }

    public void setDepartmentid(String departmentid) {
        this.departmentid = departmentid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
