package cn.com.hbase_phoenix.service.impl;

import cn.com.hbase_phoenix.service.DataService;
import cn.com.hbase_phoenix.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import javax.sql.RowSet;
import java.util.List;
import java.util.Map;

/**
 * @autor
 */

@Service
public class DataServiceImpl  implements DataService {

    @Autowired
    @Qualifier("phoenixJdbcTemplate")
    JdbcTemplate  jdbcTemplate;

    @Override
    public ResultVO add() {
        return jdbcTemplate.update("upsert into org_dept_nc (id,NAME ) VALUES ('8888888888888','dktest')") == 1?
                new ResultVO(true,"插入成功！"):new ResultVO(false,"插入失败！");
    }
    @Override
    public int countDept() {
        return jdbcTemplate.queryForObject("select count(1) from org_dept_nc ",Integer.class);
    }
    @Override
    public ResultVO delete() {
       return jdbcTemplate.update("delete from org_dept_nc WHERE ID = '8888888888888' ") == 1?
                new ResultVO(true,"删除成功！"):new ResultVO(false,"删除失败！");
    }
    @Override
    public ResultVO update() {
        return jdbcTemplate.update("upsert into org_dept_nc (id,NAME ) VALUES ('0001N110000000VHA5QR','北区共享组01')") == 1?
                new ResultVO(true,"更新成功！"):new ResultVO(false,"更新失败！");
    }
    @Override
    public List<Map<String, Object>> query() {
        return jdbcTemplate.queryForList("select * from org_dept_nc ");
    }
}
