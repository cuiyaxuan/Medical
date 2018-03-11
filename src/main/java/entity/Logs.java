package entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
@Table(name = "logs")
public class Logs {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 操作时间
     */
    private Date time;

    /**
     * 操作人
     */
    private String username;

    /**
     * 描述
     */
    private String details;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取操作时间
     *
     * @return time - 操作时间
     */
    public Date getTime() {
        return time;
    }

    /**
     * 设置操作时间
     *
     * @param time 操作时间
     */
    public void setTime(Date time) {
        this.time = time;
    }

    /**
     * 获取操作人
     *
     * @return username - 操作人
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置操作人
     *
     * @param username 操作人
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取描述
     *
     * @return details - 描述
     */
    public String getDetails() {
        return details;
    }

    /**
     * 设置描述
     *
     * @param details 描述
     */
    public void setDetails(String details) {
        this.details = details;
    }
}