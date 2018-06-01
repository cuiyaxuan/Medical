package entity;


import javax.persistence.*;

@Entity
@Table(name="m_record")
public class MRecord {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private long id;
  @Column(name = "pid")
  private long pid;
  @Column(name = "rcomplain")
  private String rcomplain;
  @Column(name = "rpresent")
  private String rpresent;
  @Column(name = "rhistory")
  private String rhistory;
  @Column(name = "rperson")
  private String rperson;
  @Column(name = "rmarriage")
  private String rmarriage;
  @Column(name = "rfamily")
  private String rfamily;
  @Column(name = "rdepartment")
  private String rdepartment;
  @Column(name = "rstate")
  private String rstate;
  @Column(name = "gmt_create")
  private String gmtCreate;
  @Column(name = "rpass")
  private String rpass;
  @Column(name = "rinfaction")
  private String rinfaction;
  @Column(name ="userloginid")
  private String userloginid;

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

  public String getRcomplain() {
    return rcomplain;
  }

  public void setRcomplain(String rcomplain) {
    this.rcomplain = rcomplain;
  }

  public String getRpresent() {
    return rpresent;
  }

  public void setRpresent(String rpresent) {
    this.rpresent = rpresent;
  }

  public String getRhistory() {
    return rhistory;
  }

  public void setRhistory(String rhistory) {
    this.rhistory = rhistory;
  }

  public String getRperson() {
    return rperson;
  }

  public void setRperson(String rperson) {
    this.rperson = rperson;
  }

  public String getRmarriage() {
    return rmarriage;
  }

  public void setRmarriage(String rmarriage) {
    this.rmarriage = rmarriage;
  }

  public String getRfamily() {
    return rfamily;
  }

  public void setRfamily(String rfamily) {
    this.rfamily = rfamily;
  }

  public String getRdepartment() {
    return rdepartment;
  }

  public void setRdepartment(String rdepartment) {
    this.rdepartment = rdepartment;
  }

  public String getRstate() {
    return rstate;
  }

  public void setRstate(String rstate) {
    this.rstate = rstate;
  }

  public String getGmtCreate() {
    return gmtCreate;
  }

  public void setGmtCreate(String gmtCreate) {
    this.gmtCreate = gmtCreate;
  }

  public String getRpass() {
    return rpass;
  }

  public void setRpass(String rpass) {
    this.rpass = rpass;
  }

  public String getRinfaction() {
    return rinfaction;
  }

  public void setRinfaction(String rinfaction) {
    this.rinfaction = rinfaction;
  }

  public String getUserloginid() {
    return userloginid;
  }

  public void setUserloginid(String userloginid) {
    this.userloginid = userloginid;
  }
}
