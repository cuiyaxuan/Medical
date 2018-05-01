package entity;

import javax.persistence.*;

@Entity
@Table(name="m_nurse_documentation")
public class MNurseDocumentation {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private long id;
  @Column(name = "pid")
  private long pid;
  @Column(name = "dtemp")
  private String dtemp;
  @Column(name = "dnursing")
  private String dnursing;
  @Column(name = "dadvice")
  private String dadvice;
  @Column(name = "doperation")
  private String doperation;
  @Column(name = "gmt_create")
  private String gmtCreate;
  @Column(name = "departmentid")
  private long departmentid;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getPid() {
    return pid;
  }

  public void setPid(long pid) {
    this.pid = pid;
  }


  public String getDtemp() {
    return dtemp;
  }

  public void setDtemp(String dtemp) {
    this.dtemp = dtemp;
  }


  public String getDnursing() {
    return dnursing;
  }

  public void setDnursing(String dnursing) {
    this.dnursing = dnursing;
  }


  public String getDadvice() {
    return dadvice;
  }

  public void setDadvice(String dadvice) {
    this.dadvice = dadvice;
  }


  public String getDoperation() {
    return doperation;
  }

  public void setDoperation(String doperation) {
    this.doperation = doperation;
  }


  public String getGmtCreate() {
    return gmtCreate;
  }

  public void setGmtCreate(String gmtCreate) {
    this.gmtCreate = gmtCreate;
  }


  public long getDepartmentid() {
    return departmentid;
  }

  public void setDepartmentid(long departmentid) {
    this.departmentid = departmentid;
  }

}
