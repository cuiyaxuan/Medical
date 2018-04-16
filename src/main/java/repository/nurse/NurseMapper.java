package repository.nurse;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * The interface Nurse mapper.
 *
 * @author: WangXinYu
 * @describe: TODO
 * @create: 2018年04月16日 08:47
 */
@Repository
public interface NurseMapper {
    /**
     * List all document list.
     * 查询所有护理文书
     *
     * @return the list
     */
    @Select("SELECT * FROM m_nurse_documentation")
    List<Map<String, Object>> listAllDocument();

    /**
     * List all document list.
     * 根据 id 查询护理文书
     *
     * @param id the id
     * @return the list
     */
    @Select("SELECT * FROM m_nurse_documentation WHERE id =#{id}")
    Map<String, Object> getDocumentById(int id);
}
