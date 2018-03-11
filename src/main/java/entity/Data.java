package entity;

import javax.persistence.*;

@Table(name = "data")
public class Data {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    /**
     * 商品名
     */
    @Column(name = "team")
    private String team;

    /**
     * 商品分类ID
     */
    @Column(name = "goodId")
    private Integer goodid;

    private String color;

    @Column(name = "No")
    private String no;

    @Column(name = "PLAYERNAME")
    private String playerName;

    @Column(name = "snumber")
    private Integer sNumber;

    @Column(name = "mnumber")
    private Integer mNumber;

    @Column(name = "lnumber")
    private Integer lNumber;

    @Column(name = "xlnumber")
    private Integer xlNumber;

    @Column(name = "xxlnumber")
    private Integer xxlNumber;

    @Column(name = "xxxlnumber")
    private Integer xxxlNumber;

    @Column(name = "MENO")
    private String meno;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取商品名
     *
     * @return team - 商品名
     */
    public String getTeam() {
        return team;
    }

    /**
     * 设置商品名
     *
     * @param team 商品名
     */
    public void setTeam(String team) {
        this.team = team;
    }

    /**
     * 获取商品分类ID
     *
     * @return goodId - 商品分类ID
     */
    public Integer getGoodid() {
        return goodid;
    }

    /**
     * 设置商品分类ID
     *
     * @param goodid 商品分类ID
     */
    public void setGoodid(Integer goodid) {
        this.goodid = goodid;
    }

    /**
     * @return color
     */
    public String getColor() {
        return color;
    }

    /**
     * @param color
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * @return No
     */
    public String getNo() {
        return no;
    }

    /**
     * @param no
     */
    public void setNo(String no) {
        this.no = no;
    }

    /**
     * @return PLAYER_NAME
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * @param playerName
     */
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    /**
     * @return s_number
     */
    public Integer getsNumber() {
        return sNumber;
    }

    /**
     * @param sNumber
     */
    public void setsNumber(Integer sNumber) {
        this.sNumber = sNumber;
    }

    /**
     * @return m_number
     */
    public Integer getmNumber() {
        return mNumber;
    }

    /**
     * @param mNumber
     */
    public void setmNumber(Integer mNumber) {
        this.mNumber = mNumber;
    }

    /**
     * @return l_number
     */
    public Integer getlNumber() {
        return lNumber;
    }

    /**
     * @param lNumber
     */
    public void setlNumber(Integer lNumber) {
        this.lNumber = lNumber;
    }

    /**
     * @return xl_number
     */
    public Integer getXlNumber() {
        return xlNumber;
    }

    /**
     * @param xlNumber
     */
    public void setXlNumber(Integer xlNumber) {
        this.xlNumber = xlNumber;
    }

    /**
     * @return xxl_number
     */
    public Integer getXxlNumber() {
        return xxlNumber;
    }

    /**
     * @param xxlNumber
     */
    public void setXxlNumber(Integer xxlNumber) {
        this.xxlNumber = xxlNumber;
    }

    /**
     * @return xxxl_number
     */
    public Integer getXxxlNumber() {
        return xxxlNumber;
    }

    /**
     * @param xxxlNumber
     */
    public void setXxxlNumber(Integer xxxlNumber) {
        this.xxxlNumber = xxxlNumber;
    }

    /**
     * @return MENO
     */
    public String getMeno() {
        return meno;
    }

    /**
     * @param meno
     */
    public void setMeno(String meno) {
        this.meno = meno;
    }
}