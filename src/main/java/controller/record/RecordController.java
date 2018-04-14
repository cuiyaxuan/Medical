package controller.record;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import service.record.RecordService;

/**
 * @author: WangXinYu
 * @describe: TODO
 * @create: 2018年04月14日 21:57
 **/
@Controller
@RequestMapping("record")
public class RecordController {
    @Autowired
    RecordService recordService;
}
