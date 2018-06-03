package entity;

import javax.persistence.*;

@Entity
@Table(name="m_remind")
public class MRemind {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private String id;
  @Column(name = "userid")
  private long userid;
  @Column(name = "message")
  private String message;
  @Column(name = "gmt_create")
  private String gmtCreate;


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public long getUserid() {
    return userid;
  }

  public void setUserid(long userid) {
    this.userid = userid;
  }


  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }


  public String getGmtCreate() {
    return gmtCreate;
  }

  public void setGmtCreate(String gmtCreate) {
    this.gmtCreate = gmtCreate;
  }

}
