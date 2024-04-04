//
// Created by sam on 2018/9/18.
//
#include "physicalplan/physicalplan.h"

/*执行delete语句的物理计划，返回删除的记录条数
 * 返回大于等于0的值，表示删除的记录条数；
 * 返回小于0的值，表示删除过程中出现错误。
 * */
int plan_execute_delete(dongmendb *db, sql_stmt_delete *sqlStmtDelete,  transaction *tx){

    /*
     * 删除语句以select的物理操作为基础实现。
     * 1. 使用 sql_stmt_delete 的条件参数，调用 physical_scan_select_create 创建select的物理计划并初始化;
     * 2. 执行 select 的物理计划，完成 delete 操作
     * */

    // 设定记录被删除的条数
    size_t deleteCount = 0;

    // delete物理计划：创建物理计划并初始化(此处sqlStmtDelete解析结果若没有限定where条件则获取的scan为null，需做相应处理)
    // plan_execute_select中包含对select * 的处理（若指定内容没有正常处理则报错）
     physical_scan* deleteScan = physical_scan_generate(db, sqlStmtDelete->where, tx); // 针对拼接语句构建
    // 指针置前
    deleteScan->beforeFirst(deleteScan);
    // 遍历结果集（扫描物理数据表）
    while (deleteScan->next(deleteScan)){
        deleteScan->deleterec(deleteScan);
        printf("\n");
        // 调用删除操作执行完成,成功记录数加1
        deleteCount++;
    }
    // 关闭scan
    deleteScan->close(deleteScan);
    // 返回操作记录条数 DONGMENDB_OK 为0
    return deleteCount;

};
