package cn.liking.service.impl;

import cn.liking.entity.Employees;
import cn.liking.service.IDataBackService;
import cn.liking.service.IEmployeesService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.io.IOUtils;


import java.io.*;
import java.util.*;

/**
 * 数据库备份接口实现类
 *
 * @author liking
 */
@Slf4j
@Service
public class DataBackServiceImpl implements IDataBackService {

    @Autowired
    private IEmployeesService iEmployeesService;

    /**
     * 标记更新时间用来判断是否需要全量备份
     */
    static Map<String, String> updateTimeMap = new HashMap<>();

    /**
     * 存入文件流
     *
     * @param inputStream
     * @param filePath
     */
    public void saveInputStreamToFile(InputStream inputStream, String filePath) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
             FileWriter writer = new FileWriter(filePath, true)) {
            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line);
                writer.write(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取文件流
     *
     * @param filePath
     * @return
     */
    public InputStream readFileToInputStream(String filePath) {
        try {
            File file = new File(filePath);
            return new FileInputStream(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 备份员工信息表
     */
    public void backupEmployees() {
        /**
         * 1.判断是否需要全量备份
         */
        String updateTime = updateTimeMap.get("employees");
        if (updateTime == null) {
            //全量备份
            //查询所有员工信息
            List<Employees> employeesList = iEmployeesService.list();
            //将数据的实现类与数据封装在一起方便后续反射使用
            List<Map<String,Object>> mapList = new ArrayList<>();
            Map<String,Object> map = new HashMap<>();
            map.put("employeesServiceImpl",employeesList);
            mapList.add(map);
            //把数据写入到文件中
            String str = JSON.toJSONString(mapList);
            InputStream inputStream = IOUtils.toInputStream(str);
            saveInputStreamToFile(inputStream, "employees.txt");
            Date date = new Date();
            updateTimeMap.put("employees", date.toString());
        } else {
            //增量备份
            //查询更新时间大于上次备份时间的员工信息
            List<Employees> employeesList = iEmployeesService.list(new LambdaQueryWrapper<Employees>().between(Employees::getUpdateTime, updateTime, new Date()));
            //把数据追加到文件中
            //将数据的实现类与数据封装在一起方便后续反射使用
            List<Map<String,Object>> mapList = new ArrayList<>();
            Map<String,Object> map = new HashMap<>();
            map.put("employeesServiceImpl",employeesList);
            mapList.add(map);
            String str = JSON.toJSONString(mapList);
            InputStream inputStream = IOUtils.toInputStream(str);
            saveInputStreamToFile(inputStream, "employees.txt");
            Date date = new Date();
            updateTimeMap.put("employees", date.toString());
        }
    }

    /**
     * 恢复员工信息表
     */
    public void recoverEmployees() {
        //清空员工信息表
        iEmployeesService.remove(null);
        //从文本中读取数据流
        InputStream inputStream = readFileToInputStream("employees.txt");
        //将数据流转换为字符串
        String str = "";
        try {
            str = IOUtils.toString(inputStream);
            System.out.println(str);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //将字符串转换为对象
        JSONArray jsonArray = JSON.parseArray(str);
        for (int i = 0; i < jsonArray.size(); i++) {
            Map<String,Object> map = (Map<String, Object>) jsonArray.get(i);
            Set<String> keySet = map.keySet();
            for (String key : keySet) {
                List<Employees> employeesList = (List<Employees>) map.get(key);
                //将数据写入到数据库中
                iEmployeesService.saveBatch(employeesList);
//                log.info(employeesList.toString());
            }
        }
    }

}
