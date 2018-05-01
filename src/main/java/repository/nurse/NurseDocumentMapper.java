package repository.nurse;

import entity.MNurseDocumentation;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.BaseMapper;

/**
 * @author: WangXinYu
 * @describe: TODO
 * @create: 2018年05月01日 10:53
 **/
@Repository
public interface NurseDocumentMapper extends BaseMapper<MNurseDocumentation> {
}
