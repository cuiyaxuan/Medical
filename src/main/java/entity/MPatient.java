package entity;


import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author WangXinYu
 */
public class MPatient {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private long id;

  @Column(name = "pname")
  private String pname;

  @Column(name = "psex")
  private long psex;

  @Column(name = "page")
  private long page;

  @Column(name = "porigin")
  private String porigin;

  @Column(name = "pmarriage")
  private long pmarriage;

  @Column(name = "pbirthplace")
  private String pbirthplace;

  @Column(name = "pworkplace")
  private String pworkplace;

  @Column(name = "pwork")
  private String pwork;

  @Column(name = "pliveplace")
  private String pliveplace;

  @Column(name = "phistory")
  private String phistory;

  @Column(name = "padmissiontime")
  private Date padmissiontime;

  @Column(name = "phistorytime")
  private Date phistorytime;

  @Column(name = "gmt_create")
  private String gmtCreate;

  @Column(name = "gmt_motified")
  private String gmtMotified;

  @Column(name = "precure")
  private String precure;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getPname() {
    return pname;
  }

  public void setPname(String pname) {
    this.pname = pname;
  }


  public long getPsex() {
    return psex;
  }

  public void setPsex(long psex) {
    this.psex = psex;
  }


  public long getPage() {
    return page;
  }

  public void setPage(long page) {
    this.page = page;
  }


  public String getPorigin() {
    return porigin;
  }

  public void setPorigin(String porigin) {
    this.porigin = porigin;
  }


  public long getPmarriage() {
    return pmarriage;
  }

  public void setPmarriage(long pmarriage) {
    this.pmarriage = pmarriage;
  }


  public String getPbirthplace() {
    return pbirthplace;
  }

  public void setPbirthplace(String pbirthplace) {
    this.pbirthplace = pbirthplace;
  }


  public String getPworkplace() {
    return pworkplace;
  }

  public void setPworkplace(String pworkplace) {
    this.pworkplace = pworkplace;
  }


  public String getPwork() {
    return pwork;
  }

  public void setPwork(String pwork) {
    this.pwork = pwork;
  }


  public String getPliveplace() {
    return pliveplace;
  }

  public void setPliveplace(String pliveplace) {
    this.pliveplace = pliveplace;
  }


  public String getPhistory() {
    return phistory;
  }

  public void setPhistory(String phistory) {
    this.phistory = phistory;
  }


  public Date getPadmissiontime() {
    return padmissiontime;
  }

  public void setPadmissiontime(Date padmissiontime) {
    this.padmissiontime = padmissiontime;
  }


  public Date getPhistorytime() {
    return phistorytime;
  }

  public void setPhistorytime(Date phistorytime) {
    this.phistorytime = phistorytime;
  }


  public String getGmtCreate() {
    return gmtCreate;
  }

  public void setGmtCreate(String gmtCreate) {
    this.gmtCreate = gmtCreate;
  }


  public String getGmtMotified() {
    return gmtMotified;
  }

  public void setGmtMotified(String gmtMotified) {
    this.gmtMotified = gmtMotified;
  }


  public String getPrecure() {
    return precure;
  }

  public void setPrecure(String precure) {
    this.precure = precure;
  }

}
