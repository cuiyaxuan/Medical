package entity;


import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
  private long rdepartment;
  @Column(name = "rstate")
  private long rstate;


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


  public long getRdepartment() {
    return rdepartment;
  }

  public void setRdepartment(long rdepartment) {
    this.rdepartment = rdepartment;
  }


  public long getRstate() {
    return rstate;
  }

  public void setRstate(long rstate) {
    this.rstate = rstate;
  }

}
