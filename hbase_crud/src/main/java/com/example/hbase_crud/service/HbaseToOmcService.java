package com.example.hbase_crud.service;


import com.alibaba.fastjson.JSONObject;
import com.example.hbase_crud.config.IHbaseTemplateService;
import com.example.hbase_crud.entity.TargetParamsVo;
import com.example.hbase_crud.entity.TargetVO;
import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.hbase.filter.CompareFilter;
import org.apache.hadoop.hbase.filter.FilterList;
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;
import org.apache.hadoop.hbase.filter.SubstringComparator;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.hadoop.hbase.HbaseOperations;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * @author ycg
 *
 */
@Service
public class HbaseToOmcService implements  IHbaseToOmcService{


    private static final long serialVersionUID = 1042946646492744467L;

    @Autowired
    private IHbaseTemplateService hbaseTemplateService;
    @Override
    public Serializable getTargetInfos(String jsonString) throws Exception {
        JSONObject jsonObject=JSONObject.parseObject(jsonString);
        List<TargetParamsVo> paramsVoList=jsonObject.getJSONArray("targetParams").toJavaList(TargetParamsVo.class);


//        List<Filter> list=new ArrayList<>();
//        List<Filter> cellIdList=new ArrayList<>();
//        List<Filter> targetSetList=new ArrayList<>();
//        List<Filter> targetSonSetList=new ArrayList<>();
//        List<Filter> targetList=new ArrayList<>();

        String targetSet=null;
        String targetSonSet=null;
        String target=null;
        String cellId=null;
        FilterList filterList=new FilterList(FilterList.Operator.MUST_PASS_ONE);
        FilterList queryFilters=null;
        for(TargetParamsVo tpv:paramsVoList){
//            targetSet=tpv.getTargetSet();
//            targetSonSet=tpv.getTargetSonSet();
//            target=tpv.getTarget();
//            cellId=tpv.getCellId();

            queryFilters=new FilterList(FilterList.Operator.MUST_PASS_ALL);
            if(StringUtils.isNotBlank(cellId)){
                queryFilters.addFilter(new SingleColumnValueFilter(Bytes.toBytes("info"),Bytes.toBytes("cellId"),
                        CompareFilter.CompareOp.EQUAL,new SubstringComparator(cellId)));

            }
            if(StringUtils.isNotBlank(targetSet)){
                queryFilters.addFilter(new SingleColumnValueFilter(Bytes.toBytes("info"),Bytes.toBytes("targetSet"),
                        CompareFilter.CompareOp.EQUAL,new SubstringComparator(targetSet)));
            }
            if(StringUtils.isNotBlank(targetSonSet)){
                queryFilters.addFilter(new SingleColumnValueFilter(Bytes.toBytes("info"),Bytes.toBytes("targetSonSet"),
                        CompareFilter.CompareOp.EQUAL,new SubstringComparator(targetSonSet)));

            }
            if(StringUtils.isNotBlank(target)){
                queryFilters.addFilter(new SingleColumnValueFilter(Bytes.toBytes("info"),Bytes.toBytes("target"),
                        CompareFilter.CompareOp.EQUAL,new SubstringComparator(target)));

            }
//        list.add( new RowFilter(CompareFilter.CompareOp.EQUAL, new SubstringComparator("cell")));

//        https://blog.csdn.net/lr131425/article/details/72676254
            //     new BinaryPrefixComparator(value) //匹配字节数组前缀
////       new RegexStringComparator(expr) // 正则表达式匹配
//     new SubstringComparator(substr)// 子字符串匹配
//            new FamilyFilter(CompareFilter.CompareOp.EQUAL,new BinaryComparator(Bytes.toBytes("info")));

            filterList.addFilter(queryFilters);

        }

        //配置查询过滤器
//        FilterList cellIdFilters=new FilterList(FilterList.Operator.MUST_PASS_ONE,cellIdList);
//        FilterList targetSetFilters=new FilterList(FilterList.Operator.MUST_PASS_ONE,targetSetList);
//        FilterList targetSonSetFilters=new FilterList(FilterList.Operator.MUST_PASS_ONE,targetSonSetList);
//        FilterList targetFilters=new FilterList(FilterList.Operator.MUST_PASS_ONE,targetList);

        List<TargetVO> rsList=hbaseTemplateService.getListByCondition(TargetVO.class,"target",filterList);



        return (Serializable)rsList;
    }
}