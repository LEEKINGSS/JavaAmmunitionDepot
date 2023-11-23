package cn.liking.service;


/**
 * 数据库备份接口
 * @author liking
 */
public interface IDataBackService {
    /**
     * 备份员工信息表
     */
    void backupEmployees();

    void recoverEmployees();
}
